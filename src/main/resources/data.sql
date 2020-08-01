DROP TABLE IF EXISTS order_tbl;
 
CREATE TABLE order_tbl (
  order_id INT AUTO_INCREMENT,
  customer_name VARCHAR(250) NOT NULL,
  shipping_address VARCHAR(250) NOT NULL,
  total_price INT NOT NULL,
  order_date date default sysdate,
  PRIMARY KEY(order_id)
  );
