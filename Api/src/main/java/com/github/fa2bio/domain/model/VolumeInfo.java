package com.github.fa2bio.domain.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class VolumeInfo {
	
	@Column(name = "volume_title")
	private String title;
	
	@Column(name = "volume_published_date")
	private String publishedDate;
	
	@Column(name = "volume_description")
	private String description;
	
	@Column(name = "volume_language")
	private String language;
	
	@Column(name = "volume_page_count")
	private int pageCount;
	
	@Column(name = "volume_isbn_10")
	private String isbn_10;
	
	@Column(name = "volume_isbn_13")
	private String isbn_13;
	
	@Column(name = "volume_authors")
	@ElementCollection(targetClass=String.class)
	private List<String> authors;
}
