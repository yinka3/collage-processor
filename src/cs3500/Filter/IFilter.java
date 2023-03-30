package cs3500.Filter;


import cs3500.ImageUtil.RGBA;

public interface IFilter {

  RGBA apply (RGBA input);

  @Override
  String toString();
}
