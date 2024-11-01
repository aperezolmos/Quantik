package quantik.undo;

import quantik.util.Color;
import quantik.util.Figura;

/**
 * Clase auxiliar que almacena datos de una jugada.
 * 
 * @author <a href="apo1004@alu.ubu.es"> Amanda Pérez Olmos </a>
 * @version 1.0
 */
public class Jugada {

	/**
	 * Fila de la jugada.
	 */
	private int fila;
	
	/**
	 * Columna de la jugada.
	 */
	private int columna;
	
	/**
	 * Figura de la jugada.
	 */
	private Figura figura;
	
	/**
	 * Color del turno de la jugada.
	 */
	private Color color;
	
	/**
	 * Constructor que inicializa los atributos de una jugada.
	 * 
	 * @param fila fila de la jugada
	 * @param columna columna de la jugada
	 * @param figura figura de la jugada
	 * @param color color del turno de la jugada
	 */
	public Jugada (int fila, int columna, Figura figura, Color color) {
		
		this.fila = fila;
		this.columna = columna;
		this.figura = figura;
		this.color = color;
	}
	
	/**
	 * Devuelve la fila de la celda donde se ha colocado la pieza.
	 * 
	 * @return fila de la jugada
	 */
	public int consultarFila() {
		return fila;
	}
	
	/**
	 * Devuelve la columna de la celda donde se ha colocado la pieza.
	 * 
	 * @return columna de la jugada
	 */
	public int consultarColumna() {
		return columna;
	}
	
	/**
	 * Devuelve la figura de la pieza con la que se ha realizado la jugada.
	 * 
	 * @return figura de la jugada
	 */
	public Figura consultarFigura() {
		return figura;
	}
	
	/**
	 * Devuelve el turno que ha realizado la jugada.
	 * 
	 * @return color del turno de la jugada
	 */
	public Color consultarColor() {
		return color;
	}
}
