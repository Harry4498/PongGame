import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
public class Paddle extends  Rectangle{
    int id;  // id1 for player1 and id 2 is for player2
    int yVelocity;
    int speed=10;


    // constructor
    Paddle(int x, int y,int PADDLE_WIDTH,int PADDLE_HEIGHT,int id ){
    super (x, y, PADDLE_WIDTH,PADDLE_HEIGHT);// this for the rectangle
    this.id= id;
    }
     // method

        public void keyPressed (KeyEvent e ){
        switch (id){
            case 1: // paddle1
                if (e.getKeyCode()== KeyEvent.VK_Q)  { // if someone pressed W
                    setYDirection(-speed);// up in y axis;
                    move();
                }
                if (e.getKeyCode()== KeyEvent.VK_S)  { // if someone pressed S
                    setYDirection(speed);// down in y axis;
                    move();
                }
                break;

            case 2: // paddle 2
                if (e.getKeyCode()== KeyEvent.VK_UP)  { // if someone pressed W
                    setYDirection(-speed); // up in y axis;
                    move();
                }
                if (e.getKeyCode()== KeyEvent.VK_DOWN)  { // if someone pressed S
                    setYDirection(speed);// down in y axis;
                    move();
                }
                break;
           }

         }
        public void keyReleased ( KeyEvent e){
            switch (id){
                case 1: // paddle1
                    if (e.getKeyCode()== KeyEvent.VK_Q)  {
                        setYDirection(0);//0 because to stop from moving after key is released
                        move();
                    }
                    if (e.getKeyCode()== KeyEvent.VK_S)  {
                        setYDirection(0);//
                        move();
                    }
                    break;

                case 2: // paddle2
                    if (e.getKeyCode()== KeyEvent.VK_UP)  {
                        setYDirection(0);
                        move();
                    }
                    if (e.getKeyCode()== KeyEvent.VK_DOWN)  {
                        setYDirection(0);
                        move();
                    }
                    break;
            }
        }
        public void setYDirection( int yDirection){
        yVelocity=yDirection;
        }
        public  void move(){
            y = y + yVelocity;

        }
        public void draw( Graphics g){
        if (id ==1) // player1
            g.setColor(Color.YELLOW);
        //if ( id==2)
            else // if it is not id ==1 it must be id 2 // 2nd player
            g.setColor(Color.RED);
            g.fillRect(x,y,width,height);// fill the rectangle

        }
}
