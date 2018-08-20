import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hexu1 on 3/21/2017.
 */
public class Word implements Serializable {
    private String word;
    private List<Integer> postings;
    public static final long serialVersionUID = -3696191086353573895L;

    public Word(String word, int urlID){
        this.word = word;
        postings = new ArrayList<Integer>();
        postings.add(urlID);
    }

    public void addURLID(int urlID){postings.add(urlID);
    }

    public String getWord(){return word;}

    public boolean equals(Object obj){
        if (obj == null || obj.getClass() != this.getClass())
            return false;
        else if (obj == this)
            return true;

        Word word1 = (Word) obj;

        if (word1.getWord()!= null && word1.getWord().equals(word))
            return true;

        return false;
    }

    public List<Integer> getList(){return postings;}
}
