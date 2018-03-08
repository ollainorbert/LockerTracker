insert into Users(username, password, created_at)
values('Ravi', 'pass1', '2018-02-28 10:00:00');

insert into Users(username, password, created_at)
values('Ragn', 'pass1', '2018-02-28 11:00:00');

insert into Users(username, password, created_at)
values('admin', 'admin', '2050-01-01 00:00:00');

insert into Lockers(rented, rented_by_user_id, created_at)
values('true', '3', '2018-02-28 09:00:00');

insert into Lockers(rented, rented_by_user_id, created_at)
values('true', '1', '2018-02-28 09:00:00');

insert into Lockers(rented, rented_by_user_id, created_at)
values('false', '0', '2018-02-28 09:00:00');

insert into Lockers(rented, rented_by_user_id, created_at)
values('false', '0', '2018-02-28 09:00:00');

insert into Lockerrentals(user_id, locker_id, created_at)
values('1', '1', '2018-02-28 09:00:00');

insert into roles(role, created_at)
values('USER', '2018-02-28 10:00:00');

insert into roles(role, created_at)
values('ADMIN', '2018-02-28 10:00:00');

insert into Users_roles(user_id, role_id)
values('1', '1');

insert into Users_roles(user_id, role_id)
values('2', '1');

insert into Users_roles(user_id, role_id)
values('3', '1');

insert into Users_roles(user_id, role_id)
values('3', '2');