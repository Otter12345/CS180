import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by hexu1 on 4/6/2017.
 */
public class Search {
    public static List<Page> pageList;
    public static List<Word> wordList;
    public static List<Result> resultSet;
    private String wordListFile;
    private String pageListFile;
    public  String[]terms;

    public Search(String wordListFile,String pageListFile){
        this.wordListFile = wordListFile;
        this.pageListFile = pageListFile;
    }

    public void setup(String wordListFile, String pageListFile) {
        FileUtils fileUtils = new FileUtils();
        wordList = fileUtils.getWordList(wordListFile);

        try {
            pageList = fileUtils.getPageList(pageListFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized List<Result> executeQuery (String query) {
        try {
            nullCheck();
        } catch (Exception e) {
            e.printStackTrace();
        }
        resultSet = new ArrayList<Result>();
        terms = query.split(" ");
        //  System.out.println(Arrays.toString(terms));

        int increment = wordList.size() / 5;

        Thread[] threads = new Thread[5];
        int start=0;
        int end=start+increment;
        for(int i = 0; i < 5; i++)
        {
            threads[i] = new Thread(new SearchThread(start, end, terms));
            threads[i].start();
            start = end;
            end = end + increment;
        }




        for (int j = 0; j < 5; j++) {
            try {
                threads[j].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(resultSet!=null)
            sort();
        return resultSet;
    }

    public void nullCheck() throws Exception{
        if (resultSet == null)
            setup(wordListFile,pageListFile);
    }

    public void sort(){Collections.sort(resultSet);
    }
}
