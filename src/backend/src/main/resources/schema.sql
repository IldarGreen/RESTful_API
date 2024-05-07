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
    price NUMERIC,
    available_stock INT,
    last_update_date DATE,
    supplier_id UUID,
    image_id: UUID,
    FOREIGN KEY (supplier_id) REFERENCES supplier(id),
    FOREIGN KEY (image_id) REFERENCES image(id)
);

CREATE TABLE IF NOT EXISTS images (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    image BYTEA
);
