package cs3500.Filter;

import cs3500.ImageUtil.RGBA;

public class FilterGreen implements IFilter {

  @Override
  public RGBA apply(RGBA rgba) {
    return new RGBA(0, rgba.getGreen(), 0, rgba.getAlpha());
  }

  @Override
  public String toString() {
    return "Filter.FilterGreen";
  }
}



