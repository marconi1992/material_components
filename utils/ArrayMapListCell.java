package dependencies.material_components.utils;

import com.sun.javafx.scene.control.skin.ListCellSkin;
import dependencies.material_components.utils.ArrayMap;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.ListCell;
import javafx.scene.control.Skin;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Felipe on 27/11/2015.
 */
public class ArrayMapListCell extends ListCell<ArrayMap> {

    private SimpleObjectProperty<ArrayList<String>> keys=new SimpleObjectProperty<>();
    private SimpleStringProperty key=new SimpleStringProperty();
    @Override
    protected void updateItem(ArrayMap item, boolean empty) {
        super.updateItem(item, empty);
        if(!empty){
            setText(getContent(item));
        }else{
            setText(null);
            setGraphic(null);
        }
    }

    public void setKey(String key) {
        this.key.set(key);
    }

    public void setKeys(ArrayList<String> keys) {
        this.keys.set(keys);
    }

    private String getContent(ArrayMap item){
        String data="";
        for(int j=0;j<keys.getValue().size();j++){
            String key=keys.getValue().get(j);
            ArrayMap arrayMap=item;
            if(key.contains(".")){
                String keysSplit[]=key.split("\\.");
                for(int i=0;i<keysSplit.length;i++){
                    String value= arrayMap.get(keysSplit[i]);
                    if((arrayMap=isArrayMap(value))==null){
                        data+=value+(j+1==keys.getValue().size()?"":" ");
                    }
                }
            }else{
                String value= item.get(key);
                data+=value+(j+1==keys.getValue().size()?"":" ");
            }
        }
        return  data;
    }

    private ArrayMap isArrayMap(String data){
        try {
            JSONObject json=new JSONObject(data);
            return JSONUtils.toArrayMap(json);
        } catch (JSONException e) {
            return null;
        }
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        ListCellSkin skin=new ListCellSkin(this);
        RippleSkinFactory.getRippleEffect(skin,this);
        return skin;
    }
}
