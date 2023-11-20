import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO implements FileEditor{

    Scanner scan;

   public ArrayList<Media> readMovieData(String moviePath){
       ArrayList<Media> data = new ArrayList<>();
       File file = new File(moviePath);
       try {
           Scanner scan = new Scanner(file);
           while (scan.hasNextLine()) {
               String s = scan.nextLine();// Hele linjen vil stå i én string

               String [] row = s.split("; ");

               String titel = row[0];

               String releaseYear = row[1];

               String genre = row[2];

               String [] genreList = s.split(", ");

               float rating = Float.parseFloat(row[3]);

               Movie m = new Movie(titel,genreList,releaseYear,rating);
               data.add(m);

           }
       }catch(FileNotFoundException e){
           System.out.println("file not found");
       }
       return data;
   }


    public void writeUserData() {

    }


}
