package org.aim42.htmlsanitycheck.checker

import org.aim42.htmlsanitycheck.html.HtmlElement

// see end-of-file for license information


class InternalLinksChecker extends Checker {


    private List<String> ids    // id="XYZ"
    private List<String> hrefs  // a href="XYZ"

    @Override
    public CheckingResultsCollector check() {
        super.initResults()

        //get list of all a-tags "<a src=..." in html file
        hrefs = pageToCheck.getAllHrefStrings()

        // get list of all id="XYZ"
        ids = pageToCheck.getAllIdStrings()

        checkAllInternalLinks( )

        return checkingResults
    }

    /*
     * check all internal links against the existing id's
     * @return
     */
    private void checkAllInternalLinks() {

        // for all hrefs check wether the id exists
        hrefs.each {
            checkSingleInternalLink( it )
        }
    }

    /*
    * check a single internal link (href) against the existing id's within
    * the html document
     */
    private void checkSingleInternalLink( String href ) {

        // bookkeeping and statistics
        checkingResults.incNrOfChecks()

        // check only "local" references
        if (!href.startsWith("http:")) {
            doesLinkTargetExist(href)
        }
    }

    /**
     * check if the id for the href parameter exists
     *
     * @param href== XYZ in id="XYZ"
     **/
    private void doesLinkTargetExist(String href) {

        if (!ids.contains( href )) {
            String findingText = "link target $href missing"
            checkingResults.newFinding(findingText)
        }

    }


}
/*========================================================================
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
 ========================================================================*/


