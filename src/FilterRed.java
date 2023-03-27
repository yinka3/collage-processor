import java.util.ArrayList;

public class FilterRed implements IFilter {


  @Override
  public RGBA apply(RGBA rgba) {
    return new RGBA(rgba.getRed(), 0, 0 , rgba.getAlpha());
  }
}
