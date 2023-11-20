DROP DATABASE IF EXISTS xls;

create database xls;

use xls

-- ユーザ情報
create table Account(
  UserId int auto_increment,
  UserName varchar(50) unique,
  Password varchar(64),
  primary key(UserId)
);

-- 実習情報
create table Practice(
  PracticeId int auto_increment,
  PracticeName varchar(100) unique,
  DirPath varchar(64),
  primary key(PracticeId)
);

-- 講義情報
create table Lesson(
  LessonId int auto_increment,
  LessonName varchar(100) unique,
  LessonPath varchar(64),
  primary key(LessonId)
);

-- 実習講義依存関係
create table PracticeLesson(
  PracticeLessonId int auto_increment,
  LessonId int,
  PracticeId int,
  primary key(PracticeLessonId),
  foreign key(LessonId) references Lesson(LessonId) on update cascade on delete cascade,
  foreign key(PracticeId) references Practice(PracticeId) on update cascade on delete cascade
);

-- 修了講義情報
create table Completion(
  CompletionId int auto_increment,
  UserId int,
  LessonId int,
  CompletionDate date,
  primary key(CompletionId),
  foreign key(UserId) references Account(UserId) on update cascade on delete cascade,
  foreign key(LessonId) references Lesson(LessonId) on update cascade on delete cascade
);


-- 初期データ注入
-- 実習情報
insert into Practice (PracticeName,DirPath)
  values('SQLインジェクション','/SqlInjection');

-- 講義情報
insert into Lesson (LessonName, LessonPath)
  values('テスト','/test/test.md');
