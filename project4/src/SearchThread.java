/**
 * Created by hexu1 on 4/9/2017.
 */
public class SearchThread implements Runnable {
    public int start;
    public int finish;
    public String[] terms;
    public Result result;
    public Word word;
    public Page page;

    public SearchThread(int start, int finish, String[] terms) {
        this.start = start;
        this.finish = finish;
        this.terms = terms;
    }

    public void run() {
        // System.out.println("yes");

        for (String term : terms) {
            word = findTerm(term);
            if (word!=null){

                for (Integer id:word.getList()){

                    boolean containsResult = false;

                    for (int i=0;i<Search.resultSet.size();i++) {
                        if (Search.resultSet.get(i).urlID == id) {
                            containsResult = true;
                            break;
                        }
                    }
                    if(containsResult) {
                        // System.out.println('y');
                        for (int i = 0; i < Search.resultSet.size(); i++) {
                            result = Search.resultSet.get(i);
                            if (result.getURLID() == id)
                                result.incrementScore();
                        }
                    }
                    else {
                        for (int j=0;j<Search.pageList.size();j++){
                            page =  Search.pageList.get(j);
                            if (page.getURLID()==id){
                                Result r = new Result(page.getURL(),id);
                                Search.resultSet.add(r);

                            }
                        }

                    }
                }
            }

        }
    }

    public Word findTerm(String term) {
        //System.out.println(term);
        for (int i = start; i < finish; i++) {
            Word w = Search.wordList.get(i);
            //System.out.println(w.getWord());
            if (w.getWord().equalsIgnoreCase(term)) {
                return w;
            }
        }
        return null;
    }
}

