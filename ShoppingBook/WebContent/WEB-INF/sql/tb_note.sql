
--�������԰���Ϣ��
create table tb_note 
(
  id int primary key auto_increment,
  title varchar(20) not null ,
  author varchar(20) not null ,
  content varchar(50) not null ,
  ly_time timestamp,
  imgs varchar(20)
); 

--��������Ա��¼/ע���
create table managerInfo
(
	id int(20) primary key auto_increment,
	name varchar(20),
	password varchar(20)
);
--������ͨ�û���¼/ע���
create table userInfo
(
	id  int(20) primary key auto_increment,
	name varchar(20) ,
	password varchar(20)
);

