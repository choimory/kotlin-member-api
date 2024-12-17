insert into member (id, uuld, email, password, nickname, profile, created_at, modified_at, deleted_at)
values (1, 'UULD', 'choimory@naver.com', 'secret', 'choimory', 'introduce', '2023-04-08 20:53:52', null, null);

insert into member_authority(id, member_id, auth, created_at, modified_at, deleted_at)
values (1, 1, 'ADMIN', '2023-04-08 20:53:52', null, null);

insert into member_image(id, member_id, type, original_file_name, file_name, file_path, file_size, thumbnail_file_name, thumbnail_file_path, thumbnail_file_size, created_at, modified_at, deleted_at)
values (1, 1, 'PROFILE', 'ori_file_nm.jpg', 'uuld_date.jpg', '/foo/bar/path', 1231245124, 'thumb_uuld_date.jpg', '/thumb/foo/bar/path', 123123,'2023-04-08 20:53:52', null, null);
insert into member_image(id, member_id, type, original_file_name, file_name, file_path, file_size, thumbnail_file_name, thumbnail_file_path, thumbnail_file_size, created_at, modified_at, deleted_at)
values (2, 1, 'BACK_GROUND', 'ori_file_nm2.jpg', 'uuld_date2.jpg', '/foo/bar/path', 1231245124, 'thumb_uuld_date2.jpg', '/thumb/foo/bar/path', 123123,'2023-04-08 20:53:52', null, null);