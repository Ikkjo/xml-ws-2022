package backend.patent.controller;

import backend.patent.model.solution.dto.PatentSolutionDTO;
import org.apache.catalina.connector.Response;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import backend.patent.service.PatentSolutionService;

import java.io.ByteArrayInputStream;
import java.net.URI;
import java.util.Optional;

@RestController()
@RequestMapping(value = "/api/patent/solution")
public class PatentSolutionController {

    private final PatentSolutionService solutionService;

    public PatentSolutionController(PatentSolutionService solutionService) {
        this.solutionService = solutionService;
    }

    @PostMapping(consumes = "application/xml", produces = "application/xml")
    public ResponseEntity<?> createPatentSolution(@RequestBody PatentSolutionDTO solutionDTO) {
        try {
            String applicationNumber = solutionService.createPatentSolution(solutionDTO);
            return ResponseEntity.created(URI.create(applicationNumber)).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @GetMapping(path = "/{id}/html", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<?> getDocumentHtml(@PathVariable String id) {
        Optional<String> solutionHtml = Optional.of(solutionService.getSolutionHTML(id));
        return ResponseEntity.of(solutionHtml);
    }

    @GetMapping(path = "/report/{startDate}/{endDate}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> getReportPDF(@PathVariable String startDate, @PathVariable String endDate) {

        ByteArrayInputStream byteFile = solutionService.getReportPDF(startDate, endDate);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=report.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(byteFile));
    }
}
