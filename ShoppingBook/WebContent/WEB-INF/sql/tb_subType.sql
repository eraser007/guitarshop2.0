use shopping10_1;
create table tb_subType
  (
     subTypeId int primary key auto_increment,
     superTypeId int,
     subTypeName varchar(50),
     foreign key(superTypeId) references tb_superType(superTypeId)
  );
  
  insert into tb_subType values(null,1,'程序设计');
  insert into tb_subType values(null,1,'网络技术');