public class FilterRed implements IFilter {



  public RGBA apply(RGBA rgba) {
    return new RGBA(0, rgba.getGreen(), 0, rgba.getAlpha());
  }

}
