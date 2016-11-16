import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by Patrick on 16.11.2016.
 */
public class DSBTextField extends JPanel {
    DSBTextField(boolean rollable, String descr){
        super();

        //this.setLayout(new GridLayout(1,3));

        JLabel description = new JLabel(descr + ": ");

        description.setPreferredSize(description.getPreferredSize());
        this.add(description);

        if (rollable) {
            ImageIcon img = new ImageIcon("res/dice.png");
            JLabel rollPic = new JLabel(new ImageIcon(img.getImage().getScaledInstance(25,25, Image.SCALE_SMOOTH)));
            rollPic.setBorder(BorderFactory.createMatteBorder(0,1,0,1,Color.gray));
            this.add(rollPic);
        }

        JTextField textField = new JTextField("",10);
        textField.setBorder(null);

        this.setBackground(Color.white);
        this.setOpaque(true);

        this.add(textField);
    }
}
