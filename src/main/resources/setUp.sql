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
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `sender` VARCHAR(45) NOT NULL COMMENT '',
  `receiver` VARCHAR(45) NOT NULL COMMENT '',
  `timestamp` DATETIME NULL DEFAULT CURRENT_TIMESTAMP COMMENT '',
  `message` TEXT NULL COMMENT '',
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
  `creationTimestamp` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '',
  PRIMARY KEY (`username`)  COMMENT '',
  UNIQUE INDEX `token_UNIQUE` (`token` ASC)  COMMENT '',
  CONSTRAINT `session_username_fk`
    FOREIGN KEY (`username`)
    REFERENCES `elearning_db`.`user` (`username`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elearning_db`.`operation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elearning_db`.`operation` ;

CREATE TABLE IF NOT EXISTS `elearning_db`.`operation` (
	`operationName` VARCHAR(300) NOT NULL COMMENT '',
	PRIMARY KEY (`operationName`) COMMENT '',
	UNIQUE INDEX `operation_operationName_UNIQUE` (`operationName` ASC)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elearning_db`.`permission`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elearning_db`.`permission` ;

CREATE TABLE IF NOT EXISTS `elearning_db`.`permission` (
  `operationName` VARCHAR(300) NOT NULL COMMENT '',
  `roleName` VARCHAR(45) NOT NULL COMMENT '',
  `permission` boolean  NOT NULL DEFAULT FALSE COMMENT '',
  PRIMARY KEY (`operationName`, `roleName`)  COMMENT '',
  INDEX `permission_operationName_idx` (`operationName` ASC)  COMMENT '',
  CONSTRAINT `permission_operationName_fk`
    FOREIGN KEY (`operationName`)
    REFERENCES `elearning_db`.`operation` (`operationName`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `permission_roleName_fk`
    FOREIGN KEY (`roleName`)
    REFERENCES `elearning_db`.`role` (`name`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;






-- ===================================================================
-- !!!!!!!!!!!!!!!!!!!!!!! DO NOT DELETE START !!!!!!!!!!!!!!!!!!!!!!!


-- Inserts user roles
-- -------------------------------------------------------------------
INSERT INTO role VALUE ('administrator');
INSERT INTO role VALUE ('profesor');
INSERT INTO role VALUE ('student');


-- Adds admin user account
-- -------------------------------------------------------------------
INSERT INTO user VALUES('CerealKillersAdmin1', 'cereal', 'killers', 'thecerealkillers.pc@gmail.com', 'af212b1d8d174716cc1da021b4c5a9c4f5f1b569c22ddb88660a9f242a58e77e43622af22b5c8178a51efaaf93b2bcb63719b5b5d85eb06e1de19610f5e86d3b', 'SYpspH4x6cwPtBf72UoB8Z4l13O49tfBYmGIcGhfgV5hAJGaBojqk3JnvvToo0u6e7PlFFqsOXyDsefrIWC9BRFnBBJrDbQcSHqDs3LiZGtOjmnL9z71He98bhsjYNxw');
INSERT INTO user_status VALUES ('CerealKillersAdmin1', TRUE, DEFAULT, '');
INSERT INTO user_role VALUE ('CerealKillersAdmin1', 'administrator');


-- Inserts operations START
-- -------------------------------------------------------------------
INSERT INTO operation VALUES('AdminControllerImpl.createAccount');
INSERT INTO operation VALUES('AdminControllerImpl.suspendAccount');
INSERT INTO operation VALUES('AdminControllerImpl.reactivateAccount');
INSERT INTO operation VALUES('AdminControllerImpl.changeAccountType');
INSERT INTO operation VALUES('AdminControllerImpl.getAudit');
INSERT INTO operation VALUES('AdminControllerImpl.getAuditForUser');

INSERT INTO operation VALUES('CommentControllerImpl.createComment');
INSERT INTO operation VALUES('CommentControllerImpl.getCommentByOwnerAndTimeStamp');
INSERT INTO operation VALUES('CommentControllerImpl.getCommentsForThread');
INSERT INTO operation VALUES('CommentControllerImpl.updateComment');
INSERT INTO operation VALUES('CommentControllerImpl.deleteComment');
INSERT INTO operation VALUES('CoursesControllerImpl.getAllCourses');
INSERT INTO operation VALUES('CoursesControllerImpl.createCourse');
INSERT INTO operation VALUES('CoursesControllerImpl.deleteCourse');
INSERT INTO operation VALUES('CoursesControllerImpl.getCourse');

INSERT INTO operation VALUES('ForumThreadControllerImpl.createThread');
INSERT INTO operation VALUES('ForumThreadControllerImpl.getAll');
INSERT INTO operation VALUES('ForumThreadControllerImpl.getThreadsOwnedByUser');
INSERT INTO operation VALUES('ForumThreadControllerImpl.getThreadByTitle');
INSERT INTO operation VALUES('ForumThreadControllerImpl.getThreadsForTopic');
INSERT INTO operation VALUES('ForumThreadControllerImpl.updateThread');
INSERT INTO operation VALUES('ForumThreadControllerImpl.deleteThreadByTitle');

INSERT INTO operation VALUES('MessageControllerImpl.createMessage');
INSERT INTO operation VALUES('MessageControllerImpl.getAllMessages');
INSERT INTO operation VALUES('MessageControllerImpl.getMessagesBetweenUsers');
INSERT INTO operation VALUES('MessageControllerImpl.getMessagesByUser');
INSERT INTO operation VALUES('MessageControllerImpl.deleteUserMessages');

INSERT INTO operation VALUES('TopicControllerImpl.createTopic');
INSERT INTO operation VALUES('TopicControllerImpl.getAllTopics');
INSERT INTO operation VALUES('TopicControllerImpl.getTopicByTitle');
INSERT INTO operation VALUES('TopicControllerImpl.updateTopic');
INSERT INTO operation VALUES('TopicControllerImpl.deleteTopicByTitle');

INSERT INTO operation VALUES('UserControllerImpl.get');
INSERT INTO operation VALUES('UserControllerImpl.getAll');
INSERT INTO operation VALUES('UserControllerImpl.changePassword');
-- -------------------------------------------------------------------
-- Inserts operations END


-- Inserts permissions START
-- -------------------------------------------------------------------
-- ##################################################=-AdminControllerImpl START-=############################################################################
INSERT INTO permission (operationName, roleName, permission) VALUES('AdminControllerImpl.createAccount', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('AdminControllerImpl.createAccount', 'profesor', false);
INSERT INTO permission (operationName, roleName, permission) VALUES('AdminControllerImpl.createAccount', 'student', false);

INSERT INTO permission (operationName, roleName, permission) VALUES('AdminControllerImpl.suspendAccount', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('AdminControllerImpl.suspendAccount', 'profesor', false);
INSERT INTO permission (operationName, roleName, permission) VALUES('AdminControllerImpl.suspendAccount', 'student', false);

INSERT INTO permission (operationName, roleName, permission) VALUES('AdminControllerImpl.reactivateAccount', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('AdminControllerImpl.reactivateAccount', 'profesor', false);
INSERT INTO permission (operationName, roleName, permission) VALUES('AdminControllerImpl.reactivateAccount', 'student', false);

INSERT INTO permission (operationName, roleName, permission) VALUES('AdminControllerImpl.changeAccountType', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('AdminControllerImpl.changeAccountType', 'profesor', false);
INSERT INTO permission (operationName, roleName, permission) VALUES('AdminControllerImpl.changeAccountType', 'student', false);

INSERT INTO permission (operationName, roleName, permission) VALUES('AdminControllerImpl.getAudit', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('AdminControllerImpl.getAudit', 'profesor', false);
INSERT INTO permission (operationName, roleName, permission) VALUES('AdminControllerImpl.getAudit', 'student', false);

INSERT INTO permission (operationName, roleName, permission) VALUES('AdminControllerImpl.getAuditForUser', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('AdminControllerImpl.getAuditForUser', 'profesor', false);
INSERT INTO permission (operationName, roleName, permission) VALUES('AdminControllerImpl.getAuditForUser', 'student', false);
-- ###################################################=-AdminControllerImpl END-=#############################################################################

-- #################################################=-CommentControllerImpl START-=###########################################################################
INSERT INTO permission (operationName, roleName, permission) VALUES('CommentControllerImpl.createComment', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('CommentControllerImpl.createComment', 'profesor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('CommentControllerImpl.createComment', 'student', true);

INSERT INTO permission (operationName, roleName, permission) VALUES('CommentControllerImpl.getCommentByOwnerAndTimeStamp', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('CommentControllerImpl.getCommentByOwnerAndTimeStamp', 'profesor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('CommentControllerImpl.getCommentByOwnerAndTimeStamp', 'student', true);

INSERT INTO permission (operationName, roleName, permission) VALUES('CommentControllerImpl.getCommentsForThread', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('CommentControllerImpl.getCommentsForThread', 'profesor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('CommentControllerImpl.getCommentsForThread', 'student', true);

INSERT INTO permission (operationName, roleName, permission) VALUES('CommentControllerImpl.updateComment', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('CommentControllerImpl.updateComment', 'profesor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('CommentControllerImpl.updateComment', 'student', true);

INSERT INTO permission (operationName, roleName, permission) VALUES('CommentControllerImpl.deleteComment', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('CommentControllerImpl.deleteComment', 'profesor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('CommentControllerImpl.deleteComment', 'student', true);
-- ##################################################=-CommentControllerImpl END-=############################################################################

-- #################################################=-CoursesControllerImpl START-=###########################################################################
INSERT INTO permission (operationName, roleName, permission) VALUES('CoursesControllerImpl.getAllCourses', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('CoursesControllerImpl.getAllCourses', 'profesor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('CoursesControllerImpl.getAllCourses', 'student', true);

INSERT INTO permission (operationName, roleName, permission) VALUES('CoursesControllerImpl.createCourse', 'administrator', false);
INSERT INTO permission (operationName, roleName, permission) VALUES('CoursesControllerImpl.createCourse', 'profesor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('CoursesControllerImpl.createCourse', 'student', false);

INSERT INTO permission (operationName, roleName, permission) VALUES('CoursesControllerImpl.deleteCourse', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('CoursesControllerImpl.deleteCourse', 'profesor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('CoursesControllerImpl.deleteCourse', 'student', false);

INSERT INTO permission (operationName, roleName, permission) VALUES('CoursesControllerImpl.getCourse', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('CoursesControllerImpl.getCourse', 'profesor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('CoursesControllerImpl.getCourse', 'student', true);
-- ##################################################=-CoursesControllerImpl END-=############################################################################

-- ###############################################=-ForumThreadControllerImpl START-=#########################################################################
INSERT INTO permission (operationName, roleName, permission) VALUES('ForumThreadControllerImpl.createThread', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('ForumThreadControllerImpl.createThread', 'profesor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('ForumThreadControllerImpl.createThread', 'student', true);

INSERT INTO permission (operationName, roleName, permission) VALUES('ForumThreadControllerImpl.getAll', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('ForumThreadControllerImpl.getAll', 'profesor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('ForumThreadControllerImpl.getAll', 'student', true);

INSERT INTO permission (operationName, roleName, permission) VALUES('ForumThreadControllerImpl.getThreadsOwnedByUser', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('ForumThreadControllerImpl.getThreadsOwnedByUser', 'profesor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('ForumThreadControllerImpl.getThreadsOwnedByUser', 'student', true);

INSERT INTO permission (operationName, roleName, permission) VALUES('ForumThreadControllerImpl.getThreadByTitle', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('ForumThreadControllerImpl.getThreadByTitle', 'profesor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('ForumThreadControllerImpl.getThreadByTitle', 'student', true);

INSERT INTO permission (operationName, roleName, permission) VALUES('ForumThreadControllerImpl.getThreadsForTopic', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('ForumThreadControllerImpl.getThreadsForTopic', 'profesor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('ForumThreadControllerImpl.getThreadsForTopic', 'student', true);

INSERT INTO permission (operationName, roleName, permission) VALUES('ForumThreadControllerImpl.updateThread', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('ForumThreadControllerImpl.updateThread', 'profesor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('ForumThreadControllerImpl.updateThread', 'student', true);

INSERT INTO permission (operationName, roleName, permission) VALUES('ForumThreadControllerImpl.deleteThreadByTitle', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('ForumThreadControllerImpl.deleteThreadByTitle', 'profesor', false);
INSERT INTO permission (operationName, roleName, permission) VALUES('ForumThreadControllerImpl.deleteThreadByTitle', 'student', false);
-- ################################################=-ForumThreadControllerImpl END-=##########################################################################

-- #################################################=-MessageControllerImpl START-=###########################################################################
INSERT INTO permission (operationName, roleName, permission) VALUES('MessageControllerImpl.createMessage', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('MessageControllerImpl.createMessage', 'profesor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('MessageControllerImpl.createMessage', 'student', true);

INSERT INTO permission (operationName, roleName, permission) VALUES('MessageControllerImpl.getAllMessages', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('MessageControllerImpl.getAllMessages', 'profesor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('MessageControllerImpl.getAllMessages', 'student', true);

INSERT INTO permission (operationName, roleName, permission) VALUES('MessageControllerImpl.getMessagesBetweenUsers', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('MessageControllerImpl.getMessagesBetweenUsers', 'profesor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('MessageControllerImpl.getMessagesBetweenUsers', 'student', true);

INSERT INTO permission (operationName, roleName, permission) VALUES('MessageControllerImpl.getMessagesByUser', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('MessageControllerImpl.getMessagesByUser', 'profesor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('MessageControllerImpl.getMessagesByUser', 'student', true);

INSERT INTO permission (operationName, roleName, permission) VALUES('MessageControllerImpl.deleteUserMessages', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('MessageControllerImpl.deleteUserMessages', 'profesor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('MessageControllerImpl.deleteUserMessages', 'student', true);
-- ##################################################=-MessageControllerImpl END-=############################################################################

-- ##################################################=-TopicControllerImpl START-=############################################################################
INSERT INTO permission (operationName, roleName, permission) VALUES('TopicControllerImpl.createTopic', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('TopicControllerImpl.createTopic', 'profesor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('TopicControllerImpl.createTopic', 'student', true);

INSERT INTO permission (operationName, roleName, permission) VALUES('TopicControllerImpl.getAllTopics', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('TopicControllerImpl.getAllTopics', 'profesor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('TopicControllerImpl.getAllTopics', 'student', true);

INSERT INTO permission (operationName, roleName, permission) VALUES('TopicControllerImpl.getTopicByTitle', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('TopicControllerImpl.getTopicByTitle', 'profesor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('TopicControllerImpl.getTopicByTitle', 'student', true);

INSERT INTO permission (operationName, roleName, permission) VALUES('TopicControllerImpl.updateTopic', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('TopicControllerImpl.updateTopic', 'profesor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('TopicControllerImpl.updateTopic', 'student', true);

INSERT INTO permission (operationName, roleName, permission) VALUES('TopicControllerImpl.deleteTopicByTitle', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('TopicControllerImpl.deleteTopicByTitle', 'profesor', false);
INSERT INTO permission (operationName, roleName, permission) VALUES('TopicControllerImpl.deleteTopicByTitle', 'student', false);
-- ###################################################=-TopicControllerImpl END-=#############################################################################

-- ##################################################=-UserControllerImpl START-=#############################################################################
INSERT INTO permission (operationName, roleName, permission) VALUES('UserControllerImpl.get', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('UserControllerImpl.get', 'profesor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('UserControllerImpl.get', 'student', true);

INSERT INTO permission (operationName, roleName, permission) VALUES('UserControllerImpl.getAll', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('UserControllerImpl.getAll', 'profesor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('UserControllerImpl.getAll', 'student', true);

INSERT INTO permission (operationName, roleName, permission) VALUES('UserControllerImpl.changePassword', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('UserControllerImpl.changePassword', 'profesor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('UserControllerImpl.changePassword', 'student', true);
-- ###################################################=-UserControllerImpl END-=##############################################################################
-- -------------------------------------------------------------------
-- Inserts permissions END


-- !!!!!!!!!!!!!!!!!!!!!!!! DO NOT DELETE END !!!!!!!!!!!!!!!!!!!!!!!!
-- ===================================================================