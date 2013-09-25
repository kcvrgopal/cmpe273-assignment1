package edu.sjsu.cmpe.library.domain;

import org.hibernate.validator.constraints.NotEmpty;

public class Author {
	static int i=1;
	private int Id;
    @NotEmpty (message="Enter a valid Author name")
	private String name;
    
    
    public Author(){       
    	
    	authId(i);
    	i++;
    }
   
    
    public int getId()
    {
    	return Id;
    	
    }
    
    public void authId(int i)
    {
    	this.Id=i;
    }
    
    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name=name;
    }

}
