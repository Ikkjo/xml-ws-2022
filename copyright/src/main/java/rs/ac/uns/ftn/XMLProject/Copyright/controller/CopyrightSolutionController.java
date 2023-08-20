package rs.ac.uns.ftn.XMLProject.Copyright.controller;

import com.itextpdf.text.DocumentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.XMLProject.Copyright.exception.ResourceNotFoundException;
import rs.ac.uns.ftn.XMLProject.Copyright.models.dto.CopyrightRequestSolutionDTO;
import rs.ac.uns.ftn.XMLProject.Copyright.service.CopyrightSolutionService;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/copyright/solution")
public class CopyrightSolutionController {
    private final CopyrightSolutionService solutionService;



    public CopyrightSolutionController(CopyrightSolutionService solutionService) {
        this.solutionService = solutionService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> createCopyrightSolution(@RequestBody CopyrightRequestSolutionDTO solutionDTO) {
        try {
            return solutionService.createSolution(solutionDTO) ?
                    new ResponseEntity<CopyrightRequestSolutionDTO>(
                            solutionService.getSolutionById(solutionDTO.getRequestNumber()), HttpStatus.CREATED)
                    :
                    ResponseEntity.badRequest().build();
        } catch (ResourceNotFoundException | IOException | DocumentException | JAXBException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping (value = "/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<CopyrightRequestSolutionDTO> getCopyrightSolution(@PathVariable String id) {
        try {
            return ResponseEntity.ok(solutionService.getSolutionById(id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value="/all", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<CopyrightRequestSolutionDTO>> getAll() {
        try {
            return ResponseEntity.ok(solutionService.getAllSolutions());
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
