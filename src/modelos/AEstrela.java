/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author João Pedro de Souza Jardim da Costa
 */
public class AEstrela {

    List<Aresta> abertos, fechados;
    Grafo problema;
    Grafo solucao;

    public AEstrela(Grafo problema) {
        abertos = new LinkedList<>();
        fechados = new LinkedList<>();
        this.problema = problema;
        solucao = new Grafo();
    }

    public void solucionar(String nomeA, String nomeB) {
        long tempoInicial= System.nanoTime(),tempoFinal;
        Nodo nodoInicial = problema.getNodo(nomeA);
        Nodo nodoFinal = problema.getNodo(nomeB);
        if (nodoInicial != null && nodoFinal != null) {
        Aresta arestaMenor = new Aresta(0, nodoInicial);//inicializa com o nó incial
        abertos.add(arestaMenor);//adiciona o inicial a lista de abertos
        solucao.addNodo(arestaMenor.getNodo().getNome(),arestaMenor.getNodo().getHeuristica());//adiciona o inicial ao grafo solução
        
            while (arestaMenor.getNodo() != nodoFinal) {
                addFilhos(arestaMenor);//adiciona seus filhos a lista de abertos
                fechados.add(arestaMenor);//adiciona o elemento a lista de fechados
                abertos.remove(arestaMenor);//remove o elemento fechado da lista de abertos
                arestaMenor.getNodo().setFechado(true);//sinaliza que o elemento está na lista de fechados
                arestaMenor.getNodo().setAberto(false);//sinaliza que o elemento não está na lista de abertos
                arestaMenor = abertos.get(0);//pega o primeiro elemento da lista(o de menor custo)
            }
            if(arestaMenor.getNodo().getNome().equals(nomeB))
            {
                System.out.println("Solução:");
                solucao.imprimeCaminho(nomeB);
            }
            else
            {
                System.out.println("Não há conexão entre os dois nós");
            }
        }
        else
        {
            System.out.println("Um dos nós fornecidos não existe");
        }
        tempoFinal = System.nanoTime();
        System.out.println("Solução encontrada em: " + (tempoFinal - tempoInicial) + "ns");
    }

    //adiciona os nos filhos à lista de abertos na ordem ascendente
    private void addFilhos(Aresta arestaPai) {
        //pega todas os nós aos quais ele está conectado
        List<Aresta> arestasFilhas = arestaPai.getNodo().getArestas();
        Iterator<Aresta> arestasIterator = arestasFilhas.iterator();

        while (arestasIterator.hasNext()) {
            Aresta arestaAtual = arestasIterator.next();

            //cria nova aresta para lista de abertos
            int novoPeso = arestaAtual.getPeso() + arestaPai.getPeso();// peso é igual ao pesso do pai + o do filho
            Nodo nodo = arestaAtual.getNodo();
            Aresta novaAresta = new Aresta(novoPeso, nodo);

            //pega posicao a qual ela será adicionada
            int posicaoAdicionar = findPosition(novaAresta);

            //se o nó não estiver na lista de fechados e não estiver na lista de abertos
            if (!novaAresta.getNodo().isFechado() && !novaAresta.getNodo().isAberto()) {
                abertos.add(posicaoAdicionar, novaAresta);
                solucao.addNodo(novaAresta.getNodo().getNome(),novaAresta.getNodo().getHeuristica());//adiciona o nó ao grafo solução
                solucao.addArestaUnica(arestaPai.getNodo().getNome(),novaAresta.getNodo().getNome(),  novoPeso);//adiciona aresta do nó com o pai ao grafo solução
                nodo.setAberto(true);//sinaliza que o elemento está na lista de abertos
            } // se o nó estiver na lista de aberto mas não está na lista de fechados, então ele já está no grafo solução mas não possui a aresta.
            else if (!novaAresta.getNodo().isFechado()) 
            //else if(!novaAresta.getNodo().isFechado() && novaAresta.getNodo().isAberto()) possuem a mesma funcionalidade neste contexto
            {
                int posicaoConcorrente = findEquals(novaAresta);
                //se o no antigo está em uma posicao maior, significa que ele custa mais que o novo e precisa ser removido
                //caso contrário, não faz nada
                if (posicaoConcorrente > posicaoAdicionar) {
                    //deleta antiga(PRECISA REMOVER ANTES DE ADICIONAR, OU deletará o nó incorreto)
                    abertos.remove(abertos.get(posicaoConcorrente));
                    //adiciona nova aos abertos
                    abertos.add(posicaoAdicionar, novaAresta);
                    
                    solucao.removeTodasArestas(novaAresta.getNodo().getNome());//poda a aresta antiga da solução
                    solucao.addArestaUnica(arestaPai.getNodo().getNome(),novaAresta.getNodo().getNome(),  novoPeso);//adiciona aresta do nó com o pai ao grafo solução
                }
            }
        }
    }

    //acha a posição a qual o novo nó será adicionado
    private int findPosition(Aresta novaAresta) {
        Iterator<Aresta> arestasIterator = abertos.iterator();
        int posicaoAdicionar = 0;
        while (arestasIterator.hasNext()) {
            Aresta arestaAtual = arestasIterator.next();
            //se o nó atual tiver um custo menor e não for fechado
            if (!arestaAtual.getNodo().isFechado() && (arestaAtual.getNodo().getHeuristica() + arestaAtual.getPeso() > novaAresta.getNodo().getHeuristica() + novaAresta.getPeso())) {
                break;
            }
            posicaoAdicionar++;
        }
        return posicaoAdicionar;
    }

    //acha a posição repetida para escolher qual podar
    private int findEquals(Aresta novaAresta) {
        Iterator<Aresta> arestasIterator = abertos.iterator();
        int posicaoAdicionar = 0;
        while (arestasIterator.hasNext()) {
            Aresta arestaAtual = arestasIterator.next();
            if (arestaAtual.getNodo().equals(novaAresta.getNodo())) {
                break;
            }
            posicaoAdicionar++;
        }
        return posicaoAdicionar;
    }
}
