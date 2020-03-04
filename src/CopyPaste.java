import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;

public class CopyPaste {
    private static PathAddress pathAddress = new PathAddress();

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        pathAddress.newPath();

        ArrayList<String> fileList = new ArrayList<String>();
        File file = new File(pathAddress.getPathNameIn());
        if (file.isDirectory()){
            String[] list = file.list();
            for (String s : list){
                File f = new File(pathAddress.getPathNameIn() + s);
                if(f.isDirectory())
                    System.out.println("isDirectory");
                else
                    fileList.add(s);
            }
        }

        for(String s : fileList) {
            FileInputStream inputStream = new FileInputStream(pathAddress.getPathNameIn() + s);
            FileOutputStream outputStream = new FileOutputStream(pathAddress.getPathNameOut() + s);
            byte[] byffer = new byte[1000];
            while (inputStream.available() > 0) {
                int i = inputStream.read(byffer);
                outputStream.write(byffer, 0, i);
            }
            inputStream.close();
            outputStream.close();
        }
    }
}
