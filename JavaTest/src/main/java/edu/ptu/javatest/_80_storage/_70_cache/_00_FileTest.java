package edu.ptu.javatest._80_storage._70_cache;

import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class _00_FileTest {

    private String xmlFile = "./doc/build.xml";
    CountDownLatch countDownLatch = new CountDownLatch(1);
    @Test
    public void testXmpParseUseSax() throws ParserConfigurationException, SAXException, IOException {//10ms
        SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();

        saxParser.parse(xmlFile, getParseHandler());
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testXmpParseUseDom() throws ParserConfigurationException, SAXException, IOException {//79ms
        Document parse = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmlFile);

    }

    private DefaultHandler getParseHandler() {
        return new DefaultHandler() {
            @Override
            public void startDocument() throws SAXException {
                super.startDocument();
            }

            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                super.startElement(uri, localName, qName, attributes);
            }

            @Override
            public void endElement(String uri, String localName, String qName) throws SAXException {
                super.endElement(uri, localName, qName);
            }

            @Override
            public void endDocument() throws SAXException {
                super.endDocument();
                countDownLatch.countDown();
            }
        };
    }


}
