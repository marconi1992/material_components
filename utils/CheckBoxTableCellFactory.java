package dependencies.material_components.utils;

import javafx.beans.property.ObjectProperty;
import javafx.event.EventHandler;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;


/**
 * Created by Felipe on 15/11/2015.
 */
public class CheckBoxTableCellFactory implements Callback<TableColumn<Object,String>,TableCell<Object,String>> {

    @Override
    public TableCell<Object, String> call(TableColumn<Object, String> param) {
        return new CheckBoxTableCell<>();
    }

}
