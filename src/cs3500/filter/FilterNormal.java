package cs3500.filter;

import cs3500.imageutil.RGBA;

/**
 * A filter that does not change a pixel.
 * Implements the IFilter interface.
 */
public class FilterNormal implements IFilter {

  @Override
  public RGBA apply(RGBA input) {
    return new RGBA(input.getRed(), input.getGreen(), input.getBlue(), input.getAlpha());
  }

  @Override
  public String toString() {
    return "Filter.FilterNormal";
  }

}
