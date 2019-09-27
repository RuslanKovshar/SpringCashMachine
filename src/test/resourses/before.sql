USE cash_machine_test;

DELETE
FROM stock;

DELETE
FROM product_in_check;

DELETE
FROM checks;

DELETE
FROM buyer_info;

DELETE
FROM user_roles;

DELETE
FROM users;

DELETE
FROM products;

INSERT INTO users (id, email, first_name_en, first_name_ua, is_account_non_expired, is_account_non_locked,
                   is_credentials_non_expired, is_enabled, password, second_name_en, second_name_ua, cash)
VALUES (1, 'example@mail.com', 'Jon', 'Джон', 1, 1, 1, 1,
        '$2a$08$Gz19FTxQLPuX2Sv8kVgyjuKPIX0XeyY.DoIJ53wsfP4Nl5jiT6YpO', 'Snow', 'Сноу', 0.00),
       (2, 'ruslan.kovshar@gmail.com', 'Ruslan', 'Руслан', 1, 1, 1, 1,
        '$2a$10$rrshi5NSGtWp.5njXyECS.I6ltl.rpHX.67kojHSNSjwWX62bJygW', 'Kovshar', 'Ковшар', 0.00);

INSERT INTO user_roles (user_id, authorities)
VALUES (1, 'CASHIER'),
       (1,'MERCHANDISER'),
       (1,'SENIOR_CASHIER'),
       (2, 'CASHIER');

INSERT INTO products (dtype, id, code, name, price, type)
VALUES ('CountProduct', 1, 222, 'Bread', 6.99, 'PIECE_PRODUCT'),
       ('CountProduct', 2, 333, 'Coca-cola', 21.99, 'PIECE_PRODUCT');

INSERT INTO stock (row_id, count_of_product, product_id)
VALUES (1, 50, 1),
       (2, 100, 2);

INSERT INTO buyer_info (id, card_number, name_on_card)
VALUES (1, '5168002109418990', 'Kal El'),
       (2, '5168007556780932', 'Ernest Bukhanevych');

INSERT INTO checks (id, total_price, user_id, buyer_id)
VALUES (1, 6.99, 1, 1),
       (2, 43.98, 2, 2);

INSERT INTO product_in_check (id, price, check_id, product_id, count_of_product)
VALUES (1, 6.99, 1, 1, 1),
       (2, 43.98, 2, 2, 2);