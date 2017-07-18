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
import java.util.HashMap;

/**
 * Created by mkostiuk on 17/07/2017.
 */
public class GenXmlSendLikes implements GenerateurXml {

    /**
     *  Construit le document XML contenant l'UDN du composant ainsi que l'ensemble des couples
     *  numéro de page / likes.
     **/

    @Override
    public String getDocXml(HashMap<String, String> args) throws ParserConfigurationException, TransformerException {

        String udn = args.remove("UDN");

        String namespace = "";
        Document doc;
        DocumentBuilderFactory db = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;

        builder = db.newDocumentBuilder();
        doc = builder.newDocument();
        Element root = doc.createElementNS(namespace, "Liker");
        doc.appendChild(root);

        Element u = doc.createElementNS(namespace,"UDN");
        root.appendChild(u);
        u.appendChild(doc.createTextNode(udn));

        for (String key : args.keySet()) {
            Element k = doc.createElementNS(namespace, key);
            root.appendChild(k);
            Element nb = doc.createElementNS(namespace, "NBLIKES");
            k.appendChild(nb);
            nb.appendChild(doc.createTextNode(args.get(key)));
        }

        DOMSource source = new DOMSource(doc);
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.transform(source, result);

        return writer.toString();
    }
}
