insert into USERS(id, username, password, role) values (01, 'joao@gmail.com', '$2a$12$dmlufQ0P1/jFk0dE/F2IN.riAaWDF/hRvMqr4j/O7onPxgEKDCfXy', 'ROLE_ADMIN');
insert into USERS(id, username, password, role) values (41, 'emyle@gmail.com', '$2a$12$dmlufQ0P1/jFk0dE/F2IN.riAaWDF/hRvMqr4j/O7onPxgEKDCfXy', 'ROLE_CLIENT');
insert into USERS(id, username, password, role) values (51, 'pedro@gmail.com', '$2a$12$dmlufQ0P1/jFk0dE/F2IN.riAaWDF/hRvMqr4j/O7onPxgEKDCfXy', 'ROLE_CLIENT');
insert into USERS(id, username, password, role) values (16, 'ana@gmail.com', '$2a$12$dmlufQ0P1/jFk0dE/F2IN.riAaWDF/hRvMqr4j/O7onPxgEKDCfXy', 'ROLE_CLIENT');
insert into USERS(id, username, password, role) values (17, 'tody@gmail.com', '$2a$12$dmlufQ0P1/jFk0dE/F2IN.riAaWDF/hRvMqr4j/O7onPxgEKDCfXy', 'ROLE_CLIENT');


insert into CLIENTS(id, name, cpf, id_user) values (10, 'ana Silva', '92340493064',16);
insert into CLIENTS(id, name, cpf, id_user) values (11, 'emyle Silva', '85552266005',41);

insert into VAGAS(id, codigo, status) values (1, '01-A', 'OCUPADA');
insert into VAGAS(id, codigo, status) values (2, '02-B', 'OCUPADA');
insert into VAGAS(id, codigo, status) values (3, '03-A', 'OCUPADA');
insert into VAGAS(id, codigo, status) values (4, '04-B', 'OCUPADA');
insert into VAGAS(id, codigo, status) values (5, '05-B', 'OCUPADA');

insert into clientes_tem_vagas(numero_recibo, placa, marca, modelo, cor,data_entrada, id_client, id_vaga)
values('231202-213012','PQY_2912','HONDA','CIVIC','AZUL','2023-12-02 21:30:00',10,1);
insert into clientes_tem_vagas(numero_recibo, placa, marca, modelo, cor,data_entrada, id_client, id_vaga)
values('231202-213312','PQY_4921','HONDA','CITY','PRATA','2023-12-02 21:30:00',11,2);
insert into clientes_tem_vagas(numero_recibo, placa, marca, modelo, cor,data_entrada, id_client, id_vaga)
values('231202-213415','PQY_2912','HONDA','CIVIC','AZUL','2023-12-02 21:30:00',10,3);


