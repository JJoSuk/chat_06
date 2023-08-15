package test.login.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String s3Key; // S3 내의 파일 위치

    @Column(nullable = false)
    private String fileName; // 원본 파일 이름

    @ManyToOne
    @JoinColumn(name = "room_id")
    private ChatRoom chatRoom; // 연관된 채팅방
}