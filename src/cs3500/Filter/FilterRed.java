package cs3500.Filter;

import cs3500.ImageUtil.RGBA;

public class FilterRed implements IFilter {

  public RGBA apply(RGBA input) {
    return new RGBA(input.getRed(), 0, 0 , 0);
  }

  @Override
  public String toString() {
    return "Filter.FilterRed";
  }
}
