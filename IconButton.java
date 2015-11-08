package dependencies.material_components;

import com.sun.istack.internal.Nullable;
import dependencies.material_components.utils.RippleSkinFactory;
import dependencies.material_components.utils.SVGFactory;
import javafx.event.ActionEvent;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.Skin;
import javafx.scene.control.SkinBase;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.SVGPath;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Felipe on 25/10/2015.
 */
public class IconButton extends ButtonBase {
    private SVGPath icon;
    private SVGPath iconBackground;
    private static final int SIZE = 48;

    public IconButton() {
        setPrefSize(SIZE, SIZE);
        setMinSize(SIZE, SIZE);
        setMaxSize(SIZE, SIZE);

        addEventFilter(MouseEvent.MOUSE_CLICKED,(MouseEvent evt)->{
            if(onActionProperty().get()!=null && evt.getButton().equals(MouseButton.PRIMARY)){
                onActionProperty().get().handle(new ActionEvent(this,null));
            }
        });
    }

    @Override
    public void fire() {

    }

    protected Skin<?> createDefaultSkin() {

        IconButtonSkin skin = new IconButtonSkin(this);
        skin.setIcon(icon);
        skin.setIcon(iconBackground);
        iconBackground.setVisible(false);
        RippleSkinFactory.getRippleEffect(skin, this);
        return skin;

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

    public void setIconBackground(String path) {
        URL url = null;
        try {
            url = new URL(path);
            File file = new File(url.getFile());
            iconBackground = SVGFactory.createSVG(file);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public String getIcon() {
        return icon.getContent();
    }

    public String getIconBackground() {
        return iconBackground.getContent();
    }

    public SVGPath getIconBackgroundSVG() {
        return iconBackground;
    }

    public SVGPath getIconSVG() {
        return icon;
    }


}
