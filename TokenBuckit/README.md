# Base Spring Project

## 프로젝트 개요

이 프로젝트는 Spring Boot를 기반으로 한 기본 웹 애플리케이션 템플릿으로, **Spring Batch**, **Spring Security** 등 전역적으로 사용되는 다양한 Spring 프로젝트에서 공통으로 활용할 수 있는 기반 구조를 제공합니다.

주문 관리 시스템을 예시로 구현하여 실제 비즈니스 로직이 어떻게 구성되는지 보여주며, 이를 통해 다른 프로젝트에서도 쉽게 확장하고 적용할 수 있도록 설계되었습니다.

## 기술 스택

- **Java 17**
- **Spring Boot 3.5.3**
- **Spring Data JPA**
- **H2 Database** (개발/테스트용)
- **Lombok**
- **Gradle**
- **Swagger/OpenAPI 3.0** (API 문서화)

## 프로젝트 구조

```
src/main/java/study/basespring/
├── BaseSpringApplication.java      # Spring Boot 메인 애플리케이션
├── config/                         # 설정 클래스
│   ├── H2ServerConfig.java         # H2 데이터베이스 서버 설정
│   ├── DataInitializer.java        # 초기 데이터 설정
│   └── SwaggerConfig.java          # Swagger/OpenAPI 설정
├── controller/                     # REST API 컨트롤러 구현체
│   ├── api/                        # API 인터페이스 (Swagger 문서화)
│   │   ├── UserApi.java
│   │   ├── ProductApi.java
│   │   └── OrderApi.java
│   ├── UserController.java
│   ├── ProductController.java
│   └── OrderController.java
├── entity/                         # JPA 엔티티
│   ├── User.java
│   ├── Orders.java
│   ├── OrderItem.java
│   └── Product.java
├── repo/                           # JPA Repository 인터페이스
│   ├── UserRepository.java
│   ├── OrdersRepository.java
│   ├── OrderItemRepository.java
│   └── ProductRepository.java
├── service/                        # 비즈니스 로직 서비스
│   ├── user/
│   │   ├── UserService.java
│   │   └── UserServiceImpl.java
│   ├── order/
│   │   ├── OrderService.java
│   │   └── OrderServiceImpl.java
│   ├── product/
│   │   ├── ProductService.java
│   │   └── ProductServiceImpl.java
│   └── orderitem/
│       ├── OrderItemService.java
│       └── OrderItemServiceImpl.java
└── global/                         # 전역 설정 및 공통 모듈
    ├── GlobalExceptionHandler.java # 전역 예외 처리
    └── OrderStatus.java            # 주문 상태 Enum
```

## 주요 아키텍처 패턴

### 1. 계층형 아키텍처 (Layered Architecture)

프로젝트는 명확한 책임 분리를 위해 다음과 같은 계층 구조를 따릅니다:

- **Controller Layer**: HTTP 요청/응답 처리, DTO 변환
- **Service Layer**: 비즈니스 로직 처리, 트랜잭션 관리
- **Repository Layer**: 데이터베이스 접근, CRUD 작업
- **Entity Layer**: 도메인 모델, 데이터베이스 테이블 매핑

### 2. 의존성 주입 (Dependency Injection)

- `@RequiredArgsConstructor`를 통한 생성자 주입 방식 사용
- 인터페이스와 구현체 분리로 느슨한 결합 구현

### 3. RESTful API 설계

```java
// API 인터페이스에 Swagger 문서화
@Tag(name = "User", description = "사용자 관리 API")
@RequestMapping("/api/users")
public interface UserApi {
    @Operation(summary = "사용자 생성")
    @PostMapping
    ResponseEntity<User> createUser(@RequestBody CreateUserRequest request);
}

// 컨트롤러 구현체
@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {
    private final UserService userService;
    
    @Override
    public ResponseEntity<User> createUser(CreateUserRequest request) {
        // 비즈니스 로직 처리
    }
}
```

## 코드 흐름 예시

### 사용자 생성 플로우

1. **클라이언트 요청**
   ```
   POST /api/users
   {
     "email": "user@example.com",
     "password": "password",
     "name": "홍길동",
     "address": "서울시 강남구"
   }
   ```

2. **Controller에서 요청 수신**
   ```java
   @PostMapping
   public ResponseEntity<User> createUser(@RequestBody CreateUserRequest request) {
       User user = userService.createUser(...);
       return ResponseEntity.ok(user);
   }
   ```

3. **Service에서 비즈니스 로직 처리**
   ```java
   @Transactional
   public User createUser(String email, String password, String name, String address) {
       // 중복 이메일 검증
       // 패스워드 암호화 (추후 Spring Security 통합 시)
       // User 엔티티 생성 및 저장
   }
   ```

