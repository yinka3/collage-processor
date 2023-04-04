package cs3500.Filter;

import cs3500.ImageUtil.*;

public interface IBlending {
  RGBA apply(RGBA rgba1, RGBA rgba2);

  @Override
  String toString();
}
