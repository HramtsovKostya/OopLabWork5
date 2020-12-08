package com.khramtsov.serialize;

import com.sun.istack.internal.NotNull;
import java.io.*;

public class BinarySerializer {
    public static void serialize(
        @NotNull Object object, @NotNull String path) {

        try (OutputStream fos = new FileOutputStream(path)) {
            ObjectOutputStream stream = new ObjectOutputStream(fos);
            stream.writeObject(object);

        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public static Object deserialize(@NotNull String path) {

        try (InputStream fis = new FileInputStream(path)) {
            ObjectInputStream stream = new ObjectInputStream(fis);
            return stream.readObject();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getLocalizedMessage());
        } return null;
    }
}