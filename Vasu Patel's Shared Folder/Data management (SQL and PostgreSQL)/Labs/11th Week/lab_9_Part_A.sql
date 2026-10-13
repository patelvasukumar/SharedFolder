-- Vasukumar Patel
-- 041153940

-- Question-1
-- selecting three tables and three specific coloums and getting names for airports, city and countries.
select airports.name as Airports_Name, airports.city as City_Name, countries.name as Country_Name
from airports
join countries
on airports.country_id = countries.id;

-- Question-2
-- selecting the required tables and displaying the airports name, airlines ID and code share.
select airports.name as Name_Airport, routes.airline_id, routes.codeshare
from routes
join airports
on source_airport_id = airports.id
where airports.name like 'Lester B Pearson Intl';

-- Question-3
-- same as question - 2, but using distinct.
select distinct airports.name as Name_Airport, routes.airline_id, routes.codeshare
from routes
join airports
on source_airport_id = airports.id
where airports.name = 'Lester B Pearson Intl';

-- Question-4
-- displaying only the distinct names for the airport, airlines, and aircraft description from required tables.
select distinct airports.name as Airports_Name, airlines.name as Airlines_Name, aircrafts.description as description
from airports 
join routes on airports.id = routes.source_airport_id
join airlines on airlines.id = routes.airline_id
join aircrafts on aircrafts.id = airlines.id
where airports.name = 'Lester B Pearson Intl';

--Question-5(A)
--selecting tables  which have an elevation of 0.
select airports.name as Airport_Name, countries.name as Country_Name, airports.elevation as Airport_Elevation
from airports
join countries
on airports.country_id = countries.id
where countries.name = 'Canada' and airports.elevation = 0;

--Question-5(B)
--selecting table where all airlines in France which have an active status of 'y'.
select airlines.name as Airlines_Name, countries.name as Country_Name, airlines.active as airlines_active
from airlines
join countries
on airlines.country_id = countries.id
where countries.name = 'France' and airlines.active = 'Y';