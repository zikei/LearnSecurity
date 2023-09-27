DROP DATABASE IF EXISTS xls;

create database xls;

use xls

create table Account(
  UserID int auto_increment,
  UserName varchar(50) unique,
  Password varchar(64),
  LastLogin date,
  primary key(UserID)
);

create table Practice(
  PracticeID int auto_increment,
  PracticeName varchar(100) unique,
  ProjectPath varchar(64),
  DBCreatePath varchar(64),
  primary key(PracticeID)
);

create table Instance(
  InstanceID int auto_increment,
  InstanceName varchar(100),
  DBPath varchar(64),
  UserID int,
  PracticeID int,
  primary key(InstanceID),
  foreign key(UserID) references Account(UserID) on update cascade on delete cascade,
  foreign key(PracticeID) references Practice(PracticeID) on update cascade on delete cascade
);

create table Lesson(
  LessonID int auto_increment,
  LessonName varchar(100) unique,
  LessonPath varchar(64),
  primary key(LessonID)
);

create table Completion(
  CompletionID int auto_increment,
  UserID int,
  LessonID int,
  CompletionDate date,
  primary key(CompletionID),
  foreign key(UserID) references Account(UserID) on update cascade on delete cascade,
  foreign key(LessonID) references Lesson(LessonID) on update cascade on delete cascade
);
