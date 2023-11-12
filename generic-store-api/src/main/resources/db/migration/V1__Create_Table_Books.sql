CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE TABLE book (
    book_id uuid DEFAULT uuid_generate_v4 (),
    author TEXT,
    launch_date TIMESTAMPTZ NOT NULL,
    price NUMERIC(65, 2) NOT NULL,
    title TEXT,
    PRIMARY KEY (book_id)
);
--CREATE TABLE book (
--    id SERIAL PRIMARY KEY,
--    author TEXT,
--    launch_date TIMESTAMPTZ NOT NULL,
--    price NUMERIC(65, 2) NOT NULL,
--    title TEXT
--);
