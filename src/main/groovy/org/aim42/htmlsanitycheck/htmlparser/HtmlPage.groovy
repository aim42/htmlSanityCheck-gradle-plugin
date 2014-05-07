package org.aim42.htmlsanitycheck.htmlparser

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

// see end-of-file for license information

/**
 * Encapsulates a "real" html parser and provides
 * convenience methods to access anchor and image links
 * from html.
 *
 * Relies on http://jsoup.org parser
 */
class HtmlPage {

    // jsoup Document
    private Document document


    public HtmlPage( String text ) {
        // Jsoup promises to parse without exception -
        // we believe it, as our wrapper is for checking
        // purposes only
        document = Jsoup.parse( text, "UTF-8")
    }


    public HtmlPage( File file ) {
        assert file.exists()
        document = Jsoup.parse( file, "UTF-8" )
    }

    /**
     * builds a list from all '<img src="XYZ"/>' tags
     * @return immutable ArrayList
     */
    public final ArrayList<HtmlElement> getAllImageTags() {
        Elements elements = document?.getElementsByTag("img")

        ArrayList<HtmlElement> tempElements = new ArrayList()

        elements.each { element ->
            tempElements.add( new HtmlElement( element ) )
        }

        return tempElements

        // alternative: document?.getElementsByTag("img").asList()
    }




}
