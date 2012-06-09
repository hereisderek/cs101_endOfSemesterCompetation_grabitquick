import javax.swing.*;
import java.security.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.*;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.MessageDigest;

public class Submit {
	public static final int URL_LENGTH_LIMIT = (2000 - 200);// 250 for reserved 
	
	private int timerCounter;
	private int killNum;
	private String award;
	private String[] arguements;
	private JButton subButton, exitButton;
    private JTextField nameText;
    private JLabel scoreJLabel;
    private JTextField msgText;
    private JLabel msgJLabel;
	private JLabel timeJLabel;
	private JLabel nameJLabel;
    private SubmitJFrame gui;
    
	public Submit(int timerCounter){
		this(timerCounter, -1);
	}
	public Submit(int timerCounter, int killNum){
		this(timerCounter, -1, "empty");
	}
	public Submit(int timerCounter, int killNum, String award){
		this.award = award;
		this.timerCounter = timerCounter;
		this.killNum = killNum;
		reset();
		gui = new SubmitJFrame(433, 134,500, 200);
	}
	
	private void reset(){
	    arguements = new String[7];
		/**
		 * 	String: arguements:
		 * 		String[0] : name
		 * 		String[1] : score
		 * 		String[2] : killNum
		 * 		String[3] : award 
		 * 		String[4] : randomNumber (10000 - 99999)
		 * 		String[5] : message
		 * 		String[6] : md5
		 * */
		arguements = new String[]{
				"Anonymous",
				""+timerCounter,
				""+killNum,
				"empty",
				""+(int)(Math.random()*89999+10000),
				"empty",
				""
		};
		//nameText.setText("Anonymous");
	}
/*	private void addComponentsToPane(Container pane) {
		JLabel title = new JLabel("PageStart");
		pane.add(title, BorderLayout.PAGE_START);
	    JButton button = new JButton("Button 1 (PAGE_START)");
	    pane.add(button, BorderLayout.PAGE_START);     
	    button = new JButton("Button 2 (CENTER)");
	    button.setPreferredSize(new Dimension(200, 100));
	    pane.add(button, BorderLayout.CENTER);   
	    button = new JButton("Button 3 (LINE_START)");
	    pane.add(button, BorderLayout.LINE_START);     
	    button = new JButton("Long-Named Button 4 (PAGE_END)");
	    pane.add(button, BorderLayout.PAGE_END);      
	    button = new JButton("5 (LINE_END)");
	    pane.add(button, BorderLayout.LINE_END);
		
	}*/
	public void pullThePlug() {
		//rCJFrame.pullThePlug();
		gui.setVisible(false);
    }
	// gonna be used out of this class
	public static String changeTimeToString(int timerCounter){
		int min, sec;
		
		min = timerCounter/(1000*60);
		sec = timerCounter / 1000 - (min * 60);
		return ""+min+" minutes "+sec+" seconds";
	}
	private String generateArguments(String[] str){
		String argStr = "name="+str[0]+"&score="+str[1]+"&killNum="+str[2]+"&award="+str[3]+"&rdNum="+str[4]+"&msg="+str[5]+"&md5="+str[6];
		return argStr;
	}
	private String changeStringToMd5(String str) throws NoSuchAlgorithmException{
		//System.out.println("String to be md5 is: " + str + "time now is" + String.valueOf(System.currentTimeMillis()/10000));
		MessageDigest m = MessageDigest.getInstance("MD5");
		m.reset();
		m.update(str.getBytes());
		byte[] digest = m.digest();
		BigInteger bigInt = new BigInteger(1,digest);
		String hashtext = bigInt.toString(16);
		// Now we need to zero pad it if you actually want the full 32 chars.
		while(hashtext.length() < 32 ){
		  hashtext = "0"+hashtext;
		}
		return hashtext;
	}
	class SubmitJFrame extends JFrame{
	    public SubmitJFrame(int x, int y, int width, int height){
	        setTitle("Submit my score onlion: ");
	        setLocation(x,y);
	        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        SubmitJPanel frameContent = new SubmitJPanel();
	        Container visibleArea = getContentPane();
	        visibleArea.add(frameContent);
	        frameContent.setPreferredSize(new Dimension(width, height));
	        frameContent.setLayout(new GridLayout(4,2));
	        pack();
	        frameContent.requestFocusInWindow();
	        setVisible(true);
	    }
	}
	
	class SubmitJPanel extends JPanel implements ActionListener{
	    
