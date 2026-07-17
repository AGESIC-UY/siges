/*
 * 
 * 
 */
package com.sofis.generico.utils.xml;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author Usuario
 */
public class XMLUtils {

	public static Object saveObjectState(Object toSave) {
		ByteArrayInputStream in = null;
		ByteArrayOutputStream outStre = null;

		try {
			//JAXBContext jc = JAXBContext.newInstance(toSave.getClass().getPackage().getName());
			JAXBContext jc = JAXBContext.newInstance(toSave.getClass());

			//Crear clasificador
			Marshaller m = jc.createMarshaller();
			//Clasficar objeto en archivo.
			outStre = new ByteArrayOutputStream();
			//m.marshal(new JAXBElement(new QName("http://services.business.sofisform.sofis.com",""), toSave.getClass(), toSave), outStre);
			m.marshal(toSave, outStre);
			m.marshal(toSave, System.out);
			//Crear desclasificador
			in = new ByteArrayInputStream(outStre.toByteArray());
			Unmarshaller um = jc.createUnmarshaller();
			JAXBElement el = um.unmarshal(new StreamSource(in), toSave.getClass());
			Object saveState = el.getValue();
			return saveState;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException ex) {
					Logger.getLogger(XMLUtils.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
			if (outStre != null) {
				try {
					outStre.close();
				} catch (IOException ex) {
					Logger.getLogger(XMLUtils.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
	}

}
