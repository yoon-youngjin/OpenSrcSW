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
import java.io.File;
import java.io.IOException;
import java.util.*;

public class MidTerm {

    private String path;
    private String query;

    public MidTerm(String path, String query) {
        this.path = path;
        this.query = query;
    }

    public class Result {
        String title;
        String context;
        int cnt;

        @Override
        public String toString() {
            return title + ", " + context + ", " + cnt;
        }

        public Result(String title, String context, int cnt) {
            this.title = title;
            this.context = context;
            this.cnt = cnt;
        }
    }

    public void showSnippet() throws ParserConfigurationException, IOException, SAXException {

        File collection = new File(path);

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(collection);
        document.setXmlStandalone(true);

        NodeList nodeList = document.getElementsByTagName("doc");

        KeywordExtractor ke = new KeywordExtractor();
        KeywordList kl = ke.extractKeyword(query, true);

        List<Integer> tmp = new ArrayList<>();
        tmp.add(0);
        tmp.add(0);
        tmp.add(0);
        tmp.add(0);
        tmp.add(0);

        String[] keywards = new String[5];
        int idx1 = 0;
        for (Keyword keyword : kl) {
            int idx = 0;
            keywards[idx1] = keyword.getString();
            for (int i = 0; i < nodeList.getLength(); i++) {

                Node node = nodeList.item(i);
                Element eElement = (Element) node;

                Node body = eElement.getElementsByTagName("body").item(0);

                if (body.getTextContent().contains(keyword.getString())) {
                    tmp.set(idx, tmp.get(idx) + 1);
                }

                idx++;
            }
            idx1++;
        }


        Map<Integer, Integer> tmp2 = new HashMap<>();
        for (int i = 0; i < tmp.size(); i++) {
            tmp2.put(i, tmp.get(i));
        }

        for (int i = 0; i < tmp2.keySet().size(); i++) {

        }

        List<Result> list = new ArrayList<>();


        for (Keyword keyword : kl) {
            for (int i = 0; i < nodeList.getLength(); i++) {
                if (tmp2.containsKey(i)) {
                    Node node = nodeList.item(i);
                    Element eElement = (Element) node;

                    Node title = eElement.getElementsByTagName("title").item(0);
                    String textContent = title.getTextContent();

                    Node body = eElement.getElementsByTagName("body").item(0);
                    int i1 = body.getTextContent().indexOf(keyword.getString());

                    if (i1 != -1 && i1+30 < body.getTextContent().length()) {
                        String substring = body.getTextContent().substring(i1, i1 + 30);
                        Result result1 = new Result(textContent, substring, tmp2.get(i));
                        list.add(result1);
                    }
                }
            }
            break;
        }

//        for (int i = 0; i < nodeList.getLength(); i++) {
//            if (tmp2.containsKey(i)) {
//                Node node = nodeList.item(i);
//                Element eElement = (Element) node;
//
//                Node title = eElement.getElementsByTagName("title").item(0);
//                String textContent = title.getTextContent();
//
//                Node body = eElement.getElementsByTagName("body").item(0);
//                StringTokenizer st = new StringTokenizer(body.getTextContent(), ".");
//
//                System.out.println(st.countTokens());
//                int idx = 0;
//                int max = Integer.MIN_VALUE;
//                for (int k = 0; k < st.countTokens(); k++) {
//                    int cnt = 0;
//
//                    for (int j = 0; j < keywards.length; j++) {
//                        String tmp3 = st.nextToken();
//                        if (tmp3.contains(keywards[j])) {
//                            cnt++;
//                        }
//                    }
//                    if (max < cnt) {
//                        idx = k;
//                        max = cnt;
//                    }
//                }
//                StringTokenizer st2 = new StringTokenizer(body.getTextContent(), ".");
//
//                System.out.println(st2.countTokens());
//                String s = "";
//                while (st2.hasMoreTokens()) {
//                    int idx3 = 0;
//                    if (idx3 == idx) {
//                        s = st.nextToken();
//                    }
//                    idx3++;
//                }
//                Result result1 = new Result(textContent, s.substring(30), tmp2.get(i));
//                list.add(result1);
//            }
//        }

        Collections.sort(list, new Comparator<Result>() {
            @Override
            public int compare(Result o1, Result o2) {
                return o2.cnt - o1.cnt;
            }
        });

        list.stream().forEach(System.out::println);


    }

}
