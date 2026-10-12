
package ni.edu.uam.practicahilos.hilos;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class ProcesadorController {

    @FXML
    private Label lblEstadoClientes;

    @FXML
    private Label lblPorcentajeClientes;

    @FXML
    private ProgressBar barraClientes;

    @FXML
    private Button btnIniciarClientes;

    @FXML
    private Button btnDetenerClientes;

    @FXML
    private Label lblEstadoProductos;

    @FXML
    private Label lblPorcentajeProductos;

    @FXML
    private ProgressBar barraProductos;

    @FXML
    private Button btnIniciarProductos;

    @FXML
    private Button btnDetenerProductos;

    @FXML
    private Button btnReiniciar;

    private Thread hiloClientes;
    private Thread hiloProductos;

    @FXML
    private void iniciarClientes() {

        if (hiloClientes != null && hiloClientes.isAlive()) {
            return;
        }

        barraClientes.setProgress(0);
        lblPorcentajeClientes.setText("0%");
        lblEstadoClientes.setText("Procesando clientes...");

        btnIniciarClientes.setDisable(true);
        btnDetenerClientes.setDisable(false);

        Runnable tareaClientes = () -> {
            try {
                for (int i = 1; i <= 100; i++) {

                    if (Thread.currentThread().isInterrupted()) {
                        throw new InterruptedException();
                    }

                    Thread.sleep(100);

                    final int avance = i;

                    Platform.runLater(() -> {
                        if (!Thread.currentThread().isInterrupted()) {
                            barraClientes.setProgress(avance / 100.0);
                            lblPorcentajeClientes.setText(avance + "%");
                        }
                    });
                }

                Platform.runLater(() -> {
                    lblEstadoClientes.setText("Clientes procesados");
                    btnIniciarClientes.setDisable(false);
                    btnDetenerClientes.setDisable(true);
                });

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();

                Platform.runLater(() -> {
                    lblEstadoClientes.setText("Proceso detenido");
                    btnIniciarClientes.setDisable(false);
                    btnDetenerClientes.setDisable(true);
                });
            }
        };

        hiloClientes = new Thread(tareaClientes);
        hiloClientes.setName("Hilo-Clientes");
        hiloClientes.setDaemon(true);
        hiloClientes.start();
    }

    @FXML
    private void detenerClientes() {

        if (hiloClientes != null && hiloClientes.isAlive()) {
            btnDetenerClientes.setDisable(true);
            lblEstadoClientes.setText("Deteniendo clientes...");
            hiloClientes.interrupt();
        }
    }

    @FXML
    private void iniciarProductos() {

        if (hiloProductos != null && hiloProductos.isAlive()) {
            return;
        }

        barraProductos.setProgress(0);
        lblPorcentajeProductos.setText("0%");
        lblEstadoProductos.setText("Procesando productos...");

        btnIniciarProductos.setDisable(true);
        btnDetenerProductos.setDisable(false);

        Runnable tareaProductos = () -> {
            try {
                for (int i = 1; i <= 50; i++) {

                    if (Thread.currentThread().isInterrupted()) {
                        throw new InterruptedException();
                    }

                    Thread.sleep(150);

                    final int avance = i;

                    Platform.runLater(() -> {
                        barraProductos.setProgress(avance / 50.0);
                        lblPorcentajeProductos.setText((avance * 100 / 50) + "%");
                    });
                }

                Platform.runLater(() -> {
                    lblEstadoProductos.setText("Productos procesados");
                    btnIniciarProductos.setDisable(false);
                    btnDetenerProductos.setDisable(true);
                });

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();

                Platform.runLater(() -> {
                    lblEstadoProductos.setText("Proceso detenido");
                    btnIniciarProductos.setDisable(false);
                    btnDetenerProductos.setDisable(true);
                });
            }
        };

        hiloProductos = new Thread(tareaProductos);
        hiloProductos.setName("Hilo-Productos");
        hiloProductos.setDaemon(true);
        hiloProductos.start();
    }

    @FXML
    private void detenerProductos() {

        if (hiloProductos != null && hiloProductos.isAlive()) {
            btnDetenerProductos.setDisable(true);
            lblEstadoProductos.setText("Deteniendo productos...");
            hiloProductos.interrupt();
        }
    }

    @FXML
    private void reiniciarProceso() {

        if ((hiloClientes != null && hiloClientes.isAlive()) ||
                (hiloProductos != null && hiloProductos.isAlive())) {
            return;
        }

        barraClientes.setProgress(0);
        lblPorcentajeClientes.setText("0%");
        lblEstadoClientes.setText("Esperando inicio");

        barraProductos.setProgress(0);
        lblPorcentajeProductos.setText("0%");
        lblEstadoProductos.setText("Esperando inicio");

        btnIniciarClientes.setDisable(false);
        btnDetenerClientes.setDisable(true);

        btnIniciarProductos.setDisable(false);
        btnDetenerProductos.setDisable(true);
    }

    public void detenerAlCerrar() {

        if (hiloClientes != null && hiloClientes.isAlive()) {
            hiloClientes.interrupt();
        }

        if (hiloProductos != null && hiloProductos.isAlive()) {
            hiloProductos.interrupt();
        }
    }
}
