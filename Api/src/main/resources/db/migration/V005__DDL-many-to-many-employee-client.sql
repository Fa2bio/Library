create table employee_client(
	employee_id bigint not null,
	client_id bigint not null,
	
	primary key (employee_id, client_id),
	constraint fk_employee_client_employee foreign key (employee_id) references employee (id),
	constraint fk_employee_client_client foreign key (client_id) references client (id)
) engine=InnoDB;