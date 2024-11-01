package quantik.modelo;

import java.util.Objects;

/**
 * Representa una celda del tablero.
 * 
 * @author <a href="apo1004@alu.ubu.es"> Amanda Pérez Olmos </a>
 * @version 1.0
 */
public class Celda {

	/**
	 * Fila a la que pertenece la celda.
	 */
	private int fila;
	
	/**
	 * Columna a la que pertenece la celda.
	 */
	private int columna;
	
	/**
	 * Pieza de la celda.
	 */
	private Pieza pieza;
	
	/**
	 * Constructor que inicializa la fila y columna de la celda.
	 * 
	 * @param fila indice de la fila a la que pertenece la pieza
	 * @param columna indice de la columna a la que pertenece la pieza
	 */
	public Celda (int fila, int columna) {
		this.fila = fila;
		this.columna = columna;
	}
	
	/**
	 * Clona en profundidad una celda.
	 * 
	 * @return clon de la celda
	 */
	public Celda clonar() {
		
		Celda celdaClon = new Celda (fila, columna);
		Pieza piezaClon = null;
		
		if (!estaVacia()) {
			piezaClon = pieza.clonar();
		}
		celdaClon.colocar(piezaClon);
		return celdaClon;
	}
	
	/**
	 * Coloca la pieza en la celda.
	 *
	 * @param pieza pieza que se coloca en la celda
	 */
	public void colocar (Pieza pieza) {
		this.pieza = pieza;
	}
	
	/**
	 * Obtiene la columna en la que se encuentra la celda.
	 * 
	 * @return columna a la que pertenece la celda
	 */
	public int consultarColumna() {
		return columna;
	}
	
	/**
	 * Obtiene la fila en la que se encuentra la celda.
	 * 
	 * @return fila a la que pertenece la celda
	 */
	public int consultarFila() {
		return fila;
	}
	
	/**
	 * Devuelve un clon de la pieza que se encuentra en la celda.
	 * 
	 * @return pieza de la celda
	 */
	public Pieza consultarPieza() {
		return (pieza == null) ? pieza : pieza.clonar();
	}
	
	/**
	 * Consulta si la celda tiene o no asignada una pieza.
	 * 
	 * @return true en caso de estar vacía y false en caso contrario.
	 */
	public boolean estaVacia() {
		return consultarPieza() == null;
	}

	@Override
	public int hashCode() {
		return Objects.hash(columna, fila, pieza);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Celda other = (Celda) obj;
		return columna == other.columna && fila == other.fila && Objects.equals(pieza, other.pieza);
	}

	@Override
	public String toString() {
		return "Celda [fila=" + fila + ", columna=" + columna + ", pieza=" + pieza + "]";
	}
}
