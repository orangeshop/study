package practice.jpa.domain.onetoonetwoway.user;

import jakarta.persistence.*;
import lombok.*;
import practice.jpa.domain.onetoonetwoway.study.Lecture;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student {
    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;
    private String email;

    /*
    * one to one 양방향 매핑
    * student - lecture
    *
    * */

    @OneToOne
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;
}
