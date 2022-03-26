import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;

public class kuir {

    public static void main(String[] args) throws ParserConfigurationException, IOException, TransformerException, SAXException, ClassNotFoundException {

//        if(args.length != 2)
//            throw new RuntimeException("2개의 인자를 넣어주세요");
//        String command = args[0];
//        String path = args[1];
        String command = "-o";
        String path = "./result/index.xml";

        if (command.equals("-c")) {
            File path1 = new File(path);
            File[] fileList = path1.listFiles();
            makeCollection mc = new makeCollection();
            System.out.println(mc.mkCollection(fileList)? "Success: *.html -> collection.xml" : "Fail: *.html -> collection.xml");
        }
        else if(command.equals("-k")) {
            File collection = new File(path);
            makeKeyword mk = new makeKeyword();
            System.out.println(mk.createCollection(collection)?  "Success: collection.xml -> index.xml" : "Fail: collection.xml -> index.xml");
        }
        else if(command.equals("-o")) {
            File index = new File(path);
            indexer indexer = new indexer();
//            indexer.makeIndex(index);
            indexer.readIndex();
        }
    }
}


