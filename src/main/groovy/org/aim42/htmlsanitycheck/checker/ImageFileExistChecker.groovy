package org.aim42.htmlsanitycheck.checker

import com.gargoylesoftware.htmlunit.html.HtmlImage

import static org.junit.Assert.assertTrue

// see end-of-file for license information


class ImageFileExistChecker extends Checker {

    private List<HtmlImage> images

    @Override
    public CheckingResults check() {
        super.initResults()

        //get list of all image-tags "<img..." in html file
        images = findAllImageTags()

        checkAllImages()

        return checkingResults

    }


    private ArrayList<Finding> checkAllImages() {

        for (HtmlImage hi : images) {
            checkSingleImage(hi)

        }
    }

    private void checkSingleImage(HtmlImage hi) {
        String imagePath
        imagePath = hi.getSrcAttribute();

        // bookkeeping one more check...
        checkingResults.incNrOfChecks()

        // check only "local" image references
        if (imagePath.startsWith("images/")) {
            doesFileExist(imagePath);
        }
    }


    private List<HtmlImage> findAllImageTags() {
        // we need to filter for images containing 'src="images/"'...

        // the resulting images contains a list of the following form:
        // < [HtmlImage[<img src="images/aim42-logo.png" alt="aim42-logo">],
        //   [HtmlImage[<img src="https://travis-ci.org/aim42/aim42.png?branch=master" alt="unknown">],...>

        return (List<HtmlImage>) pageToCheck.getByXPath("//img");
    }



    private void doesFileExist(String currentImagePath) {
        String imageFilePath = buildDirPath + currentImagePath;
        File imageFile = new File(imageFilePath);

        if (!imageFile.exists()) {
          checkingResults.newFinding( "image " + imageFilePath + " missing")
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


