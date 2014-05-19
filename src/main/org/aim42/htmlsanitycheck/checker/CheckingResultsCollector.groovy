// see end-of-file for license information

package org.aim42.htmlsanitycheck.checker

/**
 * collects results for a specific checking field
 * (i.e. missing images).
 *
 * @author Gernot Starke <gs@gernotstarke.de>
 */

class CheckingResultsCollector {

    public final String headline         // i.e. Image References

    public int nrOfItemsChecked

    public ArrayList<Finding> findings

    /**
     * Initialize everything to empty or zero.
     */
    public CheckingResultsCollector( String headline ) {

        this.headline = headline
        this.nrOfItemsChecked = 0
        this.findings = new ArrayList<Finding>()
    }

    /**
     * add a single finding to the collection,
     * based upon name.
     * @param message: what kind of finding is it?
     */
    public void newFinding( String message ) {
        addFinding( new Finding( message ))
    }

    /**
     * add a single finding to the collection of Finding instances
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


    @Override
    public String toString() {
        int nrOfProblems = nrOfProblems()
        return "Checking results for $headline" + '\n' +
                " $nrOfItemsChecked items checked" + '\n' +
                " $nrOfProblems finding(s)" + '\n' +
                findings.each { it.toString() + '\n'}

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

