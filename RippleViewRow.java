package dependencies.material_components;

import com.sun.javafx.scene.control.skin.ListCellSkin;
import dependencies.material_components.RecyclerView;
import dependencies.material_components.utils.RippleSkinFactory;
import javafx.scene.control.Skin;

/**
 * Created by Felipe on 03/11/2015.
 */
public class RippleViewRow extends RecyclerView.ViewRow {

    public RippleViewRow(RecyclerView.Adapter adapter) {
        super(adapter);
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        ListCellSkin listCellSkin=new ListCellSkin(this);
        RippleSkinFactory.getRippleEffect(listCellSkin, this);
        return listCellSkin;
    }
}
