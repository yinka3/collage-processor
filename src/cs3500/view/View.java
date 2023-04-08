package cs3500.view;

import java.io.IOException;
import cs3500.model.ICollage;

/**
 * A simple view for the collager, transmits messages and state of collage to an appendable object.
 * Implements the CollageView interface.
 */
public class View implements CollageView {

  private final Appendable out;


  /**
   * Constructs a view.
   * @param model the collager being viewed.
   * @param out the appendable object the view transmits to.
   */
  public View(ICollage model, Appendable out) {
    if ((model == null) || (out == null)) {
      throw new IllegalArgumentException("Model and appendable must be non null");
    }
    this.out = out;
  }

  /**
   * Renders a message to the view.
   * @param message message being transmitted to the view.
   * @throws IOException if the message cannot be rendered.
   */
  @Override
  public void renderMessage(String message) throws IOException {
    if (message == null) {
      throw new IllegalArgumentException("Message cannot be null");
    }
    try {
      out.append(message).append("\n");
    } catch (IOException e) {
      throw new IOException(e.getMessage());
    }
  }
}
