insert into USERS(id, username, password, role) values (01, 'joao@gmail.com', '$2a$12$dmlufQ0P1/jFk0dE/F2IN.riAaWDF/hRvMqr4j/O7onPxgEKDCfXy', 'ROLE_ADMIN');
insert into USERS(id, username, password, role) values (41, 'emyle@gmail.com', '$2a$12$dmlufQ0P1/jFk0dE/F2IN.riAaWDF/hRvMqr4j/O7onPxgEKDCfXy', 'ROLE_CLIENT');
insert into USERS(id, username, password, role) values (51, 'pedro@gmail.com', '$2a$12$dmlufQ0P1/jFk0dE/F2IN.riAaWDF/hRvMqr4j/O7onPxgEKDCfXy', 'ROLE_ADMIN');
insert into USERS(id, username, password, role) values (16, 'ana@gmail.com', '$2a$12$dmlufQ0P1/jFk0dE/F2IN.riAaWDF/hRvMqr4j/O7onPxgEKDCfXy', 'ROLE_CLIENT');
insert into USERS(id, username, password, role) values (17, 'tody@gmail.com', '$2a$12$dmlufQ0P1/jFk0dE/F2IN.riAaWDF/hRvMqr4j/O7onPxgEKDCfXy', 'ROLE_CLIENT');


insert into CLIENTS(id, name, cpf, id_user) values (10, 'ana Silva', '92340493064',16);
insert into CLIENTS(id, name, cpf, id_user) values (11, 'emyle Silva', '85552266005',41);