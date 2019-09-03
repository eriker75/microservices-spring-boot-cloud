INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES ('smsdev1', '$2a$10$n8vTQ3OJpuN9aNkfaL3DS.CWI8ww0ypgl5qR0OKUUdbK3.TyYBr0q', 1, 'Jhony', 'Vidal', 'joliveira@sms-latam.com');
INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES ('smsdev2', '$2a$10$mqVkLqkq0UXXONLRwd3VJ.QfvhvJzM7vLfI3o.3aWA3kPU/NnhtUK', 1, 'JV94848846', 'AD', 'smsdev2@sms-latam.com');
INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES ('admin', '$2a$10$Q0KuCMLGKHe.0zm9GpCsu.HYZT23Wo4B9kqxElWEmjZQJRh7S.o0W', 1, 'Admin', 'Admin', 'admin@sms-latam.com');

INSERT INTO roles (nombre) VALUES ('ROLE_USE');
INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (1, 1);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2, 2);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2, 1);