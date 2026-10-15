package MainView.Play;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import Controller.Design.PlayerController;
import Controller.Design.QuestionController;
import Model.Design.Question;
import Model.Design.QuestionStore;
import MainView.SettingMode;
import MainView.SplashScreen;
import Model.Design.Player;
import util.ThemeManager;
import util.LanguageManager;

public class PlayMode extends JFrame {
    private static final long serialVersionUID = 1L;

    private QuestionController questionController = new QuestionController();
    private PlayerController playerController = new PlayerController();
    private Player player;

    private int currentLevel = 1;
    private final int TOTAL_LEVELS = 15;
    private Random rnd = new Random();

    private JLabel questionLabel;
    private JPanel optionsGrid;
    private JPanel ladderPanel;
    private JTextArea allQuestionsArea;
    private JProgressBar levelProgress;
    private JLabel timerLabel;
    private CountdownTimer countdownTimer;

    private String selectedOption = null;
    private JButton selectedButton = null;
    private String currentCorrect = null;
    private boolean lastUsedFirst = true;

    private List<String> allQuestions = new ArrayList<>();
    private long[] prizeAmounts = {1000000, 500000, 250000, 125000, 64000, 32000, 16000, 8000, 4000, 2000, 1000, 500, 300, 200, 100};

    private JButton confirmBtn;
    private JButton quitBtn;
    private SettingMode settingMode;
    private LifelinePanel lifelinePanel;

    private boolean usedSuperposition = false;
    private boolean usedEntanglement = false;
    private boolean usedInterference = false;
    private String entangledOption = null;
    public static void startWithPlayer(Player player) {
        new PlayMode(player);
    }

    public PlayMode(Player player) {
        this.player = player;
        setTitle("GameFrame - Player: " + player.getName());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new BackgroundPanel("images/background.jpg"));
        getContentPane().setLayout(new BorderLayout());

