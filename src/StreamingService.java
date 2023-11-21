import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class StreamingService {
    private ArrayList<User> users = new ArrayList<>();



    private String username;
    private String password;
    File userFile = new File("src/userdata.txt");

    private TextUI ui = new TextUI();

    public void startMenu(){

        ui.displayMessage("Hello and welcome to Streamingservice! \n" +
                "\n" +
                "Please choose one of the following options:" + "\n" +
                "1. Sign in to an existing user \n" +
                "2. Create a new user");
        try {
            Scanner scan = new Scanner(userFile);
            for (int i = 0; scan.hasNextLine(); i++) {
                String split = scan.nextLine();
                String[] usersAndPasswords = split.split(",");
                String username = usersAndPasswords[0];
                String password = usersAndPasswords[1];
                User user = new User(username, password, false);
            }
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
        String input = ui.getInput();
        if(input.equals("2")){

            signUp();

        } else if(input.equals("1")){

            logIn();

        } else{

            invalidInput();

        }

    }
    public void mainMenu(){

    }
    public void searchMedia(){

    }
    private void signUp(){

        ui.displayMessage("Please enter a username.");
        String userInput = ui.scan.nextLine();
        ui.displayMessage("Please enter a password.");
        String passwordInput = ui.scan.nextLine();
        User user = new User(userInput, passwordInput, false);
        users.add(user);
        ui.displayMessage("Thank you for signing up , " + userInput + ".");
        try{

            FileWriter writer = new FileWriter("src/userdata.txt",true);
            for(User s: users){
                String userSave = s.getUsername() + "," + s.getPassword() + "\n";
                writer.write(userSave);

            }
            writer.close();




        } catch(IOException e){
            ui.displayMessage("User not found. Press 1 to redirect to start menu.");
            if(ui.getInput().equals("1")) {

                startMenu();

            }

        }

    }
    private void logIn(){
        ui.displayMessage("Please enter your username.");
        String userInput = ui.getInput();
        ui.displayMessage("Please enter your password.");
        String passwordInput = ui.getInput();
        //User userSignIn = new User(userInput, passwordInput, false);
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
                }


            }

            if(!loggingin) {

                invalidUserPass();

            }
        } catch (FileNotFoundException e){
            System.out.println("File not found.");
        }
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

        ui.displayMessage("Username or password incorrect. Press 1 to be redirected to the start menu.");
        if (ui.getInput().equals("1")) {

            startMenu();

        } else{
            invalidInput();
        }
    }
}