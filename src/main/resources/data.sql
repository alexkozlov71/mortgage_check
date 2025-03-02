CREATE TABLE RATE (
    maturity_period INT PRIMARY KEY,
    interest_rate DECIMAL(5, 2) NOT NULL,
    last_update TIMESTAMP NOT NULL
);
INSERT INTO RATE (maturity_period, interest_rate, last_update) VALUES (10, 3, CURRENT_TIMESTAMP);
INSERT INTO RATE (maturity_period, interest_rate, last_update) VALUES (15, 3.5, CURRENT_TIMESTAMP);
INSERT INTO RATE (maturity_period, interest_rate, last_update) VALUES (20, 5, CURRENT_TIMESTAMP);
INSERT INTO RATE (maturity_period, interest_rate, last_update) VALUES (30, 4.0, CURRENT_TIMESTAMP);
