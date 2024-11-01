package quantik.undo;

import java.util.Date;

/**
 * Clase abstracta donde se almacenan atributos y métodos comunes a las máquinas del tiempo
 * con jugadas y partidas.
 * 
 * @author <a href="apo1004@alu.ubu.es"> Amanda Pérez Olmos </a>
 * @version 1.0
 */
public abstract class MaquinaDelTiempoAbstracta implements MecanismoDeDeshacer {

	/**
	 * Fecha en la que se inicializa el mecanismo de deshacer.
	 */
	private Date fecha;
	
	/**
	 * Número de filas del tablero utilizado.
	 */
	@SuppressWarnings("unused")
	private int filas;
	
	/**
	 * Número de columnas del tablero utilizado.
	 */
	@SuppressWarnings("unused")
	private int columnas;
	
	/**
	 * Inicializa la máquina del tiempo con la fecha actual y el tamaño del tablero.
	 * 
	 * @param fecha fecha actual
	 * @param filas número de filas del tablero utilizado
	 * @param columnas número de columnas del tablero utilizado
	 */
	public MaquinaDelTiempoAbstracta (Date fecha, int filas, int columnas) {

		this.fecha = fecha;
		this.filas = filas;
		this.columnas = columnas;		
	}
	
	@Override
	public Date obtenerFechaInicio() {
		return fecha;
	}
}