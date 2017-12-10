package controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.Scanner;

import com.google.common.base.Optional;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import models.Movie;
import models.Rating;
import models.User;
import utils.Serializer;


public class movieAPI implements InterfaceMethods.MovieApIInterface
{
	
  private Serializer serializer;
  private Map<Long, User> userMap = new HashMap<>();
  private Map<Long, Movie>   movieMap = new HashMap<>();
  private Table<Long, Long, Integer> movieRatingTable = HashBasedTable.create();
  
  Optional<User> currentUser;
  
  
//simplified login method
 public boolean login(Long UserID, String password) {
   Optional<User> user = Optional.fromNullable(userMap.get(UserID));
   if (user.isPresent() && user.get().password.equals(password)) {
     currentUser = user;
     System.out.println(currentUser.get().firstName +  " has logged in...");
     return true;
   }
   return false;
 }
 
//simplified and generalized version of my logout method
public void logout() {
  Optional<User> user = currentUser;
  if (user.isPresent()) {
	  System.out.println(currentUser.get().firstName +  " has logged out...");
    currentUser = Optional.absent();
  }
}
 
 



  public movieAPI()
  {}

  
  public movieAPI(Serializer serializer)
  {
    this.serializer = serializer;
  }
  
  @SuppressWarnings("unchecked")
  public void load() throws Exception
  {

    serializer.read();
    
    userMap = (Map<Long, User>) serializer.pop();
    movieMap = (Map<Long, Movie>)   serializer.pop();
    movieRatingTable = (Table<Long, Long, Integer>) serializer.pop();
    
  }
  
  public void write() throws Exception
  {
    serializer.push(userMap);
    serializer.push(movieMap);
    serializer.push(movieRatingTable);
    serializer.write(); 
  }
  
  
  
  
  // Functions related to User
  
  public User addUser (String firstName, String lastName, int age, String gender, String occupation, int zipCode, String role) 
  {
	
    User user = new User (firstName, lastName, age, gender, occupation, zipCode,role);
    userMap.put(user.id, user);
    return user;
    
  }

  public User getUser (Long id) {
	  
	User user = userMap.get(id); 
	return user;
	
	
  }
  
public void deleteUser (Long id) {
	  
	  if (userMap.containsKey(id)) {
		  userMap.remove(id);
	  }
  }
  
  
  public List<User> UserList() {
	  
	List<User> userList = new ArrayList<User>(userMap.values());
	return userList;
	  
  }
  
  // Functions related to Movie
  
  public Movie addMovie (String title, String url, int year) 
  {
	  
    Movie movie = new Movie (title, url, year);
    movieMap.put(movie.id, movie);
    return movie;
    
  }

  public Movie getMovie (Long id) {
	  
	Movie movie = movieMap.get(id); 
	return movie;

	  
  }
  
  public void deleteMovie (Long id) {
	  
	  if (movieMap.containsKey(id)) {
		  movieMap.remove(id);
	  }
  }
  
  
  public List<Movie> movieList() {
	  
	List<Movie> movieList = new ArrayList<Movie>(movieMap.values());
	return movieList;
	  
  }
  
  
  
  // Related to Rating
  
  
  // Add Rating
  public void addRating (Long userID, Long movieID, int rating)
  {
	  movieRatingTable.put(userID, movieID, rating);
	  
  }

  
  // Remove Rating
  
  public void removeRating (Long userID, Long movieID)
  {
	  movieRatingTable.remove(userID, movieID);
	  
  }
  
  public void getRating(Long userID, Long movieID) {
	  
	  System.out.println(movieRatingTable.get(userID, movieID));
	  
	  
  }
  
  
  
  public void getTopUserMovies(Long userID) 
 	{
 	 
	  Map<Long, Integer> userRatings = movieRatingTable.row(userID);
	  userRatings.entrySet().stream()
      .sorted(Map.Entry.<Long, Integer>comparingByValue().reversed()) 
      .limit(5) 
      .forEach( x -> System.out.println(movieMap.get(x.getKey()) + " ---> " + x.getValue()));
	  
 	}
  
  
  
  

  
  // Get top movies with average rating of users
  
  public double getAverageMovieRating(Long movieID) 
	{
	  
	  double score = 0;
	  double count = 0;
	  Map<Long, Integer> userRatings = movieRatingTable.column(movieID);
	  for(long i = 1; i < userRatings.size(); i++) {
	  
	  
	  	if (userRatings.get(i) != null) {
	  		score += userRatings.get(i);
	  		count++;
			
	  	}
	  }
	  // Calculate average
	  if (count != 0.0) {
		  double rat = score/count;
		  return rat;
	  } else {
		  // If count = 0 
		  return 0.0;
	  }
 
	}
  
