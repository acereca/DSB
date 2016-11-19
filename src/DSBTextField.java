import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by Patrick on 16.11.2016.
 */
public class DSBTextField extends JPanel {
    DSBTextField(boolean rollable, String descr, String type){
        super();

        this.setLayout(new FlowLayout(0,5,0));

        JLabel description = new JLabel(descr + ": ");

        description.setPreferredSize(description.getPreferredSize());
        this.add(description);

        if (rollable) {
            ImageIcon img = new ImageIcon("res/dice.png");
            JLabel rollPic = new JLabel(new ImageIcon(img.getImage().getScaledInstance(25,25, Image.SCALE_SMOOTH)));
            rollPic.setBorder(BorderFactory.createMatteBorder(0,1,0,1,Color.decode("#dddddd")));
            this.add(rollPic);
        }

        JFormattedTextField textField;
        JPanel chngVal = new JPanel();

        if (type == "_int"){
            NumberFormat format = NumberFormat.getIntegerInstance();
            NumberFormatter formatter = new NumberFormatter(format);
            formatter.setValueClass(Integer.class);
            formatter.setMinimum(-999);
            formatter.setMaximum(999);
            formatter.setAllowsInvalid(false);
            formatter.setCommitsOnValidEdit(true);
            textField = new JFormattedTextField(formatter);
            textField.setColumns(2);

            textField.setValue(0);

            chngVal.setLayout(new GridLayout(2,1,0,0));
            JButton inc = new JButton("+");
            inc.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int v = Integer.parseInt(textField.getText());
                    textField.setText(Integer.toString(v+1));
                }
            });
            inc.setContentAreaFilled(false);
            inc.setBorder(null);
            chngVal.add(inc);

            JButton dec = new JButton("-");
            dec.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int v = Integer.parseInt(textField.getText());
                    textField.setText(Integer.toString(v-1));
                }
            });
            dec.setContentAreaFilled(false);
            dec.setBorder(null);
            chngVal.add(dec);

            chngVal.setBackground(Color.white);


        } else {
            textField = new JFormattedTextField();
            textField.setColumns(15);
        }

        textField.setBorder(null);
        this.add(textField);
        this.add(chngVal);

        this.setBackground(Color.white);
        this.setOpaque(true);
    }
}
