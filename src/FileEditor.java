import java.util.ArrayList;

public interface FileEditor {

    public ArrayList<Media> readMediaData(String path);

    void writeUserData();
}
