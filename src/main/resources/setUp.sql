-- -----------------------------------------------------
-- Schema elearning_db
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `elearning_db` ;
CREATE SCHEMA IF NOT EXISTS `elearning_db` DEFAULT CHARACTER SET utf8 ;
USE `elearning_db` ;

-- -----------------------------------------------------
-- Table `elearning_db`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elearning_db`.`user` ;

CREATE TABLE IF NOT EXISTS `elearning_db`.`user` (
  `username` VARCHAR(45) NOT NULL COMMENT '',
  `firstName` VARCHAR(45) NULL COMMENT '',
  `lastName` VARCHAR(45) NULL COMMENT '',
  `email` VARCHAR(45) NULL COMMENT '',
  `hash` VARCHAR(128) NULL COMMENT '',
  `salt` VARCHAR(128) NULL COMMENT '',
  PRIMARY KEY (`username`)  COMMENT '',
  UNIQUE INDEX `email_UNIQUE` (`email` ASC)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elearning_db`.`course`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elearning_db`.`course` ;

CREATE TABLE IF NOT EXISTS `elearning_db`.`course` (
  `title` VARCHAR(150) NOT NULL COMMENT '',
  `about` TEXT NULL DEFAULT NULL COMMENT '',
  `details` TEXT NULL DEFAULT NULL COMMENT '',
  `owner` VARCHAR(45) NULL COMMENT '',
  PRIMARY KEY (`title`)  COMMENT '',
  INDEX `username_idx` (`owner` ASC)  COMMENT '',
  CONSTRAINT `course_user_username_fk`
    FOREIGN KEY (`owner`)
    REFERENCES `elearning_db`.`user` (`username`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `elearning_db`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elearning_db`.`role` ;

