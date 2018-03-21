--------------------------------------------------------
--  File created - понедельник-Май-20-2013   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Sequence CUSTOMERS_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  CUSTOMERS_SEQ  MINVALUE 1 MAXVALUE 99999999 INCREMENT BY 1 START WITH 41 CACHE 20 ;
--------------------------------------------------------
--  DDL for Sequence GOODS_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  GOODS_SEQ  MINVALUE 1 MAXVALUE 99999999 INCREMENT BY 1 START WITH 21 CACHE 20 ;
--------------------------------------------------------
--  DDL for Sequence INFLOWS_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  INFLOWS_SEQ  MINVALUE 1 MAXVALUE 99999999 INCREMENT BY 1 START WITH 41 CACHE 20;

--------------------------------------------------------
--  DDL for Sequence OUTFLOWS_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  OUTFLOWS_SEQ  MINVALUE 1 MAXVALUE 99999999 INCREMENT BY 1 START WITH 41 CACHE 20;

--------------------------------------------------------
--  DDL for Sequence PRODUCTS_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  PRODUCTS_SEQ  MINVALUE 1 MAXVALUE 99999999 INCREMENT BY 1 START WITH 41 CACHE 20 ;
--------------------------------------------------------
--  DDL for Sequence SHIPPERS_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  SHIPPERS_SEQ  MINVALUE 1 MAXVALUE 99999999 INCREMENT BY 1 START WITH 41 CACHE 20 ;
--------------------------------------------------------
--  DDL for Sequence SPECIFICATIONS_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  SPECIFICATIONS_SEQ  MINVALUE 1 MAXVALUE 99999999 INCREMENT BY 1 START WITH 41 CACHE 20;
--------------------------------------------------------
--  DDL for Sequence WAREHOUSES_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  WAREHOUSES_SEQ  MINVALUE 1 MAXVALUE 99999999 INCREMENT BY 1 START WITH 21 CACHE 20;
CREATE SEQUENCE warehouses_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 999999999999999999
  START 1
  CACHE 1;
ALTER TABLE customers_seq
  OWNER TO postgres;
--------------------------------------------------------
--  DDL for Table CUSTOMERS
--------------------------------------------------------



CREATE TABLE CUSTOMERS
   (	ID BIGINT, 
	NAME VARCHAR(45), 
	REPRESENTATIVE VARCHAR(45)
   )

CREATE TABLE GOODS 
   (	ID BIGINT, 
	NAME VARCHAR(45), 
	UNITS VARCHAR(20)
   ) 

CREATE TABLE INFLOWS 
   (	ID BIGINT, 
	PRODUCT BIGINT, 
	AMOUNT BIGINT, 
	PRICE BIGINT, 
	SHIPPER BIGINT, 
	WAREHOUSE BIGINT, 
	INFLOWDATE DATE
   )

  CREATE TABLE OUTFLOWS
   (	ID BIGINT, 
	CUSTOMER BIGINT, 
	OUTFLOWDATE DATE, 
	INFLOW BIGINT, 
	AMOUNT BIGINT
   )

CREATE TABLE PRODUCTS
   (	ID BIGINT, 
	NAME VARCHAR(45), 
	PRICE BIGINT DEFAULT 0
   )

CREATE TABLE SHIPPERS 
   (	ID BIGINT, 
	NAME VARCHAR(45), 
	REPRESENTATIVE VARCHAR(45)
   ) 

CREATE TABLE SPECIFICATIONS
   (	ID BIGINT, 
	PRODUCT BIGINT, 
	COMPONENT BIGINT, 
	PART BIGINT DEFAULT 0, 
	BOUGHT VARCHAR(1) DEFAULT 0
   )

CREATE TABLE WAREHOUSES 
   (	ID BIGINT, 
	NAME VARCHAR(45), 
	ADDRESS VARCHAR(128), 
	EMPLOYEES BIGINT DEFAULT NULL
   ) ;


