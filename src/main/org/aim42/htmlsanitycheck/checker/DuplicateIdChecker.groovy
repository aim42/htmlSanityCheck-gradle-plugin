package org.aim42.htmlsanitycheck.checker

import org.aim42.htmlsanitycheck.html.HtmlElement


// see end-of-file for license information

class DuplicateIdChecker extends Checker {

    // the pure Id's as a set (duplicates are already removed here)
    // we take this set as basis for our checks!
    Set<String> idStrings

    // all html-tags containing ids including potential duplicates
    List<String> tagsWithId

    @Override
    CheckingResultsCollector check() {
        super.initResults()

        //get list of all tagsWithId '<... id="XYZ"...' in html file
        idStrings = pageToCheck.getAllIdStrings().toSet()

        tagsWithId = pageToCheck.getAllIds()

        checkForDuplicateIds()

        return checkingResults

    }

    /*
    * iterate over all id's to check for duplicate definitions
     */
    private void checkForDuplicateIds() {

        // tagsWithId is the SET of id-strings in the document
        idStrings.each {
            checkForDuplicateDefinition( it )
        }

    }


    private void checkForDuplicateDefinition(String idString) {
        checkingResults.incNrOfChecks()

        // if idString appears more than once in tagsWithId,
        // we found
        List<String> tagsWithSpecificId =
                getAllTagsWithSpecificId( idString, tagsWithId).collect { it.toString() }

        if (tagsWithSpecificId.size() > 1) {
            String findingStrings = tagsWithSpecificId.join(",")
            checkingResults.newFinding(  "$idString has multiple definitions:" + findingStrings )
        }
    }


    /**
     * find all tags with specific id value
     * @param id
     * @param allTags List of tags containing id-attribute
     */
    public static List<HtmlElement> getAllTagsWithSpecificId( String idString,
                                                              List<HtmlElement> allTags ) {
         return allTags.findAll { htmlElement ->
            htmlElement.idAttribute  == idString
        }
    }
}


/*=====================================================================
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
 =====================================================================*/

