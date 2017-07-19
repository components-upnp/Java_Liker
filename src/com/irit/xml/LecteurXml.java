package com.irit.xml;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.StringReader;

/**
 * Created by mkostiuk on 13/07/2017.
 */
public class LecteurXml {

    private String udn;
    private String page;
    private String pageCourante;

    public LecteurXml(String xml) throws ParserConfigurationException, SAXException, IOException {

        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser sp = spf.newSAXParser();

        DefaultHandler handler = new DefaultHandler() {

            boolean isUdn = false;
            boolean isPage = false;
            boolean isPageCourante = false;

            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) {
                if (qName.equalsIgnoreCase("UDN"))
                    isUdn = true;
                if (qName.equalsIgnoreCase("NUMERO"))
                    isPage = true;
                if (qName.equalsIgnoreCase("PAGECOURANTE"))
                    isPageCourante = true;
            }

            @Override
            public void characters(char ch[], int start, int length) {
                if (isUdn) {
                    isUdn = false;
                    udn = new String(ch, start, length);
                }
                if (isPage) {
                    isPage = false;
                    page = new String(ch, start, length);
                }
                if (isPageCourante) {
                    isPageCourante = false;
                    pageCourante = new String(ch, start, length);
                }
            }

        };
        sp.parse(new InputSource(new StringReader(xml)), handler);
    }

    public String getNumPage() {
        return page;
    }

    public String getUdn() {
        return udn;
    }

    public String getPageCourante() {
        return pageCourante;
    }
}
