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
