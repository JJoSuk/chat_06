package test.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.login.entity.Chat;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
}