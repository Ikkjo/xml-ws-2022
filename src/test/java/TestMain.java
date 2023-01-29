import java.rs.ac.uns.ftn.XMLProject.Copyright.ATest;
import xml.PTest;

public class TestMain {

    private static final String ATestFilepath = "xml/a-1_instance1.xml";
    private static final String PTestFilePath = "xml/p-1_instance.xml";

    public static void main(String[] args) {

        ATest.test(ATestFilepath);
        PTest.test(PTestFilePath);
    }

}
