class Nodo {
    String valor;
    Nodo izquierdo;
    Nodo derecho;

    public Nodo(String valor) {
        this.valor = valor;
        this.izquierdo = null;
        this.derecho = null;
    }
}

public class ArbolBinario {
    public static void main(String[] args) {
        //CREANDO PADRES E HIJOS
        Nodo padre = new Nodo("Nodo Padre");
        padre.izquierdo = new Nodo("Nodo Hijo Izquierdo");
        padre.derecho = new Nodo("Nodo Hijo Derecho");

        System.out.println("Padre: " + padre.valor);
        System.out.println("Hijo Izquierdo: " + padre.izquierdo.valor);
        System.out.println("Hijo Derecho: " + padre.derecho.valor);
    }
}
