package study.basespring.bucket;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RateLimitingService {
    private final Map<String, Bucket> userBuckets = new ConcurrentHashMap<>();

    private final Bandwidth defaultLimit = Bandwidth.classic(5, Refill.greedy(5, Duration.ofSeconds(10)));

    private final Bandwidth vipLimit = Bandwidth.classic(20, Refill.greedy(20, Duration.ofSeconds(10)));

    public Bucket resoleBucket(String userId, boolean isVip) {

        System.out.println(userBuckets.get(userId));


        return userBuckets.computeIfAbsent(userId, k -> {
            if(isVip) {
                return Bucket.builder().addLimit(vipLimit).build();
            }else{
                return Bucket.builder().addLimit(defaultLimit).build();
            }
        });
    }

    public Bucket resoleBucket(String userId) {
        return resoleBucket(userId, true);
    }
}
