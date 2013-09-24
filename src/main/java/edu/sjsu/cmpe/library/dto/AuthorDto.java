package edu.sjsu.cmpe.library.dto;
import java.util.ArrayList;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import edu.sjsu.cmpe.library.domain.Author;
@JsonPropertyOrder(alphabetic = true)

public class AuthorDto extends LinksDto {
	ArrayList<Author> authors=new ArrayList<Author>();
    private static int ac=1;
    public AuthorDto() {
		// TODO Auto-generated constructor stub
	}
	public AuthorDto(Author author) {
				super();
			    }

	public ArrayList<Author> getAuthor() {
		return authors;
	}

	public void setAuthor(ArrayList<Author> authors) {
		
		for(int i=1;i<=authors.size();i++)
		{
			authors.get(i-1).setId(ac);
			ac=ac+1;
		}
		this.authors = authors;
	}
}
