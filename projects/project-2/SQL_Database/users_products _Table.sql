ALTER TABLE `ez_database`.`users_products` 
ADD COLUMN `user_id` INT NOT NULL AFTER `product_id`,
ADD PRIMARY KEY (`user_id`, `product_id`),
ADD INDEX `product_id_idx` (`product_id` ASC) VISIBLE;
;
ALTER TABLE `ez_database`.`users_products` 
ADD CONSTRAINT `user_id`
  FOREIGN KEY (`user_id`)
  REFERENCES `ez_database`.`users` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `product_id`
  FOREIGN KEY (`product_id`)
  REFERENCES `ez_database`.`products` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;