/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dependencies.material_components.utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;

/**
 * @author felipe
 */
public class JSONUtils {

    public static HashMap toMap(JSONObject object) throws JSONException {
        HashMap<String, Object> map = new HashMap<>();

        Iterator<String> keysItr = object.keys();
        while (keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = object.get(key);
            if(value instanceof JSONObject){
                value=toMap((JSONObject) value);
            }
            map.put(key, value);
        }
        return map;
    }

    public static ArrayMap toArrayMap(JSONObject object) throws JSONException {
        ArrayMap arrayMap = new ArrayMap();
        Iterator<String> keysItr = object.keys();
        while (keysItr.hasNext()) {
            String key = keysItr.next();
            Object valueObject = object.get(key);
            String value = (valueObject instanceof String ? (String) valueObject : String.valueOf(valueObject));
            arrayMap.put(key,value.equalsIgnoreCase("null")?"":value);
        }
        return arrayMap;
    }

}
