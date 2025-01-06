CREATE TABLE _user_
(
    user_id  SERIAL,
    email    VARCHAR(50)  NOT NULL,
    password VARCHAR(256) NOT NULL,
    PRIMARY KEY (user_id),
    UNIQUE (email)
);

CREATE TABLE customers
(
    customers_id SERIAL,
    name         VARCHAR(250) NOT NULL,
    email        VARCHAR(250) NOT NULL,
    PRIMARY KEY (customers_id)
);

CREATE TABLE laptop
(
    laptop_id     SERIAL,
    brand         VARCHAR(250) NOT NULL,
    model         VARCHAR(250) NOT NULL,
    serial_number VARCHAR(250) NOT NULL,
    customers_id  INTEGER,
    PRIMARY KEY (laptop_id),
    UNIQUE (serial_number),
    FOREIGN KEY (customers_id) REFERENCES customers (customers_id)
);

CREATE TABLE type_component
(
    type_component_id SERIAL,
    name              VARCHAR(250) NOT NULL,
    unit              VARCHAR(50)  NOT NULL,
    PRIMARY KEY (type_component_id)
);

CREATE TABLE repair_status
(
    repair_status_id SERIAL,
    name             VARCHAR(250) NOT NULL,
    PRIMARY KEY (repair_status_id)
);

CREATE TABLE technician
(
    technician_id SERIAL,
    name          VARCHAR(250) NOT NULL,
    email         VARCHAR(250) NOT NULL,
    PRIMARY KEY (technician_id)
);

CREATE TABLE component
(
    componenr_id      SERIAL,
    brand             VARCHAR(250)   NOT NULL,
    unite_price       NUMERIC(15, 2) NOT NULL,
    capacity          NUMERIC(15, 2),
    type_component_id INTEGER        NOT NULL,
    PRIMARY KEY (componenr_id),
    FOREIGN KEY (type_component_id) REFERENCES type_component (type_component_id)
);

CREATE TABLE repair
(
    repair_id        SERIAL,
    filing_date      TIMESTAMP NOT NULL,
    end_date         TIMESTAMP,
    laptop_id        INTEGER   NOT NULL,
    technician_id    INTEGER   NOT NULL,
    repair_status_id INTEGER   NOT NULL,
    PRIMARY KEY (repair_id),
    UNIQUE (laptop_id),
    FOREIGN KEY (laptop_id) REFERENCES laptop (laptop_id),
    FOREIGN KEY (technician_id) REFERENCES technician (technician_id),
    FOREIGN KEY (repair_status_id) REFERENCES repair_status (repair_status_id)
);

CREATE TABLE repair_details
(
    repaire_details_id SERIAL,
    quantity           INTEGER NOT NULL,
    componenr_id       INTEGER NOT NULL,
    repair_id          INTEGER NOT NULL,
    PRIMARY KEY (repaire_details_id),
    FOREIGN KEY (componenr_id) REFERENCES component (componenr_id),
    FOREIGN KEY (repair_id) REFERENCES repair (repair_id)
);

CREATE TABLE stock_movement
(
    stock_movement_id SERIAL,
    quantity          NUMERIC(15, 2) NOT NULL,
    is_enter          BOOLEAN        NOT NULL,
    date_movement     TIMESTAMP      NOT NULL,
    componenr_id      INTEGER        NOT NULL,
    PRIMARY KEY (stock_movement_id),
    FOREIGN KEY (componenr_id) REFERENCES component (componenr_id)
);

CREATE TABLE laptop_component
(
    laptop_id    INTEGER,
    componenr_id INTEGER,
    quantity     INTEGER NOT NULL,
    PRIMARY KEY (laptop_id, componenr_id),
    FOREIGN KEY (laptop_id) REFERENCES laptop (laptop_id),
    FOREIGN KEY (componenr_id) REFERENCES component (componenr_id)
);
