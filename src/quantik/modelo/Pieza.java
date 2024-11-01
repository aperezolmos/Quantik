package quantik.modelo;

import java.util.Objects;

import quantik.util.Color;
import quantik.util.Figura;

/**
 * Representa una pieza del juego.
 * 
 * @author <a href="apo1004@alu.ubu.es"> Amanda Pérez Olmos </a>
 * @version 1.0
 */
public class Pieza {

	/**
	 * Figura que representa la pieza.
	 */
	private Figura figura;
	
	/**
	 * Color de la pieza.
	 */
	private Color color;
	
	/**
	 * Constructor que inicializa la figura y el color de la pieza.
	 * 
	 * @param figura figura que tendrá la nueva pieza
	 * @param color color de la nueva pieza
	 */
	public Pieza (Figura figura, Color color) {
		this.figura = figura;
		this.color = color;
	}
	
	/**
	 * Concatenación del texto asociado a la figura y el texto asociado al color.
	 * 
	 * @return string con identificador de figura y color
	 */
	public String aTexto() {
		return figura.aTexto()+color.toChar();
	}
	
	/**
	 * Clona una pieza.
	 * 
	 * @return clon de la pieza actual
	 */
	public Pieza clonar() {
		return new Pieza(this.figura, this.color);
	}
	
	/**
	 * Obtiene el color de la pieza.
	 * 
	 * @return color
	 */
	public Color consultarColor() {
		return color;
	}
	
	/**
	 * Devuelve la figura de la pieza.
	 * 
	 * @return figura
	 */
	public Figura consultarFigura() {
		return figura;
	}

	@Override
	public int hashCode() {
		return Objects.hash(color, figura);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pieza other = (Pieza) obj;
		return color == other.color && figura == other.figura;
	}

	@Override
	public String toString() {
		return "Pieza [figura=" + figura + ", color=" + color + "]";
	}
}
