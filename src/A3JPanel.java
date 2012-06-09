/*
 * COMMENT
 * qzhu497
 * 4pm
 */

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

public class A3JPanel extends JPanel implements KeyListener, ActionListener,
		MouseListener {
	public static final Color BACKGROUND_COLOUR = Color.GREEN;
	public static final Color LAUNCH_PAD_COLOUR = new Color(220, 66, 255);
	public static final Rectangle ABOVE_GAME_AREA = A3Constants.ABOVE_GAME_AREA;
	public static final Rectangle BELOW_GAME_AREA = A3Constants.BELOW_GAME_AREA;
	public static final Rectangle LEFT_OF_GAME_AREA = A3Constants.LEFT_OF_GAME_AREA;
	public static final Rectangle RIGHT_OF_GAME_AREA = A3Constants.RIGHT_OF_GAME_AREA;
	public static final int CELL_SIZE = A3Constants.CELL_SIZE;
	public static final Rectangle GAME_AREA = A3Constants.GAME_AREA;
	public static final Rectangle LAUNCH_PAD_AREA = A3Constants.LAUNCH_PAD_AREA;
	public static final Point CENTRE_POINT = A3Constants.CENTRE_POINT;
	public static final Point INFORMATION_POSITION1 = A3Constants.INFORMATION_POSITION1;
	public static final int JFRAME_AREA_WIDTH = A3Constants.JFRAME_AREA_WIDTH;
	public static final int JFRAME_AREA_HEIGHT = A3Constants.JFRAME_AREA_HEIGHT;
	public static final int[] MONEY_AMOUNTS = A3Constants.MONEY_AMOUNTS;
	public static Color[] MYCOLOR;
	public static final int UP = A3Constants.UP;
	public static final int DOWN = A3Constants.DOWN;
	public static final int LEFT = A3Constants.LEFT;
	public static final int RIGHT = A3Constants.RIGHT;

	private BlockOfCells blockOfCells = new BlockOfCells();
	private Hero hero = new Hero();
	private Timer t;
	private BandOfVillains bandOfVillains = new BandOfVillains(hero);
	private static int sumMoney;
	private int timerCounter;
	private boolean gameHasEnded;
	private boolean showingTitleScreen;
	private Point mousePoint;
	private Submit submit;
	private RacingCar racingCar;
	private String racingCar_award;
	private String whosyourdaddy;
	private MousePressed[] mousePressed;
	private boolean ifGameStarted;
	private JButton startGameButton;
	private JEditorPane about;

	// private WelcomeJFrame welcome;

	public A3JPanel() {
		setBackground(BACKGROUND_COLOUR);
		int colorCounter = 0;
		MYCOLOR = new Color[729];
		for (int i = 0; i <= 255; i += 30) {
			for (int j = 0; j <= 255; j += 30) {
				for (int k = 0; k <= 255; k += 30) {
					MYCOLOR[colorCounter] = new Color(i, j, k);
					colorCounter++;
				}
			}
		}
		// hero = new Hero();
		reset();
		timerCounter = 0;
		addKeyListener(this);
		addMouseListener(this);
		t = new Timer(125, this);
		
		// TODO:
		// i really don't got enough time to figure how to display a web inside this JEditorPane...
		
		about = new JEditorPane();
		about.setEditable(false);

		try {
			about.setPage("http://ilovederek.crocserve.com/grabitquick/intro.html");
		} catch (IOException e) {
			about.setContentType("text/html");
			about.setText("<html>Could not load http://www.oreilly.com </html>");
		}
		// Put the editor pane in a scroll pane.
		JScrollPane editorScrollPane = new JScrollPane(about);
		editorScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		editorScrollPane.setPreferredSize(new Dimension(250, 145));
		editorScrollPane.setMinimumSize(new Dimension(10, 10));
		JScrollPane scrollPane = new JScrollPane(about);   
		
		add(scrollPane);
		// http://ilovederek.crocserve.com/grabitquick/into.php
		
		setLayout(null);
		startGameButton = new JButton("Start The Game");
		add(startGameButton);
		startGameButton.setFocusable(false);
		startGameButton.setBounds((JFRAME_AREA_WIDTH - 300) / 2,
				JFRAME_AREA_HEIGHT - 250, 300, 50);
		startGameButton.addActionListener(this);
		
		
		Submit.openURL("http://ilovederek.crocserve.com/grabitquick/into.php");
	}

	// -------------------------------------------------------
	// Initialise everything, ready for a new game.
	// -------------------------------------------------------
	private void reset() {
		// System.out.println("Inside reset()");
		ifGameStarted = false;
		mousePressed = new MousePressed[999];
		hero = new Hero();
		timerCounter = 0;
		if (t != null)
			t.stop();
		gameHasEnded = false;
		sumMoney = 0;
		showingTitleScreen = true;
		mousePoint = new Point(0, 0);
		bandOfVillains = new BandOfVillains(hero);
		blockOfCells = new BlockOfCells();
		repaint();
	}

	// -------------------------------------------------------
	// Handle KeyEvents
	// -------------------------------------------------------
	@Override
	public void keyPressed(KeyEvent arg0) {
		if (!showingTitleScreen) {
			switch (arg0.getKeyCode()) {
			case KeyEvent.VK_UP:
			case KeyEvent.VK_W:
				// System.out.println("UP KEY Has Been Pressed");
				hero.setCurrentDirection(UP);
				break;
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_S:
				hero.setCurrentDirection(DOWN);
				break;
			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_A:
				hero.setCurrentDirection(LEFT);
				break;
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_D:
				hero.setCurrentDirection(RIGHT);
				break;
			case KeyEvent.VK_ESCAPE:
				reset();
				break;
			}
			if ("" == null) {
				System.out.println(" '' == null");
			}
			// prepare to enter "whosyourdaddy" mode
			if (whosyourdaddy == null && arg0.getKeyCode() == KeyEvent.VK_UP) {
				whosyourdaddy = "u";
			} // else if()
			if (whosyourdaddy != null) {
				switch (arg0.getKeyCode()) {
				case KeyEvent.VK_UP:
					// System.out.println("UP KEY Has Been Pressed");
					whosyourdaddy += "u";
					break;
				case KeyEvent.VK_DOWN:
					whosyourdaddy += "d";
					break;
				case KeyEvent.VK_LEFT:
					whosyourdaddy += "l";
					break;
				case KeyEvent.VK_RIGHT:
					whosyourdaddy += "r";
					break;
				case KeyEvent.VK_B:
					whosyourdaddy += "b";
					break;
				case KeyEvent.VK_A:
					whosyourdaddy += "a";
					break;
				}
			}

		} else {
			if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
				showingTitleScreen = false;
				startGameButton.setVisible(false);
			}
			// TODO: only for test Here!
			/*
			 * else if (arg0.getKeyCode() == KeyEvent.VK_UP){ racingCar = new
			 * RacingCar(); racingCar_award = racingCar.getAward(); }else if
			 * (arg0.getKeyCode() == KeyEvent.VK_DOWN){
			 * racingCar.pullTheExitPlug(); }
			 */
		}
		// System.out.println("---------------key pressed!");
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

	// -------------------------------------------------------
	// Handle ActionEvents
	// -------------------------------------------------------
	@Override
	public void actionPerformed(ActionEvent arg0) {
		timerCounter++;
		if (whosyourdaddy != null && !ifGameStarted
				&& whosyourdaddy.endsWith("uuddlrlrba")) {
			// TODO:
			ifGameStarted = true;
			t.stop();
			racingCar = new RacingCar();

		}
		if (sumMoney == 45) {
			t.stop();
			submit = new Submit(125 * timerCounter,
					bandOfVillains.getNumberOfVillainsBeenShoot(),
					((racingCar == null) ? "empty" : racingCar.getAward()));
			// sumMoney = 0;
			reset();
			repaint();
			// submit.start(timerCounter);
		}
		/*
		 * if (sumMoney == 45){ reset(); System.err.println("WTF!");
		 * //submit.start(timerCounter); if (sumMoney == 45)
		 * System.err.println("WTF!!!!!!"); }
		 */
		if ((timerCounter % 2) == 0) {
			hero.move();
			// timerCounter = 0;
		}
		if (Math.random() > 0.6) {
			bandOfVillains.addAVillain();
		}
		sumMoney += blockOfCells.check007PositionAndGetMoney(new Point(
				(int) (hero.getArea().getX() + 0.5 * A3Constants.CELL_SIZE),
				(int) (hero.getArea().getY() + 0.5 * A3Constants.CELL_SIZE)));
		blockOfCells.changeMoneyPosition();
		bandOfVillains.moveVillains(hero);
		if (bandOfVillains.enemyHitsHero(hero)) {
			t.stop();
			// JOptionPane.showInternalMessageDialog(this,
			// "information","information", JOptionPane.INFORMATION_MESSAGE);
			JOptionPane pane = new JOptionPane(
					"sorry but you lost the game: \n    Your current money: "
							+ sumMoney + " \n    Time Passed: "
							+ Submit.changeTimeToString(125 * timerCounter)
							+ "\n    Killed: "
							+ bandOfVillains.getNumberOfVillainsBeenShoot());// +
																				// "\n   aware: "
																				// +
																				// racingCar.getAward());
			// pane.showInternalMessageDialog(this, "information",
			// "information", JOptionPane.INFORMATION_MESSAGE);; // Configure
			JDialog dialog = pane.createDialog(this,
					"GrabItQuick: you lost the game");
			dialog.show();
			gameHasEnded = true;
			// showingTitleScreen = true;
		}
		if (arg0.getSource() == startGameButton) {
			showingTitleScreen = false;
			// System.out.println("startGame");
			startGameButton.setVisible(false);
			repaint();
		}
		repaint();
	}

	// -------------------------------------------------------
	// Handle MouseEvents
	// -------------------------------------------------------
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		this.mousePressed(arg0);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		// System.out.println("mousePressed");
		for (int i = 0; i < mousePressed.length; i++) {
			if (mousePressed[i] == null) {
				mousePressed[i] = new MousePressed(arg0.getPoint());
				break;
			}
		}
		if (!showingTitleScreen && !gameHasEnded && !t.isRunning()) {
			t.start();
		} else if (!showingTitleScreen && !gameHasEnded && t.isRunning()) {
			mousePoint.setLocation(arg0.getX(), arg0.getY());
			bandOfVillains.setMousePoint(mousePoint);
			// System.out.println("mousePoint: " + mousePoint.toString());
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		this.mousePressed(arg0);
	}

	// -------------------------------------------------------
	// Draw everything
	// -------------------------------------------------------
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// System.out.println("inside of paintComponent()");
		if (!showingTitleScreen) {
			drawCellBlock(g);
			hero.draw(g);
			bandOfVillains.drawVillains(g);
			drawGameInformaton(g);
		} else {
			// before start:
			for (int i = 144; i >= 0; i--) {
				g.setColor(new Color(i, 0, 255));
				g.fillRect(0, (int) 5 * (144 - i), JFRAME_AREA_WIDTH, 5);
			}
			g.setColor(new Color(66, 255, 0));

			// welcome = new Welcome(g);
		}
		for (int i = 0; i < mousePressed.length; i++) {
			if (mousePressed[i] != null) {
				if (!mousePressed[i].draw(g)) {
					mousePressed[i] = null;
				}
			}
		}
	}

	// -------------------------------------------------------
	// Drawing helper methods
	// -------------------------------------------------------
	private void drawCellBlock(Graphics g) {
		// System.out.println("inside of drewCellBlock()");
		blockOfCells.drawCells(g);

	}

	private void drawGameInformaton(Graphics g) {
		g.setColor(Color.blue);
		g.drawString(
				"You have $" + sumMoney + "   Shooted: "
						+ bandOfVillains.getNumberOfVillainsBeenShoot(),
				INFORMATION_POSITION1.x, INFORMATION_POSITION1.y);
	}

	private class MousePressed implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			mouseCounter++;
			repaint();
		}

		private Point newPressed;
		private Timer mouseTimer = new Timer(10, this);
		private int mouseCounter = 0;
		private int cenPtX, cenPtY;

		MousePressed(Point newPressed) {
			this.newPressed = newPressed;
			mouseTimer.start();
			cenPtX = (int) newPressed.getX();
			cenPtY = (int) newPressed.getY();
			repaint();
			// System.out.println("new MousePressed created");
		}

		public boolean draw(Graphics g) {

			g.setColor(Color.RED);
			g.drawOval(cenPtX - mouseCounter, cenPtY - mouseCounter,
					mouseCounter * 2, mouseCounter * 2);
			// g.fillOval(cenPtX - 15, cenPtY - 15, 30, 30);
			if (2 * mouseCounter == CELL_SIZE) {
				mouseCounter = 0;
				System.out.println("Draw a new circle");
				return false;
			} else
				return true;
		}
	}
}
