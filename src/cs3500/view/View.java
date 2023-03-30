package cs3500.view;

import java.io.IOException;

import cs3500.model.ICollage;

public class View implements CollageView {

  ICollage model;

  Appendable out;


  public View(ICollage model, Appendable out) {
    if ((model == null) || (out == null)) {
      throw new IllegalArgumentException("Model and appendable must be non null");
    }
    this.model = model;
    this.out = out;
  }

  @Override
  public void renderMessage(String message) throws IOException {
    if (message == null) {
      throw new IllegalArgumentException("Message cannot be null");
    }
    try {
      out.append(message).append("\n");
    } catch (IOException e) {
      throw new IOException(e.getMessage());
    }
  }
}
