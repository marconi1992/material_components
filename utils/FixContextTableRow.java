package dependencies.material_components.utils;


import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.util.Callback;

/**
 * Created by Felipe on 17/08/2015.
 */
public class FixContextTableRow<S> implements Callback<TableView<S>,TableRow<S>> {

    private final ObservableList<MenuItem> contextItems=FXCollections.observableArrayList();
    private final ContextMenu contextMenu=new ContextMenu();


    @Override
    public TableRow<S> call(TableView<S> param) {
        TableRow<S> tableRow=new TableRow<>();
        if(contextMenu.getItems().size()==0) {
            contextMenu.getItems().addAll(contextItems);
        }
        tableRow.contextMenuProperty().bind(Bindings.when(tableRow.itemProperty().isNotNull()).then(contextMenu).otherwise((ContextMenu)null));
        return tableRow;
    }

    public ObservableList<MenuItem> getContextItems() {
        return contextItems;
    }
}
