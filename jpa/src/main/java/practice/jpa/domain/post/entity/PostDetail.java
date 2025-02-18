package practice.jpa.domain.post.entity;


import jakarta.persistence.*;

@Entity
public class PostDetail {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(nullable = false)
    private String Geo;

    @Column(nullable = false)
    private int likes;

    @Column(nullable = false)
    private int dislikes;


}