        buildTopBar();
        buildCenterArea();
        buildRightLadder();
        pack();
        setVisible(true);

     
        loadQuestion(currentLevel); 
        settingMode = new SettingMode(this);
    }

  
    private Font nirmalaBold(int size) {
        Font f = new Font("Nirmala UI", Font.BOLD, size);
        if (!f.getFamily().equals("Nirmala UI")) return new Font("Dialog", Font.BOLD, size);
        return f;
    }

    private Font nirmalaPlain(int size) {
        Font f = new Font("Nirmala UI", Font.PLAIN, size);
        if (!f.getFamily().equals("Nirmala UI")) return new Font("Dialog", Font.PLAIN, size);
        return f;
    }

    private void buildTopBar() {
        JPanel top = new JPanel();
        top.setOpaque(false);
        top.setLayout(new BorderLayout());

        JPanel left = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 10));
        left.setOpaque(false);
        JButton gearBtn = iconButtonScaled("images/gear.png", 64);
        left.add(gearBtn);

        JPanel center = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
        center.setOpaque(false);
        lifelinePanel = new LifelinePanel(this);
        center.add(lifelinePanel);

        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT, 30, 10));
        right.setOpaque(false);
        JButton profileBtn = iconButtonScaled("images/profile.png", 56);
        profileBtn.addActionListener(e -> showPlayerProfile());
        right.add(profileBtn);

        top.add(left, BorderLayout.WEST);
        top.add(center, BorderLayout.CENTER);
        top.add(right, BorderLayout.EAST);

        gearBtn.addActionListener(e -> settingMode.openSettingMode(gearBtn));

        levelProgress = new JProgressBar(0, TOTAL_LEVELS);
        levelProgress.setValue(currentLevel);
        levelProgress.setStringPainted(false);
        levelProgress.setForeground(new Color(255, 215, 0));
        levelProgress.setBackground(Color.DARK_GRAY);
        top.add(levelProgress, BorderLayout.SOUTH);
        add(top, BorderLayout.NORTH);
    }

    private void buildCenterArea() {
        JPanel center = new JPanel();
        center.setOpaque(false);
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setBorder(BorderFactory.createEmptyBorder(50, 60, 60, 60));

        questionLabel = new JLabel("QUESTION TEXT", SwingConstants.CENTER);
        questionLabel.setFont(nirmalaBold(26));
        questionLabel.setForeground(ThemeManager.getQuestionAreaForeground());
        questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        questionLabel.setOpaque(true);
        questionLabel.setBackground(ThemeManager.getQuestionAreaBackground());
        questionLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        center.add(questionLabel);
        center.add(Box.createVerticalStrut(30));

        timerLabel = new JLabel("03:00", SwingConstants.CENTER);
        timerLabel.setFont(nirmalaBold(24));
        timerLabel.setForeground(java.awt.Color.YELLOW);
        timerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        center.add(timerLabel);
        center.add(Box.createVerticalStrut(20));

        optionsGrid = new JPanel(new GridLayout(2, 2, 30, 20));
        optionsGrid.setOpaque(false);
        optionsGrid.setMaximumSize(new Dimension(1000, 150));
        center.add(optionsGrid);
        center.add(Box.createVerticalStrut(25));

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        btnPanel.setOpaque(false);

        confirmBtn = new JButton(LanguageManager.t("CONFIRM"));
        confirmBtn.setFont(nirmalaBold(18));
        confirmBtn.setBackground(ThemeManager.getOptionBackground());
        confirmBtn.setForeground(ThemeManager.getOptionForeground());
        confirmBtn.setPreferredSize(new Dimension(160, 50));
        confirmBtn.addActionListener(e -> doConfirm());

        quitBtn = new JButton(LanguageManager.t("QUIT & TAKE PRIZE"));
        quitBtn.setFont(nirmalaBold(16));
        quitBtn.setBackground(new Color(200, 50, 50));
        quitBtn.setForeground(java.awt.Color.WHITE);
        quitBtn.setPreferredSize(new Dimension(200, 50));
        quitBtn.addActionListener(e -> quitGameTakePrize());

        btnPanel.add(confirmBtn);
        btnPanel.add(quitBtn);
        center.add(btnPanel);
        center.add(Box.createVerticalStrut(25));

        allQuestionsArea = new JTextArea(10, 50);
        allQuestionsArea.setEditable(false);
        allQuestionsArea.setFont(nirmalaPlain(16));
        allQuestionsArea.setLineWrap(true);
        allQuestionsArea.setWrapStyleWord(true);
        JScrollPane scrollArea = new JScrollPane(allQuestionsArea);
        scrollArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollArea.setMaximumSize(new Dimension(1000, 200));
        center.add(scrollArea);

        add(center, BorderLayout.CENTER);
    }

    private void buildRightLadder() {
        ladderPanel = new JPanel(new GridLayout(TOTAL_LEVELS, 1, 4, 4));
        ladderPanel.setOpaque(false);
        ladderPanel.setPreferredSize(new Dimension(200, 700));
        JScrollPane scroll = new JScrollPane(ladderPanel);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 20));
        add(scroll, BorderLayout.EAST);
        refreshLadderHighlight();
    }

    private void refreshLadderHighlight() {
        ladderPanel.removeAll();
        for (int i = 0; i < TOTAL_LEVELS; i++) {
            int levelNum = TOTAL_LEVELS - i;
            JLabel lbl = new JLabel(" " + levelNum + " " + prizeAmounts[i], SwingConstants.RIGHT);
            lbl.setOpaque(true);
            lbl.setBackground(new Color(0, 0, 0, 50));
            lbl.setForeground(new Color(220, 220, 220, 180));
            lbl.setFont(nirmalaPlain(14));
            lbl.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
            if (levelNum == currentLevel) {
                lbl.setBackground(new Color(255, 215, 0, 120));
                lbl.setForeground(Color.BLACK);
                lbl.setFont(nirmalaBold(14));
            }
            ladderPanel.add(lbl);
        }
        ladderPanel.revalidate();
        ladderPanel.repaint();
    }

    
    private void updateQuestionUIPreserveTimer() {
        Question q = questionController.getQuestion(currentLevel);
        if (q == null) {
            finishGameByWinning();
            return;
        }

        selectedOption = null;
        selectedButton = null;
        entangledOption = null;
      
        boolean useFirst = lastUsedFirst;
        String qText = useFirst ? q.getQuestion1() : q.getQuestion2();
        currentCorrect = useFirst ? q.getQ1op1() : q.getQ2op1();

        List<String> opts = new ArrayList<>();
        if (useFirst) {
            opts.add(q.getQ1op1());
            opts.add(q.getQ1op2());
            opts.add(q.getQ1op3());
            opts.add(q.getQ1op4());
        } else {
            opts.add(q.getQ2op1());
            opts.add(q.getQ2op2());
            opts.add(q.getQ2op3());
            opts.add(q.getQ2op4());
        }

        Collections.shuffle(opts, rnd);

        questionLabel.setText(qText);
        questionLabel.setBackground(ThemeManager.getQuestionAreaBackground());
        questionLabel.setForeground(ThemeManager.getQuestionAreaForeground());

        if (allQuestions.size() >= currentLevel) {
        
            allQuestions.set(currentLevel - 1, "Q" + currentLevel + ": " + qText);
        } else {
            allQuestions.add("Q" + currentLevel + ": " + qText);
        }
        allQuestionsArea.setText(String.join("\n\n", allQuestions));

     
        optionsGrid.removeAll();
        for (String o : opts) {
            JButton btn = new JButton(o);
            btn.setFont(nirmalaBold(16));
            btn.setBackground(ThemeManager.getOptionBackground());
            btn.setForeground(ThemeManager.getOptionForeground());
            btn.addActionListener(e -> {
                if (selectedButton != null) selectedButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                selectedButton = btn;
                selectedButton.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
                selectedOption = o;
            });
            optionsGrid.add(btn);
        }
        optionsGrid.revalidate();
        optionsGrid.repaint();

        refreshLadderHighlight();
    }

    
    private void loadQuestion(int level) {
        Question q = questionController.getQuestion(level);
        if (q == null) {
            finishGameByWinning();
            return;
        }

        currentLevel = level;
        selectedOption = null;
        selectedButton = null;
        entangledOption = null;
        levelProgress.setValue(currentLevel);

        boolean useFirst = new Random().nextBoolean();
        lastUsedFirst = useFirst; 

        String qText = useFirst ? q.getQuestion1() : q.getQuestion2();
        currentCorrect = useFirst ? q.getQ1op1() : q.getQ2op1();

        List<String> opts = new ArrayList<>();
        if (useFirst) {
            opts.add(q.getQ1op1());
            opts.add(q.getQ1op2());
            opts.add(q.getQ1op3());
            opts.add(q.getQ1op4());
        } else {
            opts.add(q.getQ2op1());
            opts.add(q.getQ2op2());
            opts.add(q.getQ2op3());
            opts.add(q.getQ2op4());
        }

        Collections.shuffle(opts, rnd);

        questionLabel.setText(qText);
        questionLabel.setBackground(ThemeManager.getQuestionAreaBackground());
        questionLabel.setForeground(ThemeManager.getQuestionAreaForeground());

     
        if (allQuestions.size() >= level) {
            allQuestions.set(level - 1, "Q" + level + ": " + qText);
        } else {
            allQuestions.add("Q" + level + ": " + qText);
        }
        allQuestionsArea.setText(String.join("\n\n", allQuestions));

        optionsGrid.removeAll();
        for (String o : opts) {
            JButton btn = new JButton(o);
            btn.setFont(nirmalaBold(16));
            btn.setBackground(ThemeManager.getOptionBackground());
            btn.setForeground(ThemeManager.getOptionForeground());
            btn.addActionListener(e -> {
                if (selectedButton != null) selectedButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                selectedButton = btn;
                selectedButton.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
                selectedOption = o;
            });
            optionsGrid.add(btn);
        }
        optionsGrid.revalidate();
        optionsGrid.repaint();

        refreshLadderHighlight();

        if (countdownTimer == null) {
            countdownTimer = new CountdownTimer(timerLabel, () -> {
                showMessageCustom(LanguageManager.t("Time's up!"), LanguageManager.t("Time's up!"), JOptionPane.WARNING_MESSAGE);
                player.setCurrentPoint(0);
                playerController.updatePlayer(player);
                backToHome();
            });
           
            countdownTimer.start();
        } else {
            countdownTimer.restart();
        }
    }

    private void doConfirm() {
        if (countdownTimer != null) countdownTimer.stop();
        if (selectedOption == null) {
            showMessageCustom(LanguageManager.t("Select an option first."), LanguageManager.t("Select an option first."), JOptionPane.INFORMATION_MESSAGE);
            if (countdownTimer != null) countdownTimer.start();
            return;
        }
        boolean isCorrect = selectedOption.equals(currentCorrect) || (usedEntanglement && entangledOption != null && selectedOption.equals(entangledOption));
        if (isCorrect) {
            long prizeForLevel = prizeAmounts[TOTAL_LEVELS - currentLevel];
            player.setCurrentPoint((int) prizeForLevel);
            if (player.getHistoricPoint() < player.getCurrentPoint()) {
                player.setHistoricPoint(player.getCurrentPoint());
            }
            playerController.updatePlayer(player);
            if (currentLevel >= TOTAL_LEVELS) {
                finishGameByWinning();
            } else {
                loadQuestion(currentLevel + 1);
            }
        } else {
            player.setCurrentPoint(0);
            playerController.updatePlayer(player);
            showMessageCustom(LanguageManager.t("Wrong answer!"), LanguageManager.t("Wrong answer!"), JOptionPane.ERROR_MESSAGE);
            backToHome();
        }
    }

    private void showPlayerProfile() {
        ProfileView.showProfile(this, player, currentLevel);
    }

    private void quitGameTakePrize() {
        if (countdownTimer != null) countdownTimer.stop();
        long prize = prizeAmounts[TOTAL_LEVELS - currentLevel];
        player.setCurrentPoint((int) prize);
        if (player.getHistoricPoint() < player.getCurrentPoint()) {
            player.setHistoricPoint(player.getCurrentPoint());
        }
        playerController.updatePlayer(player);

        
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        JLabel l1 = new JLabel(LanguageManager.t("You quit the game!"));
        l1.setFont(nirmalaBold(16));
        JLabel l2 = new JLabel(LanguageManager.t("You take: ") + prize);
        l2.setFont(nirmalaPlain(14));
        p.add(l1);
        p.add(Box.createVerticalStrut(8));
        p.add(l2);

        JOptionPane.showMessageDialog(this, p, LanguageManager.t("Prize"), JOptionPane.INFORMATION_MESSAGE);
        backToHome();
    }

    private void finishGameByWinning() {
        long prize = prizeAmounts[0];
        player.setCurrentPoint((int) prize);
        if (player.getHistoricPoint() < player.getCurrentPoint()) {
            player.setHistoricPoint(player.getCurrentPoint());
        }
        playerController.updatePlayer(player);

     
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        JLabel title = new JLabel(LanguageManager.t("Congratulations") + " " + player.getName() + "!");
        title.setFont(nirmalaBold(16));
        JLabel body = new JLabel(LanguageManager.t("You completed all questions."));
        body.setFont(nirmalaPlain(14));
        p.add(title);
        p.add(Box.createVerticalStrut(8));
        p.add(body);

        JOptionPane.showMessageDialog(this, p, LanguageManager.t("Congratulations"), JOptionPane.INFORMATION_MESSAGE);
        backToHome();
    }

    private void backToHome() {
        dispose();
        new SplashScreen();
    }

    private JButton iconButtonScaled(String path, int size) {
        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);
        JButton b = new JButton(new ImageIcon(img));
        b.setBorderPainted(false);
        b.setContentAreaFilled(false);
        b.setFocusPainted(false);
        return b;
    }

    public void reloadCurrentQuestion() {
        loadQuestion(currentLevel);
    }

    
    public void reloadUI() {

       
        QuestionStore.reloadQuestions();

        
        confirmBtn.setText(LanguageManager.t("CONFIRM"));
        quitBtn.setText(LanguageManager.t("QUIT & TAKE PRIZE"));
        confirmBtn.setFont(nirmalaBold(18));
        quitBtn.setFont(nirmalaBold(16));

        questionLabel.setFont(nirmalaBold(26));
        timerLabel.setFont(nirmalaBold(24));
        allQuestionsArea.setFont(nirmalaPlain(16));

       
        updateQuestionUIPreserveTimer();

       
        for (Component c : optionsGrid.getComponents()) {
            if (c instanceof JButton) {
                ((JButton) c).setFont(nirmalaBold(16));
            }
        }

       
        if (lifelinePanel != null) lifelinePanel.reloadTexts();
        applyTheme();
    }

    public void applyTheme() {
        questionLabel.setBackground(ThemeManager.getQuestionAreaBackground());
        questionLabel.setForeground(ThemeManager.getQuestionAreaForeground());
        confirmBtn.setBackground(ThemeManager.getOptionBackground());
        confirmBtn.setForeground(ThemeManager.getOptionForeground());
        for (Component c : optionsGrid.getComponents()) {
            if (c instanceof JButton) {
                c.setBackground(ThemeManager.getOptionBackground());
                c.setForeground(ThemeManager.getOptionForeground());
            }
        }
        repaint();
    }

    public void useSuperposition() {
        if (usedSuperposition) return;
        usedSuperposition = true;
        List<JButton> wrongButtons = new ArrayList<>();
        for (Component c : optionsGrid.getComponents()) {
            if (c instanceof JButton) {
                JButton b = (JButton) c;
                if (!b.getText().equals(currentCorrect)) wrongButtons.add(b);
            }
        }
        if (wrongButtons.isEmpty()) return;
        int maxToDisable = Math.max(1, rnd.nextInt(Math.min(3, wrongButtons.size())) + 1);
        Collections.shuffle(wrongButtons, rnd);
        for (int i = 0; i < maxToDisable; i++) {
            JButton w = wrongButtons.get(i);
            w.setEnabled(false);
            w.setForeground(Color.GRAY);
            w.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
        }
    }

    public void useEntanglement() {
        if (usedEntanglement) return;
        usedEntanglement = true;
        List<JButton> wrongButtons = new ArrayList<>();
        for (Component c : optionsGrid.getComponents()) {
            if (c instanceof JButton) {
                JButton b = (JButton) c;
                if (!b.getText().equals(currentCorrect)) wrongButtons.add(b);
            }
        }
        if (wrongButtons.isEmpty()) return;
        JButton chosen = wrongButtons.get(rnd.nextInt(wrongButtons.size()));
        entangledOption = chosen.getText();
        chosen.setBorder(BorderFactory.createLineBorder(new Color(128, 0, 128), 3));
        chosen.setToolTipText(LanguageManager.t("Entangled (this will count as correct)"));
    }

    public void useInterference() {
        if (usedInterference) return;
        usedInterference = true;
        List<JButton> wrongButtons = new ArrayList<>();
        JButton correctBtn = null;
        for (Component c : optionsGrid.getComponents()) {
            if (c instanceof JButton) {
                JButton b = (JButton) c;
                if (b.getText().equals(currentCorrect)) correctBtn = b;
                else wrongButtons.add(b);
            }
        }
        double r = rnd.nextDouble();
        JButton interfered = null;
        if (r < 0.5 && correctBtn != null) {
            interfered = correctBtn;
            interfered.getText();
        } else if (!wrongButtons.isEmpty()) {
            interfered = wrongButtons.get(rnd.nextInt(wrongButtons.size()));
            interfered.getText();
        }
        if (interfered != null) {
            interfered.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
            interfered.setToolTipText(LanguageManager.t("Interfered suggestion"));
        }
    }

   
    private void showMessageCustom(String message, String title, int messageType) {
        JLabel label = new JLabel(message);
       
        label.setFont(nirmalaBold(16));
        label.setForeground(Color.BLACK);
        JOptionPane.showMessageDialog(this, label, title, messageType);
    }
}