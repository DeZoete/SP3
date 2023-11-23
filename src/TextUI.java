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

    public void displayArrayList(ArrayList<Media> media){
        for (Media m : media){

            System.out.println(m+"\n"+"______________________________________________"+"");

        }

    }


}