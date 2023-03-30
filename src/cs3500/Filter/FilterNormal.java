package cs3500.Filter;

import cs3500.ImageUtil.RGBA;

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
