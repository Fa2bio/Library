alter table client drop uui_code;
alter table client add uui_code varchar(255) not null;
update client set uui_code = uuid();
alter table client add constraint uk_client_uui_code unique (uui_code);

alter table employee drop uui_code;
alter table employee add uui_code varchar(255) not null;
update employee set uui_code = uuid();
alter table employee add constraint uk_employee_uui_code unique (uui_code);