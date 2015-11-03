package dependencies.material_components;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * Created by Felipe on 02/11/2015.
 */
public class DrawerLayout extends AnchorPane {
    private Pane content, nav;
    private final SimpleBooleanProperty tableScreen = new SimpleBooleanProperty();
    private static final double DEFAULT_WIDTH_NAV = 256;

    public DrawerLayout() {

        tableScreen.bind(widthProperty().lessThan(800));
        tableScreen.addListener((observable, oldValue, newValue) -> {
            responsiveBehavior(newValue);
        });

    }

    private void responsiveBehavior(boolean tabletScreen) {
        if (content != null && nav != null) {
            if(tabletScreen){
                AnchorPane.setLeftAnchor(content,0d);
                nav.setTranslateX(-DEFAULT_WIDTH_NAV);
            }else{
                AnchorPane.setLeftAnchor(content,DEFAULT_WIDTH_NAV);
                nav.setTranslateX(0);
            }
        }
    }

    public Pane getContent() {
        return content;
    }

    public void setContent(Pane content) {
        this.content = content;
        AnchorPane.setTopAnchor(content, 0d);
        AnchorPane.setRightAnchor(content, 0d);
        AnchorPane.setBottomAnchor(content, 0d);
        AnchorPane.setLeftAnchor(content, DEFAULT_WIDTH_NAV);
        getChildren().add(content);

    }

    public Pane getNav() {
        return nav;
    }

    public void setNav(Pane nav) {
        this.nav = nav;
        nav.setPrefWidth(DEFAULT_WIDTH_NAV);
        AnchorPane.setTopAnchor(nav, 0d);
        AnchorPane.setBottomAnchor(nav, 0d);
        getChildren().add(nav);
    }
}
