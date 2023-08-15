package test.login.service;

import test.login.dto.ChatRoomDto;

import java.util.List;

public interface ChatService {

    public List<ChatRoomDto> findAllRoom();
    public ChatRoomDto findRoomById(String roomId);
    public ChatRoomDto createChatRoom(String roomName, String roomPwd, boolean secretChk, int maxUserCnt);
    public void plusUserCnt(String roomId);
    public void minusUserCnt(String roomId);
    public boolean chkRoomUserCnt(String roomId);

    // 채팅방 유저 리스트에 유저 추가
    public String addUser(String roomId, String userName);

    public void delUser(String roomId, String userUUID);
    public boolean confirmPwd(String roomId, String roomPwd);
    public void delChatRoom(String roomId);

    String getUserName(String roomId, String userUUID);
}
