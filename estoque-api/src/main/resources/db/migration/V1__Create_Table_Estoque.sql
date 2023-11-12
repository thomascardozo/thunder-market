CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE TABLE estoque (
    id SERIAL PRIMARY KEY,
    item_id UUID DEFAULT uuid_generate_v4(),
    quantity BIGINT
);

