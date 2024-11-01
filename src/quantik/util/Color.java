package quantik.util;

/**
 * Enumeración color.
 * 
 * @author <a href="apo1004@alu.ubu.es"> Amanda Pérez Olmos </a>
 * @version 1.0
 */
public enum Color {

	/**
	 * Color blanco.
	 */
	BLANCO ('B'),
	
	/**
	 * Color negro.
	 */
	NEGRO ('N');
	
	/**
	 * Representa la letra asociada a cada color.
	 */
	private char letra;
	
	/**
	 * Constructor que inicializa el carácter o letra almacenado en la enumeración.
	 * 
	 * @param letra carácter que se inicializa
	 */
	private Color(char letra) {
		this.letra = letra;
	}
	
	/**
	 * Devuelve la letra asignada al color.
	 * 
	 * @return carácter letra correspondiente al color indicado
	 */
	public char toChar() {
		return letra;
	}
	
	/**
	 * Obtiene el color contrario.
	 * 
	 * @return color contrario
	 */
	public Color obtenerContrario() {
		return this.equals(BLANCO)? NEGRO : BLANCO;
	}
}
