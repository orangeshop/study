# 토큰 버킷(Token Bucket) 알고리즘을 이용한 Rate Limiting 프로젝트

API의 요청 횟수를 제어하는 Rate Limiting 기법 중 하나인 토큰 버킷 알고리즘을 학습하고, Spring Boot 환경에서 `Bucket4j` 라이브러리를 사용하여 구현하는 프로젝트입니다.

## 🎯 목적

-   토큰 버킷 알고리즘의 동작 원리를 이해합니다.
-   `Bucket4j` 라이브러리를 사용하여 특정 API에 대한 요청 횟수를 제한하는 방법을 익힙니다.
-   Spring의 `HandlerInterceptor` 또는 `Filter`를 활용하여 공통적으로 Rate Limiting을 적용하는 방법을 학습합니다.
-   API별로 다른 요청 제한 정책을 적용하는 방법을 알아봅니다.

## 🛠️ 기술 스택

*   **Framework**: Spring Boot 3.5.3
*   **Language**: Java 17
*   **Core Dependency**: `com.bucket4j:bucket4j-core`
*   **Other Dependencies**:
    *   Spring Web, Spring Data JPA
    *   H2 Database
    *   Lombok
    *   Springdoc OpenAPI (Swagger UI)

## 📖 핵심 학습 내용

이 프로젝트는 특정 API가 비정상적으로 많이 호출되는 것을 방지하여 서버를 보호하는 Rate Limiter를 구현합니다.

**토큰 버킷 알고리즘**:
1.  일정량의 토큰을 담을 수 있는 '버킷'이 존재합니다.
2.  일정한 주기로 버킷에 토큰이 채워집니다.
3.  API 요청이 들어오면 버킷에서 토큰을 하나 소모합니다.
4.  만약 버킷에 토큰이 없다면, 해당 요청은 거부되거나 대기합니다.

`Bucket4j`는 이러한 로직을 매우 편리하게 구현할 수 있도록 도와주는 라이브러리입니다. 이 프로젝트에서는 특정 API 엔드포인트에 대해 "1분에 10번만 요청 가능"과 같은 규칙을 적용하는 방법을 중점적으로 다룹니다.

## ✨ 특이사항

-   `build.gradle`에 `cleanH2DB`, `killPort`와 같은 유틸리티 태스크가 포함되어 있어, 개발 편의성을 높이고 있습니다.
-   Swagger UI가 적용되어 있어 API 테스트를 편리하게 할 수 있습니다.