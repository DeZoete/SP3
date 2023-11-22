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
                "4. List of watched content"+"\n"+
                "\n"+"9. Log out");

        String input = ui.getInput();
        switch (input) {
            case "1":
                searchMedia();
                break;
            case "2":
                searchGenre();
                break;
            case "3":
                ui.displayMessage("Watch later");
                break;

            case "4":
                ui.displayMessage("Watched content: ");
                System.out.println(currentUser.watchedList);
                break;
            case "5":
                startMenu();
                break;
            default:
                ui.displayMessage("Invalid input, try again");
                mainMenu();
                break;

        }


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
        System.out.println(library.makeGenreList(media,input));

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
        //User userSignIn = new User(userInput, passwordInput, false);

        //Det er unødvendigt at læse filen igen. Vi har allerede user data i arraylisten users
        try {
            Scanner scan = new Scanner(userFile);
            boolean loggingin = false;
            for (int i = 0; scan.hasNextLine(); i++) {
                String split = scan.nextLine();
                String[] usersAndPasswords = split.split(",");
                username = usersAndPasswords[0];
                password = usersAndPasswords[1];
                if (username.equals(userInput) && password.equals(passwordInput)) {

                    ui.displayMessage("Logging in. Stand by.");
                    loggingin=true;
                    mainMenu();
                }


            }

            if(!loggingin) {

                invalidUserPass();
            }
        } catch (FileNotFoundException e){
            System.out.println("File not found.");
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

        ui.displayMessage("Your input was invalid. Press 1 to be redirected to the start menu.");
        if(ui.getInput().equals("1")) {

            startMenu();

        } else{
            invalidInput();
        }
    }

}