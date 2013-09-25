package edu.sjsu.cmpe.library.domain;

import javax.validation.constraints.NotNull;

public class Review {
	@NotNull (message="Enter a valid rating")
	private int rating;
	@NotNull(message="Enter a valid Comment")
	private String comment;
	private int id;
	public Review(){

	}
	
	public int getId(){
		return id;
	}
	
	public void setId(int id)
	{
		this.id=id;
	}
	public int getRating(){
		return rating;
	}
	
	public void setRating(int rating)
	{
		this.rating=rating;
	}

	public String getComment(){
		return comment;
	}
	
	public void setComment(String comment)
	{
		this.comment=comment;
	}
	
	
}
