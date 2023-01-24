package controller;

import models.p.RequestForPatentRecognition;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.PatentService;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

@RestController
@RequestMapping(value = "/api/patent")
public class PatentController {

    private final PatentService patentService;

    public PatentController(PatentService patentService) {
        this.patentService = patentService;
    }

    @GetMapping(value = "/all", produces = "application/xml")
    public ArrayList<RequestForPatentRecognition> getAllPatentRecognitionRequests() throws Exception {
        return patentService.getAllPatentRecognitionRequests();
    }

    @GetMapping(path = "/{id}/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> getDocumentPdf(@PathVariable String id) {

        ByteArrayInputStream byteFile = patentService.getPdfZahtev(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=generated-" + id + ".pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(byteFile));
    }

    @GetMapping(path = "/{id}/html", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> getDocumentHtml(@PathVariable String id) {
        return new ResponseEntity<>(patentService.getHtmlZahtev(id), HttpStatus.OK);
    }

    /*@GetMapping(value = "/{id}", produces = "application/xml")
    public RequestForPatentRecognitionDTO getById(@PathVariable("id") String id) {
        return patentService.getById(id);
    }*/
}