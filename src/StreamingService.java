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



        } else if(input.equals("1")){

            logIn();

        } else{
            ui.displayMessage("Your input was invalid. Press 1 to be redirected to the start menu.");
            if(ui.getInput().equals("1")) {

                startMenu();

            }
        }

    }
    public void mainMenu(){

       initializeLibrary();

        for (Media me: series) {
            System.out.println(me);

        }


    
ui.displayMessage("Please select your desire option from the menu below\n"+"\n"+
        "1. Search a media"+"\n"+
        "2. Find a genre"+ "\n"+
        "3. Watch later"+"\n"+
        "4. Watch again"+"\n"+
        "\n"+"9. Log out");
        //"5. Add media to platform" det her bliver en admin funktion kun admin kan skal have adgang til);
String input = ui.getInput();
if (input.equals("1")){
searchMedia();
} else if (input.equals("2")) {
    ui.displayMessage("find genre");//findGenre();
} else if (input.equals("3")) {
   ui.displayMessage("Wacthed Media"); //wacthedMedia();
} else if (input.equals("4")) {
    ui.displayMessage("Watch Later");// watchLater();
}else if (input.equals("9")) {
    startMenu();
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

    public void searchGenre(){

    }
    private void signUp(){


    }
    private void logIn(){

    }
}