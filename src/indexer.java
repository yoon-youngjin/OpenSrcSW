import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class indexer {

    static Double count = 0D;

    public void makeIndex(File file) throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = docFactory.newDocumentBuilder();
        Document document = builder.parse(file);
        NodeList nodeList = document.getElementsByTagName("doc");
        count = Double.valueOf(nodeList.getLength());
        String[] arr_str;

        HashMap<String, String> hashMap = new HashMap<>();

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            Element eElement = (Element) node;

            Node body = eElement.getElementsByTagName("body").item(0);

            arr_str = body.getTextContent().split("#");

            for (String wordset : arr_str) {
                String[] word = wordset.split(":");
                if (hashMap.containsKey(word[0])) {
                    String origin = hashMap.get(word[0]);
                    String new_value = origin + ";" + i + " " + word[1];
                    hashMap.put(word[0], new_value);
                } else {
                    hashMap.put(word[0], i + " " + word[1]); // 떡:16 -> 떡;0;16
                }
            }
        }

        HashMap<String, ArrayList<String>> map = new HashMap<>();

        hashMap.keySet().parallelStream().forEach(key -> {
            String value = hashMap.get(key);

            String[] valueSet = value.split(";"); // 3 1;4 1

            for (int i = 0; i < count; i++) {
                //TODO
            }

            for (String s : valueSet) {
                double id = Double.parseDouble(s.substring(0, 1));
                double tf = Double.parseDouble(s.substring(2));
                double df = (double) valueSet.length;
                double n = count;
                double weight = tf * (Math.log(n / df));
//                System.out.println("key:"+key+" id:"+id+" tf:"+tf+" n:"+n+" df:"+df+" weight:"+weight+" n/df:"+n/df);
                if (map.containsKey(key)) {
                    String s1 = map.get(key).toString().replace("[", "").replace("]", "");
                    String s2 = s1 + " " + Math.round(id) + " " + String.format("%.2f", weight);
                    ArrayList<String> list = new ArrayList<>();
                    list.add(s2);
                    map.put(key, list);
                } else {
                    String s1 = Math.round(id) + " " + String.format("%.2f", weight);
                    ArrayList<String> list = new ArrayList<>();
                    list.add(s1);
                    map.put(key, list);
                }
            }
        });

        FileOutputStream fileOutputStream = new FileOutputStream("./result/index.post");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(map);
        objectOutputStream.close();
    }

    public void readIndex() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("./result/index.post");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        Object object = objectInputStream.readObject();
        objectInputStream.close();

        HashMap<String, ArrayList<String>> hashMap = (HashMap) object;

        for (String key : hashMap.keySet()) {
            ArrayList<String> value = hashMap.get(key);
            System.out.println(key + " -> " + value);
        }
    }
}
