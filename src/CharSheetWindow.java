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
        rootPanel.setLayout(new GridLayout(10,1));

        JLabel labl = new JLabel("hi");
        rootPanel.add(labl);

        rootFrame.setContentPane(this.rootPanel);
        rootFrame.setBackground(Color.cyan);

        rootFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        rootFrame.setSize(100,100);
    }
}
