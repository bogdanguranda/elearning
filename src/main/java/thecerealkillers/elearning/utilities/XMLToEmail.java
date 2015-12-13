package thecerealkillers.elearning.utilities;


import thecerealkillers.elearning.exceptions.XMLToEmailException;
import thecerealkillers.elearning.model.Email;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.io.File;


/**
 * Created by Dani
 */
public class XMLToEmail {

    private static String MAIL_INPUT_LOCATION = "/emailData.xvm";
    private static String LINK_STYLE = "style=\"text-decoration:none\"";
    private static String INSERT_TAG_NAME = "emailInsert";
    private static String FIELD_LIST_SEPARATOR = ",";
    private static String FIELD_LIST_NAME = "fields";



    ///=========================================Public methods======================================================

    /**
     * Calls all the required methods to read and convert from XML to Email, setting subject and the email body for
     * the returned email. If the email format requires also will add user name and the url in the places determined
     * by the XML tags (greeting and linkText)
     *
     * @param mailSubject = email scope
     * @param name        = name of the user that will receive the email (optional depending on email scope)
     * @param url         = url that user has to access (optional depending on email scope)
     * @return email that has the subject and email body set accordingly with the data stored in the XML file and the
     * name and url parameters
     * @throws XMLToEmailException
     */
    public static Email getMailForSubject(String mailSubject, String name, String url, List<String> elementsToInsert) throws XMLToEmailException {
        try {
            NodeList nList = getElementsByTag("email");
            Element elem = getTagElementByAttr(nList, "purpose", mailSubject);
            List<String> emailLines = convertElementToStrArray(elem, name, url, elementsToInsert);
            Email email = new Email();
            String str = "";

            email.setSubject(emailLines.get(0));

            for (int temp = 1; temp < emailLines.size(); temp++) {
                str += emailLines.get(temp);
            }

            str += getFooter();
            email.setMessage(str);

            return email;
        } catch (Exception ex) {
            throw new XMLToEmailException(XMLToEmailException.FAILED_TO_EMAIL + " :  " + ex.getMessage());
        }
    }



    ///========================================Private methods======================================================

    /**
     * Retrieves data that will be displayed in the footer of the email
     *
     * @return email footer
     * @throws XMLToEmailException
     */
    private static String getFooter() throws XMLToEmailException {
        try {
            NodeList nList = getElementsByTag("footer");
            Element elem = getTagElementByAttr(nList, "", "");
            List<String> footerLines = convertElementToStrArray(elem, "", "", new ArrayList<>());
            String rez = "";

            for (String str : footerLines) {
                rez += str;
            }

            return rez;
        } catch (Exception ex) {
            throw new XMLToEmailException(XMLToEmailException.FAILED_FOOTER_READ + " :  " + ex.getMessage());
        }
    }

    /**
     * Reads the file specified by MAIL_INPUT_LOCATION and retrieves all elements that have the tag name specified
     * by tagName
     *
     * @throws XMLToEmailException
     */
    private static NodeList getElementsByTag(String tagName) throws XMLToEmailException {
        try {
            URL url = XMLToEmail.class.getResource(MAIL_INPUT_LOCATION);
            File inputFile = new File(url.toURI());
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            return doc.getElementsByTagName(tagName);
        } catch (Exception ex) {
            throw new XMLToEmailException(XMLToEmailException.FAILED_XML_READ + " :  " + ex.getMessage());
        }
    }

    /**
     * Retrieves the element that has the attribute name and attribute value specified byt the parameters attributeName
     * and attributeValue
     *
     * @throws XMLToEmailException
     */
    private static Element getTagElementByAttr(NodeList nList, String attributeName, String attributeValue) throws XMLToEmailException {
        try {
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    if (eElement.getAttribute(attributeName).compareTo(attributeValue) == 0
                            || attributeName.compareTo("") == 0
                            || attributeValue.compareTo("") == 0) {
                        return eElement;
                    }
                }
            }
            return null;
        } catch (Exception ex) {
            throw new XMLToEmailException(XMLToEmailException.FAILED_GET_BY_ATTR + " :  " + ex.getMessage());
        }
    }

    /**
     * Converts an email from XML format to a list of String elements. Each String element stores all
     * data stored by a type of attribute. All tags that are part of a email format must be saved with the tag
     * names specified by the method variable FIELD_LIST_NAME separated by FIELD_LIST_SEPARATOR.
     *
     * @param eElement         = email data in xml format
     * @param name             = name of the user that will receive the email to put it after greeting
     * @param url              = url that the user has to access in order to confirm a critical action
     * @param elementsToInsert = elements the will be inserted, order is important
     * @return List <String> that contains all email lines grouped by position in email
     * @throws XMLToEmailException
     */
    private static List<String> convertElementToStrArray(Element eElement, String name, String url, List<String> elementsToInsert) throws XMLToEmailException {
        try {
            List<String> rez = new ArrayList<>();
            int indexElement = 0;

            for (String eComponent : eElement.getElementsByTagName(FIELD_LIST_NAME).item(0).getTextContent().split(FIELD_LIST_SEPARATOR)) {
                boolean ignore = false;

                if (eComponent.length() >= INSERT_TAG_NAME.length()) {
                    if (eComponent.substring(0, INSERT_TAG_NAME.length()).compareTo(INSERT_TAG_NAME) == 0) {
                        ignore = true;
                        rez.add(elementsToInsert.get(indexElement));
                        indexElement++;
                    }
                }

                if (!ignore) {
                    String tmp = "";

                    if (eComponent.compareTo("linkText") == 0)
                        tmp = "<a href=\"" + url + "\"" + LINK_STYLE + ">";

                    for (int temp = 0; temp < eElement.getElementsByTagName(eComponent).getLength(); temp++) {
                        tmp += eElement.getElementsByTagName(eComponent).item(temp).getTextContent();
                    }

                    if (eComponent.compareTo("greeting") == 0)
                        tmp += " " + name + "!";
                    if (eComponent.compareTo("linkText") == 0)
                        tmp += "</a>";

                    rez.add(tmp);
                }
            }

            return rez;
        } catch (Exception ex) {
            throw new XMLToEmailException(XMLToEmailException.FAILED_TO_ARRAY + " :  " + ex.getMessage());
        }
    }
}
