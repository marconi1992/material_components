package dependencies.material_components.utils;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by felipe on 12/09/15.
 */
public class ArrayMapCellFactory implements Callback<TableColumn.CellDataFeatures<ArrayMap, ?>, ObservableValue<?>> {
    private String key;
    private ArrayList<String> keys = new ArrayList<>();
    private String pattern;

    @Override
    public ObservableValue<?> call(TableColumn.CellDataFeatures<ArrayMap, ?> param) {
        String value = "";
        String patternTemp = pattern;
        if (keys.size() == 0) {
            value = getContent(param.getValue(),key);

        } else {
            if (patternTemp != null) {
                for (String key : keys) {
                    patternTemp = patternTemp.replaceFirst("\\?",getContent(param.getValue(),key));
                }
                value = patternTemp;
            } else {
                for (int i = 0; i < keys.size(); i++) {
                    value += getContent(param.getValue(),keys.get(i)) + (i == keys.size() - 1 ? "" : " ");
                }
            }
        }
        return new ReadOnlyStringWrapper(value);
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public ArrayList<String> getKeys() {
        return this.keys;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    private String getContent(ArrayMap item, String key) {
        String value="";
        ArrayMap arrayMap = item;
        if (key.contains(".")) {
            String keysSplit[] = key.split("\\.");
            for (int i = 0; i < keysSplit.length; i++) {
                value = arrayMap.get(keysSplit[i]);
                if ((arrayMap = isArrayMap(value)) == null) {
                  return value;
                }
            }
            return value;
        } else {
            return item.get(key);
        }
    }

    private ArrayMap isArrayMap(String data) {
        try {
            JSONObject json = new JSONObject(data);
            return JSONUtils.toArrayMap(json);
        } catch (JSONException e) {
            return null;
        }
    }
}
