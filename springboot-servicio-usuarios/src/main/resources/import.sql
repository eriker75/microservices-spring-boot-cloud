INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES ('smsdev1', '12345', 1, 'Jhony', 'Vidal', 'joliveira@sms-latam.com');
INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES ('smsdev2', '12345', 1, 'JV94848846', 'AD', 'smsdev2@sms-latam.com');
INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES ('admin', '12345', 1, 'Admin', 'Admin', 'admin@sms-latam.com');

INSERT INTO roles (nombre) VALUES ('ROLE_USE');
INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (1, 1);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2, 2);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2, 1);