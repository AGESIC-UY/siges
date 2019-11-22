/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sofis.entities.utils;

import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author bruno
 */
public class JAXBUtils {

	public static Object unmarshal(final String xml, Class clazz) throws JAXBException {
		return JAXBContext.newInstance(clazz)
			.createUnmarshaller()
			.unmarshal(new StringReader(xml));
	}

	public static String marshal(Object o, Class clazz) throws JAXBException {
		final Marshaller m = JAXBContext.newInstance(clazz)
			.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		final StringWriter w = new StringWriter();
		m.marshal(o, w);
		return w.toString();
	}

}
