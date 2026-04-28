CREATE TABLE IF NOT EXISTS `customer`
(
    `dni`         VARCHAR(9)  NOT NULL,
    `name`        VARCHAR(25) NOT NULL,
    `surname`     VARCHAR(25) NOT NULL,
    `email`       VARCHAR(50) NOT NULL,
    `phonenumber` VARCHAR(9) NOT NULL,
    PRIMARY KEY (`dni`)
);

CREATE TABLE IF NOT EXISTS `room`
(
    `roomNumber` INT(5) NOT NULL AUTO_INCREMENT,
    `roomType`   CHAR(1)     NOT NULL,
    `price`      INT(6) NOT NULL,
    `status`     VARCHAR(50) NOT NULL DEFAULT 'd',
    PRIMARY KEY (`roomNumber`)
);