Insert into CUSTOMERS (ID,NAME,REPRESENTATIVE) values (1,'Customer 1','Petrov K.O.');
Insert into CUSTOMERS (ID,NAME,REPRESENTATIVE) values (21,'Customer 2','Morozyuk K.P.');
Insert into CUSTOMERS (ID,NAME,REPRESENTATIVE) values (22,'Customer 3','Antonov U.M.');

Insert into GOODS (ID,NAME,UNITS) values (1,'Pencil','pcs');
Insert into GOODS (ID,NAME,UNITS) values (2,'Notepad','pcs');
Insert into GOODS (ID,NAME,UNITS) values (3,'Apple','kg');
Insert into GOODS (ID,NAME,UNITS) values (4,'Apple 4','kg');
Insert into GOODS (ID,NAME,UNITS) values (5,'Apple 5','kg');
Insert into GOODS (ID,NAME,UNITS) values (6,'Apple 6','kg');
Insert into GOODS (ID,NAME,UNITS) values (7,'Apple 7','kg');
Insert into GOODS (ID,NAME,UNITS) values (8,'Apple 8','kg');
Insert into GOODS (ID,NAME,UNITS) values (9,'Apple 9','kg');
Insert into GOODS (ID,NAME,UNITS) values (10,'Apple 10','kg');
Insert into GOODS (ID,NAME,UNITS) values (11,'Apple 11','kg');
Insert into GOODS (ID,NAME,UNITS) values (12,'Apple 12','kg');
Insert into GOODS (ID,NAME,UNITS) values (13,'Apple 13','kg');
Insert into GOODS (ID,NAME,UNITS) values (14,'Apple 14','kg');
Insert into GOODS (ID,NAME,UNITS) values (15,'Apple 15','kg');
Insert into GOODS (ID,NAME,UNITS) values (16,'Apple 16','kg');
Insert into GOODS (ID,NAME,UNITS) values (17,'Apple 17','kg');
Insert into GOODS (ID,NAME,UNITS) values (18,'Apple 18','kg');
Insert into GOODS (ID,NAME,UNITS) values (19,'Apple 19','kg');
Insert into GOODS (ID,NAME,UNITS) values (20,'Apple 20','kg');
Insert into GOODS (ID,NAME,UNITS) values (21,'Apple 21','kg');
Insert into GOODS (ID,NAME,UNITS) values (22,'Apple 22','kg');
Insert into GOODS (ID,NAME,UNITS) values (23,'Apple 23','kg');
Insert into GOODS (ID,NAME,UNITS) values (24,'Apple 24','kg');
Insert into GOODS (ID,NAME,UNITS) values (25,'Apple 25','kg');
Insert into GOODS (ID,NAME,UNITS) values (26,'Apple 26','kg');
Insert into GOODS (ID,NAME,UNITS) values (27,'Apple 27','kg');
Insert into GOODS (ID,NAME,UNITS) values (28,'Apple 28','kg');
Insert into GOODS (ID,NAME,UNITS) values (29,'Apple 29','kg');
Insert into GOODS (ID,NAME,UNITS) values (30,'Apple 30','kg');


Insert into INFLOWS (ID,PRODUCT,AMOUNT,PRICE,SHIPPER,WAREHOUSE,INFLOWDATE) values (1,1,50,45.95,1,1,NULL);
Insert into INFLOWS (ID,PRODUCT,AMOUNT,PRICE,SHIPPER,WAREHOUSE,INFLOWDATE) values (2,3,25,25.25,1,2,NULL);
Insert into INFLOWS (ID,PRODUCT,AMOUNT,PRICE,SHIPPER,WAREHOUSE,INFLOWDATE) values (21,2,17,5,22,3,NULL);
Insert into INFLOWS (ID,PRODUCT,AMOUNT,PRICE,SHIPPER,WAREHOUSE,INFLOWDATE) values (22,3,65,1.05,21,2,NULL);
Insert into INFLOWS (ID,PRODUCT,AMOUNT,PRICE,SHIPPER,WAREHOUSE,INFLOWDATE) values (23,3,56,1.03,21,3,NULL);

