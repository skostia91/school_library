CREATE TABLE `my_db`.`users` (
                                 `id` INT NOT NULL AUTO_INCREMENT,
                                 `name` VARCHAR(45) NOT NULL,
                                 `year` INT NOT NULL,
                                 `password` VARCHAR(205) NOT NULL,
                                 `role` VARCHAR(45) NOT NULL,
                                 PRIMARY KEY (`id`));
