import java.io.IOException;

public class View implements CollageView {

  private final Collage model;

  private final Appendable out;


  public View(Collage model) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    this.model = model;
    this.out = System.out;
  }

  public View(Collage model, Appendable out) {
    if ((model == null) || (out == null)) {
      throw new IllegalArgumentException("Model and appendable must be non null");
    }
    this.model = model;
    this.out = out;
  }

  @Override
  public void renderMessage(String message) {
    if (message == null) {
      throw new IllegalArgumentException("Message cannot be null");
    }
    try {
      out.append(message);
    } catch (IOException e) {
      throw new IllegalArgumentException("Could not render");
    }
  }
}
