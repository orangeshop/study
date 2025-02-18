package practice.jpa.domain.member.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import practice.jpa.domain.post.entity.Post;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private LocalDateTime craeteDate;

    private LocalDateTime updateDate;

    @OneToMany(mappedBy = "member")
    private List<Post> postList = new ArrayList<>();

}
