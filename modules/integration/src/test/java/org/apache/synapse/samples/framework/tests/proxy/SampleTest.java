package org.apache.synapse.samples.framework.tests.proxy;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.synapse.samples.framework.SynapseTestCase;
import org.apache.synapse.samples.framework.clients.BasicHttpClient;
import org.apache.synapse.samples.framework.clients.HttpResponse;

public class SampleTest extends SynapseTestCase {

    private String requestXml;
    private BasicHttpClient httpClient;

    public SampleTest() {
        super(160);
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

    public void testDisableChunkingWithBasicProxy() throws Exception {
        String url = "http://localhost:8280/services/StockQuoteProxy";
        HttpResponse response = httpClient.doPost(url, requestXml.getBytes(),
                "application/soap+xml;charset=UTF-8");
        assertEquals(HttpStatus.SC_OK, response.getStatus());
        assertFalse(HttpHeaders.TRANSFER_ENCODING + " is present in the header",
                response.getHeaders().containsKey(HttpHeaders.TRANSFER_ENCODING));
        assertTrue(HttpHeaders.CONTENT_LENGTH + " is missing in the header",
                response.getHeaders().containsKey(HttpHeaders.CONTENT_LENGTH));
    }
}