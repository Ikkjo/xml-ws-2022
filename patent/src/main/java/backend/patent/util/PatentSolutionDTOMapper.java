package backend.patent.util;

import backend.patent.model.solution.PatentSolution;
import backend.patent.model.solution.dto.PatentSolutionDTO;
import org.springframework.stereotype.Component;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Component
public class PatentSolutionDTOMapper {

    public PatentSolution convertSolutionFromDTO(PatentSolutionDTO solutionDTO) throws DatatypeConfigurationException {
        PatentSolution patentSolution = new PatentSolution();

        patentSolution.setIsAccepted(solutionDTO.isAccepted);
        patentSolution.setApplicationNumber(solutionDTO.applicationNumber);
        patentSolution.setOfficial(solutionDTO.official);
        patentSolution.setAcceptedOrRejectedDate(getCurrentDate());
        patentSolution.setExplanation(solutionDTO.explanation);

        return patentSolution;
    }

    public PatentSolutionDTO convertSolutionToDTO(PatentSolution solution) {
        PatentSolutionDTO patentSolutionDTO = new PatentSolutionDTO();

        patentSolutionDTO.isAccepted = solution.isIsAccepted();
        patentSolutionDTO.applicationNumber = solution.getApplicationNumber();
        patentSolutionDTO.official = solution.getOfficial();
        patentSolutionDTO.acceptedOrRejectedDate = solution.getAcceptedOrRejectedDate();
        patentSolutionDTO.explanation = solution.getExplanation();

        return patentSolutionDTO;
    }

    private XMLGregorianCalendar getCurrentDate() throws DatatypeConfigurationException {

        Date now = new Date();
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(now);
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(c);

    }
}
