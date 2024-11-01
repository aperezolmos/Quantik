package quantik.undo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import quantik.control.Partida;
import quantik.excepcion.CoordenadasIncorrectasException;
import quantik.modelo.Caja;
import quantik.modelo.Tablero;
import quantik.util.Color;
import quantik.util.Figura;

/**
 * Almacena el histórico de clones de las partidas según se vayan realizando jugadas.
 * 
 * @author <a href="apo1004@alu.ubu.es"> Amanda Pérez Olmos </a>
 * @version 1.0
 */
public class MaquinaDelTiempoConPartidas extends MaquinaDelTiempoAbstracta {

	/**
	 * Histórico de clones de partidas.
	 */
	private List<Partida> partidas;
	
	/**
	 * Partida en un estado inicial.
	 */
	private Partida partidaInicial;
	
	/**
	 * Inicializa la máquina del tiempo con la fecha actual y el tamaño del tablero.
	 * 
	 * @param fecha fecha actual
	 * @param filas número de filas del tablero utilizado
	 * @param columnas número de columnas del tablero utilizado
	 */
	public MaquinaDelTiempoConPartidas (Date fecha, int filas, int columnas) {
		
		super(fecha,filas,columnas);
		partidas = new ArrayList<>();
		partidaInicial = new Partida(new Tablero(),new Caja (Color.BLANCO), new Caja (Color.NEGRO));
	}

	@Override
	public int consultarNumeroJugadasEnHistorico() {
		return partidas.size();
	}

	@Override
	public Partida consultarPartidaActual() {
		
		Partida partidaActual = new Partida(new Tablero(), new Caja(Color.BLANCO), new Caja(Color.NEGRO));
		
		if (!partidas.isEmpty()) {
			
			int indice = partidas.size() - 1;
			partidaActual = partidas.get(indice);
		}
		return partidaActual;
	}

	@Override
	public void deshacerJugada() {
		
		if (!partidas.isEmpty()) {
			
			int indice = partidas.size() - 1;
			partidas.remove(indice);
		}
	}

	@Override
	public void hacerJugada(int fila, int columna, Figura figura, Color color) throws CoordenadasIncorrectasException {
		
		if (!partidaInicial.consultarTablero().estaEnTablero(fila,columna)){
			throw new CoordenadasIncorrectasException("Coordenadas fuera de los límites del tablero.");
		}

		Partida partidaClon = clonarUltimaPartida();
		
		partidaClon.colocarPiezaEnTurnoActual(fila, columna, figura);
		partidaClon.cambiarTurno();
		partidas.add(partidaClon);
	}
	
	/**
	 * Devuelve un clon en profundidad de la partida actual.
	 * 
	 * @return clon profundo de la partida
	 */
	private Partida clonarUltimaPartida() {
				
		Partida partidaClon = null;
		
		if (!partidas.isEmpty()) {
			
			int indice = partidas.size() - 1;
			partidaClon = partidas.get(indice).clonar();
		}
		else {
			partidaClon = partidaInicial.clonar();
		}
		return partidaClon;
	}
}
