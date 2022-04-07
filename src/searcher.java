import org.snu.ids.kkma.index.Keyword;
import org.snu.ids.kkma.index.KeywordExtractor;
import org.snu.ids.kkma.index.KeywordList;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class searcher {

    private String path;
    private static int doc_count;

    HashMap<String, String> idxHashMap;

    public searcher(String path) {
        this.path = path;
        idxHashMap = readIndex();
    }

    public class Result {

        int idx;
        Double weight;

        public Result(int idx, Double weight) {
            this.idx = idx;
            this.weight = weight;
        }
    }

    public List<String> InnerProduct(String query)  {

        if (idxHashMap == null) {
            return null;
        }

        KeywordExtractor ke = new KeywordExtractor();
        KeywordList kl = ke.extractKeyword(query, true);
        /**
         * Query: 라면에는 면, 분말, 스프가 있다.
         * -> K1 = 라면, w1 = 1
         * -> K2 = 면, w2 = 1
         * -> K3 = 분말, w3 = 1
         * -> K4 = 스프, w4 = 1
         *
         *
         * index.post
         * 라면: 0 0.0 1 20.92 2 0.0 3 0.0 4 0.0
         *      (id0 = 0, w1 = 0.0) (id1 = 1, w1 = 20.92) (id2 = 2, w2 = 0.0) (id3 = 3, w3 = 0.0) (id4 = 4, w4 = 0.0)
         * 면: 0 0.0 1 8.05 2 0.0 3 0.0 4 0.0
         *      (id0 = 0, w1 = 0.0) (id1 = 1, w1 = 8.05) (id2 = 2, w2 = 0.0) (id3 = 3, w3 = 0.0) (id4 = 4, w4 = 0.0)
         * 분말: null
         *      (id0 = 0, w1 = 0.0) (id1 = 1, w1 = 0.0) (id2 = 2, w2 = 0.0) (id3 = 3, w3 = 0.0) (id4 = 4, w4 = 0.0)
         * 스프: 0 0.0 1 4.83 2 0.0 3 0.0 4 0.0
         *      (id0 = 0, w1 = 0.0) (id1 = 1, w1 = 4.83) (id2 = 2, w2 = 0.0) (id3 = 3, w3 = 0.0) (id4 = 4, w4 = 0.0)
         *
         *
         * Q_id0 = w1 * 라면(id0_w1) + w2 * 면(id0_w1) + w3 * 분말(id0_w1) + w4 * 스프(id0_w1) -> 0번 document
         *
         *
         */

        /**
         * doc_weight
         * index.post의 각 doc에 있는 weight를 모으는 맵
         * 예) 0 -> 0.0, 0.0, 0.0, 0.0
         * 예) 1 -> 20.92, 8.05, 0.0, 4.83
         */
        Map<String, List<Double>> doc_weight = new HashMap<>();
        Double weight_0 = 0d;
        for (int i = 0; i < doc_count; i++) {
            doc_weight.put(String.valueOf(i), new ArrayList<>());
        }
        /**
         * 형태 분석기를 통해 나온 형태소의 weight를 모으는 리스트
         * 1 1 1 1
         */
        List<Double> query_weight = new ArrayList<>();

        for (Keyword keyword : kl) {

            query_weight.add(Double.valueOf((keyword.getCnt())));

            weight_0 += Double.valueOf((keyword.getCnt())) * Double.valueOf((keyword.getCnt()));

            String tmp = idxHashMap.get(keyword.getString());

            if (tmp != null) {
                String[] s1 = tmp.split(" ");
                for (int i = 0; i < s1.length; i = i + 2) {
                    List<Double> weights = doc_weight.get(s1[i]);
                    weights.add(Double.parseDouble(s1[i + 1]));
                }
            }
        }

//        System.out.println(weight_0);
        weight_0 = Math.sqrt(weight_0);

//        System.out.println(weight_0);

        Set<String> strings = doc_weight.keySet();
        List<Double> weight_1 = new ArrayList<>();


//        for (int i = 0; i < 5; i++) {
//            System.out.println(doc_weight.get(String.valueOf(i)));
//        }

        for (String string : strings) {
            List<Double> doubles = doc_weight.get(string);
            Double sum = 0d;
            for (Double aDouble : doubles) {
                sum += aDouble * aDouble;
            }
            weight_1.add(Math.sqrt(sum));
        }

//        for (int i = 0; i < 5; i++) {
//            System.out.println(weight_1.get(i));
//        }
        List<Result> result = new ArrayList<>();

        for (int i = 0; i < kl.size(); i++) {
            Double sum = 0.0;
            List<Double> doubles = doc_weight.get(String.valueOf(i));

            for (int j = 0; j < doubles.size(); j++) {
                sum += (doubles.get(j) * query_weight.get(j));
            }
            sum = sum / (weight_0 * weight_1.get(i));
            if (sum.isNaN()) {
                sum = 0.0;
            }
//            System.out.println(i +" " + sum);
            result.add(new Result(i, sum));
        }




        /**
         * id값 순서대로 정렬
         */
        List<Result> collect = result.stream().sorted(new Comparator<Result>() {
            @Override
            public int compare(Result o1, Result o2) {
                return o1.idx - o2.idx;
            }
        }).filter(
                result1 -> result1.weight !=0
        ).collect(Collectors.toList());

//        for (Result result1 : collect) {
//            System.out.println(result1.idx + " " + result1.weight);
//        }

        List<Result> sort_result = new ArrayList<>();
        for (Result result1 : collect) {
            if (sort_result.size() == 3)
                break;
            sort_result.add(result1);
        }

        return showTitle(sort_result);
    }

    private List<String> showTitle(List<Result> result)  {

        List<String> return_result = new ArrayList<>();

        try {
            File collection = new File("./index.xml");

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(collection);
            document.setXmlStandalone(true);

            NodeList nodeList = document.getElementsByTagName("doc");

            for (int i = 0; i < result.size(); i++) {
                for (int j = 0; j < nodeList.getLength(); j++) {

                    Node node = nodeList.item(j);
                    NamedNodeMap attributes = node.getAttributes();

                    if (Integer.parseInt(attributes.getNamedItem("id").getTextContent()) == result.get(i).idx) {
                        Element eElement = (Element) node;
                        return_result.add(eElement.getElementsByTagName("title").item(0).getTextContent());
                    }
                }
            }
            return return_result;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;

        } catch (SAXException e) {
            e.printStackTrace();
            return null;

        }

    }

    public HashMap<String, String> readIndex() {
        try (FileInputStream fileInputStream = new FileInputStream("./index.post")
        ) {
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object object = objectInputStream.readObject();
            HashMap<String, String> hashMap = (HashMap<String, String>) object;
            for (String key : hashMap.keySet()) {
                doc_count = hashMap.get(key).split(" ").length / 2;
                break;
            }
            return (HashMap) object;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }


}
