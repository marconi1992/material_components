package dependencies.material_components.utils;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;


/**
 * Created by Felipe on 16/08/2015.
 */
public class EditingTableCellFactory implements Callback<TableColumn<Object,String>,TableCell<Object,String>> {

    @Override
    public TableCell<Object, String> call(TableColumn<Object, String> param) {
        return new EditingTableCell();
    }
}
