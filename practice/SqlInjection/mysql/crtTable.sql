DROP DATABASE IF EXISTS sqlInjection;

CREATE DATABASE sqlInjection;
use sqlInjection;

create table Account(
  UserID int auto_increment,
  UserName varchar(50) unique,
  Password varchar(64),
  email varchar(64),
  primary key(UserID)
);

INSERT INTO Account (UserName, Password, email) VALUES
('Admin', 'adminpass', 'admin@example.com'),
('user1', 'user1pass', 'user1@example.com');

CREATE TABLE bbs (
  id int auto_increment,
  content varchar(191),
  updated_at datetime,
  UserName varchar(50),
  primary key(id)
);

INSERT INTO bbs (content, updated_at, UserName) VALUES
('BBSを作成しました', NOW(),'Admin');
