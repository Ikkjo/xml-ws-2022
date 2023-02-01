package rs.ac.uns.ftn.XMLProject.Copyright.controller;

import rs.ac.uns.ftn.XMLProject.Copyright.exception.ResourceNotFoundException;
import rs.ac.uns.ftn.XMLProject.Copyright.models.dto.CopyrightSubmissionRequestDTO;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.XMLProject.Copyright.service.CopyrightRequestService;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping(value = "/api/copyright/request")
public class CopyrightRequestController {

    private final CopyrightRequestService copyrightRequestService;

    public CopyrightRequestController(CopyrightRequestService copyrightRequestService) {
        this.copyrightRequestService = copyrightRequestService;
    }

    @GetMapping(value = "/all", produces = "application/xml")
    public List<CopyrightSubmissionRequestDTO> getAllCopyrightRequests() {
        return copyrightRequestService.getAllCopyrightSubmissionRequests();
    }

    @GetMapping(value = "/{id}", produces = "application/xml")
    public ResponseEntity<CopyrightSubmissionRequestDTO> getCopyrightRequestById(@PathVariable("id") String id) {
        try {
            return ResponseEntity.ok(copyrightRequestService.getCopyrightSubmissionRequestById(id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping(consumes = "application/xml", produces = "application/xml")
    public ResponseEntity<String> createCopyrightSubmissionRequest(@RequestBody CopyrightSubmissionRequestDTO copyrightSubmissionRequestDTO) {
        copyrightRequestService.createCopyrightSubmissionRequest(copyrightSubmissionRequestDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/{id}/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> getDocumentPdf(@PathVariable String id) {
        try {
            ByteArrayInputStream byteFile = copyrightRequestService.getCopyrightSubmissionRequestPDF(id);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "inline; filename=generated-" + id + ".pdf");

            return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(byteFile));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(path = "/{id}/html", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> getDocumentHtml(@PathVariable String id) {
        try {
            return ResponseEntity.ok(copyrightRequestService.getCopyrightSubmissionRequestHTML(id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
