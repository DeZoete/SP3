import java.util.ArrayList;
import java.util.Scanner;

public class TextUI{
    protected Scanner scan = new Scanner(System.in);

    //shows a message and returns the user's input as a String
    public String getInput(){
        return scan.nextLine();
    }


    public void displayMessage(String msg){

        System.out.println(msg);

    }




    public int getNumericInput(String msg){
        System.out.println(msg);//Stille brugeren et spørgsmål

        // int input = scan.nextInt();
        // This is ok, but BEWARE OF THE SCANNERBUG: When using nextInt() right before nextLine(): the nextLine call will be skipped...
        // fix this by simply calling nextLine() once before you actually need it
        // Another fix: read it as string, then parse it:

        String input = scan.nextLine();          //Give brugere et sted at placere sit svar og vente på svaret
        int num = 0;
        try {
            num = Integer.parseInt(input);       //Konvertere svaret til et tal

        }catch (NumberFormatException e){
            System.out.println("Please select an option from the menu, "+e.getMessage());
            num = getNumericInput(msg);

        }
        return num;

    }
    /*
     *
     * ' shows a message, lists the content of a list and returns the user's choice
     * */
    public String getChoice(ArrayList<String> options, String msg){
        System.out.println(msg);
        //  displayMenu(options);
        String input = getInput();

        //tjek om input findes i listen, hvis ikke, smid en exception
        if(!options.contains(input)){
            System.out.println("This is not an option pick an option from the menu");
            input = getChoice(options, msg);
        }

        return input;
    }


    public void displayMenu(ArrayList<String> options) {

        for (String option : options) {

            System.out.println(option);
        }

    }
    public void displayArrayList(ArrayList<Media> media){
        for (Media m : media){

        System.out.println(m+"\n"+"______________________________________________"+"");

        }

    }
}