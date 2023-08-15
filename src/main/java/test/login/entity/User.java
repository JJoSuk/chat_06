package test.login.entity;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_uuid", unique = true)
    private String userUUID;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(nullable = false, unique = true)
    private String email; // New Field: Email

    @Column(nullable = false)
    private String password; // New Field: Encrypted Password

    @ManyToOne
    @JoinColumn(name = "chatroom_id")
    private ChatRoom chatRoom;

    public User(String userUUID, String username, ChatRoom chatRoom) {
        this.userUUID = userUUID;
        this.username = username;
        this.chatRoom = chatRoom;
    }
}