/*
 * Program Name : CET-CS [LEVEL-4]
 * Course Name : Java Application Programming
 * Team Members and Student Number : 1. Vasu Patel (041153940) 
 * 									 2. Kavya Vasani (041163941)
 * Lab Professor Name : PAULO SOUSA
 * 
 */

/* ================= SWING IMPORTS ================= */
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

	
	public static void main(String[] args) {
	
		
		JFrame frame = new JFrame("GameFrame");
			
		ImageIcon bgi = new ImageIcon("background.jpg");
		JLabel bgLabel = new JLabel(bgi);		
		bgLabel.setLayout(new BorderLayout());
		
		QuantumMillionaire qmObj = new QuantumMillionaire();
		
		
		/* === Header Buttons === */
		JPanel topPanel = qmObj.headerButtons(frame);    
		bgLabel.add(topPanel, BorderLayout.NORTH);
		
		
		/* === WINNING BAR === */
        JPanel winningBar = qmObj.winningBar();
        bgLabel.add(winningBar, BorderLayout.EAST);
		

	    /* === Question Displayer === */
		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.setOpaque(false);
		
		qmObj.questionDisplay("QUESTION 1 - 100$ : What are the other names for INDIA used by people around the world throughout history ? ", bottomPanel,0);
		qmObj.optionButtonsGrid(bottomPanel,0);
		qmObj.addConfirmButton(bottomPanel,0);
		bgLabel.add(bottomPanel, BorderLayout.SOUTH);
		
		/* === Frame === */
		frame.setLayout(new BorderLayout());
		frame.add(bgLabel, BorderLayout.CENTER);
		frame.pack();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		      
	
	}   
	
	/* ================= HEADER BUTTONS =================	*/
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

	/* ================= CREATE BUTTONS =================	*/
	private JButton createIconButton(String imageName) {
	   
	    JButton button = new JButton(new ImageIcon(imageName));
	    
	    button.setBorderPainted(false);
	    button.setContentAreaFilled(false);
	    button.setFocusPainted(false);
	    button.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    
	    return button;
	
	}

	
    /* ================= QUESTION DISPLAY =================	*/
	public void questionDisplay(String question, JPanel parent, int languageChange) {

		
		
		if(languageChange == 0) {	
			
		
	    /* ========== MAIN CONTAINER ========== */
	    JPanel questionPanel = new JPanel(new GridBagLayout());
	    questionPanel.setOpaque(false);

	    JPanel wrapperPanel = new JPanel(new BorderLayout(0, 10));
	    wrapperPanel.setOpaque(false);

	    /* ========== TIMER LABEL ========== */
	    JLabel timerLabel = new JLabel("02:00", JLabel.CENTER);
	    timerLabel.setFont(new Font("Nirmala UI", Font.BOLD, 28));
	    timerLabel.setForeground(Color.CYAN);
	    timerLabel.setBorder(
	        BorderFactory.createEmptyBorder(0, 0, 10, 0)
	    );

	    /* ========== TIMER LOGIC (2 MINUTES) ========== */
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
	    questionArea.setBackground(new Color(20, 40, 120));
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
			
			
			 /* ========== MAIN CONTAINER ========== */
		    JPanel questionPanel = new JPanel(new GridBagLayout());
		    questionPanel.setOpaque(false);

		    JPanel wrapperPanel = new JPanel(new BorderLayout(0, 10));
		    wrapperPanel.setOpaque(false);

		    /* ========== TIMER LABEL ========== */
		    JLabel timerLabel = new JLabel("02:00", JLabel.CENTER);
		    timerLabel.setFont(new Font("Nirmala UI", Font.BOLD, 28));
		    timerLabel.setForeground(Color.YELLOW);
		    timerLabel.setBorder(
		        BorderFactory.createEmptyBorder(0, 0, 10, 0)
		    );

		    /* ========== TIMER LOGIC (2 MINUTES) ========== */
		    final int[] timeLeft = { 120 }; // seconds

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
		    questionArea.setBackground(new Color(20, 40, 120));
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


	/* ================= OPTIONS BUTTONS ================= */
	public void optionButtonsGrid(JPanel bottomPanel, int languageChange) {
		   
		
		  JPanel optionsPanel = new JPanel(new GridLayout(2,2,20,20));
		    optionsPanel.setOpaque(false);
		    
		    optionsPanel.setBorder(
		            BorderFactory.createEmptyBorder(20, 80, 60, 80)
		    );

		    
		    if(languageChange == 0) {
		    
		    	JButton option1 = createOptionButton("A. Bharat");
		    	JButton option2 = createOptionButton("B. Hindustan (name given by Persian travellers)");
		    	JButton option3 = createOptionButton("C. Tianzhu (used by Chinese buddhist monks )");
		    	JButton option4 = createOptionButton("D. All of the above");

		    	optionsPanel.add(option1);
		    	optionsPanel.add(option2);
		    	optionsPanel.add(option3);
		    	optionsPanel.add(option4);
		    	
		    } else if (languageChange == 1) {
		   
		    	JButton option1 = createOptionButton("(क) भारत");
			    JButton option2 = createOptionButton("(ख) हिंदुस्तान (फ़ारसी यात्रियों द्वारा दिया गया नाम)");
			    JButton option3 = createOptionButton("(ग) तियानझू (चीनी बौद्ध भिक्षुओं द्वारा प्रयुक्त नाम)");
			    JButton option4 = createOptionButton("(घ) उपरोक्त सभी");
		    	
			    optionsPanel.add(option1);
			    optionsPanel.add(option2);
			    optionsPanel.add(option3);
			    optionsPanel.add(option4);
		    	
		    }
		    
		   
		    bottomPanel.add(optionsPanel, BorderLayout.CENTER);
		
	}

	
    /* ================= OPTION BUTTONS =================	*/
    private JButton createOptionButton(String text) {

        JButton button = new JButton(text);

        button.setFont(new Font("Nirmala UI", Font.BOLD, 22));
        button.setFocusPainted(false);
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setBackground(Color.LIGHT_GRAY);
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

    /* ================= CONFIRM BUTTON ================= */
    public void addConfirmButton(JPanel bottomPanel, int changeLanguage) {

        JPanel confirmPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        confirmPanel.setOpaque(false);
        confirmPanel.setBorder(
            BorderFactory.createEmptyBorder(10, 0, 40, 0)
        );

        JButton confirmButton;
        
        if(changeLanguage == 0) {
         confirmButton = new JButton("CONFIRM");
        } else {
         confirmButton = new JButton("चयन की पुष्टि करें");
        }
        
        confirmButton.setFont(new Font("Nirmala UI", Font.BOLD, 24));
        confirmButton.setFocusPainted(false);
        confirmButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        confirmButton.setBackground(new Color(255, 180, 0));
        confirmButton.setOpaque(true);
        confirmButton.setBorder(
            BorderFactory.createLineBorder(Color.BLACK, 3)
        );

        confirmButton.addActionListener(e -> {
            System.out.println("Answer confirmed");
        });

        confirmPanel.add(confirmButton);

       
        bottomPanel.add(confirmPanel, BorderLayout.SOUTH);
    }

    /* ================= WINNING BAR (RIGHT SIDE) ================= */
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

    
}
