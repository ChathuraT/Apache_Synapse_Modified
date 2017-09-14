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
package org.apache.synapse.samples.framework.tests.proxy;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.synapse.samples.framework.SynapseTestCase;
import org.apache.synapse.samples.framework.clients.BasicHttpClient;
import org.apache.synapse.samples.framework.clients.HttpResponse;

import java.util.HashMap;
import java.util.Map;

public class Sample8004 extends SynapseTestCase {

    private String requestXml;
    private BasicHttpClient httpClient;

    public Sample8004() {
        super(8004);
        httpClient = new BasicHttpClient();
        requestXml = "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:ser=\"http://services.samples\" xmlns:xsd=\"http://services.samples/xsd\">\n" +
                "       <soap:Header/>\n" +
                "       <soap:Body>\n" +
                "          <ser:getQuote>\n" +
                "             <ser:request>\n" +
                "                <xsd:symbol>IBM</xsd:symbol>\n" +
                "             </ser:request>\n" +
                "          </ser:getQuote>\n" +
                "       </soap:Body>\n" +
                "    </soap:Envelope>";
    }

    public void testResponseHTTPStatusCode() throws Exception {
        String url = "http://localhost:8280/services/StockQuoteProxy";
//        Thread.sleep(100000);
        Map<String,String> headers = new HashMap<String, String>();
        headers = new HashMap<String, String>();
        headers.put("SOAPAction", "urn:getPrice");
        log.info("Running test: Routing Messages based on HTTP URL, HTTP Headers and " +
                "Query Parameters");

        HttpResponse response = httpClient.doPost(url, requestXml.getBytes(),
                "application/soap+xml;charset=UTF-8",headers);
        assertEquals(200, response.getStatus());
        assertTrue(response.getBodyAsString().contains("42"));
    }

}