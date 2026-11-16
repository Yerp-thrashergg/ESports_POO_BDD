package cl.duoc.esports.repository;

import cl.duoc.esports.model.Jugador;

import java.sql.Connection;
import java.util.ArrayList;

public class JugadorRepository {

    private Connection coneLocal;

    public JugadorRepository() {}

    //  Agregar Jugador:
    public boolean agregarJugador(){
        boolean inserto = false;
        return inserto;
    }

    // Eliminar Jugador:}
    public boolean eliminarJugador(){
        boolean eliminar = false;
        return eliminar;
    }

    // Ver Jugadores:
    public ArrayList<Jugador>verJugadores(){
        ArrayList<Jugador> lista = new ArrayList<>();
        return lista;
    }
}
