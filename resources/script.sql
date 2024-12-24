# CREATE DATABASE SCHEMA

CREATE TABLE `USERS` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `email` varchar(255),
  `name` varchar(255),
  `password` varchar(255),
  `created_at` timestamp,
  `updated_at` timestamp
);

CREATE TABLE `RENTALS` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255),
  `surface` numeric,
  `price` numeric,
  `picture` varchar(255),
  `description` varchar(2000),
  `owner_id` integer NOT NULL,
  `created_at` timestamp,
  `updated_at` timestamp
);

CREATE TABLE `MESSAGES` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `rental_id` integer,
  `user_id` integer,
  `message` varchar(2000),
  `created_at` timestamp,
  `updated_at` timestamp
);

CREATE UNIQUE INDEX `USERS_index` ON `USERS` (`email`);

ALTER TABLE `RENTALS` ADD FOREIGN KEY (`owner_id`) REFERENCES `USERS` (`id`);

ALTER TABLE `MESSAGES` ADD FOREIGN KEY (`user_id`) REFERENCES `USERS` (`id`);

ALTER TABLE `MESSAGES` ADD FOREIGN KEY (`rental_id`) REFERENCES `RENTALS` (`id`);


# INSERT DATASET INTO DATABASE

INSERT INTO USERS (id, email, name, password, created_at, updated_at)
VALUES
    (1, 'user1@example.com', 'User One', 'password1', '2022-01-01 00:00:00', '2022-01-01 00:00:00'),
    (2, 'user2@example.com', 'User Two', 'password2', '2022-02-01 00:00:00', '2022-02-01 00:00:00'),
    (3, 'user3@example.com', 'User Three', 'password3', '2022-03-01 00:00:00', '2022-03-01 00:00:00');

INSERT INTO `RENTALS` (id, name, surface, price, picture, description, owner_id, created_at, updated_at)
VALUES
    (1, 'test house 1', 432, 300, 'https://blog.technavio.org/wp-content/uploads/2018/12/Online-House-Rental-Sites.jpg',
    'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam a lectus eleifend, varius massa ac, mollis tortor. Quisque ipsum nulla, faucibus ac metus a, eleifend efficitur augue. Integer vel pulvinar ipsum. Praesent mollis neque sed sagittis ultricies. Suspendisse congue ligula at justo molestie, eget cursus nulla tincidunt. Pellentesque elementum rhoncus arcu, viverra gravida turpis mattis in. Maecenas tempor elementum lorem vel ultricies. Nam tempus laoreet eros, et viverra libero tincidunt a. Nunc vel nisi vulputate, sodales massa eu, varius erat.',
    1, '2012-12-02 01:00:00', '2014-12-03 02:00:00'),

    (2, 'test house 2', 154, 200, 'https://blog.technavio.org/wp-content/uploads/2018/12/Online-House-Rental-Sites.jpg',
    'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam a lectus eleifend, varius massa ac, mollis tortor. Quisque ipsum nulla, faucibus ac metus a, eleifend efficitur augue. Integer vel pulvinar ipsum. Praesent mollis neque sed sagittis ultricies. Suspendisse congue ligula at justo molestie, eget cursus nulla tincidunt. Pellentesque elementum rhoncus arcu, viverra gravida turpis mattis in. Maecenas tempor elementum lorem vel ultricies. Nam tempus laoreet eros, et viverra libero tincidunt a. Nunc vel nisi vulputate, sodales massa eu, varius erat.',
    2, '2012-12-03 03:00:00', '2014-12-04 04:00:00'),

    (3, 'test house 3', 234, 100, 'https://blog.technavio.org/wp-content/uploads/2018/12/Online-House-Rental-Sites.jpg',
    'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam a lectus eleifend, varius massa ac, mollis tortor. Quisque ipsum nulla, faucibus ac metus a, eleifend efficitur augue. Integer vel pulvinar ipsum. Praesent mollis neque sed sagittis ultricies. Suspendisse congue ligula at justo molestie, eget cursus nulla tincidunt. Pellentesque elementum rhoncus arcu, viverra gravida turpis mattis in. Maecenas tempor elementum lorem vel ultricies. Nam tempus laoreet eros, et viverra libero tincidunt a. Nunc vel nisi vulputate, sodales massa eu, varius erat.',
    1, '2012-12-05 05:00:00', '2014-12-06 06:00:00');

INSERT INTO `MESSAGES` (id, rental_id, user_id, message, created_at, updated_at)
VALUES
    (1, 1, 2, 'Hi, is this rental available next week?', '2023-01-06 09:00:00', '2023-01-06 09:00:00'),
    (2, 1, 1, 'Yes, it is available. Let me know the dates you prefer.', '2023-01-06 09:10:00', '2023-01-06 09:10:00'),
    (3, 2, 3, 'Can I schedule a visit for this rental?', '2023-01-07 15:30:00', '2023-01-07 15:30:00'),
    (4, 2, 2, 'Sure, let me know your availability.', '2023-01-07 15:45:00', '2023-01-07 15:45:00'),
    (5, 3, 3, 'Is this property still on the market?', '2023-01-08 10:00:00', '2023-01-08 10:00:00'),
    (6, 3, 1, 'Yes, feel free to book it if you are interested.', '2023-01-08 10:15:00', '2023-01-08 10:15:00');
