package quantik.undo;

import java.util.Date;

import quantik.control.Partida;
import quantik.excepcion.CoordenadasIncorrectasException;
import quantik.util.Color;
import quantik.util.Figura;

/**
 * Interfaz para el mecanismo de deshacer jugadas.
 * 
 * @author <a href="apo1004@alu.ubu.es"> Amanda Pérez Olmos </a>
 * @version 1.0
 */
public interface MecanismoDeDeshacer {

	/**
	 * Devuelve el número de jugadas que pueden deshacerse hasta el momento.
	 * 
	 * @return número de jugadas que pueden deshacerse actualmente
	 */
	public int consultarNumeroJugadasEnHistorico();
	
	/**
	 * Devuelve un clon en profundidad de la partida en el estado actual, según se hayan
	 * hecho y deshecho las jugadas.
	 * 
	 * @return clon profundo de la partida actual
	 */
	public Partida consultarPartidaActual();
	
	/**
	 * Deshace la última jugada realizada.
	 */
	public void deshacerJugada();
	
	/**
	 * Recibe la última jugada realizada para guardar sus efectos.
	 * 
	 * @param fila fila del tablero en la que se coloca la pieza
	 * @param columna columna del tablero en la que se coloca la pieza
	 * @param figura figura que tiene la pieza colocada
	 * @param color color que tiene la pieza colocada
	 * @throws CoordenadasIncorrectasException si las coordenadas están fuera del tablero
	 */
	public void hacerJugada (int fila, int columna, Figura figura, Color color) throws CoordenadasIncorrectasException;
	
	/**
	 * Devuelve la fecha en la que se inicializa el mecanismo de deshacer.
	 * 
	 * @return fecha
	 */
	public Date obtenerFechaInicio();
}
