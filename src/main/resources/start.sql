CREATE SCHEMA M4L10;

CREATE TABLE devices (
                         id INTEGER PRIMARY KEY AUTO_INCREMENT,
                         name TEXT,
                         installation_date DATE,
                         max_connections INTEGER
);


CREATE TABLE ports (
                       id INTEGER PRIMARY KEY AUTO_INCREMENT,
                       device_id INTEGER,
                       name TEXT,
                       bandwidth INTEGER
);


INSERT
INTO devices (name, installation_date, max_connections)
VALUES
    ('C0MF_CNTRL1', '2015-11-04', 2),
    ('C0MX_CNTRL23', '2014-01-11', 6),
    ('C0RU_CNTRL90', '2010-12-20', 5),
    ('C0AZ_CNTRL27', '2015-07-01', NULL),
    ('C0XX_CNTRL76', '2013-06-08', NULL),
    ('C0AZ_CNTRL99', '2015-07-20', 3),
    ('C0BM_CNTRL100', '2012-12-14', NULL);


INSERT INTO ports (device_id, name, bandwidth)
VALUES
    (1, 'Port 1', 2),
    (1, 'Port 2', 3),
    (2, 'Port x', 4);

SELECT * FROM devices;

DROP TABLE devices;
DROP TABLE ports;