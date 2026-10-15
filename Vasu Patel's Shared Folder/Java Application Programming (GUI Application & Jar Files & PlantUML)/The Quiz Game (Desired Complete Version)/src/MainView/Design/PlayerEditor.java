package MainView.Design;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import CommonComponents.CommonComponents;
import Controller.Design.PlayerController;
import Model.Design.Player;
import Model.Exception.GameException;

public class PlayerEditor extends JFrame {

    private static final long serialVersionUID = 1L;

    private CommonComponents components = new CommonComponents();
    private PlayerController controller = new PlayerController();

    private JTable table;
    private DefaultTableModel tableModel;

    private JFrame parentDesign;

    public PlayerEditor(JFrame parent) {

        this.parentDesign = parent;

        setTitle("Player Editor");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setOpaque(false);

        JButton home = components.createButton("HOME");
        home.setFont(home.getFont().deriveFont(14f));
        home.setMaximumSize(new Dimension(120,40));

        topPanel.add(home);
        add(topPanel, BorderLayout.NORTH);

        String[] columns = {"ID", "Name", "Gender", "Nationality", "Current Point", "Historic Point"};

        tableModel = new DefaultTableModel(columns,0);
        table = new JTable(tableModel);

        loadTable();

        JScrollPane scroll = new JScrollPane(table);
        add(scroll,BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        bottom.setOpaque(false);
        bottom.setLayout(new FlowLayout(FlowLayout.CENTER,20,10));

        JButton addBtn = components.optionButton("Add",1);
        JButton editBtn = components.optionButton("Edit",0);
        JButton delBtn = components.optionButton("Delete",0);

        bottom.add(addBtn);
        bottom.add(editBtn);
        bottom.add(delBtn);

        add(bottom,BorderLayout.SOUTH);

        home.addActionListener(e -> {
            dispose();
            if(parentDesign != null) parentDesign.setVisible(true);
        });

        addBtn.addActionListener(e -> showAddDialog());
        editBtn.addActionListener(e -> showEditDialog());
        delBtn.addActionListener(e -> doDelete());

        setVisible(true);
    }

    private void loadTable() {

        tableModel.setRowCount(0);

        for(Player p : controller.getPlayers()) {

            tableModel.addRow(new Object[]{
                    p.getId(),
                    p.getName(),
                    p.getGender(),
                    p.getNationality(),
                    p.getCurrentPoint(),
                    p.getHistoricPoint()
            });
        }
    }

    private void showAddDialog() {

        String name = JOptionPane.showInputDialog(this,"Enter player name:");

        if(name == null) return;

        name = name.trim();

        if(name.isEmpty()) {
            JOptionPane.showMessageDialog(this,"Name cannot be empty.");
            return;
        }

        String[] genders = {"Male","Female","Other"};

        String gender = (String) JOptionPane.showInputDialog(
                this,
                "Select Gender:",
                "Gender",
                JOptionPane.QUESTION_MESSAGE,
                null,
                genders,
                genders[0]);

        if(gender == null) return;

        String[] nations = {"Indian","Canadian","Other"};

        String nationality = (String) JOptionPane.showInputDialog(
                this,
                "Select Nationality:",
                "Nationality",
                JOptionPane.QUESTION_MESSAGE,
                null,
                nations,
                nations[0]);

        if(nationality == null) return;

        if(nationality.equals("Other")) {

            nationality = JOptionPane.showInputDialog(this,"Enter Nationality:");

            if(nationality == null || nationality.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this,"Nationality cannot be empty.");
                return;
            }
        }

        // ask for initial points, but validate robustly
        int currentPoint = 0;
        int historicPoint = 0;

        String curStr = JOptionPane.showInputDialog(this, "Enter Current Point (leave blank for 0):", "0");
        if (curStr == null) return; // user cancelled
        curStr = curStr.trim();
        if (!curStr.isEmpty()) {
            try {
                currentPoint = Integer.parseInt(curStr);
                if (currentPoint < 0) {
                    JOptionPane.showMessageDialog(this, "Points cannot be negative. Using 0.");
                    currentPoint = 0;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid number for Current Point. Operation cancelled.");
                return;
            }
        }

        String histStr = JOptionPane.showInputDialog(this, "Enter Historic Point (leave blank for 0):", "0");
        if (histStr == null) return; // cancelled
        histStr = histStr.trim();
        if (!histStr.isEmpty()) {
            try {
                historicPoint = Integer.parseInt(histStr);
                if (historicPoint < 0) {
                    JOptionPane.showMessageDialog(this, "Points cannot be negative. Using 0.");
                    historicPoint = 0;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid number for Historic Point. Operation cancelled.");
                return;
            }
        }

        controller.addPlayer(name, gender, nationality, currentPoint, historicPoint);

        loadTable();
    }

    private void showEditDialog() {

        try {

            int row = table.getSelectedRow();

            if(row == -1) {
                GameException.raise("Please select a player first.");
            }

            int id = (int) tableModel.getValueAt(row,0);

            Player selected = controller.getPlayers()
                    .stream()
                    .filter(p -> p.getId() == id)
                    .findFirst()
                    .orElse(null);

            if(selected == null) return;

            String newName = JOptionPane.showInputDialog(this,"Edit name:",selected.getName());

            if(newName == null) return;

            String[] genders = {"Male","Female","Other"};

            String newGender = (String) JOptionPane.showInputDialog(
                    this,
                    "Select Gender:",
                    "Gender",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    genders,
                    selected.getGender()
            );

            if(newGender == null) return;

            String[] nations = {"Indian","Canadian","Other"};

            String newNation = (String) JOptionPane.showInputDialog(
                    this,
                    "Select Nationality:",
                    "Nationality",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    nations,
                    selected.getNationality()
            );

            if(newNation == null) return;

            if(newNation.equals("Other")) {

                newNation = JOptionPane.showInputDialog(this,"Enter Nationality:");

                if(newNation == null || newNation.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this,"Nationality cannot be empty.");
                    return;
                }
            }

            // Edit points with validation
            String curStr = JOptionPane.showInputDialog(this,"Edit Current Point:", String.valueOf(selected.getCurrentPoint()));
            if (curStr == null) return;
            curStr = curStr.trim();
            int newCurrentPoint;
            try {
                newCurrentPoint = Integer.parseInt(curStr);
                if (newCurrentPoint < 0) {
                    JOptionPane.showMessageDialog(this, "Points cannot be negative. Operation cancelled.");
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid number for Current Point. Operation cancelled.");
                return;
            }

            String histStr = JOptionPane.showInputDialog(this,"Edit Historic Point:", String.valueOf(selected.getHistoricPoint()));
            if (histStr == null) return;
            histStr = histStr.trim();
            int newHistoricPoint;
            try {
                newHistoricPoint = Integer.parseInt(histStr);
                if (newHistoricPoint < 0) {
                    JOptionPane.showMessageDialog(this, "Points cannot be negative. Operation cancelled.");
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid number for Historic Point. Operation cancelled.");
                return;
            }

            selected.setName(newName);
            selected.setGender(newGender);
            selected.setNationality(newNation);
            selected.setCurrentPoint(newCurrentPoint);
            selected.setHistoricPoint(newHistoricPoint);

            controller.updatePlayer(selected);

            loadTable();

        }
        catch(GameException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private void doDelete() {

        try {

            int row = table.getSelectedRow();

            if(row == -1) {
                GameException.raise("Please select a player first.");
            }

            int id = (int) tableModel.getValueAt(row,0);

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Delete this player?",
                    "Confirm",
                    JOptionPane.YES_NO_OPTION
            );

            if(confirm == JOptionPane.YES_OPTION) {

                controller.removePlayer(id);

                loadTable();
            }

        }
        catch(GameException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
}