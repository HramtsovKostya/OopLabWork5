package com.khramtsov.serialize;

import com.sun.istack.internal.NotNull;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class XmlSerializer {
    public static void serialize(
        @NotNull Object object, @NotNull String path) {

        try (OutputStream fos = new FileOutputStream(path)) {
            JAXBContext context = JAXBContext
                .newInstance(object.getClass());
            Marshaller marshaller = context.createMarshaller();

            marshaller.setProperty(Marshaller
                .JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            marshaller.marshal(object, fos);
        } catch (IOException | JAXBException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public static Object deserialize(
        @NotNull Object object, @NotNull String path) {

        try (InputStream fis = new FileInputStream(path)) {
            JAXBContext context = JAXBContext
                .newInstance(object.getClass());
            Unmarshaller unmarshaller = context.createUnmarshaller();

            return unmarshaller.unmarshal(fis);
        } catch (IOException | JAXBException e) {
            System.out.println(e.getLocalizedMessage());
        } return null;
    }
}