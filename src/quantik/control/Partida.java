package quantik.control;

import java.util.Objects;

import quantik.excepcion.CoordenadasIncorrectasException;
import quantik.modelo.Caja;
import quantik.modelo.Celda;
import quantik.modelo.GestorGrupos;
import quantik.modelo.Tablero;
import quantik.util.Color;
import quantik.util.Figura;

/**
 * Gestión del funcionamiento de la partida.
 * 
 * @author <a href="apo1004@alu.ubu.es"> Amanda Pérez Olmos </a>
 * @version 2.0
 */
public class Partida {

	/**
	 * Caja de piezas blancas.
	 */
	private Caja cajaBlancas;
	
	/**
	 * Caja de piezas negras.
	 */
	private Caja cajaNegras;
	
	/**
	 * Jugador 1 de la partida.
	 */
	private Color jugadorA;
	
	/**
	 * Jugador 2 de la partida.
	 */
	private Color jugadorB;
	
	/**
	 * Color de las piezas del jugador con el turno actual.
	 */
	private Color turno;
	
	/**
	 * Gestor de los grupos del tablero.
	 */
	private GestorGrupos gestor;
	
	/**
	 * Tablero del juego.
	 */
	private Tablero tablero;
	
	/**
	 * Número de jugadas que se han llevado a cabo en la partida.
	 */
	private int numJugadas;
	
	/**
	 * Constructor que inicializa la partida con el tablero y las cajas de ambos jugadores.
	 * 
	 * @param tablero tablero del juego
	 * @param cajaBlancas caja del jugador con piezas blancas
	 * @param cajaNegras caja del jugador con piezas negras
	 */
	public Partida (Tablero tablero, Caja cajaBlancas, Caja cajaNegras) {
		
		this.tablero = tablero;
		this.cajaBlancas = cajaBlancas;
		this.cajaNegras = cajaNegras;
		
		gestor = new GestorGrupos(tablero);
		jugadorA = Color.BLANCO;
		jugadorB = Color.NEGRO;

		// Se le da el primer turno al jugador con piezas blancas.
		turno = (jugadorA == Color.BLANCO ? jugadorA : jugadorB);
	}
	
	/**
	 * Cambia el turno actual al otro jugador.
	 */
	public void cambiarTurno() {
		
		if (turno == jugadorA) {
			turno = jugadorB;
		}
		else {
			turno = jugadorA;
		}
	}
	
	/**
	 * Devuelve un clon en profundidad de la partida actual.
	 * 
	 * @return clon de la partida
	 */
	public Partida clonar() {
		
		Partida partidaClon = new Partida(consultarTablero(),consultarCajaBlancas(),consultarCajaNegras());
		partidaClon.numJugadas = consultarNumeroJugada();
		partidaClon.turno = consultarTurno();
		
		return partidaClon;
	}
	
	/**
	 * Coloca una pieza del turno actual en las coordenadas indicadas.
	 * 
	 * @param fila fila de la celda donde se quiere colocar la pieza
	 * @param columna columna de la celda donde se quiere colocar la pieza
	 * @param figura figura que tiene la pieza que se quiere colocar
	 * @throws CoordenadasIncorrectasException si las coordenadas están fuera del tablero
	 */
	public void colocarPiezaEnTurnoActual (int fila, int columna, Figura figura) throws CoordenadasIncorrectasException {
		
		Caja cajaActual = (turno == Color.BLANCO ? cajaBlancas : cajaNegras);
		
		if (!tablero.estaEnTablero(fila,columna)){
			throw new CoordenadasIncorrectasException("Coordenadas fuera de los límites del tablero.");
		}
		tablero.colocar(fila, columna, cajaActual.retirar(figura));
		numJugadas++;
	}
	
	/**
	 * Devuelve un clon en profundidad de la caja blanca.
	 * 
	 * @return clon de la caja blanca
	 */
	public Caja consultarCajaBlancas() {
		return cajaBlancas.clonar();
	}
	
	/**
	 * Devuelve un clon en profundidad de la caja negra.
	 * 
	 * @return clon de la caja negra
	 */
	public Caja consultarCajaNegras() {
		return cajaNegras.clonar();
	}
	
	/**
	 * Consulta el turno que ha ganado la partida siempre y cuando esta haya finalizado.
	 * 
	 * @return turno ganador de la partida o null si no ha finalizado aún
	 */
	public Color consultarGanador() {
				
		if (estaAcabadaPartida()) {

			if (hayAlgunGrupoCompleto()) {
				return turno;
			}
			else if (estaBloqueadoTurnoActual()) {
				return turno.obtenerContrario();
			}
		}
		return null;
	}
	
