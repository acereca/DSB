import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by Patrick on 16.11.2016.
 */
public class DSBField extends JPanel {
    DSBField(boolean rollable, String descr, String type, String[] enumlist){
        super();

        int gap = 8;
        this.setLayout(new FlowLayout(0,gap,0));
        this.setBorder(new EmptyBorder(0,0,0,-gap));

        JLabel description = new JLabel(descr + ": ");

        description.setPreferredSize(description.getPreferredSize());
        this.add(description);

        if (rollable) {
            ImageIcon img = new ImageIcon("res/dice.png");
            JButton rollPic = new JButton(new ImageIcon(img.getImage().getScaledInstance(25,25, Image.SCALE_SMOOTH)));
            chgValBtn(rollPic);
            rollPic.setBorder(BorderFactory.createMatteBorder(0,1,0,1,Color.decode("#dddddd")));
            this.add(rollPic);
        }



        if (type.equals("_int")){
            NumberFormat format = NumberFormat.getIntegerInstance();
            NumberFormatter formatter = new NumberFormatter(format);
            formatter.setValueClass(Integer.class);
            formatter.setMinimum(-999);
            formatter.setMaximum(999);
            formatter.setAllowsInvalid(false);
            formatter.setCommitsOnValidEdit(true);
            JFormattedTextField textField;
            textField = new JFormattedTextField(formatter);
            textField.setColumns(2);
            textField.setBorder(null);
            textField.setValue(0);
            this.add(textField);

            JPanel chngVal = new JPanel();
            chngVal.setLayout(new GridLayout(2,1,0,0));

            JButton inc = new JButton("+");
            inc.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int v = Integer.parseInt(textField.getText());
                    textField.setText(Integer.toString(v+1));
                }
            });
            chgValBtn(inc);
            chngVal.add(inc);

            JButton dec = new JButton("-");
            dec.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int v = Integer.parseInt(textField.getText());
                    textField.setText(Integer.toString(v-1));
                }
            });
            chgValBtn(dec);
            chngVal.add(dec);

            chngVal.setBackground(Color.white);
            this.add(chngVal);

        } else if (type.equals("_string")) {
            JTextField textField;
            textField = new JTextField();
            textField.setColumns(15);
            textField.setBorder(null);
            this.add(textField);

        } else if (type.equals("_enum")) {

            JComboBox enumDropdown = new JComboBox<String>(enumlist);
            enumDropdown.setUI((ComboBoxUI) MyComboBoxUI.createUI(enumDropdown));
            enumDropdown.setBorder(null);
            enumDropdown.setBackground(Color.white);
            enumDropdown.setOpaque(true);

            this.add(enumDropdown);
        }

        this.setBackground(Color.white);
        this.setOpaque(true);
        this.setSize(100,10);
    }

    void stdConstruction(){

    }

    void chgValBtn(JButton btn){
        btn.setContentAreaFilled(false);
        btn.setBorder(new EmptyBorder(0,5,0,5));
        btn.setOpaque(true);
        btn.setSize(btn.getHeight(),btn.getHeight());
        btn.setBackground(Color.white);

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                JButton button = (JButton) e.getSource();
                button.setBackground(Color.decode("#cccccc"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                JButton button = (JButton) e.getSource();
                button.setBackground(Color.white);
            }
        });
    }

    //Design Class for Dropdown Menus
    static class MyComboBoxUI extends BasicComboBoxUI {
        public static ComponentUI createUI(JComponent c) {
            return new MyComboBoxUI();
        }

        protected JButton createArrowButton() {
            this.comboBox.setBorder(null);

            JButton button = new JButton("v");
            button.setBorder(null);
            button.setOpaque(true);
            button.setBackground(Color.white);
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    JButton btn = (JButton) e.getSource();
                    btn.setBackground(Color.decode("#eeeeee"));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    JButton btn = (JButton) e.getSource();
                    btn.setBackground(Color.white);
                }
            });
            return button;
        }

        protected ComboPopup createPopup() {
            BasicComboPopup bcp = (BasicComboPopup) super.createPopup();



            // set the border around the popup
            bcp.setBorder(new LineBorder(Color.decode("#aaaaaa"),1));

            //there is an inner border around the list, insdie the scroller
            //    it can be set thus:
            JList list = bcp.getList();
            list.setBorder(null);
            list.setBackground(Color.white);
            list.setSelectionBackground(Color.white);

            return bcp;
        }
    }
}
