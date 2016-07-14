package com.example.rssfeeder.repository.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by Андрей on 11.07.2016.
 */
public class RssItemDeserializer implements JsonDeserializer<RssItem>
{

    @Override
    public RssItem deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
    {
        final RssItem rssItem = new RssItem();
        final JsonObject jsonObject = json.getAsJsonObject();

        rssItem.setTitle(jsonObject.get("title").getAsString());
        rssItem.setDetail(jsonObject.get("text").getAsString());

        return rssItem;
    }
}
