select *
from airports;

-- '' with string
select name
from airports
-- where name = kos; -- does not work.
-- where name = 'kos'; -- capital matter. -- does not work
where name = 'Kos';

-- '' with number
select id
from airports
-- where id = 974; -- works
where id = '974'; -- works 

-- in with numbers
select id, name
from airports
-- where id in ('974','1458'); -- works
-- where id in (974, 1458); -- works
-- where name in (Beira); -- error
where name in ('Beira'); -- works

-- >= and <=
select name, elevation
from airports
-- where elevation <= '10'
-- and elevation >= '0';
where elevation <= 10
and elevation >= 0;			-- query works with or without single quotes.

-- with String 
select name 
from airports
-- where name <= 'Beira'		-- does not work, but does not show errors
-- and name >= 'Cobra Airport';
where name <= Beira				-- does not work and it show error
and name >= Cobra Airport;

-- between
select name, elevation
from airports
-- where elevation between 0 and 10; -- works
where elevation between '0' and '10'; -- works
-- it only works with numeric values. for string, it displays error.
-- if you use not operator before between, it includes only number before 0 and after 10.
 
-- wildcard symbols: _ (Underscore): unspecified character in a specific position 
--				     % (Percentage): any sequence of contiguous, unspecified characters (including spaces)

-- like operator 
select name
from airports
-- where name like Beira; -- does not work
-- where name like 'Beira';  -- works
where name like '%B'; -- olny includes characters which is before the B. After B, it omits.
-- %B% it means the characters before and after is goign to include.
-- it does not mean that it will not include cha. which are starting or ending with B.

-- select *
-- from sku_data
-- where sku like '%2%';
-- like always comes with single quotes for interger and string.


-- Underscore
select name
from airports
where name like 'B____';
-- there are many names in name coloums. like Bus, Beira. when you do B___ (three underscore)
-- it only includes Beira. it does not include Bus.

-- Not Null
select name
from airports
-- where name is null; -- works
where name is not null; -- works

-- SQL does not require that the column used in the WHERE 
-- clause also appear in the SELECT clause column list.

-- Aliases (As) to rename.
-- table syntax renaming the table
-- coloum syntax renaming the coloum

----------

-- SQL - not full fetured programming language.
-- SQL - data sub-language for creating and processing database data and metadata
-- SQL is everywhere in DBMS products in companies
-- Critical skill

-- SQL statements Five Catagories: 
-- 1. DDL (Data definition language) - creating tables, relationships, and other structures
-- 2. DML (Data Manipulation Language) - for queries, inserting, modifying and deleting
-- 3. SQL/Persistent Stored Modules (SQL/PSM)
-- 4. Transaction control language (TCL) 
-- 5. DCL (Data Control Language)

-- create table is used for creating table.
-- each coloum is described 3 parts: 1. name of the table
--									  2. data type
--									  3. constrain 
-- constrain can be define in the table or we can use ALTER to define a constrain. 
-- default keyword for default value

create table surat(
	kosmadi text(20),
	person text(50) default 'vasu',
	constraint Rulekosmadi check (text<=20),
	constraint kospk primary key(kosmadi) -- unique(value1,value2,....)
); 

/*
CREATE TABLE table_name (
    column_name data_type,
    CONSTRAINT constraint_name CHECK (condition)
);
*/

-- different for foreign key: FOREIGN KEY (customer_id) REFERENCES customers(customer_id) ON UPDATE NO ACTION

constraint string check(in('vasu','chaitanya','bhavya')),

constraint salesRage check((salesPrice>0) and (salesPrice<100))

-- SQL ALTER table statement is DDL -- affect on coloum and constraints

alter table student
add id int primary key;

alter table student
drop column id; 

-- same for constraints
alter table student
add constraint myconstraint check(price>=0);

alter table student
drop constraint myconstraint;

-- drop table is easy but it also drop all data
drop table student;

alter table student rename column student to students;

-- if we drop constraints which are dependent on one table. we just have to
-- drop that table. Not all tables.

truncate table student
-- truncate is not applicable when a table is refer by a foreign key
-- it also reset the surrogate key to initial value

-- index is used to enhance the data performance 
-- SQL CREATE, ALTER AND DROP INDEX Statement 

insert into student (LastName, ID, FirstName) values (Patel,01,Vasu);

-- more than one example
INSERT INTO students (student_id, first_name, last_name)
VALUES
    (2, 'Jane', 'Smith'),
    (3, 'Alice', 'Johnson'),
    (4, 'Bob', 'Williams');

-- Bulck Update
insert into student (LastName,ID,FirstName) select lastName,ID,FirstName from vasistha;

-- Update for only values, not for coloums like previous 
update student
set ID = 02
where LastName = 'Patel' and FirstName = 'Vasu';

-- bulck update
update student
set ID = 03 -- if there is more than two record, it will change all.

/*
-- SQL SUB-QUERY WITH THE UPDATE STATEMENT 

UPDATE PURCHASE_ORDER
SET TaxRate =
(SELECT Tax
FROM TAX_TABLE
WHERE TAX_TABLE.City = 'Bodega Bay’)
WHERE PURCHASE_ORDER.City = 'Bodega Bay';

*/

delete 
from student
where id=2;
-- does not reset surrogate key values , if do not write where, you will delete everything

-- Third conditional and aggregate functions

