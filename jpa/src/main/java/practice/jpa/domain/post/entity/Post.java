package practice.jpa.domain.post.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import practice.jpa.domain.member.entity.Member;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Member_id")
    private Member member;

    @Column(nullable = false)
    private String context;

    @Column(nullable = false)
    private LocalDateTime createDate;


    private LocalDateTime updateDate;

}
