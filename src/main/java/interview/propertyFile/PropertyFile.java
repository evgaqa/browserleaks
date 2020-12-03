package interview.propertyFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyFile {

    private static Properties getPropertyFile() {
        FileInputStream fileInput = null;
        try {
            fileInput = new FileInputStream(new File("hashes.properties").getAbsolutePath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Properties prop = new Properties();

        //load properties file
        try {
            prop.load(fileInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

    public static String getPropertyFileValue(String variable){
        return getPropertyFile().getProperty(variable);
    }
}
