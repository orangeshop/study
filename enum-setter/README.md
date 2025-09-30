# Enum-Setter 프로젝트

Java의 Enum(열거형) 타입을 JPA 엔티티 등에서 사용할 때, Setter를 사용하지 않고 안전하게 값을 관리하는 방법을 탐구하는 프로젝트입니다.

## 목적

-   Enum의 불변성을 유지하면서 사용하는 방법을 익힙니다.
-   Setter 대신 생성자나 별도의 메소드를 통해 상태를 관리하여 객체의 일관성을 보장하는 패턴을 학습합니다.
-   JPA의 `@Enumerated` 어노테이션 사용법을 이해합니다.

## 기술 스택

*   **Framework**: Spring Boot 3.4.2
*   **Language**: Java 17
*   **Dependencies**:
    *   Spring Data JPA
    *   Spring Web
    *   Lombok
    *   H2 Database

## 핵심 내용

일반적으로 엔티티의 필드를 변경할 때 `@Setter`를 사용하면 편리하지만, Enum 타입에 Setter를 무분별하게 열어두면 상태가 의도치 않게 변경될 수 있습니다.

이 프로젝트에서는 Setter를 막고, 특정 비즈니스 로직을 담은 메소드(예: `changeStatusTo(OrderStatus newStatus)`)를 통해 상태를 변경하는 방식을 테스트합니다. 이를 통해 코드의 안정성과 예측 가능성을 높일 수 있습니다.
