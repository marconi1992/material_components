package dependencies.material_components.utils;

import dependencies.activities.Activity;
import dependencies.material_components.DrawerLayout;
import dependencies.material_components.Toolbar;
import javafx.scene.Node;

/**
 * Created by Felipe on 03/11/2015.
 */
public class ActionBarDrawerToggle implements DrawerLayout.DrawerListener{

    public ActionBarDrawerToggle(Activity activity,DrawerLayout drawer, Toolbar toolbar){
           toolbar.setOnHomeButtonAction(evt->{
               if(drawer.isDrawerOpened()){
                   drawer.closeDrawer();
               }else{
                   drawer.openDrawer();
               }

           });
    }
    @Override
    public void onDrawerClosed(Node node) {

    }

    @Override
    public void onDrawerOpened(Node node) {

    }
}
