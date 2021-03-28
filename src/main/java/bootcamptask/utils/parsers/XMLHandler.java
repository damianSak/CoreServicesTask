package bootcamptask.utils.parsers;

import bootcamptask.model.Genre;
import bootcamptask.model.Song;
import bootcamptask.model.SongBuilder;
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

    public static void saveXMLToFile(List<Song> songsList, File createdFile) {

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

            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

            transformer.setOutputProperty(
                    "{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(createdFile);


            transformer.transform(source, result);


        } catch (ParserConfigurationException | TransformerException pce) {
            pce.printStackTrace();
        }
    }

    public static List<Song> parseXMLFileToList(File fileName) {

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
            if (builder != null) {
                document = builder.parse(fileName);
            }
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }

        List<Song> loadedSongs = new ArrayList<>();
        NodeList nodeList = null;
        if (document != null) {
            nodeList = document.getDocumentElement().getChildNodes();
        }
        if (nodeList != null) {
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

                    int votes = Integer.parseInt(elem.getElementsByTagName("votes")
                            .item(0).getChildNodes().item(0).getNodeValue());

                    Genre genreCategory = GenreUtils.findByNameCaseInsensitive(category);
                    loadedSongs.add(SongBuilder.builder().title(title).author(author).album(album).category(genreCategory)
                            .vote(votes).build());
                }
            }
        }

        System.out.println("Wczytano utwory:");
        loadedSongs.forEach((Song) -> System.out.println(Song.toString()));

        System.out.println("\n Pomyślnie wczytano elementy z pliku do pamięci programu");

        return loadedSongs;
    }

}








