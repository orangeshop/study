package practice.jpa.domain.onetooneandoneway.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import practice.jpa.domain.onetooneandoneway.study.Study;

@Entity
@Table(name = "member")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    /*
    * 유저랑 스터디 단방향
    * 유저가 onwer
    * 1:1 매핑
    * */

    @OneToOne
    @JoinColumn(name = "study_id")
    private Study study;

}
