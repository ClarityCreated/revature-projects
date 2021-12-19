ALTER TABLE `ez_database`.`users_reviews` 
ADD COLUMN `theUser_id` INT NOT NULL AFTER `review_id`,
ADD PRIMARY KEY (`review_id`, `theUser_id`),
ADD INDEX `theUser_id_idx` (`theUser_id` ASC) VISIBLE;
;
ALTER TABLE `ez_database`.`users_reviews` 
ADD CONSTRAINT `theUser_id`
  FOREIGN KEY (`theUser_id`)
  REFERENCES `ez_database`.`users` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `review_id`
  FOREIGN KEY (`review_id`)
  REFERENCES `ez_database`.`reviews` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;
