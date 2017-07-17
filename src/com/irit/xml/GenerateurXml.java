package com.irit.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

/**
 * Created by mkostiuk on 13/07/2017.
 */
public class GenerateurXml {

    private String getDocXml(String udn, String like) throws ParserConfigurationException, TransformerException {

        String namespace = "";
        Document doc;
        DocumentBuilderFactory db = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;

        builder = db.newDocumentBuilder();
        doc = builder.newDocument();
        Element root = doc.createElementNS(namespace, "Liker");
        doc.appendChild(root);

        Element k = doc.createElementNS(namespace, "UDN");
        root.appendChild(k);
        k.appendChild(doc.createTextNode(udn));

        Element n = doc.createElementNS(namespace, "NUMERO");
        root.appendChild(n);
        n.appendChild(doc.createTextNode(like));

        DOMSource source = new DOMSource(doc);
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.transform(source, result);

        return writer.toString();
    }

}
