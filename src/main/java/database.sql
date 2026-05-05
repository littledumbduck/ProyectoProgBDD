-- Table User
CREATE TABLE user
(
    id                  INT             NOT NULL AUTO_INCREMENT,
    username            VARCHAR(25)     NOT NULL,
    password            VARCHAR(25)     NOT NULL,
    CONSTRAINT pk_id PRIMARY KEY (id)
);

-- Table Customer
CREATE TABLE customer
(
    dni             VARCHAR(9)      NOT NULL,
    name            VARCHAR(25)     NOT NULL,
    surname         VARCHAR(25)     NOT NULL,
    email           VARCHAR(50)     NOT NULL,
    phonenumber     VARCHAR(15)     NOT NULL,
    CONSTRAINT pk_customer PRIMARY KEY (dni)
);

-- Table Room
CREATE TABLE room
(
    roomNumber  INT                 AUTO_INCREMENT,
    roomfloor   INT                 NOT NULL,
    roomType    CHAR(1)             NOT NULL,
    price       DECIMAL(8,2)        NOT NULL,
    status      CHAR(1)             DEFAULT 'D' NOT NULL,
    CONSTRAINT pk_room PRIMARY KEY (roomNumber)
);

-- Table Book
CREATE TABLE book
(
    idBook              INT             NOT NULL AUTO_INCREMENT,
    customer_dni        VARCHAR(9)      NOT NULL,
    room_id             INT             NOT NULL,
    dateEntry           DATE            NOT NULL,
    dateLeave           DATE,
    purchaseStatus      BOOLEAN,
    CONSTRAINT pk_idbook PRIMARY KEY (idBook),
    CONSTRAINT fk_customer_book FOREIGN KEY (customer_dni)
        REFERENCES customer (dni) ON DELETE CASCADE,
    CONSTRAINT fk_room_book FOREIGN KEY (room_id)
        REFERENCES room (roomNumber) ON DELETE CASCADE
);

-- Planta 1 (7 habitaciones)
INSERT INTO room (roomfloor, roomType, price, status) VALUES (1, 'S', 45.00, 'D');
INSERT INTO room (roomfloor, roomType, price, status) VALUES (1, 'S', 45.00, 'D');
INSERT INTO room (roomfloor, roomType, price, status) VALUES (1, 'D', 75.00, 'D');
INSERT INTO room (roomfloor, roomType, price, status) VALUES (1, 'D', 75.00, 'D');
INSERT INTO room (roomfloor, roomType, price, status) VALUES (1, 'D', 75.00, 'D');
INSERT INTO room (roomfloor, roomType, price, status) VALUES (1, 'S', 45.00, 'D');
INSERT INTO room (roomfloor, roomType, price, status) VALUES (1, 'D', 75.00, 'D');

-- Planta 2 (7 habitaciones)
INSERT INTO room (roomfloor, roomType, price, status) VALUES (2, 'S', 50.00, 'D');
INSERT INTO room (roomfloor, roomType, price, status) VALUES (2, 'S', 50.00, 'D');
INSERT INTO room (roomfloor, roomType, price, status) VALUES (2, 'D', 80.00, 'D');
INSERT INTO room (roomfloor, roomType, price, status) VALUES (2, 'D', 80.00, 'D');
INSERT INTO room (roomfloor, roomType, price, status) VALUES (2, 'D', 85.00, 'D');
INSERT INTO room (roomfloor, roomType, price, status) VALUES (2, 'D', 85.00, 'D');
INSERT INTO room (roomfloor, roomType, price, status) VALUES (2, 'S', 50.00, 'D');

-- Planta 3 (6 habitaciones)
INSERT INTO room (roomfloor, roomType, price, status) VALUES (3, 'D', 90.00, 'D');
INSERT INTO room (roomfloor, roomType, price, status) VALUES (3, 'D', 90.00, 'D');
INSERT INTO room (roomfloor, roomType, price, status) VALUES (3, 'S', 120.00, 'D'); -- Suite
INSERT INTO room (roomfloor, roomType, price, status) VALUES (3, 'S', 120.00, 'D'); -- Suite
INSERT INTO room (roomfloor, roomType, price, status) VALUES (3, 'D', 95.00, 'D');
INSERT INTO room (roomfloor, roomType, price, status) VALUES (3, 'S', 150.00, 'D'); -- Suite Presidencial