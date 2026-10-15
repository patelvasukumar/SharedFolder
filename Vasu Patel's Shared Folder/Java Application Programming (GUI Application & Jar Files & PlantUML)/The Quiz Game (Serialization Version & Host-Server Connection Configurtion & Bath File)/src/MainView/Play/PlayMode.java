package MainView.Play;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
import Network.ChatPanel;
import Network.MultiplayerGameController;
import Network.NetworkConnectDialog;
import Network.NetworkHostDialog;
import Network.ScoresPanel;

/**
 * Main game screen for Play Mode.
 * Handles question flow, UI rendering, lifelines, multiplayer interaction,
 * scoring system, and game lifecycle management.
 */
public class PlayMode extends JFrame {
    private static final long serialVersionUID = 1L;

    /** 
     * Controller for handling questions from the question bank.
     */
    private final QuestionController questionController = new QuestionController();
    
    /** 
     * Controller responsible for updating and managing player data. 
     */
    private final PlayerController playerController = new PlayerController();
    
    /** 
     * Current player participating in the game session.
     */
    private final Player player;

    /** 
     * Current level of the game (1 to TOTAL_LEVELS). 
     */
    private int currentLevel = 1;

    /** 
     * Total number of levels in the game.
     */
    private final int TOTAL_LEVELS = 15;

    /** 
     * Random generator used for shuffling options and selections. 
     */
    private final Random rnd = new Random();

    /** 
     * Label that displays the current question text.
     */
    private JLabel questionLabel;

    /** 
     * Grid panel containing answer option buttons.
     */
    private JPanel optionsGrid;

    /** 
     * Ladder panel showing prize progression. 
     */
    private JPanel ladderPanel;

    /**
     *  Text area showing history of all questions.
     */
    private JTextArea allQuestionsArea;

    /** 
     * Progress bar indicating current level progress.
     */
    private JProgressBar levelProgress;

    /** 
     * Label showing count down timer. 
     */
    private JLabel timerLabel;

    /** 
     * Count down timer controlling question time limit. 
     */
    private CountdownTimer countdownTimer;

    /** 
     * Currently selected answer option text. 
     */
    private String selectedOption = null;

    /** 
     * Currently selected answer button in UI.
     */
    private JButton selectedButton = null;

    /** 
     * Correct answer for the current question.
     */
    private String currentCorrect = null;

    /**
     *  Tracks whether first or second question version was used.
     */
    private boolean lastUsedFirst = true;

    /** 
     * Stores all questions displayed during game play history.
     */
    private final List<String> allQuestions = new ArrayList<>();

    /** 
     * Prize values for each level in descending order.
     */
    private final long[] prizeAmounts = {
        1000000, 500000, 250000, 125000, 64000,
        32000, 16000, 8000, 4000, 2000,
        1000, 500, 300, 200, 100
    };
    
    /** 
     * Confirm button to submit selected answer. 
     */
    private JButton confirmBtn;

    /**
     *  Button to quit and take current prize.
     */
    private JButton quitBtn;

    /** 
     * Button for range-based quit option. 
     */
    private JButton rangeQuitBtn;

    /** 
     * Button to disconnect from multiplayer session.
     */
    private JButton disconnectBtn;

    /**
     *  Settings panel for configuration options.
     */
    private SettingMode settingMode;

    /** 
     * Life line panel containing special game abilities. 
     */
    private LifelinePanel lifelinePanel;
    
    /** 
     * Indicates whether Superposition life line has been used. 
     */
    private boolean usedSuperposition = false;

    /** 
     * Indicates whether Entanglement life line has been used. 
     */
    private boolean usedEntanglement = false;

    /** 
     * Indicates whether Interference life line has been used. 
     */
    private boolean usedInterference = false;
    
    /** 
     * Option linked through entanglement life line. 
     */
    private String entangledOption = null;

    /** 
     * Controller handling multiplayer communication and logic. 
     */
    private MultiplayerGameController multiplayerController;

    /** 
     * Chat panel used in multiplayer mode. 
     */
    private ChatPanel chatPanel;

    /** 
     * Panel showing live player scores.
     */
    private ScoresPanel scoresPanel;

    /** 
     * Indicates whether multiplayer mode is enabled. 
     */
    private boolean multiplayerEnabled = false;

    /** 
     * Current network state (OFFLINE, HOST, CLIENT). 
     */
    private String networkState = "OFFLINE";
    
