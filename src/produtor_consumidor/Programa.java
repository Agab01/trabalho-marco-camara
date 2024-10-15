package produtor_consumidor;

public class Programa {

	public static void main(String[] args) throws InterruptedException {

		// OBJETOS
		FIFO fila = new FIFO(10); // fila de tamanho 10

		ConsumerProducer programa = new ConsumerProducer(fila); // fila é passada como buffer para novo Objeto ConsumerProducer

		// Cria as threads produtor e consumidor
		Thread produtor = new Thread(() -> programa.produce());
		Thread consumidor = new Thread(() -> programa.consume());

		// INICIA PROGRAMA
		produtor.start();
		consumidor.start();

		// main abdica do controle - aguarda execução das threads
		produtor.join();
		consumidor.join();
	}
}
