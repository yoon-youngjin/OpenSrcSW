import org.jsoup.Jsoup;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 2주차 실습 코드
 *
 * 주어진 5개의 html 문서를 전처리하여 하나의 xml 파일을 생성하세요.
 *
 * input : data 폴더의 html 파일들
 * output : collection.xml
 */
public class makeCollection {

    private String data_path;
    private String output_flie = "./collection.xml";

    public makeCollection(String path) {
        this.data_path = path;
    }

    /**
     * Document 생성
     */
    public boolean mkCollection() {
        int id = 0;
        try {

            File[] fileList = new File(data_path).listFiles();

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
            Document new_document = documentBuilder.newDocument();
            new_document.setXmlStandalone(true);

            Element docs = new_document.createElement("docs");
            new_document.appendChild(docs);

            for (File file : fileList) {
                org.jsoup.nodes.Document document = Jsoup.parse(file, "UTF-8");

                Element doc = new_document.createElement("doc");
                Element title = new_document.createElement("title");
                Element body = new_document.createElement("body");

                doc.setAttribute("id", String.valueOf(id));
                title.setTextContent(document.title());
                body.setTextContent(document.body().text());

                doc.appendChild(title);
                doc.appendChild(body);
                docs.appendChild(doc);

                id++;
            }
            if (transform(new_document)) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (ParserConfigurationException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * document를 transform
     */
    private boolean transform(Document document) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();

            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4"); //정렬 스페이스4칸
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes"); //들여쓰기
            transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "yes"); //doc.setXmlStandalone(true); 했을때 붙어서 출력되는부분 개행

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new FileOutputStream(new File(output_flie)));

            transformer.transform(source, result);
            return true;
        } catch (TransformerConfigurationException e) {
            return false;
        } catch (FileNotFoundException e) {
            return false;
        } catch (TransformerException e) {
            return false;
        }

    }


}