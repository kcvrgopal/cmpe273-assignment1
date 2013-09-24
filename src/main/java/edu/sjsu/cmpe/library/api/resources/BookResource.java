package edu.sjsu.cmpe.library.api.resources;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//import com.fasterxml.jackson.annotation.JsonProperty;









import java.util.ArrayList;
//import java.util.Enumeration;
import java.util.HashMap;








//import com.yammer.dropwizard.jersey.params.LongParam;
import com.yammer.metrics.annotation.Timed;

import edu.sjsu.cmpe.library.domain.Author;
import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.domain.Review;
import edu.sjsu.cmpe.library.dto.AuthorDto;
//import edu.sjsu.cmpe.library.domain.Author;
import edu.sjsu.cmpe.library.dto.BookDto;
import edu.sjsu.cmpe.library.dto.LinkDto;
import edu.sjsu.cmpe.library.dto.LinksDto;
import edu.sjsu.cmpe.library.dto.ReviewDto;


@Path("/v1/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class BookResource {

	static int i=1,r=0,k=0; 
    private static HashMap<Integer,Book> map=new HashMap<Integer,Book>();

    public BookResource() {
    	
    }

    @GET
    @Path("/{isbn}")
    @Timed(name = "view-book")
    
    public Response viewBook(@PathParam("isbn") int isbn) {
    Book book = map.get(isbn);	
	BookDto bookResponse=new BookDto(book);

	bookResponse.addLink(new LinkDto("view-book", "/books/" + book.getIsbn(),"GET"));
	bookResponse.addLink(new LinkDto("update-book","/books/" + book.getIsbn(), "PUT"));
	bookResponse.addLink(new LinkDto("delete-book","/books/" + book.getIsbn(), "DELETE"));
	if(book.reviews.size()>0)
		bookResponse.addLink(new LinkDto("view-all-reviews","/books/" + isbn + "/reviews", "GET"));
	return Response.status(200).entity(bookResponse).build();
    }
    
    
    @POST
    @Timed(name = "create-book")
  
    
    public Response createBook(Book book) {


    book.setIsbn(i);
	map.put(new Integer(i), book);
	i++;

	
	LinksDto bookResponse = new LinksDto();
	
	bookResponse.addLink(new LinkDto("view-book", "/books/" + book.getIsbn(), "GET"));
	bookResponse.addLink(new LinkDto("insert-book","/books/" + book.getIsbn(), "POST"));
	bookResponse.addLink(new LinkDto("update-book","/books/" + book.getIsbn(), "PUT"));
	bookResponse.addLink(new LinkDto("delete-book","/books/" + book.getIsbn(), "DELETE"));
	return Response.status(201).entity(bookResponse).build();
    }
        
    
    @DELETE
    @Path("/{isbn}")
    @Timed(name = "delete-book")
    
    public Response deleteBook(@PathParam("isbn") int isbn){
    	map.remove(isbn);
    	
    	LinksDto bookResponse = new LinksDto();
    	
    	bookResponse.addLink(new LinkDto("create-book","/books/" , "POST"));
    	return Response.status(200).entity(bookResponse).build();
    	
    }
    
    
    @PUT
    @Path("/{isbn}")
    @Timed(name = "update-book")
    public Response updateBook(@PathParam("isbn") int isbn, @QueryParam("status") String status ) {
        try{
	
	    	Book book = map.get(isbn);
	    	if(!status.equalsIgnoreCase("available")||!status.equalsIgnoreCase("checked-out")||!status.equalsIgnoreCase("lost")||!status.equalsIgnoreCase("in-queue"))
	    	{
	    		throw new Exception("Wrong status");
	    	}
	    		
	    	book.setStatus(status);
	    	
	    	LinksDto bookResponse = new LinksDto();
	    	
	    	bookResponse.addLink(new LinkDto("view-book", "/books/" , "GET"));
	    	bookResponse.addLink(new LinkDto("create-book","/books/", "POST"));
	    	bookResponse.addLink(new LinkDto("update-book","/books/", "PUT"));
	    	bookResponse.addLink(new LinkDto("delete-book","/books/", "DELETE"));
	    	if(book.reviews.size()>0)
	    		bookResponse.addLink(new LinkDto("view-all-reviews","/books/" + isbn + "/reviews", "GET"));
	
	    	return Response.status(200).entity(bookResponse).build();
	        }
        catch(Exception ex){
        	
			return Response.ok("Check the status of book").build();

        }
    }
    
    
    @POST
    @Path("/{isbn}/reviews")
    @Timed(name = "create-review")
  
    
    public Response createReview(@PathParam("isbn") int isbn,Review review) {
    	
    	Book book = map.get(isbn);
    	ArrayList<Review> reviews=book.addReview(review);
    	book.setReview(reviews);
    	map.put(isbn,book);
    	LinksDto lk=new LinksDto();
    	lk.addLink(new LinkDto("view-review","/books/"+book.getIsbn()+"/reviews/"+book.reviews.size(),"GET"));
    	return Response.status(201).entity(lk).build();
    }
    
    
    @GET
    @Path("{isbn}/reviews/{id}")
    public Response viewReview(@PathParam("isbn") int isbn,@PathParam("id") int id){
		try{
	    	Book book=	map.get(isbn);

	    	Review rv=new Review();
	    	
	    	int k;
	    		for(k=1;k<=book.reviews.size();k++)
	    		{
	    			if(k==id)
	    			{
	    			rv= book.reviews.get(id-1);
	    			}
	    		}
	    		ReviewDto reviewResponse=new ReviewDto(rv);
		    	reviewResponse.addLink(new LinkDto("view-review", "/books/"+book.getIsbn()+"/reviews/"+rv.getId(),"GET"));
		    	return Response.status(200).entity(reviewResponse).build();
	    	    	
		}
		catch(Exception ex){
			return Response.ok("Check Id numbers ").build();
		}
    
    }
    
    
    @GET
    @Path("{isbn}/reviews")
    public Response viewReviewByIsbn( @PathParam("isbn") int isbn){
    	Book book=	map.get(isbn);

    	ArrayList<Review> rev=new ArrayList<Review>();

		int j;
		
			for(j=1;j<=book.reviews.size();j++)
			{
				rev.add(book.reviews.get(j-1));
			}
		return Response.status(200).entity(rev).build();
    	
    	
    
    }
    
    
    @GET
    @Path("{isbn}/authors/{id}")
    public Response viewBookAuthorById(@PathParam("isbn") int isbn, @PathParam("id") int id){
		
    	Book book=	map.get(isbn);

		Author as=new Author();
		int k;
		
			for(k=1;k<=book.authors.size();k++)
			{
				if(k==id)
				{					
				as= book.authors.get(id-1);
				}
			}
			AuthorDto authorResponse=new AuthorDto(as);
	    	authorResponse.addLink(new LinkDto("view-author", "/books/" + book.getIsbn()+"/authors/"+id,"GET"));
	    	return Response.status(200).entity(authorResponse).build();
    	
    }
    
    
    @GET
    @Path("{isbn}/authors")
    public Response viewBookAllAuthor(@PathParam("isbn") int isbn){
		
    	Book book=	map.get(isbn);

    ArrayList<Author> au=new ArrayList<Author>();
	int k;
	
		for(k=1;k<=book.authors.size();k++)
		{
			au.add(book.authors.get(k-1));
		
		}
  
		return Response.status(200).entity(au).build();
    }


}

