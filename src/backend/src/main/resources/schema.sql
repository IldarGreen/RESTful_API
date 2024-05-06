DROP SCHEMA IF EXISTS shop_b2 CASCADE;

CREATE SCHEMA IF NOT EXISTS shop_b2;

CREATE TABLE IF NOT EXISTS address (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    country VARCHAR(100),
    city VARCHAR(100),
    street VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS client (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    client_name VARCHAR(100),
    client_surname VARCHAR(100),
    birthday DATE,
    gender VARCHAR(100),
    registration_date DATE,
    address_id UUID,
    FOREIGN KEY (address_id) REFERENCES address(id)
);

CREATE TABLE IF NOT EXISTS supplier (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(100),
    address_id UUID,
    phone_number VARCHAR(100),
    FOREIGN KEY (address_id) REFERENCES address(id)
);

CREATE TABLE IF NOT EXISTS product (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(100),
    category VARCHAR(100),
    price
    available_stock // число закупленных экземпляров товара
    last_update_date // число последней закупки
    supplier_id
    image_id: UUID
);


--CREATE TABLE IF NOT EXISTS chat.users (
--    id SERIAL PRIMARY KEY,
--    login VARCHAR(20) NOT NULL,
--    password VARCHAR(30) NOT NULL
--);
--
--CREATE TABLE IF NOT EXISTS chat.chatrooms (
--    id SERIAL PRIMARY KEY,
--    name VARCHAR(20) NOT NULL,
--    owner_id INTEGER REFERENCES chat.users(id) NOT NULL
--);
--
--CREATE TABLE IF NOT EXISTS chat.messages (
--    id SERIAL PRIMARY KEY,
--    author_id INTEGER REFERENCES chat.users(id) NOT NULL,
--    room_id INTEGER REFERENCES chat.chatrooms(id) NOT NULL,
--    text VARCHAR(1024) NOT NULL,
--    datetime TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW()
--);