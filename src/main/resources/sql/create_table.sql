CREATE TABLE `my_db`.`students` (
                                    `id` INT NOT NULL AUTO_INCREMENT,
                                    `name` VARCHAR(45) NOT NULL,
                                    `year` INT NOT NULL,
                                    PRIMARY KEY (`id`));

CREATE TABLE `my_db`.`books` (
                                 `id` INT NOT NULL AUTO_INCREMENT,
                                 `title` VARCHAR(100) NOT NULL,
                                 `author` VARCHAR(100) NOT NULL,
                                 `year` INT NOT NULL,
                                 `student_id` INT NULL,
                                 PRIMARY KEY (`id`),
                                 INDEX `id_idx` (`student_id` ASC) VISIBLE,
                                 CONSTRAINT `id`
                                     FOREIGN KEY (`student_id`)
                                         REFERENCES `my_db`.`students` (`id`)
                                         ON DELETE SET NULL
                                         ON UPDATE NO ACTION);