    /** 
     * Indicates whether freeze-time mode is active. 
     */
    private boolean freezeTimeEnabled = false;

    /** 
     * Card layout used to switch between chat and questions views. 
     */
    private CardLayout centerCardLayout;

    /** 
     * Container holding different center panels (chat/questions). 
     */
    private JPanel centerCards;

    /** 
     * Button for switching to Questions tab. 
     */
    private JButton questionsTabBtn;

    /**
     * Button for switching to Chat tab. 
     */
    private JButton chatTabBtn;

    /** 
     * If true, disables interaction for host-only display mode. 
     */
    private boolean hostDisplayOnly = false;

    /**
     * Starts the PlayMode game with the given player.
     *
     * @param player the player object to start the game with
     */
    public static void startWithPlayer(Player player) {
        new PlayMode(player);
    }

    /**
     * Constructs the PlayMode game screen for the given player.
     *
     * @param player the player who is currently playing the game
     */
    public PlayMode(Player player) {
        this.player = player;

        chatPanel = new ChatPanel();
        scoresPanel = new ScoresPanel();
        multiplayerController = new MultiplayerGameController(this, questionController, playerController, player);
        chatPanel.setSendHandler(msg -> multiplayerController.sendChat(msg));

        setTitle("GameFrame - Player: " + player.getName());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new BackgroundPanel("images/background.jpg"));
        getContentPane().setLayout(new BorderLayout());

        buildTopBar();
        buildLeftScoreBoard();
        buildCenterArea();
        buildRightLadder();

        settingMode = new SettingMode(this);

        pack();
        setVisible(true);

