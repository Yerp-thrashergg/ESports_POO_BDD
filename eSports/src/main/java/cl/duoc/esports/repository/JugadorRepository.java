package cl.duoc.esports.repository;

import cl.duoc.esports.model.Casual;
import cl.duoc.esports.model.Competitivo;
import cl.duoc.esports.model.Jugador;
import cl.duoc.esports.utils.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class JugadorRepository {
    private Connection coneLocal;

    public JugadorRepository() {}

    //  Agregar Jugador:
    public boolean agregarJugador(Jugador j) throws SQLException {
        boolean inserto = false;
        try{
            coneLocal = new Conexion().getCone();
            String sql  = "INSERT INTO jugador ( nickname, videojuego, nivel, puntos ) VALUES (?,?,?,?)";
            PreparedStatement ps = coneLocal.prepareStatement(sql);
            ps.setString(1,j.getNickname());
            ps.setString(2,j.getVideojuego());
            ps.setInt(3,j.getNivel());
            ps.setInt(4,j.getPuntos());
            int res = ps.executeUpdate();
            if (res == 1) {
                inserto = true;
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }finally{
            coneLocal.close();
        }
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
