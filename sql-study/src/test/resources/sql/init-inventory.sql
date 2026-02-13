INSERT INTO product (id, name, sku, create_at) VALUES (1, '무선 키보드', 'KB-001', NOW());
INSERT INTO product (id, name, sku, create_at) VALUES (2, '무선 마우스', 'MS-002', NOW());
INSERT INTO product (id, name, sku, create_at) VALUES (3, 'USB 허브', 'UH-003', NOW());

INSERT INTO inventory (id, product_id, quantity, updated_at, version) VALUES (1, 1, 7, NOW(), 0);
INSERT INTO inventory (id, product_id, quantity, updated_at, version) VALUES (2, 2, 3, NOW(), 0);
INSERT INTO inventory (id, product_id, quantity, updated_at, version) VALUES (3, 3, 5, NOW(), 0);
