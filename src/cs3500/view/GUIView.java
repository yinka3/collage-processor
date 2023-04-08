package cs3500.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import cs3500.controller.Commands;

/**
 * This class represents the Graphical User Interface that allows for the user to visually see
 * all the changes their inputs make in the program.
 * The class extends the JFrame class which allows for frame pop up, and the class also
 * implements GUI, CollageView, and ActionLister interface.
 * ActionListen allows for the action for buttons to have an effect in the GUI.
 */
public class GUIView extends JFrame implements GUI, CollageView, ActionListener {

  private Commands commands;
  private final JTextField firstName;
  private final JTextArea loadName;
  private final JLabel imageLabel;
  private final JLabel radioDisplay;
  private final JLabel fileOpenDisplay;
  private final JLabel fileSaveDisplay;
  private final JLabel fileSaveDisplay2;
  private final Appendable appendable;
  JPanel mainPanel;
  JPanel dialogBoxesPanel;
  private final JTextArea viewMessages;


  /**
   * The main method that handles all the components for the gui.
   */
  public GUIView() {
    setTitle(" My mother-freaking image processor ");
    setSize(1080, 720);
    mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout(10, 10));
    JScrollPane mainScrollPane = new JScrollPane(mainPanel);
    mainPanel.setBackground(Color.darkGray);
    add(mainScrollPane);


    dialogBoxesPanel = new JPanel();
    dialogBoxesPanel.setBorder(BorderFactory.createTitledBorder("Dialog boxes"));
    dialogBoxesPanel.setLayout(new BoxLayout(dialogBoxesPanel, BoxLayout.PAGE_AXIS));
    mainPanel.add(dialogBoxesPanel);
    firstName = new JTextField();
    firstName.setBorder(BorderFactory.createTitledBorder("Layer Name"));
    firstName.setMaximumSize(new Dimension(500, 50));

    JPanel radioPanel = new JPanel();
    radioPanel.add(firstName);
    radioPanel.add(Box.createVerticalGlue());
    radioPanel.setBorder(BorderFactory.createTitledBorder("Image Processing Commands"));

    radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.PAGE_AXIS));

    JRadioButton[] radioButtons = new JRadioButton[12];
    ButtonGroup rGroup1 = new ButtonGroup();

    //selection of buttons for filters
    radioButtons[0] = new JRadioButton("Red-Component");
    radioButtons[1] = new JRadioButton("Green-Component");
    radioButtons[2] = new JRadioButton("Blue-Component");
    radioButtons[3] = new JRadioButton("BrightenByIntensity");
    radioButtons[4] = new JRadioButton("BrightenByLuma");
    radioButtons[5] = new JRadioButton("BrightenByValue");
    radioButtons[6] = new JRadioButton("DarkenByIntensity");
    radioButtons[7] = new JRadioButton("DarkenByLuma");
    radioButtons[8] = new JRadioButton("DarkenByValue");
    radioButtons[9] = new JRadioButton("BlendingBrighten");
    radioButtons[10] = new JRadioButton("BlendingDarken");
    radioButtons[11] = new JRadioButton("BlendingDifference");

    for (int i = 0; i < radioButtons.length; i++) {
      radioButtons[i].setActionCommand("RB" + (i + 1));
      radioButtons[i].addActionListener(this);
      rGroup1.add(radioButtons[i]);
      radioPanel.add(radioButtons[i]);
    }

    radioDisplay = new JLabel("Selected Feature : N/A");
    radioPanel.add(radioDisplay);
    mainPanel.add(radioPanel, BorderLayout.LINE_END);
    radioPanel.setPreferredSize(new Dimension(300, 500));
    radioPanel.add(Box.createVerticalGlue());

    //process button
    JButton enter = new JButton("Process");
    enter.setActionCommand("Process");
    enter.addActionListener(this);
    radioPanel.add(enter);

    //message panel for showing progress on layers.
    JPanel messagePanel = new JPanel();
    messagePanel.setBorder(BorderFactory.createTitledBorder("Processes on Layers"));
    messagePanel.setLayout(new GridLayout(1, 2, 5, 5));
    messagePanel.setPreferredSize(new Dimension(250, 500));
    appendable = new StringBuilder();
    String start = "Messages showing the layer name and the filters being applied\n";
    try {
      appendable.append(start);
    } catch (IOException e) {
      //
    }
    viewMessages = new JTextArea();
    viewMessages.setText(appendable.toString());
    viewMessages.setEditable(false);
    viewMessages.setLineWrap(true);
    viewMessages.setWrapStyleWord(true);
    JScrollPane mesgScroll = new JScrollPane(viewMessages);
    messagePanel.add(mesgScroll);
    mainPanel.add(messagePanel, BorderLayout.LINE_START);


    //the panel that deals with all the opening.
    JPanel fileOpenPanel = new JPanel();
    fileOpenPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    dialogBoxesPanel.add(fileOpenPanel);
    JButton fileOpenButton = new JButton("Load PPM Image or Project");
    JButton newProject = new JButton("Create Project");
    newProject.setActionCommand("Create Project");
    newProject.addActionListener(this);
    JButton addLayer = new JButton("Add Layer");
    addLayer.setActionCommand("Add Layer");
    addLayer.addActionListener(this);
    fileOpenPanel.add(newProject);
    fileOpenPanel.add(addLayer);
    loadName = new JTextArea(1, 20);
    loadName.setBorder(BorderFactory.createTitledBorder("Name of Layer in program"));
    fileOpenPanel.add(loadName);
    fileOpenButton.setActionCommand("Load PPM Image or Project");
    fileOpenButton.addActionListener(this);
    fileOpenPanel.add(fileOpenButton);
    fileOpenDisplay = new JLabel("File path");
    fileOpenPanel.add(fileOpenDisplay);
    mainPanel.add(fileOpenPanel, BorderLayout.PAGE_START);


    //the panel that deals with all the save operations.
    JPanel fileSavePanel = new JPanel();
    fileSavePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    dialogBoxesPanel.add(fileSavePanel);
    JButton fileSaveButton = new JButton("Save a ppm file");
    fileSaveButton.setActionCommand("Save a ppm file");
    fileSaveButton.addActionListener(this);
    fileSaveDisplay = new JLabel("File path");
    JButton fileSaveButton2 = new JButton("Save a collage file");
    fileSaveButton2.setActionCommand("Save a collage file");
    fileSaveButton2.addActionListener(this);
    fileSaveDisplay2 = new JLabel("File path");
    mainPanel.add(fileSavePanel, BorderLayout.PAGE_END);
    JPanel optionsDialogPanel = new JPanel();
    optionsDialogPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(optionsDialogPanel);
    JLabel optionDisplay = new JLabel("Save the image luv;)");
    fileSavePanel.add(optionDisplay);
    fileSavePanel.add(fileSaveButton);
    fileSavePanel.add(fileSaveDisplay);
    JLabel optionDisplay2 = new JLabel("Now, Save the project too luv:)");
    fileSavePanel.add(optionDisplay2);
    fileSavePanel.add(fileSaveButton2);
    fileSavePanel.add(fileSaveDisplay2);


    // The image panel that displays the layers
    JPanel imgPanel = new JPanel();
    imgPanel.setBorder(BorderFactory.createTitledBorder("Current Project"));
    imgPanel.setLayout(new GridLayout(1, 0));
    imgPanel.setPreferredSize(new Dimension(300, 500));
    imageLabel = new JLabel();
    JScrollPane imageScrollPane = new JScrollPane(imageLabel);
    imgPanel.add(imageScrollPane);
    mainPanel.add(imgPanel, BorderLayout.CENTER);
  }

  @Override
  public void renderMessage(String message) throws IOException {
    appendable.append(message);
  }


  /**
   * This method allows for the usage for the buttons to do some form of action.
   *
   * @param commands the event to be processed.
   */
  public void actionPerformed(ActionEvent commands) {
    switch (commands.getActionCommand()) {
      case "Create Project":
        String width = JOptionPane.showInputDialog("Give me width");
        String height = JOptionPane.showInputDialog("Give me height");
        this.commands.createProject(height, width);
        try {
          this.appendable.append("A project has been created, with width ").append(width).
                  append(" and height ").append(height).append(" \n");
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
        viewMessages.setText(appendable.toString());
        ImageIcon thumbsUp = new ImageIcon("res/thumps up.jpg");
        JOptionPane.showMessageDialog(GUIView.this,
                new JLabel("You have started a project, now have some fun you idiot.",
                        thumbsUp, JLabel.LEFT), "PROJECT STARTED", JOptionPane.PLAIN_MESSAGE);
        break;
      case "Add Layer":
        ImageIcon monke = new ImageIcon("res/monke2.jpg");
        JOptionPane.showMessageDialog(GUIView.this, new JLabel(monke, JLabel.LEFT),
                "GIVE LAYER", JOptionPane.PLAIN_MESSAGE);
        String layerName = JOptionPane.showInputDialog("Give me layer name");
        this.commands.addLayer(layerName);
        try {
          this.appendable.append("The layer name: ").append(layerName).append(" has been added.\n");
        } catch (IOException e) {
          throw new RuntimeException(e);
        } catch (NullPointerException e) {
          try {
            appendable.append("No Layer is given.\n");
          } catch (IOException ex) {
            //
          }
        }
        viewMessages.setText(appendable.toString());
        break;
      case "Load PPM Image or Project":
        final JFileChooser fchooser = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Image Types", "jpg", "ppm", "png", "jpeg");
        fchooser.setFileFilter(filter);
        int retvalue = fchooser.showOpenDialog(GUIView.this);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fchooser.getSelectedFile();
          fileOpenDisplay.setText(f.getAbsolutePath());
          String heightImage = JOptionPane.showInputDialog("Give Height");
          String widthImage = JOptionPane.showInputDialog("Give Width");
          if (!loadName.getText().equals("")) {
            this.commands.addImageToLayer(loadName.getText(), fileOpenDisplay.getText(),
                    heightImage, widthImage);
            firstName.setEditable(true);
            firstName.setVisible(true);
          }
        }
        break;
      case "Save a ppm file":
        final JFileChooser fchooser2 = new JFileChooser(".");
        int retvalue2 = fchooser2.showSaveDialog(GUIView.this);
        if (retvalue2 == JFileChooser.APPROVE_OPTION) {
          File f = fchooser2.getSelectedFile();
          fileSaveDisplay.setText(f.getAbsolutePath());
          this.commands.saveImage(fileSaveDisplay.getText());
          // try to make this a pop-up instead
        }
        try {
          this.appendable.append("A new image ").append(" has been added saved.").append("\n");
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
        viewMessages.setText(appendable.toString());
        break;
      case "Save a collage file":
        final JFileChooser fchooser3 = new JFileChooser(".");
        int retvalue3 = fchooser3.showSaveDialog(GUIView.this);
        if (retvalue3 == JFileChooser.APPROVE_OPTION) {
          File f = fchooser3.getSelectedFile();
          fileSaveDisplay2.setText(f.getAbsolutePath());
          this.commands.saveProject(fileSaveDisplay2.getText());
          // try to make this a pop-up instead
        }
        try {
          this.appendable.append("A new collage ").append(" has been added saved.").append("\n");
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
        viewMessages.setText(appendable.toString());
        break;
      case "Process":
        String command = radioDisplay.getText().toLowerCase().substring(0,
                radioDisplay.getText().indexOf(' '));
        try {
          switch (command) {
            case "red-component":
              if (!firstName.getText().equals("")) {
                this.commands.setFilter(firstName.getText(), "red-component");
              }
              try {
                this.appendable.append("The layer ").append(firstName.getText()).
                        append(" has been filtered by red-component\n");
              } catch (IOException e) {
                throw new RuntimeException(e);
              }
              viewMessages.setText(appendable.toString());
              break;
            case "green-component":
              if (!firstName.getText().equals("")) {
                this.commands.setFilter(firstName.getText(), "green-component");
              }
              try {
                this.appendable.append("The layer ").append(firstName.getText()).
                        append(" has been filtered by a green-component\n");
              } catch (IOException e) {
                throw new RuntimeException(e);
              }
              viewMessages.setText(appendable.toString());
              break;
            case "blue-component":
              if (!firstName.getText().equals("")) {
                this.commands.setFilter(firstName.getText(), "blue-component");
              }
              try {
                this.appendable.append("The layer ").append(firstName.getText()).
                        append(" has been filtered by a blue-component\n");
              } catch (IOException e) {
                throw new RuntimeException(e);
              }
              viewMessages.setText(appendable.toString());
              break;
            case "brightenbyintensity":
              if (!firstName.getText().equals("")) {
                this.commands.setFilter(firstName.getText(), "brighten-intensity");
              }
              try {
                this.appendable.append("The layer ").append(firstName.getText()).
                        append(" has been brightenbyintensity\n");
              } catch (IOException e) {
                throw new RuntimeException(e);
              }
              viewMessages.setText(appendable.toString());
              break;
            case "brightenbyluma":
              if (!firstName.getText().equals("")) {
                this.commands.setFilter(firstName.getText(), "brighten-luma");
              }
              try {
                this.appendable.append("The layer ").
                        append(firstName.getText()).append(" has been brightenbyluma\n");
              } catch (IOException e) {
                throw new RuntimeException(e);
              }
              viewMessages.setText(appendable.toString());
              break;
            case "brightenbyvalue":
              if (!firstName.getText().equals("")) {
                this.commands.setFilter(firstName.getText(), "brighten-value");
              }
              try {
                this.appendable.append("The layer ").
                        append(firstName.getText()).append(" has been brightenbyvalue\n");
              } catch (IOException e) {
                throw new RuntimeException(e);
              }
              viewMessages.setText(appendable.toString());
              break;
            case "darkenbyintensity":
              if (!firstName.getText().equals("")) {
                this.commands.setFilter(firstName.getText(), "darken-intensity");
              }
              try {
                this.appendable.append("The layer ").
                        append(firstName.getText()).append(" has been darkenbyintensity\n");
              } catch (IOException e) {
                throw new RuntimeException(e);
              }
              viewMessages.setText(appendable.toString());
              break;
            case "darkenbyluma":
              if (!firstName.getText().equals("")) {
                this.commands.setFilter(firstName.getText(), "darken-luma");
              }
              try {
                this.appendable.append("The layer ").append(firstName.getText()).append(" " +
                        "has been darkenbyluma\n");
              } catch (IOException e) {
                throw new RuntimeException(e);
              }
              viewMessages.setText(appendable.toString());
              break;
            case "darkenbyvalue":
              if (!firstName.getText().equals("")) {
                this.commands.setFilter(firstName.getText(), "darken-value");
              }
              try {
                this.appendable.append("The layer ").append(firstName.getText()).append(" " +
                        "has been darkenbyvalue\n");
              } catch (IOException e) {
                throw new RuntimeException(e);
              }
              viewMessages.setText(appendable.toString());
              break;
            case "blendingbrighten":
              if (!firstName.getText().equals("")) {
                this.commands.setBlend(firstName.getText(), "BlendingBrighten");
              }
              try {
                this.appendable.append("The layer ").append(firstName.getText()).append(" " +
                        "has been blendingbrighten\n");
              } catch (IOException e) {
                throw new RuntimeException(e);
              }
              viewMessages.setText(appendable.toString());
              break;
            case "blendingdarken":
              if (!firstName.getText().equals("")) {
                this.commands.setBlend(firstName.getText(), "BlendingDarken");
              }
              try {
                this.appendable.append("The layer ").append(firstName.getText()).append("" +
                        " has been blendingdarken\n");
              } catch (IOException e) {
                throw new RuntimeException(e);
              }
              viewMessages.setText(appendable.toString());
              break;
            case "blendingdifference":
              if (!firstName.getText().equals("")) {
                this.commands.setBlend(firstName.getText(), "BlendingDifference");
              }
              try {
                this.appendable.append("The layer ").append(firstName.getText()).append(
                        " has been blendingdifference\n");
              } catch (IOException e) {
                throw new RuntimeException(e);
              }
              viewMessages.setText(appendable.toString());
              break;
            default:
              this.appendable.append("Not a valid filter");
              viewMessages.setText(appendable.toString());
          }
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
        break;
      case "RB1":
        radioDisplay.setText("Red-Component was selected");
        break;
      case "RB2":
        radioDisplay.setText("Green-Component was selected");
        break;
      case "RB3":
        radioDisplay.setText("Blue-Component was selected");
        break;
      case "RB4":
        radioDisplay.setText("BrightenByIntensity was selected");
        break;
      case "RB5":
        radioDisplay.setText("BrightenByLuma was selected");
        break;
      case "RB6":
        radioDisplay.setText("BrightenByValue was selected");
        break;
      case "RB7":
        radioDisplay.setText("DarkenByIntensity was selected");
        break;
      case "RB8":
        radioDisplay.setText("DarkenByLuma was selected");
        break;
      case "RB9":
        radioDisplay.setText("DarkenByValue was selected");
        break;
      case "RB10":
        radioDisplay.setText("BlendingBrighten was selected");
        break;
      case "RB11":
        radioDisplay.setText("BlendingDarken was selected");
        break;
      case "RB12":
        radioDisplay.setText("BlendingDifference was selected");
        break;
      default:
        try {
          this.appendable.append("Not a valid command.");
        } catch (IOException e) {
          //
        }
        viewMessages.setText(appendable.toString());
    }
  }

  /**
   * This method is used as safe access to the commands from the model to the gui so each button
   * can perform its desired effects.
   * @param cmd the commands to be used in the GUI class.
   */
  @Override
  public void addCommands(Commands cmd) {
    this.commands = cmd;
  }


  public void refresh() {
    validate();
    repaint();
  }

  /**
   * This method is used after a command fom the model is called to the GUI. It refreshes the gui
   * to display the most update image after any modifications to the layer.
   *
   * @param image the image being updated.
   */
  public void refreshImage(Image image) {
    imageLabel.setIcon(new ImageIcon(image));
    validate();
    repaint();
  }
}
