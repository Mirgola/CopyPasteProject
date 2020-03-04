import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XmlPars extends DefaultHandler {
    private static PathAddress pathAddress = new PathAddress();
    private static String thisElement = "";

    public void startDocument() throws SAXException {
        System.out.println("Start parse XML");
    }
    public void endDocument() throws SAXException {
        System.out.println("End parse XML");
    }
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        thisElement = qName;
    }
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        thisElement = "";
    }
    public void characters(char[] ch, int start, int length) throws SAXException {
        String s = new String(ch, start, length);
        s = s.replace("\n", "").trim();
        if (!s.isEmpty()){
            if (thisElement.equals("address_copy"))
                pathAddress.setPathNameIn(s);
            if (thisElement.equals("address_paste"))
                pathAddress.setPathNameOut(s);
        }
    }
}
