package backend.patent.controller;

import backend.patent.model.solution.dto.PatentSolutionDTO;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import backend.patent.service.PatentSolutionService;

import java.io.ByteArrayInputStream;

@RestController()
@RequestMapping(value = "/api/solution")
public class PatentSolutionController {
    @Autowired
    private PatentSolutionService solutionService;

    @PostMapping(value = "/create", consumes = "application/xml", produces = "application/xml")
    public int createPatentSolution(@RequestBody PatentSolutionDTO solutionDTO) {
        try {
            solutionService.createPatentSolution(solutionDTO);
            return Response.SC_OK;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(path = "/{id}/html", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> getDocumentHtml(@PathVariable String id) {
        return new ResponseEntity<>(solutionService.getSolutionHTML(id), HttpStatus.OK);
    }

    @GetMapping(path = "/report/{startDate}/{endDate}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> getReportPDF(@PathVariable String startDate, @PathVariable String endDate) {

        ByteArrayInputStream byteFile = solutionService.getReportPDF(startDate, endDate);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=report.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(byteFile));
    }
}
