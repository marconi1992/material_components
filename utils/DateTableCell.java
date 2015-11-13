/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dependencies.material_components.utils;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.DatePicker;

import java.time.LocalDate;

/**
 * @author Felipe
 */
public class DateTableCell extends RippleTableCell<Object, String> {

    private DatePicker datePicker;


    @Override
    public void startEdit() {

        if (!isEmpty()) {
            if (getTableRow().isEditable()) {
                super.startEdit();
                createDatePicker(getItem());
                setText(null);
                setGraphic(datePicker);
            }

        }
    }

    @Override
    public void cancelEdit() {
        if (!isEmpty()) {
            super.cancelEdit();
            setText(getItem());
            setGraphic(null);
            commitEdit(getItem());

        }

    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {

            if (isEditing()) {
                createDatePicker(item);
                setText(null);
                setGraphic(datePicker);
            } else {
                setText(item);
                setGraphic(null);
            }

        }

    }


    private void createDatePicker(String item) {
        if (datePicker == null) {
            datePicker = new DatePicker();
            String[] dateArray = item.split("-");
            datePicker.setValue(LocalDate.of(Integer.parseInt(dateArray[0]), Integer.parseInt(dateArray[1]), Integer.parseInt(dateArray[2])));
            datePicker.valueProperty().addListener(new ChangeListener<LocalDate>() {

                @Override
                public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                    commitEdit(newValue.toString());
                }
            });
        }

    }

}
