// see end-of-file for license information

package org.aim42

import org.aim42.htmlsanitycheck.checker.CheckingResults
import org.aim42.htmlsanitycheck.checker.ImageFileExistChecker

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

import com.gargoylesoftware.htmlunit.WebClient
import com.gargoylesoftware.htmlunit.html.HtmlPage
// TODO: encapsulate dependency from htmlunit into one single class,
// instead of spreading this around...


import static org.junit.Assert.assertTrue

/**
 *
 */
class HtmlSanityCheckTask extends DefaultTask {

    static String buildDirPath;
    static String indexFilePath;
    static String imageDirPath;
    static String indexFileURL;



    static HtmlPage htmlPageToCheck;


    /**
     * entry point for several html sanity checks
     * @author Gernot Starke <gs@gernotstarke.de>
     */
    @TaskAction
    public void sanityCheckHtml() {
        // read and parse the html page
        htmlPageToCheck = readHtmlPage()

        CheckingResults imageFilesExist =
        new ImageFileExistChecker(
                headline: "Image File Exist Check",
                name: "img links",
                sourceItemName: "img link",
                targetItemName: "image file",
                pageToCheck: htmlPageToCheck
        ).check

        // TODO CheckingResults internalLinkTargetsExist

        // TODO CheckingResults duplicateIdDefinition


    }


    /**
     * read + parse the html file that will be checked.
     */
    private HtmlPage readHtmlPage( ) {
        String userDir = System.getProperty("user.dir");
        buildDirPath = userDir + "/build/docs/";
        indexFilePath = buildDirPath + "index.html";
        imageDirPath = buildDirPath + "images/";

        //def indexFile = new File(indexFilePath);
        indexFileURL = "file://" + indexFilePath;

        HtmlPage htmlPage = new HtmlPage()

        webClient = new WebClient();

        try {
            htmlPage = webClient.getPage(indexFileURL);
        } catch (IOException e) {
            assertTrue("index.html should be readable by webClient, but threw exception!", false);
            e.printStackTrace();
        }

        println "finished read/parse html file"
        return htmlPage
    }


    /**
     * initSanityCheckReport
     *
     */
    private void initSanityCheckReport() {
        println "init Sanity Check Report"
    }

    // the following code reduces the amount of log-info written by htmlunit-parser.
    static {
        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(java.util.logging.Level.SEVERE);
    }


}


/************************************************************************
 Copyright 2014 Gernot Starke and aim42 contributors

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an
 "AS IS" BASIS,WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 ************************************************************************/
