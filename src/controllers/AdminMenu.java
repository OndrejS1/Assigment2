package controllers;

import java.io.IOException;
import java.util.List;

import asg.cliche.Command;
import asg.cliche.Param;
import models.Movie;
import models.User;

public class AdminMenu {
	
	
	
	private String name;
	  private User user;
	  private movieAPI movAPI;

	  public AdminMenu(movieAPI movAPI, User user) {
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
	  
	  
	  
	  @Command(description="Delete User")
	  public void deleteUser (@Param(name="User ID") Long id)
	  {
		 movAPI.deleteUser(id);
		  
	  }
	  
	  
	  
	  @Command(description="List of Users")
	  public void userList ()
	  {
		  System.out.println("Name           Age         Occupation       ZipCode      Gender");
		  for(User user : movAPI.UserList()) {
			  System.out.println(user.firstName + " " + user.lastName + "   " + user.age + "        " + user.occupation + "       " + user.zipCode + "     " + user.gender);
		  }
		  
	  }
	  
	  
	  @Command(description="Search Movie")
	  public void searchMovie (@Param(name="title") String substring)
	  {
		  
		 List<Movie> movies = movAPI.searchMovie(substring);
		 for (Movie movie : movies) {
			 System.out.println(movie.title + "  ----  " + movie.url );
			 
		 }
		 
	  }
	  
	  
	  @Command(description="Add a new Movie")
	  public void addMovie (@Param(name="Title") String title, @Param(name="Year")  int year,
			  				@Param(name="URL")  String url)
	  						
	  {
	    movAPI.addMovie(title, url, year);
	    
	  }
	  
	  @Command(description="Get a Movie")
	  public void getMovie (@Param(name="Movie ID") Long id)
	  {
	    System.out.println("Title " + movAPI.getMovie(id).title);
	    System.out.println("Year : " + movAPI.getMovie(id).year);
	    System.out.println("URL : " + movAPI.getMovie(id).url);
	    
	  }
	  
	  @Command(description="Get a Rating")
	  public void getRating (@Param(name="User ID ") Long userID, @Param(name="Movie ID") Long movieID)
	  {
	    
		  movAPI.getRating(userID, movieID);
	    
	  }
	  
	  @Command(description="Initial Load from file")
	  public void initialLoad() throws Exception
	  {
		  movAPI.initialLoad();
		 
	  }
	  
	  
	  @Command(description="Delete User")
	  public void deleteMovie (@Param(name="Movie ID") Long id)
	  {
		 movAPI.deleteMovie(id);
		  
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
	  
	  
	  @Command(description="Add a new Rating")
	  public void addRating (@Param(name="user ID") Long userID, @Param(name="movie ID")  Long movieID,
			  				@Param(name="rating")  int rating)
	  						
	  {
	    movAPI.addRating(userID, movieID, rating);
	    
	  }
	  
	  @Command(description="Delete a movie Rating")
	  public void deleteRating (@Param(name="userID") Long userID, @Param(name="movieID")  Long movieID)
	  						
	  {
	    movAPI.removeRating(userID, movieID);
	    
	  }
	  
	  @Command (description="Users top movies")
		public void getUsersTop(@Param(name="user ID")Long userID) {
			movAPI.getTopUserMovies(userID);
		}
	  
	  
	  
	  
	  @Command (description="Get me my recommendation")
		public void getTopRatedMovies(@Param(name="movie ID")Long userID) {
			movAPI.getUserRecomendation(userID);
			
		}
	  
	  @Command (description="Average movie rating")
		public void getAverageMovieRating(@Param(name="movie ID")Long movieID) {
			System.out.println(movAPI.getAverageMovieRating(movieID));
			
		}
	  
	  
	  @Command (description="Top 10 most popular movies")
		public void MostPopularMovies() {
			movAPI.getTopTenMovies();
			
		}
	  
	  @Command (description="Logout")
		public void logout() {
			logout();
			
		}
	  
	  
	  public String getName() {
		    return name;
		  }
		  public void setName(String name) {
		    this.name = name;
		  }
	
	

}
