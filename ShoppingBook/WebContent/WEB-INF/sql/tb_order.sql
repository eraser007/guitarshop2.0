  //������
  use lovo;
  create table tb_order
  (
    orderId int primary key auto_increment,
    name varchar(20),
    address varchar(20),
    postcode varchar(10),
    email varchar(20),
    orderDate timestamp,
    flag int 
  );
 //���Ϊ0 �����û���� ���Ϊ1 ���Ѿ����� ���Ϊ2 ������