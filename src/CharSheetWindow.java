import javax.swing.*;
import java.awt.*;

/**
 * Created by Patrick on 16.11.2016.
 */
public class CharSheetWindow {
    private JPanel rootPanel;
    private JFrame rootFrame;

    public CharSheetWindow(){
        rootFrame = new JFrame();

        setupUI();

        rootFrame.pack();
        rootFrame.setLocationRelativeTo(null);
        rootFrame.setVisible(true);
    }

    private void setupUI() {

        rootPanel = new JPanel();
        //rootPanel.setLayout(new BoxLayout(rootPanel, 1));

        DSBTextField labl = new DSBTextField(true, "Alter", "_int");
        rootPanel.add(labl);

        rootFrame.setContentPane(this.rootPanel);
        rootFrame.setBackground(Color.cyan);

        rootFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        rootFrame.setPreferredSize(new Dimension(900,500));
    }
}
