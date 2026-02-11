DROP TABLE IF EXISTS common_code;
DROP TABLE IF EXISTS common_code_group;

CREATE TABLE common_code_group
(
    group_code VARCHAR(50) PRIMARY KEY,
    group_name VARCHAR(100) NOT NULL
);

CREATE TABLE common_code
(
    code_id     BIGINT AUTO_INCREMENT PRIMARY KEY,
    group_code  VARCHAR(50)  NOT NULL,
    code_name   VARCHAR(50)  NOT NULL, -- enum 상수명과 매핑 (PAID, PENDING 등)
    code_value  VARCHAR(100) NOT NULL, -- 실제 코드값 (PS001 등)
    description VARCHAR(200),          -- 코드 설명 (결제완료 등)
    sort_order  INT DEFAULT 0,
    FOREIGN KEY (group_code) REFERENCES common_code_group (group_code)
);

