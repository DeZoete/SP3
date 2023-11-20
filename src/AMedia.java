import java.lang.reflect.Array;

public abstract class AMedia implements Media {
    String[] genre;
    String releaseYear;
    String titel;
    float rating;

    public AMedia(String titel, String[] genre, String releaseYear, float rating){
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.titel = titel;
        this.rating = rating;

    }

    public void play(){
        System.out.println(titel + " is now playing");
    }

}
