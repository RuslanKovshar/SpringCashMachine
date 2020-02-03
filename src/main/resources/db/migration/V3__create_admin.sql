insert into users
values (1, 0, 'example@mail.com', 'Admin', 'Адміністратор', 1, 1, 1, 1,
        '$2a$08$H3uAJH9Bf/Hblzc4I6sqReVHCtM9.CK8Mk9MaiYElQTGMbVnxRDeu', 'admin', 'адмін');

insert into users_authorities
values (1, 1),
       (1, 2),
       (1, 3),
       (1, 4);