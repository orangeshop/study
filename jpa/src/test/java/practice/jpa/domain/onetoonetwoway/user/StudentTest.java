package practice.jpa.domain.onetoonetwoway.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import practice.jpa.domain.onetoonetwoway.study.Lecture;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
class StudentTest {

    @PersistenceContext
    EntityManager entityManager ;

    @Test
    void oneToOneTwoWayTest(){
        Student student = new Student();
        student.setEmail("test@email.com");
        student.setFirstName("Test");
        student.setLastName("Test");

        Lecture lecture = new Lecture();
        lecture.setName("Test");

        student.setLecture(lecture);
        lecture.setStudent(student);

        System.out.println(student.getLecture().getName());
        System.out.println(lecture.getStudent().getEmail());

        entityManager.persist(lecture);
        entityManager.persist(student);
        entityManager.flush();

    }
}