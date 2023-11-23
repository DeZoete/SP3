import java.util.ArrayList;
import java.util.HashSet;


public class StreamingService {
    private ArrayList<User> users = new ArrayList<>();

    private ArrayList<String> mediaGenres = new ArrayList<>();
    private ArrayList<Media> media = new ArrayList<>();
    private ArrayList<Media> movies = new ArrayList<>();
    private ArrayList<Media> series = new ArrayList<>();
    private ArrayList<Media> kidsMedia = new ArrayList<>();


    private User currentUser;
    private ArrayList<Media> currentList;
    private ArrayList<Media> emptyList = new ArrayList<>();
    private int mediaType;


    private TextUI ui = new TextUI();
    private FileIO io = new FileIO();
    private MediaLibrary library = new MediaLibrary();

    public void startMenu(){

        ui.displayMessage("Hello and welcome to StreamStream! \n" +
                "\n" +
                "Please choose one of the following options:" + "\n" +
                "1. Sign in to an existing user \n" +
                "2. Create a new user");
        users = io.readUserData("src/userdata.txt");
        String input = ui.getInput();
       switch(input){
           case "1":
               logIn();
               break;
           case "2":
               signUp();
               break;
           default:
               invalidInput();
               break;
       }

    }
    private void mainMenu(){
        if(currentUser.getAge()<15){
            kidsMenu();
        }

        initializeLibrary();

        ui.displayMessage("Please select your desired option from the menu below\n" + "\n" +
                "1. Search for media" + "\n" +
                "2. Find media sorted by genre" + "\n" +
                "3. Find media sorted by rating" + "\n" +
                "4. Show plan to watch list" + "\n" +
                "5. Show media history" + "\n" +
                "\n" + "6. Log out");

        String input = ui.getInput();
        switch (input) {
            case "1":
                pickMediaType();
                searchMedia();
                mediaChoice(pickMedia(currentList));

                break;
            case "2":
                pickMediaType();
                searchGenre();
                mediaChoice(pickMedia(currentList));
                break;
            case "3":
                pickMediaType();
                searchRating();
                mediaChoice(pickMedia(currentList));
                break;
            case "4":
                showToWatchlist();
                break;
            case "5":
                showHistory();
                break;

            case "6":
                startMenu();
                break;
            default:
                invalidInput();
                mainMenu();
                break;

        }


        }

        public void kidsMenu(){

        currentList = library.getKidsMedia();
            ui.displayMessage("Please select your desired option from the menu below\n"+"\n"+
                    "1. Search for media"+"\n"+
                    "2. Show media history"+"\n"+
                    "9. Log out"+"\n"+
                    "\n"+"0. Exit");

            String input = ui.getInput();
            switch (input) {
                case "1":
                    searchMedia();
                    break;
                case "2":
                    break;
                case "9":
                    startMenu();
                    break;
                case "0":
                    System.exit(0);
                    break;
                default:
                    invalidInput();
                    kidsMenu();
                    break;

            }

        }


    public void searchMedia() {

        ui.displayMessage("Search for the title you want to watch" + "\n" + "0. Go to main menu" + "\n");

        String input = ui.getInput();
        if (!input.equals("0")) {
            ArrayList<Media> results = new ArrayList<>();
            HashSet<String> uniqueList = new HashSet<>(); //Der kan kun være en af hver
            for (Media m : media) {
                String title = m.getTitel();
                if (title.toLowerCase().contains(input) && uniqueList.add(title.toLowerCase())) {
                    results.add(m);
                }
            }
            for (Media r : results) {
                System.out.println(r);
            }
        } else if (input.equals("0")) {
            mainMenu();
        }
    }
    public void searchGenre() {
        ui.displayMessage("Type in your genre you want to find" + "\n" + "0. Go to main menu" + "\n");

        System.out.println(library.getMovieGenres());
        String input = ui.getInput();
        if (!input.equals("0")) {
            currentList = library.makeGenreList(media, input);
            ui.displayArrayList(currentList);
        currentList = library.makeGenreList(currentList,input);
        ui.displayArrayList(currentList);

        } else if (input.equals("0")) {
            mainMenu();
        } else{
            ui.displayMessage("please enter a correct genre");
            searchGenre();
        }
    }

