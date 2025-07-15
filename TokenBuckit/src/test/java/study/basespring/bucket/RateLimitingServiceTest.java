package study.basespring.bucket;

import io.github.bucket4j.Bucket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("RateLimitingService 단위 테스트")
class RateLimitingServiceTest {
    @InjectMocks
    private RateLimitingService rateLimitingService;

    @Test
    @DisplayName("기본 사용자: 초기 버스트 용량 내에서 요청이 허용되어야 한다")
    void defaultUserShouldAllowInitialBurst(){
        String userId = "test_user_burst";
        Bucket bucket = rateLimitingService.resoleBucket(userId, false);

        for(int i =0; i < 5; i++){
            assertThat(bucket.tryConsume(1)).isTrue();
        }

        assertThat(bucket.getAvailableTokens()).isEqualTo(0);
    }

    @Test
    @DisplayName("기본 사용자: 버스트 용량을 초과하면 요청이 거부되어야 한다")
    void defaultUserShouldNotAllowInitialBurst(){
        String userId = "test_user_burst";
        Bucket bucket = rateLimitingService.resoleBucket(userId, false);

        for(int i =0; i < 5; i++){
            bucket.tryConsume(1);
        }

        assertThat(bucket.tryConsume(1)).isFalse();
        assertThat(bucket.getAvailableTokens()).isEqualTo(0);
    }

    @Test
    @DisplayName("기본 사용자: 시간 경과 후 토큰이 리필되어야 한다")
    void defaultUserShouldRefillTokensOverTime() throws InterruptedException {
        String userId = "test_user_burst";
        Bucket bucket = rateLimitingService.resoleBucket(userId, false);

        for(int i =0; i < 5; i++){
            bucket.tryConsume(1);
        }

        assertThat(bucket.getAvailableTokens()).isEqualTo(0);

        Thread.sleep(Duration.ofSeconds(2).toMillis() + 500);

        assertThat(bucket.getAvailableTokens()).isGreaterThanOrEqualTo(1);

        assertThat(bucket.tryConsume(1)).isTrue();
        assertThat(bucket.getAvailableTokens()).isEqualTo(0);

    }

    @Test
    @DisplayName("동시성: 여러 스레드에서 동시에 요청할 때 Rate Limit이 올바르게 작동해야 한다")
    void concurrentRequestsShouldRespectRateLimit() throws InterruptedException {
        String userId = "test_user_burst";
        Bucket bucket = rateLimitingService.resoleBucket(userId, false);

        int numberOfThreads = 10;
        int requestPerThread = 1;

        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);
        AtomicInteger successfulRequests = new AtomicInteger(0);

        for(int i =0; i < numberOfThreads; i++){
            executorService.execute(() -> {
                try {
                    if(bucket.tryConsume(1)){
                        successfulRequests.incrementAndGet();
                    }
                }finally {
                    latch.countDown();
                }
            });
        }

        latch.await(5, TimeUnit.SECONDS);
        executorService.shutdown();

        assertThat(successfulRequests.get()).isEqualTo(5);
        assertThat(bucket.getAvailableTokens()).isEqualTo(0);

    }

    @Test
    @DisplayName("VIP 사용자: 더 높은 Rate Limit이 적용되어야 한다")
    void vipUser_ShouldHaveHigherRateLimit() {
        String vipUserId = "vip_user_A";
        Bucket vipBucket = rateLimitingService.resoleBucket(vipUserId, true); // VIP

        // VIP 버킷 용량은 20개 (RateLimitingService에서 정의한 대로)
        for (int i = 0; i < 20; i++) {
            assertThat(vipBucket.tryConsume(1)).isTrue();
        }
        assertThat(vipBucket.getAvailableTokens()).isEqualTo(0L);

        assertThat(vipBucket.tryConsume(1)).isFalse(); // 21번째는 거부
    }


}