package dependencies.material_components;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
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
    private final SimpleBooleanProperty isXm = new SimpleBooleanProperty();
    private final SimpleBooleanProperty isSm = new SimpleBooleanProperty();
    private final SimpleBooleanProperty isMd = new SimpleBooleanProperty();
    private final SimpleBooleanProperty isLg = new SimpleBooleanProperty();
    private final FlowPane flowPane = new FlowPane();
    private final ObservableList<Node> container;

    public FlexLayout() {

        container = flowPane.getChildren();
        flowPane.setPadding(new Insets(16,0,0,16));

        isXm.bind(flowPane.widthProperty().lessThanOrEqualTo(768));
        isSm.bind(flowPane.widthProperty().greaterThan(768).and(flowPane.widthProperty().lessThanOrEqualTo(992)));
        isMd.bind(flowPane.widthProperty().greaterThan(992).and(flowPane.widthProperty().lessThanOrEqualTo(1200)));
        isLg.bind(flowPane.widthProperty().greaterThan(1200));

        isXm.addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                resizeChildren(XM);
            }
        });
        isSm.addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                resizeChildren(SM);
            }
        });
        isMd.addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                resizeChildren(MD);
            }
        });
        isLg.addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                resizeChildren(LG);
            }
        });
        flowPane.getStyleClass().add("flex-layout");
        flowPane.setPrefWrapLength(0);

        setContent(flowPane);
        flowPane.setPrefWidth(0);
        flowPane.setMinWidth(USE_PREF_SIZE);
        flowPane.prefWidthProperty().bind(this.widthProperty());
        setHbarPolicy(ScrollBarPolicy.NEVER);
    }

    public final ObservableList<Node> getContainer() {
        return container;
    }

    public FlowPane getFlowPane() {
        return flowPane;
    }

    private void resizeChildren(String display) {

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
