package rs.ac.uns.ftn.XMLProject.Copyright.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.uns.ftn.XMLProject.Copyright.exception.ResourceNotFoundException;
import rs.ac.uns.ftn.XMLProject.Copyright.service.CopyrightReportService;

import javax.xml.datatype.XMLGregorianCalendar;
import java.io.ByteArrayInputStream;

@RestController(value = "api/copyright/report")
@RequiredArgsConstructor
public class CopyrightReportController {

    private final CopyrightReportService reportService;

    @GetMapping(produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> getReportPdf(@RequestParam String startDate, @RequestParam String endDate) {
        try {
            ByteArrayInputStream byteFile = reportService.getReportPDF(startDate, endDate);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "inline; filename=generated-report-(" + startDate + ")-(" + endDate +").pdf");

            return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(byteFile));
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
