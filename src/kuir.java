import java.io.File;

public class kuir {

    public static void main(String[] args) {
//        if(args.length != 2)
//            throw new RuntimeException("2개의 인자를 넣어주세요");
        String command = args[0];
        String path = args[1];
//        String command = "-c";
//        String path = "./data";

        switch (command) {
            case "-c":
                makeCollection mc = new makeCollection(path);
                System.out.println(mc.mkCollection() ? "Success: *.html -> collection.xml" : "Fail: *.html -> collection.xml");
                break;
            case "-k":
                makeKeyword mk = new makeKeyword(path);
                System.out.println(mk.createCollection() ? "Success: collection.xml -> index.xml" : "Fail: collection.xml -> index.xml");
                break;
            case "-o":
                indexer indexer = new indexer(path);
                System.out.println(indexer.makeIndex() ? "Success: index.xml -> index.post" : "Fail: index.xml -> index.post");
//                indexer.readIndex();
                break;

        }

    }
}


