package dependencies.material_components.utils;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.util.ArrayList;

/**
 * Created by Felipe on 27/11/2015.
 */
public class ArrayMapListCellFactory implements Callback<ListView<ArrayMap>, ListCell<ArrayMap>> {
    private String key;
    private ArrayList<String> keys=new ArrayList<>();
    @Override
    public ListCell<ArrayMap> call(ListView<ArrayMap> param) {
        ArrayMapListCell listCell=new ArrayMapListCell();
        listCell.setKeys(keys);
        return listCell ;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public ArrayList<String> getKeys() {
        return keys;
    }
}
