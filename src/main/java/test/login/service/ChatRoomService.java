package test.login.service;

import test.login.entity.ChatRoom;

public interface ChatRoomService {
    ChatRoom findRoomById(String roomId);
}