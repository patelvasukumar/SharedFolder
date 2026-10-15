package MainView.Design;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import CommonComponents.CommonComponents;
import Controller.Design.QuestionController;
import Model.Design.Question;
import Model.Exception.GameException;

/**
 * The QuestionEditor class provides a graphical interface
 * for managing questions in the system.
 */
public class QuestionEditor extends JFrame {

    private static final long serialVersionUID = 1L;

    /**
     * Common UI components helper for styling.
     */
    private CommonComponents components = new CommonComponents();
    
    /**
     * Controller responsible for question operations.
     */
    private QuestionController controller = new QuestionController();

    /**
     * Table used to display questions.
     */
    private JTable table;
    
    /**
     * Table model used to manage table data.
     */
    private DefaultTableModel tableModel;

    /**
     * Constructs the QuestionEditor window and initializes
     * UI components, layout, and event handling.
     */
    public QuestionEditor() {

        setTitle("Question Editor");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton home = components.createButton("HOME");
        home.setPreferredSize(new Dimension(120, 40));

        topPanel.add(home);
        add(topPanel, BorderLayout.NORTH);

        String[] columns = {"Level", "Question 1", "Question 2", "Price"};

        tableModel = new DefaultTableModel(columns, 0) {
        	
            
			private static final long serialVersionUID = 1L;


            /**
             * Prevents editing of table cells.
             */
			@Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
			
        };
        
        table = new JTable(tableModel);

        loadTable();

        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel bottom = new JPanel();

        JButton addBtn = components.optionButton("Add", 1);
        JButton editBtn = components.optionButton("Edit", 0);
        JButton delBtn = components.optionButton("Delete", 0);

        bottom.add(addBtn);
        bottom.add(editBtn);
        bottom.add(delBtn);

        add(bottom, BorderLayout.SOUTH);

        /**
         * Navigates back to DesignMode.
         */
        home.addActionListener(e -> {
            dispose();
            new DesignMode();
        });

        addBtn.addActionListener(e -> addQuestion());
        editBtn.addActionListener(e -> editQuestion());
        delBtn.addActionListener(e -> deleteQuestion());

        setVisible(true);
    }

    /**
     * Loads questions into the table for all levels (1–15).
     * If a question does not exist, empty placeholders are shown.
     */
    private void loadTable() {

        tableModel.setRowCount(0);

        for (int level = 1; level <= 15; level++) {

            Question q = controller.getQuestion(level);

            if (q != null) {

                tableModel.addRow(new Object[]{
                        q.getLevel(),
                        q.getQuestion1(),
                        q.getQuestion2(),
                        q.getPrice()
                });

            } else {

                tableModel.addRow(new Object[]{
                        level,
                        "",
                        "",
                        controller.getPrice(level)
                });
            }
        }
    }

    /**
     * Opens dialog inputs to add a new question.
     * Validates level and input values before saving.
     */
    private void addQuestion() {

        try {
            String levelStr = JOptionPane.showInputDialog(this, "Enter Level (1-15)");
            if (levelStr == null) return; // cancelled
            int level = Integer.parseInt(levelStr.trim());
            if (level < 1 || level > 15) {
                JOptionPane.showMessageDialog(this, "Level must be between 1 and 15.");
                return;
            }

            String q1 = JOptionPane.showInputDialog(this, "Enter Question 1");
            if (q1 == null) return;

            String q1op1 = JOptionPane.showInputDialog(this, "Question 1 - Option 1 (correct)");
            if (q1op1 == null) return;
            String q1op2 = JOptionPane.showInputDialog(this, "Question 1 - Option 2 (wrong)");
            if (q1op2 == null) return;
            String q1op3 = JOptionPane.showInputDialog(this, "Question 1 - Option 3 (wrong)");
            if (q1op3 == null) return;
            String q1op4 = JOptionPane.showInputDialog(this, "Question 1 - Option 4 (wrong)");
            if (q1op4 == null) return;

            String q2 = JOptionPane.showInputDialog(this, "Enter Question 2");
            if (q2 == null) return;

            String q2op1 = JOptionPane.showInputDialog(this, "Question 2 - Option 1 (correct)");
            if (q2op1 == null) return;
            String q2op2 = JOptionPane.showInputDialog(this, "Question 2 - Option 2 (wrong)");
            if (q2op2 == null) return;
            String q2op3 = JOptionPane.showInputDialog(this, "Question 2 - Option 3 (wrong)");
            if (q2op3 == null) return;
            String q2op4 = JOptionPane.showInputDialog(this, "Question 2 - Option 4 (wrong)");
            if (q2op4 == null) return;

            controller.addQuestion(
                    level,
                    q1,
                    q2,
                    q1op1, q1op2, q1op3, q1op4,
                    q2op1, q2op2, q2op3, q2op4
            );

            loadTable();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid level. Please enter a number between 1 and 15.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error adding question: " + ex.getMessage());
        }
    }

