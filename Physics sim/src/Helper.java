import java.util.ArrayList;

public class Helper {

    public ArrayList<GameObject> objects;

    public Helper() {
        objects = new ArrayList<GameObject>();
    }

    public void add(GameObject u) {
        objects.add(u);
    }

    public void remove(GameObject u) {
        objects.remove(u);
    }
}
