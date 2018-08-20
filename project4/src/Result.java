import java.io.Serializable;

/**
 * Created by hexu1 on 4/6/2017.
 */
public class Result implements Serializable, Comparable<Result> {
    public int score;
    public static final long serialVersionUID = -938761094876384658L;
    public String url;
    public int urlID;

    public Result(String url, int urlID){
        this.url = url;
        this.urlID = urlID;
        score=1;
    }

    public void updateScore(int score){
        this.score = this.score + score;
    }

    public void incrementScore(){
        score++;
    }

    public int getScore(){return score;}

    public String getURL(){return url;}

    public int getURLID(){return urlID;}

    public boolean equals(Object obj){
        if (obj == null || obj.getClass() != this.getClass())
            return false;

        if (obj == this)
            return true;

        return true;
    }

    public int compareTo(Result candidate){
        if (candidate.score == this.score)
            return 0;
        else if (candidate.score> this.score)
            return -1;
        else
            return 1;
    }
}
