package me.beepbeat;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VertretungsPlan {
    int offset;
    List<line> lines = new ArrayList<line>();

    class line{
        HashMap<String, String> columns = new HashMap<>();

        line(String s){
            String[] s1 = s.split(" ");
            columns.put("Klasse",s1[0]);
            columns.put("Stunde",s1[1]);
            columns.put("Lehrer",s1[2]);
            columns.put("Ersatzfach",s1[3]);
            columns.put("Originalfach",s1[4]);
            columns.put("Raum",s1[5]);
            columns.put("Typ",s1[6]);

        }

        @Override
        public String toString() {
            return columns.get("Klasse") + " " + columns.get("Stunde") + " " + columns.get("Lehrer") + " " + columns.get("Ersatzfach") + " " + columns.get("Originalfach") + " " + columns.get("Raum") + " " + columns.get("Typ");
        }
    }
    public void addLine(String... strings){
        if (strings.length < 7) throw new IllegalArgumentException("Argument must have atleast 7 entries");
        String s1 = "";
        for (String s : strings){
            s1 = s1 + s + " ";
        }
        lines.add(new line(s1));
    }


}
