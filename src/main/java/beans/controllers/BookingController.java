package beans.controllers;

import beans.models.Event;
import beans.models.Ticket;
import beans.models.User;
import beans.services.BookingService;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author siva
 */
@Controller
public class BookingController {

    @Autowired
    BookingService bookingService;

    @RequestMapping(value = "/booking/ticketprice", method = RequestMethod.GET)
    public String getTicketPrice(String event, String auditorium, 
            String strDateTime, List<Integer> seats, User user, ModelMap modelMap) {
        LocalDateTime dateTime = LocalDateTime.parse(strDateTime);
        double ticketPrice = bookingService.getTicketPrice(event, auditorium, dateTime, seats, user);
        modelMap.addAttribute("ticketPrice", ticketPrice);
        return "ticketPrice";
    }

    @RequestMapping(value = "/booking/bookticket", method = RequestMethod.POST)
    public String bookTicket(@RequestParam String userId, @RequestParam String eventId, 
            @RequestParam String seats, ModelMap modelMap) {
        User user = new User();
        user.setId(Long.parseLong(userId));
        Ticket ticket = new Ticket();
        Event event = new Event();
        event.setId(Long.parseLong(eventId));
        ticket.setEvent(event);
        ticket.setSeats(seats);
        bookingService.bookTicket(user, ticket);
        modelMap.addAttribute("bookedTicket", seats + " seats are booked for the event");
         return "bookingResult";
    }

    @RequestMapping(value = "/booking/tickets", method = RequestMethod.GET)
    public String getTicketsForEvent(String event, String auditorium, String strDateTime, ModelMap modelMap) {
        LocalDateTime date = LocalDateTime.parse(strDateTime);
        List<Ticket> eventTickets = bookingService.getTicketsForEvent(event, auditorium, date);
         modelMap.addAttribute("eventTickets", eventTickets);
         return "eventTickets";
    }
    
    @RequestMapping("/booking/bookticket")
    public String bookTicket(){
        return "bookticket";
    }

}