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


    private String username;
    private String password;
    private File userFile = new File("src/userdata.txt");

    private TextUI ui = new TextUI();
    private FileIO io = new FileIO();
    private MediaLibrary library = new MediaLibrary();

    public void startMenu(){

        ui.displayMessage("\n" + "Hello and welcome to Streamingservice! \n" +
                "\n" +
                "Please choose one of the following options:" + "\n" +
                "1. Sign in to an existing user \n" +
                "2. Create a new user" + "\n" +
                 "\n" + "0. Exit" + "\n");
        users = io.readUserData("src/userdata.txt");
        String input = ui.getInput();
       switch(input){
           case "1":
               logIn();
               break;
           case "2":
               signUp();
               break;
           case "0":
               System.exit(0);
           default:
               invalidInput();
               break;
       }

    }
    public void mainMenu(){

       initializeLibrary();
/*
        for (Media me: series) {
            System.out.println(me);

        }*/
        ui.displayMessage("Please select your desired option from the menu below\n"+"\n"+
                "1. Search a media"+"\n"+
                "2. Find a genre"+ "\n"+
                "3. Watch later"+"\n"+
                "4. Watch again"+"\n"+
                "\n"+"9. Log out" + "\n" +
                "0. Exit" + "\n");

        String input = ui.getInput();
        switch (input) {
            case "1":
                searchMedia();
                break;
            case "2":
                searchGenre();
                break;
            case "3":
                if(currentUser.getToWatchList()==null){
                    ui.displayMessage("Your watch later list is empty. Add a media to your watch later list."+"\n");
                    mainMenu();
                }else {
                    currentUser.getToWatchList();
                }
                break;

            case "4":
                if(currentUser.getToWatchList()==null){
                    ui.displayMessage("Your watch later list is empty. Add a media to your watch later");
                    mainMenu();
                }else {
                    currentUser.getToWatchList();
                }
                break;

            case "9":
                ui.displayMessage("\n" + "You have been logged out.");
                startMenu();
                break;
            case "0":
                ui.displayMessage("\n" + "Thank you for using StreamingService.");
                System.exit(0);
            default:
                invalidInputMainMenu();
                break;

        }


        }

    public void playMedia(Media m){
        m.play();
        currentUser.addWatchedList(m);
        try{

            Thread.sleep(5000);
            ui.displayMessage(m.getTitel() + " is now finished playing." + "\n" +
                    "\n"+
                    "Would you like to submit a rating for the content you just watched?" + "\n"
                    + "\n" +
                    "1. Yes" + "\n" +
                    "2. No");
            String input = ui.getInput();
            switch(input){
                case "1":
                    submitRating();
                    break;
                case "2":
                    break;
                default:
                    invalidInputMainMenu();
                    break;
            }
        } catch(InterruptedException e){
            System.out.println("An error occurred while playing.");
        }
        mainMenu();
    }

    public void searchMedia(){
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
            for(Media r : results ){
            System.out.println(r);
        }
    }

    public void searchGenre() {
        ui.displayMessage("Type in your genre you want to find" + "\n");

        System.out.println(library.getMovieGenres());
        String input = ui.getInput();
        currentList = library.makeGenreList(media,input);
        System.out.println(currentList);

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

    private void invalidInputMainMenu(){
        ui.displayMessage("Your input was invalid. Press 1 to be redirected to the main menu.");
        if(ui.getInput().equals("1")) {

            mainMenu();

        } else{
            invalidInputMainMenu();
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
    private void MediaChoice(Media media){
        ui.displayMessage("1. Play "+media+"\n"+
                "2. Add to watch later"+"\n"+
                "3. Go back to main menu");
        String input = ui.getInput();
      switch (input){
          case"1":
          playMedia(media);
          break;
          case"2":
              currentUser.addToWatchList(media);
              break;
          case"3":
              mainMenu();
              break;
          default:
             invalidInput();
      }
    }

    private void submitRating(){

        ui.displayMessage("Please enter a rating between 1 and 10 that most accurately represents your opinion of the watched content.");
        String input = ui.getInput();
        int parsedInput = Integer.parseInt(input);
        if(parsedInput > 0 && parsedInput <= 10){
            ui.displayMessage("Thank you for submitting your rating!");
        }
        else{
            ui.displayMessage("Your input was invalid.");
            submitRating();
        }
    }

}