package beans.wsclient;

import java.io.StringReader;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.springframework.ws.client.core.WebServiceTemplate;

/**
 *
 * @author siva
 */
public class WebServiceClient {

    private static final String MESSAGE
            = "<message xmlns=\"http://epam.com\">Hello Web Service World</message>";
    private final WebServiceTemplate webServiceTemplate = new WebServiceTemplate();

    public void simpleSendAndReceive() {
        StreamSource source = new StreamSource(new StringReader(MESSAGE));
        StreamResult result = new StreamResult(System.out);
        webServiceTemplate.sendSourceAndReceiveToResult(source, result);
    }

    public void customSendAndReceive() {
        StreamSource source = new StreamSource(new StringReader(MESSAGE));
        StreamResult result = new StreamResult(System.out);
        webServiceTemplate.sendSourceAndReceiveToResult("http://localhost:8080/ws/myWs",
                source, result);
    }
}