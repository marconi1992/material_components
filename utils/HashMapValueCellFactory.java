package dependencies.material_components.utils;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.util.HashMap;

/**
 * Created by Felipe on 14/11/2015.
 */
public class HashMapValueCellFactory implements Callback<TableColumn.CellDataFeatures<HashMap, ?>, ObservableValue<?>> {
    private StringConverter<HashMap> converter;
    private String key;

    @Override
    public ObservableValue<?> call(TableColumn.CellDataFeatures<HashMap, ?> param) {
        String value = "";
        if (converter != null) {
            if (key.contains(".")) {
                String keys[]=key.split("\\.");
                HashMap data=param.getValue();
                for(int i=0;i<keys.length;i++){
                    data= (HashMap) data.get(keys[i]);
                }
                value = converter.toString(data);
            } else {
                value = converter.toString((HashMap) param.getValue().get(key));
            }
        } else {
            value = param.getValue().get(key).toString();
        }
        return new ReadOnlyStringWrapper(value);
    }

    public StringConverter<HashMap> getConverter() {
        return converter;
    }

    public void setConverter(StringConverter<HashMap> converter) {
        this.converter = converter;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


}
