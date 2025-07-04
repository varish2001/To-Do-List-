import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ToDoListGui extends JFrame implements ActionListener {
    private JPanel taskPanel, taskComponentPanel;

    public ToDoListGui() {
        super("To Do List Application ");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(CommonConstants.GUI_SIZE);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);

        addGuiComponents();
        pack();
    }

    private void addGuiComponents() {
        // banner text
        JLabel bannerLabel = new JLabel("To Do List", SwingConstants.CENTER);

        bannerLabel.setFont(createFont("resources/Light.otf", 36f));
        bannerLabel.setBounds(
            0,
            15,
            CommonConstants.BANNER_SIZE.width,
            CommonConstants.BANNER_SIZE.height
        );

        // task panel and component panel
        taskPanel = new JPanel();
        taskPanel.setLayout(new BorderLayout());
        taskComponentPanel = new JPanel();
        taskComponentPanel.setLayout(new BoxLayout(taskComponentPanel, BoxLayout.Y_AXIS));
        taskPanel.add(taskComponentPanel, BorderLayout.CENTER);

        // add scrolling to the task panel
        JScrollPane scrollPane = new JScrollPane(taskPanel);
        scrollPane.setBorder(BorderFactory.createLoweredBevelBorder());
        scrollPane.setBounds(8, 70, CommonConstants.TASKPANEL_SIZE.width, CommonConstants.TASKPANEL_SIZE.height);
        scrollPane.setMaximumSize(CommonConstants.TASKPANEL_SIZE);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // changing the speed of the scroll bar
        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(20);

        // add task button
        JButton addTaskButton = new JButton("Add Task");
        addTaskButton.setFont(createFont("resources/Light.otf", 18f));
        addTaskButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addTaskButton.setBounds(
            0,
            CommonConstants.GUI_SIZE.height - 88,
            CommonConstants.ADDTASK_BUTTON_SIZE.width,
            CommonConstants.ADDTASK_BUTTON_SIZE.height
        );
        addTaskButton.addActionListener(this);

        // ADD TO FRAME
        this.getContentPane().setLayout(null);
        this.getContentPane().add(bannerLabel);
        this.getContentPane().add(scrollPane);
        this.getContentPane().add(addTaskButton);
    }

    private Font createFont(String resource, float size){
        // get the font file path
        java.net.URL url = getClass().getClassLoader().getResource(resource);
        if (url == null) {
            System.out.println("Font resource not found: " + resource);
            return new Font("SansSerif", Font.PLAIN, (int)size); // fallback font
        }
        String filePath = url.getPath();

        if(filePath.contains("%20")){
            filePath = filePath.replaceAll("%20", " ");
        }

        try{
            File customFontFile = new File(filePath);
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, customFontFile).deriveFont(size);
            return customFont;
        }catch(Exception e){
            System.out.println("Error :" + e);
            return new Font("SansSerif", Font.PLAIN, (int)size); // fallback font
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equalsIgnoreCase("Add Task")) {
            // Add a small vertical gap between tasks, but only if there is already a task
            if (taskComponentPanel.getComponentCount() > 0) {
                taskComponentPanel.add(Box.createVerticalStrut(5));
            }

            // create a task component
            TaskComponent taskComponent = new TaskComponent(taskComponentPanel);
            taskComponentPanel.add(taskComponent);

            // make the task field request focus after creation
            taskComponent.getTaskField().requestFocusInWindow();
            taskComponentPanel.revalidate();
            taskComponentPanel.repaint();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ToDoListGui().setVisible(true);
        });
    }
}
