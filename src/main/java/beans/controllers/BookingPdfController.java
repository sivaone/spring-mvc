package beans.controllers;

import beans.models.Ticket;
import beans.services.BookingService;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @RequestMapping(value = "/eventTickets", method = RequestMethod.GET, produces = "application/pdf")
    public ResponseEntity<byte[]> getTicketsForEvent(String event, String auditorium, String strDateTime, ModelMap modelMap) {
        LocalDateTime date = LocalDateTime.parse(strDateTime);
        List<Ticket> eventTickets = bookingService.getTicketsForEvent(event, auditorium, date);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PDDocument doc = new PDDocument();
        try {
            PDPage page = new PDPage();
            doc.addPage(page);
            PDFont font = PDType1Font.HELVETICA_BOLD;
            try (PDPageContentStream contents = new PDPageContentStream(doc, page)) {
                contents.beginText();
                contents.setFont(font, 12);
                contents.newLineAtOffset(100, 700);
                for (Ticket ticket : eventTickets) {
                    contents.showText(ticket.toString());
                    contents.endText();
                }
            }
            doc.save(output);
            doc.close();

        } catch (IOException ex) {
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        String filename = "output.pdf";
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(output.toByteArray(), headers, HttpStatus.OK);
        return response;
    }
}
