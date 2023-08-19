package backend.patent.controller;

import backend.patent.model.p.dto.CreatePatentRecognitionRequestDTO;
import backend.patent.model.p.dto.RequestForPatentRecognitionDTO;
import backend.patent.util.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import backend.patent.service.PatentService;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/patent")
@RequiredArgsConstructor
public class PatentController {

    private final PatentService patentService;
    private final TokenUtils tokenUtils;

    @GetMapping(value = "/{id}", produces = "application/xml")
    public ResponseEntity<?> getPatentRecognitionRequest(@PathVariable("id") String id) throws Exception {
        try {
            return ResponseEntity.of(patentService.getPatentRecognitionRequest(id));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e);
        }
    }

    @GetMapping(value = "/all", produces = "application/xml")
    public ResponseEntity<?> getAllPatentRecognitionRequests() {
        try {
            return ResponseEntity.ok(patentService.getAllPatentRecognitionRequests());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e);
        }
    }

    @GetMapping(path = "/{id}/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> getDocumentPdf(@PathVariable String id) {

        ByteArrayInputStream byteFile = patentService.getRequestForPatentRecognitionPDF(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=generated-" + id + ".pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(byteFile));
    }

    @GetMapping(path = "/{id}/html", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> getDocumentHtml(@PathVariable String id) {
        return ResponseEntity.ok(patentService.getRequestForPatentRecognitionHTML(id));
    }

    @PostMapping(value = "/create", consumes = "application/xml", produces = "application/xml")
    public ResponseEntity<?> createNewPatentRecognitionRequest(@RequestBody CreatePatentRecognitionRequestDTO createPatentRecognitionRequestDTO) {
        try {
            String requestId = patentService.createNewPatentRecognitionRequest(createPatentRecognitionRequestDTO);
            return ResponseEntity.created(URI.create(requestId)).build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e);
        }
    }

    @GetMapping(path = "/{id}/rdf", produces = "application/xml")
    public ResponseEntity<String>  getMetadataRdf(@PathVariable String id) {
        return ResponseEntity.of(patentService.getRdfMetadata(id));
    }

    @GetMapping(path = "/{id}/json", produces = "application/json")
    public ResponseEntity<String>  getMetadataJson(@PathVariable String id) {
        return ResponseEntity.of(patentService.getJsonMetadata(id));
    }

    @GetMapping(path = "/search/content/{content}", produces = "application/xml")
    public ResponseEntity<?> searchByContent(@PathVariable String content, HttpServletRequest request) {
        String role = tokenUtils.getRoleFromHeader(request);
        return ResponseEntity.ok(patentService.searchByContent(role, content));
    }

    @GetMapping(path = "/search/metadata/{condition}", produces = "application/xml")
    public ResponseEntity<?> searchByMetadata(@PathVariable String condition, HttpServletRequest request) {
        String role = tokenUtils.getRoleFromHeader(request);
        return ResponseEntity.ok(patentService.search(role, condition));
    }

    @GetMapping(path = "/{id}/linked-document", produces = "application/json")
    public ResponseEntity<String>  getLinkedDocument(@PathVariable String id) {
        return ResponseEntity.ok(patentService.getLinkedDocuments(id));
    }
}