-- sort
order by -- fieldName
-- ascending
asc
-- descending 
desc

-- can sort multiple fied with the help of the comma.

-- Aggregate functions are functions that allow you to view a single piece
-- of data from multiple pieces of data.
-- Most functions operate on a single row or record.
-- Aggregate functions operate on multiple rows of data and return a
-- single value.
-- sum,count,min,max,avg

-- we can use distinct in the count to remove duplicate

-- having removes null values, it does not count.
-- Aggregate functions like COUNT(column_name), SUM, AVG, MAX, and MIN ignore NULL values.
-- count(*) , it includes null value.

-- The SQL WHERE clause specifies which rows will be used
-- to determine the groups.
-- The SQL HAVING clause specifies which groups will be
-- used in the final result.

-- When using the GROUP BY clause, any and all column names in the SELECT
-- clause that are not used by or associated with an SQL built-in function
-- must appear in the GROUP BY clause.

-- You cannot combine table column names with an SQL built-in function.
-- for example
SELECT Department, COUNT(*)
FROM SKU_DATA;

-- You cannot use them (table names) in an SQL WHERE clause because the
-- SQL WHERE clause operates on rows (choosing which rows will be
-- displayed), whereas the aggregate functions operate on columns.

-- CONCATE
SELECT SKU, SKU_Description, CONCAT(Buyer, ' in ', Department) AS Sponsor
FROM SKU_DATA
ORDER BY SKU;

-- RTRIM
/* *** SQL-Query *** */
SELECT SKU, SKU_Description, CONCAT(RTRIM(Buyer),'in',RTRIM(Department)) AS Sponsor
FROM SKU_DATA
ORDER BY SKU;

----------

-- fourth

-- subquery
select count(student) as student_number
from studenttable
where studentname in (select studentname from personal where id = 100);

-- Note that SQL queries using subqueries are still effectively single table queries 
-- in the sense that only  the columns of the top level query can be displayed
-- in the query results!

-- Data is stored in individual tables. It is the relationship
-- between the tables that make the data meaningful. JOINs
-- perform that meaningful relationship

-- -- natural join
-- creates a join based on the common attributes 
-- duplicate column omit
-- if common column not there, it becomes cross product (cartesian product)

select *
from table1 
natural join table2

-- cross

select *
from table1
cross join table2

-- an implicit join
-- rows matching the primary key values of one table with the foreign key values of a second table

-- Whenever the field name is the same in both tables, you need to add the
-- table name where the field is from to the SQL statement.

select *
from student, buses
where student.id = buses.id;

-- when table are joining through equal sign (=), it called equijoin
-- ❖SQL subqueries and SQL joins both process multiple tables.
-- ❖An SQL subquery can only be used to retrieve data from the
-- “top table.”
-- ❖An SQL join can be used to obtain data from any number of
-- tables, including the “top table” of the subquery.

select name as nam, department as dep
from person join company
on person.id = department.id
order by _____;

-- use full-stop (.) when coloum name is same in more than one table 
-- frist before join = primary key
-- second after join = foreign key 

select name as nam, department as dep
from person inner join company
on person.id = department.id
order by _____;
-- null omited
-- Non-matching records are not chosen.

select name as nam, department as dep
from person left/right outter join company
on person.id = department.id
order by _____;
-- returns all records from the left/right table and only the matching records from the right/left table.
-- When there are no matches from the right/left  table, it returns a NULL value. 
-- Unmatched data from either table returns as NULL.

-- SQL SET RULES:
-- 1. The table columns involved in the operations must be the same number in each SELECT component
-- 2. The corresponding columns must have the same or compatible (e.g., CHAR and VARCHAR) data types.

1. UNION
2. INTERSECT
3. EXCEPT 

select
from
union / intersect / except
select
from

-- View
create view first_view as 
select *
from student, buses
where student.id = buses.id;

-- already existed views (to update)
create or replace view first_view as 
select *
from student, buses
where student.id = buses.id;

-- drop view
drop view if exists first_view

-- 2 type: 1. Dynamic & 2. materialized 

-- dynamic == virtual table, logic view, derived table
-- it does not occupy space. Also no need to referesh 
-- any changes to table will affect the dynamic view

-- materialized == persistent view 
-- take storage 

create materialized view first_view as 
select *
from student, buses
where student.id = buses.id;

drop materialized view if exists first_view;

refresh materialized view first_view;

-- index
-- Most queries only require a small amount of information from a database
-- search time = tree depth + index length

-- unique and Nonunique index : first for unique and primary key
-- second for the normal attributes 

create index first_index on student(id,age);

-- Transactions 
BEGIN;
DML Statement 1
DML Statement 2
DML Statement 3
COMMIT or ROLLBACK;

-- transaction is that it bundles multiple steps into a single, all-or-nothing operation.
-- intermediate states between the steps are not visible
-- if some failure occurs that prevents the transaction from completing, 
-- then none of the steps affect the database at all
-- default : commit
-- characteristics : ACID

-- SAVEPOINT
-- a SAVEPOINT statement is used to creates a designation or mark using
-- an identifier inside a transaction.
-- Savepoint ensures that all statements performed after the savepoint can be rolled back.
-- Transactions are restored to the former state prior to the savepoint
-- when multiple savepoints are set with the same name, 
-- the newest savepoint is responsible for the rollback.