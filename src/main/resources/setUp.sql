DROP SCHEMA IF EXISTS `elearning_db`;
CREATE SCHEMA `elearning_db`;

USE elearning_db;

DROP TABLE IF EXISTS `course`;
CREATE TABLE course (
  title                 VARCHAR(150) PRIMARY KEY,
  about                 TEXT,
  recommendedBackground TEXT,
  suggestedReadings     TEXT,
  courseFormat          TEXT
);

INSERT INTO course VALUES ('R Programming',
                           'In this course you will learn how to program in R and how to use R for effective data analysis.
                           You will learn how to install and configure software necessary for a statistical programming environment and
                           describe generic programming language concepts as they are implemented in a high-level statistical language.
                           The course covers practical issues in statistical computing which includes programming in R, reading data into R,
                           accessing R packages, writing R functions, debugging, profiling R code, and organizing and commenting R code.
                           Topics in statistical data analysis will provide working examples.',
                           'Some familiarity with programming concepts will be useful as well basic knowledge of statistical reasoning;
                           Data Scientist\'s Toolbox',
                           'The e-book R Programming for Data Science covers all of the material presented in this course.
                           It is available for download from Leanpub.',
                           'There will be weekly lecture videos, quizzes, and programming assignments.
                           As part of this class you will be required to set up a GitHub account. GitHub is a tool for collaborative
                           code sharing and editing. During this course and other courses in the Specialization you will be submitting
                           links to files you publicly place in your GitHub account as part of peer evaluation. If you are concerned about
                           preserving your anonymity you will need to set up an anonymous GitHub account and be careful not to include
                           any information you do not want made available to peer evaluators.');

INSERT INTO course VALUES ('Cum sa faci cartofi prajiti cand ai ramas fara ulei', 'Chiar ce zice titlul!',
                           'bla bla', 'bla bla', 'bla bla');