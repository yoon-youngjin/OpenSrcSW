import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class indexer {

    static Double count = 0D; // 총 doc 개수

    public class Item { // 0 0.0 1 0.0 2 0.0 3 0.0 4 0.0

        private Map<Integer, String> map = new HashMap<>();

        public Item() {
            for (int i = 0; i < count; i++) {
                map.put(i, "0.0");
            }
        }
        @Override
        public String toString() {
            String result = "";
            for (int i = 0; i < count; i++) {
                result += i + " " + map.get(i) + " ";
            }
            return result;
        }
    }

    public boolean makeIndex(File file) {

        HashMap<String, String> result = new HashMap<>();

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = docFactory.newDocumentBuilder();

            Document document = builder.parse(file);
            NodeList nodeList = document.getElementsByTagName("doc");

            count = Double.valueOf(nodeList.getLength());

            HashMap<String, String> hashMap = new HashMap<>();

            for (int i = 0; i < nodeList.getLength(); i++) {

                Node node = nodeList.item(i);
                Element eElement = (Element) node;

                Node body = eElement.getElementsByTagName("body").item(0); // 떡:16#멥쌀:4#찹쌀:3#곡식:1#가루:4#음식:1#통칭:1#말:3#일반적:1#을:3#주재료:1#사용:3#감자:1#녹말:1#기타:1# ...

                String[] arr_str = body.getTextContent().split("#"); // 떡:16 / 멥쌀:4 / ...

                for (String wordset : arr_str) { // 떡:16
                    String[] word = wordset.split(":"); // 떡 / 16
                    if (hashMap.containsKey(word[0])) {
                        String origin = hashMap.get(word[0]);
                        String new_value = origin + ";" + i + " " + word[1]; // 떡, 0 16 -> 떡 0 16 ; 1 14
                        hashMap.put(word[0], new_value);
                    } else {
                        hashMap.put(word[0], i + " " + word[1]); // 떡:16 -> 떡, 0 16
                    }
                }
            }

            hashMap.keySet().stream().forEach(key -> {
                        String value = hashMap.get(key);

                        String[] valueSet = value.split(";"); // 3 1;4 1

                        Item item = new Item(); // 0 0.00 1 0.00 2 0.00 3 0.00 4 0.00

                        for (String s : valueSet) { // 3 1
//                        double id = Double.parseDouble(s.substring(0, 1));
                            double tf = Double.parseDouble(s.substring(2));
                            double df = (double) valueSet.length;
                            double n = count;
                            double weight = tf * (Math.log(n / df));
                            item.map.put(Integer.parseInt(s.substring(0, 1)), String.format("%.2f", weight)); // 0 0.00 1 0.00 2 0.00 3 1.61 4 0.00
                        }
                        result.put(key, item.toString());
                    }
            );


        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (SAXException e) {
            e.printStackTrace();
            return false;
        }

        try (FileOutputStream fileOutputStream = new FileOutputStream("./index.post")) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(result);

            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void readIndex() {
        try (FileInputStream fileInputStream = new FileInputStream("./index.post")
        ) {
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object object = objectInputStream.readObject();

            HashMap<String, String> hashMap = (HashMap) object;

            for (String key : hashMap.keySet()) {
                String value = hashMap.get(key);
                System.out.println(key + " -> " + value);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
