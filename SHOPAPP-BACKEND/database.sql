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