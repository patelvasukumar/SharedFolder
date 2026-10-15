/*
 * Program Name : CET-CS [LEVEL-4]
 * Course Name : Java Application Programming
 * Team Members and Student Number : 1. Vasu Patel (041153940) 
 * 									 2. Kavya Vasani (041163941)
 * Lab Professor Name : PAULO SOUSA
 * 
 */

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/* ================= AWT IMPORTS ================= */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Cursor;
import java.awt.Dimension;


public class QuantumMillionaire {

	
	private static boolean debugMode = false;
	private static JTextArea debugArea;
	private static JPanel debugPanel;

	
	private static JButton[] optionButtons = new JButton[4];
	private static String[] optionsEnglish = {
	    "A. Bharat",
	    "B. Hindustan (name given by Persian travellers)",
	    "C. Tianzhu (used by Chinese buddhist monks )",
	    "D. All of the above"
	};
	
	public static void main(String[] args) {
	
		
		JFrame frame = new JFrame("GameFrame");
			
		ImageIcon bgi = new ImageIcon("background.jpg");
		JLabel bgLabel = new JLabel(bgi);		
		bgLabel.setLayout(new BorderLayout());
		
		QuantumMillionaire qmObj = new QuantumMillionaire();
		
		
		int choice = JOptionPane.showConfirmDialog(
		
		null, "Activate Debug Mode?", "Debug Mode", JOptionPane.YES_NO_OPTION
		
		);

		if(choice == JOptionPane.YES_OPTION){ debugMode = true; } else { debugMode = false; }
		
		
		JPanel topPanel = qmObj.headerButtons(frame);    
		bgLabel.add(topPanel, BorderLayout.NORTH);
		
		
        JPanel winningBar = qmObj.winningBar();
        bgLabel.add(winningBar, BorderLayout.EAST);
		

		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.setOpaque(false);
		
		qmObj.questionDisplay("QUESTION 1 - 100$ : What are the other names for INDIA used by people around the world throughout history ? ", bottomPanel,0,0);
		qmObj.optionButtonsGrid(bottomPanel,0,0);
		qmObj.addConfirmButton(bottomPanel,0);
		bgLabel.add(bottomPanel, BorderLayout.SOUTH);
		
		
		debugPanel = new JPanel(new BorderLayout());
		debugArea = new JTextArea(5,40);
		debugArea.setEditable(false);
		debugArea.setBackground(Color.BLACK);
		debugArea.setForeground(Color.GREEN);
		debugArea.setFont(new Font("Consolas", Font.BOLD, 16));

		debugPanel.add(new JScrollPane(debugArea), BorderLayout.CENTER);

		if(debugMode){
		    debugArea.setText("Hello Everyone !");
		}
		
		
		frame.setLayout(new BorderLayout());
		frame.add(bgLabel, BorderLayout.CENTER);
		frame.pack();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		JMenu gameMenu = new JMenu();
		JMenuItem restartItem = new JMenuItem("restart Options");

		restartItem.addActionListener(e -> restartGame());

		gameMenu.add(restartItem);
		menuBar.add(gameMenu);
		frame.setJMenuBar(menuBar);
		frame.setVisible(true);
		      
	
	}   
	
	private JPanel headerButtons(JFrame frame) {

	    JPanel topPanel = new JPanel();
	    topPanel.setLayout((LayoutManager) new BoxLayout(topPanel, BoxLayout.X_AXIS)); 
	    topPanel.setOpaque(false);

        JButton settingsButton = createIconButton("gear.png");
        JButton helpButton = createIconButton("call.png");
        JButton f50Button = createIconButton("50.png");
        JButton timeButton = createIconButton("addTime.png");
        JButton flipButton = createIconButton("flip.png");
        JButton profileButton = createIconButton("profile.png");

        topPanel.add(settingsButton);
        
        topPanel.add(Box.createRigidArea(new Dimension(370, 0)));
        
        topPanel.add(helpButton);
        topPanel.add(f50Button);
        topPanel.add(timeButton);
        topPanel.add(flipButton);
        
        topPanel.add(Box.createHorizontalGlue());
        
        topPanel.add(profileButton);

       
        SettingMode settings = new SettingMode(frame);
        settingsButton.addActionListener(e ->
        	  settings.openSettingMode(settingsButton)
        );

        return topPanel;
    
	}

