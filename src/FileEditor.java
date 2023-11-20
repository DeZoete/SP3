import java.util.ArrayList;

public interface FileEditor {

    public ArrayList<Media> readMovieData(String path);

    void writeUserData();
}
