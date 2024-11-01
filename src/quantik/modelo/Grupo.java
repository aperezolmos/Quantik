package quantik.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import quantik.util.Color;
import quantik.util.Figura;

/**
 * Cuatro celdas del tablero que representan un grupo.
 * 
 * @author <a href="apo1004@alu.ubu.es"> Amanda Pérez Olmos </a>
 * @since 1.0
 * @version 2.0
 */
public class Grupo {

	/**
	 * Celdas que conforman un grupo del tablero.
	 */
	private List<Celda> celdas;
	
	/**
	 * Valor constante del número de celdas que conforman un grupo.
	 */
	private final static int NUM_CELDAS_EN_GRUPO = 4;
	
	/**
	 * Inicializa un grupo con las referencias a las celdas del tablero que lo conforman.
	 * 
	 * @param celdas celdas que pertenecen al grupo
	 */
	public Grupo (List<Celda> celdas) {
		
		this.celdas = new ArrayList<>();
		
		for (Celda celdaActual : celdas) {
			this.celdas.add(celdaActual);
		}
	}
	
	/**
	 * Clona en profundidad el grupo actual.
	 * 
	 * @return clon del grupo
	 */
	public Grupo clonar() {
		
		List<Celda> celdasClon = new ArrayList<>();
		
		for (Celda celdaActual : celdas) {
			celdasClon.add(celdaActual.clonar());
		}
		return new Grupo(celdasClon);
	}
	
	/**
	 * Devuelve el número de celdas que componen actualmente el grupo.
	 * 
	 * @return número de celdas del grupo
	 */
	public int consultarNumeroCeldas() {
		return celdas.size();
	}
	
	/**
	 * Devuelve el número total de piezas actualmente en celdas del grupo.
	 * 
	 * @return número de piezas en el grupo
	 */
	public int consultarNumeroPiezas() {
		
		int contador = 0;
		
		for (Celda celdaActual : celdas) {
			
			if (!celdaActual.estaVacia()) {
				contador++;
			}
		}
		return contador;
	}
	
	/**
	 * Comprueba si existe una celda en el grupo igual a la celda pasada como argumento.
	 * 
	 * @param celdaABuscar celda que se quiere localizar
	 * @return true si la celda existe en el grupo y false en caso contrario
	 */
	public boolean contieneCelda (Celda celdaABuscar) {
		return celdas.contains(celdaABuscar);
	}
	
	/**
	 * Comprueba si hay cuatro piezas con cuatro figuras diferentes en el grupo.
	 * 
	 * @return true si las hay y false si no
	 */
	public boolean estaCompletoConFigurasDiferentes() {
		
		if (consultarNumeroPiezas() == NUM_CELDAS_EN_GRUPO) {
			
			for (int i = 0; i < celdas.size() - 1; i++) {
				for (int j = i + 1; j < celdas.size(); j++) {
					
					if (celdas.get(i).consultarPieza().consultarFigura() == celdas.get(j).consultarPieza().consultarFigura()) {
						return false;
					}
				}
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Comprueba si en las celdas hay alguna pieza con la misma figura y color contrario.
	 * 
	 * @param figura figura de la pieza buscada
	 * @param color color del que se quiere buscar su contrario
	 * @return true si existe y false si no
	 */
	public boolean existeMismaPiezaDelColorContrario (Figura figura, Color color) {

		for (Celda celdaActual : celdas) {
			
			if (!celdaActual.estaVacia() && celdaActual.consultarPieza().equals(new Pieza(figura,color.obtenerContrario()))) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(celdas);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Grupo other = (Grupo) obj;
		return Objects.equals(celdas, other.celdas);
	}

	@Override
	public String toString() {
		return "Grupo [celdas=" + celdas + "]";
	}
}
