package cs3500.view;

import cs3500.controller.Commands;

/**
 * This is the interface used for observing the GUI component of the programs.
 * Handles giving access to commands from the model to the gui class.
 */
public interface GUI {

  /**
   * This the method used to access the command made from the model to be used in
   * the GUI class, while preserving a level of security.
   * @param cmd the commands to be used in the GUI class.
   */
  void addCommands(Commands cmd);

}
