CREATE TABLE customer
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    full_name  VARCHAR(255) NOT NULL,
    email      VARCHAR(255) NOT NULL UNIQUE,
    created_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE product
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(255)   NOT NULL UNIQUE,
    price      DECIMAL(10, 2) NOT NULL,
    created_at TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE orders
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT       NOT NULL,
    status      ENUM('NEW', 'PAID', 'CANCELED') NOT NULL,
    created_at  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    INDEX       idx_orders_customer (customer_id),
    FOREIGN KEY (customer_id) REFERENCES customer (id) ON DELETE CASCADE
);

CREATE TABLE payment
(
    order_id INT PRIMARY KEY,
    amount   DECIMAL(10, 2) NOT NULL,
    method   ENUM('SWISH', 'CARD') NOT NULL,
    paid_at  TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE CASCADE
);

CREATE TABLE order_item
(
    order_id   INT            NOT NULL,
    product_id INT            NOT NULL,
    quantity   INT            NOT NULL CHECK (quantity > 0),
    unit_price DECIMAL(10, 2) NOT NULL,

    PRIMARY KEY (order_id, product_id),
    INDEX      idx_order_item_product (product_id),

    FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES product (id)
);