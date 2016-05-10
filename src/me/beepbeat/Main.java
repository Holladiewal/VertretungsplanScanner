package me.beepbeat;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class Main {
    static VertretungsPlan vPlan = new VertretungsPlan();
    static Stundenplan sPlan = new Stundenplan();

    public static void removeFirstTag(List<String> in, String tag){
        boolean delete = false;
        for (int i = 0; i < in.size(); i++) {
            String s = in.get(i);
            if (s.contains("<" + tag + "")) delete = true;
            if (s.contains("</" + tag)) {
                in.remove(i);
                break;
            }
            if (delete) {in.remove(i); i--;}
        }
    }

    public static void main(String[] args) throws Exception {
        //System.out.println("test");
        URL vertretungsplanURL = new URL("http://nibis.ni.schule.de/~kwrg/infostundenplan/1/subst_001.htm");
        List<String> response = null;
        try {
            //response = IOUtils.readLines((InputStream) vertretungsplanURL.openConnection().getContent(), vertretungsplanURL.openConnection().getContentEncoding());
            response = IOUtils.readLines((InputStream) vertretungsplanURL.openConnection().getContent(), Charset.forName("iso-8859-1"));
        } catch (ConnectException e) {
            System.out.println("Error connecting to Server");
            System.exit(-1);
        }
        //vertretungsplanURL.openConnection().getContent();

        removeFirstTag(response, "head");
        removeFirstTag(response, "table");
        removeFirstTag(response, "div");
        removeFirstTag(response, "tr");
        removeFirstTag(response, "tr");

        for (int i = 0; i < 6; i++){
            response.remove(0);
        }
        int count = 0;
        while (response.get(0).contains("<tr")) {
                removeFirstTag(response, "tr");
        }
        response.remove(0);response.remove(0);response.remove(0);
        List<String> wichtigerTeil = new ArrayList<>();
        while (response.get(0).contains("<tr")){
            wichtigerTeil.add(response.get(0));
            response.remove(0);
        }
        for (String s : wichtigerTeil) {
            List<String> test = new ArrayList<>();
            test.addAll(Arrays.asList(s.split("<")));
            //System.out.println("test = " + test);
            for (int i = 0; i < test.size(); i++) {
                String s1 = test.get(i);
                if (s1.equalsIgnoreCase("")) continue;
                if (s1.charAt(0) == '/') {
                    test.remove(i);
                    i--;
                }
            }
            test.remove(0);
            test.remove(0);
            test.remove(0);
            for (int i = 0; i < test.size(); i++) {
                try {
                    test.set(i, test.get(i).split(">")[1]);
                } catch (Exception e) {
                    test.remove(i);
                    i--;
                }
            }
            if (test.get(0).equalsIgnoreCase(sPlan.klasse)){
                if (test.get(1).contains(" - ")){
                    String s1 = "";
                    for (char c : test.get(1).toCharArray()){
                        if (c != ' ') s1 += c;
                    }
                    test.set(1, s1);
                }
                vPlan.addLine(test.toArray(new String[7]));
            }
        }
        Persistence.load();
        for (VertretungsPlan.line line : vPlan.lines) {
            //if (line.toString().split(" ")[4].equals(sPlan.Plan.get(Persistence.Strings.values()[Calendar.DAY_OF_WEEK - 3].toString()).get(Integer.parseInt(line.toString().split(" ")[1]) - 1).Fach))
            System.out.println(line.toString());
        }
        //sPlan.addStunde(1, "Es", "De", "Montag");
        //sPlan.addStunde(2, "Es", "De", "Montag");
        //sPlan.addStunde(1, "Es", "Rk", "Dienstag");

        saveAndExit();
    }

    private static void saveAndExit(){
        Persistence.save();
        System.exit(0);

        

    }


}
