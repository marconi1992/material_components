package dependencies.material_components.utils;

import javafx.event.Event;
import javafx.scene.control.*;


/**
 * Created by Felipe on 15/11/2015.
 */
public class CheckBoxTableCell<S> extends TableCell<S, String> {
    private CheckBox checkBox;

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (!empty) {
            if (checkBox == null) {
                this.getStyleClass().add("check-box-table-cell");
                checkBox = new CheckBox();
            }
            checkBox.setSelected(Boolean.valueOf(item));
            createCheckBox();
            setGraphic(checkBox);
            setText(null);
        } else {
            setGraphic(null);
            setText(null);
        }

    }

    private void createCheckBox() {

        checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue != null) {
                TableView table = getTableView();

                TableColumn.CellEditEvent editEvent = new TableColumn.CellEditEvent(
                        table,
                        new TablePosition(getTableView(),getIndexRow(),getTableColumn()),
                        TableColumn.editCommitEvent(),
                        newValue
                );
                TableColumn column = getTableColumn();
                Event.fireEvent(column, editEvent);
            }
        });
    }

    private int getIndexRow(){
        int i=0;
        for(S item:getTableView().getItems()){
            if(item.equals(getTableRow().getItem())){
               return i;
            }
            i++;
        }
        return -1;

    }


}
