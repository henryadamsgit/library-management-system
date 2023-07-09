package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class JSONFileHandler<T> {
    private final String fileName;
    private final Gson gson;
    private final Type objectType;

    public JSONFileHandler(String fileName, Class<T> objectType) {
        this.fileName = fileName;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.objectType = TypeToken.getParameterized(List.class, objectType).getType();
    }

    public void addObject(T object) throws IOException {
        List<T> objects = getObjects();
        objects.add(object);
        saveObjects(objects);
    }

    public List<T> getObjects() throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (Reader reader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8)) {
            List<T> objects = gson.fromJson(reader, objectType);
            return objects != null ? objects : (List<T>) new ArrayList<>();
        }
    }

    public void saveObjects(List<T> objects) throws IOException {
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(fileName), StandardCharsets.UTF_8)) {
            gson.toJson(objects, objectType, writer);
        }
    }
}
