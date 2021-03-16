package bootcamp_task.utils;

import bootcamp_task.model.Genre;
import bootcamp_task.model.Song;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLHandler {

    String file = "D:\\test\\songsXML.xml";

//    public List<Song> readXMLFile(String fileName) {
//
//        List<Song> loadedList = new ArrayList<>();
//        try {
//            File fXmlFile = new File(fileName);
//            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//            Document doc = dBuilder.parse(fXmlFile);
//
//            //optional, but recommended
//            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
//            doc.getDocumentElement().normalize();
//
//            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
//
//            NodeList nList = doc.getElementsByTagName("song");
//
//            System.out.println("----------------------------");
//
//            for (int temp = 0; temp < nList.getLength(); temp++) {
//
//                Node nNode = nList.item(temp);
//
//                System.out.println("\nCurrent Element :" + nNode.getNodeName());
//
//                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
//
//                    Element eElement = (Element) nNode;
//
//                    System.out.println("Song title : " + eElement.getElementsByTagName("title").item(0).getTextContent());
//                    System.out.println("Song author : " + eElement.getElementsByTagName("author").item(0).getTextContent());
//                    System.out.println("Song album : " + eElement.getElementsByTagName("album").item(0).getTextContent());
//                    System.out.println("Song category : " + eElement.getElementsByTagName("category").item(0).getTextContent());
//                    System.out.println("Song votes : " + eElement.getElementsByTagName("votes").item(0).getTextContent());
//
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return loadedList;
//    }

    public static void saveXMLFile(List<Song> songsList,File createdFile) {

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("songs");
            doc.appendChild(rootElement);

            for (Song singleSong : songsList) {

                Element song = doc.createElement("song");
                rootElement.appendChild(song);

                Element title = doc.createElement("title");
                title.appendChild(doc.createTextNode(String.valueOf(singleSong.getTitle())));
                song.appendChild(title);

                Element author = doc.createElement("author");
                author.appendChild(doc.createTextNode(String.valueOf(singleSong.getAuthor())));
                song.appendChild(author);

                Element album = doc.createElement("album");
                album.appendChild(doc.createTextNode(String.valueOf(singleSong.getAlbum())));
                song.appendChild(album);

                Element category = doc.createElement("category");
                category.appendChild(doc.createTextNode(String.valueOf(singleSong.getCategory())));
                song.appendChild(category);

                Element votes = doc.createElement("votes");
                votes.appendChild(doc.createTextNode(String.valueOf(singleSong.getVote())));
                song.appendChild(votes);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty("omit-xml-declaration", "yes");

            transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");

            transformer.setOutputProperty(
                    "{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(createdFile);


            transformer.transform(source, result);


        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

     public List<Song> parseXMLToList(File fileName) {

        //Get Document Builder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        // Load the input XML document, parse it and return an instance of the
        // Document class.
        Document document = null;
        try {
            document = builder.parse(fileName);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Song> loadedSongs = new ArrayList<>();
        NodeList nodeList = document.getDocumentElement().getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) node;

//                // Get the value of the ID attribute.
//                String ID = node.getAttributes().getNamedItem("ID").getNodeValue();

                // Get the value of all sub-elements.
                String title = elem.getElementsByTagName("title")
                        .item(0).getChildNodes().item(0).getNodeValue();

                String author = elem.getElementsByTagName("author").item(0)
                        .getChildNodes().item(0).getNodeValue();

                String album = elem.getElementsByTagName("album")
                        .item(0).getChildNodes().item(0).getNodeValue();

                String category = elem.getElementsByTagName("category")
                        .item(0).getChildNodes().item(0).getNodeValue();

                Integer votes = Integer.parseInt(elem.getElementsByTagName("votes")
                        .item(0).getChildNodes().item(0).getNodeValue());

                loadedSongs.add(new Song(title, author, album, Genre.valueOf(category), votes));
            }
        }

         System.out.println("\n Pomyślnie wczytano elementy z pliku \n");

        return loadedSongs;
    }

}