    public void searchRating() {
       String input = String.valueOf(ui.getNumericInput("Sort by rating. What is the minimum rating media should have?" + "\n" + "0. Go to main menu" + "\n"));


        if (!input.equals("0")) {
            //Dette kan gå galt hvis bruger ikke skriver en float
            float rating = Float.parseFloat(input);
            currentList = library.makeMinimumRatingList(media, rating);
            ui.displayArrayList(currentList);

        } else if (input.equals("0")) {
            mainMenu();
        }
    }



    private void showHistory() {
        if(currentUser.getWatchedList()==null){
            ui.displayMessage("Your media history list is empty. Go watch some media :)");
            mainMenu();
        }else {
            currentList=currentUser.getWatchedList();
            ui.displayArrayList(currentList);
            mainMenu();
        }
    }

    private void showToWatchlist() {
        if(currentUser.getToWatchList()==null){
            ui.displayMessage("Your plan to watch list is empty. When you find media you can add it to your plan to watch list");
            mainMenu();
        }else {
            currentList=currentUser.getToWatchList();
            ui.displayArrayList(currentList);
            mainMenu();
        }
    }




    private void signUp(){

        ui.displayMessage("Please enter a username.");
        String userInput = ui.scan.nextLine();
        ui.displayMessage("Please enter a password.");
        String passwordInput = ui.scan.nextLine();
       int age = ui.getUserAge("Please enter your age");
        User user = new User(userInput, passwordInput, false,age);

        users.add(user);
        ui.displayMessage("Thank you for signing up , " + userInput + ".");

        io.writeUserData("src/userdata.txt", users);

        startMenu();

        }


    private void logIn(){

        ui.displayMessage("Please enter your username.");
        String userInput = ui.getInput();
        ui.displayMessage("Please enter your password.");
        String passwordInput = ui.getInput();
        boolean loggingin = false;
        for(User c: users) {

            if (userInput.equals(c.getUsername()) && passwordInput.equals(c.getPassword())) {
                ui.displayMessage("Logging in. Stand by.");
                currentUser = c;
                loggingin = true;
                mainMenu();
            }
        }


            if(!loggingin){
                invalidUserPass();
            }
        }



    private void initializeLibrary(){
        movies = library.getAllMovies();
        series = library.getAllSeries();
        media = library.getAllMedia();

    }

    private void invalidInput(){

        ui.displayMessage("Your input was invalid. Press 1 to be redirected to the start menu.");
        if(ui.getInput().equals("1")) {

            startMenu();

        } else{
            invalidInput();
        }
    }

    private void invalidUserPass(){

        ui.displayMessage("Your username or password is invalid. Press 1 to be redirected to the start menu.");
        if(ui.getInput().equals("1")) {

            startMenu();

        } else{
            invalidInput();
        }
    }
    private void playMedia(Media m){
        m.play();
        currentUser.addWatchedList(m);
        mainMenu();
    }
    private void mediaChoice(Media media){
        ui.displayMessage("1. Play "+media.getTitel()+"\n"+
                "2. Add to watch later"+"\n"+
                "0. Go back to main menu");
        String input = ui.getInput();
      switch (input){
          case"1":
          playMedia(media);
          break;
          case"2":
              currentUser.addToWatchList(media);
              mediaChoice(media);
              break;
          case"0":
              mainMenu();
              break;
          default:
             invalidInput();
      }
    }

    private Media pickMedia(ArrayList<Media> list){
        ui.displayMessage("Pick media from the list above by writing a titel.");
        String input = ui.getInput();
        Media pickedMedia;
        for (Media m : list) {
            if(input.equalsIgnoreCase(m.getTitel())){
                pickedMedia= m;
                return pickedMedia;
            }
        }

        ui.displayMessage("The titel you wrote doesn't match your list. Try again.");
        return pickMedia(list);
    }
    private void pickMediaType(){
        ui.displayMessage("Please select what type of media you want to search for\n"+"\n"+
                "1. Movies"+"\n"+
                "2. Series"+ "\n"+
                "3. Both"+ "\n"+
                "\n"+"0. Back to main menu");
        String inputM = ui.getInput();
        switch (inputM){
            case"1":
                mediaType=1;
                currentList=series;
                break;
            case"2":
                mediaType=2;
                currentList=series;
                break;
            case"3":
                mediaType=3;
                currentList=media;
                break;
            case"0":
                mainMenu();
                break;
            default:
                invalidInput();
                break;
        }

    }

}
