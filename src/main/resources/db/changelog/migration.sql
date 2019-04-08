--changeset dbadmin:1
CREATE TABLE contestant(
    id UUID PRIMARY KEY,
    name varchar(255),
    phone_no varchar(255),
    email varchar(255)
);
