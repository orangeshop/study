package practice.jpa.domain.onetoonetwoway.study;

import jakarta.persistence.*;
import lombok.*;
import practice.jpa.domain.onetoonetwoway.user.Student;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Lecture {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToOne(mappedBy = "lecture")
    private Student student;
}
