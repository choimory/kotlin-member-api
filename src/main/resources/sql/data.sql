insert into member (id, uuld, email, password, nickname, profile, created_at, modified_at, deleted_at)
values (0, 'UULD', 'choimory@naver.com', 'secret', 'choimory', 'introduce', '2023-04-08 20:53:52', null, null);

insert into member_authority(id, member_id, auth, created_at, modified_at, deleted_at)
values (0, 1, 'ADMIN', '2023-04-08 20:53:52', null, null);