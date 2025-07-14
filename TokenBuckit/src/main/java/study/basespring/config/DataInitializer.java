package study.basespring.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import study.basespring.entity.Product;
import study.basespring.entity.User;
import study.basespring.repo.ProductRepository;
import study.basespring.repo.UserRepository;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Value("${server.port}")
    private String serverPort;

    @Bean
    @Profile("!test") // 테스트 환경에서는 실행하지 않음
    public CommandLineRunner initData() {
        return args -> {
            log.info("=== 초기 데이터 생성 시작 ===");

            // 사용자 데이터 생성
            if (userRepository.count() == 0) {
                User user1 = User.createUser(
                        "admin@example.com",
                        "admin123",
                        "관리자",
                        "서울시 강남구"
                );

                User user2 = User.createUser(
                        "user@example.com",
                        "user123",
                        "일반사용자",
                        "서울시 서초구"
                );

                userRepository.save(user1);
                userRepository.save(user2);
                log.info("사용자 데이터 2건 생성 완료");
            }

            // 상품 데이터 생성
            if (productRepository.count() == 0) {
                Product product1 = Product.createProduct(
                        "Spring Boot 입문서",
                        25000L,
                        100L
                );

                Product product2 = Product.createProduct(
                        "JPA 프로그래밍",
                        30000L,
                        50L
                );

                Product product3 = Product.createProduct(
                        "클린 코드",
                        33000L,
                        75L,
                        "로버트 마틴의 클린 코드"
                );

                productRepository.save(product1);
                productRepository.save(product2);
                productRepository.save(product3);
                log.info("상품 데이터 3건 생성 완료");
            }

            log.info("=== 초기 데이터 생성 완료 ===");
            log.info("H2 Console URL: http://localhost:" + serverPort + "/h2-console");
            log.info("Swagger UI URL: http://localhost:" + serverPort + "/swagger-ui.html");
            log.info("JDBC URL: jdbc:h2:file:./data/h2db");
            log.info("Username: sa");
            log.info("Password: (비어있음)");
        };
    }
}
