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
INSERT INTO `internetshop`.`items` (`name`, `price`) VALUES ('mac', '1500.00');

CREATE TABLE `internetshop`.`orders` (
  `order_id` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`order_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE `internetshop`.`orders_items` (
  `orders_items_id` INT NOT NULL AUTO_INCREMENT,
  `order_id` INT NOT NULL,
  `item_id` INT NOT NULL,
  PRIMARY KEY (`orders_items_id`),
  INDEX `orders_items_orders_fk_idx` (`order_id` ASC) VISIBLE,
  INDEX `orders_items_items_fk_idx` (`item_id` ASC) VISIBLE,
  CONSTRAINT `orders_items_orders_fk`
    FOREIGN KEY (`order_id`)
    REFERENCES `internetshop`.`orders` (`order_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `orders_items_items_fk`
    FOREIGN KEY (`item_id`)
    REFERENCES `internetshop`.`items` (`item_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

    ALTER TABLE `internetshop`.`items`
CHANGE COLUMN `price` `price` DECIMAL(8,2) NOT NULL ;

    ALTER TABLE `internetshop`.`users`
DROP COLUMN `role_id`,
ADD COLUMN `token` VARCHAR(64) NULL AFTER `password`;

ALTER TABLE `internetshop`.`orders`
ADD COLUMN `user_id` INT NOT NULL AFTER `order_id`,
ADD INDEX `orders_users_fk_idx` (`user_id` ASC) VISIBLE;
;
ALTER TABLE `internetshop`.`orders`
ADD CONSTRAINT `orders_users_fk`
  FOREIGN KEY (`user_id`)
  REFERENCES `internetshop`.`users` (`user_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

  ALTER TABLE `internetshop`.`users`
ADD COLUMN `bucket_id` INT NULL AFTER `token`;

CREATE TABLE `internetshop`.`users_roles` (
  `users_roles_id` INT NOT NULL AUTO_INCREMENT,
  `role_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`users_roles_id`),
  INDEX `users_roles_users_fk_idx` (`user_id` ASC) VISIBLE,
  INDEX `users_roles_roles_fk_idx` (`role_id` ASC) VISIBLE,
  CONSTRAINT `users_roles_users_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `internetshop`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `users_roles_roles_fk`
    FOREIGN KEY (`role_id`)
    REFERENCES `internetshop`.`roles` (`role_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE `internetshop`.`buckets` (
  `bucket_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NULL,
  PRIMARY KEY (`bucket_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE `internetshop`.`buckets_items` (
  `buckets_items_id` INT NOT NULL AUTO_INCREMENT,
  `bucket_id` INT NOT NULL,
  `item_id` INT NOT NULL,
  PRIMARY KEY (`buckets_items_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE `internetshop`.`users_orders` (
  `users_orders_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NULL,
  `order_id` INT NULL,
  PRIMARY KEY (`users_orders_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


