-- Table: _user_
INSERT INTO _user_ (email, password)
VALUES ('john.doe@example.com', 'password123'),
       ('jane.smith@example.com', 'securepassword');

-- Table: customers
INSERT INTO customers (name, email)
VALUES ('John Doe', 'john.doe@example.com'),
       ('Jane Smith', 'jane.smith@example.com');

-- Table: type_component
INSERT INTO type_component (name, unit)
VALUES ('Processor', 'GHz'),
       ('RAM', 'GB'),
       ('Storage', 'TB');

-- Table: repair_status
INSERT INTO repair_status (name)
VALUES ('Pending'),
       ('In Progress'),
       ('Completed');

-- Table: technician
INSERT INTO technician (name, email)
VALUES ('Tech 1', 'tech1@repairshop.com'),
       ('Tech 2', 'tech2@repairshop.com');

-- Table: brand_component
INSERT INTO brand_component (name)
VALUES ('Intel'),
       ('AMD'),
       ('Samsung');

-- Table: brand_laptop
INSERT INTO brand_laptop (name)
VALUES ('Dell'),
       ('HP'),
       ('Apple');

-- Table: laptop_type
INSERT INTO laptop_type (name)
VALUES ('Gaming'),
       ('Business'),
       ('Ultrabook');

-- Table: laptop
INSERT INTO laptop (model, serial_number, laptop_type_id, brand_laptop_id, customers_id)
VALUES ('XPS 15', 'SN12345', 1, 1, 1),
       ('MacBook Pro', 'SN67890', 3, 3, 2);

-- Table: component
INSERT INTO component (model, unite_price, capacity, brand_component_id, type_component_id)
VALUES ('Core i7-12700K', 300.00, 3.5, 1, 1),
       ('Ryzen 7 5800X', 200.00, 16, 2, 2),
       ('870 EVO', 150.00, 1, 3, 3);

-- Table: repair
INSERT INTO repair (filing_date, end_date, laptop_id, technician_id, repair_status_id)
VALUES ('2025-01-01 10:00:00', '2025-01-02 15:00:00', 1, 1, 3),
       ('2025-01-05 09:00:00', NULL, 2, 2, 2);

-- Table: repair_details
INSERT INTO repair_details (quantity, component_id, repair_id)
VALUES (1, 1, 1),
       (2, 2, 2);

-- Table: stock_movement
INSERT INTO stock_movement (quantity, is_enter, date_movement, component_id)
VALUES (10, TRUE, '2025-01-01 08:00:00', 1),
       (5, FALSE, '2025-01-02 12:00:00', 2);

-- Table: retour
INSERT INTO retour (retour_date, repair_id)
VALUES ('2025-01-03 10:00:00', 1);

-- Table: laptop_component
INSERT INTO laptop_component (laptop_id, component_id, quantity)
VALUES (1, 1, 1),
       (2, 2, 2);
