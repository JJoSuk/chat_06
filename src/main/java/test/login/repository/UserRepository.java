package test.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.login.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByChatRoom_RoomId(String roomId);
    User findByUserUUIDAndChatRoom_RoomId(String userUUID, String roomId);
    User findByEmail(String email);
}