CREATE TABLE `quizDb`.`multichoiceQuiz` (
                                            `id` INT NOT NULL AUTO_INCREMENT,
                                            `question` VARCHAR(1000) NULL,
                                            `answerA` VARCHAR(45) NULL,
                                            `answerB` VARCHAR(45) NULL,
                                            `answerC` VARCHAR(45) NULL,
                                            `answerD` VARCHAR(45) NULL,
                                            `correctAnswer` INT NULL,
                                            `topic` VARCHAR(45) NULL,
                                            PRIMARY KEY (`id`));

CREATE TABLE `quizDb`.`binaryQuiz` (
                                       `id` INT NOT NULL AUTO_INCREMENT,
                                       `question` VARCHAR(10000) NULL,
                                       `correctAnswer` VARCHAR(45) NULL,
                                       `topic` VARCHAR(45) NULL,
                                       PRIMARY KEY (`id`));

CREATE TABLE `quizDb`.`score` (
                                  `id` INT NOT NULL AUTO_INCREMENT,
                                  `user` VARCHAR(45) NULL,
                                  `score` INT NULL,
                                  `topic` VARCHAR(45) NULL,
                                  PRIMARY KEY (`id`));

