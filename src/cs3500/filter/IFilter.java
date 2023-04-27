package cs3500.filter;


import cs3500.imageutil.IRGBA;



/**
 * The main functionality of the interface is used to modify one RGBA component on a low-level
 * that can then be used at a higher level in the layers class.
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
