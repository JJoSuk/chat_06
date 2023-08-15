package test.login.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import test.login.entity.ChatRoom;
import test.login.repository.ChatRoomRepository;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    @Override
    public ChatRoom findRoomById(String roomId) {
        return chatRoomRepository.findById(roomId).orElse(null);
    }
}