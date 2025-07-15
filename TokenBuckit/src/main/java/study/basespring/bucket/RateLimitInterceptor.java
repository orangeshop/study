package study.basespring.bucket;

import io.github.bucket4j.Bucket;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@AllArgsConstructor
public class RateLimitInterceptor implements HandlerInterceptor {
    private final RateLimitingService rateLimitingService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String userId = request.getHeader("userId");
        System.out.println(userId);


        if(userId == null || userId.isEmpty()) {
            userId = request.getRemoteAddr();
        }

        Bucket userBucket = rateLimitingService.resoleBucket(userId);

        if(userBucket.tryConsume(1)){
            return true;
        }else{
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.getWriter().write("Too Many Requests. Please try again later.");
            response.setContentType("text/plain");
            return false; // 요청 처리 중단
        }
    }
}
