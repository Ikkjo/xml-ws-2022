package rs.ac.uns.ftn.XMLProject.Copyright.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.XMLProject.Copyright.exception.ResourceNotFoundException;
import rs.ac.uns.ftn.XMLProject.Copyright.models.dto.CopyrightRequestSolutionDTO;
import rs.ac.uns.ftn.XMLProject.Copyright.repository.CopyrightRequestSolutionRepository;
import rs.ac.uns.ftn.XMLProject.Copyright.util.CopyrightSolutionDTOMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CopyrightSolutionService {
    CopyrightRequestSolutionRepository copyrightRequestSolutionRepository;

    CopyrightSolutionDTOMapper copyrightRequestDTOMapper;

    public CopyrightRequestSolutionDTO getSolutionById(String id) throws ResourceNotFoundException {
        return copyrightRequestDTOMapper.getSolutionDto(copyrightRequestSolutionRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new));
    }

    public boolean createSolution(CopyrightRequestSolutionDTO solutionDTO) {
        return copyrightRequestSolutionRepository.save(copyrightRequestDTOMapper.getSolutionEntity(solutionDTO));
    }

    public List<CopyrightRequestSolutionDTO> getAllSolutions() {
        return copyrightRequestSolutionRepository.findAll().stream()
                .map(copyrightRequestDTOMapper::getSolutionDto).collect(Collectors.toList());
    }
}