	private JButton createIconButton(String imageName) {
	   
	    JButton button = new JButton(new ImageIcon(imageName));
	    
	    button.setBorderPainted(false);
	    button.setContentAreaFilled(false);
	    button.setFocusPainted(false);
	    button.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    
	    return button;
	
	}

	
	public void questionDisplay(String question, JPanel parent, int languageChange, int theme) {

		
		
		if(languageChange == 0) {	
			
		
	    JPanel questionPanel = new JPanel(new GridBagLayout());
	    questionPanel.setOpaque(false);

	    JPanel wrapperPanel = new JPanel(new BorderLayout(0, 10));
	    wrapperPanel.setOpaque(false);

	    JLabel timerLabel = new JLabel("02:00", JLabel.CENTER);
	    timerLabel.setFont(new Font("Nirmala UI", Font.BOLD, 28));
	    timerLabel.setForeground(Color.CYAN);
	    timerLabel.setBorder(
	        BorderFactory.createEmptyBorder(0, 0, 10, 0)
	    );

	    final int[] timeLeft = { 120 };

	    Timer timer = new Timer(1000, e -> {

	        int minutes = timeLeft[0] / 60;
	        int seconds = timeLeft[0] % 60;

	        timerLabel.setText(
	            String.format("%02d:%02d", minutes, seconds)
	        );

	        timeLeft[0]--;

	        if (timeLeft[0] < 0) {
	            ((Timer) e.getSource()).stop();
	            timerLabel.setText("TIME UP!");
	            timerLabel.setForeground(Color.RED);
	        }
	    });

	    timer.start();
	    
	    JButton quitButton = createIconButton("quit.png");
	    quitButton.setToolTipText("Quit Game");
	    quitButton.addActionListener(e -> System.exit(0));

	    question = "QUESTION 1 - 100$ : What are the other names for INDIA used by people around the world throughout history ?";
	   
	    JTextArea questionArea = new JTextArea(question);
	    questionArea.setLineWrap(true);
	    questionArea.setWrapStyleWord(true);
	    questionArea.setEditable(false);
	    questionArea.setOpaque(true);
	    questionArea.setFont(new Font("Dialog", Font.BOLD, 32));
	    
	    
	    if (theme == 0) {
	        questionArea.setBackground(new Color(20, 40, 120));  
	    } else {
	        questionArea.setBackground(new Color(200, 220, 255));
	    }
	  
	    
	    questionArea.setForeground(Color.WHITE);
	    questionArea.setBorder(
	        BorderFactory.createEmptyBorder(30, 60, 25, 60)
	    );
	    questionArea.setPreferredSize(new Dimension(1100, 120));

	    
	    wrapperPanel.add(timerLabel, BorderLayout.NORTH);
	    wrapperPanel.add(quitButton, BorderLayout.WEST);
	    wrapperPanel.add(questionArea, BorderLayout.CENTER);

	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    gbc.anchor = GridBagConstraints.CENTER;

	    questionPanel.add(wrapperPanel, gbc);
	    parent.add(questionPanel, BorderLayout.NORTH);
	
	    
		} else if (languageChange == 1) {
			
			
		    JPanel questionPanel = new JPanel(new GridBagLayout());
		    questionPanel.setOpaque(false);

		    JPanel wrapperPanel = new JPanel(new BorderLayout(0, 10));
		    wrapperPanel.setOpaque(false);

		    JLabel timerLabel = new JLabel("02:00", JLabel.CENTER);
		    timerLabel.setFont(new Font("Nirmala UI", Font.BOLD, 28));
		    timerLabel.setForeground(Color.YELLOW);
		    timerLabel.setBorder(
		        BorderFactory.createEmptyBorder(0, 0, 10, 0)
		    );

		    final int[] timeLeft = { 120 }; 

		    Timer timer = new Timer(1000, e -> {

		        int minutes = timeLeft[0] / 60;
		        int seconds = timeLeft[0] % 60;

		        timerLabel.setText(
		            String.format("%02d:%02d", minutes, seconds)
		        );

		        timeLeft[0]--;

		        if (timeLeft[0] < 0) {
		            ((Timer) e.getSource()).stop();
		            timerLabel.setText("TIME UP!");
		            timerLabel.setForeground(Color.RED);
		        }
		    });

		    timer.start();
		    
		    JButton quitButton = createIconButton("quit.png");
		    quitButton.setToolTipText("Quit Game");
		    quitButton.addActionListener(e -> System.exit(0));

		   
		    JTextArea questionArea = new JTextArea(" प्रश्न 1 – 100 $ :"
		    		+ "इतिहास में दुनिया भर के लोगों द्वारा भारत के लिए उपयोग किए गए अन्य नाम कौन-कौन से हैं? ");
		    questionArea.setLineWrap(true);
		    questionArea.setWrapStyleWord(true);
		    questionArea.setEditable(false);
		    questionArea.setOpaque(true);
		    questionArea.setFont(new Font("Nirmala UI", Font.BOLD, 32));
		    
		    
		    if (theme == 0) {
		        questionArea.setBackground(new Color(20, 40, 120));  
		    } else {
		        questionArea.setBackground(new Color(200, 220, 255));
		    }
		    
		    
		    questionArea.setForeground(Color.WHITE);
		    questionArea.setBorder(
		        BorderFactory.createEmptyBorder(30, 60, 25, 60)
		    );
		    questionArea.setPreferredSize(new Dimension(1100, 120));

		    
		    wrapperPanel.add(timerLabel, BorderLayout.NORTH);
		    wrapperPanel.add(quitButton, BorderLayout.WEST);
		    wrapperPanel.add(questionArea, BorderLayout.CENTER);

		    GridBagConstraints gbc = new GridBagConstraints();
		    gbc.gridx = 0;
		    gbc.gridy = 0;
		    gbc.anchor = GridBagConstraints.CENTER;

		    questionPanel.add(wrapperPanel, gbc);
		    parent.add(questionPanel, BorderLayout.NORTH);
				
			
		}
	
	}

