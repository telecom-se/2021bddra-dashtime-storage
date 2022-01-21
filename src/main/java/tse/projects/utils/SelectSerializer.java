package tse.projects.utils;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SelectSerializer implements JsonSerializer<Object> {
    private final List<String> select;

    public SelectSerializer(List<String> select) {
        super();
        this.select = select;
    }

    @Override
    public JsonElement serialize(Object o, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jObj = (JsonObject) new GsonBuilder().create().toJsonTree(o);

        if (this.select.get(0).equals("*")) return jObj;

        Set<String> keys = new HashSet<>(jObj.keySet());
        for (String column : keys) {
            if (!this.select.contains(column)) {
                jObj.remove(column);
            }
        }
        return jObj;
    }
}
