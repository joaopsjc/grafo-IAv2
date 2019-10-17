/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.aestrela;

import modelos.AEstrela;
import modelos.Grafo;

/**
 *
 * @author Andre William
 */
public class IAAestrela {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Grafo problema = new Grafo();
        problema.addNodo("Arad", 366);
        problema.addNodo("Oradea", 380);
        problema.addNodo("Zerind", 374);
        problema.addNodo("Timisoara", 329);
        problema.addNodo("Lugoj", 244);
        problema.addNodo("Mehadia", 241);
        problema.addNodo("Dobreta", 242);
        problema.addNodo("Craiova", 160);
        problema.addNodo("Sibiu", 253);
        problema.addNodo("Rimnicu Vilcea", 193);
        problema.addNodo("Fagaras", 178);
        problema.addNodo("Pitesti", 98);
        problema.addNodo("Bucharest", 0);
        
        problema.addArestaDupla("Lugoj", "Mehadia",70);
        problema.addArestaDupla("Oradea", "Zerind", 71);
        problema.addArestaDupla("Arad", "Zerind", 75);
        problema.addArestaDupla("Dobreta", "Mehadia",75);
        problema.addArestaDupla("Rimnicu Vilcea", "Sibiu",80);
        problema.addArestaDupla("Rimnicu Vilcea", "Pitesti",97);
        problema.addArestaDupla("Fagaras", "Sibiu", 99);
        problema.addArestaDupla("Pitesti", "Bucharest", 101);
        problema.addArestaDupla("Lugoj", "Timisoara",111);
        problema.addArestaDupla("Arad", "Timisoara",118);
        problema.addArestaDupla("Dobreta", "Craiova",120);
        problema.addArestaDupla("Pitesti", "Craiova",138);
        problema.addArestaDupla("Arad", "Sibiu", 140);
        problema.addArestaDupla("Rimnicu Vilcea", "Craiova",146);
        problema.addArestaDupla("Oradea", "Sibiu", 151);
        problema.addArestaDupla("Fagaras", "Bucharest", 211);
        
        problema.imprimeGrafo();
        
        AEstrela busca = new AEstrela(problema);
        busca.solucionar("Arad", "Bucharest");
        
    }
    
}