        loadQuestion(currentLevel);
    }
    
    /**
     * Enables or disables host-only display mode.
     * In host mode, player interaction is disabled.
     *
     * @param hostOnly true to enable host-only mode, false to disable
     */
    public void setHostDisplayOnly(boolean hostOnly) {
        hostDisplayOnly = hostOnly;

        if (scoresPanel != null) {
            scoresPanel.setVisible(true);
        }

        if (lifelinePanel != null) {
            lifelinePanel.setVisible(!hostOnly);
        }

        if (confirmBtn != null) {
            confirmBtn.setEnabled(!hostOnly);
        }

        if (quitBtn != null) {
            quitBtn.setEnabled(!hostOnly);
        }

        if (rangeQuitBtn != null) {
            rangeQuitBtn.setEnabled(!hostOnly);
        }

        if (optionsGrid != null) {
            for (Component c : optionsGrid.getComponents()) {
                if (c instanceof JButton) {
                    c.setEnabled(!hostOnly);
                }
            }
        }

        revalidate();
        repaint();
    }

    /**
     * Checks if host-only display mode is enabled.
     *
     * @return true if host-only mode is active, otherwise false
     */
    public boolean isHostDisplayOnly() {
        return hostDisplayOnly;
    }

    /**
     * Creates a bold Nirmala UI font with fallback.
     */
    private Font nirmalaBold(int size) {
        Font f = new Font("Nirmala UI", Font.BOLD, size);
        if (!f.getFamily().equals("Nirmala UI")) return new Font("Dialog", Font.BOLD, size);
        return f;
    }

    /**
     * Creates a plain Nirmala UI font with fallback.
     */
    private Font nirmalaPlain(int size) {
        Font f = new Font("Nirmala UI", Font.PLAIN, size);
        if (!f.getFamily().equals("Nirmala UI")) return new Font("Dialog", Font.PLAIN, size);
        return f;
    }

    /**
     * Builds the top bar UI including settings, lifelines, and profile controls.
     */
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

    /**
     * Builds the left Score board panel.
     */
    private void buildLeftScoreBoard() {
        JPanel left = new JPanel();
        left.setOpaque(false);
        left.setLayout(new BorderLayout(6, 6));
        left.setBorder(BorderFactory.createEmptyBorder(20, 15, 20, 15));
        left.setPreferredSize(new Dimension(260, 700));

        disconnectBtn = new JButton(LanguageManager.t("Disconnect"));
        disconnectBtn.setFont(nirmalaBold(16));
        disconnectBtn.addActionListener(e -> disconnectNetwork());

        left.add(scoresPanel, BorderLayout.CENTER);
        left.add(disconnectBtn, BorderLayout.SOUTH);

        add(left, BorderLayout.WEST);
    }

    /**
     * Builds the center game play area including questions and options.
     */
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
        center.add(Box.createVerticalStrut(20));

        timerLabel = new JLabel("03:00", SwingConstants.CENTER);
        timerLabel.setFont(nirmalaBold(24));
        timerLabel.setForeground(Color.YELLOW);
        timerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        center.add(timerLabel);
        center.add(Box.createVerticalStrut(20));

        optionsGrid = new JPanel(new GridLayout(2, 2, 30, 20));
        optionsGrid.setOpaque(false);
        optionsGrid.setMaximumSize(new Dimension(1000, 150));
        center.add(optionsGrid);
        center.add(Box.createVerticalStrut(20));

        JPanel tabRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        tabRow.setOpaque(false);

        questionsTabBtn = new JButton(LanguageManager.t("Questions"));
        chatTabBtn = new JButton(LanguageManager.t("Chat"));
        questionsTabBtn.addActionListener(e -> showQuestionsTab());
        chatTabBtn.addActionListener(e -> showChatTab());

        tabRow.add(questionsTabBtn);
        tabRow.add(chatTabBtn);
        center.add(tabRow);
        center.add(Box.createVerticalStrut(10));

        centerCardLayout = new CardLayout();
        centerCards = new JPanel(centerCardLayout);
        centerCards.setOpaque(false);

        allQuestionsArea = new JTextArea(10, 50);
        allQuestionsArea.setEditable(false);
        allQuestionsArea.setFont(nirmalaPlain(16));
        allQuestionsArea.setLineWrap(true);
        allQuestionsArea.setWrapStyleWord(true);

        JScrollPane questionScroll = new JScrollPane(allQuestionsArea);
        questionScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        centerCards.add(questionScroll, "QUESTIONS");
        centerCards.add(chatPanel, "CHAT");

        center.add(centerCards);
        center.add(Box.createVerticalStrut(15));

        JPanel controlRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 14, 0));
        controlRow.setOpaque(false);

        confirmBtn = new JButton(LanguageManager.t("CONFIRM"));
        confirmBtn.setFont(nirmalaBold(18));
        confirmBtn.setBackground(ThemeManager.getOptionBackground());
        confirmBtn.setForeground(ThemeManager.getOptionForeground());
        confirmBtn.setPreferredSize(new Dimension(160, 50));
        confirmBtn.addActionListener(e -> {
            if (multiplayerEnabled) {
                multiplayerController.submitLocalAnswer(selectedOption);
            } else {
                doConfirm();
            }
        });

        quitBtn = new JButton(LanguageManager.t("QUIT & TAKE PRIZE"));
        quitBtn.setFont(nirmalaBold(16));
        quitBtn.setBackground(new Color(200, 50, 50));
        quitBtn.setForeground(Color.WHITE);
        quitBtn.setPreferredSize(new Dimension(200, 50));
        quitBtn.addActionListener(e -> quitGameTakePrize());

        rangeQuitBtn = new JButton(LanguageManager.t("Range Quit"));
        rangeQuitBtn.setFont(nirmalaBold(16));
        rangeQuitBtn.setPreferredSize(new Dimension(170, 50));
        rangeQuitBtn.addActionListener(e -> rangeQuitGame());

        controlRow.add(confirmBtn);
        controlRow.add(quitBtn);
        controlRow.add(rangeQuitBtn);
        center.add(controlRow);
        center.add(Box.createVerticalStrut(15));

        add(center, BorderLayout.CENTER);
    }

    /**
     * Builds the right-side prize ladder panel.
     */
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

    /**
     * Updates ladder UI highlighting based on current level.
     */
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

    /**
     * Loads a question for the specified level.
     */
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

        if (freezeTimeEnabled && countdownTimer != null) {
            countdownTimer.stop();
            timerLabel.setText("FROZEN");
        }
    }

    /**
     * Updates UI while preserving timer state.
     */
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

        if (freezeTimeEnabled && countdownTimer != null) {
            countdownTimer.stop();
            timerLabel.setText("FROZEN");
        }
    }

    /**
     * Confirms selected answer and evaluates correctness.
     */
    private void doConfirm() {
        if (countdownTimer != null) countdownTimer.stop();
        if (selectedOption == null) {
            showMessageCustom(LanguageManager.t("Select an option first."), LanguageManager.t("Select an option first."), JOptionPane.INFORMATION_MESSAGE);
            if (countdownTimer != null && !freezeTimeEnabled) countdownTimer.start();
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

    /**
     * Displays player profile popup.
     */
    private void showPlayerProfile() {
        ProfileView.showProfile(this, player, currentLevel);
    }

    /**
     * Quits game and awards current prize.
     */
    private void quitGameTakePrize() {
        if (multiplayerEnabled) {
            rangeQuitGame();
            return;
        }

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

    /**
     * Handles game completion when all levels are finished.
     */
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

    /**
     * Returns user to splash screen.
     */
    private void backToHome() {
        dispose();
        new SplashScreen();
    }

    /**
     * Creates an icon button with scaled image.
     */
    private JButton iconButtonScaled(String path, int size) {
        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);
        JButton b = new JButton(new ImageIcon(img));
        b.setBorderPainted(false);
        b.setContentAreaFilled(false);
        b.setFocusPainted(false);
        return b;
    }

    /**
     * Reloads current question without changing level.
     */
    public void reloadCurrentQuestion() {
        loadQuestion(currentLevel);
    }

    /**
     * Reloads UI after language/theme changes.
     */
    public void reloadUI() {
        QuestionStore.reloadQuestions();

        confirmBtn.setText(LanguageManager.t("CONFIRM"));
        quitBtn.setText(LanguageManager.t("QUIT & TAKE PRIZE"));
        rangeQuitBtn.setText(LanguageManager.t("Range Quit"));
        disconnectBtn.setText(LanguageManager.t("Disconnect"));

        confirmBtn.setFont(nirmalaBold(18));
        quitBtn.setFont(nirmalaBold(16));
        rangeQuitBtn.setFont(nirmalaBold(16));

        questionLabel.setFont(nirmalaBold(26));
        timerLabel.setFont(nirmalaBold(24));
        allQuestionsArea.setFont(nirmalaPlain(16));

        if (!multiplayerEnabled) {
            updateQuestionUIPreserveTimer();
        }

        for (Component c : optionsGrid.getComponents()) {
            if (c instanceof JButton) {
                ((JButton) c).setFont(nirmalaBold(16));
            }
        }

        if (lifelinePanel != null) lifelinePanel.reloadTexts();
        applyTheme();
    }

    /**
     * Applies current theme colors to UI components.
     */
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

    /**
     * Life line: Superposition - removes incorrect options.
     */
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

    /**
     * Life line: Entanglement - marks a wrong option as correct.
     */
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

    /**
     * Life line: Interference - highlights a suggested answer.
     */
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
        } else if (!wrongButtons.isEmpty()) {
            interfered = wrongButtons.get(rnd.nextInt(wrongButtons.size()));
        }
        if (interfered != null) {
            interfered.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
            interfered.setToolTipText(LanguageManager.t("Interfered suggestion"));
        }
    }

    /**
     * Gets the current network state of the game.
     *
     * @return network state (HOST / CLIENT / OFFLINE)
     */
    public String getNetworkState() {
        return networkState;
    }

    /**
     * Sets the network state label.
     *
     * @param state new network state value
     */
    public void setNetworkStateLabel(String state) {
        networkState = state == null ? "OFFLINE" : state;
    }

    /**
     * Refreshes network buttons (placeholder).
     */
    public void refreshNetworkButtons() {
    }

    /**
     * Enables or disables multiplayer UI mode.
     *
     * @param enabled true to enable multiplayer UI, false to disable
     */
    public void enableMultiplayerUI(boolean enabled) {
        multiplayerEnabled = enabled;
        if (confirmBtn != null) confirmBtn.setEnabled(true);
    }

    /**
     * Enables or disables answer options and confirm button.
     *
     * @param enabled true to enable options, false to disable
     */
    public void setOptionsEnabled(boolean enabled) {
        boolean allow = enabled && !hostDisplayOnly;

        for (Component c : optionsGrid.getComponents()) {
            if (c instanceof JButton) {
                c.setEnabled(allow);
            }
        }

        if (confirmBtn != null) {
            confirmBtn.setEnabled(allow);
        }
    }

    /**
     * Checks whether freeze time mode is enabled.
     *
     * @return true if freeze time is active, otherwise false
     */
    public boolean isFreezeTimeEnabled() {
        return freezeTimeEnabled;
    }

    /**
     * Enables or disables freeze time mode.
     *
     * @param enabled true to freeze timer, false to resume
     */
    public void setFreezeTimeEnabled(boolean enabled) {
        freezeTimeEnabled = enabled;
        if (countdownTimer != null) {
            if (enabled) {
                countdownTimer.stop();
                if (timerLabel != null) timerLabel.setText("FROZEN");
            } else {
                countdownTimer.restart();
            }
        }
    }

    /**
     * Toggles freeze time from settings and syncs with multiplayer if online.
     *
     * @param enabled true to enable freeze time, false to disable
     */
    public void toggleFreezeTimeFromSettings(boolean enabled) {
        setFreezeTimeEnabled(enabled);
        if (multiplayerController != null && multiplayerController.isOnline()) {
            multiplayerController.setFreezeTime(enabled);
        }
    }

    /**
     * Shows a network status message in the question log area.
     *
     * @param text message to display
     */
    public void showNetworkStatus(String text) {
        if (allQuestionsArea != null) {
            allQuestionsArea.append("\n\n" + text);
            allQuestionsArea.setCaretPosition(allQuestionsArea.getDocument().getLength());
        }
    }

    /**
     * Appends a message to the question log area.
     *
     * @param text log message
     */
    public void appendQuestionLog(String text) {
        if (allQuestionsArea != null) {
            allQuestionsArea.append("\n\n" + text);
            allQuestionsArea.setCaretPosition(allQuestionsArea.getDocument().getLength());
        }
    }

    /**
     * Adds a chat message to the chat panel.
     *
     * @param message message to display in chat
     */
    public void addChatMessage(String message) {
        if (chatPanel != null) chatPanel.addMessage(message);
    }

    /**
     * Updates multiplayer scores display.
     *
     * @param scores map of player IDs and their scores
     */
    public void updateScores(Map<Integer, Integer> scores) {
        if (scoresPanel == null) return;

        LinkedHashMap<Integer, Integer> filtered = new LinkedHashMap<>();
        for (Map.Entry<Integer, Integer> e : scores.entrySet()) {
            if (e.getKey() != 0) {
                filtered.put(e.getKey(), e.getValue());
            }
        }
        Map<Integer, String> names = (multiplayerController != null)
                ? multiplayerController.getNamesSnapshot()
                : new java.util.LinkedHashMap<>();
        scoresPanel.updateScores(filtered, names);
    }

    /**
     * Shows questions tab.
     */
    public void showQuestionsTab() {
        if (centerCardLayout != null) centerCardLayout.show(centerCards, "QUESTIONS");
    }

    /**
     * Shows chat tab.
     */
    public void showChatTab() {
        if (centerCardLayout != null) centerCardLayout.show(centerCards, "CHAT");
    }

    /**
     * Opens host configuration dialog.
     */
    public void openHostConfiguration() {
        if (multiplayerController == null) return;

        NetworkHostDialog dlg = new NetworkHostDialog(this);

        dlg.btnCancel.addActionListener(e -> dlg.dispose());
        dlg.btnClose.addActionListener(e -> dlg.dispose());
        dlg.btnApply.addActionListener(e -> {
            try {
                multiplayerController.startHost(dlg.getPlayerName(), dlg.getPort(), dlg.getSelectedPlayers());
                dlg.setStatus("Status : Hosting on port " + dlg.getPort());
                setNetworkStateLabel("HOST");
                enableMultiplayerUI(true);
                multiplayerController.hostSendQuestion();
                dlg.dispose();
            } catch (Exception ex) {
                dlg.setStatus("Status : " + ex.getMessage());
            }
        });

        dlg.setLocationRelativeTo(this);
        dlg.setVisible(true);
    }

    /**
     * Opens connect-to-server dialog.
     */
    public void openConnectConfiguration() {
        if (multiplayerController == null) return;

        NetworkConnectDialog dlg = new NetworkConnectDialog(this);

        dlg.btnCancel.addActionListener(e -> dlg.dispose());
        dlg.btnClose.addActionListener(e -> dlg.dispose());
        dlg.btnApply.addActionListener(e -> {
            try {
                multiplayerController.joinHost(dlg.getPlayerName(), dlg.getServerIP(), dlg.getPort());
                dlg.setStatus("Status : Connected to server");
                setNetworkStateLabel("CLIENT");
                enableMultiplayerUI(true);
                dlg.dispose();
            } catch (Exception ex) {
                dlg.setStatus("Status : " + ex.getMessage());
            }
        });

        dlg.setLocationRelativeTo(this);
        dlg.setVisible(true);
    }

    /**
     * Disconnects from multiplayer session.
     */
    public void disconnectNetwork() {
        if (multiplayerController != null) {
            multiplayerController.disconnect();
        }
        setNetworkStateLabel("OFFLINE");
        enableMultiplayerUI(false);
        setOptionsEnabled(true);
        setFreezeTimeEnabled(false);
        if (chatPanel != null) chatPanel.clear();
        if (scoresPanel != null) scoresPanel.updateScores(new LinkedHashMap<>());
        allQuestions.clear();
        allQuestionsArea.setText("");
        currentLevel = 1;
        loadQuestion(currentLevel);
        setHostDisplayOnly(false);
    }

    /**
     * Ends multiplayer game session.
     */
    public void finishMultiplayerGame() {
        if (multiplayerController != null) {
            openRankingWindow(multiplayerController.getNamesSnapshot(), multiplayerController.getScoresSnapshot());
        } else {
            backToHome();
        }
    }

    /**
     * Opens ranking window showing final multiplayer results.
     *
     * @param names map of player IDs to player names
     * @param scores map of player IDs to scores
     */
    public void openRankingWindow(Map<Integer, String> names, Map<Integer, Integer> scores) {
        JDialog d = new JDialog(this, "Ranking", true);
        d.setSize(360, 420);
        d.setLocationRelativeTo(this);
        d.setLayout(new BorderLayout(8, 8));

        StringBuilder sb = new StringBuilder();
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(scores.entrySet());
        list.sort((a, b) -> Integer.compare(b.getValue(), a.getValue()));

        int rank = 1;
        for (Map.Entry<Integer, Integer> e : list) {
            int id = e.getKey();
            String name = names.getOrDefault(id, "Player " + id);
            sb.append(rank++).append(". ").append(name).append(" - ").append(e.getValue()).append("\n");
        }

        JTextArea area = new JTextArea(sb.toString());
        area.setEditable(false);
        area.setFont(nirmalaPlain(16));
        d.add(new JScrollPane(area), BorderLayout.CENTER);

        JButton endBtn = new JButton("End Game");
        endBtn.addActionListener(e -> {
            d.dispose();
            disconnectNetwork();
            backToHome();
        });

        JPanel south = new JPanel(new FlowLayout(FlowLayout.CENTER));
        south.add(endBtn);
        d.add(south, BorderLayout.SOUTH);
        d.setVisible(true);
    }

    /**
     * Handles range quit in multiplayer mode.
     */
    private void rangeQuitGame() {
        if (multiplayerEnabled && multiplayerController != null) {
            multiplayerController.endGame();
            return;
        }
        quitGameTakePrize();
    }

    /**
     * Shows custom styled message dialog.
     */
    private void showMessageCustom(String message, String title, int messageType) {
        JLabel label = new JLabel(message);
        label.setFont(nirmalaBold(16));
        label.setForeground(Color.BLACK);
        JOptionPane.showMessageDialog(this, label, title, messageType);
    }

    /**
     * Displays a question and its options on screen.
     *
     * @param qText question text
     * @param options answer options
     * @param level current level number
     * @param isHost whether current user is host
     */
    public void showQuestion(String qText, String[] options, int level, boolean isHost) {
        currentLevel = level;
        questionLabel.setText(qText);
        levelProgress.setValue(level);

        optionsGrid.removeAll();

        selectedOption = null;
        selectedButton = null;

        boolean host = multiplayerController != null && multiplayerController.isHost();
        setHostDisplayOnly(host);

        for (String o : options) {
            JButton btn = new JButton(o);
            btn.setFont(nirmalaBold(16));
            btn.setBackground(ThemeManager.getOptionBackground());
            btn.setForeground(ThemeManager.getOptionForeground());
            btn.addActionListener(e -> {
                if (hostDisplayOnly) return;
                if (selectedButton != null) {
                    selectedButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                }
                selectedButton = btn;
                selectedButton.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
                selectedOption = o;
            });
            optionsGrid.add(btn);
        }

        optionsGrid.revalidate();
        optionsGrid.repaint();
        refreshLadderHighlight();

        if (freezeTimeEnabled && countdownTimer != null) {
            countdownTimer.stop();
            timerLabel.setText("FROZEN");
        }
    }

    /**
     * Displays result after answering a question.
     *
     * @param correct true if answer is correct
     * @param correctAnswer correct answer text
     * @param score current score
     */
    public void showAnswerResult(boolean correct, String correctAnswer, int score) {
        String text = correct
                ? "Correct! Score: " + score
                : "Wrong! Correct answer: " + correctAnswer;

        if (allQuestionsArea != null) {
            allQuestionsArea.append("\n\n" + text);
            allQuestionsArea.setCaretPosition(allQuestionsArea.getDocument().getLength());
        }
    }
}