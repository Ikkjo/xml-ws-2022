package rs.ac.uns.ftn.XMLProject.Copyright.service;

import com.itextpdf.text.DocumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.XMLProject.Copyright.exception.ResourceNotFoundException;
import rs.ac.uns.ftn.XMLProject.Copyright.models.a.CopyrightSubmissionRequest;
import rs.ac.uns.ftn.XMLProject.Copyright.models.dto.CopyrightRequestSolutionDTO;
import rs.ac.uns.ftn.XMLProject.Copyright.repository.CopyrightRequestSolutionRepository;
import rs.ac.uns.ftn.XMLProject.Copyright.repository.CopyrightSubmissionRequestRepository;
import rs.ac.uns.ftn.XMLProject.Copyright.util.CopyrightPDFTransformer;
import rs.ac.uns.ftn.XMLProject.Copyright.util.CopyrightSolutionDTOMapper;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CopyrightSolutionService {
    private final CopyrightRequestSolutionRepository copyrightRequestSolutionRepository;

    private final CopyrightSubmissionRequestRepository copyrightSubmissionRequestRepository;

    private final EmailService emailService;

    private final CopyrightPDFTransformer copyrightPDFTransformer = new CopyrightPDFTransformer();

    private final CopyrightSolutionDTOMapper copyrightRequestDTOMapper;


    public CopyrightRequestSolutionDTO getSolutionById(String id) throws ResourceNotFoundException {
        return copyrightRequestDTOMapper.getSolutionDto(copyrightRequestSolutionRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new));
    }

    public boolean createSolution(CopyrightRequestSolutionDTO solutionDTO) throws ResourceNotFoundException, JAXBException, IOException, DocumentException {
        boolean saved = copyrightRequestSolutionRepository.save(copyrightRequestDTOMapper.getSolutionEntity(solutionDTO));
        CopyrightSubmissionRequest request = copyrightSubmissionRequestRepository.findById(
                solutionDTO.getRequestNumber()).orElseThrow(ResourceNotFoundException::new);

        request.setAccepted(solutionDTO.isAccepted());
        copyrightSubmissionRequestRepository.update(request);

        copyrightPDFTransformer.generateSolutionHTML(copyrightRequestDTOMapper.getSolutionEntity(solutionDTO));
        copyrightPDFTransformer.generatePDF(solutionDTO.getRequestNumber() + "-solution");

        emailService.send(request.getApplicant().getEmail(), solutionDTO.getRequestNumber());

        return saved;
    }

    public List<CopyrightRequestSolutionDTO> getAllSolutions() {
        return copyrightRequestSolutionRepository.findAll().stream()
                .map(copyrightRequestDTOMapper::getSolutionDto).collect(Collectors.toList());
    }
}
