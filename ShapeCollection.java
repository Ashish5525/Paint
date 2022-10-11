import java.util.ArrayList;

import javafx.scene.shape.Arc;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class ShapeCollection {

	private static ArrayList<Line> lines;
	private static ArrayList<Rectangle> rectangles;
	private static ArrayList<Ellipse> elipses;
	private static ArrayList<Arc> arcs;
	
	ArrayList <Shape> shape = new ArrayList<>();
	
	public ShapeCollection() {
		lines = new ArrayList<>();
		rectangles = new ArrayList<>();
		elipses = new ArrayList<>();
	}
	
	public static void addShape(Shape shape) {
		
		if(shape instanceof Line) {
			lines.add((Line)shape);
		}
		else if(shape instanceof Rectangle) {
			rectangles.add((Rectangle)shape);
		}
		else if(shape instanceof Ellipse) {
			elipses.add((Ellipse)shape);
		}
		else if(shape instanceof Arc) {
			arcs.add((Arc)shape);
		}
	}

	public static ArrayList<Line> getLines() {
		return lines;
	}

	public static ArrayList<Rectangle> getRectangles() {
		return rectangles;
	}

	public static ArrayList<Ellipse> getElipses() {
		return elipses;
	}
	
	public static ArrayList<Arc> getArc() {
		return arcs;
	}
	
	public ArrayList<Shape> getAllShapes(){
		
		shape.addAll(lines);
		shape.addAll(rectangles);
		shape.addAll(elipses);
		shape.addAll(arcs);
		
		return shape;
	}
	
}
