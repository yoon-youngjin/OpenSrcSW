import java.io.File;

public class kuir {

    public static void main(String[] args) {
//        if(args.length != 2)
//            throw new RuntimeException("2개의 인자를 넣어주세요");
        String command = args[0];
        String path = args[1];
//        String command = "-o";
//        String path = "./index.xml";
        File file = new File(path);

        switch (command) {
            case "-c":
                File[] fileList = file.listFiles();
                makeCollection mc = new makeCollection();
                System.out.println(mc.mkCollection(fileList) ? "Success: *.html -> collection.xml" : "Fail: *.html -> collection.xml");
                break;
            case "-k":
                makeKeyword mk = new makeKeyword();
                System.out.println(mk.createCollection(file) ? "Success: collection.xml -> index.xml" : "Fail: collection.xml -> index.xml");
                break;
            case "-o":
                indexer indexer = new indexer();
                System.out.println(indexer.makeIndex(file) ? "Success: index.xml -> index.post" : "Fail: index.xml -> index.post");
//                indexer.readIndex();
                break;

        }

    }
}


