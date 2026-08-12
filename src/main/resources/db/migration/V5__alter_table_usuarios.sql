ALTER TABLE `curso-database`.tasks DROP FOREIGN KEY fk_task_user;
ALTER TABLE `curso-database`.guests DROP FOREIGN KEY guests_ibfk_2;


ALTER TABLE `curso-database`.users MODIFY COLUMN id bigint auto_increment NOT NULL;

ALTER TABLE `curso-database`.tasks ADD CONSTRAINT fk_task_user FOREIGN KEY (creatorId) REFERENCES users(id) ON DELETE CASCADE;
ALTER TABLE `curso-database`.guests ADD CONSTRAINT guests_ibfk_2 FOREIGN KEY (userId) REFERENCES users(id) ON DELETE CASCADE;
