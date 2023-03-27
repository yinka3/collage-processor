import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
  public static void main(String[] args) {
    HashMap<String, int[][]> maps = new HashMap<>();
    maps.put("test", new int[][]{ { 1, 2, 3 }, { 4, 5, 6 } });
    maps.put("test2", new int[][]{{4,3,436}, {345,63,234}});

    ArrayList<HashMap<String, int[][]>> newmaps = null;
    
    newmaps.add(maps);
    //Collage model = new Collage();
    //View textView = new View(model, System.out);
    //Readable rd = new InputStreamReader(System.in);
    //IController controller = new Controller(model, rd, textView);
    //controller.apply();
  }
}