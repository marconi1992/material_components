package dependencies.material_components.utils;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;


/**
 * Created by Felipe on 16/08/2015.
 */
class EditingTableCell<S,T> extends RippleTableCell<S, T> {

    private TextField textField;

    public EditingTableCell(){
        super();
        getStyleClass().add("editing-table-cell");
    }

    @Override
    public void startEdit() {
        if (!isEmpty()) {
            super.startEdit();
            createTextField(getItem());
            setText(null);
            setGraphic(textField);
            textField.selectAll();
        }
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();

        setText((String) getItem());
        setGraphic(null);
    }

    @Override
    public void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            if (isEditing()) {
                createTextField(item);
                setText(null);
                setGraphic(textField);
            } else {
                setText((String) item);
                setGraphic(null);
            }
        }
    }

    private void createTextField(T item) {
        if (textField == null) {
            textField = new TextField();
        }
        textField.setText((String) item);
        textField.selectAll();
        textField.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(event.getCode().ENTER)) {
                    commitEdit((T) textField.getText());
                }
            }
        });
    }
}
