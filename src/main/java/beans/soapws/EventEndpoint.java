package beans.soapws;

import beans.daos.EventDAO;
import beans.models.Auditorium;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 *
 * @author siva
 */
@Endpoint
public class EventEndpoint {

    private static final String NAMESPACE_URI = "http://epam.com/myws";

    @Autowired
    private EventDAO eventDao;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEventRequest")
    @ResponsePayload
    public GetEventResponse getEvent(@RequestPayload GetEventRequest request) {
        GetEventResponse response = new GetEventResponse();
        response.setEvent(eventDao.get(request.getEventName(),
                new Auditorium(request.getAuditorium()), request.getEventDate()));
        return response;
    }
}
