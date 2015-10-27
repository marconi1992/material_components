package dependencies.material_components.utils;

import dependencies.material_components.IconButton;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;



/**
 * Created by Felipe on 25/10/2015.
 */
public class Toolbar extends VBox {
    private final HBox topPlace=new HBox();
    private final Label title=new Label("UPOLU");
    public Toolbar(){
        getStyleClass().add("toolbar");
        setAlignment(Pos.CENTER_LEFT);
        topPlace.setAlignment(Pos.CENTER_LEFT);
        setPrefHeight(56);
        IconButton icon=new IconButton();
        icon.setIcon(getClass().getResource("icons/menu.svg").toString());
        icon.setIconBackground(getClass().getResource("icons/arrow_back.svg").toString());
        topPlace.getChildren().addAll(icon, title);
        getChildren().add(topPlace);
    }
}
