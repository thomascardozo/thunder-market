CREATE TABLE auction (
    id SERIAL PRIMARY KEY,
    product_name VARCHAR(255) NOT NULL,
    starting_price DECIMAL(19, 2) NOT NULL,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL
);

CREATE TABLE bid (
    id SERIAL PRIMARY KEY,
    amount DECIMAL(19, 2) NOT NULL,
    bidder_id BIGINT NOT NULL,
    auction_id BIGINT NOT NULL,
    FOREIGN KEY (auction_id) REFERENCES auction (id)
);

CREATE TABLE participant (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE auction_participant (
    auction_id BIGINT NOT NULL,
    participant_id BIGINT NOT NULL,
    PRIMARY KEY (auction_id, participant_id),
    FOREIGN KEY (auction_id) REFERENCES auction (id),
    FOREIGN KEY (participant_id) REFERENCES participant (id)
);


