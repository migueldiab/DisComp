package edu.ort.discomp.xml;

import edu.ort.discomp.xml.jaxb.MvcConfig;
import java.io.File;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author migueldiab
 */
public class MvcXMLReader {

  private static MvcConfig mvcConfig;

  public static MvcConfig getMvcConfig() {
    return mvcConfig;
  }

  public static void loadMvcConfig(InputStream is) {
    try {
      JAXBContext jc = JAXBContext.newInstance("edu.ort.discomp.xml.jaxb");
      Unmarshaller unmarshaller = jc.createUnmarshaller();
      mvcConfig = (MvcConfig) unmarshaller.unmarshal(is);
    } catch (JAXBException ex) {
      Logger.getLogger(MvcXMLReader.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public static void loadMvcConfig(File myFile) {
    try {
      JAXBContext jc = JAXBContext.newInstance("edu.ort.discomp.xml.jaxb");
      Unmarshaller unmarshaller = jc.createUnmarshaller();
      mvcConfig = (MvcConfig) unmarshaller.unmarshal(myFile);
    } catch (JAXBException ex) {
      Logger.getLogger(MvcXMLReader.class.getName()).log(Level.SEVERE, null, ex);
    }

  }
 
}
