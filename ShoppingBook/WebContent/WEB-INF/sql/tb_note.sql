
--创建留言板信息表
create table tb_note 
(
  id int primary key auto_increment,
  title varchar(20) not null ,
  author varchar(20) not null ,
  content varchar(50) not null ,
  ly_time timestamp,
  imgs varchar(20)
); 

--创建管理员登录/注册表
create table managerInfo
(
	id int(20) primary key auto_increment,
	name varchar(20),
	password varchar(20)
);
--创建普通用户登录/注册表
create table userInfo
(
	id  int(20) primary key auto_increment,
	name varchar(20) ,
	password varchar(20)
);

