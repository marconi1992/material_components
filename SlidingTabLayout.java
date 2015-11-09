package dependencies.material_components;

import com.sun.javafx.scene.control.skin.ButtonSkin;
import dependencies.material_components.utils.RippleSkinFactory;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.Skin;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.util.Duration;

import javax.swing.text.View;

/**
 * Created by Felipe on 04/11/2015.
 */
public class SlidingTabLayout extends VBox {
    private final int DEFAULT_HEIGHT_BAR=3;

    private final HBox tabStrip=new HBox();
    private ViewPager viewPager;
    private Line bar=new Line();
    public SlidingTabLayout(){
        getStyleClass().add("sliding-tab-layout");
        bar.setManaged(false);
        bar.getStyleClass().add("bar");
        bar.setStrokeWidth(DEFAULT_HEIGHT_BAR);
        bar.layoutYProperty().bind(heightProperty().subtract(DEFAULT_HEIGHT_BAR-1.5));
        getChildren().add(tabStrip);
        getChildren().add(bar);
    }

    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
        viewPager.setOnPageChangeListener(new PagerListener());
        populateStrip();
    }

    private void populateStrip() {
        PagerAdapter adapter=viewPager.getAdapter();

        for(int i=0;i<adapter.getCount();i++){
            Tab tab= new Tab();
            tab.setText(adapter.getPageTitle(i));
            tab.setOnAction(evt->{
                Tab tabPressed= (Tab) evt.getSource();
                viewPager.setCurrentItem(tabStrip.getChildren().indexOf(tabPressed));
            });
            tabStrip.getChildren().add(tab);
        }

    }

    private void animatingBar(double width, double x){
        Timeline timeline=new Timeline(new KeyFrame(new Duration(200),
                new KeyValue(bar.endXProperty(),width),
                new KeyValue(bar.layoutXProperty(),x)
        ));
        timeline.play();
    }

    private class Tab extends Button {

        @Override
        protected Skin<?> createDefaultSkin() {
            SkinBase skin= new ButtonSkin(this);
            RippleSkinFactory.getRippleEffect(skin, this);
            return super.createDefaultSkin();
        }

    }

    private class PagerListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageSelected(int position) {
            Tab tab = (Tab) tabStrip.getChildren().get(position);
            if(tab.getWidth()>0) {
                animatingBar(tab.getWidth(), getPadding().getLeft() + tab.getLayoutX());
            }else{
                tab.widthProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                        if(newValue.intValue()>0){
                            animatingBar(tab.getWidth(), getPadding().getLeft() + tab.getLayoutX());
                            tab.widthProperty().removeListener(this);
                        }
                    }
                });
            }
        }
    }
}
