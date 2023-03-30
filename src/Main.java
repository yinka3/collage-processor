import java.io.InputStreamReader;

import cs3500.controller.Controller;
import cs3500.controller.IController;
import cs3500.model.Collage;
import cs3500.model.ICollage;
import cs3500.view.View;


public class Main {
  public static void main(String[] args) {
    ICollage model = new Collage();
    View textView = new View(model, System.out);
    Readable rd = new InputStreamReader(System.in);
    IController controller = new Controller(model, rd, textView);
    controller.apply();
  }
}