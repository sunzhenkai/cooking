package pub.wii.cook.java.xml;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.StringWriter;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import pub.wii.cook.java.utils.ResourcesUtils;

public class CookXML {

  public static Document createEmptyDocument() throws Exception {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    return builder.newDocument();
  }

  public static Document getFromResource(String path) throws Exception {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    return builder.parse(ResourcesUtils.getResources(path));
  }

  public static String documentToString(Document doc) throws Exception {
    StringWriter sw = new StringWriter();

    // get & set transformer
    TransformerFactory tf = TransformerFactory.newInstance();
    Transformer transformer = tf.newTransformer();
    transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
    transformer.setOutputProperty(OutputKeys.METHOD, "xml");
    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

    DOMSource source = new DOMSource(doc);
    StreamResult result = new StreamResult(sw);
    transformer.transform(source, result);
    return sw.toString();
  }

  public static void appendElement(Document doc) {
    Element root = doc.createElement("user");
    root.setAttribute("name", "wii");
    root.setAttribute("age", "18");
    doc.appendChild(root);

    Element item = doc.createElement("country");
    item.appendChild(doc.createTextNode("china"));
    root.appendChild(item);
  }

  public static Element getByXPath(Document doc, String xpathCompile) throws Exception {
    XPathFactory xpf = XPathFactory.newInstance();
    XPath xpath = xpf.newXPath();
    XPathExpression expr = xpath.compile(xpathCompile);
    return (Element) expr.evaluate(doc, XPathConstants.NODE);
  }

  public static void printElement(Element element) {
    System.out.println(element.getTagName() + ": " + element.getTextContent());
    System.out.println(element.getTagName() + ": " + element.getFirstChild().getNodeValue());
    System.out.println(element.getTagName() + ": " + element.getNodeValue());
  }

  public static void main(String[] args) throws Exception {
    Document document = createEmptyDocument();
    System.out.println(documentToString(document));
    appendElement(document);
    System.out.println(documentToString(document));
    printElement(getByXPath(document, "/user/country"));

    System.out.println();
    System.out.println(documentToString(getFromResource("sample.xml")));
  }
}
