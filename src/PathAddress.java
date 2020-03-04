import org.xml.sax.SAXException;
import org.yaml.snakeyaml.Yaml;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class PathAddress {
    static private String pathNameIn = "c:/Test/In/";
    static private String pathNameOut = "c:/Test/Out/";

    public String getPathNameIn() {
        return pathNameIn;
    }
    public String getPathNameOut() {
        return pathNameOut;
    }
    public void setPathNameIn(String name){
        this.pathNameIn = name;
    }
    public void setPathNameOut(String name){
        this.pathNameOut = name;
    }
    public void newPath() throws ParserConfigurationException, SAXException, IOException {
        File configFile = new File("сonfig/");
        if (configFile.isDirectory()){
            String[] str = configFile.list();
            for (String s : str){
                if (s.equals("copypaste.xml")){
                    SAXParserFactory factory = SAXParserFactory.newInstance();
                    SAXParser parser = factory.newSAXParser();
                    XmlPars pars = new XmlPars();
                    parser.parse(new File("сonfig/copypaste.xml"), pars);
                }
                else {
                    if (s.equals("copypaste.yaml")){
                        Yaml yaml = new Yaml();
                        InputStream inputStream = new FileInputStream(new File("сonfig/copypaste.yaml"));
                        Map<String, String> map = yaml.load(inputStream);
                        inputStream.close();
                        for (Map.Entry<String, String> pair : map.entrySet()){
                            if (pair.getKey().equals("address_copy"))
                                pathNameIn = pair.getValue();
                            if (pair.getKey().equals("address_paste"))
                                pathNameOut = pair.getValue();
                        }
                    }
                }
            }
        }
    }
}