Insert into OUTFLOWS (ID,CUSTOMER,OUTFLOWDATE,INFLOW,AMOUNT) values (1,1,NULL,2,17);
Insert into OUTFLOWS (ID,CUSTOMER,OUTFLOWDATE,INFLOW,AMOUNT) values (2,1,NULL,2,5);
Insert into OUTFLOWS (ID,CUSTOMER,OUTFLOWDATE,INFLOW,AMOUNT) values (21,21,NULL,1,23);

Insert into PRODUCTS (ID,NAME,PRICE) values (1,'A',5);
Insert into PRODUCTS (ID,NAME,PRICE) values (2,'B',2);
Insert into PRODUCTS (ID,NAME,PRICE) values (3,'C',3);
Insert into PRODUCTS (ID,NAME,PRICE) values (4,'D',8);
Insert into PRODUCTS (ID,NAME,PRICE) values (5,'E',9);
Insert into PRODUCTS (ID,NAME,PRICE) values (6,'F',5);
Insert into PRODUCTS (ID,NAME,PRICE) values (7,'G',38);
Insert into PRODUCTS (ID,NAME,PRICE) values (8,'H',56);
Insert into PRODUCTS (ID,NAME,PRICE) values (9,'I',6);
Insert into PRODUCTS (ID,NAME,PRICE) values (21,'J',35);
Insert into PRODUCTS (ID,NAME,PRICE) values (22,'K',95);

Insert into SHIPPERS (ID,NAME,REPRESENTATIVE) values (1,'Shipper 1','Ivanov A.A.');
Insert into SHIPPERS (ID,NAME,REPRESENTATIVE) values (21,'Shipper 2','John Wayne');
Insert into SHIPPERS (ID,NAME,REPRESENTATIVE) values (22,'Shipper 3','Jack Sheppard');

Insert into SPECIFICATIONS (ID,PRODUCT,COMPONENT,PART,BOUGHT) values (1,1,2,0.18,'0');
Insert into SPECIFICATIONS (ID,PRODUCT,COMPONENT,PART,BOUGHT) values (2,1,3,0.05,'0');
Insert into SPECIFICATIONS (ID,PRODUCT,COMPONENT,PART,BOUGHT) values (3,1,4,0.23,'0');
Insert into SPECIFICATIONS (ID,PRODUCT,COMPONENT,PART,BOUGHT) values (4,2,5,0.19,'1');
Insert into SPECIFICATIONS (ID,PRODUCT,COMPONENT,PART,BOUGHT) values (5,2,6,0.17,'0');
Insert into SPECIFICATIONS (ID,PRODUCT,COMPONENT,PART,BOUGHT) values (6,6,7,0.31,'1');
Insert into SPECIFICATIONS (ID,PRODUCT,COMPONENT,PART,BOUGHT) values (7,6,8,0.29,'1');
Insert into SPECIFICATIONS (ID,PRODUCT,COMPONENT,PART,BOUGHT) values (8,1,9,0.25,'1');
Insert into SPECIFICATIONS (ID,PRODUCT,COMPONENT,PART,BOUGHT) values (9,9,3,0.19,'0');
Insert into SPECIFICATIONS (ID,PRODUCT,COMPONENT,PART,BOUGHT) values (21,1,8,0.18,'1');
Insert into SPECIFICATIONS (ID,PRODUCT,COMPONENT,PART,BOUGHT) values (24,1,1,0.58,'1');
Insert into SPECIFICATIONS (ID,PRODUCT,COMPONENT,PART,BOUGHT) values (25,2,1,0.89,'1');

Insert into WAREHOUSES (ID,NAME,ADDRESS,EMPLOYEES) values (1,'Warehouse 1','Donetsk, Artema, 25a',15);
Insert into WAREHOUSES (ID,NAME,ADDRESS,EMPLOYEES) values (2,'Warehouse 2','Donetsk, Universitetskaya, 15',10);
Insert into WAREHOUSES (ID,NAME,ADDRESS,EMPLOYEES) values (3,'Warehouse 3','Donetsk, Petrovskogo, 78b',13);

select nextval ('WAREHOUSES_SEQ')
select nextval ('WAREHOUSES_SEQ')
