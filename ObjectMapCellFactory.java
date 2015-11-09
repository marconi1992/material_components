package dependencies.material_components;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by Felipe on 08/11/2015.
 */
public class ObjectMapCellFactory<S extends Object> implements Callback<TableColumn.CellDataFeatures<S, ?>, ObservableValue<?>> {

    private String field;
    private ArrayList<String> fields = new ArrayList<>();

    @Override
    public ObservableValue<?> call(TableColumn.CellDataFeatures<S, ?> param) {
        try {
            if (fields.size() > 0) {
                String value="";
                for(String fieldSt:fields){
                    Field field=getField(param.getValue().getClass(),fieldSt);
                    value+=(field.get(param.getValue())+(fieldSt.indexOf(fieldSt)==fields.size()-1?"":" "));
                }
                return new ReadOnlyObjectWrapper(value);
            } else {
                Field field = getField(param.getValue().getClass(),this.field);
                Object value = field.get(param.getValue());
                return new ReadOnlyObjectWrapper(value);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Field getField(Class<?> type,String fieldStr) {
        try {
            Field field = type.getDeclaredField(fieldStr);
            field.setAccessible(true);
            return field;
        } catch (NoSuchFieldException e) {
            return getField(type.getSuperclass(),fieldStr);
        }

    }

    public void setField(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }

    public ArrayList<String> getFields() {
        return fields;
    }
}
