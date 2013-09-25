package edu.sjsu.cmpe.library.domain;

import java.util.ArrayList;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Book {
    private int isbn;
    @NotNull(message="Please Enter a valid title") private String title;
    private String language;
    @JsonProperty ("num-pages")
    private int pages;
    @JsonProperty ("publication-date")
    @NotEmpty(message="Please Enter a valid publication date") private String date;
    public String status="available";
    static int rc=1;
    
    public ArrayList<Author> authors=new ArrayList<Author>();
    public ArrayList<Review> reviews=new ArrayList<Review>();
    
    
    public int getIsbn() {
	return isbn;
    }

    public void setIsbn(int i) {
	isbn = i;
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }
    
    public String getLanguage() {
    	return language;
        }

    public void setLanguage(String language) {
    	this.language = language;
        }
    
    public int getPages() {
    	return pages;
        }

    public void setPages(int pages) {
    	this.pages = pages;
        }

    public String getDate(){
    	return date;
    }
    
    public void setDate(String date){
    	this.date=date;
    }
    
    public String getStatus() {
    	return status;
        }

    public void setStatus(String status) {
    	this.status = status;
        }
    
    public ArrayList<Author> getAuthors() {
		return authors;
	}


	public void setAuthors(ArrayList<Author> authors) {
		this.authors = authors;
	}
	
	
	public ArrayList<Review> getReview() {
		return reviews;
	}

	
	public void setReview(ArrayList<Review> review) {
		  this.reviews=review;
	}
	

	public ArrayList<Review> addReview(Review review){
		
		review.setId(rc);
		if(reviews.size()==0)
		{
			reviews=new ArrayList<Review>();	
		}
		reviews.add(review);
		rc=rc+1;
		return reviews;
	}

}
