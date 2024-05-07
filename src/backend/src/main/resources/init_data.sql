INSERT INTO shop_b2.address(country, city, street) VALUES
    ('Франция', 'Леон', 'Вьен'),
    ('Россия', 'Сакт-Петербург', 'Лунная');

INSERT INTO shop_b2.client(client_name, client_surname, birthday, gender, registration_date, address_id) VALUES
    ('Кэлен', 'Амнелл', '2000-04-05', 'F', '2014-05-06',
        (SELECT id FROM shop_b2.address WHERE country = 'Франция' AND city = 'Леон' AND street = 'Вьен')),
    ('Йон', 'Тихий', '1940-01-02', 'M', '2024-05-07',
        (SELECT id FROM shop_b2.address WHERE country = 'Россия' AND city = 'Сакт-Петербург' AND street = 'Лунная'));

INSERT INTO shop_b2.supplier(name, address_id, phone_number) VALUES
    ('Поставщик из Парижу', (SELECT id FROM shop_b2.address WHERE country = 'Франция' AND city = 'Леон' AND street = 'Вьен'), '+79139139913'),
    ('Поставщик из Петербурга', (SELECT id FROM shop_b2.address WHERE country = 'Россия' AND city = 'Сакт-Петербург' AND street = 'Лунная'), '+79139139913');

INSERT INTO shop_b2.images(image) VALUES
    (decode(md5(random()::text), 'hex')),
    (decode(md5(random()::text), 'hex'));

INSERT INTO shop_b2.product(name, category, price, available_stock, last_update_date, supplier_id, image_id) VALUES
    ('Духи Французские', 'Духи', 100.0, 10, '2024-05-07',
        (SELECT id FROM shop_b2.supplier WHERE name = 'Поставщик из Парижу'),
        (SELECT id FROM shop_b2.images ORDER BY id LIMIT 1)),
    ('Краски Ладога', 'Краски', 50.0, 100, '2024-05-07',
        (SELECT id FROM shop_b2.supplier WHERE name = 'Поставщик из Петербурга'),
        (SELECT id FROM shop_b2.images ORDER BY id DESC LIMIT 1));
