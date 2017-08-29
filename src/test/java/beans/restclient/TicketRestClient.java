
package beans.restclient;

import org.springframework.web.client.RestTemplate;

/**
 *
 * @author siva
 */
public class TicketRestClient {
    
    RestTemplate restTemplate = new RestTemplate();
    
    public void testGetTicketsForEvent(){
        String response = restTemplate.getForObject("http://localhost:8080/spring-mvc/rest/booking/tickets", String.class, 
                "testevent", "auditorium1", "2017-10-10");
    }
}
