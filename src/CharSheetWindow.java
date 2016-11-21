import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.io.*;

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

        JSONObject charSheet = genJSON();

        String[] lst = {"a","feqwb"};
        //DSBField labl = new DSBField(true, "Alter", "_enum", lst );
        //rootPanel.add(labl);

        for (Object group: charSheet.keySet()){

            JSONObject groupEntries = (JSONObject) charSheet.get(group);
            for (Object entry: groupEntries.keySet()){
                JSONObject characteristic = (JSONObject) groupEntries.get(entry);

                Object rollable = characteristic.get("_rollable");
                String type = characteristic.get("_type").toString();

                String[] enumList;
                try {
                    JSONArray enumArray = (JSONArray) characteristic.get("_enumlist");
                    enumList = new String[enumArray.toArray().length];
                    for (int i = 0; i < enumList.length; i++) {
                        enumList[i] = (String) enumArray.get(i);
                    }
                } catch (NullPointerException e) {
                    try {
                        JSONObject enumObject = (JSONObject) characteristic.get("_enumdict");
                        enumList
                    }
                }



                DSBField field = new DSBField(
                        rollable != null && (boolean) rollable,
                        (String) entry,
                        type,
                        enumList);
                System.out.println(type);
                rootPanel.add(field);
            }
        }



        rootFrame.setContentPane(this.rootPanel);
        rootFrame.setBackground(Color.cyan);

        rootFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        rootFrame.setPreferredSize(new Dimension(900,500));
    }

    private JSONObject genJSON(){

        JSONParser parse = new JSONParser();
        JSONObject json = null;
        try {
            Object obj = parse.parse(new FileReader("res/charsheet.json"));
            json = (JSONObject) obj;
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;
    }
}
