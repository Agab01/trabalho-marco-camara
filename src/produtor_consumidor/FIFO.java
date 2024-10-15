package produtor_consumidor;

public class FIFO {

	// VARIAVEIS
	private int cabeca, cauda, qtd_elementos; // índices e contador
	private int fila[]; // vetor de elementos
	private int tamanho;

	// METODOS
	public int getTamanho() {
		return tamanho;
	}

	// constructor -- determina capacidade da fila / inicializando fila
	public FIFO(int tamanho) {
		this.tamanho = tamanho;
		this.fila = new int[tamanho]; // cria vetor com capacidade = 'tamanho'
		cauda = 0;
		cabeca = 0;
		qtd_elementos = 0;
	}

	// Função de inserção na fila
	public void enqueue(int elemento) {
		this.fila[this.cauda] = elemento; // insere elemento na cauda
		if (this.cauda == this.fila.length - 1) {
			this.cauda = 0; // circula e volta para o início
		} else {
			this.cauda += 1; // incrementa índice da cauda
		}
		this.qtd_elementos++; // incrementa a quantidade de elementos
	}

	// Função de remoção da fila
	public int dequeue() {
		int valor = this.fila[this.cabeca]; // extrai o primeiro da fila
		if (this.cabeca == this.fila.length - 1) {
			this.cabeca = 0; // circula e volta para o início
		} else {
			this.cabeca += 1; // incrementa índice da cabeça
		}
		this.qtd_elementos--; // decrementa a quantidade de elementos
		return valor;
	}

	// Função que indica se a fila está cheia
	public boolean cheia() {
		return this.qtd_elementos == this.fila.length;
	}

	// Função que indica se a fila está vazia
	public boolean vazia() {
		return this.qtd_elementos == 0;
	}
}
