package dependencies.material_components;

import dependencies.material_components.utils.RippleSkinFactory;
import dependencies.material_components.utils.SVGFactory;
import javafx.event.ActionEvent;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.Skin;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by Felipe on 07/11/2015.
 */
public class FloatingActionButton extends ButtonBase {
    private SVGPath icon;
    private static final int DEFAULT_SIZE=56;
    private final Circle base=new Circle(DEFAULT_SIZE/2,DEFAULT_SIZE/2,DEFAULT_SIZE/2);
    public FloatingActionButton(){

        setPrefSize(DEFAULT_SIZE, DEFAULT_SIZE);
        setMinSize(DEFAULT_SIZE, DEFAULT_SIZE);
        setMaxSize(DEFAULT_SIZE, DEFAULT_SIZE);
        getStyleClass().add("floating-button");
        setShape(base);


        addEventFilter(MouseEvent.MOUSE_CLICKED, (MouseEvent evt) -> {
            if (onActionProperty().get() != null && evt.getButton().equals(MouseButton.PRIMARY)) {
                onActionProperty().get().handle(new ActionEvent(this, null));
            }
        });
    }
    @Override
    public void fire() {

    }

    public void setIcon(String path) {
        URL url = null;
        try {
            url = new URL(path);
            File file = new File(url.getFile());
            icon = SVGFactory.createSVG(file);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public String getIcon() {
        return icon.getContent();
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        IconButtonSkin skin=new IconButtonSkin(this);
        skin.setIcon(icon);
        RippleSkinFactory.getRippleEffect(skin,this,null);
        return skin;
    }
}
