-- Table for Gender
CREATE TABLE gender (
    gender_id SERIAL PRIMARY KEY,
    description VARCHAR(50) NOT NULL UNIQUE
);

-- Table for Employment Type
CREATE TABLE employment_type (
    employment_type_id SERIAL PRIMARY KEY,
    description VARCHAR(100) NOT NULL UNIQUE
);

-- Table for Age Groups
CREATE TABLE age_group (
    age_group_id SERIAL PRIMARY KEY,
    description VARCHAR(50) NOT NULL UNIQUE
);

-- Table for Period
-- The period is split into year and quarter (TI, TII, TIII, TIV)
CREATE TABLE period (
    period_id SERIAL PRIMARY KEY,
    year INTEGER NOT NULL,
    quarter VARCHAR(3) NOT NULL,
    UNIQUE (year, quarter)
);

-- Table for Employment Data (Fact Table)
CREATE TABLE employment_data (
    employment_data_id SERIAL PRIMARY KEY,
    gender_id INTEGER NOT NULL REFERENCES gender(gender_id),
    employment_type_id INTEGER NOT NULL REFERENCES employment_type(employment_type_id),
    age_group_id INTEGER NOT NULL REFERENCES age_group(age_group_id),
    period_id INTEGER NOT NULL REFERENCES period(period_id),
    total NUMERIC(10,2) NOT NULL
);
