
package util;

import beans.models.Ticket;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

/**
 *
 * @author siva
 */
public class PdfMessageConverter implements HttpMessageConverter<Object>{

    @Override
    public boolean canRead(Class<?> type, MediaType mt) {
        return false;
    }

    @Override
    public boolean canWrite(Class<?> type, MediaType mt) {
        return true;
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return Arrays.asList(new MediaType("application","pdf"));
    }

    @Override
    public Object read(Class<? extends Object> type, HttpInputMessage him) throws IOException, HttpMessageNotReadableException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void write(Object t, MediaType mt, HttpOutputMessage hom) throws IOException, HttpMessageNotWritableException {
        List<Ticket> eventTickets = (List<Ticket>) t;
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
        hom.getBody().write(output.toByteArray());
    }
    
}
