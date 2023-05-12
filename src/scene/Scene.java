package scene;

import geometries.Geometries;
import geometries.Intersectable;
import lighting.AmbientLight;
import primitives.Color;
import primitives.Double3;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import java.awt.*;
import java.util.Arrays;

public class Scene {
    public String name = null;

    public Geometries geometries = new Geometries();
    public Color background = Color.BLACK;
    public AmbientLight ambient = AmbientLight.NONE;
    public Scene(String name) { this.name = name; } // private constructor!

    public Scene setBackground(Color color) { background = color; return this; }
    public Scene setAmbient(Color color, Double3 ka) {
        ambient = new AmbientLight(color, ka); return this;
    }
    public Scene addGeometries(Intersectable... geometries) {
        this.geometries = new Geometries(geometries);return this;
    }

}
