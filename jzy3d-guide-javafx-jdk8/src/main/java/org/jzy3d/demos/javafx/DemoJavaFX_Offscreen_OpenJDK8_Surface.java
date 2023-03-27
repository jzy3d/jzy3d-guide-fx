package org.jzy3d.demos.javafx;

import org.jzy3d.chart.AWTNativeChart;
import org.jzy3d.colors.Color;
import org.jzy3d.colors.ColorMapper;
import org.jzy3d.colors.colormaps.ColorMapRainbow;
import org.jzy3d.javafx.controllers.mouse.JavaFXCameraMouseController;
import org.jzy3d.javafx.offscreen.JavaFXOffscreenChartFactory;
import org.jzy3d.javafx.offscreen.JavaFXOffscreenRenderer3d;
import org.jzy3d.maths.Range;
import org.jzy3d.plot3d.builder.Mapper;
import org.jzy3d.plot3d.builder.SurfaceBuilder;
import org.jzy3d.plot3d.primitives.Shape;
import org.jzy3d.plot3d.rendering.canvas.Quality;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Showing how to pipe an offscreen Jzy3d chart image to a JavaFX ImageView.
 * 
 * {@link JavaFXOffscreenChartFactory} delivers dedicated {@link JavaFXCameraMouseController} and
 * {@link JavaFXOffscreenRenderer3d}
 * 
 * Will run on OpenJDK8, require jxfrt.jar to be available
 * 
 * @author Martin Pernollet
 */
@SuppressWarnings("restriction")
public class DemoJavaFX_Offscreen_OpenJDK8_Surface extends Application {
  public static Application app;
  
  public static void main(String[] args) {
    Application.launch(args);
  }

  @Override
  public void start(Stage stage) {
    app = this;
    
    stage.setTitle(DemoJavaFX_Offscreen_OpenJDK8_Surface.class.getSimpleName());

    // Jzy3d
    JavaFXOffscreenChartFactory factory = new JavaFXOffscreenChartFactory();
    AWTNativeChart chart = getDemoChart(factory);
    ImageView imageView = factory.bindImageView(chart);

    // JavaFX
    StackPane pane = new StackPane();
    Scene scene = new Scene(pane);
    stage.setScene(scene);
    pane.getChildren().add(imageView);
    stage.show();

    factory.addSceneSizeChangedListener(chart, scene);

    stage.setWidth(500);
    stage.setHeight(500);
  }

  private AWTNativeChart getDemoChart(JavaFXOffscreenChartFactory factory) {
    // -------------------------------
    // Define a function to plot
    Mapper mapper = new Mapper() {
      @Override
      public double f(double x, double y) {
        return x * Math.sin(x * y);
      }
    };

    // Define range and precision for the function to plot
    Range range = new Range(-3, 3);
    int steps = 80;

    // Create the object to represent the function over the given range.
    final Shape surface = new SurfaceBuilder().orthonormal(mapper, range, steps);
    surface.setColorMapper(new ColorMapper(new ColorMapRainbow(), surface, new Color(1, 1, 1, .75f)));
    surface.setFaceDisplayed(true);
    surface.setWireframeDisplayed(false);

    // -------------------------------
    // Create a chart
    Quality quality = Quality.Advanced();

    // let factory bind mouse and keyboard controllers to JavaFX node
    factory.getPainterFactory().setOffscreen(800, 600);
    AWTNativeChart chart = (AWTNativeChart) factory.newChart(quality);
    chart.getScene().getGraph().add(surface);
    return chart;
  }
}
