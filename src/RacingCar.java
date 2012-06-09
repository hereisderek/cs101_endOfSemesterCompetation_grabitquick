import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class RacingCar {
 /*
  * public static final Rectangle RACING_INFO_GAME_AREA = new Rectangle(0, 0,
  * 500, 50); public static final Rectangle RACING_GAME_AREA = new
  * Rectangle(0, 50, 500, 700);
  * 
  * telnet towel.blinkenlights.nl cp
  * /media/DATA/program/android/source/.repo/repo/repo
  */
 public static final Rectangle RACING_GAME_AREA = A3Constants.RACING_GAME_AREA;
 public static final Rectangle RACING_INFO_GAME_AREA = A3Constants.RACING_INFO_GAME_AREA;
 private static final int height = (int) RACING_GAME_AREA.getHeight(); // 700
 private static final Color BACKGROUND_COLOUR = Color.BLUE;
 public static final int CELL_SIZE = A3Constants.CELL_SIZE;
 private static final int totalTime = 80; // second
 private static final int startSpeed = 3;// Second / screen
 private static final int endSpeed = 1;// Second / screen
 private static final int stageLastFor = 5; // second for every change speed
 private static final double diffOfEverySecond = (startSpeed - endSpeed)
   / (double) totalTime; // 40plx /
 private static final Color[] COLOURS = { new Color(224, 32, 32),
   new Color(145, 32, 224), new Color(145,0,225),
   new Color(255, 0, 255), new Color(27, 234, 56),
   new Color(176, 228, 53), new Color(255, 252, 29),
   new Color(255, 138, 0), new Color(95,225,0) };
 private static final int t1TimerToEaserEgg = 3;
 private RCJFrame rCJFrame;
 private boolean ifGotAward;
 private String award;

 // t1 to countDown to start game, t2 to count time during the game

 // private BandOfBlock bandOfBlock;
 public RacingCar() {
  // System.out.println("Inside of RacingCar");
  // bandOfBlock = new BandOfBlock();
  award = new String("failed");
  ifGotAward = false;
  rCJFrame = new RCJFrame(3 + A3Constants.JFRAME_AREA_WIDTH, 0, 500, 750);
 }

 private void reset() {

 }
 public boolean ifAward() {
  return ifGotAward;
 }
 public String getAward(){
  return(award);
 }
 
 public void openCMDLine() {
  this.openCMDLine("telnet towel.blinkenlights.nl");
 }
 public void openCMDLine(String cmd) {
  String osName = System.getProperty("os.name");
  try {
   if (osName.startsWith("Windows")) {
    String command = "cmd /t:f4 /c  start " + cmd;
    Runtime rt = Runtime.getRuntime();
    Process pr = rt.exec(command);
   } else {
    try {
     Runtime r = Runtime.getRuntime();
     cmd = "xterm -e " + cmd + " -4";
     // String myScript =
     // "xterm -e telnet -4 towel.blinkenlights.nl";
     // String[] cmdArray = {"echo hello world && " ,
     // "ping 127.1 -c 5 >> a && ", myScript + " ; le_exec"};
     r.exec(cmd).waitFor();
    } catch (InterruptedException ex) {
     ex.printStackTrace();
    } catch (IOException ex) {
     ex.printStackTrace();
    }
   }
  } catch (Exception e) {
   e.toString();
  }
 }

 public void pullTheExitPlug() {
  // System.out.println("pullTheExitPlug been called");
  rCJFrame.pullThePlug();
  // pullThePlug();
  rCJFrame.setVisible(false);

 }

 private class RCJFrame extends JFrame implements WindowListener {
  private CRJPanel frameContent;

  public RCJFrame(int x, int y, int width, int height) {
   setTitle("Welcome to car race field: ");
   setLocation(x, y);
   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   frameContent = new CRJPanel();
   Container visibleArea = getContentPane();
   visibleArea.add(frameContent);
   frameContent.setPreferredSize(new Dimension(width, height));
   // frameContent.setLayout(new GridLayout(3, 2));
   pack();
   frameContent.requestFocusInWindow();
   setVisible(true);
  }

  public void pullThePlug() {
   frameContent.pullThePlug();
   this.setVisible(false);
  }
  // public void pullThePlug() {
  // WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
  // Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
  // }

  @Override
  public void windowActivated(WindowEvent e) {
   // TODO Auto-generated method stub
   
  }

  @Override
  public void windowClosed(WindowEvent e) {
   // TODO Auto-generated method stub
   this.windowClosing(e);
  }

  @Override
  public void windowClosing(WindowEvent e) {
   // TODO Auto-generated method stub
   System.out.println("Window is closing");
   pullTheExitPlug();
  }

  @Override
  public void windowDeactivated(WindowEvent e) {
   // TODO Auto-generated method stub
   
  }

  @Override
  public void windowDeiconified(WindowEvent e) {
   // TODO Auto-generated method stub
   
  }

  @Override
  public void windowIconified(WindowEvent e) {
   // TODO Auto-generated method stub
   
  }

  @Override
  public void windowOpened(WindowEvent e) {
   // TODO Auto-generated method stub
   
  }
 }

 private class CRJPanel extends JPanel implements ActionListener,
   KeyListener {
  private Blocks[] blocks;
  private int theLine; // y position of the line
  private boolean gameHasEnded;
  private double ySpeed;
  private Timer t1, t2;
  // private int t2Interval;
  private int t1Counter, t2Counter;
  private Hero2 hero;
  private int blocksCounter;

  public CRJPanel() {
   blocks = new Blocks[900];
   blocksCounter = 0;

   // blocks[0].initBlock();
   hero = new Hero2();
   theLine = (int) RACING_GAME_AREA.getY();
   ySpeed = 0;
   addKeyListener(this);
   setBackground(Color.BLUE);
   gameHasEnded = false;
   t1Counter = 5;
   t2Counter = 0;
   // blocks = new Blocks[300];
   t1 = new Timer(1000, this);
   t2 = new Timer(10, this);
   addKeyListener(this);
   // t2Interval = 100;
   t1.start();
   repaint();
  }

  public void pullThePlug() {
   // System.out.println("CRJPanel.pullThePlug() been called ");
   t1.stop();
   t2.stop();
  }

  public void actionPerformed(ActionEvent e) {
   if (t1Counter <= 0) {
    if (t1Counter == 0) {
     t2.start();
     if (blocks[0] == null)
      blocks[0] = new Blocks();
    }
   }
   if (e.getSource() == t1) {
    t1Counter--;
    // System.out.println("t1Counter: " + t1Counter);
   }
   if (0 == (t1Counter % stageLastFor)) {
    // theLine = 0;
    // the speed in y now (plexs / second)
    // height of a screen / the time of a screen
    // ySpeed = (int) RACING_GAME_AREA.getHeight() / ((startSpeed +
    // (t1Counter / stageLastFor) * diffOfEveryStage) /
    // t2.getDelay()) ;
    ySpeed = RACING_GAME_AREA.getHeight()
      / (startSpeed + t1Counter * diffOfEverySecond);
   }
   if (e.getSource() == t2) {
    t2Counter++;
    theLine += ySpeed / (1000 / t2.getDelay());
    for (int i = 0; i < blocks.length; i++) {
     if (blocks[i] != null) {
      if (! blocks[i].moveY( (int)(ySpeed / (1000 / t2.getDelay())), hero)){
       // hits
       t1.stop();
       t2.stop();
       award = new String("empty");
       ifGotAward = false;
       JOptionPane.showMessageDialog(null, "What a pity, you failed! \n     Time survived: " + -t1Counter + "     Final Speed: " + (int)ySpeed);
       pullTheExitPlug();
      }
     }
    }
    // System.out.println();
   }
   if (t2Counter % 5 == 0
     && Math.random() >= 0.2
     && blocks[blocksCounter] != null
     && blocks[blocksCounter].getY() > RACING_GAME_AREA.getY()
       + 3 * CELL_SIZE) {
    blocks[++blocksCounter] = new Blocks();
    // TODO: comment next line
    System.out.println("blocksCounter: " + blocksCounter);
   }
   //: change the time here
   if (-totalTime == t1Counter && !ifGotAward) {
    JOptionPane pane = new JOptionPane(
      "Congratulations: \n\n     you made it for 100 Seconds, you got a award: good racer\n     and a ascii video to watch: Star War");
    // pane.showInternalMessageDialog(this, "information",
    // "information", JOptionPane.INFORMATION_MESSAGE);; //
    // Configure
    
    award = new String("goodRacer! Time survived: " + -t1Counter + "  Final Speed: " + ySpeed);
    JDialog dialog = pane
      .createDialog(this,
        "RACING CAR: Congratulation, you alived for " + -t1Counter + " seconds");
    dialog.show();
    // System.out.println("this: " + this.toString() + "\nSuper: " +
    // super.toString());
    
    if (!ifGotAward)
     openCMDLine();
    ifGotAward = true;
    pullTheExitPlug();
   }
   /*
    * if (e.getSource() == t2) { //isDrawALine = true; for (int
    * i = 0; i < theLines.length; i++){ if (theLines[i] == null){
    * theLines[i] = new
    * Rectangle(0,(int)RACING_GAME_AREA.getY(),(int)RACING_GAME_AREA
    * .getWidth(), (int)RACING_GAME_AREA.getY()); } } }
    */
   repaint();
  }

  public void paintComponent(Graphics g) {
   g.setFont(new Font("TIMES", Font.CENTER_BASELINE, 20));
   g.setColor(BACKGROUND_COLOUR);
   g.fillRect((int) RACING_GAME_AREA.getX(),
     (int) RACING_GAME_AREA.getY(),
     (int) RACING_GAME_AREA.getWidth(),
     (int) RACING_GAME_AREA.getHeight());
   g.setColor(Color.white);
   g.fillRect((int) RACING_INFO_GAME_AREA.getX(),
     (int) RACING_INFO_GAME_AREA.getY(),
     (int) RACING_INFO_GAME_AREA.getWidth(),
     (int) RACING_INFO_GAME_AREA.getHeight());

   // draw the lines
   if (theLine >= (int) RACING_GAME_AREA.getHeight()
     + RACING_GAME_AREA.getY())
    theLine = (int) RACING_GAME_AREA.getY();
   else if (theLine != 0) {
    g.setColor(Color.orange);
    g.fillRect(0, theLine, (int) RACING_GAME_AREA.getWidth(), 2);
   }
   // draw blocks
   for (int i = 0; i <= blocksCounter; i++) {
    if (blocks[i] != null) {
     blocks[i].draw(g);
    }
   }
   hero.draw(g);

   /*
    * for (int i = 0; i < theLines.length; i++) { if (theLines[i] !=
    * null) { g.setColor(Color.orange); g.fillRect((int)
    * theLines[i].getX(), (int) theLines[i].getY(), (int)
    * RACING_GAME_AREA.getWidth(), 2);
    * System.out.println("Draw a line: " + theLines[i].toString());
    * g.drawString("Draw a line: " + theLines[i].toString(), 0, 70); }
    * }
    */
   g.setColor(Color.white);
   g.fillRect(0, 0, (int)RACING_INFO_GAME_AREA.getWidth(), (int)RACING_INFO_GAME_AREA.getHeight());
   if (t1Counter >= 0) {
    g.setColor(Color.BLUE);
    if (t1Counter == 0) {
     g.drawString("ACTION !!!", 125, 40);
     // t2 = new Timer(1000, this);
     t2.start();
    } else {
     g.drawString("to start the game: " + t1Counter, 125, 40);
     // (int) (RACING_INFO_GAME_AREA.getX() +
     // (RACING_INFO_GAME_AREA.getWidth() - 50)),
     // (int)RACING_GAME_AREA.getY());
    }
   }
   if (t1Counter < 0 && (-t1Counter) < 200) {
    g.setColor(Color.BLUE);
    g.drawString("Time Passed: " + (-t1Counter)
      + " current Speed = " + (int) ySpeed, 0, 40);
   }
  }

  @Override
  public void keyPressed(KeyEvent arg0) {
   if (t2.isRunning()) {
    switch (arg0.getKeyCode()) {
    case KeyEvent.VK_LEFT:
     hero.moveX(-CELL_SIZE / 4);
     break;
    case KeyEvent.VK_RIGHT:
     hero.moveX(CELL_SIZE / 4);
     break;
    case KeyEvent.VK_UP:
     ySpeed +=  (ySpeed / (1000 / t2.getDelay()));
     repaint();
     break;
    default:
     break;
    }
   }

  }

  @Override
  public void keyReleased(KeyEvent arg0) {
   // TODO Auto-generated method stub

  }

  @Override
  public void keyTyped(KeyEvent arg0) {
   // TODO Auto-generated method stub
   this.keyPressed(arg0);
  }

  class Hero2 extends Hero {
   private Rectangle area;
   private int distence = CELL_SIZE / 2;

   public Hero2() {
    area = new Rectangle(
      (int) ((RACING_GAME_AREA.getWidth() - CELL_SIZE) / 2),
      (int) (RACING_GAME_AREA.getHeight()
        + RACING_GAME_AREA.getY() - CELL_SIZE),
      CELL_SIZE, CELL_SIZE);
    // System.out.println(area.toString());
   }
   public Rectangle getArea(){
    return area;
   } 

   public void moveX(int xValue) {
    if (!((area.getX() < 0 && xValue < 0) || (area.getX()
      + CELL_SIZE > RACING_GAME_AREA.getWidth() && xValue > 0)))
     area.setLocation((int) area.getX() + xValue,
       (int) area.getY());
   }

   public void draw(Graphics g) {
    super.drawHero(g, (int) area.getX(), (int) area.getY());
   }
  }

  class Blocks {
   private Color color;
   private Rectangle rectangle;
   private boolean isVisible;

   Blocks() {
    color = COLOURS[(int) (Math.random() * COLOURS.length)];
    this.initBlock();
    this.isVisible = true;
   }

   public double getY() {
    return (int) rectangle.getY();
   }

   private Rectangle getARandomRectangle() {
    Rectangle newRect = new Rectangle();
    do {
     int height = getAHeight();
     newRect = new Rectangle(
       (int) (Math.random() <= 0.8 ? 
           (Math.random() * 300 + 100): 
           (Math.random() * 400 - 200)),
         -height, 
         (int) (Math.random() * 100 + 150),
         height);
    } while (true && !((newRect.getX() > 1.3 * CELL_SIZE) || (RACING_GAME_AREA
      .getWidth() - (newRect.getX() + newRect.getWidth()) > 1.3 * CELL_SIZE)));
    // RACING_GAME_AREA.getWidth() - newRect.getX() -
    // newRect.getWidth() > CELL_SIZE));
    return newRect;
   }

   private boolean rectangleOutOfBorder() {
    // TODO:!
    if (rectangle.getX() > RACING_GAME_AREA.getCenterX()
      + RACING_GAME_AREA.getHeight()) {
     this.isVisible = false;
     return true;
    } else
     return false;
   }

   private int getAHeight() {
    if (Math.random() <= 0.8) {
     return ((int) (Math.random() * 50 + 50));
    } else {
     return ((int) (Math.random() * 50 + 100));
    }
   }

   public boolean moveY(int y, Hero2 hero) {
    rectangle.translate(0, y);
    // hits
    if (rectangle.intersects(hero.getArea()))
     return false;
    else
     return true;
   }

   public void initBlock() {
    rectangle = getARandomRectangle();
   }

   public boolean getVisible() {
    return this.isVisible;
   }

   public void setVisible(boolean b) {
    this.isVisible = b;
   }

   public void draw(Graphics g) {
    g.setColor(color);
    g.fillRect((int) rectangle.getX(), (int) rectangle.getY(),
      (int) rectangle.getWidth(), (int) rectangle.getHeight());
   }
  }
 }
}
