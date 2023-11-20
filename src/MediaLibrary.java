import java.util.ArrayList;

public class MediaLibrary {

    FileIO io;
    ArrayList<String> mediaGenres = new ArrayList<>();
    ArrayList<Media> allMedia = new ArrayList<>();
    ArrayList<Media> allMovies = new ArrayList<>();
    ArrayList<Media> allSeries = new ArrayList<>();


    ArrayList<Media> getGenreList(ArrayList<Media> mediaList, String genre){
        ArrayList<Media> genreList = new ArrayList<>();

        for (Media m : mediaList) {
            if((m.getGenre).equals(genre)){
                genreList.add(m);
            }
        }

        return genreList;
    }



}
