package test.login.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import test.login.dto.ChatRoomDto;
import test.login.entity.ChatRoom;
import test.login.service.ChatRoomService;
import test.login.service.ChatService;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatRoomController {

    // ChatRepository Bean 가져오기
    private final ChatService chatService;
    private final ChatRoomService chatRoomService;

    // 채팅방 생성
    // 채팅방 생성 후 다시 / 로 return
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/chat/createroom")
    public String createRoom(@RequestParam("roomName") String roomName,
                             @RequestParam("roomPwd") String roomPwd,
                             @RequestParam(value = "maxUserCnt", defaultValue = "2") String maxUserCnt,
                             @RequestParam("secretChk") String secretChk,
                             RedirectAttributes attr) {

        // 매개변수 : 방 이름, 패스워드, 방 잠금 여부, 방 인원수
        ChatRoomDto room;

        room = chatService.createChatRoom(roomName, roomPwd, Boolean.parseBoolean(secretChk), Integer.parseInt(maxUserCnt));
        log.info("CREATE Chat Room {}", room);
        attr.addFlashAttribute("roomName", room);

        return "redirect:/";
    }

    // 채팅방 입장 화면
    // 파라미터로 넘어오는 roomId 를 확인후 해당 roomId 를 기준으로
    // 채팅방을 찾아서 클라이언트를 chatroom 으로 보낸다.
    @RequestMapping("/chat/room")
    public String roomDetail(Model model, @RequestParam String roomId) {
        model.addAttribute("roomId", roomId);

        // 로그인한 사용자의 이름을 가져오는 코드
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        ChatRoom room = chatRoomService.findRoomById(roomId);
        model.addAttribute("room", room);

        // 모델에 사용자 이름 추가
        model.addAttribute("username", username);

        return "/chatroom";
    }

    // 채팅방 비밀번호 확인
    @PostMapping("/chat/confirmPwd/{roomId}")
    @ResponseBody
    public boolean confirmPwd(@PathVariable String roomId, @RequestParam String roomPwd){

        // 넘어온 roomId 와 roomPwd 를 이용해서 비밀번호 찾기
        // 찾아서 입력받은 roomPwd 와 room pwd 와 비교해서 맞으면 true, 아니면  false
        return chatService.confirmPwd(roomId, roomPwd);
    }

    // 채팅방 삭제
    @GetMapping("/chat/delRoom/{roomId}")
    public String delChatRoom(@PathVariable String roomId){

        // roomId 기준으로 chatRoomMap 에서 삭제, 해당 채팅룸 안에 있는 사진 삭제
        chatService.delChatRoom(roomId);

        return "redirect:/";
    }

    @GetMapping("/chat/chkUserCnt/{roomId}")
    @ResponseBody
    public boolean chUserCnt(@PathVariable String roomId){

        return chatService.chkRoomUserCnt(roomId);
    }
}