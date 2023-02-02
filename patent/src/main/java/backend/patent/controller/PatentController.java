package backend.patent.controller;

import backend.patent.model.p.dto.CreatePatentRecognitionRequestDTO;
import backend.patent.model.p.dto.RequestForPatentRecognitionDTO;
import backend.patent.util.TokenUtils;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import backend.patent.service.PatentService;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping(value = "/api/patent")
public class PatentController {

    @Autowired
    private PatentService patentService;

    @Autowired
    private TokenUtils tokenUtils;

    @GetMapping(value = "/{id}", produces = "application/xml")
    public RequestForPatentRecognitionDTO getPatentRecognitionRequest(@PathVariable("id") String id) throws Exception {
        return patentService.getPatentRecognitionRequest(id);
    }

    @GetMapping(value = "/all", produces = "application/xml")
    public List<RequestForPatentRecognitionDTO> getAllPatentRecognitionRequests() throws Exception {
        return patentService.getAllPatentRecognitionRequests();
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
        return new ResponseEntity<>(patentService.getRequestForPatentRecognitionHTML(id), HttpStatus.OK);
    }

    @PostMapping(value = "/create", consumes = "application/xml", produces = "application/xml")
    public int createNewPatentRecognitionRequest(@RequestBody CreatePatentRecognitionRequestDTO createPatentRecognitionRequestDTO) throws Exception {
        patentService.createNewPatentRecognitionRequest(createPatentRecognitionRequestDTO);
        return Response.SC_OK;
    }

    @GetMapping(path = "/{id}/rdf", produces = "application/xml")
    public ResponseEntity<String>  getMetadataRdf(@PathVariable String id) {
        return new ResponseEntity<>(patentService.getRdfMetadata(id), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}/json", produces = "application/json")
    public ResponseEntity<String>  getMetadataJson(@PathVariable String id) {
        return new ResponseEntity<>(patentService.getJsonMetadata(id), HttpStatus.OK);
    }

    @GetMapping(path = "/search/content/{content}", produces = "application/xml")
    public List<RequestForPatentRecognitionDTO> searchByContent(@PathVariable String content, HttpServletRequest request) {
        String role = tokenUtils.getRoleFromHeader(request);
        return patentService.searchByContent(role, content);
    }

    @GetMapping(path = "/search/metadata/{condition}", produces = "application/xml")
    public List<RequestForPatentRecognitionDTO> searchByMetadata(@PathVariable String condition, HttpServletRequest request) {
        String role = tokenUtils.getRoleFromHeader(request);
        return patentService.search(role, condition);
    }

    @GetMapping(path = "/{id}/linked-document", produces = "application/json")
    public ResponseEntity<String>  getLinkedDocument(@PathVariable String id) {
        return new ResponseEntity<>(patentService.getLinkedDocuments(id), HttpStatus.OK);
    }
}
