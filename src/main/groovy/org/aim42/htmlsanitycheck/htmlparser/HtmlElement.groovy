package org.aim42.htmlsanitycheck.htmlparser

import org.jsoup.nodes.Element

// see end-of-file for license information

/**
 * Encapsulates a single HTML element with attributes
 * Relies on jsoup.select.Element
 */
class HtmlElement {

    private Element element

    public HtmlElement( Element element ) {
        this.element = element
    }


    /**
     * @return XYZ for img src="XYZ" tags
     */
    public String getSrcAttribute() {
        if (element.tagName().equals("img"))
            element.attr("src")
        else return ""
    }

}
