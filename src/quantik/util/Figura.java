package quantik.util;

/**
 * Enumeración figura.
 * 
 * @author <a href="apo1004@alu.ubu.es"> Amanda Pérez Olmos </a>
 * @version 1.0
 */
public enum Figura {

	/**
	 * Figura cilindro.
	 */
	CILINDRO ("CL"),
	
	/**
	 * Figura cono.
	 */
	CONO ("CN"),
	
	/**
	 * Figura cubo.
	 */
	CUBO ("CB"),
	
	/**
	 * Figura esfera.
	 */
	ESFERA ("ES");
	
	/**
	 * Texto asociado a la figura.
	 */
	private String texto;
	
	/**
	 * Constructor.
	 * 
	 * @param texto texto asociado a la figura
	 */
	private Figura (String texto) {
		this.texto = texto;
	}
	
	/**
	 * Obtiene el texto de la figura.
	 * 
	 * @return texto de la figura
	 */
	public String aTexto() {
		return texto;
	}
}
