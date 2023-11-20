import org.w3c.dom.Text;

import java.util.ArrayList;

public class StreamingService {
    private ArrayList<User> users;

    private TextUI ui = new TextUI();

    public void startMenu(){

        ui.displayMessage("Hello and welcome to Streamingservice! \n" +
                "\n" +
                "Please choose one of the following options:" + "\n" +
                "1. Sign in to an existing user \n" +
                "2. Create a new user");
                if(ui.getInput().equals("2")){

                    ui.displayMessage("Please enter a username.");
                    String userInput = ui.scan.nextLine();
                    ui.displayMessage("Please enter a password.");
                    String passwordInput = ui.scan.nextLine();
                    User user = new User(userInput, passwordInput, false);
                    ui.displayMessage("Thank you for signing up , " + userInput + ".");

                } else if(ui.getInput().equals(("1"))){

                } else{
                    ui.displayMessage("Your input was invalid. Press 1 to be redirected to the start menu.");
                    if(ui.getInput().equals("1")) {

                        startMenu();

                    }
                }

    }
    public void mainMenu(){

    }
    public void searchMedia(){

    }
    private void signUp(){


    }
    private void logIn(){

    }
}
