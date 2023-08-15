package test.login.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import test.login.service.ChatService;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MainChatController {

    private final ChatService chatService;

    // 채팅 리스트 화면
    // / 로 요청이 들어오면 전체 채팅룸 리스트를 담아서 return
    @GetMapping("/")
    public String goChatRoom(Model model){

        model.addAttribute("list", chatService.findAllRoom());
        // model.addAttribute("user", "hey");
        log.info("SHOW ALL ChatList {}", chatService.findAllRoom());
        return "roomlist";
    }
}
