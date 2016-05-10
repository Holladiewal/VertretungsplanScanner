package me.beepbeat;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Stundenplan {
    public int jahrgang = 10;
    public String KlassenZusatz = "c";
    public String klasse = jahrgang + KlassenZusatz;
    public HashMap<String, List<Stunde>> Plan = new HashMap<>();

    public Stundenplan(){
        Plan.put("Montag", new ArrayList<Stunde>());
        Plan.put("Dienstag", new ArrayList<Stunde>());
        Plan.put("Mittwoch", new ArrayList<Stunde>());
        Plan.put("Donnerstag", new ArrayList<Stunde>());
        Plan.put("Freitag", new ArrayList<Stunde>());

    }

    public Stunde createStunde(int stunde, String Lehrer, String Fach){
        return new Stunde(stunde, Lehrer, Fach);
    }

    public class Stunde {
        int stunde;
        String Lehrer;
        String Fach;

        public Stunde(int stunde, String Lehrer, String Fach){
            this.stunde = stunde;
            this.Lehrer = Lehrer;
            this.Fach = Fach;
        }

        @Override
        public String toString() {
            return stunde + "," + Fach + "," + Lehrer;
        }
    }
    void addStunde(int stunde, String Lehrer, String Fach, String Tag){
        Plan.get(Tag).add(stunde - 1, new Stunde(stunde, Lehrer, Fach));
    }

    @Override
    public String toString() {
        String retV = "";
        for (List<Stunde> stundeList : Plan.values()) {
            for (Stunde s : stundeList){
                if (s == null) continue;
                retV += s.toString();
            }
        }

        return super.toString();
    }
}
