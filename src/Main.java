import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import cs3500.controller.Controller;
import cs3500.controller.ControllerGUI;
import cs3500.model.Collage;
import cs3500.model.ICollage;
import cs3500.view.CollageView;
import cs3500.view.GUIView;
import cs3500.view.View;
import cs3500.controller.IController;

/**
 * The main in the intersection of the model, view and controller. This is designed to run three
 * versions of the model controller, one that involves GUI, the other with terminal user input and
 * the last dealing with script files.
 */
public class Main {
  /**
   * The main method delegated between the three versions based off the first word in the command
   * arguments.
   * @param args the command arguments that will determine which version to run.
   */
  public static void main(String[] args) {
    ICollage model = new Collage();
    if (args.length == 0) {
      GUIView.setDefaultLookAndFeelDecorated(false);
      GUIView frame = new GUIView();
      frame.setVisible(true);
      ImageIcon holyImage = new ImageIcon("res/luffy-icon.jpg");
      frame.setIconImage(holyImage.getImage());
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      ControllerGUI controllerGUI = new ControllerGUI(model, frame);
      controllerGUI.setView(frame);
    } else if (args[0].equals("-text")) {
      CollageView textView = new View(model, System.out);
      Readable rd = new InputStreamReader(System.in);
      IController controller = new Controller(model, rd, textView);
      controller.apply();
    } else if (args[0].equals("-file")) {
      Reader reader = null;
      try {
        reader = new FileReader(args[1]);
        Scanner scan = new Scanner(reader);
        while (scan.hasNextLine()) {
          System.out.println(scan.nextLine());
        }
      } catch (FileNotFoundException e) {
        throw new RuntimeException(e);
      }
    }
  }
}