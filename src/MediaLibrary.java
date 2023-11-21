import java.util.ArrayList;

public class MediaLibrary {

    FileIO io = new FileIO();
    ArrayList<String> movieGenres = new ArrayList<>();
    ArrayList<String> seriesGenres = new ArrayList<>();
    ArrayList<String> mediaGenres = new ArrayList<>();

    ArrayList<Media> allMedia = new ArrayList<>();
    ArrayList<Media> allMovies = new ArrayList<>();
    ArrayList<Media> allSeries = new ArrayList<>();

    public ArrayList<String> getMovieGenres() {
        movieGenres.add("Crime");
        movieGenres.add("Drama");
        movieGenres.add("Biography");
        movieGenres.add("Sport");
        movieGenres.add("History");
        movieGenres.add("Romance");
        movieGenres.add("War");
        movieGenres.add("Mystery");
        movieGenres.add("Adventure");
        movieGenres.add("Family");
        movieGenres.add("Fantasy");
        movieGenres.add("Thriller");
        movieGenres.add("Horror");
        movieGenres.add("Film-Noir");
        movieGenres.add("Action");
        movieGenres.add("Sci-fi");
        movieGenres.add("Comedy");
        movieGenres.add("Musical");
        movieGenres.add("Western");
        movieGenres.add("Music");

        return movieGenres;
    }

    public ArrayList<Media> getAllMovies() {
        allMovies = io.readMovieData("src/100bedstefilm.txt");
        return allMovies;
    }

    public ArrayList<Media> getAllSeries() {
        allSeries = io.readSeriesData("src/100bedsteserier.txt");
        return allSeries;
    }

    public ArrayList<Media> getAllMedia(){

        allMedia = getAllMovies();

        for (Media s: getAllSeries()) {
            allMedia.add(s);
        }

        return allMedia;
    }


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
