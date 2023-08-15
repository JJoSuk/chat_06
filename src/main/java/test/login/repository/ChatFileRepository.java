package test.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.login.entity.ChatFile;
import test.login.entity.ChatRoom;

import java.util.List;

@Repository
public interface ChatFileRepository extends JpaRepository<ChatFile, Long> {
    List<ChatFile> findByChatRoom(ChatRoom chatRoom);
}