package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * The AmbientLight class represents an ambient light source in a 3D scene.
 * It provides methods for setting and retrieving the intensity of the light.
 */
public class AmbientLight extends Light {

    /**
     * A static constant representing no ambient light, with intensity set to black.
     */
    public static AmbientLight NONE = new AmbientLight(Color.BLACK,Double3.ZERO);

    /**
     * Constructs an AmbientLight object with the specified intensity and ambient reflection coefficient - sending the Color to super constructor ( Light ).
     * @param Ia The color representing the intensity of the ambient light.
     * @param Ka The ambient reflection coefficient as a Double3 object.
     */
    public AmbientLight(Color Ia, Double3 Ka) {
        super(new Color(Ia.scale(Ka).getColor()));
    }

}
