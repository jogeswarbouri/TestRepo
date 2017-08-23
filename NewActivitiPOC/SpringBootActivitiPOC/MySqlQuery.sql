create database IF NOT EXISTS K_ACTIVITI_POC;

CREATE TABLE IF NOT EXISTS K_ACTIVITI_POC.USER (user_id int(5) NOT NULL AUTO_INCREMENT, user_name varchar(20)NOT NULL, user_pw varchar(50)NOT NULL, PRIMARY KEY (`user_id`));

INSERT INTO K_ACTIVITI_POC.USER (`user_id`, `user_name`, `user_pw`) VALUES
	(1, 'admin', 'admin'),
	(2, 'userA', 'userA'),
	(3, 'userB', 'UserB'),
	(4, 'userC', 'userC'),
	(5, 'userD', 'userD'),
	(6, 'userE', 'userE'),
	(7, 'userF', 'userF'),
	(8, 'userG', 'userG'); 

CREATE TABLE IF NOT EXISTS K_ACTIVITI_POC.REVIEW(rev_id int(5) NOT NULL AUTO_INCREMENT, review_type varchar(50)NOT NULL, PRIMARY KEY (`rev_id`));

INSERT INTO K_ACTIVITI_POC.REVIEW (`rev_id`, `review_type`) VALUES
	(1, 'Credit Review'),
	(2, 'Compliance Review'),
	(3, 'Asset Review');

CREATE TABLE IF NOT EXISTS K_ACTIVITI_POC.TASK (task_id int(5) NOT NULL AUTO_INCREMENT, task_name varchar(50)NOT NULL, sla varchar(50)NOT NULL, PRIMARY KEY (`task_id`),
rev_id int, CONSTRAINT FK_REVIEW FOREIGN KEY (rev_id) REFERENCES K_ACTIVITI_POC.REVIEW (rev_id));

insert into  k_activiti_poc.task (task_name,sla,rev_id) values ('Paystubs','3 hrs',1); 	
insert into  k_activiti_poc.task (task_name,sla,rev_id) values ('W2','4 hrs',1);	
insert into  k_activiti_poc.task (task_name,sla,rev_id) values ('Tax Returns','5 hrs',1);	
insert into  k_activiti_poc.task (task_name,sla,rev_id) values ('Income Calculator','20 min',2); 	
insert into  k_activiti_poc.task (task_name,sla,rev_id) values ('Paystubs','3 hrs',2); 	
insert into  k_activiti_poc.task (task_name,sla,rev_id) values ('W2','4 hrs',2); 	
insert into  k_activiti_poc.task (task_name,sla,rev_id) values ('Income Calculator','15 min',2);	
insert into  k_activiti_poc.task (task_name,sla,rev_id) values ('Paystubs','3 hrs',3);  
insert into  k_activiti_poc.task (task_name,sla,rev_id) values ('Bank Statements','8 hrs',3); 
insert into  k_activiti_poc.task (task_name,sla,rev_id) values ('Investment Accounts','3hrs 30 min',3);

CREATE TABLE IF NOT EXISTS K_ACTIVITI_POC.DEALSETUP (deal_id int(5) NOT NULL AUTO_INCREMENT, review_id varchar(50)NOT NULL, 
task_id varchar(20)NOT NULL, priority varchar(50)NOT NULL, PRIMARY KEY (`deal_id`) );
	
 

CREATE TABLE IF NOT EXISTS K_ACTIVITI_POC.SKILLS (skill_id int(5) NOT NULL AUTO_INCREMENT, skill_name varchar(255) NOT NULL, PRIMARY KEY (`skill_id`));

INSERT INTO K_ACTIVITI_POC.SKILLS (`skill_name`, `skill_id`) VALUES
('',1),
('VA', 2),
('FHA',3),
('Document Verification', 4),
('Jambo Loan', 5),
('Income Analysis(Salaried)', 6),
('Income Analysis(Self-Employed)', 7),
('Asset Verification', 8),
('Data Entry', 9); 

CREATE TABLE IF NOT EXISTS K_ACTIVITI_POC.USER_SKILL (usar_skill_id int(5) NOT NULL AUTO_INCREMENT, PRIMARY KEY (`usar_skill_id`),
user_id int, CONSTRAINT FK_USER FOREIGN KEY (user_id) REFERENCES K_ACTIVITI_POC.USER (user_id),
skill_id int, CONSTRAINT FK_SKILLS FOREIGN KEY (skill_id) REFERENCES K_ACTIVITI_POC.SKILLS (skill_id));

INSERT INTO K_ACTIVITI_POC.USER_SKILL (`user_id`, `skill_id`) VALUES
('2', '2'), 
('2', '3'),
('2', '6'),
('2', '7'),
('3', '2'), 
('3', '3'),
('3', '5'),
('4', '4'),
('5', '2'), 
('5', '3'),
('5', '5'),
('5', '6'),
('6', '4'),
('7', '7'),
('7', '9'),
('8', '8'),
('8', '9');


CREATE TABLE IF NOT EXISTS K_ACTIVITI_POC.TASK_SKILL (task_skill_id int NOT NULL AUTO_INCREMENT, PRIMARY KEY (task_skill_id),
task_id int, CONSTRAINT FK_TASK FOREIGN KEY (task_id) REFERENCES K_ACTIVITI_POC.TASK (task_id),
skill_id int, CONSTRAINT FK_SKILL FOREIGN KEY (skill_id) REFERENCES K_ACTIVITI_POC.SKILLS (skill_id));

INSERT INTO K_ACTIVITI_POC.TASK_SKILL (`task_id`, `skill_id`) VALUES
('1', '2'), 
('1', '3'), 
('1', '5'),
('2', '6'), 
('2', '2'),
('3', '4'), 
('4', '7'), 
('5', '2'), 
('5', '3'), 
('5', '5'),
('6', '2'),
('6', '6'),
('7', '9'),
('8', '2'), 
('8', '3'), 
('8', '5'),
('9', '8'), 
('10', '4');


===============================================================================================================================================


ALTER TABLE K_ACTIVITI_POC.DEALSETUP
ADD status varchar(200);

select * from K_ACTIVITI_POC.DEALSETUP;

UPDATE K_ACTIVITI_POC.DEALSETUP
SET status = 'Initiated' WHERE deal_id in (1,2,3,4,5,6,7,8......);

