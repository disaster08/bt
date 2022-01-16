insert into users (id, username, email, password) values (1, 'john', 'warton@gmail.com', '123456');
insert into users (id, username, email, password) values (2, 'mike', 'lanister@gmail.com', '123456');
insert into users (id, username, email, password) values (3, 'steve', 'reeves@gmail.com', '123456');
insert into users (id, username, email, password) values (4, 'admin', 'admin@gmail.com', '123456');

INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');
INSERT INTO roles(name) VALUES('ROLE_AGENT');
INSERT INTO roles(name) VALUES('ROLE_APPROVER');
INSERT INTO roles(name) VALUES('ROLE_MODERATOR');

--INSERT INTO USER_ROLES(user_id, role_id) VALUES(4, 1);
--INSERT INTO USER_ROLES(user_id, role_id) VALUES(4, 2);
--INSERT INTO USER_ROLES(user_id, role_id) VALUES(4, 3);
--INSERT INTO USER_ROLES(user_id, role_id) VALUES(1, 1);
--INSERT INTO USER_ROLES(user_id, role_id) VALUES(2, 2);


--SELECT * FROM USERS
--join
--user_roles on USERs.id = user_roles.user_id
--join
--roles on roles.id = user_roles.role_id

-- INSERT EMPLOYEES
--insert into employee (user_id, first_name, last_name, email, login) values (1, 'John', 'Warton', 'warton@gmail.com', 'jwatson');
--insert into employee (user_id, first_name, last_name, email, login) values (2, 'Mike', 'Lanister', 'lanister@gmail.com', 'mlanister');
--insert into employee (user_id, first_name, last_name, email, login) values (3, 'Steve', 'Reeves', 'Reeves@gmail.com', 'sreeves');
--insert into employee (employee_id, first_name, last_name, email) values (nextval('employee_seq'), 'Mike', 'Lanister', 'lanister@gmail.com');
--insert into employee (employee_id, first_name, last_name, email) values (nextval('employee_seq'), 'Steve', 'Reeves', 'Reeves@gmail.com');
--insert into employee (employee_id, first_name, last_name, email) values (nextval('employee_seq'), 'Ronald', 'Connor', 'connor@gmail.com');
--insert into employee (employee_id, first_name, last_name, email) values (nextval('employee_seq'), 'Jim', 'Salvator', 'Sal@gmail.com');
--insert into employee (employee_id, first_name, last_name, email) values (nextval('employee_seq'), 'Peter', 'Henley', 'henley@gmail.com');
--insert into employee (employee_id, first_name, last_name, email) values (nextval('employee_seq'), 'Richard', 'Carson', 'carson@gmail.com');
--insert into employee (employee_id, first_name, last_name, email) values (nextval('employee_seq'), 'Honor', 'Miles', 'miles@gmail.com');
--insert into employee (employee_id, first_name, last_name, email) values (nextval('employee_seq'), 'Tony', 'Roggers', 'roggers@gmail.com');