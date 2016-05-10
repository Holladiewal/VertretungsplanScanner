package me.beepbeat;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class Persistence {
    public enum Strings {Montag, Dienstag, Mittwoch, Donnerstag, Freitag}
    static HashMap<String, List<Stundenplan.Stunde>> sPlan = Main.sPlan.Plan;
    public final static strictfp void save(){

        File persistence = new File("persistence");
            try {
                persistence.delete();
                persistence.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
        }

            for (int i = 0; i < Strings.values().length ; i++) {
                String write = Strings.values()[i].toString() + " BEGIN \n";
                try {
                    for (int j = 0; j < ((List) sPlan.get(Strings.values()[i].toString())).size(); j++) {
                        //write.add(((Stundenplan.Stunde) ((List) sPlan.get(Strings.values()[i])).get(j)).toString() + ";");
                        write += ((Stundenplan.Stunde)
                            ((List) sPlan.get(
                                Strings.values()[i].toString()))
                                .get(j))
                            .toString() +
                            "\n";

                    }
                    write += "END";
                    FileUtils.write(persistence, write, true);
                    write = "";
                    FileUtils.write(persistence, "\n", true);


                } catch (IOException e) {
                    e.printStackTrace();
                }

            }



        //root.add()
    }
    public final static strictfp void load(){
        File persistence = new File("persistence");
        if (!persistence.exists()){
            System.out.println("ERROR: persistence file not found. Disregard on first run!");
            return;
        }
        try {
            List<String> lines = FileUtils.readLines(persistence);
            if (lines.size() == 0 || lines.get(0).equalsIgnoreCase("")) return;
            for (int d = 0; d < 6; d++) {
                if (lines.get(0).equalsIgnoreCase("END")) lines.remove(0);
                if (lines.size() == 0 || lines.get(0) == null || lines.get(0).equalsIgnoreCase("")) return;
                String dayName = lines.get(0).split(" ")[0];
                List<Stundenplan.Stunde> day = sPlan.get(dayName);
                if (day == null) return;
                lines.remove(0);

                //String s1 = lines.get(0);
                //String[] s1A = s1.split(",");
                for (int i = 0; i < 11; i++) {
                    day.add(null);
                }
                while (!lines.get(0).equalsIgnoreCase("END")) {
                    String s = lines.get(0);
                    String[] sA = s.split(",");
                    day.set(Integer.parseInt(s.split(",")[0]) - 1, Main.sPlan.createStunde(Integer.parseInt(sA[0]), sA[2], sA[1]));
                    lines.remove(0);
                }
                for (int x = 0; x < day.size(); x++){
                    if (day.get(x) == null) {day.remove(x);x--;}
                }
                sPlan.put(dayName, day);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
