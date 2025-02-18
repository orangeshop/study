package practice.jpa.domain.comment.entity;

import jakarta.persistence.*;
import practice.jpa.domain.post.entity.Post;

import java.time.LocalDateTime;

@Entity
public class Comment {
    @Id
    @GeneratedValue
    private Long id;

    @JoinColumn(name = "post_id")
    @OneToOne
    private Post post;

    private String content;

    private LocalDateTime createDate;

}
