CREATE SCHEMA `internetshop` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `internetshop`.`items` (
    `item_id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(225) NOT NULL,
    `price` DECIMAL(6,2) NOT NULL,
    `description` VARCHAR(1000) NULL,
    PRIMARY KEY (`item_id`));

INSERT INTO `internetshop`.`items` (`name`, `price`) VALUES ('horse', '2000');
INSERT INTO `internetshop`.`items` (`name`, `price`) VALUES ('pen', '10.00');
INSERT INTO `internetshop`.`items` (`name`, `price`) VALUES ('phone', '200.00');
INSERT INTO `internetshop`.`items` (`name`, `price`) VALUES ('cheese', '60.00');
INSERT INTO `internetshop`.`items` (`name`, `price`) VALUES ('mac', '1500.00')

