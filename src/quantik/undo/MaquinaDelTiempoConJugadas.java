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
 * Almacena el histórico de las jugadas realizadas según se vayan realizando.
 * 
 * @author <a href="apo1004@alu.ubu.es"> Amanda Pérez Olmos </a>
 * @version 1.0
 */
public class MaquinaDelTiempoConJugadas extends MaquinaDelTiempoAbstracta {

	/**
	 * Histórico de jugadas realizadas.
	 */
	private List<Jugada> jugadas;
	
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
	public MaquinaDelTiempoConJugadas (Date fecha, int filas, int columnas) {
		
		super(fecha,filas,columnas);
		jugadas = new ArrayList<>();
		partidaInicial = new Partida(new Tablero(),new Caja (Color.BLANCO), new Caja (Color.NEGRO));
	}

	@Override
	public int consultarNumeroJugadasEnHistorico() {
		return jugadas.size();
	}

	@Override
	public Partida consultarPartidaActual() {
		
		Partida partidaActual = new Partida(new Tablero(), new Caja(Color.BLANCO), new Caja(Color.NEGRO));
		
		if (!jugadas.isEmpty()) {
						
			for (Jugada j : jugadas) {
							
				try {
					partidaActual.colocarPiezaEnTurnoActual(j.consultarFila(), j.consultarColumna(), j.consultarFigura());
				}
				catch (CoordenadasIncorrectasException ex) {
					throw new RuntimeException("Error grave en el código.",ex);
				}
				partidaActual.cambiarTurno();
			}
		}
		return partidaActual;
	}

	@Override
	public void deshacerJugada() {
		
		if (!jugadas.isEmpty()) {
			
			int indice = jugadas.size() - 1;
			jugadas.remove(indice);
		}
	}

	@Override
	public void hacerJugada(int fila, int columna, Figura figura, Color color) throws CoordenadasIncorrectasException {
		
		if (!partidaInicial.consultarTablero().estaEnTablero(fila,columna)){
			throw new CoordenadasIncorrectasException("Coordenadas fuera de los límites del tablero.");
		}
		jugadas.add(new Jugada(fila,columna,figura,color));		
	}
}
