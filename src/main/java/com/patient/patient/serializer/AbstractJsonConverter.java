package com.patient.patient.serializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.time.LocalDateTime;

public abstract class AbstractJsonConverter
{

    private final Gson gson;

    public AbstractJsonConverter()
    {
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTypeAdapter())
                .create();
    }

    protected <T> String serialize(final T entity)
    {
        try
        {
            return gson.toJson(entity);
        }
        catch (Exception ex)
        {
            throw new JsonSyntaxException("Error occured during serialization", ex);
        }
    }

    protected <T> T deserialize(final String jsonString, Class<T> clazz)
    {
        try
        {
            return gson.fromJson(jsonString, clazz);
        }
        catch (JsonSyntaxException ex)
        {
            throw new JsonSyntaxException("Error occured during deserialization", ex);
        }
    }
}