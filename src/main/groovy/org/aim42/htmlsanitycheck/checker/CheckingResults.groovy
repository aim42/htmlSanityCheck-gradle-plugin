// see end-of-file for license information

package org.aim42.htmlsanitycheck.checker

/**
 * The collected results for a specific checkingResults (i.e. missing images).
 *
 * @author Gernot Starke <gs@gernotstarke.de>
 */

class CheckingResults {

    int nrOfItemsChecked

    ArrayList<Finding> findings

    /**
     * Initialize everything to empty or zero.
     */
    public CheckingResults() {
        nrOfItemsChecked = 0
        findings = new ArrayList<Finding>()
    }

    /**
     * called when an issue has been found
     */
    public void newFinding() {
        f = new Finding()
    }


    /**
     * add a single finding to the collection of Finding instances
     * @deprecated shall only be used for testing
     * @param singleFinding
     */
    public void addFinding(Finding singleFinding) {
        findings.add(singleFinding)
    }

    /**
     * one more check :-)
     */
    public void incNrOfChecks() {
        nrOfItemsChecked += 1
    }

    /**
     *
     * @return ( int ) the nr of issues found for this checkingResults.
     */
    public int nrOfProblems() {
        return findings.size()
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

