import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.KeyEvent;

   public class GamePannel extends  JPanel implements  Runnable{
    static  final int GAME_WIDTH = 1000;
    static  final int GAME_HEIGHT = (int )(GAME_WIDTH * ( 0.555555));
    static final Dimension SCREEN_SIZE= new Dimension(GAME_WIDTH,GAME_HEIGHT);
    // static because if we have some reason   we multiply constructor/ instances of our game panel class they will share oneGame_width
    // final keyword  prohibit with accidentally modifying the Game_width and another reason is it also make run little faster
    static  final int BALL_DIAMETER= 20;
    static  final int PADDLE_HEIGHT=100;
    static  final int PADDLE_WIDTH=25;
    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    Paddle paddle1; // this is first player
    Paddle paddle2 ; // this is second player
    Ball ball;
    Score score;


    GamePannel(){
    newPaddle();// this will call the newpaddle method;
        newBall();
        score = new Score(GAME_WIDTH,GAME_HEIGHT);// it will take input from the score  class  from the score constructor
        this.setFocusable(true);
        this.addKeyListener(new AL());// AL is the inner class that is actionnnListner
        this.setPreferredSize(SCREEN_SIZE);
        gameThread = new Thread(this);
        gameThread.start();
    }
    // method
    public void newBall(){
    random = new Random();
    ball=new Ball((GAME_WIDTH/2)-(BALL_DIAMETER/2),random.nextInt(GAME_HEIGHT-BALL_DIAMETER),BALL_DIAMETER, BALL_DIAMETER); //Ball will appear in the middle of the X axis;
    }
    public void newPaddle (){
        paddle1 = new Paddle(0,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH, PADDLE_HEIGHT,1);
        paddle2 = new Paddle(GAME_WIDTH-PADDLE_WIDTH,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH, PADDLE_HEIGHT,2);

    }
    public void paint (Graphics g){
        image = createImage(getWidth(),getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this);// this is  to pass j pannel(GamePannel);
    }
    public void draw(Graphics g ){
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);
    }
    public  void move( ){
    paddle1.move();
    paddle2.move();
     ball.move();
    }
    public void checkCollision(){

        // this will  Bounce the ball off top & Window edges
        if ( ball.y<=0){
            ball.setYDirection(-ball.yVelocity); // it will reverse the direction of ball while collision
        }
        if ( ball.y>=GAME_HEIGHT-BALL_DIAMETER){
            // here we consider the ball diameter
            ball.setYDirection(-ball.yVelocity);
        }
        //Bounces ball from the peddle
        if (ball.intersects(paddle1)){
            ball.xVelocity=Math.abs(ball.xVelocity);// turning it in to positive no.
            ball.xVelocity++; // optional for more difficulty
            if(ball.yVelocity>0)
                ball.yVelocity++;// this is for to increase the velocity of ball with every collision with the peddle;
            else
                ball.yVelocity--;
            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }
        // this is for the paddle 2
        if (ball.intersects(paddle2)){
            ball.xVelocity=Math.abs(ball.xVelocity);// turning it in to positive no.
            ball.xVelocity++; // optional for more difficulty
            if(ball.yVelocity>0)
                ball.yVelocity++;// this is for to increase the velocity of ball with every collision with the peddle;
            else
                ball.yVelocity--;
            ball.setXDirection(-ball.xVelocity); // (-)to flip the x velocity
            ball.setYDirection(ball.yVelocity);
        }
        // Stop paddles at window edges
        if (paddle1.y<=0)
            paddle1.y=0;
        if(paddle1.y>=(GAME_HEIGHT-PADDLE_HEIGHT))
            paddle1.y= GAME_HEIGHT-PADDLE_HEIGHT;
        if (paddle2.y<=0)
            paddle2.y=0;
        if(paddle2.y>=(GAME_HEIGHT-PADDLE_HEIGHT))
            paddle2.y= GAME_HEIGHT-PADDLE_HEIGHT;

        // THis section give player 1 point and creates new paddle & ball
        if ( ball.x<=0){
            // it means player2 scores 1 point it mean ball touched to the left boundary
            score.player2++;
            // now let's create new ball & peddle
            newPaddle();
            newBall();
            System.out.println(" Player 2 : "+score.player2);
        }
        if ( ball.x>=GAME_WIDTH-BALL_DIAMETER){
            // it means player2 scores 1 point it mean ball touched to the left boundary
            score.player1++;
            // now let's create new ball & peddle
            newPaddle();
            newBall();
            System.out.println(" Player 1 : "+score.player1);
        }
    }

    public void run (){
// we will create a basic game loop;
        long  lastTime= System.nanoTime();
        double amountOfTicks= 60.0;
        double  ns = 1000000000/amountOfTicks; // ns is nanoseconds
        double delta = 0;
        while (true){ //  while game is running
            long now = System.nanoTime();
            delta += (now - lastTime)/ns;
            lastTime= now;
            if ( delta>=1){
                move();// move all the components
                checkCollision();
                repaint();// repaint everything
                delta--;

            }
        }
    }
    public class AL extends KeyAdapter{
        public void keyPressed(KeyEvent e) {
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
        }
        public void keyReleased(KeyEvent e) {
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }
    }
}

