package edu.sjsu.cmpe.library.dto;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import edu.sjsu.cmpe.library.domain.Author;
@JsonPropertyOrder(alphabetic = true)

public class AuthorDto extends LinksDto {
	ArrayList<Author> authors=new ArrayList<Author>();
	private Author author;
    public AuthorDto(Author author) {
		super();
		this.author=author;
	}

	public ArrayList<Author> getAuthor() {
		return authors;
	}

}
