package dependencies.material_components;

import javafx.util.StringConverter;

import java.util.HashMap;

/**
 * Created by Felipe on 14/11/2015.
 */
public class HashMapStringConverter extends StringConverter<HashMap> {
    private String key;
    @Override
    public String toString(HashMap object) {
        return (String) object.get(key);
    }

    @Override
    public HashMap fromString(String string) {
        return null;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
