CREATE TABLE _user_(
   user_id SERIAL,
   email VARCHAR(50)  NOT NULL,
   password VARCHAR(256)  NOT NULL,
   PRIMARY KEY(user_id),
   UNIQUE(email)
);

CREATE TABLE customers(
   customers_id SERIAL,
   name VARCHAR(250)  NOT NULL,
   email VARCHAR(250)  NOT NULL,
   PRIMARY KEY(customers_id)
);

CREATE TABLE type_component(
   type_component_id SERIAL,
   name VARCHAR(250)  NOT NULL,
   unit VARCHAR(50)  NOT NULL,
   PRIMARY KEY(type_component_id)
);

CREATE TABLE repair_status(
   repair_status_id SERIAL,
   name VARCHAR(250)  NOT NULL,
   PRIMARY KEY(repair_status_id)
);

CREATE TABLE technician(
   technician_id SERIAL,
   name VARCHAR(250)  NOT NULL,
   email VARCHAR(250)  NOT NULL,
   PRIMARY KEY(technician_id)
);

CREATE TABLE brand_component(
   brand_component_id SERIAL,
   name VARCHAR(250)  NOT NULL,
   PRIMARY KEY(brand_component_id)
);

CREATE TABLE brand_laptop(
   brand_laptop_id SERIAL,
   name VARCHAR(250)  NOT NULL,
   PRIMARY KEY(brand_laptop_id)
);

CREATE TABLE laptop_type(
   laptop_type_id SERIAL,
   name VARCHAR(50)  NOT NULL,
   PRIMARY KEY(laptop_type_id)
);

CREATE TABLE repair_type(
   repair_type_id SERIAL,
   name VARCHAR(250)  NOT NULL,
   PRIMARY KEY(repair_type_id)
);

CREATE TABLE laptop(
   laptop_id SERIAL,
   model VARCHAR(250)  NOT NULL,
   serial_number VARCHAR(250)  NOT NULL,
   laptop_type_id INTEGER NOT NULL,
   brand_laptop_id INTEGER NOT NULL,
   customers_id INTEGER,
   PRIMARY KEY(laptop_id),
   UNIQUE(serial_number),
   FOREIGN KEY(laptop_type_id) REFERENCES laptop_type(laptop_type_id),
   FOREIGN KEY(brand_laptop_id) REFERENCES brand_laptop(brand_laptop_id),
   FOREIGN KEY(customers_id) REFERENCES customers(customers_id)
);

CREATE TABLE component(
   component_id SERIAL,
   unite_price NUMERIC(15,2)   NOT NULL,
   capacity NUMERIC(15,2)  ,
   brand_component_id INTEGER NOT NULL,
   type_component_id INTEGER NOT NULL,
   PRIMARY KEY(component_id),
   FOREIGN KEY(brand_component_id) REFERENCES brand_component(brand_component_id),
   FOREIGN KEY(type_component_id) REFERENCES type_component(type_component_id)
);

CREATE TABLE repair(
   repair_id SERIAL,
   filing_date TIMESTAMP NOT NULL,
   end_date TIMESTAMP,
   total NUMERIC(15,2)  ,
   repair_type_id INTEGER NOT NULL,
   laptop_id INTEGER NOT NULL,
   technician_id INTEGER NOT NULL,
   repair_status_id INTEGER NOT NULL,
   PRIMARY KEY(repair_id),
   UNIQUE(laptop_id),
   FOREIGN KEY(repair_type_id) REFERENCES repair_type(repair_type_id),
   FOREIGN KEY(laptop_id) REFERENCES laptop(laptop_id),
   FOREIGN KEY(technician_id) REFERENCES technician(technician_id),
   FOREIGN KEY(repair_status_id) REFERENCES repair_status(repair_status_id)
);

CREATE TABLE repair_details(
   repaire_details_id SERIAL,
   quantity INTEGER NOT NULL,
   component_id INTEGER NOT NULL,
   repair_id INTEGER NOT NULL,
   PRIMARY KEY(repaire_details_id),
   FOREIGN KEY(component_id) REFERENCES component(component_id),
   FOREIGN KEY(repair_id) REFERENCES repair(repair_id)
);

CREATE TABLE stock_movement(
   stock_movement_id SERIAL,
   quantity NUMERIC(15,2)   NOT NULL,
   is_enter BOOLEAN NOT NULL,
   date_movement TIMESTAMP NOT NULL,
   component_id INTEGER NOT NULL,
   PRIMARY KEY(stock_movement_id),
   FOREIGN KEY(component_id) REFERENCES component(component_id)
);

CREATE TABLE retour(
   retour_id SERIAL,
   retour_date TIMESTAMP NOT NULL,
   repair_id INTEGER NOT NULL,
   PRIMARY KEY(retour_id),
   FOREIGN KEY(repair_id) REFERENCES repair(repair_id)
);

CREATE TABLE composant_recommande(
   id SERIAL,
   date_recommande DATE NOT NULL,
   component_id INTEGER NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(component_id) REFERENCES component(component_id)
);

CREATE TABLE laptop_component(
   laptop_id INTEGER,
   component_id INTEGER,
   quantity INTEGER NOT NULL,
   PRIMARY KEY(laptop_id, component_id),
   FOREIGN KEY(laptop_id) REFERENCES laptop(laptop_id),
   FOREIGN KEY(component_id) REFERENCES component(component_id)
);