    // Prints the movies that User hasn't seen sorted by their popularity
    public void getUserRecomendation(Long userID) {
    		
    		if (userID <= userMap.size()) {
    		// Prepares Hashmap to sort them by double values later
    		Map<Double, Movie> sortedMovies = new HashMap<>();
    		// Stores which movie(key) user hasn't seen
    		ArrayList<Long> unseenMovies = new ArrayList<Long>();
    		for(long i = 1; i <= movieMap.size(); i++) {
    		// Prints Every column for certain user 
    		// System.out.println(movieRatingTable.get(userID, i));
    			if (movieRatingTable.get(userID, i) == null) {
    				  unseenMovies.add(i);
    			  }
    		  }

    		  for(Long movieID : unseenMovies){
					//System.out.println(movieMap.get(movieID) + " ---> " + getAverageMovieRating(movieID));
					sortedMovies.put(getAverageMovieRating(movieID), movieMap.get(movieID));
    		  }  
		  sortedMovies.entrySet().stream()
				      .sorted(Map.Entry.<Double, Movie>comparingByKey()
				    	  .reversed())
				      .forEach( x -> System.out.println( x.getValue() + " ---> " + x.getKey()));

    	 	}
    		else {
    			System.out.println("This user is not in database");
    		}
    }
    		 
    		 
    public void getTopTenMovies() {	 
    	Map<Double, Movie> sortedMovies = new HashMap<>();	
    	
    	for(Long movieID = 1L; movieID <= movieMap.size(); movieID++){
			//System.out.println(movieMap.get(movieID) + " ---> " + getAverageMovieRating(movieID));
			sortedMovies.put(getAverageMovieRating(movieID), movieMap.get(movieID));
	  } 
    	
    	sortedMovies.entrySet().stream()
	      .sorted(Map.Entry.<Double, Movie>comparingByKey()
	    	  .reversed()).limit(10)
	      .forEach( x -> System.out.println( x.getValue() + " ---> " + x.getKey()));
    	
    }
    
 

  // Movies Alphabetically sorted
  
  
public Collection<Movie> sortedByTitle() {
	  
	  List<Movie> sortedByTitle = new ArrayList<Movie>(movieMap.values());
	  Collections.sort(sortedByTitle, new AlphabeticalComparator());
	  
	  return sortedByTitle;
  }
  
  
  // Movies By Year sorted
  
  public Collection<Movie> sortedByYear() {
	  
	  List<Movie> sortedByYear = new ArrayList<Movie>(movieMap.values());
	  Collections.sort(sortedByYear, new YearComparator());
	  
	  return sortedByYear;
  }
  
  class YearComparator implements Comparator<Movie> {
	    @Override
	    public int compare(Movie a, Movie b) {
	        return a.year < b.year ? -1 : a.year == b.year ? 0 : 1;
	    }
	}
  
  class AlphabeticalComparator implements Comparator<Movie> {
	    @Override
	    public int compare(Movie a, Movie b) {
	        return a.title.compareToIgnoreCase(b.title);
	    }
	}
  
  
  public List<Movie> searchMovie(String substring) {
	  
	  List<Movie> movieList = new ArrayList<Movie>();
	  for (Movie movie : movieMap.values()) {
		  if(movie.title.contains(substring)) {
			  movieList.add(movie);
		  }
		  
	  }
	  return movieList;
  }
  
  
  // Initial Load of datas from Files
  public void initialLoad () throws IOException
  {
	  Long userID = 1l;
	  Long movieID = 1l;
	  
	  
	  // Loading movies datas
	  String delims = "[|]";
      Scanner scanner = new Scanner(new File("./data/items5.dat"));
      while (scanner.hasNextLine()) {
          String movieDetails = scanner.nextLine();
          // parse user details string
          String[] movieTokens = movieDetails.split(delims);
          
          // Extracting year from brackets ----- (1995)
          int year;
        	  year = Integer.parseInt(movieTokens[1].substring(movieTokens[1].indexOf("(")+1,movieTokens[1].indexOf(")")));
              
     
          Movie movie = new Movie(movieTokens[1], movieTokens[3], year);
          movieMap.put(movieID, movie);
          movieID++;
         
          
          }
      scanner.close();
      // Loading Ratings data
      Scanner scanner1 = new Scanner(new File("./data/ratings5.dat"));
      while (scanner1.hasNextLine()) {
          String ratingDetails = scanner1.nextLine();
          // Parse user details string
          String[] strings = ratingDetails.split(delims);
          // Convert String input to Integers
          long usID = Long.parseLong(strings[0]);
          long movID = Long.parseLong(strings[1]);
          int scoreRating = Integer.parseInt(strings[2]);
          
          Rating rating = new Rating(usID, movID, scoreRating);
          movieRatingTable.put(usID, movID, scoreRating);
          
      }
      scanner1.close();
      // loading user datas
      Scanner scanner2 = new Scanner(new File("./data/users5.dat"));
      while (scanner2.hasNextLine()) {
          String userDetails = scanner2.nextLine();
          // parse user details string
          String[] userTokens = userDetails.split(delims);
          
          User user = new User(userTokens[1], userTokens[2], Integer.parseInt(userTokens[3]),userTokens[4], userTokens[5], Integer.parseInt(userTokens[6]), userTokens[7]);
          userMap.put(userID, user);
          userID++;
          
      }
      scanner2.close();
      
	  
  }

}

 

