create table book_authors(
	volume_authors varchar(255),
	book_id bigint not null,
	
	constraint fk_book_authors foreign key (book_id) references book (id)
)engine=InnoDB;