package study.sqlstudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class SqlStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SqlStudyApplication.class, args);
    }

}