4. **Repository를 통한 데이터 저장**
   ```java
   public interface UserRepository extends JpaRepository<User, Long> {
       Optional<User> findByEmail(String email);
   }
   ```

## 전역 기능

### 1. 예외 처리 (GlobalExceptionHandler)

- `@RestControllerAdvice`를 통한 전역 예외 처리
- 일관된 에러 응답 형식 제공
- 비즈니스 예외와 시스템 예외 구분 처리

```java
@ExceptionHandler(IllegalArgumentException.class)
public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException e) {
    return ResponseEntity.badRequest()
        .body(new ErrorResponse("BAD_REQUEST", e.getMessage()));
}
```

### 2. 엔티티 관계 설정

- **User ↔ Orders**: 일대다 관계 (한 사용자가 여러 주문)
- **Orders ↔ OrderItem**: 일대다 관계 (한 주문에 여러 주문 항목)
- **OrderItem ↔ Product**: 다대일 관계 (여러 주문 항목이 하나의 상품 참조)

## 확장 가능성

이 기본 구조는 다음과 같은 기능들을 쉽게 추가할 수 있도록 설계되었습니다:

### 1. Spring Security 통합
- 인증/인가 처리
- JWT 토큰 기반 인증
- 권한 기반 접근 제어

### 2. Spring Batch 통합
- 대량 데이터 처리
- 주기적인 배치 작업
- 데이터 마이그레이션

### 3. 추가 기능 모듈
- 캐싱 (Spring Cache)
- 이벤트 처리 (Spring Events)
- 메시징 (Spring AMQP/Kafka)
- API 문서화 (Swagger/OpenAPI) ✅ 구현 완료

## H2 데이터베이스 설정

이 프로젝트는 Spring Boot 실행 시 H2 데이터베이스가 자동으로 시작되도록 구성되어 있습니다.

### H2 데이터베이스 특징
- **파일 기반 저장**: 데이터가 `./data/h2db` 파일에 저장되어 재시작 후에도 유지됩니다.
- **자동 시작**: Spring Boot 실행 시 H2 서버가 자동으로 시작됩니다.
- **TCP 서버**: 외부 도구(IntelliJ, DBeaver 등)에서도 접속 가능합니다 (포트: 9092).
- **웹 콘솔**: 브라우저에서 데이터베이스를 관리할 수 있습니다.

### H2 접속 정보
- **H2 Console URL**: http://localhost:8080/h2-console
- **JDBC URL**: `jdbc:h2:file:./data/h2db`
- **TCP 접속 URL**: `jdbc:h2:tcp://localhost:9092/./data/h2db`
- **Username**: sa
- **Password**: (비어있음)

### 초기 데이터
애플리케이션 시작 시 다음 데이터가 자동으로 생성됩니다:
- 사용자 2명 (admin@example.com, user@example.com)
- 상품 3개 (Spring Boot 입문서, JPA 프로그래밍, 클린 코드)

## 실행 방법

1. **프로젝트 클론**
   ```bash
   git clone [repository-url]
   cd BaseStringProject
   ```

2. **애플리케이션 실행**
   ```bash
   ./gradlew bootRun
   ```

3. **H2 콘솔 접속**
   - 브라우저에서 http://localhost:8081/h2-console 접속
   - JDBC URL: `jdbc:h2:file:./data/h2db`
   - Connect 버튼 클릭

4. **Swagger UI 접속**
   - 브라우저에서 http://localhost:8081/swagger-ui.html 접속
   - API 문서 확인 및 테스트 가능

## Swagger/OpenAPI 문서화

이 프로젝트는 Swagger(OpenAPI 3.0)를 사용하여 API 문서를 자동 생성합니다.

### 주요 기능
- **대화형 API 문서**: Swagger UI를 통해 API를 직접 테스트할 수 있습니다.
- **API 스펙 자동 생성**: 코드의 어노테이션을 기반으로 문서가 자동 생성됩니다.
- **요청/응답 예시**: 각 API의 요청 본문과 응답 형식을 미리 확인할 수 있습니다.

### Swagger 접속 정보
- **Swagger UI**: http://localhost:8081/swagger-ui.html
- **API Docs (JSON)**: http://localhost:8081/api-docs

### Swagger 어노테이션 사용 예시
```java
// API 인터페이스에 Swagger 어노테이션 작성
@Tag(name = "User", description = "사용자 관리 API")
public interface UserApi {
    @Operation(summary = "사용자 생성", description = "새로운 사용자를 생성합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "사용자 생성 성공"),
        @ApiResponse(responseCode = "400", description = "잘못된 요청")
    })
    @PostMapping
    ResponseEntity<User> createUser(@RequestBody CreateUserRequest request);
}

// 컨트롤러는 인터페이스 구현에 집중
@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {
    private final UserService userService;
    
    @Override
    public ResponseEntity<User> createUser(CreateUserRequest request) {
        User user = userService.createUser(
            request.email(), 
            request.password(), 
            request.name(), 
            request.address()
        );
        return ResponseEntity.ok(user);
    }
}
```

