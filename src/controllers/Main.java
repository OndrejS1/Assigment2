package controllers;

import java.io.File;
import java.io.IOException;



import utils.Serializer;
import utils.XMLSerializer;
import asg.cliche.Command;
import asg.cliche.Param;
import asg.cliche.Shell;
import asg.cliche.ShellDependent;
import asg.cliche.ShellFactory;
import models.Movie;
import models.User;


  
	
	public class Main implements ShellDependent {
		
		
		  
		  public movieAPI movAPI;
		  private Shell theShell;
		  
		  public Main() throws Exception {
			
			
		    File datastore = new File("datastore.xml");
		    Serializer serializer = new XMLSerializer(datastore);
		    movAPI = new movieAPI(serializer);
		    
		   
		  }
		  
		  public void cliSetShell(Shell theShell) {
			    this.theShell = theShell;
			  }
				
		  
		  
		  @Command(description = "Log in")
		  public void logIn(@Param(name = "UserID") Long UserID, @Param(name = "password") String password)
		      throws IOException {

			  
		    if (movAPI.login(UserID, password) && movAPI.currentUser.isPresent()) {
		      User user = movAPI.currentUser.get();
		      System.out.println("You are logged in with the ID: " + user.firstName);
		      if (user.role!=null && user.role.equals("admin")) {
		        AdminMenu adminMenu = new AdminMenu(movAPI, user);
		        ShellFactory.createSubshell(user.firstName, theShell, "Admin", adminMenu).commandLoop();
		      } else {
		        DefaultMenu defaultMenu = new DefaultMenu(movAPI, user);
		        ShellFactory.createSubshell(user.firstName, theShell, "Default", defaultMenu).commandLoop();
		      }
		    } else
		      System.out.println("Unknown username/password.");
		  }

		  
		 
		  
		  @Command(description="Initial Load from file")
		  public void initialLoad() throws Exception
		  {
			  movAPI.initialLoad();
			 
		  }
		 
		  public static void main(String[] args) throws Exception {
			
		    Main main = new Main();
		    
		    Shell shell = ShellFactory.createConsoleShell("pm", "Welcome to MovieRecommender - ?help for instructions",
		        main);
		    shell.commandLoop();
		    main.movAPI.write();
		  }
		}
  
  

 
  
  

  

  
