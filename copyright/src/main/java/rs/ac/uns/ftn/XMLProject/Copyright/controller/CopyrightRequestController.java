package rs.ac.uns.ftn.XMLProject.Copyright.controller;

import jakarta.websocket.server.PathParam;
import org.apache.catalina.connector.Response;
import rs.ac.uns.ftn.XMLProject.Copyright.exception.ResourceNotFoundException;
import rs.ac.uns.ftn.XMLProject.Copyright.models.dto.CopyrightSubmissionRequestDTO;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.XMLProject.Copyright.service.CopyrightRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.print.attribute.standard.Media;
import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping(path = "/api/copyright/request", consumes = MediaType.APPLICATION_XML_VALUE)
public class CopyrightRequestController {

    private final CopyrightRequestService copyrightRequestService;

    private static final Logger logger = LoggerFactory.getLogger(CopyrightRequestController.class);

    public CopyrightRequestController(CopyrightRequestService copyrightRequestService) {
        this.copyrightRequestService = copyrightRequestService;
    }

    @GetMapping(path = "/all", produces = "application/xml")
    public List<CopyrightSubmissionRequestDTO> getAllCopyrightRequests() {
        return copyrightRequestService.getAllCopyrightSubmissionRequests();
    }

    @GetMapping(path = "/{id}", produces = "application/xml")
    public ResponseEntity<CopyrightSubmissionRequestDTO> getCopyrightRequestById(@PathVariable("id") String id) {
        try {
            return ResponseEntity.ok(copyrightRequestService.getCopyrightSubmissionRequestById(id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<String> createCopyrightSubmissionRequest(@RequestBody CopyrightSubmissionRequestDTO copyrightSubmissionRequestDTO) {
        logger.info(copyrightSubmissionRequestDTO.toString());
        copyrightRequestService.createCopyrightSubmissionRequest(copyrightSubmissionRequestDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> getDocumentPdf(@PathVariable String id) {
        try {
            ByteArrayInputStream byteFile = copyrightRequestService.getCopyrightSubmissionRequestPDF(id);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "inline; filename=generated-request-" + id + ".pdf");

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

    @GetMapping(path = "/{id}/linked", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getLinkedSolution(@PathVariable String id) {
        return ResponseEntity.ok(copyrightRequestService.getLinkedDocuments(id));
    }

    @GetMapping(path = "/{id}/rdf")
    public ResponseEntity<String>  getMetadataRdf(@PathVariable String id) {
        return ResponseEntity.ok(copyrightRequestService.getRdfMetadata(id));
    }

    @GetMapping(path = "/{id}/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String>  getMetadataJson(@PathVariable String id) {
        return ResponseEntity.ok(copyrightRequestService.getJsonMetadata(id));
    }

    @GetMapping(path = "/search/metadata", produces = "application/xml")
    public ResponseEntity<List<CopyrightSubmissionRequestDTO>> searchByMetadata(@RequestParam String condition) {
        return ResponseEntity.ok(copyrightRequestService.searchMetadata(condition));
    }

    @GetMapping(path = "/search/", produces = "application/xml")
    public ResponseEntity<List<CopyrightSubmissionRequestDTO>> searchByContent(@RequestParam String content) {
        return ResponseEntity.ok(copyrightRequestService.search(content));
    }
}
