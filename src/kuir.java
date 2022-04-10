import java.util.List;

public class kuir {

    public static void main(String[] args)  {
//        if(args.length < 2)
//            throw new RuntimeException("2개의 인자를 넣어주세요");
        String command = args[0];
        String path = args[1];


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
                if (args[2].equals("-q")) {
                    String query = args[3];
                    searcher searcher = new searcher(path);
                    List<String> result = searcher.CalcSim(query);
                    if (result == null)
                        System.out.println("Fail");
                    else {
                        if (result.size() == 0)
                            System.out.println("검색된 문서가 없습니다.");
                        else {
                            result.stream().forEach(System.out::println);
                        }
                    }
                }
                break;
        }
    }
}


