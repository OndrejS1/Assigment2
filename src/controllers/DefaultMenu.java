package controllers;

import java.util.List;

import asg.cliche.Command;
import asg.cliche.Param;
import models.Movie;
import models.User;

public class DefaultMenu {
	
	
	
	  private String name;
	  private User user;
	  private movieAPI movAPI;

	  public DefaultMenu(movieAPI movAPI, User user) {
	    this.movAPI = movAPI;
	    this.setName(user.firstName);
	    this.user = user;
	  }
	  @Command(description="Get a User")
	  public void getUser (@Param(name="User ID") Long id)
	  {
	    System.out.println("Name " + movAPI.getUser(id).firstName + movAPI.getUser(id).lastName);
	    System.out.println("Age: " + movAPI.getUser(id).age);
	    System.out.println("Occupation: " + movAPI.getUser(id).occupation);
	    System.out.println("ZipCode: " + movAPI.getUser(id).zipCode);
	    System.out.println("Gender: " + movAPI.getUser(id).gender);
	  }
	  
	  @Command(description="Initial Load from file")
	  public void initialLoad() throws Exception
	  {
		  movAPI.initialLoad();
		 
	  }
	    
	  
	  @Command(description="List of Users")
	  public void userList ()
	  {
		  System.out.println("Name           Age         Occupation       ZipCode      Gender");
		  for(User user : movAPI.UserList()) {
			  System.out.println(user.firstName + " " + user.lastName + "   " + user.age + "        " + user.occupation + "       " + user.zipCode + "     " + user.gender);
		  }
		  
	  }
	  
	  
	  @Command(description="Get a Movie")
	  public void getMovie (@Param(name="Movie ID") Long id)
	  {
	    System.out.println("Title " + movAPI.getMovie(id).title);
	    System.out.println("Year : " + movAPI.getMovie(id).year);
	    System.out.println("URL : " + movAPI.getMovie(id).url);
	    
	  }
	  
	  
	  
	  @Command(description="List of Users")
	  public void movieList ()
	  {
		  System.out.println("Title           Year        URL ");
		  for(Movie movie : movAPI.movieList()) {
			  System.out.println(movie.title + "   " + movie.year + "        " + movie.url);
		  }
		  
	  }
	  
	  
	  @Command(description="Movies by title")
	  public void sortedByTitle ()
	  {
		  System.out.println("Title           Year        URL ");
		  for(Movie movie : movAPI.sortedByTitle()) {
			  System.out.println(movie.title + "   " + movie.year + "        " + movie.url);
		  }
		  
	  }
	  
	  
	  @Command(description="Sort by Year")
	  public void sortedByYear ()
	  {
		  System.out.println("Title           Year        URL ");
		  for(Movie movie : movAPI.sortedByYear()) {
			  System.out.println(movie.title + "   " + movie.year + "        " + movie.url);
		  }
		  
	  }
	  @Command (description="Get me my recommendation")
		public void getUserRecommendations(@Param(name="movie ID")Long userID) {
			movAPI.getUserRecomendation(userID);
			
		}
	  
	
	  @Command (description="Get User's top movies")
		public void getUsersTop(@Param(name="user ID")Long userID) {
			movAPI.getTopUserMovies(userID);
		}
	  
	  @Command(description="Search Movie")
	  public void searchMovie (@Param(name="title") String substring)
	  {
		  
		 List<Movie> movies = movAPI.searchMovie(substring);
		 for (Movie movie : movies) {
			 System.out.println(movie.title + "  ----  " + movie.url );
			 
		 }
		 
	  }
	  
	  @Command (description="Top 10 most popular movies")
		public void MostPopularMovies() {
			movAPI.getTopTenMovies();
			
		}
	  
	  
	  
	  public String getName() {
	    return name;
	  }
	  public void setName(String name) {
	    this.name = name;
	  }

}
