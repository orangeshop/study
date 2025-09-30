# JPA 학습 프로젝트

Spring Data JPA의 핵심 개념과 다양한 기능을 깊이 있게 학습하기 위한 프로젝트입니다.

## 목적

-   JPA의 기본 CRUD 기능 및 쿼리 메소드 사용법을 익힙니다.
-   엔티티 생명주기(Entity Lifecycle)와 영속성 컨텍스트(Persistence Context)를 이해합니다.
-   다양한 연관관계 매핑(`@OneToMany`, `@ManyToOne`, `@OneToOne`, `@ManyToMany`)을 직접 구현하고 테스트합니다.
-   Fetch 전략(Eager, Lazy)과 N+1 문제의 발생 및 해결 과정을 학습합니다.

## 기술 스택

*   **Framework**: Spring Boot 3.4.1
*   **Language**: Java 17
*   **Dependencies**:
    *   Spring Data JPA
    *   Spring Web
    *   Lombok
    *   H2 Database
    *   JJWT (JSON Web Token) - (인증/인가 테스트용으로 포함된 것으로 보입니다)

## 핵심 학습 내용

이 프로젝트는 단순히 JPA를 사용하는 것을 넘어, JPA가 내부적으로 어떻게 동작하는지를 이해하는 데 중점을 둡니다. 특히 영속성 컨텍스트의 1차 캐시, 쓰기 지연(dirty checking), 지연 로딩 등의 개념을 코드로 직접 확인하며 학습합니다.
