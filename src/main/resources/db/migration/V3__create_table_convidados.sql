CREATE TABLE guests (
  id BIGINT PRIMARY KEY,
  taskId BIGINT,
  userId BIGINT,
  FOREIGN KEY (taskId) REFERENCES tasks(Id),
  FOREIGN KEY (userId) REFERENCES users(Id)
);