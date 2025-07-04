import java.awt.Dimension;

public class CommonConstants {
    // frame config
    public static final Dimension GUI_SIZE = new Dimension(540, 760);

    // banner config
    public static final Dimension BANNER_SIZE = new Dimension(GUI_SIZE.width, 50);

    // task panel config
    public static final Dimension TASKPANEL_SIZE = new Dimension(GUI_SIZE.width - 30, GUI_SIZE.height - 175);

    // add task button config
    public static final Dimension ADDTASK_BUTTON_SIZE = new Dimension(GUI_SIZE.width, 50);

    // taskcomponent config
    public static final Dimension TASKFEILD_SIZE = new Dimension((int) (TASKPANEL_SIZE.width * 0.80), 50);

    public static final Dimension CHECKBOX_SIZE = new Dimension((int) (TASKFEILD_SIZE.width * 0.05), 50);

    public static final Dimension DELETE_BUTTON_SIZE = new Dimension((int) (TASKFEILD_SIZE.width * 0.12), 50);
}