CREATE TABLE IF NOT EXISTS `elearning_db`.`role` (
  `name` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`name`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elearning_db`.`user_role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elearning_db`.`user_role` ;

CREATE TABLE IF NOT EXISTS `elearning_db`.`user_role` (
  `username` VARCHAR(45) NOT NULL COMMENT '',
  `role` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`username`, `role`)  COMMENT '',
  INDEX `role_idx` (`role` ASC)  COMMENT '',
  CONSTRAINT `user_role_user_username_fk`
    FOREIGN KEY (`username`)
    REFERENCES `elearning_db`.`user` (`username`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `user_role_role_name_fk`
    FOREIGN KEY (`role`)
    REFERENCES `elearning_db`.`role` (`name`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elearning_db`.`group`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elearning_db`.`group` ;

CREATE TABLE IF NOT EXISTS `elearning_db`.`group` (
  `name` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`name`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elearning_db`.`group_user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elearning_db`.`group_user` ;

CREATE TABLE IF NOT EXISTS `elearning_db`.`group_user` (
  `group` VARCHAR(45) NOT NULL COMMENT '',
  `username` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`group`, `username`)  COMMENT '',
  INDEX `username_idx` (`username` ASC)  COMMENT '',
  CONSTRAINT `group_user_group_name_fk`
    FOREIGN KEY (`group`)
    REFERENCES `elearning_db`.`group` (`name`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `group_user_user_username_fk`
    FOREIGN KEY (`username`)
    REFERENCES `elearning_db`.`user` (`username`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elearning_db`.`user_status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elearning_db`.`user_status` ;

CREATE TABLE IF NOT EXISTS `elearning_db`.`user_status` (
  `username` VARCHAR(45) NOT NULL COMMENT '',
  `active` TINYINT(1) NOT NULL COMMENT '',
  `signUpTimestamp` DATETIME NULL DEFAULT CURRENT_TIMESTAMP COMMENT '',
  `token` VARCHAR(36) NULL COMMENT '',
  UNIQUE INDEX `token_UNIQUE` (`token` ASC)  COMMENT '',
  PRIMARY KEY (`username`)  COMMENT '',
  CONSTRAINT `status_user_username_fk`
    FOREIGN KEY (`username`)
    REFERENCES `elearning_db`.`user` (`username`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elearning_db`.`message`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elearning_db`.`message` ;

CREATE TABLE IF NOT EXISTS `elearning_db`.`message` (
  `sender` VARCHAR(45) NOT NULL COMMENT '',
  `receiver` VARCHAR(45) NOT NULL COMMENT '',
  `timestamp` DATETIME NULL DEFAULT CURRENT_TIMESTAMP COMMENT '',
  `message` TEXT NULL COMMENT '',
  PRIMARY KEY (`sender`, `receiver`)  COMMENT '',
  INDEX `username_idx` (`receiver` ASC)  COMMENT '',
  CONSTRAINT `sender_username_fk`
    FOREIGN KEY (`sender`)
    REFERENCES `elearning_db`.`user` (`username`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `receiver_username_fk`
    FOREIGN KEY (`receiver`)
    REFERENCES `elearning_db`.`user` (`username`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elearning_db`.`file`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elearning_db`.`file` ;

CREATE TABLE IF NOT EXISTS `elearning_db`.`file` (
  `name` VARCHAR(100) NOT NULL COMMENT '',
  `size` INT NULL COMMENT '',
  `extension` VARCHAR(45) NULL COMMENT '',
  `content` MEDIUMBLOB NULL COMMENT '',
  PRIMARY KEY (`name`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elearning_db`.`module`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elearning_db`.`module` ;

CREATE TABLE IF NOT EXISTS `elearning_db`.`module` (
  `title` VARCHAR(45) NOT NULL COMMENT '',
  `content` TEXT NULL COMMENT '',
  PRIMARY KEY (`title`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elearning_db`.`module_file`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elearning_db`.`module_file` ;

CREATE TABLE IF NOT EXISTS `elearning_db`.`module_file` (
  `module` VARCHAR(45) NOT NULL COMMENT '',
  `file` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`module`, `file`)  COMMENT '',
  INDEX `file_idx` (`file` ASC)  COMMENT '',
  CONSTRAINT `module_file_module_title_fk`
    FOREIGN KEY (`module`)
    REFERENCES `elearning_db`.`module` (`title`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `module_file_file_name_fk`
    FOREIGN KEY (`file`)
    REFERENCES `elearning_db`.`file` (`name`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elearning_db`.`homework`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elearning_db`.`homework` ;

CREATE TABLE IF NOT EXISTS `elearning_db`.`homework` (
  `title` VARCHAR(45) NOT NULL COMMENT '',
  `content` TEXT NULL COMMENT '',
  PRIMARY KEY (`title`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elearning_db`.`group_homework`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elearning_db`.`group_homework` ;

CREATE TABLE IF NOT EXISTS `elearning_db`.`group_homework` (
  `group` VARCHAR(45) NOT NULL COMMENT '',
  `homework` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`group`, `homework`)  COMMENT '',
  INDEX `title_idx` (`homework` ASC)  COMMENT '',
  CONSTRAINT `group_homework_group_name_fk`
    FOREIGN KEY (`group`)
    REFERENCES `elearning_db`.`group` (`name`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `group_homework_homework_title_fk`
    FOREIGN KEY (`homework`)
    REFERENCES `elearning_db`.`homework` (`title`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elearning_db`.`course_group`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elearning_db`.`course_group` ;

CREATE TABLE IF NOT EXISTS `elearning_db`.`course_group` (
  `course` VARCHAR(45) NOT NULL COMMENT '',
  `group` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`course`, `group`)  COMMENT '',
  INDEX `name_idx` (`group` ASC)  COMMENT '',
  CONSTRAINT `course_group_course_title_fk`
    FOREIGN KEY (`course`)
    REFERENCES `elearning_db`.`course` (`title`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `course_group_group_name_fk`
    FOREIGN KEY (`group`)
    REFERENCES `elearning_db`.`group` (`name`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elearning_db`.`course_module`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elearning_db`.`course_module` ;

CREATE TABLE IF NOT EXISTS `elearning_db`.`course_module` (
  `course` VARCHAR(45) NOT NULL COMMENT '',
  `module` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`course`, `module`)  COMMENT '',
  INDEX `title_idx` (`module` ASC)  COMMENT '',
  CONSTRAINT `course_module_course_title_fk`
    FOREIGN KEY (`course`)
    REFERENCES `elearning_db`.`course` (`title`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `course_module_module_title_fk`
    FOREIGN KEY (`module`)
    REFERENCES `elearning_db`.`module` (`title`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elearning_db`.`topic`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elearning_db`.`topic` ;

CREATE TABLE IF NOT EXISTS `elearning_db`.`topic` (
  `title` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`title`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elearning_db`.`thread`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elearning_db`.`thread` ;

CREATE TABLE IF NOT EXISTS `elearning_db`.`thread` (
  `title` VARCHAR(45) NOT NULL COMMENT '',
  `owner` VARCHAR(45) NULL COMMENT '',
  PRIMARY KEY (`title`)  COMMENT '',
  INDEX `username_idx` (`owner` ASC)  COMMENT '',
  CONSTRAINT `thread_user_username_fk`
    FOREIGN KEY (`owner`)
    REFERENCES `elearning_db`.`user` (`username`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elearning_db`.`comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elearning_db`.`comment` ;

CREATE TABLE IF NOT EXISTS `elearning_db`.`comment` (
  `owner` VARCHAR(45) NOT NULL COMMENT '',
  `timestamp` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '',
  `message` TEXT NULL COMMENT '',
  PRIMARY KEY (`owner`, `timestamp`)  COMMENT '',
  CONSTRAINT `comment_user_username_fk`
    FOREIGN KEY (`owner`)
    REFERENCES `elearning_db`.`user` (`username`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elearning_db`.`thread_comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elearning_db`.`thread_comment` ;

CREATE TABLE IF NOT EXISTS `elearning_db`.`thread_comment` (
  `thread` VARCHAR(45) NOT NULL COMMENT '',
  `commentOwner` VARCHAR(45) NOT NULL COMMENT '',
  `timestamp` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '',
  PRIMARY KEY (`thread`, `commentOwner`, `timestamp`)  COMMENT '',
  INDEX `owner_idx` (`commentOwner` ASC, `timestamp` ASC)  COMMENT '',
  CONSTRAINT `thread_comment_thread_title_fk`
    FOREIGN KEY (`thread`)
    REFERENCES `elearning_db`.`thread` (`title`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `thread_comment_composite_fk`
    FOREIGN KEY (`commentOwner` , `timestamp`)
    REFERENCES `elearning_db`.`comment` (`owner` , `timestamp`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elearning_db`.`topic_thread`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elearning_db`.`topic_thread` ;

CREATE TABLE IF NOT EXISTS `elearning_db`.`topic_thread` (
  `topic` VARCHAR(45) NOT NULL COMMENT '',
  `thread` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`topic`, `thread`)  COMMENT '',
  INDEX `title_idx` (`thread` ASC)  COMMENT '',
  CONSTRAINT `topic_thread_topic_title_fk`
    FOREIGN KEY (`topic`)
    REFERENCES `elearning_db`.`topic` (`title`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `topic_thread_thread_title_fk`
    FOREIGN KEY (`thread`)
    REFERENCES `elearning_db`.`thread` (`title`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elearning_db`.`session`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elearning_db`.`session` ;

CREATE TABLE IF NOT EXISTS `elearning_db`.`session` (
  `username` VARCHAR(45) NOT NULL COMMENT '',
  `token` VARCHAR(36) NULL COMMENT '',
  `creationTimestamp` DATETIME NULL DEFAULT CURRENT_TIMESTAMP COMMENT '',
  PRIMARY KEY (`username`)  COMMENT '',
  UNIQUE INDEX `token_UNIQUE` (`token` ASC)  COMMENT '',
  CONSTRAINT `session_username_fk`
    FOREIGN KEY (`username`)
    REFERENCES `elearning_db`.`user` (`username`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

INSERT INTO role VALUE ('student');
INSERT INTO role VALUE ('administrator');
INSERT INTO role VALUE ('profesor');