package me.beepbeat;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by beepbeat/holladiewal on 17.04.2016.
 */
public class Config {
    private static File config = new File("config");
    private static boolean runRegularly = false;

    public final static strictfp synchronized void save(){
        try {
            if (!config.exists()) {config.createNewFile();}
            FileUtils.write(config, Boolean.toString(runRegularly));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public final static strictfp synchronized void load(){
        try {
            if (!config.exists()) return;
            runRegularly = Boolean.valueOf(FileUtils.readLines(config).get(0));
        }
        catch (IOException e){
            e.printStackTrace();        }
    }
}
