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
public class Grafo {
    List<Nodo> nodos;
    int quantNodos;

    public Grafo() {
        nodos = new LinkedList<>();
        quantNodos =0;
    }
    public boolean addNodo(String nome, int heuristica)
    {
        Iterator<Nodo> nodosIterator = nodos.iterator();
        while(nodosIterator.hasNext())
        {
            Nodo nodoAtual = nodosIterator.next();
            if(nome.equals(nodoAtual.getNome()))
            {
                return false;
            }
        }
        quantNodos++;
        nodos.add(new Nodo(nome,heuristica));
        return true;
    }
    public boolean removeNodo(String nome)
    {
        Iterator<Nodo> nodosIterator = nodos.iterator();
        Nodo nodoRemover=null;
        while(nodosIterator.hasNext())
        {
            Nodo nodoAtual = nodosIterator.next();
            
            if(nome.equals(nodoAtual.getNome()))
            {
                nodoRemover = nodoAtual;
            }
        }
        if(nodoRemover==null)
        {
            return false;
        }
        else
        {
            nodosIterator = nodos.iterator();
            while(nodosIterator.hasNext())
            {
                Nodo nodoAtual = nodosIterator.next();
                nodoAtual.removeAresta(nodoRemover);
            }
            quantNodos--;
            nodos.remove(nodoRemover);
            return true;
        }
        
    }

    public boolean addArestaUnica(String nomeA,String nomeB, int peso)
    {
        Iterator<Nodo> nodosIterator = nodos.iterator();
        Nodo nodoA=null,nodoB=null;
        while(nodosIterator.hasNext())
        {
            Nodo nodoAtual = nodosIterator.next();
            if(nomeA.equals(nodoAtual.getNome()))
            {
                nodoA = nodoAtual;
            }
            else if(nomeB.equals(nodoAtual.getNome()))
            {
                nodoB = nodoAtual;
            }
        }
        if(nodoA==null||nodoB==null)//se um dos dois nodos não existem, retorna que a operação foi incorreta
        {
            return false;
        }
        else
        {
            nodoA.addAresta(nodoB, peso);
            return true;
        }
    }
     public boolean addArestaDupla(String nomeA,String nomeB, int peso)
    {
        Iterator<Nodo> nodosIterator = nodos.iterator();
        Nodo nodoA=null,nodoB=null;
        while(nodosIterator.hasNext())
        {
            Nodo nodoAtual = nodosIterator.next();
            if(nomeA.equals(nodoAtual.getNome()))
            {
                nodoA = nodoAtual;
            }
            else if(nomeB.equals(nodoAtual.getNome()))
            {
                nodoB = nodoAtual;
            }
        }
        if(nodoA==null||nodoB==null)//se um dos dois nodos não existem, retorna que a operação foi incorreta
        {
            return false;
        }
        else
        {
            nodoA.addAresta(nodoB, peso);
            nodoB.addAresta(nodoA, peso);
            return true;
        }
    }
    public boolean removeTodasArestas(String nomeA)
    {
        Iterator<Nodo> nodosIterator = nodos.iterator();
        Nodo nodoA=null;
        while(nodosIterator.hasNext())
        {
            Nodo nodoAtual = nodosIterator.next();
            if(nomeA.equals(nodoAtual.getNome()))
            {
                nodoA = nodoAtual;
            }
        }
        if(nodoA==null)
        {
            return false;
        }
        else
        {
            nodosIterator = nodos.iterator();
            while(nodosIterator.hasNext())
            {
                Nodo nodoAtual = nodosIterator.next();
                nodoAtual.removeAresta(nodoA);
            }
            return true;
        }
    }
    public boolean removeArestaDupla(String nomeA,String nomeB)
    {
        Iterator<Nodo> nodosIterator = nodos.iterator();
        Nodo nodoA=null,nodoB=null;
        while(nodosIterator.hasNext())
        {
            Nodo nodoAtual = nodosIterator.next();
            if(nomeA.equals(nodoAtual.getNome()))
            {
                nodoA = nodoAtual;
            }
            else if(nomeB.equals(nodoAtual.getNome()))
            {
                nodoB = nodoAtual;
            }
        }
        if(nodoA==null||nodoB==null)
        {
            return false;
        }
        else
        {
            nodoA.removeAresta(nodoB);
            nodoB.removeAresta(nodoA);
            return true;
        }
    }
    public boolean removeArestaUnica(String nomeA,String nomeB)
    {
        Iterator<Nodo> nodosIterator = nodos.iterator();
        Nodo nodoA=null,nodoB=null;
        while(nodosIterator.hasNext())
        {
            Nodo nodoAtual = nodosIterator.next();
            if(nomeA.equals(nodoAtual.getNome()))
            {
                nodoA = nodoAtual;
            }
            else if(nomeB.equals(nodoAtual.getNome()))
            {
                nodoB = nodoAtual;
            }
        }
        if(nodoA==null||nodoB==null)
        {
            return false;
        }
        else
        {
            nodoA.removeAresta(nodoB);
            return true;
        }
    }
    public List<Aresta> getArestas(String nome)
    {
        Iterator<Nodo> nodosIterator = nodos.iterator();
        while(nodosIterator.hasNext())
        {
            Nodo nodoAtual = nodosIterator.next();
            if(nome.equals(nodoAtual.getNome()))
            {
                return nodoAtual.getArestas();
            }
        }
        return null;
    }
    public Nodo getNodo(String nome)
    {
        Iterator<Nodo> nodosIterator = nodos.iterator();
        while(nodosIterator.hasNext())
        {
            Nodo nodoAtual = nodosIterator.next();
            if(nome.equals(nodoAtual.getNome()))
            {
                return nodoAtual;
            }
        }
        return null;
    }
    public List<Nodo> getListaNodo()
    {
        return nodos;
    }
    public void imprimeGrafo()
    {
        Iterator<Nodo> nodosIterator = nodos.iterator();
        System.out.println("Grafo:");
        while(nodosIterator.hasNext())
        {
            Nodo nodoAtual = nodosIterator.next();
            int heuristica = nodoAtual.getHeuristica();
            System.out.print(nodoAtual.getNome() + "(h=" + heuristica + ")" );
            nodoAtual.imprimeArestas();
        }
        System.out.print('\n');
    }
    public boolean imprimeCaminho(String destino)
    {
        Iterator<Nodo> nodosIterator = nodos.iterator();
        Nodo nodoDestino=null;
        while(nodosIterator.hasNext())
        {
            Nodo nodoAtual = nodosIterator.next();
            if(destino.equals(nodoAtual.getNome()))
            {
                nodoDestino = nodoAtual;
            }
        }
        if(nodoDestino==null)
        {
            return false;
        }
        else
        {
            String solucao = caminho(nodoDestino);
            System.out.println(solucao);
            return true;
        }
    }
    private String caminho(Nodo nodoDestino)
    {
        Nodo nodoPai = getPai(nodoDestino);
        if(nodoPai==null)
        {
            return nodoDestino.getNome();
        }
        else
        {
            return caminho(nodoPai) + ", " + nodoDestino.getNome();
        }
    }
    //funciona apenas com poda
    private Nodo getPai(Nodo nodoFilho)
    {
        Iterator<Nodo> nodosIterator = nodos.iterator();
        while(nodosIterator.hasNext())
        {
            Nodo nodoAtual = nodosIterator.next();
            if(nodoAtual.containsAresta(nodoFilho))
            {
                return nodoAtual;
            }
        }
        return null;
    }
}
