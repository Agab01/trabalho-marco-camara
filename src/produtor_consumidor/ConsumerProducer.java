package produtor_consumidor;

import java.util.Random;

public class ConsumerProducer {

	// VARIAVEIS
	FIFO fila; // fila que será usada como memória compartilhada entre threads consumidor e
				// produtor
	private int elementosNoBuffer = 0; // contador para elementos no buffer
	private Random random = new Random(); // gera números aleatórios

	// METODOS

	// constructor
	public ConsumerProducer(FIFO fila) {
		this.fila = fila; // variável fila referencia uma fila passada como argumento
	}

	// produtor
	public void produce() {
		while (true) // executa em loop
		{
			synchronized (this) // acesso atômico à fila
			{
				while (this.fila.cheia()) // se fila cheia
				{
					try {
						wait(); // bloqueia o thread
					} catch (InterruptedException e) {
					}
				}

				// Se houver espaço na fila
				int espacoDisponivel = 10 - elementosNoBuffer; // checa espaço disponível no buffer
				int qtdProduzir = random.nextInt(Math.min(espacoDisponivel, 10)) + 1; // produz entre 1 e o espaço
																						// disponível no buffer
				System.out.println("Produtor vai produzir: " + qtdProduzir);

				for (int i = 0; i < qtdProduzir && !this.fila.cheia(); i++) {
					int valorProduzido = random.nextInt(100); // Produz um valor aleatório entre 0 e 99
					this.fila.enqueue(valorProduzido); // insere o valor aleatório na fila
					elementosNoBuffer++; // incrementa o contador de elementos no buffer
				}
				System.out.printf("Buffer após produção: " + "*".repeat(elementosNoBuffer) + "\n\n");

				notify(); // notifica que há elementos na fila
			}

			try {
				int tempoEspera = random.nextInt(2000) + 500; // espera aleatória entre 500ms e 2500ms
				Thread.sleep(tempoEspera);
			} catch (InterruptedException e) {
			}
		}
	}

	// consumidor
	public void consume() {
		while (true) {
			synchronized (this) // inicia operação atômica
			{
				while (this.fila.vazia()) // se fila vazia
				{
					try {
						wait(); // bloqueia o thread
					} catch (InterruptedException e) {
					}
				}

				// Se houver elementos no buffer
				int qtdConsumir = random.nextInt(Math.min(elementosNoBuffer, 10)) + 1; // consome entre 1 e a quantidade
																						// disponível
				System.out.println("Consumidor vai consumir: " + qtdConsumir);

				for (int i = 0; i < qtdConsumir && !this.fila.vazia(); i++) {
					int valorConsumido = this.fila.dequeue(); // retira um valor da fila
					elementosNoBuffer--; // decrementa o contador de elementos no buffer
				}
				System.out.printf("Buffer após consumo: " + "*".repeat(elementosNoBuffer) + "\n\n");

				notify(); // notifica que há espaço na fila
			}

			try {
				int tempoEspera = random.nextInt(2000) + 500; // espera aleatória entre 500ms e 2500ms
				Thread.sleep(tempoEspera);
			} catch (InterruptedException e) {
			}
		}
	}
}
