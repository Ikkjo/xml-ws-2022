package rs.ac.uns.ftn.XMLProject.Copyright.util;

import org.springframework.stereotype.Component;
import rs.ac.uns.ftn.XMLProject.Copyright.models.dto.CopyrightRequestSolutionDTO;
import rs.ac.uns.ftn.XMLProject.Copyright.models.dto.OfficialDTO;
import rs.ac.uns.ftn.XMLProject.Copyright.models.solution.CopyrightRequestSolution;
import rs.ac.uns.ftn.XMLProject.Copyright.models.solution.ObjectFactory;
import rs.ac.uns.ftn.XMLProject.Copyright.models.solution.Official;

@Component
public class CopyrightSolutionDTOMapper {

    private static final ObjectFactory factory = new ObjectFactory();

    public CopyrightSolutionDTOMapper() {
    }

    public CopyrightRequestSolution getSolutionEntity(CopyrightRequestSolutionDTO solutionDTO) {
        CopyrightRequestSolution solution = factory.createCopyrightRequestSolution();

        solution.setAccepted(solutionDTO.isAccepted());
        solution.setSolutionDate(solutionDTO.getSolutionDate());
        solution.setMotivation(solutionDTO.getMotivation());
        solution.setOfficial(getOfficialEntity(solutionDTO.getOfficial()));
        solution.setRequestNumber(solutionDTO.getRequestNumber());

        return solution;
    }

    public CopyrightRequestSolutionDTO getSolutionDto(CopyrightRequestSolution solution) {
        return new CopyrightRequestSolutionDTO(
                solution.getRequestNumber(),
                solution.isAccepted(),
                getOfficialDto(solution.getOfficial()),
                solution.getSolutionDate(),
                solution.getMotivation()
        );
    }

    public Official getOfficialEntity(OfficialDTO officialDTO) {
        Official official = factory.createOfficial();

        official.setFirstName(officialDTO.getFirstName());
        official.setLastName(officialDTO.getLastName());

        return official;
    }

    public OfficialDTO getOfficialDto(Official official) {
        return new OfficialDTO(
                official.getFirstName(),
                official.getLastName()
        );
    }
}
