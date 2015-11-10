package dependencies.material_components.utils;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

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
        if (keys.size() == 0) {
            value = param.getValue().get(this.key);

        } else {
            if (pattern != null) {
                for (String key : keys) {
                    pattern = pattern.replaceFirst("\\?", param.getValue().get(key));
                }
                value = pattern;
            } else {
                for (int i = 0; i < keys.size(); i++) {
                    value += param.getValue().get(keys.get(i)) + (i == keys.size() - 1 ? "" : " ");
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
}
