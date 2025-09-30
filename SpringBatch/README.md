# Spring Batch 학습 프로젝트

대용량 데이터의 배치(Batch) 처리를 위한 프레임워크인 Spring Batch의 주요 개념과 사용법을 학습하는 프로젝트입니다.

## 목적

-   Spring Batch의 핵심 구성요소인 `Job`, `Step`, `JobRepository` 등을 이해합니다.
-   `ItemReader`, `ItemProcessor`, `ItemWriter`를 사용하여 데이터를 읽고, 가공하고, 쓰는 과정을 구현합니다.
-   다양한 종류의 `ItemReader`(CSV, JDBC, JPA 등)와 `ItemWriter`를 테스트합니다.
-   Job Parameter를 사용하여 동일한 Job을 다른 파라미터로 여러 번 실행하는 방법을 학습합니다.

## 기술 스택

*   **Framework**: Spring Boot 3.5.4, Spring Batch
*   **Language**: Java 17
*   **Dependencies**:
    *   Lombok
    *   H2 Database

## 핵심 학습 내용

이 프로젝트는 대량의 데이터를 정해진 스케줄에 따라 안정적으로 처리해야 하는 시나리오를 다룹니다.

예를 들어, "매일 자정마다 CSV 파일을 읽어 데이터베이스에 저장한다" 또는 "한 달간의 사용자 데이터를 정산하여 통계 테이블을 만든다"와 같은 요구사항을 Spring Batch를 통해 어떻게 구현하는지 학습합니다.

Chunk 지향 처리 모델을 중심으로 `ItemReader`, `ItemProcessor`, `ItemWriter`의 역할을 명확히 이해하고, 트랜잭션 관리와 재시도(Retry), 건너뛰기(Skip) 같은 고급 기능을 다루는 것을 목표로 합니다.
