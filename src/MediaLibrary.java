import java.util.ArrayList;

public class MediaLibrary {

    FileIO io;
    ArrayList<String> mediaGenres = new ArrayList<>();
    ArrayList<Media> allMedia = new ArrayList<>();
    ArrayList<Media> allMovies = new ArrayList<>();
    ArrayList<Media> allSeries = new ArrayList<>();


    ArrayList<Media> makeGenreList(ArrayList<Media> mediaList, String genre){
        ArrayList<Media> genreList = new ArrayList<>();

        for (Media m : mediaList) {

            ArrayList<String> genres = new ArrayList<>();
            genres = m.getGenre();

            for (String g:genres) {
                if(g.equals(genre)){
                    genreList.add(m);
            }


            }
        }

        return genreList;
    }



}
