show databases;
use zomato_db;
show tables;
select * from customer;
select * from address;
select* from restaurant;
select* from dish;
select* from restaurant_dishes;

INSERT INTO restaurant (id, contact, created_date, cuisine_type, email, name, rating, updated_date)
VALUES
    (1, '123-456-7890', NOW(), 'Italian', 'example@example.com', 'Olive Garden', 4.5, NOW()),
    (2, '987-654-3210', NOW(), 'Chinese', 'contact@chinarest.com', 'Chow Chow', 4.2, NOW()),
    (3, '555-555-5555', NOW(), 'Indian', 'info@indiacuisine.com', 'Taj Mahal', 4.7, NOW());
INSERT INTO dish (id, amount, created_date, name, updated_date, dish_type)
VALUES
    (1, 150, NOW(), 'Pasta Alfredo', NOW(), 'VEG'),
    (2, 170, NOW(), 'Pasta Arrabiata', NOW(), 'VEG'),
    (3, 80, NOW(), 'Spring Rolls', NOW(), 'NON_VEG'),
    (4, 110, NOW(), 'Garlic Noodles', NOW(), 'NON_VEG'),
    (5, 250, NOW(), 'Shahi Paneer', NOW(), 'VEG'),
    (6, 120, NOW(), 'Malayi Chaap', NOW(), 'VEG');
INSERT INTO restaurant_dish (restaurant_id, dish_id)
VALUES
     (1,1),(1,2),
     (2,3),(2,4),
     (3,5),(3,6);