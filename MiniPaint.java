import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MiniPaint extends Application {
	int red;
	int green;
	int blue;
	int num;

	public static void main(String[] args) {

		launch(args);

	}

	public void start(Stage primaryStage) throws IOException {

		primaryStage.setTitle("Definetly Not Paint");

		ShapeCollection sc = new ShapeCollection(); 

		BorderPane bp = new BorderPane();
		Pane pane = new Pane();

//Tool Text 
		Text toolT = new Text("Tool");
		toolT.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		toolT.setUnderline(true);

//Radio Buttons
		ToggleGroup tg = new ToggleGroup();
		RadioButton draw = new RadioButton("Draw");
		RadioButton move = new RadioButton("Move");
		RadioButton delete = new RadioButton("Delete");

		draw.setToggleGroup(tg);
		move.setToggleGroup(tg);
		delete.setToggleGroup(tg);
//		draw.fire();
//		draw.setSelected(true);
	
//Clearing Page
		Button dAll = new Button("Clear Page");
		
		dAll.setOnAction((ActionEvent event) -> {
			pane.getChildren().clear();
		});
		
////Creating a new Application
//		Button newPage = new Button("Create a 2nd Window");
//		
//		dAll.setOnAction((ActionEvent event) -> {
//			try {
//				ex.start(primaryStage);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		});

//Combo Box
		ComboBox<String> selection = new ComboBox<String>();
		selection.getItems().addAll("Line", "Rectangle", "Ellipse");
		selection.getSelectionModel().selectFirst();

//Text for all the sliders
		Text colorT = new Text("Color");
		colorT.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		colorT.setUnderline(true);

		Text redT = new Text("Red");
		Text greenT = new Text("Green");
		Text blueT = new Text("Blue");
		Text currentT = new Text("Current");
		
		
//Sliders
		Slider redSlide = new Slider(0.0, 255.0, 0.0);
		Slider greenSlide = new Slider(0.0, 255.0, 0.0);
		Slider blueSlide = new Slider(0.0, 255.0, 0.0);

//Showing color in box
		Ellipse colorShow = new Ellipse();
		colorShow.setRadiusX(15);
		colorShow.setRadiusY(10);

//Code for changing color in the box
		redSlide.valueProperty().addListener((observable, oldValue, newValue) -> {

			red = (int) redSlide.getValue();
			colorShow.setFill(Color.rgb(red, green, blue));

		});

		greenSlide.valueProperty().addListener((observable, oldValue, newValue) -> {

			green = (int) greenSlide.getValue();
			colorShow.setFill(Color.rgb(red, green, blue));

		});

		blueSlide.valueProperty().addListener((observable, oldValue, newValue) -> {

			blue = (int) blueSlide.getValue();
			colorShow.setFill(Color.rgb(red, green, blue));

		});

//For Creating
		EventHandler<MouseEvent> create = event -> {
			String select = selection.getSelectionModel().getSelectedItem();

			if (select.equals("Line")) {
				Line line = new Line();
				line.setStrokeWidth(5);
				line.setStartX(event.getX());
				line.setStartY(event.getY());
			

				line.setStroke(colorShow.getFill());

				ShapeCollection.addShape(line);
				pane.getChildren().add(line);
			}

			if (select.equals("Rectangle")) {
				Rectangle rectangle = new Rectangle();
				rectangle.setX(event.getX());
				rectangle.setY(event.getY());

				rectangle.setFill(colorShow.getFill());

				ShapeCollection.addShape(rectangle);
				pane.getChildren().add(rectangle);
			}

			if (select.equals("Ellipse")) {
				Ellipse el = new Ellipse();
				// el.setStrokeWidth(5);
				el.setCenterX(event.getX());
				el.setCenterY(event.getY());

				el.setFill(colorShow.getFill());

				ShapeCollection.addShape(el);
				pane.getChildren().add(el);
			}

		};

//For Draging		
		EventHandler<MouseEvent> draging = event -> {
			String select = selection.getSelectionModel().getSelectedItem();

			if (select.equals("Line")) {
				Line line = ShapeCollection.getLines().get(ShapeCollection.getLines().size() - 1);
				line.setEndX(event.getX());
				line.setEndY(event.getY());
			}

			if (select.equals("Rectangle")) {
				Rectangle rectangle = ShapeCollection.getRectangles().get(ShapeCollection.getRectangles().size() - 1);
				rectangle.setWidth(event.getX() - rectangle.getX());
				rectangle.setHeight(event.getY() - rectangle.getY());
			}

			if (select.equals("Ellipse")) {
				Ellipse ellipse = ShapeCollection.getElipses().get(ShapeCollection.getElipses().size() - 1);
				ellipse.setRadiusX(event.getX() - ellipse.getCenterX());
				ellipse.setRadiusY(event.getY() - ellipse.getCenterY());
			}

		};

//For Moving
		EventHandler<MouseEvent> moving = event -> {

			if (event.getTarget() instanceof Line) {
				int indLine = ShapeCollection.getLines().indexOf(event.getTarget());

				ShapeCollection.getLines().get(indLine).setEndX(event.getX());
				ShapeCollection.getLines().get(indLine).setEndY(event.getY());
			}

			if (event.getTarget() instanceof Rectangle) {
				int indRect = ShapeCollection.getRectangles().indexOf(event.getTarget());

				ShapeCollection.getRectangles().get(indRect).setX(event.getX());
				ShapeCollection.getRectangles().get(indRect).setY(event.getY());
			}

			if (event.getTarget() instanceof Ellipse) {
				int indEllipse = ShapeCollection.getElipses().indexOf(event.getTarget());

				ShapeCollection.getElipses().get(indEllipse).setCenterX(event.getX());
				ShapeCollection.getElipses().get(indEllipse).setCenterY(event.getY());
			}

			if (event.getTarget() instanceof Arc) {
				int indArc = ShapeCollection.getArc().indexOf(event.getTarget());

				ShapeCollection.getArc().get(indArc).setCenterX(event.getX());
				ShapeCollection.getArc().get(indArc).setCenterY(event.getY());
			}
		};

//For Deleting
		EventHandler<MouseEvent> deleting = event -> {

			if (event.getTarget() instanceof Line) {
				int indLine = ShapeCollection.getLines().indexOf(event.getTarget());

				pane.getChildren().remove(event.getTarget());
				ShapeCollection.getLines().remove(indLine);
			}

			if (event.getTarget() instanceof Rectangle) {
				int indRect = ShapeCollection.getRectangles().indexOf(event.getTarget());

				pane.getChildren().remove(event.getTarget());
				ShapeCollection.getRectangles().remove(indRect);
			}

			if (event.getTarget() instanceof Ellipse) {
				int indEllipse = ShapeCollection.getElipses().indexOf(event.getTarget());

				pane.getChildren().remove(event.getTarget());
				ShapeCollection.getElipses().remove(indEllipse);
			}

			if (event.getTarget() instanceof Arc) {
				int indArc = ShapeCollection.getArc().indexOf(event.getTarget());

				pane.getChildren().remove(event.getTarget());
				ShapeCollection.getArc().remove(indArc);
			}
		};
		
	

//For Drawing
		EventHandler<ActionEvent> eDraw = event -> {
			selection.setDisable(false);

			pane.addEventHandler(MouseEvent.MOUSE_PRESSED, create);
			pane.addEventHandler(MouseEvent.MOUSE_DRAGGED, draging);
		};

		EventHandler<ActionEvent> dDraw = event -> {
			selection.setDisable(true);

			pane.removeEventHandler(MouseEvent.MOUSE_PRESSED, create);
			pane.removeEventHandler(MouseEvent.MOUSE_DRAGGED, draging);
		};

//Moving
		EventHandler<ActionEvent> eMove = event -> {
			pane.addEventHandler(MouseEvent.MOUSE_DRAGGED, moving);
		};

		EventHandler<ActionEvent> dMove = event -> {
			pane.removeEventHandler(MouseEvent.MOUSE_DRAGGED, moving);
		};

//Deleting
		EventHandler<ActionEvent> edelete = event -> {
			pane.addEventHandler(MouseEvent.MOUSE_CLICKED, deleting);
		};
		EventHandler<ActionEvent> ddelete = event -> {
			pane.removeEventHandler(MouseEvent.MOUSE_CLICKED, deleting);
		};

		draw.addEventHandler(ActionEvent.ACTION, eDraw);
		draw.addEventHandler(ActionEvent.ACTION, dMove);
		draw.addEventHandler(ActionEvent.ACTION, ddelete);

		move.addEventHandler(ActionEvent.ACTION, eMove);
		move.addEventHandler(ActionEvent.ACTION, dDraw);
		move.addEventHandler(ActionEvent.ACTION, ddelete);

		delete.addEventHandler(ActionEvent.ACTION, edelete);
		delete.addEventHandler(ActionEvent.ACTION, dMove);
		delete.addEventHandler(ActionEvent.ACTION, dDraw);

		HBox c = new HBox(15, currentT, colorShow);
		VBox all = new VBox(15, toolT, draw, move, delete, dAll, selection, colorT, redT, redSlide, greenT, greenSlide,
				blueT, blueSlide, c);
		all.setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		all.setPadding(new Insets(10));

		bp.setCenter(pane);
		bp.setLeft(all);
		
		
		Scene scene = new Scene(bp, 600, 600);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

}
