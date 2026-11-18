package cl.duoc.esports.controller;

import cl.duoc.esports.model.Casual;
import cl.duoc.esports.model.Competitivo;
import cl.duoc.esports.model.Jugador;
import cl.duoc.esports.repository.JugadorRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class JugadorController {
    public Label labelTitulo;
    public Label labelNickname;
    public Label labelVideojuego;
    public Label labelNivel;
    public Label labelTipoJugador;
    public Label labelCantVicPartJug;
    public TextField textFieldNickname;
    public TextField textFieldNivel;
    public TextField textFieldCantVicPartJug;
    public ComboBox<String> comboBoxVideojuego;
    public ComboBox<String> comboBoxTipoJugador;
    public Button buttonRegistrar;
    public Button buttonEliminar;
    public TableView<Jugador> tableViewJugadores;
    public TableColumn<Jugador,String> tableColumnNickname;
    public TableColumn<Jugador,String> tableColumnVideojuego;
    public TableColumn<Jugador,Integer> tableColumnNivel;
    public TableColumn<Jugador,Integer> tableColumnPuntos;
    public Label labelMensaje;

    @FXML
    public void initialize(){
        comboBoxVideojuego.getItems().addAll("Counter Strike", "Valorant", "Minecraft");
        comboBoxTipoJugador.getItems().addAll("Casual","Competitivo");

        comboBoxTipoJugador.setOnAction(evento -> {
            textFieldCantVicPartJug.setDisable(false);
            String tipoJugador = comboBoxTipoJugador.getValue();
            if("Casual".equals(tipoJugador)){
                labelCantVicPartJug.setText("Partidas jugadas");
                textFieldCantVicPartJug.setPromptText("ingrese partidas jugadas");
            }else{
                labelCantVicPartJug.setText("Cantidad de victorias");
                textFieldCantVicPartJug.setPromptText("ingrese cantidad de victorias");
            }
        });
    }

    ArrayList<Jugador> jugadores = new ArrayList<>();

    @FXML
    protected void registrarJugador() {
        try {
            if(comboBoxTipoJugador.getValue()==null){
                labelMensaje.setText("Debe seleccionar un tipo de jugador.");
                return;
            }
            if(comboBoxVideojuego.getValue()==null){
                labelMensaje.setText("Debe seleccionar un videojuego.");
                return;
            }
            Jugador j;
            String nickname = textFieldNickname.getText();
            String videojuego = comboBoxVideojuego.getValue();
            int nivel = Integer.parseInt(textFieldNivel.getText());
            int cantVicPartJug = Integer.parseInt(textFieldCantVicPartJug.getText());

            String tipo = comboBoxTipoJugador.getValue();
            if("Casual".equals(tipo)){
                j = new Casual(nickname,videojuego,nivel,cantVicPartJug);
            }else{
                j = new Competitivo(nickname,videojuego,nivel,cantVicPartJug);
            }
            boolean res = new JugadorRepository().agregarJugador(j);
            if(res){
                mostrarMensaje("Jugador registrado");
                cargarTabla();
                limpiar();
            }else{
                mostrarMensaje("Jugador no registrado");
            }
        }catch (NumberFormatException ex){
            labelMensaje.setText("Debe ingresar números para campos numéricos.");
        }catch (Exception ex){
            labelMensaje.setText(ex.getMessage());
        }
    }

    @FXML
    protected void eliminarJugador() {
        try {
            Jugador j = tableViewJugadores.getSelectionModel().getSelectedItem();
            if(j==null){
                labelMensaje.setText("Debe seleccionar un jugador de la tabla.");
                return;
            }
            jugadores.remove(j);
            cargarTabla();
            limpiar();
            mostrarMensaje("Jugador eliminado.");
        }catch (Exception ex){
            labelMensaje.setText( ex.getMessage() );
        }
    }

    //métodos auxiliares:
    private void limpiar(){
        textFieldNickname.clear();
        textFieldNivel.clear();
        comboBoxVideojuego.setValue(null);
        comboBoxTipoJugador.setValue(null);
        labelCantVicPartJug.setText("-");
        textFieldCantVicPartJug.clear();
        textFieldCantVicPartJug.setDisable(true);
        labelMensaje.setText("-");
    }

    private void cargarTabla(){
        tableColumnNickname.setCellValueFactory(new PropertyValueFactory<>("nickname"));
        tableColumnVideojuego.setCellValueFactory(new PropertyValueFactory<>("videojuego"));
        tableColumnNivel.setCellValueFactory(new PropertyValueFactory<>("nivel"));
        tableColumnPuntos.setCellValueFactory(new PropertyValueFactory<>("puntos"));

        ObservableList<Jugador> data = FXCollections.observableArrayList(jugadores);
        tableViewJugadores.setItems(data);
    }

    private void mostrarMensaje(String mensaje){
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setContentText(mensaje);
        alerta.show();
    }

}
