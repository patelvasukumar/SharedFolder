/* Student Name: Vasukumar Patel */
-- Student Number: 041153940

create table customers(
	id INT primary key,
	name VARCHAR(45) NOT NULL,
	Email VARCHAR(45),
	Phone_Number VARCHAR(45),
	Street_Address VARCHAR(45),
	City VARCHAR(45),
	Province CHAR(2),
	Postal_Code CHAR(6)
);

create table orders(
	id INT primary key,
	order_date date,
	customer_id int,
	foreign key (customer_id) references customers(id) 
);

create table product_type(
	id int primary key,
	name varchar(45)
);

create table products(
	id int primary key,
	Name VARCHAR(45) not null,
	Description varchar(45) not null,
	price int,
	product_type_id int,
	foreign key (product_type_id) references product_type(id)
);  

create table order_line(
	order_id int,
	product_id int,
	quantity int,
	price decimal (10,2),
	line_total decimal (10,2),
	foreign key (product_id) references products(id),
	foreign key (order_id) references orders(id)
);

insert into customers(id,name,email,phone_number,street_address,city,province,postal_code)
values 
(010,'Vasukumar Patel', 'vasupatelworkmail@gmail.com', '222-000-1111' , 'baseline_road', 'ottawa', 'ON', 'k2c3k4'),
(020, 'Bhavya Gyani', 'bhavyagyaniworkmail@hotmail.com','111-222-0000','baseline_zeroroad','ottawa','ON','k2c3k1'),
(030, 'Sabit Patel', 'sabitworkmail@hotmail.com','111-555-0000','baseline_oneroad','ottawa','ON','k2c3k2'),
(040, 'Nibir Patel', 'nibirworkmail@workmail.com','111-666-0000','baseline_secondroad','ottawa','ON','k2c3D1'),
(050, 'Ravin Kumar', 'ravinworkmail@gmail.com','111-888-0000','baseline_thirdroad','ottawa','ON','k2v3k1');

select * from customers;

insert into product_type(id, name) values 
('100100','Software'),
('200200','Hardware'),
('300300','Service');

select * from product_type;

insert into products(id,name,description,price,product_type_id) values 
('101', 'Windows', 'it is an operating system', '100',(select id from product_type where name = 'Software')),
('202', 'Visual Studio', 'it is a code editor', '200',(select id from product_type where name = 'Service')),
('303', 'Mouse', 'it is a hardware component', '300',(select id from product_type where name = 'Hardware')),
('404', 'MC OS', 'it is a operating system from Apple', '400',(select id from product_type where name = 'Software')),
('505', 'Adobe Photoshop editor', 'it is a photo editor service', '500',(select id from product_type where name = 'Service')),
('606', 'Keyboard', 'it is a hardware component', '600',(select id from product_type where name = 'Hardware'));

select *
from products;

update customers
set phone_number = '999-997-4316'
where id = 010;

update customers
set postal_code = 'k2c3k5'
where id = 020;

select * from customers;

delete from products
where product_type_id = (select id from product_type where name = 'Software');

delete from product_type
where name = 'Software';

select * from products;