package com.uefs.br.jogopaciencia.models;

import com.uefs.br.jogopaciencia.interfaces.IHome;
/**
 *
 * @author Mateus Guimarães e Karol Lima
 */
final public class NoCarta {
    private int numero;
    private Naipe naipe;
    
    private NoCarta prox = null;
    private boolean aberta = false;
    private boolean draggable = false;
    
    
    /**
     * Instancia da classe pai (home), pode ser uma ListaHome ua PilhaHome ou Baralho
     */
    private IHome home = null;
    
    public NoCarta(int numero, Naipe naipe) {
        this.numero = numero;
        this.naipe = naipe;
       
    }
    
    public int getCountProx() {
        int totalProx = 0;
        NoCarta aux = this;
        while(aux.getProx() != null) {
            totalProx++;
            aux = aux.getProx();
        }
        
        return totalProx;
    }
    
    public NoCarta getProx() {
        return this.prox;
    }
    
    public void setProx(NoCarta prox) {
        this.prox = prox;
    }
    
    
    public boolean isDraggable() {
        return this.draggable;
    }
    
    
    
    public void setDraggable(boolean draggable) {
        this.draggable = draggable;
    }
    
    
    
    public int getNumero() {
        return this.numero;
    }
    
    /**
     * Recupera a representação do número da carta, por ex:
     * Ao invés de 13, 12, 11, 1 será retornado K, Q, J, A
     * @return String
     */
    public String getNumRep() {
        String ret;
        switch(this.numero) {
            case 1:
                ret = "A";
                break;
            case 11:
                ret = "J";
                break;
            case 12:
                ret = "Q";
                break;
            case 13:
                ret = "K";
                break;
            default: 
                ret = String.valueOf(this.numero);
                break;
        }
        
        return ret;
    }
    
    public String getNaipeRep() {
        String ret;
        switch(this.naipe.getNaipe()) {
            case COPAS:
                ret = "c";
                break;
            case ESPADAS:
                ret = "e";
                break;
            case OURO:
                ret = "o";
                break;
            case PAUS:
            default: 
                ret = "p";
                break;
        }
        
        return ret;
    }

    public Naipe getNaipe() {
        return this.naipe;
    }
    
    /**
     * Mostra uma representação do objeto da carta no padrão: "A de COPAS"
     * @return String
     */
    @Override
    public String toString() {
        return this.getNumRep() + " de " + this.getNaipe().getNaipe().name();
    }
    
    /**
     * Seta a home da carta (e também de todos as cartas filhas).
     * Pode ser uma ListaHome, PilhaHome ou Baralho
     * @param home 
     */
    public void setHome(IHome home) {
        this.home = home;
        
        if(this.getProx() != null)
            this.getProx().setHome(home);
    }
    
    public IHome getHome() {
        return this.home;
    }
}