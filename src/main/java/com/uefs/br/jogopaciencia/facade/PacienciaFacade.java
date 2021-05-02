/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uefs.br.jogopaciencia.facade;

import com.uefs.br.jogopaciencia.models.Baralho;
import com.uefs.br.jogopaciencia.models.ListaHome;
import com.uefs.br.jogopaciencia.models.MonteHome;
import com.uefs.br.jogopaciencia.models.NoCarta;
import com.uefs.br.jogopaciencia.models.PilhaHome;
import javax.swing.JOptionPane;

/**
 *
 * @author matee
 */
public class PacienciaFacade {
    private Baralho baralho;
    private PilhaHome[] pilhas = new PilhaHome[4];
    private ListaHome[] tableaus = new ListaHome[7];
    private MonteHome monte;
    
    
    public void iniciarJogo(){
        baralho = new Baralho();
        for (int i=0; i <= 3; i++){
            pilhas[i] = new PilhaHome("Pilha "+i);
        }
        
        this.iniciarMonte();
    }
   
    
     public void iniciarMonte() {
        // Separa os 8 montes de 3 cartas que não vão pras listas
        monte = new MonteHome();
        for(int j = 0; j < 24; j++) {
            NoCarta carta = this.baralho.retiraCartaTopo();
            monte.inserir(carta);
        }
    }
     
    private void preencherTableaus() {
        for (int i = 0; i < 7; i++) {
            ListaHome lista = new ListaHome("Lista " + (i + 1));

            // Retira do baralho e receberNo nas listas de cartas
            for (int j = 0; j < (i + 1); j++) {
                NoCarta nc = this.baralho.retiraCartaTopo();
                nc.setHome(lista);
                lista.inserir(nc);
            }

            this.tableaus[i] = lista;
        }
    }
    
    /**
     * Valida se a carta1 é a sequencia da carta2 (crescentemente).
     * 
     * @param carta1
     * @param carta2
     * @return 
     */
    public static boolean cartaSequenciaValida(NoCarta carta1, NoCarta carta2) {
        if (carta1 == null || carta2 == null)
            return false;

        return ((carta1.getNumero() == carta2.getNumero() + 1)
                && (carta1.getNaipe().getCor() != carta2.getNaipe().getCor()));
    }
    
    public ListaHome[] getListas() {
        return this.tableaus;
    }
    
    public MonteHome getMonteHome() {
        return this.monte;
    }
    
    public PilhaHome[] getBases() {
        return this.pilhas;
    }
    
    public Baralho getBaralho() {
        return this.baralho;
    }
    
    /**
     * Verifica se o jogo acabou.O jogo termina quando o topo de todas as pilhas-base são K
     * @return
     */
    public boolean verificaFimDeJogo() {
        int numK = 0;
        for(PilhaHome ph : this.pilhas) {
            if(ph.elementoTopo() != null && ph.elementoTopo().getNumRep().equals("K")) {
                numK++;
            }
        }
        
        if(numK == 4) {
           return true;
        } else
        return false;
    }
}
