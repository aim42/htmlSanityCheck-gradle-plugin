package org.aim42.htmlsanitycheck.html

import org.junit.Test

// see end-of-file for license information


class HtmlPageTest extends GroovyTestCase {

    /**
     * this file resides in git - so we can
     * hardcode the filename into the tests
     */
    final String fileName = 'file-to-test.html'

    /**
     * the local (relative) path to the test/resources directory
     * is fix - so we can hardcode the name into the tests
     */
    final String localPath = "/src/test/resources/"


    final String HTML_WITH_IMG_TAG =
            '''
           <html>
             $HTML_HEAD
              <body>
                   <h1>dummy-heading-1</h1>
                   <img src="images/test_xyz_uvw.jpg" >
              </body>
           </html>'''

    final String HTML_WITH_TWO_IMG_TAGS =
            '''
           <html>
             $HTML_HEAD
              <body>
                   <img src='test.jpg'/>
                   <img src='test.jpg'/>
              </body>
           </html>'''

    File tmpFile


    public void setUp() {
        tmpFile = File.createTempFile("testfile", "html")
    }


    @Test
    public void testGetTwoImagesFromHtml() {

        HtmlPage htmlpage = new HtmlPage(HTML_WITH_TWO_IMG_TAGS)

        ArrayList<HtmlElement> images = htmlpage.getAllImageTags()

        // should yield TWO image tags!
        assertEquals("TWO images expected", 2, images.size())


    }


    @Test
    public void testGetOneImageFromHtml() {

        HtmlPage htmlpage = new HtmlPage(HTML_WITH_IMG_TAG)

        ArrayList images = htmlpage.getAllImageTags()

        // should yield exactly ONE image tag!
        assertEquals("ONE image expected", 1, images.size())

    }

    @Test
    public void testGetOneImageFromHtmlFile() {
        tmpFile.write( HTML_WITH_IMG_TAG )

        HtmlPage htmlpage = new HtmlPage( tmpFile )

        ArrayList images = htmlpage.getAllImageTags()

        // should yield exactly ONE image tag!
        assertEquals("ONE image expected", 1, images.size())

    }

    @Test
    public void testGetTwoImagesFromHtmlFile() {
        tmpFile.write( HTML_WITH_TWO_IMG_TAGS )

        HtmlPage htmlpage = new HtmlPage( tmpFile )

        ArrayList images = htmlpage.getAllImageTags()

        // should yield exactly ONE image tag!
        assertEquals("ONE image expected", 2, images.size())

    }


    @Test
    public void testGetHtmlImgTagsFromFile() {
        String userDir = System.getProperty("user.dir")
        String filePath = userDir + localPath + fileName

        // make sure the generated file exists...
        assertTrue( "file $filePath  does NOT exist (but should!)",
                new File(filePath).exists())

        HtmlPage htmlPage = new HtmlPage( new File(filePath) )

        ArrayList images = htmlPage.getAllImageTags()
        assertEquals( "expected 2 images", 2, images.size())


    }

}
