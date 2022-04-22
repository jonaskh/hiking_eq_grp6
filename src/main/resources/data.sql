INSERT INTO users (email, password, enabled)
values ('user', 'user', true);

INSERT INTO users (email, password, enabled)
values ('admin', 'admin', true);


INSERT INTO authorities (email, authority)
values ('user', 'ROLE_USER');

INSERT INTO authorities (email, authority)
values ('admin', 'ROLE_ADMIN');