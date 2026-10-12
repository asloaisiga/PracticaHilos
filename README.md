# JavaFX - Práctica de Hilos

## Descripción
Aplicación de escritorio desarrollada en JavaFX que simula el procesamiento de registros de clientes y productos utilizando programación concurrente.

El objetivo es ejecutar dos procesos independientes sin bloquear la interfaz gráfica.

## Tecnologías utilizadas
- Java 21
- JavaFX
- Maven
- FXML
- IntelliJ IDEA

## Funcionalidades
- Procesamiento de 100 registros de clientes.
- Procesamiento de 50 registros de productos.
- Dos hilos independientes utilizando Thread y Runnable.
- Barras de progreso individuales.
- Botones para iniciar y detener cada proceso.
- Opción para reiniciar los procesos.
- Actualización de la interfaz mediante Platform.runLater().

## Funcionamiento
Cada proceso utiliza un hilo independiente para simular el procesamiento de registros mediante Thread.sleep().

Se utiliza Platform.runLater() para actualizar las barras de progreso y los porcentajes sin bloquear la interfaz.

El método interrupt() permite solicitar la detención de los hilos.

## Resultado
Se logró implementar una aplicación que ejecuta dos tareas concurrentes y mantiene la interfaz gráfica disponible durante el procesamiento.
