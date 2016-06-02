  //订单表
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
 //如果为0 则货还没发出 如果为1 货已经发出 如果为2 货冻结