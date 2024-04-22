SET FOREIGN_KEY_CHECKS=0;
Drop table if exists user;
Drop table if exists `order`;
drop table if exists role;
drop table if exists user_roles;
drop table if exists order_detail;
drop table if exists brand;
drop table if exists category;
drop table if exists deliveryMethod;
drop table if exists status;
drop table if exists paymentMethod;
drop table if exists invoice;
drop table if exists size;
drop table if exists product;

CREATE TABLE brand (
                       id INTEGER AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(50) NOT NULL
);

CREATE TABLE category (
                          id INTEGER AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(50) NOT NULL
);

CREATE TABLE role (
                      id INTEGER AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(50) NOT NULL
);

CREATE TABLE user (
                      id INTEGER AUTO_INCREMENT PRIMARY KEY,
                      username VARCHAR(50) NOT NULL,
                      password VARCHAR(255) NOT NULL,
                      email VARCHAR(50) NOT NULL,
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                      edited_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
                  );

CREATE TABLE size (
                      id INTEGER AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(50) NOT NULL
);

CREATE TABLE deliveryMethod (
                                id INTEGER AUTO_INCREMENT PRIMARY KEY,
                                name VARCHAR(50) NOT NULL
);

CREATE TABLE paymentMethod (
                               id INTEGER AUTO_INCREMENT PRIMARY KEY,
                               name VARCHAR(50) NOT NULL
);

CREATE TABLE status (
                        id INTEGER AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(50) NOT NULL
);




CREATE TABLE product (
                         id INTEGER AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(50) NOT NULL,
                         price DECIMAL(10,2) NOT NULL,
                         description VARCHAR(5000) NOT NULL,
                         category_id INTEGER NOT NULL,
                         size_id INTEGER NOT NULL,
                         brand_id INTEGER NOT NULL,
                         quantity INTEGER NOT NULL,
                         production_year INTEGER NOT NULL,
                         fork VARCHAR(50) NOT NULL,
                         fork_material VARCHAR(50) NOT NULL,
                         frame_material VARCHAR(50) NOT NULL,
                         drive VARCHAR(50) NOT NULL,
                         front_derailleur VARCHAR(50) NOT NULL,
                         rear_derailleur VARCHAR(50) NOT NULL,
                         handle VARCHAR(50) NOT NULL,
                         crank VARCHAR(50) NOT NULL,
                         cassette VARCHAR(50) NOT NULL,
                         brake_type VARCHAR(50) NOT NULL,
                         brake VARCHAR(50) NOT NULL,
                         wheel_size VARCHAR(50) NOT NULL,
                         wheel VARCHAR(50) NOT NULL,
                         tire VARCHAR(50) NOT NULL,
                         pedals VARCHAR(50) NOT NULL,
                         saddle VARCHAR(50) NOT NULL,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                         updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL

);

CREATE TABLE `order` (
                       id INTEGER AUTO_INCREMENT PRIMARY KEY,
                       user_id INTEGER NOT NULL,
                       street VARCHAR(100) NOT NULL,
                       postalCode VARCHAR(8) NOT NULL,
                       city VARCHAR(30) NOT NULL,
                       phoneNumber VARCHAR(12) NOT NULL,
                       email VARCHAR(50) NOT NULL,
                       price DECIMAL(10, 2) NOT NULL,
                       created_at TIMESTAMP NOT NULL,
                       status_id INTEGER NOT NULL,
                       paymentMethod_id INTEGER NOT NULL,
                       deliveryMethod_id INTEGER NOT NULL,
                       FOREIGN KEY (user_id) REFERENCES user(id),
                       FOREIGN KEY (status_id) REFERENCES status(id),
                       FOREIGN KEY (paymentMethod_id) REFERENCES paymentMethod(id),
                       FOREIGN KEY (deliveryMethod_id) REFERENCES deliveryMethod(id)
);

CREATE TABLE order_detail (
                              id INTEGER AUTO_INCREMENT PRIMARY KEY,
                              order_id INTEGER NOT NULL,
                              product_id INTEGER NOT NULL,
                              quantity INTEGER NOT NULL,
                              unitPrice DECIMAL(10, 2) NOT NULL,
                              totalPrice DECIMAL(10, 2) NOT NULL,
                              FOREIGN KEY (order_id) REFERENCES `order`(id),
                              FOREIGN KEY (product_id) REFERENCES product(id)
);



CREATE TABLE invoice (
                         id INTEGER AUTO_INCREMENT PRIMARY KEY,
                         user_id INTEGER NOT NULL,
                         order_id INTEGER NOT NULL,
                         tax DECIMAL(10, 2) NOT NULL,
                         value DECIMAL(10, 2) NOT NULL,
                         paymentDate TIMESTAMP NOT NULL,
                         FOREIGN KEY (user_id) REFERENCES user(id),
                         FOREIGN KEY (order_id) REFERENCES `order`(id)
);

CREATE TABLE user_roles (
                            user_id INTEGER NOT NULL,
                            roles_id INTEGER NOT NULL,
                            PRIMARY KEY (user_id, roles_id),
                            FOREIGN KEY (user_id) REFERENCES user(id),
                            FOREIGN KEY (roles_id) REFERENCES role(id)
);


