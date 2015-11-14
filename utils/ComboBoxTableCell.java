package dependencies.material_components.utils;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;

/**
 * Created by Felipe on 13/11/2015.
 */
public class ComboBoxTableCell<S,T> extends RippleTableCell<S,T>{
    private ObjectProperty<ComboBox<S>> comboBox=new SimpleObjectProperty<>();


    @Override
    public void startEdit() {
        super.startEdit();
        if(!isEmpty()){
            setGraphic(comboBox.getValue());
            setText(null);
            createComboBox();
        }

    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        setGraphic(null);
        setText((String) getItem());
    }

    @Override
    protected void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);
        if(!empty){
            if(isEditing()) {
                createComboBox();
                setGraphic(comboBox.getValue());
                setText(null);
            }else{
                setText((String) item);
                setGraphic(null);
            }
        }else{
            setText(null);
            setGraphic(null);
        }
    }

    private void createComboBox(){

        comboBox.getValue().setValue((S) getItem());
        comboBox.getValue().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<S>() {
            @Override
            public void changed(ObservableValue<? extends S> observable, S oldValue, S newValue) {
                if(comboBox.getValue().getConverter()!=null) {
                    commitEdit((T) comboBox.getValue().getConverter().toString(newValue));
                }
                comboBox.getValue().getSelectionModel().selectedItemProperty().removeListener(this);
            }
        });
    }

    public ObjectProperty<ComboBox<S>> comboBoxProperty() {
        return comboBox;
    }
}
