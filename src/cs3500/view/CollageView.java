package cs3500.view;

import java.io.IOException;

/**
 * An interface for a collage view, a way to observe the state of a collage.
 */
public interface CollageView {

  /**
   * Renders a message to the controllers.
   * @param message the String representation of the message to be rendered.
   * @throws IOException if there is an error rendering the message.
   */
  void renderMessage(String message) throws IOException;
}
