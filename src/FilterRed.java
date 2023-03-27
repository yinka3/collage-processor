public class FilterRed implements IFilter  {


  public RGBA apply(RGBA main) {
    return new RGBA(main.setRed(main.getRed()), 0, 0, main.setAlpha(main.getAlpha()));
  }

}
