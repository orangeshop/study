# H2 데이터베이스 접속 가이드

## IntelliJ IDEA에서 H2 접속하기

1. **Database 탭 열기**
   - View → Tool Windows → Database
   - 또는 우측 사이드바에서 Database 클릭

2. **새 데이터소스 추가**
   - `+` 버튼 클릭
   - Data Source → H2 선택

3. **접속 정보 입력**
   - **방법 1: 파일 직접 접속**
     - Connection type: `Embedded`
     - Path: `./data/h2db.mv.db`
     - User: `sa`
     - Password: (비워두기)

   - **방법 2: TCP 서버 접속 (추천)**
     - Connection type: `Remote`
     - Host: `localhost`
     - Port: `9092`
     - Database: `./data/h2db`
     - User: `sa`
     - Password: (비워두기)

4. **Test Connection** 클릭하여 연결 확인

5. **OK** 클릭하여 저장

## DBeaver에서 H2 접속하기

1. **새 연결 생성**
   - Database → New Database Connection
   - H2 Embedded 선택

2. **접속 정보 입력**
   - JDBC URL: `jdbc:h2:tcp://localhost:9092/./data/h2db`
   - Username: `sa`
   - Password: (비워두기)

3. **Test Connection** → **OK**

## 터미널에서 H2 확인하기

```bash
# H2 Shell 실행 (프로젝트 루트에서)
java -cp ~/.gradle/caches/modules-2/files-2.1/com.h2database/h2/*/h2*.jar org.h2.tools.Shell

# 접속 정보 입력
URL: jdbc:h2:./data/h2db
User: sa
Password: (Enter 키)

# SQL 명령어 실행
sql> SHOW TABLES;
sql> SELECT * FROM USER;
sql> SELECT * FROM PRODUCT;
```

## 주의사항

1. **Spring Boot 애플리케이션이 실행 중이어야 함**
   - TCP 서버 접속 시 반드시 애플리케이션이 실행 중이어야 합니다.

2. **파일 잠금 문제**
   - 여러 프로세스가 동시에 같은 H2 파일에 접근하면 잠금 문제가 발생할 수 있습니다.
   - TCP 모드로 접속하면 이 문제를 피할 수 있습니다.

3. **데이터베이스 초기화**
   - `./data` 폴더를 삭제하면 데이터베이스가 초기화됩니다.
   - 애플리케이션 재시작 시 DataInitializer가 초기 데이터를 다시 생성합니다.
