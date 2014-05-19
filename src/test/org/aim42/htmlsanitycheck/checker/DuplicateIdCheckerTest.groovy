package org.aim42.htmlsanitycheck.checker

import org.aim42.htmlsanitycheck.html.HtmlPage
import org.junit.Test

// see end-of-file for license information


class DuplicateIdCheckerTest extends GroovyTestCase {

    Checker duplicateIdChecker
    HtmlPage htmlPage
    CheckingResultsCollector collector

    @Test
    public void testDuplicateId() {
        String HTML_WITH_DUPLICATE_ID = '''
           <html>
             <head></head>
              <body>
                   <h1 id="aim42>dummy-heading-1</h1>
                   <a href="#aim42">link-to-aim42</a>
                   <h2 id="aim42">aim42 Architecture Improvement</h3>
              </body>
           </html>'''

        htmlPage = new HtmlPage( HTML_WITH_DUPLICATE_ID )

        duplicateIdChecker = new duplicateIdChecker(
                pageToCheck: htmlPage,
                headline: "Duplicate Id Check"
        )
        collector = duplicateIdChecker.check()

        assertEquals( "expected one finding", 1, collector.nrOfProblems())
        assertEquals( "expected two checks", 2, collector.nrOfItemsChecked)

        String actual = collector.findings.first()
        String expected = "link target nonexisting missing"
        String message = "expected $expected"

        assertEquals(message, expected, actual)
    }
}
