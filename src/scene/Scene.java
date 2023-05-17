package scene;

import geometries.Geometries;
import geometries.Intersectable;
import lighting.AmbientLight;
import lighting.LightSource;
import primitives.Color;
import primitives.Double3;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import java.awt.*;
import java.util.Arrays;


/**
 * The Scene class represents a scene in a 3D graphics environment.
 * It contains information such as the name of the scene, the background color,
 * the ambient light, and the geometries present in the scene.
 */
public class Scene {
    public String name = null;

    public Geometries geometries = new Geometries();
    public Color background = Color.BLACK;
    public AmbientLight ambientLight = AmbientLight.NONE;

    public List<LightSource> lights = new LinkedList<>();

    /**
     * Constructs a Scene object with the specified name.
     * @param name the name of the scene
     */
    public Scene(String name) { this.name = name; } // private constructor!

    /**
     * Setter of the background color
     * @param background the background color of the scene
     * @return the updated scene
     */
    public Scene setBackground(Color background) {this.background = background;return this;}
    /**
     * Setter of the scene's ambient light
     * @param ambientLight the ambient color of the scene
     * @return the updated scene
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {this.ambientLight = ambientLight;
        return this;
    }

    /**
     * Setter of the geometries in the scene
     *
     * @param geometries a group of all geometries in the scene
     * @return the updated scene
     */
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }

    /**
     * Setter of the lights
     * @param lights the list of light sources
     * @return the updated scene
     */
    public Scene setLights(List<LightSource> lights) {
        this.lights = lights;return this;
    }

}
