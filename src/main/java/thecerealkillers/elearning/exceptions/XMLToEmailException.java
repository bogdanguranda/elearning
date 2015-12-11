package thecerealkillers.elearning.exceptions;


/**
 * Created by Dani
 */
public class XMLToEmailException extends Exception {

    public static final String FAILED_TO_EMAIL = "An error occurred while converting from string array to mail.\n";
    public static final String FAILED_FOOTER_READ = "An error occurred while reading footer data.\n";
    public static final String FAILED_XML_READ = "An error occurred while reading XML file.\n";
    public static final String FAILED_GET_BY_ATTR = "An error occurred while trying to retrieve a email by attribute.\n";
    public static final String FAILED_TO_ARRAY = "An error occurred while converting from XML to string array.\n";

    public XMLToEmailException(String message) {
        super(message);
    }
}
