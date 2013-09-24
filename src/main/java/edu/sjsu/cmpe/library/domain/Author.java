package edu.sjsu.cmpe.library.domain;

public class Author {
	private int Id=1;
    private String name;
    
    
    public Author(){
    }
    
    public int getId()
    {
    	return Id;
    }
    
    public void setId(int ac)
    {
    	this.Id=Id++;
    }
    
    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name=name;
    }
    

}
