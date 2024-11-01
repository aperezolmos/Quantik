package quantik.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import quantik.excepcion.CoordenadasIncorrectasException;

/**
 * Tablero del juego Quantik.
 * 
 * @author <a href="apo1004@alu.ubu.es"> Amanda Pérez Olmos </a>
 * @since 1.0
 * @version 2.0
 */
public class Tablero {

	/**
	 * Celdas del tablero.
	 */
	private List<List<Celda>> matriz;
	
	/**
	 * Valor constante del número de filas del tablero.
	 */
	private final static int NUM_FILAS = 4;
	
	/**
	 * Valor constante del número de columnas del tablero.
	 */
	private final static int NUM_COLUMNAS = 4;
	
	/**
	 * Crea un tablero.
	 */
	public Tablero() {
		
		matriz = new ArrayList<>();
		
		for (int i = 0; i < NUM_FILAS; i++) {
			
			matriz.add(new ArrayList<>());
			
			for (int j = 0; j < NUM_COLUMNAS; j++) {
				matriz.get(i).add(j, new Celda(i,j));
			}
		}
	}
	
	/**
	 * Devuelve el estado del tablero con las piezas actualmente colocadas.
	 * 
	 * @return representación en formato texto del tablero actual
	 */
	public String aTexto() {

		String texto = "\t 0\t 1\t 2\t 3\n0\t";
		int contadorLinea = 0;
		
		for (int fila = 0; fila < matriz.size(); fila++) {
			for (int columna = 0; columna < matriz.get(0).size(); columna++) {
				
				try {
					if (obtenerCelda(fila,columna).estaVacia()) {
						texto += "-----\t";
					}
					else {
						Celda celda = obtenerCelda(fila,columna);
						Pieza pieza = celda.consultarPieza();
						
						String elemento = pieza.aTexto();
						texto += "-" + elemento + "-\t";
					}
				}
				catch (CoordenadasIncorrectasException ex) {
					throw new RuntimeException("Error grave en el código.",ex);
				}
			}
			contadorLinea++;
			texto += "\n\n" + contadorLinea + "\t";
		}
		return texto.substring(0,texto.length()-2);
	}
	
	/**
	 * Devuelve un clon en profundidad del tablero actual.
	 * 
	 * @return clon del tablero
	 */
	public Tablero clonar() {
		
		Tablero tableroClon = new Tablero();
		
		for (int i = 0; i < matriz.size(); i++) {
			for (int j = 0; j < matriz.get(0).size(); j++) {
				
				try {
					tableroClon.matriz.get(i).set(j, obtenerCelda(i,j).clonar());
				}
				catch (CoordenadasIncorrectasException ex) {
					throw new RuntimeException("Error grave en el código.",ex);
				}
			}
		}
		return tableroClon;
	}
	
	/**
	 * Coloca en la posición indicada del tablero la pieza pasada como argumento.
	 * 
	 * @param fila fila del tablero
	 * @param columna columna del tablero
	 * @param pieza pieza que se quiere colocar
	 * @throws CoordenadasIncorrectasException si las coordenadas están fuera del tablero
	 */
	public void colocar (int fila, int columna, Pieza pieza) throws CoordenadasIncorrectasException{
		
		if (!estaEnTablero(fila,columna)){
			throw new CoordenadasIncorrectasException("Coordenadas fuera de los límites del tablero.");
		}
		else {
			if (obtenerCelda(fila,columna) != null && obtenerCelda(fila,columna).estaVacia()
					&& pieza != null) {
				
				obtenerCelda(fila,columna).colocar(pieza);
			}
		}
	}
	
	/**
	 * Obtiene un clon en profundidad de una celda en una determinada posición.
	 * 
	 * @param fila fila de la celda a obtener
	 * @param columna columna de la celda a obtener
	 * @return celda que se encuentra en las coordenadas indicadas
	 * @throws CoordenadasIncorrectasException si las coordenadas están fuera del tablero
	 */
	public Celda consultarCelda (int fila, int columna) throws CoordenadasIncorrectasException {
		
		if (!estaEnTablero(fila,columna)){
			throw new CoordenadasIncorrectasException("Coordenadas fuera de los límites del tablero.");
		}
		return matriz.get(fila).get(columna).clonar();
	}
	
	/**
	 * Obtiene el número de columnas del tablero.
	 * 
	 * @return número de columnas
	 */
	public int consultarNumeroColumnas() {
		return matriz.get(0).size();
	}
	
	/**
	 * Obtiene el número de filas del tablero.
	 * 
	 * @return número de filas
	 */
	public int consultarNumeroFilas() {
		return matriz.size();
	}
	
	/**
	 * Comprueba si la fila y la columna están dentro del tablero.
	 * 
	 * @param fila fila a buscar
	 * @param columna columna a buscar
	 * @return true si las coordenadas pertenecen a los límites del tablero y false en
	 * caso contrario
	 */
	public boolean estaEnTablero (int fila, int columna) {
		return (fila >= 0 && fila < matriz.size() && columna >= 0 && columna < matriz.get(0).size());
	}
	
	/**
	 * Obtiene la celda de una determinada posición.
	 * 
	 * @param fila fila de la celda
	 * @param columna columna de la celda
	 * @return celda que se encuentra en las coordenadas indicadas
	 * @throws CoordenadasIncorrectasException si las coordenadas están fuera del tablero
	 */
	Celda obtenerCelda (int fila, int columna) throws CoordenadasIncorrectasException {
		
		if (!estaEnTablero(fila,columna)){
			throw new CoordenadasIncorrectasException("Coordenadas fuera de los límites del tablero.");
		}
		return matriz.get(fila).get(columna);
	}

	@Override
	public int hashCode() {
		return Objects.hash(matriz);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tablero other = (Tablero) obj;
		return Objects.equals(matriz, other.matriz);
	}

	@Override
	public String toString() {
		return "Tablero [matriz=" + matriz + "]";
	}
}
