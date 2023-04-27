package cs3500.view;

import java.io.IOException;

/**
 * The interface for a collage view. This interface servers as way to observe the state of a collage
 * and serves a simple view for the user.
 * It transmits messages to the controller server.
 */
public interface CollageView {

  /**
   * Renders a message to the controllers.
   * @param message the String representation of the message to be rendered.
   * @throws IOException if there is an error rendering the message.
   */
  void renderMessage(String message) throws IOException;
}
