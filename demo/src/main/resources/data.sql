INSERT INTO common_code_group (group_code, group_name)
VALUES ('ORDER_STATUS', '주문상태'),
       ('MEMBER_GRADE', '회원등급'),
       ('PAYMENT_STATUS', '결제상태'),
       ('PAYMENT_METHOD', '결제수단');

INSERT INTO common_code (group_code, code_name, code_value, description, sort_order)
VALUES
   ('PAYMENT_STATUS', 'PAID',     'PS001', '결제완료', 1),
   ('PAYMENT_STATUS', 'PENDING',  'PS002', '결제대기', 2),
   ('PAYMENT_STATUS', 'CANCELED', 'PS003', '결제취소', 3);

-- 1단계: 최상위 카테고리 (루트)
INSERT INTO category (name, parent_id) VALUES ('전자제품', NULL); -- ID: 1
INSERT INTO category (name, parent_id) VALUES ('의류', NULL);     -- ID: 2
INSERT INTO category (name, parent_id) VALUES ('식품', NULL);     -- ID: 3

-- 2단계: 전자제품(1)의 하위 카테고리
INSERT INTO category (name, parent_id) VALUES ('컴퓨터', 1);     -- ID: 4
INSERT INTO category (name, parent_id) VALUES ('스마트폰', 1);   -- ID: 5
INSERT INTO category (name, parent_id) VALUES ('가전제품', 1);   -- ID: 6

-- 3단계: 컴퓨터(4)의 하위 카테고리
INSERT INTO category (name, parent_id) VALUES ('노트북', 4);
INSERT INTO category (name, parent_id) VALUES ('데스크탑', 4);
INSERT INTO category (name, parent_id) VALUES ('태블릿', 4);

-- 3단계: 스마트폰(5)의 하위 카테고리
INSERT INTO category (name, parent_id) VALUES ('애플', 5);
INSERT INTO category (name, parent_id) VALUES ('삼성', 5);

-- 3단계: 가전제품(6)의 하위 카테고리
INSERT INTO category (name, parent_id) VALUES ('TV', 6);
INSERT INTO category (name, parent_id) VALUES ('냉장고', 6);

-- 2단계: 의류(2)의 하위 카테고리
INSERT INTO category (name, parent_id) VALUES ('남성의류', 2);   -- ID: 14
INSERT INTO category (name, parent_id) VALUES ('여성의류', 2);   -- ID: 15

-- 3단계: 남성의류(14)의 하위 카테고리
INSERT INTO category (name, parent_id) VALUES ('셔츠', 14);
INSERT INTO category (name, parent_id) VALUES ('바지', 14);
