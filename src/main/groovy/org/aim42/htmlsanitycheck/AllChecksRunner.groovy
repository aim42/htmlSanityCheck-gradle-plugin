package org.aim42.htmlsanitycheck

import org.aim42.htmlsanitycheck.checker.Checker
import org.aim42.htmlsanitycheck.checker.CheckingResultsCollector
import org.aim42.htmlsanitycheck.checker.ImageFileExistChecker
import org.aim42.htmlsanitycheck.htmlparser.HtmlPage

// see end-of-file for license information

/**
 * Coordinates and runs all available html sanity checks.
 * <p>
 * <ol>
 *     <li>parse the html file </li>
 *     <li>initialize image file checker </li>
 *     <li></li>
 *     <li></li>
 * </ol>
 * <p>
 * Uses @see Checker instances (they implement the
 * <a href="http://en.wikipedia.org/wiki/Template_method_pattern">
 * template pattern</a>)
 **/

class AllChecksRunner {

    static Checker imageChecker
    static Checker undefinedInternalLinksChecker
    static Checker duplicateIdChecker

    static CheckingResultsCollector imageCheckingResults

    static String fileName
    static String docDirPath
    static String pathToHtmlFile
    static String imageDirPath


    private HtmlPage pageToCheck


    public AllChecksRunner(
            String docDirPath,
            String fileName,
            String imageDirPath) {
        this.pathToHtmlFile =  docDirPath + fileName
        this.fileName = fileName
        this.imageDirPath = imageDirPath
    }


    public AllChecksRunner() {

        this.fileName = "index.html"
        this.docDirPath = System.getProperty("user.dir") + "/build/docs/"

        this.imageDirPath = docDirPath + "images/";
    }

    /**
     * reads the html page
     */
    private void parseHtml() {
        pageToCheck = new HtmlPage( new File( pathToHtmlFile ))
    }

    /**
     * runs the checks from the command
     * line with default settings...
     * @param args
     */
    public static void main(String[] args) {
        // TODO: read parameter from command line
        AllChecksRunner allChecksRunner = new AllChecksRunner()

        allChecksRunner.fileName = "file-to-test.html"
        allChecksRunner.docDirPath = System.getProperty("user.dir") + "/src/test/resources/"
        allChecksRunner.pathToHtmlFile = docDirPath + fileName
        allChecksRunner.imageDirPath = docDirPath

        allChecksRunner.parseHtml()

        imageChecker = new ImageFileExistChecker(
                pageToCheck: allChecksRunner.pageToCheck,
                baseDir: imageDirPath,
                headline: "Image File Exist Check",
                name: "img links",
                sourceItemName: "img link",
                targetItemName: "image file")

        imageCheckingResults = imageChecker.check()
        println imageCheckingResults.toString()
    }
}