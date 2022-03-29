
import org.snu.ids.kkma.index.Keyword;
import org.snu.ids.kkma.index.KeywordExtractor;
import org.snu.ids.kkma.index.KeywordList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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
 * 3주차 실습 코드
 *
 * kkma 형태소 분석기를 이용하여 index.xml 파일을 생성하세요.
 *
 * index.xml 파일 형식은 아래와 같습니다.
 * (키워드1):(키워드1에 대한 빈도수)#(키워드2):(키워드2에 대한 빈도수)#(키워드3):(키워드3에 대한 빈도수) ...
 * e.g., 라면:13#밀가루:4#달걀:1 ...
 *
 * input : collection.xml
 * output : index.xml
 */
public class makeKeyword {

    private String input_file;
    private String output_flie = "./index.xml";

    public makeKeyword(String path) {
        this.input_file = path;
    }

    /**
     * DocumentBuilderFactory를 통한 xml파싱
     */
    public boolean createCollection() {
        try {
            File collection = new File(input_file);

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(collection);
            document.setXmlStandalone(true);

            NodeList nodeList = document.getElementsByTagName("doc");

            for (int i = 0; i < nodeList.getLength(); i++) {
                StringBuilder sb = new StringBuilder();

                Node node = nodeList.item(i);
                Element eElement = (Element) node;

                KeywordExtractor ke = new KeywordExtractor();

                Node body = eElement.getElementsByTagName("body").item(0);
                KeywordList kl = ke.extractKeyword(body.getTextContent(), true);

                for (Keyword kw : kl) {
                    sb.append(kw.getString()).append(":").append(kw.getCnt()).append("#");
                }
                body.setTextContent(sb.toString());
            }

            if (transform(document)) {
                return true;
            } else {
                return false;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return false;
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
