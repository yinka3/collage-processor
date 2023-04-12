package cs3500.filter;


import cs3500.imageutil.IRGBA;
import cs3500.imageutil.RGBA;


/**
 * This interface defines the public methods for a filter that uses and changes only one pixel.
 * This interface will be used through the layer class.
 */
public interface IFilter {

  /**
   * Applies a filter to a single RGBA input.
   * @param input rgba the filter is being applied to.
   * @return the new RGBA with the filter applied.
   */
  IRGBA apply(IRGBA input);

  /**
   * Creates a representation of a filter.
   * @return a string representation of the filter.
   */
  @Override
  String toString();
}
