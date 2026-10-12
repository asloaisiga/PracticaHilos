module ni.edu.uam.practicahilos.hilos {
    requires javafx.controls;
    requires javafx.fxml;


    opens ni.edu.uam.practicahilos.hilos to javafx.fxml;
    exports ni.edu.uam.practicahilos.hilos;
}