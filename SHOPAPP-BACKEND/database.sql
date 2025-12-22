CREATE DATABASE ShopApp;
-- MariaDB của XAMPP không phân biệt chữ hoa chữ thường
-- ví dụ: shopapp và ShopApp là giống nhau
USE ShopApp;
--Khách hàng khi muốn mua hàng => phải đăng ký tài khoản => bảng users
CREATE TABLE users(
    id INT PRiMARY KEY AUTO_INCREMENT,
    fullname VARCHAR(100) DEFAULT "",
    phone_number VARCHAR(10) NOT NULL,
    address VARCHAR(200) DEFAULT "",
    password VARCHAR(100) NOT NULL DEFAULT "",
    created_at DATETIME,
    updated_at DATETIME,
    is_active TINYINT(1) DEFAULT 1,  
    date_of_birth DATE,
    facebook_account_id INT DEFAULT 0,
    google_account_id INT DEFAULT 0
);
-- Lưu ý:
-- "is_active" để tinint vì để boolean khi đổi cơ sở dữ liệu khác đôi khi sẽ không hỗ trợ boolean

ALTER TABLE users ADD COLUMN role_id INT;

CREATE TABLE roles(
    id INT PRIMARY KEY,
    name VARCHAR(20) NOT NULL
);

ALTER TABLE users ADD FOREIGN KEY (role_id) REFERENCES roles(id);

CREATE TABLE tokens(
    id INT PRiMARY KEY AUTO_INCREMENT,
    token VARCHAR(255) UNIQUE NOT NULL,
    token_type VARCHAR(50) NOT NULL,
    expiration_date DATETIME,
    revoked TINYINT(1) NOT NULL,
    expired TINYINT(1) NOT NULL,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- hỗ trợ đăng nhập từ Facebook và Google
CREATE TABLE social_accounts(
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `provider` VARCHAR(50) NOT NULL COMMENT 'e.g., facebook, google',
    `provider__id` VARCHAR(50) NOT NULL,
    `email` VARCHAR(150) NOT NULL COMMENT 'Email tài khoản xã hội',
    `name` VARCHAR(100) NOT NULL COMMENT 'Tên người dùng',
    `user_id` INT,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

--Bảng danh mục sản phẩm(Categories)
CREATE TABLE categories(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL DEFAULT "" COMMENT 'Tên danh mục, vd: Điện thoại, Laptop'
);

--Bảng chứa san phẩm(Products): "laptop macbook pro 2023", "iphone 14 pro max",...

CREATE TABLE products(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(350) NOT NULL DEFAULT "" COMMENT 'Tên sản phẩm',
    price FLOAT NOT NULL CHECK (price >= 0),
    thumbnail VARCHAR(300) DEFAULT "",
    description LONGTEXT DEFAULT "",
    created_at DATETIME,
    updated_at DATETIME,
    category_id INT,
    FOREIGN KEY (category_id) REFERENCES categories(id)
);

-- Đặt hàng - orders
CREATE TABLE orders(
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES users(id),
    fullname VARCHAR(100) DEFAULT "",
    email VARCHAR(100) DEFAULT "",
    phone_number VARCHAR(20) NOT NULL,
    address VARCHAR(200),
    note VARCHAR(100) DEFAULT "",
    order_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    total_money FLOAT CHECK (total_money >= 0)
);

ALTER TABLE orders ADD COLUMN `shipping_method` VARCHAR(100);
ALTER TABLE orders ADD COLUMN `shipping_address` VARCHAR(100);
ALTER TABLE orders ADD COLUMN `shipping_date` DATE;
ALTER TABLE orders ADD COLUMN `tracking_number` VARCHAR(100);
ALTER TABLE orders ADD COLUMN `payment_method` VARCHAR(100);

-- xóa 1 đơn hàng => xóa mềm => thêm trường active
ALTER TABLE orders ADD COLUMN is_active TINYINT(1);
-- Trạng thái đơn hàng chỉ được phép nhận "một số giá trị cụ thể"
ALTER TABLE orders ADD COLUMN status ENUM('pending', 'processing', 'shipped', 'delivered', 'canceled')
COMMENT "Trạng thái đơn hàng"

ALTER TABLE orders 
MODIFY COLUMN status ENUM('pending', 'processing', 'shipped', 'delivered', 'canceled') 
COMMENT 'Trạng thái đơn hàng';

CREATE TABLE order_details(
    id INT PRIMARY KEY AUTO_INCREMENT,
    order_id INT,
    FOREIGN KEY (order_id) REFERENCES orders(id),
    product_id INT,
    FOREIGN KEY (product_id) REFERENCES products(id),
    price FLOAT CHECK (price >= 0),
    number_of_products INT CHECK (number_of_products > 0),
    total_money FLOAT CHECK (total_money >= 0),
    color VARCHAR(20) DEFAULT ""
);