### API 인터페이스 분리의 장점
1. **관심사의 분리**: API 문서화와 비즈니스 로직 구현을 분리
2. **가독성 향상**: 컨트롤러 코드가 깔끔해지고 비즈니스 로직에 집중
3. **재사용성**: API 인터페이스를 클라이언트 라이브러리 생성에 활용 가능
4. **유지보수성**: Swagger 문서와 실제 구현이 명확히 분리되어 관리 용이

## API 엔드포인트

### User API
- `GET /api/users` - 전체 사용자 조회
- `GET /api/users/{userId}` - 특정 사용자 조회
- `POST /api/users` - 사용자 생성
- `PUT /api/users/{userId}` - 사용자 정보 수정

### Product API
- `GET /api/products` - 전체 상품 조회
- `GET /api/products/{productId}` - 특정 상품 조회
- `GET /api/products/available` - 재고가 있는 상품 조회
- `POST /api/products` - 상품 생성
- `PUT /api/products/{productId}` - 상품 정보 수정
- `PUT /api/products/{productId}/stock` - 재고 수량 수정
- `DELETE /api/products/{productId}` - 상품 삭제

### Order API
- `GET /api/orders` - 전체 주문 조회
- `GET /api/orders/{orderId}` - 특정 주문 조회
- `GET /api/orders/user/{userId}` - 특정 사용자의 주문 조회
- `POST /api/orders` - 주문 생성
- `PUT /api/orders/{orderId}/status` - 주문 상태 변경
- `POST /api/orders/{orderId}/cancel` - 주문 취소

## API 사용 예시

### 상품 생성
```json
POST /api/products
{
  "name": "Spring in Action",
  "price": 45000,
  "stockQuantity": 50,
  "description": "Spring 프레임워크 실전 가이드"
}
```

### 주문 생성
```json
POST /api/orders
{
  "userId": 1,
  "orderItems": [
    {
      "productId": 1,
      "quantity": 2
    },
    {
      "productId": 3,
      "quantity": 1
    }
  ]
}
```

### 주문 상태 변경
```json
PUT /api/orders/1/status
{
  "status": "PROCESSING"
}
```

## API 테스트

### Swagger UI 사용
1. http://localhost:8081/swagger-ui.html 접속
2. 원하는 API 선택
3. "Try it out" 버튼 클릭
4. 파라미터 입력 후 "Execute" 클릭

### Postman Collection
프로젝트의 `/postman` 폴더에 Postman Collection 파일이 포함되어 있습니다.
1. Postman 실행
2. Import → `postman/Base_Spring_Collection.json` 파일 선택
3. 환경 변수에서 `baseUrl`을 `http://localhost:8081`로 설정
4. API 테스트 실행

### cURL 예시
```bash
# 사용자 생성
curl -X POST http://localhost:8081/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "email": "newuser@example.com",
    "password": "password123",
    "name": "새로운사용자",
    "address": "서울시 서초구"
  }'

# 상품 조회
curl -X GET http://localhost:8081/api/products

# 주문 생성
curl -X POST http://localhost:8081/api/orders \
  -H "Content-Type: application/json" \
  -d '{
    "userId": 1,
    "orderItems": [
      {"productId": 1, "quantity": 2}
    ]
  }'
```

## 개발 규칙

1. **네이밍 컨벤션**
   - 클래스명: PascalCase
   - 메서드/변수명: camelCase
   - 패키지명: 소문자

2. **패키지 구조**
   - 기능별로 패키지 분리 (user, order, product)
   - 각 기능은 독립적으로 관리

3. **코드 스타일**
   - Lombok 활용으로 보일러플레이트 코드 최소화
   - 인터페이스 기반 설계
   - DTO 사용으로 계층 간 데이터 전달

4. **API 설계 원칙**
   - 컨트롤러는 API 인터페이스를 구현
   - Swagger 어노테이션은 인터페이스에 작성
   - 비즈니스 로직은 서비스 레이어에서 처리
   - 정적 팩토리 메서드를 통한 엔티티 생성

## 주의사항

- H2 데이터베이스는 파일 기반으로 저장되며, `./data` 폴더에 데이터가 보관됩니다.
- 데이터를 완전히 초기화하려면 `./data` 폴더를 삭제하고 재시작하면 됩니다.
- 프로덕션 환경에서는 적절한 데이터베이스(MySQL, PostgreSQL 등)로 변경이 필요합니다.
- 보안 관련 기능(인증, 권한)은 Spring Security 통합 후 구현 예정입니다.

## 기여 방법

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 라이선스

이 프로젝트는 MIT 라이선스 하에 있습니다.