	public void optionButtonsGrid(JPanel bottomPanel, int languageChange, int theme) {
		   
		
		  JPanel optionsPanel = new JPanel(new GridLayout(2,2,20,20));
		    optionsPanel.setOpaque(false);
		    
		    optionsPanel.setBorder(
		            BorderFactory.createEmptyBorder(20, 80, 60, 80)
		    );

		    
		    if(languageChange == 0) {
		    
		    	for(int i=0;i<4;i++){
		            optionButtons[i] = createOptionButton(optionsEnglish[i], theme);
		            optionsPanel.add(optionButtons[i]);
		        }
		    
		    } else if (languageChange == 1) {
		   
		    	JButton option1 = createOptionButton("(क) भारत", theme);
			    JButton option2 = createOptionButton("(ख) हिंदुस्तान (फ़ारसी यात्रियों द्वारा दिया गया नाम)", theme);
			    JButton option3 = createOptionButton("(ग) तियानझू (चीनी बौद्ध भिक्षुओं द्वारा प्रयुक्त नाम)", theme);
			    JButton option4 = createOptionButton("(घ) उपरोक्त सभी", theme);
		    	
			    optionsPanel.add(option1);
			    optionsPanel.add(option2);
			    optionsPanel.add(option3);
			    optionsPanel.add(option4);
		    	
		    }
		    
		   
		    bottomPanel.add(optionsPanel, BorderLayout.CENTER);
		
	}

    private JButton createOptionButton(String text, int theme) {

        JButton button = new JButton(text);

        button.setFont(new Font("Nirmala UI", Font.BOLD, 22));
        button.setFocusPainted(false);
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        
        
        if (theme == 0) {
            button.setBackground(Color.LIGHT_GRAY);
        } else {
            button.setBackground(new Color(255, 230, 180));  
        }

        
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addActionListener(e -> {
        	
        	if(button.getBackground().equals(Color.GREEN)) {
        			button.setBackground(Color.LIGHT_GRAY);
        	} else {
        			button.setBackground(Color.GREEN);
        	}
        });
        
        return button;
    
    }

