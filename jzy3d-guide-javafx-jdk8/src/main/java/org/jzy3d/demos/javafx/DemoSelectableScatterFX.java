package org.jzy3d.demos.javafx;

import java.util.Random;
import org.jzy3d.chart.AWTNativeChart;
import org.jzy3d.chart.controllers.mouse.AWTDualModeMouseSelector;
import org.jzy3d.chart.controllers.mouse.selection.AWTScatterMouseSelector;
import org.jzy3d.colors.Color;
import org.jzy3d.javafx.offscreen.JavaFXOffscreenChartFactory;
import org.jzy3d.maths.Coord3d;
import org.jzy3d.plot3d.primitives.selectable.SelectableScatter;
import org.jzy3d.plot3d.rendering.canvas.Quality;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


@SuppressWarnings("restriction")
public class DemoSelectableScatterFX extends Application {
  public static void main(String[] args) {
    Application.launch(args);
  }

  @Override
  public void start(Stage stage) {
    stage.setTitle(DemoSelectableScatterFX.class.getSimpleName());

    // Jzy3d
    JavaFXOffscreenChartFactory factory = new JavaFXOffscreenChartFactory();
    AWTNativeChart chart = getDemoChart(factory);
    Canvas canvas = factory.bindCanvas(chart);

    // JavaFX
    StackPane pane = new StackPane();
    Scene scene = new Scene(pane);
    stage.setScene(scene);
    stage.show();
    pane.getChildren().add(canvas);

    stage.setWidth(500);
    stage.setHeight(500);
  }

  private AWTNativeChart getDemoChart(JavaFXOffscreenChartFactory factory) {
    Quality quality = Quality.Advanced();
    int POINTS = 1000;
    SelectableScatter scatter = generateScatter(POINTS);
    AWTNativeChart chart = (AWTNativeChart) factory.newChart(quality);
    chart.getScene().add(scatter);
    chart.getView().setMaximized(true);

    AWTScatterMouseSelector selector = new AWTScatterMouseSelector(scatter, chart);
    new AWTDualModeMouseSelector(chart, selector);
    return chart;
  }

  protected SelectableScatter generateScatter(int npt) {
    Coord3d[] points = new Coord3d[npt];
    Color[] colors = new Color[npt];
    Random rng = new Random();
    rng.setSeed(0);
    for (int i = 0; i < npt; i++) {
      colors[i] = new Color(0, 64 / 255f, 84 / 255f);
      points[i] = new Coord3d(rng.nextFloat(), rng.nextFloat(), rng.nextFloat());
    }
    SelectableScatter dots = new SelectableScatter(points, colors);
    dots.setWidth(1);
    dots.setHighlightColor(Color.YELLOW);
    return dots;
  }
}
