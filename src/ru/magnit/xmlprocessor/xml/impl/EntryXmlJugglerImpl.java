package ru.magnit.xmlprocessor.xml.impl;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ru.magnit.xmlprocessor.entity.Entry;
import ru.magnit.xmlprocessor.xml.EntryXmlJuggler;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class EntryXmlJugglerImpl implements EntryXmlJuggler {
    @Override
    public void saveToFile(final List<Entry> entities, final File file) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("entries");
            doc.appendChild(rootElement);
            for (Entry entity : entities) {
                Element entry = doc.createElement("entry");
                rootElement.appendChild(entry);
                Element field = doc.createElement("field");
                field.appendChild(doc.createTextNode(String.valueOf(entity.getField())));
                entry.appendChild(field);
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.transform(source, result);
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Entry> loadFromFile(final File file) {
        List<Entry> result = new ArrayList<>();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("entry");
            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    result.add(new Entry(Integer.parseInt(eElement.getAttribute("field"))));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void transform(final String dataXml, final String inputXsl, final String outputXml) {
        TransformerFactory factory = TransformerFactory.newInstance();
        StreamSource xslStream = new StreamSource(inputXsl);
        Transformer transformer;
        try {
            transformer = factory.newTransformer(xslStream);
            StreamSource in = new StreamSource(dataXml);
            StreamResult out = new StreamResult(outputXml);
            transformer.transform(in, out);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
