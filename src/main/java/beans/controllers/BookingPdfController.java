
package beans.controllers;

import beans.models.Ticket;
import beans.services.BookingService;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author siva
 */
@Controller
@RequestMapping("/pdf")
public class BookingPdfController {
    
    @Autowired
    BookingService bookingService;
    
    @RequestMapping(value = "/eventTickets", method = RequestMethod.GET)
    public String getTicketsForEvent(String event, String auditorium, String strDateTime, ModelMap modelMap) {
        LocalDateTime date = LocalDateTime.parse(strDateTime);
        List<Ticket> eventTickets = bookingService.getTicketsForEvent(event, auditorium, date);
         modelMap.addAttribute("eventTickets", eventTickets);
         return "eventTickets";
    }
}
