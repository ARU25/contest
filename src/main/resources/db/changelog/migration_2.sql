--changeset dbadmin:2
CREATE TABLE result(
    id UUID PRIMARY KEY REFERENCES contestant(id),
    rank integer UNIQUE
);