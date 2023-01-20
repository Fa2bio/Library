create table book(
	id bigint not null auto_increment,
	isbn varchar(13) not null,
	client_id bigint,
	uui_code varchar(255) not null,
	volume_title varchar(255) not null,
	volume_published_date varchar(255) not null,
	volume_description varchar(10000),
	volume_language varchar(255) not null,
	volume_page_count int not null,
	volume_isbn_10 varchar(10) not null,
	volume_isbn_13 varchar(13) not null,
	
	primary key (id),
	constraint uk_book_uui_code unique (uui_code),
	constraint fk_book_client foreign key (client_id) references client (id)
)engine=InnoDB;