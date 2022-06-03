import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
public class Gameframe extends  JFrame  {
    // constructor
    GamePannel panel ;
Gameframe(){
    panel = new GamePannel();
    this.add( panel);
    this.setTitle("Ping Pong ");
    this.setResizable(false);
    this.setBackground(Color.white);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.pack(); // The resulting width and height of the window are automatically enlarged
    this.setVisible(true);
    this.setLocationRelativeTo(null);// this for when we run the frame will appear in the middle of the screen



}
}
