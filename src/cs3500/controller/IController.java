package cs3500.controller;

/**
 * Represents a controller a user can use to manipulate a collage. This can be either through
 * a terminal user interface or a graphical user interface.
 */
public interface IController {

  /**
   * Applies a command to the collage to be executed.
   */
  void apply();

}
