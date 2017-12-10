# Assignment 2 - Movie Recommender.

Ondřej Svojše

## Overview.
MovieLibraryRecommender is simple Java program that reads data from external 3rd party service about Users, Movies and their Ratings. Program recomends best matching movie that user hasn't seen. Program can also register new users and work with stored informations.


## Functionality
indludes implemented features (functions) of program.

User
-Adding users
-Listing all users
-deleting users
- retrieving user by ID

Movie
-Adding movie
-Listing all movies
-deleting movies
- retrieving movie by ID

Ratings
- add Rating
- delete rating
- retrieve average rating of movie
- retrieve user's rating for movie

Loading external datas
Top movies based on user rating
Recommendation of unseen movies
Searching for movie
Login
10 Most popular movies


## Installation requirements
. . . .  List of software, libraries and tools used (hint: everything on your build path libraries ) . . . . . . .
+ Java JRE v1.8
+ Guava v0.1
+ Cliche

## Getting started

Import the project into your IDE. Check for compiling errors, if there are non, hit the run button.


>The project comes with data in CSV format that can be used to prime the application with initial data.
- In the CLI, execute the prime command to import data from the CSV movie files:
```
Welcome to MovieRecommender-console - ?help for instructions
pm> ?list
abbrev    name    params
li    log-in    (UserID, password)
il    initial-load    ()
pm>
- Log in as the administrator user (Melody)
```
Melody has logged in...
You are logged in with the ID: Melody
Admin

## Examples

. . . . . Examples of program's user interface (e.g. CLI)  (see example below) with appropriate captions (user regeneration and login views, if implemented, can be omitted) . . . . . . .

- Get Users top 5 movies
```
pm/Leonard> gut 1
Movie{1, Toy Story (1995), http://us.imdb.com/M/title-exact?Toy%20Story%20(1995)} ---> 5
Movie{2, GoldenEye (1995), http://us.imdb.com/M/title-exact?GoldenEye%20(1995)} ---> 5
Movie{3, Four Rooms (1995), http://us.imdb.com/M/title-exact?Four%20Rooms%20(1995)} ---> 3
Movie{10, Richard III (1995), http://us.imdb.com/M/title-exact?Richard%20III%20(1995)} ---> 3
Movie{7, Twelve Monkeys (1997), http://us.imdb.com/M/title-exact?Twelve%20Monkeys%20(1995)} ---> 1

- Getting movie recommendations for Gregory (test data):
```
pm/Leonard> gur 2
Movie{9, Dead Man Walking (1995), http://us.imdb.com/M/title-exact?Dead%20Man%20Walking%20(1995)} ---> 1.0
Movie{8, Babe (1995), http://us.imdb.com/M/title-exact?Babe%20(1995)} ---> 0.0
Movie{5, Copycat (1995), http://us.imdb.com/M/title-exact?Copycat%20(1995)} ---> -3.0

`
## Independent learning.

I have learned how to use new Java 8 features and Guava google library to make my code more readable and easier to modify.

