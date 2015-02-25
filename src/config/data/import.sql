INSERT INTO ROLE VALUES (1, 'ROLE_ADMIN', 'Administrator role', '2014-01-01');
INSERT INTO ROLE VALUES (2, 'ROLE_MANAGER', 'Manager role', '2014-01-01');
INSERT INTO ROLE VALUES (3, 'ROLE_USER', 'User role', '2014-01-01');

-- Add master user, password is admin
INSERT INTO USER VALUES(1, 1, 1, 'admin', 'adminlast','ADDRESS', 'admin@localhost.com', 'Male', '1900-01-01','0000-000-000','Development', '8192892', 'd033e22ae348aeb5660fc2140aec35850c4da997', '2014-01-01', '2014-01-01','22', '2014-01-01');
-- Add a manager user, password is manager
INSERT INTO USER VALUES(2, 1, 2,  'manager', 'managerlast', 'ADDRESS', 'manager@localhost.com', 'Male','1900-01-01','0000-000-000','Development', '8192892', '1a8565a9dc72048ba03b4156be3e569f22771f23', '2014-01-01', '2014-01-01', '22', '2014-01-01');
-- Add a normal user, password is user
INSERT INTO USER VALUES(3, 1, 3, 'user', 'adminlast', 'ADDRESS', 'user@localhost.com', 'Male','1900-01-01','0000-000-000','Development', '8192892', '12dea96fec20593566ab75692c9949596833adc9', '2014-01-01', '2014-01-01', '22', '2014-01-01');

--Add master user to a admin role
INSERT INTO USER_ROLES VALUES(1, 1);
--Add manager user to manager role
INSERT INTO USER_ROLES VALUES(2, 2);
--Add normal user to user role
INSERT INTO USER_ROLES VALUES(3, 3);

--Skills
INSERT INTO SKILLS VALUES(1, 'Development basics', 'Commerce Fundamentals', 'Basic development knowledge.', '2014-01-01');
INSERT INTO SKILLS VALUES(2, 'Front end', 'Commerce Fundamentals', 'Knowledge on frontend development. Includes CSS, JS, HTML', '2014-01-01');
INSERT INTO SKILLS VALUES(3, 'Controller flow', 'Commerce Fundamentals', 'Controller command framework', '2014-01-01');
INSERT INTO SKILLS VALUES(4, 'Persistence', 'Commerce Fundamentals', 'Persistence framework', '2014-01-01');

INSERT INTO SKILLS VALUES(5, 'IBM HTTP server', 'Intermediates', 'IBM HTTP Server knowledge and its management', '2014-01-01');
INSERT INTO SKILLS VALUES(6, 'WebSphere Application Server', 'Intermediates', 'IBM WebSphere Application Server knowledge and its management', '2014-01-01');

INSERT INTO SKILLS VALUES(7, 'Performance Tuning', 'Advanced', 'Performance fine Tuning of application', '2014-01-01');

INSERT INTO SKILLS VALUES(8, 'Core Metrics', 'Others', 'Core metrics development and management', '2014-01-01');
INSERT INTO SKILLS VALUES(9, 'Amplience', 'Others', 'Amplience thirdparty development and management', '2014-01-01');
INSERT INTO SKILLS VALUES(10, 'Sterling', 'Others', 'Sterling Order Management system', '2014-01-01');

--user holidays
INSERT INTO USER_HOLIDAYS VALUES(1, 2014, 0, 0, 0, 0, 0, 0, 22, 0, 1);
INSERT INTO USER_HOLIDAYS VALUES(2, 2014, 0, 0, 0, 0, 0, 0, 22, 0, 2);
INSERT INTO USER_HOLIDAYS VALUES(3, 2014, 0, 0, 0, 0, 0, 0, 22, 0, 3);
