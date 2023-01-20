set foreign_key_checks = 0;

delete from client;
delete from employee;
delete from employee_client;
delete from book;
delete from book_authors;

set foreign_key_checks = 1;

alter table employee_client auto_increment = 1;
alter table book auto_increment = 1;
alter table book_authors auto_increment = 1;

insert into client(
	id, cep, cpf, address_bairro, address_cep, address_complemento, address_localidade,
	address_logradouro, name, uui_code
)
values(1, '22220001', '437.177.880-19', 'Catete', '22041011',
'','Rio de Janeiro','Rua do Catete - de 197/198 ao fim', 'Anão Azul', '85debe05-c2d6-4566-b5cc-61feabadedc8');

insert into client(
	id, cep, cpf, address_bairro, address_cep, address_complemento, address_localidade,
	address_logradouro, name, uui_code
)
values(2, '21351050', '780.182.480-60', 'Madureira', '22041011',
'300, fundos','Rio de Janeiro','Estrada do Portela - até 279 - lado ímpar', 'Anão Amarelo', 'bc971d82-bb12-4a02-972f-e8eb39883eb0');

insert into employee(
	id, cep, cpf, address_bairro, address_cep, address_complemento, address_localidade,
	address_logradouro, name, uui_code
)
values(1, '22041011', '397.162.500-29', 'Copacabana', '22041011',
'','Rio de Janeiro', 'Rua Santa Clara - de 1 ao fim - lado ímpar', 'Anão Azul', '96685573-862a-4d25-8465-125ffac87c03');

insert into employee(
	id, cep, cpf, address_bairro, address_cep, address_complemento, address_localidade,
	address_logradouro, name, uui_code
)
values(2, '22041011', '427.655.130-74', 'Copacabana', '22041011',
'','Rio de Janeiro', 'Rua Santa Clara - de 1 ao fim - lado ímpar', 'Anão Amarelo', '599fef44-d30e-440b-93b8-83bef513c1e5');

insert into employee(
	id, cep, cpf, address_bairro, address_cep, address_complemento, address_localidade,
	address_logradouro, name, uui_code
)
values(3, '22041011', '517.364.180-09', 'Copacabana', '22041011',
'','Rio de Janeiro', 'Rua Santa Clara - de 1 ao fim - lado ímpar', 'Anão Verde', '869560ba-821a-4418-9691-3888db5c1f34');

insert into employee(
	id, cep, cpf, address_bairro, address_cep, address_complemento, address_localidade,
	address_logradouro, name, uui_code
)
values(4, '22410003', '737.629.800-20', 'Ipanema', '22410003',
'','Rio de Janeiro', 'Rua Visconde de Pirajá - de 333 ao fim - lado ímpar', 'Anão Laranja', '1e975918-f32d-45a0-83c3-049fde8586b4');

insert into employee(
	id, cep, cpf, address_bairro, address_cep, address_complemento, address_localidade,
	address_logradouro, name, uui_code
)
values(5, '22410003', '445.757.430-97', 'Ipanema', '22410003',
'','Rio de Janeiro', 'Rua Visconde de Pirajá - de 333 ao fim - lado ímpar', 'Anão Preto', 'c17737a5-ea0f-4f4a-a888-d86cf4ff8172');

insert into employee_client (employee_id, client_id) values (1,1), (1,2), (2,1);

insert into book (id, uui_code, isbn, volume_title, volume_published_date,volume_description, volume_language,volume_page_count,volume_isbn_10,volume_isbn_13, client_id) 
values (1, '54ccdbff-0275-47ea-88c1-7a986c4c663d', '8535914846','abc','2019','volume_description','pt-BR',100,'8535914846','8535914846000','1');

insert into book (id, uui_code, isbn, volume_title, volume_published_date,volume_description, volume_language,volume_page_count,volume_isbn_10,volume_isbn_13, client_id) 
values (2, '7cb11cea-a407-4fc2-81d3-6bcb2789c077', '8535909559','cde','2019','volume_description','pt-BR','100','8535909559','8535909559001','1');

insert into book (id, uui_code, isbn, volume_title, volume_published_date,volume_description, volume_language,volume_page_count,volume_isbn_10,volume_isbn_13, client_id) 
values (3, '49831ae3-1223-4ce0-b070-94178d08987b', '8535914846','efg','2019','volume_description','pt-BR','100','8572839046','8572839046000','2');

