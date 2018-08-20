import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by hexu1 on 3/31/2017.
 */
public class Parser {
    public Document getDocument(String url) throws ParseException {
        Document d = null;

        if (url == null)
            throw new ParseException("getDocument() failed. String url is null.");
        else if (url.equals(""))
            throw new ParseException("getDocument() failed. String url is empty.");
           /* Connect to URL and get the web page */

        try {
            d = Jsoup.connect(url).get();
        }
        catch (java.lang.IllegalArgumentException g)
        {
            throw new ParseException("getDocument() failed. Connection failed.");
        }

        catch (IOException e) {
            e.printStackTrace();
        }

        if (d == null)
            throw new ParseException("getDocument() failed: Document is null.");

        return d;
    }

    public String getBody(Document d) throws ParseException{
        if (d == null)
            throw new ParseException("getBody() failed. Document parameter is null.");
        Element body = d.body();
        String content = body.text();

        return content;
    }

    public Elements getLinks(Document d) throws ParseException {
     /* Get the Document for the given web page */

       /* Null check */
        if(d == null)
        {
            throw new ParseException("getLinks() failed. Document parameter is null." );
        }

        Elements links = d.select("a[href]");

        return links;
    }


    public String printBody(Document doc)  throws ParseException
    {
       /* Get the Document for the given web page */

       /* Null check */
        if(doc == null)
        {
            throw new ParseException("getBody() failed. Document parameter is null");
        }

       /* A HTML document has one <body> tag, so we can store its contents using an Element
        * object just as we did for links above by using the Document classes .body() method.
        * the Document classes .body() method;
        */
        Element body =  doc.body();
        return body.text();
    }
}
