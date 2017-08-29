package beans.controllers;

import beans.models.Event;
import beans.models.Ticket;
import beans.models.User;
import beans.services.BookingService;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author siva
 */
@RestController
@RequestMapping("/rest")
public class BookingControllerRest {

    @Autowired
    BookingService bookingService;

    @RequestMapping(value = "/booking/ticketprice", method = RequestMethod.GET)
    public double getTicketPrice(String event, String auditorium, 
            String strDateTime, String seat, User user) {
        LocalDateTime dateTime = LocalDateTime.parse(strDateTime);
        List<Integer> seats = Arrays.asList(Integer.parseInt(seat));
        double ticketPrice = bookingService.getTicketPrice(event, auditorium, dateTime, seats, user);
        return ticketPrice;
    }

    @RequestMapping(value = "/booking/bookticket", method = RequestMethod.POST, 
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Ticket bookTicket(User user, Ticket ticket) {
        return bookingService.bookTicket(user, ticket);
    }

    @RequestMapping(value = "/booking/tickets", method = RequestMethod.GET, 
            produces = {MediaType.APPLICATION_JSON_VALUE, "application/pdf"})
    public List<Ticket> getTicketsForEvent(String event, String auditorium, String strDateTime) {
        LocalDateTime date = LocalDateTime.parse(strDateTime);
        return bookingService.getTicketsForEvent(event, auditorium, date);
    }

}