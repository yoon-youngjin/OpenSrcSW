import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class kuir {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
//        if(args.length < 2)
//            throw new RuntimeException("2개의 인자를 넣어주세요");
        String command = args[0];
        String path = args[1];
        String q = args[2];
        String query = args[3];
//        String command = args[1];
//        String path = "./index.post";
//        String q = "-q";
//        String query = "라면에는 면, 분만 스프가 있다.";

        switch (command) {
            case "-c":
                makeCollection mc = new makeCollection(path);
                System.out.println(mc.mkCollection() ? "Success: *.html -> collection.xml" : "Fail: *.html -> collection.xml");
                break;
            case "-k":
                makeKeyword mk = new makeKeyword(path);
                System.out.println(mk.createCollection() ? "Success: collection.xml -> index.xml" : "Fail: collection.xml -> index.xml");
                break;
            case "-i":
                indexer indexer = new indexer(path);
                System.out.println(indexer.makeIndex() ? "Success: index.xml -> index.post" : "Fail: index.xml -> index.post");
//                indexer.readIndex();
                break;
            case "-s":
                searcher searcher = new searcher(path, query);
                searcher.CalcSim();

        }

    }
}


