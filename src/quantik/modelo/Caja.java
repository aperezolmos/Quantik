package quantik.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import quantik.util.Color;
import quantik.util.Figura;

/**
 * Caja que contiene las figuras de cada jugador.
 * 
 * @author <a href="apo1004@alu.ubu.es"> Amanda Pérez Olmos </a>
 * @since 1.0
 * @version 2.0
 */
public class Caja {
	
	/**
	 * Piezas que se encuentran en la caja.
	 */
	private List<Pieza> piezas;
	
	/**
	 * Color de las piezas de la caja.
	 */
	private Color color;
	
	/**
	 * Número de figuras de cada tipo que se encuentran en la caja.
	 */
	private final static int NUM_FIGURAS_TIPO = 2;
	
	/**
	 * Carga la caja con piezas del color que corresponda.
	 * 
	 * @param color color de las piezas
	 */
	public Caja (Color color) {
		
		piezas = new ArrayList<>();
		this.color = color;
		
		for (Figura figuraActual : Figura.values()) {
			
			for (int i = 0; i < NUM_FIGURAS_TIPO; i++) {
				piezas.add(new Pieza (figuraActual,color));
			}
		}
	}
	
	/**
	 * Clona en profundidad la caja actual.
	 * 
	 * @return clon de la caja
	 */
	public Caja clonar() {
		
		Caja cajaClon = new Caja(color);
		List<Pieza> piezasClon = new ArrayList<>();
		
		for (Pieza piezaActual : piezas) {
			
			if (piezaActual != null) {
				piezasClon.add(piezaActual.clonar());
			}
			else {
				piezasClon.add(null);
			}
		}
		cajaClon.piezas = piezasClon;
		return cajaClon;
	}
	
	/**
	 * Consulta el color de las piezas de la caja.
	 * 
	 * @return color de las piezas
	 */
	public Color consultarColor() {
		return color;
	}
	
	/**
	 * Devuelve una lista genérica con clones en profundidad de las piezas disponibles en la caja.
	 * 
	 * @return lista clon de las piezas disponibles
	 */
	public List<Pieza> consultarPiezasDisponibles() {
		
		List<Pieza> piezasClon = new ArrayList<>();
		
		for (Pieza piezaActual : piezas) {
			
			if (piezaActual != null) {
				piezasClon.add(piezaActual.clonar());
			}
		}
		return piezasClon;
	}
	
	/**
	 * Devuelve el número de piezas que se encuentran actualmente en la caja.
	 * 
	 * @return número de piezas actuales en la caja
	 */
	public int contarPiezasActuales() {
		
		int contador = 0;
		
		for (Pieza piezaActual : piezas) {
			
			if (piezaActual != null) {
				contador++;
			}
		}
		return contador;
	}
	
	/**
	 * Consulta si existe una pieza disponible con la figura dada.
	 * 
	 * @param figura tipo de la figura que debe ser la pieza
	 * @return true si está disponible y false si no lo está
	 */
	public boolean estaDisponible (Figura figura) {
		return piezas.contains(new Pieza(figura,color));
	}
	
	/**
	 * Extrae una pieza con la figura indicada de la caja.
	 * 
	 * @param figura pieza que se quiere retirar
	 * @return clon de la pieza extraída o null si no está disponible
	 */
	public Pieza retirar (Figura figura) {
		
		Pieza piezaExtraida = null;
		
		if (estaDisponible(figura)) {
			
			for (int i = 0; i < piezas.size(); i++) {
				
				if (piezas.get(i) != null && piezas.get(i).consultarFigura() == figura) {

					piezaExtraida = piezas.get(i);
					piezas.set(i, null);
					return piezaExtraida;
				}
			}
		}
		return piezaExtraida;
	}

	@Override
	public int hashCode() {
		return Objects.hash(color, piezas);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Caja other = (Caja) obj;
		return color == other.color && Objects.equals(piezas, other.piezas);
	}

	@Override
	public String toString() {
		return "Caja [piezas=" + piezas + ", color=" + color + "]";
	}
}
