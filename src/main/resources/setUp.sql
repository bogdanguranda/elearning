create table user (username nvarchar(50) primary key, firstname nvarchar(100), lastname nvarchar(100), 
email nvarchar(100) not null unique, SID nvarchar (30), hash nvarchar(100), salt nvarchar(100));

insert into user values ('gbir1474', 'Bogdan', 'Guranda', 'bogdanguranda@gmail.com', '11474', null, null);
insert into user values ('abir1423', 'Georgel', 'Pop', 'georgel_pop@gmail.com', '11342', null, null);
insert into user values ('piir1432', 'Vasile Sorin', 'Iacob', 'vsiacob@yahoo.com', '11444', null, null);

create table course (title nvarchar(150) primary key, about text, recommendedBackground text, suggestedReadings text,
courseFormat text);

insert into course values ('R Programming',  
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

insert into course values ('Cum sa faci cartofi prajiti cand ai ramas fara ulei', 'Chiar ce zice titlul!',
'bla bla', 'bla bla', 'bla bla');