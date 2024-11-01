package quantik.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import quantik.excepcion.CoordenadasIncorrectasException;
import quantik.util.Color;
import quantik.util.Figura;

/**
 * Gestión de los grupos del tablero.
 * 
 * @author <a href="apo1004@alu.ubu.es"> Amanda Pérez Olmos </a>
 * @since 1.0
 * @version 2.0
 */
public class GestorGrupos {

	/**
	 * Grupos que hay en el tablero.
	 */
	private List<Grupo> grupos;
	
	/**
	 * Crea los grupos con las respectivas celdas contenidas en el tablero pasado como argumento.
	 * 
	 * @param tablero tablero donde se encuentran las celdas pertenecientes a los grupos
	 */
	public GestorGrupos (Tablero tablero) {
		
		grupos = new ArrayList<>();
		
		generarGruposHorizontales(tablero);
		generarGruposVerticales(tablero);
		generarGruposCuadrados(tablero);
	}
	
	/**
	 * Genera grupos horizontales en un tablero.
	 * 
	 * @param tablero tablero donde se encuentran las celdas pertenecientes a los grupos
	 */
	private void generarGruposHorizontales (Tablero tablero) {
		
		List<Celda> celdasActuales = new ArrayList<>();
		int numFilas = tablero.consultarNumeroFilas();
		int numColumnas = tablero.consultarNumeroColumnas();
		
		for (int fila = 0; fila < numFilas; fila++) {
			for (int columna = 0; columna < numColumnas; columna++) {
				
				try {
					celdasActuales.add(tablero.obtenerCelda(fila, columna));
				}
				catch (CoordenadasIncorrectasException ex) {
					throw new RuntimeException("Error grave en el código.",ex);
				}
			}
			grupos.add(new Grupo(celdasActuales));
			celdasActuales.clear();
		}
	}
	
	/**
	 * Genera grupos verticales en un tablero.
	 * 
	 * @param tablero tablero donde se encuentran las celdas pertenecientes a los grupos
	 */
	private void generarGruposVerticales (Tablero tablero) {
		
		List<Celda> celdasActuales = new ArrayList<>();
		int numFilas = tablero.consultarNumeroFilas();
		int numColumnas = tablero.consultarNumeroColumnas();
		
		for (int columna = 0; columna < numColumnas; columna++) {
			for (int fila = 0; fila < numFilas; fila++) {
				
				try {
					celdasActuales.add(tablero.obtenerCelda(fila, columna));
				}
				catch (CoordenadasIncorrectasException ex) {
					throw new RuntimeException("Error grave en el código.",ex);
				}
			}
			grupos.add(new Grupo(celdasActuales));
			celdasActuales.clear();
		}
	}
	
	/**
	 * Genera grupos cuadrados en un tablero.
	 * 
	 * @param tablero tablero donde se encuentran las celdas pertenecientes a los grupos
	 */
	private void generarGruposCuadrados (Tablero tablero) {
		
		List<Celda> celdasActuales = new ArrayList<>();
		int numFilas = tablero.consultarNumeroFilas();
		int numColumnas = tablero.consultarNumeroColumnas();
		int limiteFila, limiteCol;
		
		for (int filaStart = 0; filaStart < numFilas; filaStart += 2) {
			for (int columnaStart = 0; columnaStart < numColumnas; columnaStart += 2) {
				
				limiteFila = filaStart + 2;
				limiteCol = columnaStart + 2;
								
				for (int fila = filaStart; fila < limiteFila; fila++) {
					for (int columna = columnaStart; columna < limiteCol; columna++) {
						
						try {
							celdasActuales.add(tablero.obtenerCelda(fila, columna));
						}
						catch (CoordenadasIncorrectasException ex) {
							throw new RuntimeException("Error grave en el código.",ex);
						}
					}
				}
				grupos.add(new Grupo(celdasActuales));
				celdasActuales.clear();
			}
		}
	}
	
	/**
	 * Comprueba si habría algún conflicto a la hora de colocar una pieza en los grupos a los
	 * que pertenece la celda.
	 * <p>
	 * No se puede colocar una pieza en un grupo donde ya haya una con la misma figura y opuesto color.
	 * 
	 * @param celda celda perteneciente a los grupos
	 * @param figura figura de la pieza que se espera colocar
	 * @param turno color de la pieza que se espera colocar
	 * @return true si hay conflicto y false en caso contrario
	 */
	public boolean hayConflictoEnGruposDeCelda (Celda celda, Figura figura, Color turno) {
		
		for (Grupo grupoActual : obtenerGruposConteniendoCelda(celda)) {
			
			if (grupoActual.existeMismaPiezaDelColorContrario(figura, turno)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Comprueba si algún grupo está completo con una combinación de 4 piezas de figuras diferentes.
	 * 
	 * @return true si hay un grupo y false si no hay ninguno
	 */
	public boolean hayGrupoGanador() {
		
		for (Grupo grupoActual : grupos) {
			
			if (grupoActual.estaCompletoConFigurasDiferentes()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Obtiene los grupos que contienen a la celda pasada como argumento.
	 * 
	 * @param celda celda que deben contener los grupos
	 * @return grupos con esa celda
	 */
	public List<Grupo> obtenerGruposConteniendoCelda (Celda celda) {
		
		List<Grupo> gruposContienen = new ArrayList<>();
		
		for (Grupo grupoActual : grupos) {
			
			if (grupoActual.contieneCelda(celda)) {
				gruposContienen.add(grupoActual);
			}
		}
		return gruposContienen;
	}

	@Override
	public int hashCode() {
		return Objects.hash(grupos);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GestorGrupos other = (GestorGrupos) obj;
		return Objects.equals(grupos, other.grupos);
	}

	@Override
	public String toString() {
		return "GestorGrupos [grupos=" + grupos + "]";
	}
}
