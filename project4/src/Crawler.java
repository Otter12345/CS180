import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Chris on 4/6/2017.
 */
public class Crawler {
    public static int currentID;
    public static int totalURLs ;
    public static int limit;
    public static String domain;
    public MyQueue toParse;
    public static List<Page> parsed;
    public static List<String> visited;
    public static List<Word> words;
    public static Parser parser;
    public Crawler(String seed,
                   String domain,
                   int limit)
    {
        toParse = new MyQueue();
        this.domain = domain;
        this.limit = limit;
        toParse.add(seed);
        parsed = new ArrayList<Page>();
        visited = new ArrayList<String>();
        words = new ArrayList<Word>();
        parser = new Parser();
        totalURLs = 0;

    }
    public void crawl() throws Exception {
        Document d = null;
        try {
            d = parser.getDocument((String) toParse.peek().getData());

        } catch (ParseException e) {
            //   e.printStackTrace();
        }
        Elements links = null;
        try {
            links = parser.getLinks(d);
        } catch (ParseException e) {
            // e.printStackTrace();
        }
        ArrayList<String> seperated = new ArrayList();
        for (Element link : links) {
            seperated.add( link.attr("abs:href"));
        }


        // System.out.println(seperated);


        for(String i: seperated)
        {
            if(isInDomain(i) && isValidURL(i) && !i.contains(" "))
            {
                if(totalURLs < limit)
                {
                    toParse.add(i);
                    totalURLs++;
                }
            }
        }


        currentID = 0;
        while(!(toParse .isEmpty()))
        {
            System.out.println((currentID+1)+" / " + limit + ", toParse size: " + toParse.size());
            boolean failedParsed = false;
            Document f = null;
            String s= (String) toParse.peek().getData();
            //System.out.println(s);
            try {
                f  = parser.getDocument(s);

            } catch (Exception e) {
                failedParsed = true;
                toParse.remove();

                // e.printStackTrace();
            }
            String body = null;
            if(!failedParsed) {
                try {
                    body = parser.getBody(f);
                } catch (Exception e) {
                    failedParsed = true;
                    //  e.printStackTrace();
                    toParse.remove();

                }
                String[] urlseperated = new String[0];
                try {
                    urlseperated = body.split(" ");
                }
                catch (Exception e)
                {
                    //e.printStackTrace();
                }
                // System.out.println(Arrays.toString(urlseperated));
                for (String i : urlseperated) {
                    boolean containsThisWord = false;
                    for (Word w : words) {
                        if (w.getWord().equals(i)) {
                            w.addURLID(currentID);
                            containsThisWord = true;
                        }


                    }
                    if(!containsThisWord) {
                        words.add(new Word(i, currentID));
                    }
                }
                parsed.add(new Page((String) toParse.peek().getData(), currentID));
                toParse.remove();
                if (!failedParsed)
                    currentID++;
            }
        }
        // for(Page i: parsed)
        //  System.out.println(i);
        //  for(Word w: words)
        //  System.out.println(w.getWord());



    }

    public boolean parse(Document doc,
                         int id)  {

        try {
            parser.getBody(doc);

        } catch (ParseException e) {
            e.printStackTrace();

        }
        return false;

    }

    public boolean isValidURL(String url)
    {

        if(url.contains("http://") || url.contains("https://"))
            return true;

        return false;
    }
    public boolean isInDomain(String url)
    {
        if(url.contains(domain))
            return true;
        return false;
    }

    public void addPageToList(Page p)
    {
        parsed.add(p);

    }

    public void parseLinks(Document doc) throws Exception{
        try {
            parser.getLinks(doc);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
    }

    public void parseText(Document doc, int id) throws Exception{
        try {
            parser.getBody(doc);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
    }

    public void addWordToList(String word, int id){
        word.toLowerCase();

        Word w = new Word(word,id);
        words.add(w);
    }

    public void addToQueue(String url){
        toParse.add(url);
        totalURLs++;
    }

}
