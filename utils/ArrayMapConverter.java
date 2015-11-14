package dependencies.material_components.utils;

import javafx.util.StringConverter;

import java.util.ArrayList;

/**
 * Created by Felipe on 15/09/2015.
 */
public class ArrayMapConverter<S> extends StringConverter<S> {
    private String key = "";
    private String pattern = "";
    private ArrayList<String> keys = new ArrayList<>();

    @Override
    public String toString(Object object) {
        if (object instanceof String) {
            return (String) object;
        } else {
            ArrayMap array = ((ArrayMap) object);
            String pattern = this.pattern;
            if (key.isEmpty()) {
                if (keys != null) {
                    for (String key : keys) {
                        if (this.pattern.isEmpty()) {
                            pattern += keys.indexOf(key) == keys.size() - 1 ? array.get(key) : (array.get(key) + " ");
                        } else {
                            pattern = pattern.replaceFirst("\\?", array.get(key));
                        }
                    }
                    return pattern;
                } else {
                    return object.toString();
                }

            } else {
                return array.get(key);
            }
        }
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public ArrayList getKeys() {
        return keys;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public S fromString(String string) {
        return null;
    }
}
