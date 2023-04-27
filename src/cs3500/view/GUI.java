package cs3500.view;

import java.awt.Image;
import cs3500.controller.Commands;

/**
 * This is the interface used for observing the GUI component of the programs.
 * The GUI class gives more detailed to what the whole purpose of the GUI is.
 * Handles giving access to commands from the model to the gui class.
 */
public interface GUI {

  /**
   * This the method used to access the command made from the model to be used in
   * the GUI class, while preserving a level of security.
   * @param cmd the commands to be used in the GUI class.
   */
  void addCommands(Commands cmd);

  /**
   * This method is used after a command that has been executed from the model is called to the GUI.
   * It refreshes the GUI to display the most update image after any modifications have been made
   * to the layer.
   *
   * @param image the given image being updated.
   */
  void refreshImage(Image image);

  /**
   * The method refresh the GUI after a command has been called to the GUI. This ensures that the
   * GUI is updated and reflected the correct information
   */
  void refresh();
}
