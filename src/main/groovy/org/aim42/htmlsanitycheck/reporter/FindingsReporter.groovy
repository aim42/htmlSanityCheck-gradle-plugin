// see end-of-file for license information

package org.aim42.htmlsanitycheck.reporter

import org.aim42.htmlsanitycheck.checker.CheckingResults

abstract class FindingsReporter {

    private ArrayList<CheckingResults> checkingResults

    int totalNrOfChecksPerformed
    int totalNrOfFindings

    // summary
    int percentSuccessful


    public FindingsReporter( ) {
        checkingResults = new ArrayList<CheckingResults>()
        totalNrOfChecksPerformed = 0

        percentSuccessful = 0
        totalNrOfFindings = 0
    }


    // primarily used for testing
    public void addCheckingField( CheckingResults checkingField) {
        this.checkingResults.add( checkingField )
    }

    abstract void reportFindings()

    abstract void reportSummary()


    public void calculateSummary() {
        totalNrOfChecksPerformed = 0
        checkingResults.each { checkingField ->
            totalNrOfChecksPerformed +=  checkingField.nrOfItemsChecked }

        totalNrOfFindings = 0
        checkingResults.each { checkingField ->
            totalNrOfFindings += checkingField.nrOfProblems()
        }

        // base case: if no checks performed, 100% successful
        if (totalNrOfChecksPerformed <= 0) {
            percentSuccessful = 100
        }
            // at least one check was performed, calculate percentage
        else {
            percentSuccessful =
                    100 - (100*totalNrOfFindings) / totalNrOfChecksPerformed
        }

    }

    protected void reportDetails() {}


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