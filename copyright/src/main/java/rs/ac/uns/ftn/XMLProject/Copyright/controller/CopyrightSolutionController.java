package rs.ac.uns.ftn.XMLProject.Copyright.controller;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.XMLProject.Copyright.exception.ResourceNotFoundException;
import rs.ac.uns.ftn.XMLProject.Copyright.models.dto.CopyrightRequestSolutionDTO;
import rs.ac.uns.ftn.XMLProject.Copyright.service.CopyrightSolutionService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/copyright/solution", consumes = MediaType.APPLICATION_XML_VALUE)
public class CopyrightSolutionController {
    private final CopyrightSolutionService solutionService;



    public CopyrightSolutionController(CopyrightSolutionService solutionService) {
        this.solutionService = solutionService;
    }

    @PostMapping
    public ResponseEntity<CopyrightRequestSolutionDTO> createCopyrightSolution(@RequestBody CopyrightRequestSolutionDTO solutionDTO) {
        try {
            return solutionService.createSolution(solutionDTO) ?
                    new ResponseEntity<CopyrightRequestSolutionDTO>(
                            solutionService.getSolutionById(solutionDTO.getRequestNumber()), HttpStatus.CREATED)
                    :
                    ResponseEntity.badRequest().build();
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping (value = "/{id}")
    public ResponseEntity<CopyrightRequestSolutionDTO> getCopyrightSolution(@PathVariable String id) {
        try {
            return ResponseEntity.ok(solutionService.getSolutionById(id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value="/all")
    public ResponseEntity<List<CopyrightRequestSolutionDTO>> getAll() {
        try {
            return ResponseEntity.ok(solutionService.getAllSolutions());
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
