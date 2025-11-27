create table if not exists employee
(
    id        int auto_increment
    primary key,
    last_name varchar(50) null,
    email     varchar(50) null,
    gender    char        null,
    age       int         null
    );



INSERT INTO mp.employee (id, last_name, email, gender, age) VALUES (1, 'Tom', 'tom@atguigu.com', '1', 22);
INSERT INTO mp.employee (id, last_name, email, gender, age) VALUES (2, 'Jerry', 'jerry@atguigu.com', '0', 25);
INSERT INTO mp.employee (id, last_name, email, gender, age) VALUES (3, 'Black', 'black@atguigu.com', '1', 30);
INSERT INTO mp.employee (id, last_name, email, gender, age) VALUES (4, 'White', 'white@atguigu.com', '0', 35);
