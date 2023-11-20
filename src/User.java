import java.util.ArrayList;

public class User {
   private Boolean administrator;
   private String username;
   private String password;
   private ArrayList<Media> toWatchList;
   private  ArrayList<Media> watchedList;

   User(String username, String password, boolean administrator){

       this.username = username;
       this.password = password;
       this.administrator = administrator;

   }

   public void addToWatchList(Media media){

       toWatchList.add(media);

    }
    public  ArrayList<Media> getWatchedList(){

       return watchedList;

    }
    public ArrayList<Media> getToWatchList(){

       return toWatchList;

    }
    void addMedia(Media media){


    }

    public String getUsername(){
       return username;
    }

    public String getPassword(){
       return password;
    }
}
