/*회원정보*/
create or replace table member
(
    id          bigint auto_increment,
    uuld        varchar(255)  not null,
    email       varchar(255)  not null,
    password    varchar(255)  not null,
    nickname    varchar(255)  not null,
    profile     text     null,
    created_at  datetime not null,
    modified_at datetime null,
    deleted_at  datetime null,
    constraint member_pk
        primary key (id)
) collate utf8mb4_bin;

create or replace unique index member_email_uindex
    on member (email);

create or replace unique index member_nickname_uindex
    on member (nickname);

create or replace unique index member_uuld_uindex
    on member (uuld);


/*회원 권한*/
create or replace table member_authority(
    id bigint not null auto_increment primary key,
    member_id bigint not null,
    auth varchar(255),
    created_at datetime not null,
    modified_at datetime,
    deleted_at datetime
) collate utf8mb4_bin;

/*회원 사진*/
create or replace  table member_image(
    id bigint not null auto_increment primary key,
    member_id bigint not null,
    type varchar(255) not null,
    original_file_name varchar(255),
    file_name text,
    file_path text,
    file_size bigint,
    thumbnail_file_name text,
    thumbnail_file_path text,
    thumbnail_file_size bigint
) collate utf8mb4_bin;