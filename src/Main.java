import java.io.InputStreamReader;

public class Main {
  public static void main(String[] args) {

    Collage model = new Collage();
    View textView = new View(model, System.out);
    Readable rd = new InputStreamReader(System.in);
    IController controller = new Controller(model, rd, textView);
    controller.apply();
  }
}