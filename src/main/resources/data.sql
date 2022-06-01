insert into roles( description)
VALUES ( 'Admin'),
       ( 'Manager'),
       ( 'Employee');




insert into users(id,username, password, role_id, is_enabled)
values ( 1,'Admin','$2a$10$nAB5j9G1c3JHgg7qzhiIXO7cqqr5oJ3LXRNQJKssDUwHXzDGUztNK',1, true);



insert into users(id,username, password, role_id, is_enabled)
values ( 2,'Manager','$2a$10$nAB5j9G1c3JHgg7qzhiIXO7cqqr5oJ3LXRNQJKssDUwHXzDGUztNK',2, true);
-- Abc1