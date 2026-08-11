ALTER TABLE tasks
ADD COLUMN creatorId BIGINT;

ALTER TABLE tasks
ADD CONSTRAINT fk_task_user
FOREIGN KEY (creatorId) REFERENCES users(Id);