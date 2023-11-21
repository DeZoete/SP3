import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
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
               String [] row = s.split(";");

               String titel = row[0];

               String releaseYear = row[1].trim();

               String genre = row[2];

               String [] genreList = genre.split(",");

               ArrayList<String> genreArrList = new ArrayList<>();

               for (String g: genreList) {
                   genreArrList.add(g.trim());
               }

               row[3] = row[3].replace(',', '.').trim();

               float rating = Float.parseFloat(row[3]);

               Movie movie = new Movie(titel,genreArrList,releaseYear,rating);
               data.add(movie);

           }
       }catch(FileNotFoundException e){
           System.out.println("file not found");
       }
       return data;
   }

    public ArrayList<Media> readSeriesData(String seriesPath){
        ArrayList<Media> data = new ArrayList<>();
        File file = new File(seriesPath);
        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String s = scan.nextLine();// Hele linjen vil stå i én string

                String [] row = s.split(";");

                String titel = row[0];

                String releaseYear = row[1].trim();

                String genre = row[2];

                String [] genreList = genre.split(", ");

                ArrayList<String> genreArrList = new ArrayList<>();

                for (String g: genreList) {
                    genreArrList.add(g.trim());
                }

                row[3] = row[3].replace(',', '.').trim();

                float rating = Float.parseFloat(row[3]);

                String seasons = row[4];

                String [] seasonsList = seasons.split(",");

                int seasonAmount = 0;

                HashMap<Integer, Integer> seasonEpisodeAmount = new HashMap<>();;

                for (String se: seasonsList) {

                    String [] seasonEpisode = se.split("-");

                    seasonEpisodeAmount.put(Integer.parseInt(seasonEpisode[0].trim()),Integer.parseInt(seasonEpisode[1].trim()));

                    seasonAmount++;

                }

                Series series = new Series(titel,genreArrList,releaseYear,rating, seasonAmount,seasonEpisodeAmount);
                data.add(series);

            }
        }catch(FileNotFoundException e){
            System.out.println("file not found");
        }
        return data;
    }




    public void writeUserData() {

    }


}
