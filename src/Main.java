import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
  public static void main(String[] args) {
    Collage model = new Collage();
    View textView = new View(model, System.out);
    Readable rd = new InputStreamReader(System.in);
    IController controller = new Controller(model, rd, textView);
    controller.apply();
  }
}