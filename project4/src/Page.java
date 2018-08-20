import java.io.Serializable;

/**
 * Created by hexu1 on 3/21/2017.
 */
public class Page implements Serializable, Comparable<Page> {
    public String url;
    public int urlID;
    public static final long serialVersionUID = -1827677255104766839L;

    public Page(String url, int urlID){
        this.url = url;
        this.urlID = urlID;
    }

    public int getURLID(){return urlID;}

    public String getURL(){return url;}

    public boolean equals(Object obj){
        if (obj == null || (obj.getClass() != this.getClass()))
            return false;
        else if (obj == this)
            return true;

        Page page = (Page) obj;

        if (page.getURLID() == this.getURLID() || page.getURL().equals(url) && page.getURL()!=null)
            return true;
        else
            return false;
    }

    public int compareTo(Page candidate){
        if (candidate.getURLID() < this.getURLID())
            return -1;
        else if (candidate.getURLID() == this.getURLID())
            return 0;
        else
            return 1;
    }

}
