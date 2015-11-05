package dependencies.material_components;

import dependencies.activities.Fragment;
import dependencies.activities.FragmentManager;
import dependencies.activities.FragmentTransaction;
import javafx.scene.layout.Pane;

/**
 * Created by Felipe on 04/11/2015.
 */
public abstract class FragmentStatePagerAdapter extends PagerAdapter {
    private FragmentManager fragmentManager;
    private int currentItem;
    public FragmentStatePagerAdapter(FragmentManager fragmentManager){
        this.fragmentManager=fragmentManager;
    }
    public abstract Fragment getItem(int position);
    @Override
    public Object instantiateItem(Pane container, int position) {
        if(position<getCount()){
            FragmentTransaction transaction=fragmentManager.beginTransaction();
            transaction.replace(container.getId(),getItem(position));
            transaction.commit();
        }
        return null;
    }
}
