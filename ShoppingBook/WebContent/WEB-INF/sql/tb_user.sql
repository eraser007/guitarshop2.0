用户注册
create database lovo;
use lovo;
create table tb_user
(
	id int primary key auto_increment,       #编号
         name varchar(30),                                      #用户名 （form：name） （不得含有特殊字符及空格）
         password varchar(16),		                #密码（form：password） 不超过16位的字母和数字（不少于8位）
	email varchar(50)，			     #邮箱（form：email） （不得为空，为合法地址，包含@）
	trueName varchar(40),                                  #用户的真实姓名（form：trueName）
        sex varchar(30),
        birthday timestamp,
	address varchar(100),			    #用户真实收件地址（form：address） 
	postcode varchar(10),					    #邮政编码（form：postcode） 
	phone varchar(15)，					    #用户的固定电话（form：phone） 
	mphone varchar(15),				     #用户的手机号（form：mphone） 
        question varchar(30),
        answer varchar(30)
        
);

create table tb_user
(
	id int primary key auto_increment,
    name varchar(30),
    password varchar(16),
	email varchar(50),
	trueName varchar(40),
    sex varchar(30),
    birthday timestamp,
	address varchar(100),
	postcode varchar(10),
	phone varchar(15),
	mphone varchar(15),
    question varchar(30),
    answer varchar(30)
);