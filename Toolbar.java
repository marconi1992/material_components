package dependencies.material_components;

import dependencies.activities.ActionBar;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Created by Felipe on 25/10/2015.
 */
public class Toolbar extends VBox implements ActionBar{
    private final HBox topPlace=new HBox();
    private final Label title=new Label();
    private final IconButton home=new IconButton();
    private boolean homeAsUp=false;

    public Toolbar(){
        getStyleClass().add("toolbar");
        setAlignment(Pos.CENTER_LEFT);
        topPlace.setMinHeight(64);
        topPlace.setAlignment(Pos.CENTER_LEFT);
        topPlace.getChildren().add(title);
        topPlace.getStyleClass().add("top-place");
        title.getStyleClass().add("title");

        home.setIcon(getClass().getResource("utils/icons/menu.svg").toString());
        home.setIconBackground(getClass().getResource("utils/icons/arrow_back.svg").toString());

        getChildren().add(topPlace);
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }


    @Override
    public void setDisplayHomeAsUpEnabled(boolean showHomeAsUp) {
        homeAsUp=true;
        home.setIcon(getClass().getResource("utils/icons/arrow_back.svg").toString());
        home.setVisible(false);
        home.setManaged(false);
        if(!topPlace.getChildren().contains(home)){
            topPlace.getChildren().add(0, home);
        }
    }

    @Override
    public void setDisplayShowHomeEnabled(boolean showHome) {
        if(!topPlace.getChildren().contains(home)){
            topPlace.getChildren().add(0,home);
        }

    }

    @Override
    public void showHomeAsUp() {
        home.setVisible(true);
        home.setManaged(true);
    }

    @Override
    public boolean isDisplayHomeAsUpEnabled() {
        return homeAsUp;
    }

    @Override
    public void setOnHomeButtonAction(EventHandler<ActionEvent> handler){
        home.setOnAction(handler);
    }


}
