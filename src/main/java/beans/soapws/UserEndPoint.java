package beans.soapws;

import beans.daos.UserDAO;
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
public class UserEndPoint {

    private static final String NAMESPACE_URI = "http://epam.com/myws";

    @Autowired
    UserDAO userDAO;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserRequest")
    @ResponsePayload
    public GetUserResponse getUser(@RequestPayload GetUserRequest request) {
        GetUserResponse response = new GetUserResponse();
        response.setUser(userDAO.getByEmail(request.getEmail()));
        return response;
    }
}