	    public SubmitJPanel(){
	    	scoreJLabel = new JLabel("    Score: ");
	    	timeJLabel = new JLabel("Time Passed: " + changeTimeToString(timerCounter) + (killNum==-1?null:("\n Kills: " + killNum)) + "\n award:" + award);
	    	nameJLabel = new JLabel("    Name: ");
	    	nameText = new JTextField("Anonymous");
	    	msgJLabel = new JLabel("    Message: (" + URL_LENGTH_LIMIT +"characters max)");
	    	msgText = new JTextField("Hello World!");
	    	subButton = new JButton("submit");
	    	exitButton = new JButton("cancle");
	    	
	    	add(scoreJLabel);
	    	add(timeJLabel);
	    	add(nameJLabel);
	    	add(nameText);
	    	add(msgJLabel);
	    	add(msgText);
	    	add(subButton);
	    	add(exitButton);
	    	
	    	subButton.addActionListener(this);
	    	exitButton.addActionListener(this);
	    	
	    	repaint();
	    }
	    public void actionPerformed(ActionEvent e){
	    	
	    	arguements[0] = new String(nameText.getText());
	    	arguements[3] = new String(award);
	    	if (msgText.getText().length()>URL_LENGTH_LIMIT){
	    		arguements[5] = new String(msgText.getText().substring(0,URL_LENGTH_LIMIT ));
	    	} else {
	    		arguements[5] = new String(msgText.getText());
	    	}
	    	try {
				arguements[6] = new String(changeStringToMd5(""+(String)arguements[0]+(String)arguements[1]+(String)arguements[2]+(String)arguements[3]+(String)arguements[4]+String.valueOf(System.currentTimeMillis()/100000)+arguements[5]));
			} catch (NoSuchAlgorithmException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    	if (e.getSource() == subButton){
	    		//String webRoot = "http://127.0.0.1/php/grabitquick/";
	    		String webRoot = "http://ilovederek.crocserve.com/grabitquick/";
	    		
	    		openURL(webRoot + "?" + generateArguments(arguements)
	    				//TODO: TEMP
	    				//+"&dateT="+(int)(System.currentTimeMillis()/10000)
	    				);
	    		
	    		gui.setVisible(false);
	    		reset();
	    	}
	    	if (e.getSource() == exitButton){
	    		reset();
	    		gui.setVisible(false); //you can't see me!
	    	}
	    	
	    }
	    
	    public void paintComponent(Graphics g){
	    	g.setFont(new Font("TIMES", Font.CENTER_BASELINE, 20));
	    }
	}
	public static void openURL(String url) {
		String osName = System.getProperty("os.name");
		try {
			if (osName.startsWith("Windows"))
				Runtime.getRuntime().exec(
						"rundll32 url.dll,FileProtocolHandler " + url);
			else {
				String[] browsers = { "chrome", "firefox", "opera", "konqueror",
						"epiphany", "mozilla", "netscape" };
				String browser = null;
				for (int count = 0; count < browsers.length && browser == null; count++)
					if (Runtime.getRuntime()
							.exec(new String[] { "which", browsers[count] })
							.waitFor() == 0)
						browser = browsers[count];
				Runtime.getRuntime().exec(new String[] { browser, url });
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error in opening browser"
					+ ":\n" + e.getLocalizedMessage());
		}
	}
	/*	private JFrame frame;
	private JPanel panel;
	
	public static void openURL(String url) {
		String osName = System.getProperty("os.name");
		try {
			if (osName.startsWith("Windows"))
				Runtime.getRuntime().exec(
						"rundll32 url.dll,FileProtocolHandler " + url);
			else {
				String[] browsers = { "firefox", "opera", "konqueror",
						"epiphany", "mozilla", "netscape" };
				String browser = null;
				for (int count = 0; count < browsers.length && browser == null; count++)
					if (Runtime.getRuntime()
							.exec(new String[] { "which", browsers[count] })
							.waitFor() == 0)
						browser = browsers[count];
				Runtime.getRuntime().exec(new String[] { browser, url });
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error in opening browser"
					+ ":\n" + e.getLocalizedMessage());
		}
	}
	void Submit(){
		JFrame frame = new JFrame("Submit your score onlion");
		JPanel panel = new JPanel();
		
	}
	
	void start(int timerCounter){
		//panel.setPreferredSize(new Dimension(500, 500));
		JButton button = new JButton("Visit Us");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openURL("http://www.google.com");
			}
		});
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.add(button);
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);
	}
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		JButton button = new JButton("Visit Us");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openURL("http://www.roseindia.net");
			}
		});
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.add(button);
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);
	}*/
}