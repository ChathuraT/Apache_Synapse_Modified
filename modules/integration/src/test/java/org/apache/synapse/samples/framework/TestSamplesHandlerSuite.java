/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 *  * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.synapse.samples.framework;

import junit.framework.TestSuite;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.synapse.samples.framework.tests.proxy.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This is executed by maven and handles which samples to run
 */
public class TestSamplesHandlerSuite extends TestSuite {

    private static final Log log = LogFactory.getLog(TestSamplesHandlerSuite.class);
    private static HashMap<String, Object> sampleClassRepo
            = new HashMap<String, Object>();

    public static TestSuite suite() {

        //Adding all samples available
        populateSamplesMap();

        ArrayList<Class> suiteClassesList = new ArrayList<Class>();
        TestSuite suite = new TestSuite();

        String inputSuiteName = System.getProperty("suite");
        String tests = System.getProperty("tests");
        String suiteName = "SamplesSuite";

        //preparing suites, if specified
        if (inputSuiteName != null) {
            if (inputSuiteName.equalsIgnoreCase("proxy")) {
                suiteName = "ProxySamplesSuite";
                for (int i = 150; i <= 170; i++) {
                    Class testClass = (Class) sampleClassRepo.get(Integer.toString(i));
                    if (testClass != null) {
                        suiteClassesList.add(testClass);
                    }
                }
            }

        } else if (tests != null) {
            String[] testArray = tests.split(",");
            suiteName = "SelectedSamplesSuite";
            for (String testNum : testArray) {
                Class testClass = (Class) sampleClassRepo.get(testNum);
                if (testClass != null) {
                    suiteClassesList.add(testClass);
                }
            }
        } else {
            suiteName = "AllSamplesSuite";
            for (int i = 0; i <= 20000; i++) {
                Class testClass = (Class) sampleClassRepo.get(Integer.toString(i));
                if (testClass != null) {
                    suiteClassesList.add(testClass);
                }
            }
        }

        for (Class testClass : suiteClassesList) {
            suite.addTestSuite(testClass);
            log.info("Adding Sample:" + testClass);
        }
        suite.setName(suiteName);

        return suite;
    }

    private static void populateSamplesMap() {


        //Proxy Service
//        sampleClassRepo.put("150", Sample150.class);
//        sampleClassRepo.put("151", Sample151.class);
//        sampleClassRepo.put("152", Sample152.class);
//        sampleClassRepo.put("153", Sample153.class); // unable to load the JKS files
//        sampleClassRepo.put("154", Sample154.class);
//        sampleClassRepo.put("155", Sample155.class);
//        sampleClassRepo.put("156", Sample156.class);
//        sampleClassRepo.put("157", Sample157.class);
        sampleClassRepo.put("162", SampleTest.class);
//        sampleClassRepo.put("161", Sample161.class);

    }
}
