package com.khramtsov.serialize;

import com.sun.istack.internal.NotNull;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class JsonSerializer {
    public static void serialize(
        @NotNull Object object, @NotNull String path) {

        try (OutputStream fos = new FileOutputStream(path)) {
            ObjectMapper mapper = new ObjectMapper();

            mapper.configure(SerializationFeature
                .INDENT_OUTPUT, Boolean.TRUE);

            mapper.writeValue(fos, object);
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public static Object deserialize(
        @NotNull Object object, @NotNull String path) {

        try (InputStream fis = new FileInputStream(path)) {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(fis, object.getClass());
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        } return null;
    }
}