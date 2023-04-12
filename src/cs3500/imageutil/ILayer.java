package cs3500.imageutil;

import cs3500.filter.IBlending;
import cs3500.filter.IFilter;

public interface ILayer {
  String getName();
  double getAlpha();
  void setRGBA(IRGBA[][] pixels);
  void setFilter(IFilter filter);
  IRGBA[][] getFilter(IRGBA[][] filtered);
  void setBlend(IBlending filter);
  IBlending getBlend();
  IFilter getFilter();
  IRGBA[][] visualize();
  void addImage(IPPMUtil imgName, int yOffset, int xOffset);

}
