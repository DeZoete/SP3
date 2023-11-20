public class Main {
    public static void main(String[] args){
        String[] genre = new String [2];
        genre[0] = "Drama";
        genre[1] = "Krimi";
Movie film = new Movie("Monner", genre,"2010", 9.9F);
film.play();
        System.out.println(film);
    }
}