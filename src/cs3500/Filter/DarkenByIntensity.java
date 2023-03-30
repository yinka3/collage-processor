package cs3500.Filter;

import cs3500.ImageUtil.RGBA;

public class DarkenByIntensity implements IFilter {
  @Override
  public RGBA apply(RGBA input) {
    int r = RGBA.clamp(input.getRed() - input.intensity(input.getRed(), input.getGreen(), input.getBlue()));
    int g = RGBA.clamp(input.getGreen() - input.intensity(input.getRed(), input.getGreen(), input.getBlue()));
    int b = RGBA.clamp(input.getBlue() - input.intensity(input.getRed(), input.getGreen(), input.getBlue()));
    return new RGBA(r, g, b, input.getAlpha());
  }

  @Override
  public String toString() {
    return "Filter.DarkenByIntensity";
  }
}