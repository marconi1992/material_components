package dependencies.material_components.utils;

import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import javafx.util.StringConverter;


/**
 * Created by Felipe on 13/11/2015.
 */
public class ComboBoxTableCellFactory implements Callback<TableColumn<Object,String>,TableCell<Object,String>> {
    private ObjectProperty<ComboBox> comboBox=new SimpleObjectProperty();
    @Override
    public TableCell<Object, String> call(TableColumn<Object, String> param) {
        ComboBoxTableCell comboBoxTableCell=new ComboBoxTableCell();
        comboBoxTableCell.comboBoxProperty().bind(comboBox);
        return comboBoxTableCell ;
    }

    public ComboBox getComboBox() {
        return comboBox.get();
    }

    public void setComboBox(ComboBox comboBox) {
        this.comboBox.set(comboBox);
    }
}
