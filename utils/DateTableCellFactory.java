package dependencies.material_components.utils;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;


/**
 * Created by Felipe on 12/11/2015.
 */
public class DateTableCellFactory implements Callback<TableColumn<Object,String>,TableCell<Object,String>> {

    @Override
    public TableCell<Object, String> call(TableColumn<Object, String> param) {
        return new DateTableCell();
    }
}
