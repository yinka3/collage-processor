public class FilterBlue implements IFilter {
  @Override
  public RGBA apply(RGBA rgba) {
    return new RGBA(0, 0, rgba.getBlue(), rgba.getAlpha());
  }
}
