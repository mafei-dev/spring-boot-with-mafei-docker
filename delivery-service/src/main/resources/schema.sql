CREATE TABLE IF NOT EXISTS `sbm-delivery-service-db`.delivery_detail
(
    delivery_detail_id INT AUTO_INCREMENT PRIMARY KEY,
    username           VARCHAR(255) NOT NULL,
    address            TEXT         NOT NULL,
    tel                VARCHAR(20)  NOT NULL,
    email              VARCHAR(100) NULL,
    order_id           VARCHAR(36)  NOT NULL,
    transaction_id     VARCHAR(36)  NOT NULL
);