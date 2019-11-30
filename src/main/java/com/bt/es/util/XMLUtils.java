package com.bt.es.util;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class XMLUtils {

	public static <T> String fromClassToXMLString(T t, Class<T> clazz) throws JAXBException {
		//Create JAXB Context
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
         
        //Create Marshaller
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        //Required formatting??
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        //Print XML String to Console
        StringWriter sw = new StringWriter();
        jaxbMarshaller.marshal(t, sw);
        
		return sw.toString();
	}
	
	
	
	public static <T> T fromXMLStringToClass(String content, Class<T> clazz) throws JAXBException {
		JAXBContext jaxbContext;
        jaxbContext = JAXBContext.newInstance(clazz);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        StringReader sr = new StringReader(content);
        
		return (T) jaxbUnmarshaller.unmarshal(sr);
	}
	
}
