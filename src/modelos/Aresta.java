/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author João Pedro de Souza Jardim da Costa
 */
public class Aresta {
    int peso;
    Nodo nodo;

    public Aresta(int peso, Nodo nodo) {
        this.peso = peso;
        this.nodo = nodo;
    }

    public int getPeso() {
        return peso;
    }

    public Nodo getNodo() {
        return nodo;
    }
    
}
