alter table client add constraint uk_client_cpf unique (cpf);
alter table employee add constraint uk_employee_cpf unique (cpf);