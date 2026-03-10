CREATE TABLE cart
(
    id     BIGINT AUTO_INCREMENT NOT NULL,
    subtotal DOUBLE NULL,
    userid BINARY(16)            NULL,
    CONSTRAINT pk_cart PRIMARY KEY (id)
);

CREATE TABLE cartitem
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    quantity  INT NULL,
    price DOUBLE NULL,
    cartid    BIGINT NULL,
    productid BINARY(16)            NULL,
    CONSTRAINT pk_cartitem PRIMARY KEY (id)
);

CREATE TABLE categories
(
    id                BIGINT AUTO_INCREMENT NOT NULL,
    name              VARCHAR(255) NULL,
    quantity_in_stock INT NULL,
    CONSTRAINT pk_categories PRIMARY KEY (id)
);

CREATE TABLE favoritesproduct
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    userid    BINARY(16)            NULL,
    productid BINARY(16)            NULL,
    CONSTRAINT pk_favoritesproduct PRIMARY KEY (id)
);

CREATE TABLE `order`
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    subtotal DOUBLE NULL,
    shipping DOUBLE NULL,
    coupon DOUBLE NULL,
    total DOUBLE NULL,
    payment_method VARCHAR(255) NULL,
    date_created   datetime NULL,
    is_done        BIT(1) NULL,
    userid         BINARY(16)            NULL,
    CONSTRAINT pk_order PRIMARY KEY (id)
);

CREATE TABLE orderitem
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    quantity  INT NULL,
    price DOUBLE NULL,
    productid BINARY(16)            NULL,
    orderid   BIGINT NULL,
    CONSTRAINT pk_orderitem PRIMARY KEY (id)
);

CREATE TABLE products
(
    id                BINARY(16)   NOT NULL,
    name              VARCHAR(255) NULL,
    detail            VARCHAR(255) NULL,
    img               VARCHAR(255) NULL,
    price DOUBLE NULL,
    rating DOUBLE NULL,
    date_add          datetime NULL,
    date_modify       datetime NULL,
    quantity_in_stock INT NULL,
    is_delete         BIT(1) NULL,
    categoryid        BIGINT NULL,
    CONSTRAINT pk_products PRIMARY KEY (id)
);

CREATE TABLE `role`
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NULL,
    CONSTRAINT pk_role PRIMARY KEY (id)
);

CREATE TABLE user
(
    id           BINARY(16)   NOT NULL,
    name         VARCHAR(255) NULL,
    password     VARCHAR(255) NULL,
    phone_number BIGINT NULL,
    email        VARCHAR(255) NULL,
    first_name   VARCHAR(255) NULL,
    last_name    VARCHAR(255) NULL,
    address      VARCHAR(255) NULL,
    roleid       BIGINT NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

ALTER TABLE cart
    ADD CONSTRAINT uc_cart_userid UNIQUE (userid);

ALTER TABLE cartitem
    ADD CONSTRAINT FK_CARTITEM_ON_CARTID FOREIGN KEY (cartid) REFERENCES cart (id);

ALTER TABLE cartitem
    ADD CONSTRAINT FK_CARTITEM_ON_PRODUCTID FOREIGN KEY (productid) REFERENCES products (id);

ALTER TABLE cart
    ADD CONSTRAINT FK_CART_ON_USERID FOREIGN KEY (userid) REFERENCES user (id);

ALTER TABLE favoritesproduct
    ADD CONSTRAINT FK_FAVORITESPRODUCT_ON_PRODUCTID FOREIGN KEY (productid) REFERENCES products (id);

ALTER TABLE favoritesproduct
    ADD CONSTRAINT FK_FAVORITESPRODUCT_ON_USERID FOREIGN KEY (userid) REFERENCES user (id);

ALTER TABLE orderitem
    ADD CONSTRAINT FK_ORDERITEM_ON_ORDERID FOREIGN KEY (orderid) REFERENCES `order` (id);

ALTER TABLE orderitem
    ADD CONSTRAINT FK_ORDERITEM_ON_PRODUCTID FOREIGN KEY (productid) REFERENCES products (id);

ALTER TABLE `order`
    ADD CONSTRAINT FK_ORDER_ON_USERID FOREIGN KEY (userid) REFERENCES user (id);

ALTER TABLE products
    ADD CONSTRAINT FK_PRODUCTS_ON_CATEGORYID FOREIGN KEY (categoryid) REFERENCES categories (id);

ALTER TABLE user
    ADD CONSTRAINT FK_USER_ON_ROLEID FOREIGN KEY (roleid) REFERENCES `role` (id);