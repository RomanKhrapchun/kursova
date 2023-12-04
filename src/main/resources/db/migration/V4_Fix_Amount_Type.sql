use kursova;
alter table order_details
modify column product_amount int not null;

alter table product
modify column product_amount int;
