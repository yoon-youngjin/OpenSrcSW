import org.snu.ids.kkma.index.Keyword;
import org.snu.ids.kkma.index.KeywordExtractor;
import org.snu.ids.kkma.index.KeywordList;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;

public class searcher {

    private String path;
    private String query;

    HashMap<String, String> hashMap;

    public searcher(String path, String query) {

        this.path = path;
        this.query = query;

        hashMap = readIndex();
    }

    public void Searcher() {
        KeywordExtractor ke = new KeywordExtractor();
        KeywordList kl = ke.extractKeyword(query, true);

        for (Keyword keyword : kl) {
//           라면 0 0.0 1 20.92 2 0.0 3 0.0 4 0.0

//            String s = hashMap.get(keyword.getString());
//            System.out.println(keyword.getString() +" "+ s);

            String[] split_value = hashMap.get(keyword.getString()).split(" ");


            System.out.println(keyword.getCnt());
            HashMap<String, String> map_word = new HashMap<>();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < split_value.length; i += 2) {
                int index = Integer.parseInt(split_value[i]);
                double weight = Double.parseDouble(split_value[i + 1]);
                for (int j = 0; j < 5; j++) {
                    if (index == j)
                        sb.append(j + ":" + weight + ";");
                    else sb.append(j + ":0;");
                }
                System.out.println(sb);
            }
            map_word.put(keyword.getString(), keyword.getCnt() + "/" + sb);

//            String[] split_value = hashMap.get(keyword.getString()).split(" ");
//
//            HashMap<String, String> map_word = new HashMap<>();
//
//            StringBuilder sb = new StringBuilder();
//            for (int i = 0; i < split_value.length; i += 2) {
//                int index = Integer.parseInt(split_value[i]);
//                double weight = Double.parseDouble(split_value[i + 1]);
//                for (int j = 0; j < 5; j++) {
//                    if (index == j)
//                        sb.append(j + ":" + weight + ";");
//                    else sb.append(j + ":0;");
//                }
////                System.out.println(sb);
//            }
//            map_word.put(keyword.getString(), keyword.getCnt() + "/" + sb);

        }




    }

    public HashMap<String, String> readIndex() {
        try (FileInputStream fileInputStream = new FileInputStream("./index.post")
        ) {
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object object = objectInputStream.readObject();

            hashMap = (HashMap) object;

            return hashMap;
//            for (String key : hashMap.keySet()) {
//                String value = hashMap.get(key);
//                System.out.println(key + " -> " + value);
//            }
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