    /**
     * Allows editing of the selected question.
     * Updates question details after validation.
     */
    private void editQuestion() {

        try {

            int row = table.getSelectedRow();

            if (row == -1) GameException.raise("Select a level first");

            Object lvlObj = tableModel.getValueAt(row, 0);
            int level = (lvlObj instanceof Number) ? ((Number) lvlObj).intValue() : Integer.parseInt(lvlObj.toString());

            Question q = controller.getQuestion(level);

            if (q == null) {
                JOptionPane.showMessageDialog(this, "No question exists at the selected level. Use Add to create it.");
                return;
            }

            String q1 = JOptionPane.showInputDialog(this, "Edit Question 1", q.getQuestion1());
            if (q1 == null) return;

            String q1op1 = JOptionPane.showInputDialog(this, "Question 1 - Option 1 (correct)", q.getQ1op1());
            if (q1op1 == null) return;
            String q1op2 = JOptionPane.showInputDialog(this, "Question 1 - Option 2 (wrong)", q.getQ1op2());
            if (q1op2 == null) return;
            String q1op3 = JOptionPane.showInputDialog(this, "Question 1 - Option 3 (wrong)", q.getQ1op3());
            if (q1op3 == null) return;
            String q1op4 = JOptionPane.showInputDialog(this, "Question 1 - Option 4 (wrong)", q.getQ1op4());
            if (q1op4 == null) return;

            String q2 = JOptionPane.showInputDialog(this, "Edit Question 2", q.getQuestion2());
            if (q2 == null) return;

            String q2op1 = JOptionPane.showInputDialog(this, "Question 2 - Option 1 (correct)", q.getQ2op1());
            if (q2op1 == null) return;
            String q2op2 = JOptionPane.showInputDialog(this, "Question 2 - Option 2 (wrong)", q.getQ2op2());
            if (q2op2 == null) return;
            String q2op3 = JOptionPane.showInputDialog(this, "Question 2 - Option 3 (wrong)", q.getQ2op3());
            if (q2op3 == null) return;
            String q2op4 = JOptionPane.showInputDialog(this, "Question 2 - Option 4 (wrong)", q.getQ2op4());
            if (q2op4 == null) return;

            Question updated = new Question(
                    q.getLevel(),
                    q1,
                    q2,
                    q.getPrice(),

                    q1op1, q1op2, q1op3, q1op4,
                    q2op1, q2op2, q2op3, q2op4
            );

            controller.updateQuestion(updated);

            loadTable();

        } catch (GameException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid level value in table.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error editing question: " + ex.getMessage());
        }
    }

    /**
     * Deletes the selected question after confirmation.
     */
    private void deleteQuestion() {

        try {

            int row = table.getSelectedRow();

            if (row == -1) GameException.raise("Select a level first");

            Object lvlObj = tableModel.getValueAt(row, 0);
            int level = (lvlObj instanceof Number) ? ((Number) lvlObj).intValue() : Integer.parseInt(lvlObj.toString());

            int confirm = JOptionPane.showConfirmDialog(this, "Delete questions for level " + level + " ?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm != JOptionPane.YES_OPTION) return;

            controller.deleteQuestion(level);

            loadTable();

        } catch (GameException ex) {

            JOptionPane.showMessageDialog(this, ex.getMessage());

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(this, "Error deleting question: " + ex.getMessage());

        }
    }
}