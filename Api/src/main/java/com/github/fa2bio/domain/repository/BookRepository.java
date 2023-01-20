package com.github.fa2bio.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.github.fa2bio.domain.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

	@Query("from Book where uui_code = :uui_code")	
	Optional<Book> findByCode(String uui_code);
}
