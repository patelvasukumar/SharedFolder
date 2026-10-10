-- PART-B
-- selecting all coloums from airports databse
select *  from airports;

-- selecting name with distinct from airport database. Also, we changed the name from name to airport_name
select distinct (name) as airport_name from airports;

-- selecting all coloums from countries table where we put a condition of id = 160
select * from countries where id = 160;

-- selecting name, city and country_id coloums from the airports database where the condition is that all city should be hamilton
select name, city, country_id from airports where city = 'Hamilton';

-- same like above query but making more condition which is that country_id should be 160
select name, city, country_id from airports where city = 'Hamilton' and country_id = 160;

-- selecting all coloums from airports table and making condition where we are just selecting two cities in the coloum.
select * from airports where city in ('Chicago', 'Boston');

-- selecting all coloums from airports and the condition is that elevation coloum should be in desc and it should be between 400 and 500.
select * from airports where elevation >= 400 and elevation <= 500 order by elevation desc;

-- PART-B

-- selecting all from airlines database where condition is country_id is null
select * from airlines where country_id is null;

-- selecting all from airlines where condition is that airlines name must start with can and we do not care what is behind it
select * from airlines where name like 'Can%' order by country_id;

-- selecting all from airlines table where condtion is that name must start with international and we do not care what is before and after the name
select * from airlines where name like '%International%';

-- selecting all from airlines table where condtion is that name must start with aviation and we do not care what is before the name
select * from airlines where name like '%Aviation';

-- selecting all from countries and airports tables where name is Canda and country_id =208 and elevation = 0
select * from countries where name = 'Canda';
select * from airports where country_id = 208 and elevation = 0;

-- selecting all from countries and airports tables where name is France and country_id =130 and elevation = 'Y'
select * from countries where name = 'France';
select * from airlines where country_id = 130 and active = 'Y';
