import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Scanner;
import javax.swing.*;
import cs3500.controller.Controller;
import cs3500.controller.ControllerGUI;
import cs3500.model.Collage;
import cs3500.model.ICollage;
import cs3500.view.GUIView;
import cs3500.view.View;
import cs3500.controller.IController;


public class Main {
  public static void main(String[] args) {
    ICollage model = new Collage();
    if (args.length == 0) {
      GUIView.setDefaultLookAndFeelDecorated(false);
      GUIView frame = new GUIView();
      frame.setVisible(true);
      ImageIcon holyImage = new ImageIcon("luffy-icon.jpg");
      frame.setIconImage(holyImage.getImage());
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      ControllerGUI controllerGUI = new ControllerGUI(model, frame);
      controllerGUI.setView(frame);
    } else if (args[0].equals("-text")) {
      View textView = new View(model, System.out);
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