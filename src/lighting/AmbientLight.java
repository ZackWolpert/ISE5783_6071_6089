package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * The AmbientLight class represents an ambient light source in a 3D scene.
 * It provides methods for setting and retrieving the intensity of the light.
 */
public class AmbientLight {

    /**
     * A static constant representing no ambient light, with intensity set to black.
     */
    public static AmbientLight NONE = new AmbientLight(Color.BLACK,Double3.ZERO);

    /**
     * The intensity of the ambient light.
     */
    public Color intensity;

    /**
     * Constructs an AmbientLight object with the specified intensity and ambient reflection coefficient.
     * @param Ia The color representing the intensity of the ambient light.
     * @param Ka The ambient reflection coefficient as a Double3 object.
     */
    public AmbientLight(Color Ia, Double3 Ka) {
        intensity = new Color(Ia.scale(Ka).getColor());
    }

    /**
     * Constructs an AmbientLight object with the specified ambient reflection coefficient.
     * The intensity of the ambient light is calculated based on the default intensity and the given coefficient.
     * @param Ka The ambient reflection coefficient as a double value.
     */
    public AmbientLight(double Ka){
        intensity = new Color(intensity.scale(Ka).getColor());
    }

    /**
     * Retrieves the intensity of the ambient light.
     * @return The color representing the intensity of the ambient light.
     */
    public Color getIntensity() {
        return intensity;
    }


}
