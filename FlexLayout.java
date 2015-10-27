package dependencies.material_components;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

import java.util.Iterator;

/**
 * Created by Felipe on 14/09/2015.
 */
public class FlexLayout extends ScrollPane {

    static private final double LAYOUT_WEIGHT = 12;
    static private final String XM = "xm", SM = "sm", MD = "md", LG = "lg";

    private final FlowPane flowPane = new FlowPane();
    private final ObservableList<Node> container;

    public FlexLayout() {

        container = flowPane.getChildren();


        flowPane.getStyleClass().add("flex-layout");
        flowPane.setPrefWrapLength(0);
        flowPane.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double old = oldValue.doubleValue(), current = newValue.doubleValue();

                if ((old >= 768 && current < 768)) {
                    resizeChildren(XM);
                } else if ((old < 768 && current >= 768) || (old >= 992 && current < 992)) {
                    resizeChildren(SM);
                } else if ((old < 992 && current >= 992) || (old >= 1200 && current < 1200)) {
                    resizeChildren(MD);
                } else if ((old < 1200 && current >= 1200)) {
                    resizeChildren(LG);
                }


            }

        });
        setContent(flowPane);
        flowPane.setPrefWidth(0);
        flowPane.setMinWidth(USE_PREF_SIZE);
        flowPane.prefWidthProperty().bind(this.widthProperty());
        setHbarPolicy(ScrollBarPolicy.NEVER);
    }

    public final ObservableList<Node> getContainer() {
        return container;
    }

    private void resizeChildren(String display) {
        System.out.println(display);
        Iterator<Node> children = flowPane.getChildren().iterator();

        while (children.hasNext()) {
            Pane node = (Pane) children.next();
            Iterator<String> styleClass = node.getStyleClass().iterator();
            while (styleClass.hasNext()) {
                String style = styleClass.next();
                if (style.contains(display)) {
                    double divider = LAYOUT_WEIGHT / Double.parseDouble(style.split("-")[1]);


                    node.prefWidthProperty().bind(new DoubleBinding() {
                        {
                            super.bind(flowPane.widthProperty());
                        }

                        @Override
                        protected double computeValue() {

                            if (flowPane.getWidth() % 2 != 0 && divider % 2 == 0) {

                                return ((flowPane.getWidth() - 1) / divider) - 16;


                            } else {
                                return ((flowPane.getWidth() / divider) - 1) - 16;
                            }

                        }
                    });

                }
            }
        }
    }
}
