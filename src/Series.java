import java.util.ArrayList;
import java.util.HashMap;

public class Series extends AMedia {
    int seasonAmount;
    private HashMap<Integer, Integer> seasonEpisodeAmount;

    public Series(String titel, ArrayList<String> genre, String releaseYear, float rating) {
        super(titel, genre, releaseYear, rating);
        this.rating = rating;
    }


    public void play() {
        System.out.println(titel + " is now playing");
    }

    public String getTitel() {
        return titel;
    }

    public ArrayList<String> getGenre() {
        return genre;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public float getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "\n Title:  "
                + "\n Genre:  "
                + "\n Release Year:  "
                + "\n Rating:  ";
    }
}

