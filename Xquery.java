import java.io.*;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Xquery {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("=======Input queries file missing!=======");
            return;
        }
        try {
            File inputFile = new File(args[0]);

            Scanner scanner = new Scanner(inputFile);

            System.out.println("=======starting query=======");
            String query = "";
            while (scanner.hasNextLine()) {
                query += scanner.nextLine() + " ";
            }
            System.out.println("querying : " + query);
            try {

                ANTLRInputStream antlrIS = new ANTLRInputStream(query);
                XqueryLexer xqLexer = new XqueryLexer(antlrIS);
                CommonTokenStream commonTS = new CommonTokenStream(xqLexer);
                XqueryParser xqParser = new XqueryParser(commonTS);
                ParseTree pTree = xqParser.xq();
                XqVisitor visitor = new XqVisitor();
                ArrayList<Node> result = visitor.visit(pTree);


                if (result != null) {
                    System.out.println("query done. showing result for query. result size : " + result.size());
                    System.out.println("---------------------");
                    for (Node n : result) {
                        if (n.getNodeType() == 2) {
                            System.out.println(n.getNodeValue());
                        }
                        System.out.println(nodeToString(n));
                    }
                    nodesToXML(result);
                    System.out.println("---------------------");
                    System.out.println("All results shown. result size : " + result.size());
                }
            } catch (Exception e) {
                System.out.println("error occurs in the query : " + query);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File " + args[0] + " not found.");
        }
        System.out.println("=======end of query=======");
    }

    private static String nodeToString(Node node) {
        StringWriter sw = new StringWriter();
        try {
            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.transform(new DOMSource(node), new StreamResult(sw));
        } catch (TransformerException te) {
            System.out.println("nodeToString Transformer Exception");
        }
        return sw.toString();
    }

    private static void nodesToXML(ArrayList<Node> nodes) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            Element root = doc.createElement("NodesReturned");
            doc.appendChild(root);

            for (Node n : nodes) {
                Node temp = doc.importNode(n,true);
                root.appendChild(temp);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transf = transformerFactory.newTransformer();

            transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transf.setOutputProperty(OutputKeys.INDENT, "yes");
            transf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            DOMSource source = new DOMSource(doc);

            String outputFileName = "Result.xml";

            File myFile = new File(outputFileName);

            StreamResult console = new StreamResult(System.out);
            StreamResult file = new StreamResult(myFile);

            // transf.transform(source, console);
            transf.transform(source, file);
            System.out.println("result file generated. file name : " + outputFileName);
        }
        catch (Exception e) {
            System.out.println("generating xml file failed. " + e);
            e.printStackTrace();
        }
    }
}