    public void addConfirmButton(JPanel bottomPanel, int changeLanguage) {

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.setOpaque(false);

        JPanel confirmPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        confirmPanel.setOpaque(false);

        JButton confirmButton;

        if(changeLanguage == 0){
            confirmButton = new JButton("CONFIRM");
        }else{
            confirmButton = new JButton("चयन की पुष्टि करें");
        }

        confirmButton.setFont(new Font("Nirmala UI", Font.BOLD, 24));
        confirmButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        confirmButton.setBackground(new Color(255,180,0));
        confirmButton.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));

        confirmButton.addActionListener(e -> {
            System.out.println("Answer confirmed");
        });

        JButton restartButton = new JButton("Restart");
        restartButton.setFont(new Font("Dialog", Font.BOLD, 20));
        restartButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        restartButton.addActionListener(e -> restartGame());

        confirmPanel.add(confirmButton);
        confirmPanel.add(restartButton);
        controlPanel.add(confirmPanel);

        debugMode(controlPanel);

        bottomPanel.add(controlPanel, BorderLayout.SOUTH);
    }

    private JPanel winningBar() {
        
    	final int rows = 15;

        JPanel barPanel = new JPanel(new GridLayout(rows, 3, 0, 0));
        barPanel.setOpaque(false);

        barPanel.setPreferredSize(new Dimension(220, rows * 36));

        long[] amounts = {
                100, 200, 300, 500, 1000, 2000, 4000, 8000,
                16000, 32000, 64000, 125000, 250000, 500000, 1000000
        };

        int level = 16;
        
        for (int i = amounts.length - 1; i >= 0; i--) {
            
            level--;
            long amt = amounts[i];
            String amtStr = "$" + amt;

            JLabel left = new JLabel("", JLabel.RIGHT);
            left.setFont(new Font("Dialog", Font.PLAIN, 12));
            left.setOpaque(true);
            left.setBackground(new Color(40, 40, 40, 200));
            left.setForeground(Color.WHITE);
            left.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

            JLabel middle = new JLabel(String.valueOf(level), JLabel.CENTER);
            middle.setFont(new Font("Dialog", Font.BOLD, 14));
            middle.setOpaque(true);
            middle.setBackground(new Color(60, 60, 60, 200));
            middle.setForeground(Color.WHITE);
            middle.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

            JLabel right = new JLabel(amtStr, JLabel.LEFT);
            right.setFont(new Font("Dialog", Font.BOLD, 12));
            right.setOpaque(true);
            right.setBackground(new Color(30, 30, 30, 200));
            right.setForeground(Color.WHITE);
            right.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));

            barPanel.add(left);
            barPanel.add(middle);
            barPanel.add(right);
        }

        JScrollPane scroll = new JScrollPane(
                barPanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );
        
        scroll.setBorder(null);
        scroll.getViewport().setOpaque(false);
        scroll.setOpaque(false);
        scroll.setPreferredSize(new Dimension(240, 0));

        JPanel container = new JPanel(new BorderLayout());
        container.setOpaque(false);
        container.add(scroll, BorderLayout.CENTER);
        container.setPreferredSize(new Dimension(240, 0));

        return container;
    }

    private static void debugMode(JPanel controlPanel) {
    	
    	if(debugMode){

            debugArea = new JTextArea(5,50);
            debugArea.setEditable(false);
            debugArea.setBackground(Color.WHITE);
            debugArea.setForeground(Color.RED);
            debugArea.setFont(new Font("Nirmala UI", Font.BOLD, 25));
            debugArea.setText(" Hello, Everyone ! ");

            JScrollPane scroll = new JScrollPane(debugArea);
            scroll.setPreferredSize(new Dimension(700,120));

            controlPanel.add(scroll);
        }
    	
    	
    }
    
    private static void restartGame(){

        java.util.List<String> list = java.util.Arrays.asList(optionsEnglish);
        java.util.Collections.shuffle(list);

        for(int i=0;i<4;i++){
            optionButtons[i].setText(list.get(i));
        }

    }
    
    
}