	/**
	 * Devuelve el número de jugadas realizadas hasta el momento.
	 * 
	 * @return número actual de jugadas
	 */
	public int consultarNumeroJugada() {
		return numJugadas;
	}
	
	/**
	 * Consulta el tablero actual devolviendo un clon en profundidad del mismo.
	 * 
	 * @return clon del tablero
	 */
	public Tablero consultarTablero() {
		return tablero.clonar();
	}
	
	/**
	 * Devuelve el turno actual que puede realizar la siguiente jugada.
	 * 
	 * @return turno actual
	 */
	public Color consultarTurno() {
		return turno;
	}
	
	/**
	 * Comprueba si es legal colocar una figura en las coordenadas indicadas, con el turno actual.
	 * <p>
	 * Será legal si la celda está vacía, en los límites del tablero, la figura deseada
	 * se encuentra disponible en la caja del turno actual y no hay conflicto en los grupos.
	 * 
	 * @param fila fila de la celda donde se quiere colocar la pieza
	 * @param columna columna de la celda donde se quiere colocar la pieza
	 * @param figura figura de la pieza que se quiere colocar
	 * @return true si la jugada es legal y false si no lo es
	 */
	public boolean esJugadaLegalEnTurnoActual (int fila, int columna, Figura figura) {
		
		Caja cajaActual = (turno == Color.BLANCO ? cajaBlancas : cajaNegras);
		
		if (tablero.estaEnTablero(fila,columna)) {
			
			try {
				
				Celda celda = tablero.consultarCelda(fila,columna);
				
				if (celda.estaVacia() && cajaActual.estaDisponible(figura)
					&& !gestor.hayConflictoEnGruposDeCelda(celda, figura, turno)) {
					
					return true;
				}
			}
			catch (CoordenadasIncorrectasException ex) {
				throw new RuntimeException("Error grave en el código.",ex);
			}
		}
		return false;
	}
	
	/**
	 * Comprueba si la partida está acabada por alguna de las condiciones de finalización.
	 * 
	 * @return true si la partida está acabada y false si no lo está
	 */
	public boolean estaAcabadaPartida() {
		
		return (hayAlgunGrupoCompleto() || estaBloqueadoTurnoActual());
	}
	
	/**
	 * Comprueba si el jugador con turno actual no puede colocar ninguna de sus piezas.
	 * <p>
	 * Habrá bloqueo si el jugador con el turno actual no puede colocar ninguna de sus
	 * piezas o si se ha quedado sin piezas y tiene que mover.
	 * 
	 * @return true si el turno está bloqueado y falso si no
	 */
	public boolean estaBloqueadoTurnoActual() {
		
		Caja cajaActual = (turno == Color.BLANCO ? cajaBlancas : cajaNegras);
		int jugadasLegales = 0;
		
		// Se recorre todo el tablero con las diferentes figuras y se cuentan las jugadas legales.
		for (Figura fig : Figura.values()) {
			
			for (int i = 0; i < tablero.consultarNumeroFilas(); i++) {
				for (int j = 0; j < tablero.consultarNumeroColumnas(); j++) {
					
					if (esJugadaLegalEnTurnoActual(i,j,fig)) {
						jugadasLegales++;
					}
				}
			}
		}
		return (jugadasLegales == 0 || cajaActual.contarPiezasActuales() == 0);
	}
	
	/**
	 * Comprueba si alguno de los grupos tiene cuatro piezas diferentes con independencia del color.
	 * 
	 * @return true si hay algún grupo y false si no hay ninguno
	 */
	public boolean hayAlgunGrupoCompleto() {
		return gestor.hayGrupoGanador();
	}

	@Override
	public int hashCode() {
		return Objects.hash(cajaBlancas, cajaNegras, gestor, jugadorA, jugadorB, numJugadas, tablero, turno);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Partida other = (Partida) obj;
		return Objects.equals(cajaBlancas, other.cajaBlancas) && Objects.equals(cajaNegras, other.cajaNegras)
				&& Objects.equals(gestor, other.gestor) && jugadorA == other.jugadorA && jugadorB == other.jugadorB
				&& numJugadas == other.numJugadas && Objects.equals(tablero, other.tablero) && turno == other.turno;
	}
}
