package quantik.excepcion;

/**
 * Lanzada para indicar que las coordenadas están fuera de los límites del tablero.
 * 
 * @author <a href="apo1004@alu.ubu.es"> Amanda Pérez Olmos </a>
 * @version 1.0
 */
public class CoordenadasIncorrectasException extends Exception {

	/**
	 * Valor utilizado para la serialización de objetos.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Construye una CoordenadasIncorrectasException sin mensaje.
	 */
	public CoordenadasIncorrectasException() {
		super();
	}
	
	/**
	 * Construye una CoordenadasIncorrectasException con el mensaje especificado.
	 * 
	 * @param message mensaje especificado para la excepción
	 */
	public CoordenadasIncorrectasException (String message) {
		super(message);
	}

	/**
	 * Construye una CoordenadasIncorrectasException con la causa especificada.
	 * 
	 * @param cause causa especificada de la excepción
	 */
	public CoordenadasIncorrectasException (Throwable cause) {
		super(cause);
	}
	
	/**
	 * Construye una CoordenadasIncorrectasException con el mensaje y causa especificados.
	 * 
	 * @param message mensaje especificado para la excepción
	 * @param cause causa especificada de la excepción
	 */
	public CoordenadasIncorrectasException (String message, Throwable cause) {
		super(message,cause);
	}
}
