package org.aim42.htmlsanitycheck.checker

// see end-of-file for license information

import com.gargoylesoftware.htmlunit.html.HtmlPage

/**
 * Base class for the different concrete checkers (i.e. ImageChecker)
 *
 * No constructor is defined, allowing for arbitrary "named parameters"
 * in constructor calls.
 *
 * @author Gernot Starke <gs@gernotstarke.de>
 */

abstract class Checker {

    String headline         // i.e. Image References
    String name             // i.e. missing images

    // source-item is checked against target-item
    String sourceItemName   // i.e. image-reference, anchor/link
    String targetItemName   // i.e. image-file, id/bookmark

    // i.e. image-links, internal links (anchors/bookmarks)
    CheckingResults checkingResults

    HtmlPage pageToCheck


    protected void initResults() {
        checkingResults = new CheckingResults()

    }

    /**
     * perform a particular kind of checks, i.e. check if all
     * referenced image files really exist on their expected location
     *
     * @return collected results of this checking-group
     */
    abstract public CheckingResults check( )

    // TODO: check for valid parameters
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


