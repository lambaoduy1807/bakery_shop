
-- Thêm dữ liệu mẫu cho bảng categories
INSERT INTO categories (id, name, quantity_in_stock) VALUES
    (1, 'Bread',        100),
    (2, 'Cake',          50),
    (3, 'Pastry',        80);

-- Thêm dữ liệu mẫu cho bảng products
INSERT INTO products (id, name, detail, img, price, rating, date_add, date_modify, quantity_in_stock, is_delete, categoryid) VALUES
    (UNHEX(REPLACE('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa','-','')), 'Baguette',        'Fresh French baguette',      '/images/baguette.jpg',  1.50, 4.5, NOW(), NOW(), 100, 0, 1),
    (UNHEX(REPLACE('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb','-','')), 'Sourdough',       'Classic sourdough bread',    '/images/sourdough.jpg', 2.00, 4.7, NOW(), NOW(),  80, 0, 1),
    (UNHEX(REPLACE('cccccccc-cccc-cccc-cccc-cccccccccccc','-','')), 'Chocolate Cake',  'Rich chocolate layer cake',  '/images/choc_cake.jpg', 15.0, 4.8, NOW(), NOW(),  30, 0, 2),
    (UNHEX(REPLACE('dddddddd-dddd-dddd-dddd-dddddddddddd','-','')), 'Cheesecake',      'New York style cheesecake',  '/images/cheesecake.jpg',18.0, 4.6, NOW(), NOW(),  20, 0, 2),
    (UNHEX(REPLACE('eeeeeeee-eeee-eeee-eeee-eeeeeeeeeeee','-','')), 'Croissant',       'Butter croissant',           '/images/croissant.jpg',  2.50,4.4, NOW(), NOW(),  60, 0, 3);

-- Thêm dữ liệu mẫu cho bảng cart
INSERT INTO cart (id, subtotal, userid) VALUES
    (1, 0.00, UNHEX(REPLACE('22222222-2222-2222-2222-222222222222','-',''))),
    (2, 0.00, UNHEX(REPLACE('33333333-3333-3333-3333-333333333333','-','')));

-- Thêm dữ liệu mẫu cho bảng cartitem
INSERT INTO cartitem (id, quantity, price, cartid, productid) VALUES
    (1, 2,  3.00, 1, UNHEX(REPLACE('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa','-',''))),
    (2, 1, 15.00, 1, UNHEX(REPLACE('cccccccc-cccc-cccc-cccc-cccccccccccc','-',''))),
    (3, 3,  7.50, 2, UNHEX(REPLACE('eeeeeeee-eeee-eeee-eeee-eeeeeeeeeeee','-','')));

-- Thêm dữ liệu mẫu cho bảng order
INSERT INTO `order` (id, subtotal, shipping, coupon, total, payment_method, date_created, is_done, userid) VALUES
    (1, 18.00, 2.00, 0.00, 20.00, 'CASH',    NOW(), 1, UNHEX(REPLACE('22222222-2222-2222-2222-222222222222','-',''))),
    (2, 25.50, 2.50, 0.00, 28.00, 'CARD',    NOW(), 0, UNHEX(REPLACE('33333333-3333-3333-3333-333333333333','-','')));

-- Thêm dữ liệu mẫu cho bảng orderitem
INSERT INTO orderitem (id, quantity, price, productid, orderid) VALUES
    (1, 2,  3.00,  UNHEX(REPLACE('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa','-','')), 1),
    (2, 1, 15.00,  UNHEX(REPLACE('cccccccc-cccc-cccc-cccc-cccccccccccc','-','')), 1),
    (3, 3,  2.50,  UNHEX(REPLACE('eeeeeeee-eeee-eeee-eeee-eeeeeeeeeeee','-','')), 2);

-- Thêm dữ liệu mẫu cho bảng favoritesproduct
INSERT INTO favoritesproduct (id, userid, productid) VALUES
    (1, UNHEX(REPLACE('22222222-2222-2222-2222-222222222222','-','')), UNHEX(REPLACE('cccccccc-cccc-cccc-cccc-cccccccccccc','-',''))),
    (2, UNHEX(REPLACE('22222222-2222-2222-2222-222222222222','-','')), UNHEX(REPLACE('dddddddd-dddd-dddd-dddd-dddddddddddd','-',''))),
    (3, UNHEX(REPLACE('33333333-3333-3333-3333-333333333333','-','')), UNHEX(REPLACE('eeeeeeee-eeee-eeee-eeee-eeeeeeeeeeee','-','')));

