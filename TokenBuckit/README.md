# BaseSpring - Token Bucket Rate Limiting Project

## 🎯 프로젝트 개요
Token Bucket 알고리즘을 적용한 Rate Limiting 구현 프로젝트

## 🔥 Token Bucket을 선택한 이유

### 1. **API 남용 방지의 필요성**

- **문제상황**: 무제한 API 호출로 인한 서버 과부하
- **해결필요**: 악의적 사용자의 DoS 공격 차단


// Token Bucket: 버스트 트래픽 허용 + 평균 제한 ✅


### 3. **실제 구현한 차별화 포인트**
```java
// VIP 사용자 구분
private final Bandwidth defaultLimit = Bandwidth.classic(5, Refill.greedy(5, Duration.ofSeconds(10)));
private final Bandwidth vipLimit = Bandwidth.classic(20, Refill.greedy(20, Duration.ofSeconds(10)));
```
- 일반 사용자: 10초당 5회
- VIP 사용자: 10초당 20회
- **근거**: 실제 서비스에서 유료/무료 사용자 구분 필요

### 4. **기술적 선택 근거**

#### Bucket4j 라이브러리 선택
```java
// 직접 구현 대신 검증된 라이브러리 사용
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bandwidth;
```
- **이유**: 멀티스레드 안전성 보장
- **효과**: 개발 시간 단축 + 안정성 확보

#### Interceptor 패턴 적용
```java
@Override
public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
    // 모든 /api/** 요청을 사전 차단
}
```
- **이유**: 비즈니스 로직과 분리된 횡단 관심사 처리
- **효과**: 코드 재사용성 + 유지보수성 향상

#### IP 기반 Fallback
```java
if(userId == null || userId.isEmpty()) {
    userId = request.getRemoteAddr(); // IP로 대체
}
```
- **이유**: 인증되지 않은 사용자도 제한 필요
- **효과**: 익명 사용자 남용 방지


## 🏗 아키텍처
```
Request → RateLimitInterceptor → TokenBucket 검증 → Controller
                ↓
           Rate Limit 초과시 429 응답
```

이 프로젝트는 실제 프로덕션 환경에서 발생할 수 있는 API 남용 문제를 해결하기 위한 실습용 구현입니다.
