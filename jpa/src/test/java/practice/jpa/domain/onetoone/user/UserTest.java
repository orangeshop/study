package practice.jpa.domain.onetoone.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import practice.jpa.domain.onetooneandoneway.study.Study;
import practice.jpa.domain.onetooneandoneway.user.User;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserTest {

    @PersistenceContext
    EntityManager entityManager ;

    @Test
    void oneToOneTest(){
        User user = new User();
        user.setEmail("test@email.com");
        user.setFirstName("test");
        user.setLastName("test");

        Study study = new Study();
        study.setName("test");
        user.setStudy(study);

        entityManager.persist(user);
        entityManager.persist(study);
        entityManager.flush();


        assertEquals(user.getStudy().getName(), study.getName());
    }

}