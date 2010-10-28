package edu.ort.discomp.xml;

import edu.ort.discomp.xml.mvc.MvcConfig;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author migueldiab
 */
public class MvcXMLReader {

  private static MvcConfig mvcConfig;

  public static void loadMvcConfig() {
    try {
      JAXBContext jaxbContext = JAXBContext.newInstance("edu.ort.discomp.xml.mvc");
      Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
      File mvcFile = new File("./WEB-INF/mvc.xml");
      JAXBElement<MvcConfig> mvcConfigElement = (JAXBElement<MvcConfig>) unmarshaller.unmarshal(mvcFile);
      mvcConfig = mvcConfigElement.getValue();
    } catch (JAXBException ex) {
      Logger.getLogger(MvcXMLReader.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public static MvcConfig getMvcConfig() {
    return mvcConfig;
  }
  
}
