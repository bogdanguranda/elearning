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
-- Table `elearning_db`.`group`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elearning_db`.`group` ;

CREATE TABLE IF NOT EXISTS `elearning_db`.`group` (
  `name` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`name`)  COMMENT '')
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
  `associatedGroup` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`title`)  COMMENT '',
  INDEX `username_idx` (`owner` ASC)  COMMENT '',
  CONSTRAINT `course_user_username_fk`
    FOREIGN KEY (`owner`)
    REFERENCES `elearning_db`.`user` (`username`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `course_group_name_fk`
    FOREIGN KEY (`associatedGroup`)
    REFERENCES `elearning_db`.`group` (`name`))
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
-- Table `elearning_db`.`module`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elearning_db`.`module` ;

CREATE TABLE IF NOT EXISTS `elearning_db`.`module` (
  `title` VARCHAR(45) NOT NULL COMMENT '',
  `course` VARCHAR(45) NOT NULL COMMENT '',
  `description` TEXT NULL COMMENT '',
  PRIMARY KEY (`title`, `course`)  COMMENT '',
  CONSTRAINT `module_course_fk`
  FOREIGN KEY (`course`)
  REFERENCES `elearning_db`.`course` (`title`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elearning_db`.`module_file`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elearning_db`.`module_file` ;

CREATE TABLE IF NOT EXISTS `elearning_db`.`module_file` (
  `name` VARCHAR(100) NOT NULL COMMENT '',
  `course` VARCHAR(45) NOT NULL COMMENT '',
  `module` VARCHAR(45) NOT NULL COMMENT '',
  `size` INT NULL COMMENT '',
  `type` VARCHAR(45) NULL COMMENT '',
  `content` MEDIUMBLOB NULL COMMENT '',
  PRIMARY KEY (`name`, `course`, `module`)  COMMENT '',
  CONSTRAINT `file_associatedCourse_course_title_fk`
  FOREIGN KEY (`course`)
  REFERENCES `elearning_db`.`course` (`title`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `file_associatedModule_module_title_fk`
  FOREIGN KEY (`module`)
  REFERENCES `elearning_db`.`module` (`title`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elearning_db`.`file`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elearning_db`.`file` ;


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
-- This does not exist anymore. Left for cleanup purposes.
DROP TABLE IF EXISTS `elearning_db`.`course_group` ;

-- -----------------------------------------------------
-- Table `elearning_db`.`topic`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elearning_db`.`topic` ;

CREATE TABLE IF NOT EXISTS `elearning_db`.`topic` (
  `title` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`title`) COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elearning_db`.`thread`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elearning_db`.`thread` ;

CREATE TABLE IF NOT EXISTS `elearning_db`.`thread` (
  `topic` VARCHAR(45) NOT NULL COMMENT '',
  `title` VARCHAR(45) NOT NULL COMMENT '',
  `owner` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`topic`, `title`)  COMMENT '',
  CONSTRAINT `thread_user_username_fk`
    FOREIGN KEY (`owner`)
    REFERENCES `elearning_db`.`user` (`username`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `thread_topic_fk`
    FOREIGN KEY (`topic`)
    REFERENCES `elearning_db`.`topic` (`title`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elearning_db`.`comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elearning_db`.`comment` ;

CREATE TABLE IF NOT EXISTS `elearning_db`.`comment` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '',
  `owner` VARCHAR(45) NOT NULL COMMENT '',
  `topic` VARCHAR(45) NOT NULL COMMENT '',
  `thread` VARCHAR(45) NOT NULL COMMENT '',
  `message` TEXT NOT NULL COMMENT '',
  `timestamp` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '',
  INDEX `threadID_idx` (`id` ASC)  COMMENT '',
  CONSTRAINT `comment_user_username_fk`
    FOREIGN KEY (`owner`)
    REFERENCES `elearning_db`.`user` (`username`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `comment_topic_fk`
    FOREIGN KEY (`topic`, `thread`)
    REFERENCES `elearning_db`.`thread` (`topic`, `title`)
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
	`operationName` VARCHAR(200) NOT NULL COMMENT '',
	PRIMARY KEY (`operationName`) COMMENT '',
	UNIQUE INDEX `operation_operationName_UNIQUE` (`operationName` ASC)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elearning_db`.`permission`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elearning_db`.`permission` ;

CREATE TABLE IF NOT EXISTS `elearning_db`.`permission` (
  `operationName` VARCHAR(200) NOT NULL COMMENT '',
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


-- -----------------------------------------------------
-- Table `elearning_db`.`audit`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elearning_db`.`audit`;

CREATE TABLE IF NOT EXISTS `elearning_db`.`audit` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '',
  `username` VARCHAR(45) NOT NULL COMMENT '',
  `operationName` VARCHAR(200) NOT NULL COMMENT '',
  `dataReceived` TEXT NOT NULL COMMENT '',
  `response` TEXT NOT NULL COMMENT '',
  `success` BOOLEAN NOT NULL COMMENT '',
  `timestamp` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '',
  INDEX `threadID_idx` (`id` ASC)  COMMENT '',
  CONSTRAINT `audit_user_username_fk`
    FOREIGN KEY (`username`)
    REFERENCES `elearning_db`.`user` (`username`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `audit__operationName_fk`
    FOREIGN KEY (`operationName`)
    REFERENCES `elearning_db`.`operation` (`operationName`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- ===================================================================
-- !!!!!!!!!!!!!!!!!!!!!!! DO NOT DELETE START !!!!!!!!!!!!!!!!!!!!!!!


-- Inserts user roles
-- -------------------------------------------------------------------
INSERT INTO role VALUE ('administrator');
INSERT INTO role VALUE ('professor');
INSERT INTO role VALUE ('student');

-- Inserts operations START
-- -------------------------------------------------------------------
INSERT INTO operation VALUES('AdminControllerImpl.createAccount');
INSERT INTO operation VALUES('AdminControllerImpl.suspendAccount');
INSERT INTO operation VALUES('AdminControllerImpl.reactivateAccount');
INSERT INTO operation VALUES('AdminControllerImpl.changeAccountType');
INSERT INTO operation VALUES('AdminControllerImpl.getAudit');
INSERT INTO operation VALUES('AdminControllerImpl.getAuditForUser');

INSERT INTO operation VALUES('CommentControllerImpl.createComment');
INSERT INTO operation VALUES('CommentControllerImpl.getComment');
INSERT INTO operation VALUES('CommentControllerImpl.getCommentsInThread');
INSERT INTO operation VALUES('CommentControllerImpl.updateComment');
INSERT INTO operation VALUES('CommentControllerImpl.deleteComment');

INSERT INTO operation VALUES('CoursesControllerImpl.getAllCourses');
INSERT INTO operation VALUES('CoursesControllerImpl.createCourse');
INSERT INTO operation VALUES('CoursesControllerImpl.deleteCourse');
INSERT INTO operation VALUES('CoursesControllerImpl.getCourse');
INSERT INTO operation VALUES('CoursesControllerImpl.enrollUserToCourse');
INSERT INTO operation VALUES('CoursesControllerImpl.unEnrollUserFromCourse');
INSERT INTO operation VALUES('CoursesControllerImpl.getEnrolledUsers');
INSERT INTO operation VALUES('CoursesControllerImpl.getAttendedCourses');


INSERT INTO operation VALUES('GroupsControllerImpl.getGroups');

INSERT INTO operation VALUES('ForumThreadControllerImpl.createThread');
INSERT INTO operation VALUES('ForumThreadControllerImpl.getThread');
INSERT INTO operation VALUES('ForumThreadControllerImpl.getThreadsInTopic');
INSERT INTO operation VALUES('ForumThreadControllerImpl.getThreadsOwnedByUser');
INSERT INTO operation VALUES('ForumThreadControllerImpl.getAll');
INSERT INTO operation VALUES('ForumThreadControllerImpl.updateThread');
INSERT INTO operation VALUES('ForumThreadControllerImpl.deleteThread');

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

INSERT INTO operation VALUES('UserControllerImpl.authenticate');
INSERT INTO operation VALUES('UserControllerImpl.signOut');
INSERT INTO operation VALUES('UserControllerImpl.signUp');
INSERT INTO operation VALUES('UserControllerImpl.get');
INSERT INTO operation VALUES('UserControllerImpl.getAll');
INSERT INTO operation VALUES('UserControllerImpl.validateUserAccount');
INSERT INTO operation VALUES('UserControllerImpl.resetPasswordRequest');
INSERT INTO operation VALUES('UserControllerImpl.resetPassword');
INSERT INTO operation VALUES('UserControllerImpl.changePassword');
INSERT INTO operation VALUES('UserControllerImpl.logout');


INSERT INTO operation VALUES('ModuleControllerImpl.createModule');
INSERT INTO operation VALUES('ModuleControllerImpl.deleteModule');
INSERT INTO operation VALUES('ModuleControllerImpl.getAll');
INSERT INTO operation VALUES('ModuleControllerImpl.get');
INSERT INTO operation VALUES('ModuleControllerImpl.renameModule');

INSERT INTO operation VALUES('ModuleFileControllerImpl.uploadFile');
INSERT INTO operation VALUES('ModuleFileControllerImpl.deleteFile');
INSERT INTO operation VALUES('ModuleFileControllerImpl.getFile');
INSERT INTO operation VALUES('ModuleFileControllerImpl.getAll');
INSERT INTO operation VALUES('ModuleFileControllerImpl.renameFile');
-- -------------------------------------------------------------------
-- Inserts operations END


-- Inserts permissions START
-- -------------------------------------------------------------------
-- ##################################################=-AdminControllerImpl START-=############################################################################
INSERT INTO permission (operationName, roleName, permission) VALUES('AdminControllerImpl.createAccount', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('AdminControllerImpl.createAccount', 'professor', false);
INSERT INTO permission (operationName, roleName, permission) VALUES('AdminControllerImpl.createAccount', 'student', false);

INSERT INTO permission (operationName, roleName, permission) VALUES('AdminControllerImpl.suspendAccount', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('AdminControllerImpl.suspendAccount', 'professor', false);
INSERT INTO permission (operationName, roleName, permission) VALUES('AdminControllerImpl.suspendAccount', 'student', false);

INSERT INTO permission (operationName, roleName, permission) VALUES('AdminControllerImpl.reactivateAccount', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('AdminControllerImpl.reactivateAccount', 'professor', false);
INSERT INTO permission (operationName, roleName, permission) VALUES('AdminControllerImpl.reactivateAccount', 'student', false);

INSERT INTO permission (operationName, roleName, permission) VALUES('AdminControllerImpl.changeAccountType', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('AdminControllerImpl.changeAccountType', 'professor', false);
INSERT INTO permission (operationName, roleName, permission) VALUES('AdminControllerImpl.changeAccountType', 'student', false);

INSERT INTO permission (operationName, roleName, permission) VALUES('AdminControllerImpl.getAudit', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('AdminControllerImpl.getAudit', 'professor', false);
INSERT INTO permission (operationName, roleName, permission) VALUES('AdminControllerImpl.getAudit', 'student', false);

INSERT INTO permission (operationName, roleName, permission) VALUES('AdminControllerImpl.getAuditForUser', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('AdminControllerImpl.getAuditForUser', 'professor', false);
INSERT INTO permission (operationName, roleName, permission) VALUES('AdminControllerImpl.getAuditForUser', 'student', false);
-- ###################################################=-AdminControllerImpl END-=#############################################################################

-- #################################################=-CommentControllerImpl START-=###########################################################################
INSERT INTO permission (operationName, roleName, permission) VALUES('CommentControllerImpl.createComment', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('CommentControllerImpl.createComment', 'professor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('CommentControllerImpl.createComment', 'student', true);

INSERT INTO permission (operationName, roleName, permission) VALUES('CommentControllerImpl.getComment', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('CommentControllerImpl.getComment', 'professor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('CommentControllerImpl.getComment', 'student', true);

INSERT INTO permission (operationName, roleName, permission) VALUES('CommentControllerImpl.getCommentsInThread', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('CommentControllerImpl.getCommentsInThread', 'professor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('CommentControllerImpl.getCommentsInThread', 'student', true);

INSERT INTO permission (operationName, roleName, permission) VALUES('CommentControllerImpl.updateComment', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('CommentControllerImpl.updateComment', 'professor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('CommentControllerImpl.updateComment', 'student', true);

INSERT INTO permission (operationName, roleName, permission) VALUES('CommentControllerImpl.deleteComment', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('CommentControllerImpl.deleteComment', 'professor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('CommentControllerImpl.deleteComment', 'student', true);
-- ##################################################=-CommentControllerImpl END-=############################################################################

-- #################################################=-CoursesControllerImpl START-=###########################################################################
INSERT INTO permission (operationName, roleName, permission) VALUES('CoursesControllerImpl.getAllCourses', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('CoursesControllerImpl.getAllCourses', 'professor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('CoursesControllerImpl.getAllCourses', 'student', true);

INSERT INTO permission (operationName, roleName, permission) VALUES('CoursesControllerImpl.createCourse', 'administrator', false);
INSERT INTO permission (operationName, roleName, permission) VALUES('CoursesControllerImpl.createCourse', 'professor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('CoursesControllerImpl.createCourse', 'student', false);

INSERT INTO permission (operationName, roleName, permission) VALUES('CoursesControllerImpl.deleteCourse', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('CoursesControllerImpl.deleteCourse', 'professor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('CoursesControllerImpl.deleteCourse', 'student', false);

INSERT INTO permission (operationName, roleName, permission) VALUES('CoursesControllerImpl.getCourse', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('CoursesControllerImpl.getCourse', 'professor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('CoursesControllerImpl.getCourse', 'student', true);

INSERT INTO permission (operationName, roleName, permission) VALUES('CoursesControllerImpl.enrollUserToCourse', 'administrator', false);
INSERT INTO permission (operationName, roleName, permission) VALUES('CoursesControllerImpl.enrollUserToCourse', 'professor', false);
INSERT INTO permission (operationName, roleName, permission) VALUES('CoursesControllerImpl.enrollUserToCourse', 'student', true);

INSERT INTO permission (operationName, roleName, permission) VALUES('CoursesControllerImpl.unEnrollUserFromCourse', 'administrator', false);
INSERT INTO permission (operationName, roleName, permission) VALUES('CoursesControllerImpl.unEnrollUserFromCourse', 'professor', false);
INSERT INTO permission (operationName, roleName, permission) VALUES('CoursesControllerImpl.unEnrollUserFromCourse', 'student', true);

INSERT INTO permission (operationName, roleName, permission) VALUES('CoursesControllerImpl.getEnrolledUsers', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('CoursesControllerImpl.getEnrolledUsers', 'professor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('CoursesControllerImpl.getEnrolledUsers', 'student', true);
-- ##################################################=-CoursesControllerImpl END-=############################################################################

-- #################################################=-GroupsControllerImpl START-=###########################################################################
INSERT INTO permission (operationName, roleName, permission) VALUES('GroupsControllerImpl.getGroups', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('GroupsControllerImpl.getGroups', 'professor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('GroupsControllerImpl.getGroups', 'student', true);
-- ###################################################=-GroupsControllerImpl END-=###########################################################################

-- #################################################=-ModuleControllerImpl START-=###########################################################################
INSERT INTO permission (operationName, roleName, permission) VALUES('ModuleControllerImpl.createModule', 'administrator', false);
INSERT INTO permission (operationName, roleName, permission) VALUES('ModuleControllerImpl.createModule', 'professor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('ModuleControllerImpl.createModule', 'student', false);

INSERT INTO permission (operationName, roleName, permission) VALUES('ModuleControllerImpl.deleteModule', 'administrator', false);
INSERT INTO permission (operationName, roleName, permission) VALUES('ModuleControllerImpl.deleteModule', 'professor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('ModuleControllerImpl.deleteModule', 'student', false);

INSERT INTO permission (operationName, roleName, permission) VALUES('ModuleControllerImpl.getAll', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('ModuleControllerImpl.getAll', 'professor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('ModuleControllerImpl.getAll', 'student', true);

INSERT INTO permission (operationName, roleName, permission) VALUES('ModuleControllerImpl.get', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('ModuleControllerImpl.get', 'professor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('ModuleControllerImpl.get', 'student', true);

INSERT INTO permission (operationName, roleName, permission) VALUES('ModuleControllerImpl.renameModule', 'administrator', false);
INSERT INTO permission (operationName, roleName, permission) VALUES('ModuleControllerImpl.renameModule', 'professor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('ModuleControllerImpl.renameModule', 'student', false);
-- ###################################################=-ModuleControllerImpl END-=###########################################################################

--  #################################################=-ModuleFileControllerImpl START-=###########################################################################
INSERT INTO permission (operationName, roleName, permission) VALUES('ModuleFileControllerImpl.uploadFile', 'administrator', false);
INSERT INTO permission (operationName, roleName, permission) VALUES('ModuleFileControllerImpl.uploadFile', 'professor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('ModuleFileControllerImpl.uploadFile', 'student', false);

INSERT INTO permission (operationName, roleName, permission) VALUES('ModuleFileControllerImpl.deleteFile', 'administrator', false);
INSERT INTO permission (operationName, roleName, permission) VALUES('ModuleFileControllerImpl.deleteFile', 'professor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('ModuleFileControllerImpl.deleteFile', 'student', false);

INSERT INTO permission (operationName, roleName, permission) VALUES('ModuleFileControllerImpl.getFile', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('ModuleFileControllerImpl.getFile', 'professor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('ModuleFileControllerImpl.getFile', 'student', true);

INSERT INTO permission (operationName, roleName, permission) VALUES('ModuleFileControllerImpl.getAll', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('ModuleFileControllerImpl.getAll', 'professor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('ModuleFileControllerImpl.getAll', 'student', true);

INSERT INTO permission (operationName, roleName, permission) VALUES('ModuleFileControllerImpl.renameFile', 'administrator', false);
INSERT INTO permission (operationName, roleName, permission) VALUES('ModuleFileControllerImpl.renameFile', 'professor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('ModuleFileControllerImpl.renameFile', 'student', false);
--  #################################################=-ModuleFileControllerImpl END-=###########################################################################

-- ###############################################=-ForumThreadControllerImpl START-=#########################################################################
INSERT INTO permission (operationName, roleName, permission) VALUES('ForumThreadControllerImpl.createThread', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('ForumThreadControllerImpl.createThread', 'professor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('ForumThreadControllerImpl.createThread', 'student', true);

INSERT INTO permission (operationName, roleName, permission) VALUES('ForumThreadControllerImpl.getThread', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('ForumThreadControllerImpl.getThread', 'professor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('ForumThreadControllerImpl.getThread', 'student', true);

INSERT INTO permission (operationName, roleName, permission) VALUES('ForumThreadControllerImpl.getThreadsInTopic', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('ForumThreadControllerImpl.getThreadsInTopic', 'professor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('ForumThreadControllerImpl.getThreadsInTopic', 'student', true);

INSERT INTO permission (operationName, roleName, permission) VALUES('ForumThreadControllerImpl.getThreadsOwnedByUser', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('ForumThreadControllerImpl.getThreadsOwnedByUser', 'professor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('ForumThreadControllerImpl.getThreadsOwnedByUser', 'student', true);

INSERT INTO permission (operationName, roleName, permission) VALUES('ForumThreadControllerImpl.getAll', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('ForumThreadControllerImpl.getAll', 'professor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('ForumThreadControllerImpl.getAll', 'student', true);

INSERT INTO permission (operationName, roleName, permission) VALUES('ForumThreadControllerImpl.updateThread', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('ForumThreadControllerImpl.updateThread', 'professor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('ForumThreadControllerImpl.updateThread', 'student', true);

INSERT INTO permission (operationName, roleName, permission) VALUES('ForumThreadControllerImpl.deleteThread', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('ForumThreadControllerImpl.deleteThread', 'professor', false);
INSERT INTO permission (operationName, roleName, permission) VALUES('ForumThreadControllerImpl.deleteThread', 'student', false);
-- ################################################=-ForumThreadControllerImpl END-=##########################################################################

-- #################################################=-MessageControllerImpl START-=###########################################################################
INSERT INTO permission (operationName, roleName, permission) VALUES('MessageControllerImpl.createMessage', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('MessageControllerImpl.createMessage', 'professor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('MessageControllerImpl.createMessage', 'student', true);

INSERT INTO permission (operationName, roleName, permission) VALUES('MessageControllerImpl.getAllMessages', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('MessageControllerImpl.getAllMessages', 'professor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('MessageControllerImpl.getAllMessages', 'student', true);

INSERT INTO permission (operationName, roleName, permission) VALUES('MessageControllerImpl.getMessagesBetweenUsers', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('MessageControllerImpl.getMessagesBetweenUsers', 'professor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('MessageControllerImpl.getMessagesBetweenUsers', 'student', true);

INSERT INTO permission (operationName, roleName, permission) VALUES('MessageControllerImpl.getMessagesByUser', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('MessageControllerImpl.getMessagesByUser', 'professor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('MessageControllerImpl.getMessagesByUser', 'student', true);

INSERT INTO permission (operationName, roleName, permission) VALUES('MessageControllerImpl.deleteUserMessages', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('MessageControllerImpl.deleteUserMessages', 'professor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('MessageControllerImpl.deleteUserMessages', 'student', true);
-- ##################################################=-MessageControllerImpl END-=############################################################################

-- ##################################################=-TopicControllerImpl START-=############################################################################
INSERT INTO permission (operationName, roleName, permission) VALUES('TopicControllerImpl.createTopic', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('TopicControllerImpl.createTopic', 'professor', false);
INSERT INTO permission (operationName, roleName, permission) VALUES('TopicControllerImpl.createTopic', 'student', false);

INSERT INTO permission (operationName, roleName, permission) VALUES('TopicControllerImpl.getAllTopics', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('TopicControllerImpl.getAllTopics', 'professor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('TopicControllerImpl.getAllTopics', 'student', true);

INSERT INTO permission (operationName, roleName, permission) VALUES('TopicControllerImpl.getTopicByTitle', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('TopicControllerImpl.getTopicByTitle', 'professor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('TopicControllerImpl.getTopicByTitle', 'student', true);

INSERT INTO permission (operationName, roleName, permission) VALUES('TopicControllerImpl.updateTopic', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('TopicControllerImpl.updateTopic', 'professor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('TopicControllerImpl.updateTopic', 'student', true);

INSERT INTO permission (operationName, roleName, permission) VALUES('TopicControllerImpl.deleteTopicByTitle', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('TopicControllerImpl.deleteTopicByTitle', 'professor', false);
INSERT INTO permission (operationName, roleName, permission) VALUES('TopicControllerImpl.deleteTopicByTitle', 'student', false);
-- ###################################################=-TopicControllerImpl END-=#############################################################################

-- ##################################################=-UserControllerImpl START-=#############################################################################
INSERT INTO permission (operationName, roleName, permission) VALUES('UserControllerImpl.get', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('UserControllerImpl.get', 'professor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('UserControllerImpl.get', 'student', true);

INSERT INTO permission (operationName, roleName, permission) VALUES('UserControllerImpl.getAll', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('UserControllerImpl.getAll', 'professor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('UserControllerImpl.getAll', 'student', true);

INSERT INTO permission (operationName, roleName, permission) VALUES('UserControllerImpl.changePassword', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('UserControllerImpl.changePassword', 'professor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('UserControllerImpl.changePassword', 'student', true);

INSERT INTO permission (operationName, roleName, permission) VALUES('UserControllerImpl.logout', 'administrator', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('UserControllerImpl.logout', 'professor', true);
INSERT INTO permission (operationName, roleName, permission) VALUES('UserControllerImpl.logout', 'student', true);
-- ###################################################=-UserControllerImpl END-=##############################################################################
-- -------------------------------------------------------------------
-- Inserts permissions END

-- Adds admin user account
-- -------------------------------------------------------------------
INSERT INTO user VALUES('admin', 'Administrator', 'Administrator', 'thecerealkillers.pc@gmail.com', 'c7ad44cbad762a5da0a452f9e854fdc1e0e7a52a38015f23f3eab1d80b931dd472634dfac71cd34ebc35d16ab7fb8a90c81f975113d6c7538dc69dd8de9077ec', '');
INSERT INTO user_status VALUES ('admin', TRUE, DEFAULT, '');
INSERT INTO user_role VALUE ('admin', 'administrator');

INSERT INTO user VALUES('lucian', 'Lucian', 'Iacob', 'lucianiacob@gmail.com', 'efd4e8d764738b55e41e1fe19336f534477e715b88043803f449364691670219b8e9c2db105890a4bc377ec1196b1d8b3ee380a4b8ec3cbfc9f069bb06cab313', '');
INSERT INTO user VALUES('daniel', 'Daniel', 'Iliesi', 'danieliliesi@gmail.com', 'f68a41e098cf7ecb8924645bfe335941beb068e7bebaf0bba26549c0693560df08cace69debbc59f6d2e30a328570dc331c1ec2f998a43cd0340b08065d4318a', '');
INSERT INTO user VALUES('corvin', 'Corvin-Ciprian', 'Tiperciuc', 'cuvidk@yahoo.com', '5006723b1d50f5ad513ccccad426051b237ac50b0250ec22a08052084b03283dd83fbe875e89b42143975821bb203d0e88c4002772afbc4a09c2241962680ed1', '');
INSERT INTO user VALUES('emanuel', 'Emanuel', 'Barac', 'baracemanuel@yahoo.com', '214be8cc3605acd9366b878e827da74105f408a3577315db63b3ea0cefac847cef65a29ee86f3708d39a89baf914a3a38654b1c09fddee64ca835213c42abaaf', '');
INSERT INTO user VALUES('bianca', 'Bianca', 'Felecan', 'bianca.felecan@yahoo.com', 'a6b6fa1ea4adaa7acd47cc3641310936d0e2c77115033c798926dd08815e296e8e1d1b1e04a2334d66419e01c99b993df4ae19203d840594416f8b86434eabaa', '');
INSERT INTO user VALUES('alexandru', 'Alexandru', 'Vancea', 'alexandruvancea@yahoo.com', '534a8adddb4c93bec4a7640c178f0c78bf2cf199bbc869b430a7dd48a78cbddc898112581425849b0bd2c9f8f77cac4b1f4ab16272d5c43a5467799963f36b29', '');
INSERT INTO user_status VALUES ('lucian', TRUE, DEFAULT, '1');
INSERT INTO user_status VALUES ('daniel', TRUE, DEFAULT, '2');
INSERT INTO user_status VALUES ('corvin', TRUE, DEFAULT, '3');
INSERT INTO user_status VALUES ('emanuel', TRUE, DEFAULT, '4');
INSERT INTO user_status VALUES ('bianca', TRUE, DEFAULT, '5');
INSERT INTO user_status VALUES ('alexandru', TRUE, DEFAULT, '6');
INSERT INTO user_role VALUE ('lucian', 'student');
INSERT INTO user_role VALUE ('daniel', 'student');
INSERT INTO user_role VALUE ('corvin', 'student');
INSERT INTO user_role VALUE ('emanuel', 'student');
INSERT INTO user_role VALUE ('bianca', 'student');
INSERT INTO user_role VALUE ('alexandru', 'professor');

-- Inserts topic START
-- -------------------------------------------------------------------
INSERT INTO topic VALUES ('General Discussion');
INSERT INTO topic VALUES ('Courses');
INSERT INTO topic VALUES ('Books & Tutorials');
INSERT INTO topic VALUES ('About E-learning');
INSERT INTO topic VALUES ('Off Topic');
-- -------------------------------------------------------------------
-- Inserts topic END

INSERT INTO thread VALUES ('Off Topic', 'Are panda bears nearly extinct?', 'corvin');
INSERT INTO thread VALUES ('Off Topic', 'How did Napoleon die?', 'lucian');
INSERT INTO thread VALUES ('Off Topic', 'Is Kung Fu really that good?', 'corvin');
INSERT INTO thread VALUES ('Off Topic', 'Fun facts about French Bulldogs', 'corvin');
INSERT INTO thread VALUES ('Off Topic', 'Help pls...', 'daniel');

INSERT INTO comment VALUES (DEFAULT, 'corvin', 'Off Topic', 'How did Napoleon die?',
                            'Some say he was poisoned on St. Elena island in the Pacific Ocean...', DEFAULT);
INSERT INTO comment VALUES (DEFAULT, 'corvin', 'Off Topic', 'How did Napoleon die?',
                            'There is also the option that he died of the bad climate.', DEFAULT);
INSERT INTO comment VALUES (DEFAULT, 'emanuel', 'Off Topic', 'How did Napoleon die?',
                            'Most likely he was poisoned having in mind the fear that the english man had of him.', DEFAULT);
INSERT INTO comment VALUES (DEFAULT, 'lucian', 'Off Topic', 'How did Napoleon die?',
                            'Most likely...', DEFAULT);
INSERT INTO comment VALUES (DEFAULT, 'corvin', 'Off Topic', 'How did Napoleon die?',
                            'Yep :)', DEFAULT);

INSERT INTO thread VALUES ('General Discussion', 'Forum rules', 'corvin');
INSERT INTO thread VALUES ('General Discussion', 'Tips and tricks', 'lucian');

INSERT INTO comment VALUES (DEFAULT, 'corvin', 'General Discussion', 'Forum rules',
                            'Forum rules:\n1. Be patience with newcomers\n2. Don\'t swear.', DEFAULT);
INSERT INTO comment VALUES (DEFAULT, 'lucian', 'General Discussion', 'Tips and tricks',
                            'Tips and tricks:\n1. Use Chrome Browser for fast speed.', DEFAULT);
-- dev only end

-- Inserts course modules start
INSERT INTO elearning_db.group VALUES ('GROUP_CS AC');
INSERT INTO course VALUES ('Arhitectura Calculatoarelor', 'Limbajul de asamblare 8086', 'Un curs introductiv in istoria sistemelor de calcul, a componentei acestora avand ca studiu practic limbajul de asamblare 8086', 'alexandru', 'GROUP_CS AC');
INSERT INTO module VALUES ('Arhitectura microprocesor 8086', 'Arhitectura Calculatoarelor', 'Introducere in arhitectura microprocesorului 8086');
INSERT INTO module VALUES ('Elementele de baza ale limbajului 8086', 'Arhitectura Calculatoarelor', 'Intructiuni, registrii, adrese.');
INSERT INTO module VALUES ('Instructiuni ale limbajului de asamblare 8086', 'Arhitectura Calculatoarelor', 'Instructiuni de baza: mov, lea, define, byte etc.');
INSERT INTO module VALUES ('Intreruperi 8086', 'Arhitectura Calculatoarelor', 'Intreruperile de citire/scriere caractere/siruri de caractere, int 21h etc.');
INSERT INTO module VALUES ('Implementarea apelului de subprograme', 'Arhitectura Calculatoarelor', 'Cum implementam apelul de subprograme ca in limbajele de nivel inalt.');
INSERT INTO module VALUES ('Programarea multimodul', 'Arhitectura Calculatoarelor', 'Compilare separata, instructiunile de import/export.');

INSERT INTO elearning_db.group VALUES ('GROUP_CS FLP');
INSERT INTO course VALUES ('Fundamentele Limbajelor de Programare', 'Istoria dezvoltarii limbajelor de programare', 'Un curs ce-si propune sa prezinte istoria evolutiei limbajelor de programare de la inceputul anilor \'50 incoace.', 'alexandru', 'GROUP_CS FLP');
INSERT INTO module VALUES ('FORTRAN', 'Fundamentele Limbajelor de Programare', 'Studiu de caz limbajul FORTRAN.');
INSERT INTO module VALUES ('Ada', 'Fundamentele Limbajelor de Programare', 'Studiu de caz asupra limbajului Ada (denumire dupa numele primului programator).');
INSERT INTO module VALUES ('Miranda', 'Fundamentele Limbajelor de Programare', 'Studiu de caz limbajul Miranda.');
INSERT INTO module VALUES ('Lisp', 'Fundamentele Limbajelor de Programare', 'Studiu de caz un limbaj functional, limbajul Lisp.');
--  Inserts course modules end

-- !!!!!!!!!!!!!!!!!!!!!!!! DO NOT DELETE END !!!!!!!!!!!!!!!!!!!!!!!!
-- ===================================================================