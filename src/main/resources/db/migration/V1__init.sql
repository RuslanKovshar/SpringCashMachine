use cash_machine;

create table product_in_check
(
    id               bigint not null auto_increment,
    count_of_product integer,
    price            decimal(19, 2),
    check_id         bigint,
    product_id       bigint,
    primary key (id)
) engine = InnoDB;
create table products
(
    dtype varchar(31)    not null,
    id    bigint         not null auto_increment,
    code  integer        not null,
    name  varchar(255)   not null,
    price decimal(19, 2) not null,
    type  varchar(255),
    primary key (id)
) engine = InnoDB;
create table stock
(
    row_id           bigint not null auto_increment,
    count_of_product integer,
    product_id       bigint,
    primary key (row_id)
) engine = InnoDB;
create table user_role
(
    id   integer not null auto_increment,
    role varchar(255),
    primary key (id)
) engine = InnoDB;
create table users
(
    id                         bigint       not null auto_increment,
    cash                       decimal(19, 2),
    email                      varchar(255) not null,
    first_name_en              varchar(255) not null,
    first_name_ua              varchar(255) not null,
    is_account_non_expired     bit          not null,
    is_account_non_locked      bit          not null,
    is_credentials_non_expired bit          not null,
    is_enabled                 bit          not null,
    password                   varchar(255) not null,
    second_name_en             varchar(255) not null,
    second_name_ua             varchar(255) not null,
    primary key (id)
) engine = InnoDB;
create table users_authorities
(
    user_id bigint  not null,
    role_id integer not null,
    primary key (user_id, role_id)
) engine = InnoDB;
create table buyer
(
    id           bigint not null auto_increment,
    card_number  varchar(255),
    name_on_card varchar(255),
    primary key (id)
) engine = InnoDB;

create table checks
(
    id          bigint not null auto_increment,
    total_price decimal(19, 2),
    buyer_id    bigint,
    user_id     bigint,
    primary key (id)
) engine = InnoDB;

alter table products
    add constraint UK57ivhy5aj3qfmdvl6vxdfjs4p unique (code);
alter table products
    add constraint UKo61fmio5yukmmiqgnxf8pnavn unique (name);
alter table user_role
    add constraint UKs21d8k5lywjjc7inw14brj6ro unique (role);
alter table users
    add constraint UK6dotkott2kjsp8vw4d0m25fb7 unique (email);
alter table checks
    add constraint FK55jgk6t58ntqs2x09faae6n42 foreign key (buyer_id) references buyer (id);
alter table checks
    add constraint FK7tnskeyl2rtf8dyihpia7qjj9 foreign key (user_id) references users (id);
alter table product_in_check
    add constraint FKaa12j0ipbyafdohoe110670lx foreign key (check_id) references checks (id);
alter table product_in_check
    add constraint FKiroxkb3jgv2vi4ohk7rbs0b5b foreign key (product_id) references products (id);
alter table stock
    add constraint FKeuiihog7wq4cu7nvqu7jx57d2 foreign key (product_id) references products (id);
alter table users_authorities
    add constraint FK84cblw8weehv8c4s2cxvh1yda foreign key (role_id) references user_role (id);
alter table users_authorities
    add constraint FKq3lq694rr66e6kpo2h84ad92q foreign key (user_id) references users (id);