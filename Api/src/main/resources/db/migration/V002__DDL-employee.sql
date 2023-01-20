create table employee (
	id bigint not null auto_increment,
	cep varchar(9) not null,
	cpf varchar(14) not null,
	address_bairro varchar(255),
	address_cep varchar(255),
	address_complemento varchar(255),
	address_localidade varchar(255),
	address_logradouro varchar(255),
	name varchar(255) not null,
	uui_code varchar(255),
	primary key (id)
) engine=InnoDB;