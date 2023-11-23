import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class StreamingService {
    private ArrayList<User> users = new ArrayList<>();

    private ArrayList<String> mediaGenres = new ArrayList<>();
    private ArrayList<Media> media = new ArrayList<>();
    private ArrayList<Media> movies = new ArrayList<>();
    private ArrayList<Media> series = new ArrayList<>();


    private User currentUser;
    private ArrayList<Media> currentList;
    private ArrayList<Media> emptyList = new ArrayList<>();


    private String username;
    private String password;
    private File userFile = new File("src/userdata.txt");

    private TextUI ui = new TextUI();
    private FileIO io = new FileIO();
    private MediaLibrary library = new MediaLibrary();

    public void startMenu(){

        ui.displayMessage("Hello and welcome to Streamingservice! \n" +
                "\n" +
                "Please choose one of the following options:" + "\n" +
                "1. Sign in to an existing user \n" +
                "2. Create a new user");
        users = io.readUserData("src/userdata.txt");
        String input = ui.getInput();
       switch(input){
           case "1":
               logIn();
               break;
           case "2":
               signUp();
               break;
           default:
               invalidInput();
               break;
       }

    }
    private void mainMenu(){

       initializeLibrary();

        ui.displayMessage("Please select your desired option from the menu below\n"+"\n"+
                "1. Search for media"+"\n"+
                "2. Find media sorted by genre"+ "\n"+
                "3. Find media sorted by rating"+ "\n"+
                "4. Show plan to watch list"+"\n"+
                "5. Show media history"+"\n"+
                "\n"+"6. Log out");

        String input = ui.getInput();
        switch (input) {
            case "1":
                searchMedia();
                mediaChoice(pickMedia(currentList));

                break;
            case "2":
                searchGenre();
                mediaChoice(pickMedia(currentList));
                break;
            case "3":
                searchRating();
                mediaChoice(pickMedia(currentList));
                break;
            case "4":
                showToWatchlist();
                break;
            case "5":
                showHistory();
                break;

            case "6":
                startMenu();
                break;
            default:
                invalidInput();
                mainMenu();
                break;

        }


        }



    private void searchMedia(){
        ui.displayMessage("Search for the title you want to watch"+"\n");

        String input = ui.getInput();
        ArrayList<Media>results = new ArrayList<>();
        HashSet<String> uniqueList = new HashSet<>(); //Der kan kun være en af hver
        for (Media m : media) {
            String title = m.getTitel();
            if (title.toLowerCase().contains(input) && uniqueList.add(title.toLowerCase())) {
                results.add(m);
            }
        }
            currentList=results;
           ui.displayArrayList(currentList);

        }


    private void searchGenre() {
        ui.displayMessage("Type in your genre you want to find" + "\n");

        System.out.println(library.getMovieGenres());
        String input = ui.getInput();
        currentList = library.makeGenreList(media,input);
        System.out.println(currentList);

    }

    private void searchRating() {
        ui.displayMessage("Sort by rating. What is the minimum rating media should have?" + "\n");

        String input = ui.getInput();

        //Dette kan gå galt hvis bruger ikke skriver en float
        float rating = Float.parseFloat(input);

        currentList = library.makeMinimumRatingList(media,rating);
        System.out.println(currentList);

    }



    private void showHistory() {
        if(currentUser.getWatchedList()==null){
            ui.displayMessage("Your media history list is empty. Go watch some media :)");
            mainMenu();
        }else {
            currentList=currentUser.getWatchedList();
            ui.displayArrayList(currentList);
            mainMenu();
        }
    }

    private void showToWatchlist() {
        if(currentUser.getToWatchList()==null){
            ui.displayMessage("Your plan to watch list is empty. When you find media you can add it to your plan to watch list");
            mainMenu();
        }else {
            currentList=currentUser.getToWatchList();
            ui.displayArrayList(currentList);
            mainMenu();
        }
    }




    private void signUp(){

        ui.displayMessage("Please enter a username.");
        String userInput = ui.scan.nextLine();
        ui.displayMessage("Please enter a password.");
        String passwordInput = ui.scan.nextLine();
        User user = new User(userInput, passwordInput, false);
        users.add(user);
        ui.displayMessage("Thank you for signing up , " + userInput + ".");

        io.writeUserData("src/userdata.txt", users);

        startMenu();

        }


    private void logIn(){

        ui.displayMessage("Please enter your username.");
        String userInput = ui.getInput();
        ui.displayMessage("Please enter your password.");
        String passwordInput = ui.getInput();
        User userSignIn = new User(userInput, passwordInput, false);
        boolean loggingin = false;
        for(User c: users) {

            if (userInput.equals(c.getUsername()) && passwordInput.equals(c.getPassword())) {
                ui.displayMessage("Logging in. Stand by.");
                currentUser = c;
                loggingin = true;
                mainMenu();
            }
        }


            if(!loggingin){
                invalidUserPass();
            }
        }



    private void initializeLibrary(){
        movies = library.getAllMovies();
        series = library.getAllSeries();
        media = library.getAllMedia();
    }

    private void invalidInput(){

        ui.displayMessage("Your input was invalid. Press 1 to be redirected to the start menu.");
        if(ui.getInput().equals("1")) {

            startMenu();

        } else{
            invalidInput();
        }
    }

    private void invalidUserPass(){

        ui.displayMessage("Your username or password is invalid. Press 1 to be redirected to the start menu.");
        if(ui.getInput().equals("1")) {

            startMenu();

        } else{
            invalidInput();
        }
    }
    private void playMedia(Media m){
        m.play();
        currentUser.addWatchedList(m);
        mainMenu();
    }
    private void mediaChoice(Media media){
        ui.displayMessage("1. Play "+media.getTitel()+"\n"+
                "2. Add to watch later"+"\n"+
                "0. Go back to main menu");
        String input = ui.getInput();
      switch (input){
          case"1":
          playMedia(media);
          break;
          case"2":
              currentUser.addToWatchList(media);
              mediaChoice(media);
              break;
          case"0":
              mainMenu();
              break;
          default:
             invalidInput();
      }
    }

    private Media pickMedia(ArrayList<Media> list){
        ui.displayMessage("Pick media from the list above by writing a titel.");
        String input = ui.getInput();
        Boolean picked = false;
        Media pickedMedia;
        for (Media m : list) {
            if(input.equalsIgnoreCase(m.getTitel())){
                picked=true;
                pickedMedia= m;
                return pickedMedia;
            }
        }

        ui.displayMessage("The titel you wrote doesn't match your list. Try again.");
        return pickMedia(list);
    }
    private


}