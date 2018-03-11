/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connecta4;

import java.util.ArrayList;

/**
 *
 * @author Usuari
 */

class Point {
    public int x;
    public int y;
    
    Point(int a, int b){
        x=a;
        y=b;
    }
    int getX(){
        return x;
    }
    
    int getY(){
        return y;
    }
}



public class Player1 {
    private Tauler meutaulell;
    public int millor;
    public int valor;
    public int alfa;
    public int beta;
    private static int[][] puntsTaula  = {{3, 4, 5, 7, 5, 4, 3}, 
                                          {4, 6, 8, 10, 8, 6, 4},
                                          {5, 8, 11, 13, 11, 8, 5}, 
                                          {5, 8, 11, 13, 11, 8, 5},
                                          {4, 6, 8, 10, 8, 6, 4},
                                          {3, 4, 5, 7, 5, 4, 3}};
    
    Player1(Tauler entrada){
        meutaulell = entrada;
    }
    public int[] tirada(){
        int x,y;
        
        //Crea les 6 possibles jugades
        ArrayList <Point> jugades = new ArrayList<Point>();
        for(int i=0;i<meutaulell.getX();i++){
            int j=0;
            while (j<meutaulell.getY() && meutaulell.getpos(i,j) != 0){
                j++;
            }
            if (j<meutaulell.getY()){
                Point p = new Point(i,j);
                jugades.add(p);
            }
        }
        
        int act;
        int bestvalue=0;
        Point jugadabona=new Point(0,0);
        
        for (int t=0;t<jugades.size();t++){
            Tauler tau = new Tauler();
            //Crea una copia per la nova jugada
            for(int i=0;i<meutaulell.getX();i++){
                for(int j=0;j<meutaulell.getY();j++){
                    tau.getTaulell()[i][j]=meutaulell.getTaulell()[i][j];
                }
            }
            
            tau.setpos(jugades.get(t).getX(), jugades.get(t).getY());
            
            act=alfabeta(tau,0,-9999,9999);
            //act = minimax(tau,0);
            
            if (act>bestvalue){
                jugadabona = jugades.get(t);
                bestvalue=act;
            }
        }
        System.out.printf("--> " +bestvalue);
        return new int[]{jugadabona.getX(),jugadabona.getY()};

    }
    
    
    int alfabeta(Tauler node_actual,int nivell,int alfa,int beta){
        //Algorisme AlfaBeta
        if (nivell>=10){
            return heuristica(node_actual);
        }
        else{
            ArrayList <Tauler> fills = genera_movs(node_actual);
            int nfills = fills.size();
            if(fills.isEmpty()){
                return heuristica(node_actual);
            }
            else{
                if (nivell%2==0){
                    int i=0;
                    while((i<nfills) && (alfa<beta)){
                        nivell++;
                        valor = alfabeta(fills.get(i),nivell,alfa,beta);
                        if (valor < beta){
                            beta=valor;
                        }
                        i++;
                    }
                    return beta;
                }
                else {
                    int i=0;
                    while((i<nfills) && (alfa<beta)){
                        nivell++;
                        valor = alfabeta(fills.get(i),nivell,alfa,beta);
                        if (valor >alfa){
                            alfa=valor;
                        }
                        i++;
                    }
                    return alfa;
                }
            }
        }
    }
    
    int minimax(Tauler node_actual, int nivell){
        //Algorisme MinMax
        if (nivell>=10){
            return heuristica(node_actual);
        }
        else{
            ArrayList <Tauler> fills = genera_movs(node_actual);
            if(fills.isEmpty()){
                return heuristica(node_actual);
            }
            else{
                if (nivell%2==0){
                   millor=9999999;
                   for (Tauler t : fills){
                       nivell++;
                       valor = minimax(t,nivell);
                       if (valor < millor){
                           millor = valor;
                       }
                   }
                   
                }
                else{
                    millor=0;
                    for (Tauler t : fills){
                        nivell++;
                        valor = minimax(t,nivell);
                        if (valor > millor){
                            millor = valor;
                       }
                   }
                }
            }
        }
        return millor;
    }
    
    ArrayList <Tauler> genera_movs(Tauler node_actual){
        //Retorna una llista amb els 6 taulers resultants.
        ArrayList <Tauler>taulers = new ArrayList<Tauler>();
        for(int i=0;i<node_actual.getX();i++){
            Tauler tau = new Tauler();
            for(int t=0;t<node_actual.getX();t++){
                for(int k=0;k<node_actual.getY();k++){
                    tau.getTaulell()[t][k]=node_actual.getTaulell()[t][k];
                }
            }
            int j=0;
            while (j<node_actual.getY() && node_actual.getpos(i,j) != 0){
                j++;
            }
           if (j<node_actual.getY()){
                tau.setpos(i,j);
                taulers.add(tau);
           }
        }
        return taulers;
    }
    
    int threatsAdversari(Tauler t){
        int threatsA = 0;
        
        for (int i = 0; i < t.getX(); i++){
            for (int j = 0; j <t.getY(); j++){
                
            
            }
        }
        
        
        return threatsA;
    
    }
    
    int threatsJugador(Tauler t){
        int threatsJ = 0;
        
        
        return threatsJ;
    }
        
    int heur(Tauler t){
        return threatsJugador(t) - threatsAdversari(t);
    
    }
    // i = x, j = y
    int comptarDefenses(Tauler t, int jugador, int i, int j){
        int eval = 0;
        int pdefensa = 500;
        int rival = 0;
        if(jugador == 1)
            rival = 2;
        else
            rival = 1;
        
        
        if (j<=3){
                    if (t.getTaulell()[i][j] == jugador && t.getTaulell()[i][j+1] == jugador && t.getTaulell()[i][j+2] == jugador && t.getTaulell()[i][j+3] == rival){
                        eval += pdefensa;
                    }
                }
                else if (j==3){
                    if (t.getTaulell()[i][j] == jugador && t.getTaulell()[i][j+1] == jugador && t.getTaulell()[i][j+2] == jugador && t.getTaulell()[i][j+3] == rival){
                        eval += pdefensa;
                    }
                    else if (t.getTaulell()[i][j] == rival && t.getTaulell()[i][j-1] == jugador && t.getTaulell()[i][j-2] == jugador && t.getTaulell()[i][j-3] == jugador){
                        eval += pdefensa;
                    }
                }
                else if (j>3){
                    if (t.getTaulell()[i][j] == rival && t.getTaulell()[i][j-1] == jugador && t.getTaulell()[i][j-2] == jugador && t.getTaulell()[i][j-3] == jugador){
                        eval += pdefensa;
                    }
                }
                //Defensa Horitzontal
                else if (i<3){
                    if (t.getTaulell()[i][j] == rival && t.getTaulell()[i+1][j] == jugador && t.getTaulell()[i+2][j] == jugador && t.getTaulell()[i+3][j] == jugador){
                        eval += pdefensa;
                    }
                    else if (t.getTaulell()[i][j] == jugador && t.getTaulell()[i+1][j] == jugador && t.getTaulell()[i+2][j] == jugador && t.getTaulell()[i+3][j] == rival){
                        eval += pdefensa;
                    }
                    else if (t.getTaulell()[i][j] == jugador && t.getTaulell()[i+1][j] == rival && t.getTaulell()[i+2][j] == jugador && t.getTaulell()[i+3][j] == jugador){
                        eval += pdefensa;
                    }
                    else if (t.getTaulell()[i][j] == jugador && t.getTaulell()[i+1][j] == jugador && t.getTaulell()[i+2][j] == rival && t.getTaulell()[i+3][j] == jugador){
                        eval += pdefensa;
                    }
                }
                else if (i>=3){
                    if (t.getTaulell()[i][j] == rival && t.getTaulell()[i-1][j] == jugador && t.getTaulell()[i-2][j] == jugador && t.getTaulell()[i-3][j] == jugador){
                        eval += pdefensa;
                    }
                    else if (t.getTaulell()[i][j] == jugador && t.getTaulell()[i-1][j] == jugador && t.getTaulell()[i-2][j] == jugador && t.getTaulell()[i-3][j] == rival){
                        eval += pdefensa;
                    }
                    else if (t.getTaulell()[i][j] == jugador && t.getTaulell()[i-1][j] == rival && t.getTaulell()[i-2][j] == jugador && t.getTaulell()[i-3][j] == jugador){
                        eval += pdefensa;
                    }
                    else if (t.getTaulell()[i][j] == jugador && t.getTaulell()[i-1][j] == jugador && t.getTaulell()[i-2][j] == rival && t.getTaulell()[i-3][j] == jugador){
                        eval += pdefensa;
                    }
                }
                //Defensa diagonal alt +1 +1
                else if (i<3 && j<4){
                    if (t.getTaulell()[i][j] == rival && t.getTaulell()[i+1][j+1] == jugador && t.getTaulell()[i+2][j+2] == jugador && t.getTaulell()[i+3][j+3] == jugador){
                        eval += pdefensa;
                    }
                    else if (t.getTaulell()[i][j] == jugador && t.getTaulell()[i+1][j+1] == jugador && t.getTaulell()[i+2][j+2] == jugador && t.getTaulell()[i+3][j+3] == rival){
                        eval += pdefensa;
                    }
                    else if (t.getTaulell()[i][j] == jugador && t.getTaulell()[i+1][j+1] == rival && t.getTaulell()[i+2][j+2] == jugador && t.getTaulell()[i+3][j+3] == jugador){
                        eval += pdefensa;
                    }
                    else if (t.getTaulell()[i][j] == jugador && t.getTaulell()[i+1][j+1] == jugador && t.getTaulell()[i+2][j+2] == rival && t.getTaulell()[i+3][j+3] == jugador){
                        eval += pdefensa;
                    }
                }
                //Defensa diagonal alt -1 +1
                else if (i>2 && j<4){
                    if (t.getTaulell()[i][j] == rival && t.getTaulell()[i-1][j+1] == jugador && t.getTaulell()[i-2][j+2] == jugador && t.getTaulell()[i-3][j+3] == jugador){
                        eval += pdefensa;
                    }
                    else if (t.getTaulell()[i][j] == jugador && t.getTaulell()[i-1][j+1] == jugador && t.getTaulell()[i-2][j+2] == jugador && t.getTaulell()[i-3][j+3] == rival){
                        eval += pdefensa;
                    }
                    else if (t.getTaulell()[i][j] == jugador && t.getTaulell()[i-1][j+1] == rival && t.getTaulell()[i-2][j+2] == jugador && t.getTaulell()[i-3][j+3] == jugador){
                        eval += pdefensa;
                    }
                    else if (t.getTaulell()[i][j] == jugador && t.getTaulell()[i-1][j+1] == jugador && t.getTaulell()[i-2][j+2] == rival && t.getTaulell()[i-3][j+3] == jugador){
                        eval += pdefensa;
                    }
                }
                //Defensa diagonal baix -1-1
                else if (i>2 && j>2){
                    if (t.getTaulell()[i][j] == rival && t.getTaulell()[i-1][j-1] == jugador && t.getTaulell()[i-2][j-2] == jugador && t.getTaulell()[i-3][j-3] == jugador){
                        eval += pdefensa;
                    }
                    else if (t.getTaulell()[i][j] == jugador && t.getTaulell()[i-1][j-1] == jugador && t.getTaulell()[i-2][j-2] == jugador && t.getTaulell()[i-3][j-3] == rival){
                        eval += pdefensa;
                    }
                    else if (t.getTaulell()[i][j] == jugador && t.getTaulell()[i-1][j-1] == rival && t.getTaulell()[i-2][j-2] == jugador && t.getTaulell()[i-3][j-3] == jugador){
                        eval += pdefensa;
                    }
                    else if (t.getTaulell()[i][j] == jugador && t.getTaulell()[i-1][j-1] == jugador && t.getTaulell()[i-2][j-2] == rival && t.getTaulell()[i-3][j-3] == jugador){
                        eval += pdefensa;
                    }
                }
                //Defensa diagonal baix +1-1
                else if (i<3 && j>2){
                    if (t.getTaulell()[i][j] == rival && t.getTaulell()[i+1][j-1] == jugador && t.getTaulell()[i+2][j-2] == jugador && t.getTaulell()[i+3][j-3] == jugador){
                        eval += pdefensa;
                    }
                    else if (t.getTaulell()[i][j] == jugador && t.getTaulell()[i+1][j-1] == jugador && t.getTaulell()[i+2][j-2] == jugador && t.getTaulell()[i+3][j-3] == rival){
                        eval += pdefensa;
                    }
                    else if (t.getTaulell()[i][j] == jugador && t.getTaulell()[i+1][j-1] == rival && t.getTaulell()[i+2][j-2] == jugador && t.getTaulell()[i+3][j-3] == jugador){
                        eval += pdefensa;
                    }
                    else if (t.getTaulell()[i][j] == jugador && t.getTaulell()[i+1][j-1] == jugador && t.getTaulell()[i+2][j-2] == rival && t.getTaulell()[i+3][j-3] == jugador){
                        eval += pdefensa;
                    }
                }
        
        return eval;
        
    }
    int calculaPosiblesLinies(Tauler t, int jugador, int i, int j){
        int eval = 0; int rival = 0;
        int punts = 30;
        if(jugador == 1)
            rival = 2;
        else
            rival = 1;
        
        if (t.getTaulell()[i][j] == rival){
            eval += puntsTaula[i][j] * punts;
        }
        else if (t.getTaulell()[i][j] == jugador){
            eval -= puntsTaula[i][j] * punts;
        }
        return eval;
    }
    int victoriesPosibles(Tauler t, int jugador, int i, int j){
        int eval = 0;
        int rival = 0;
        int pvictoria = 450;
        int ptres = 300;
        int pdos = 50;
        if(jugador == 1)
            rival = 2;
        else
            rival = 1;
        //verticals
        if(j <= 3){
            //linia vertical 4
            if(j >=3 && t.getTaulell()[i][j] == jugador  && t.getTaulell()[i][j-1] == jugador && t.getTaulell()[i][j-2] == jugador && t.getTaulell()[i][j-3] == jugador){
                eval += pvictoria;
            }
            else if(j >= 2 && j <6 && t.getTaulell()[i][j] == jugador  && t.getTaulell()[i][j-1] == jugador && t.getTaulell()[i][j-2] == jugador && t.getTaulell()[i][j+1] != rival){
                eval += ptres;
            }
            else if(j >= 1 && j <5 && t.getTaulell()[i][j] == jugador  && t.getTaulell()[i][j-1] == jugador && t.getTaulell()[i][j+1] != rival ){
                eval += pdos;
            }
        }
        //horitzontals
        if(i >= 3){
            if(t.getTaulell()[i][j] == jugador && t.getTaulell()[i-1][j] == jugador && t.getTaulell()[i-2][j] == jugador && t.getTaulell()[i-3][j] == jugador ){
                eval += pvictoria;
            }
            else if(t.getTaulell()[i][j] == jugador && t.getTaulell()[i-1][j] == jugador && t.getTaulell()[i-2][j] == jugador && t.getTaulell()[i-3][j] != rival ){
                eval += ptres;
            }
            else if(t.getTaulell()[i][j] == jugador && t.getTaulell()[i-1][j] == jugador && t.getTaulell()[i-2][j] != rival && t.getTaulell()[i-3][j] != rival ){
                eval += pdos;
            }
        }
        //horitzontals
        else if(i <=2){
            if(t.getTaulell()[i][j] == jugador && t.getTaulell()[i+1][j] == jugador && t.getTaulell()[i+2][j] == jugador && t.getTaulell()[i+3][j] == jugador ){
                eval += pvictoria;
            }
            else if(t.getTaulell()[i][j] == jugador && t.getTaulell()[i+1][j] == jugador && t.getTaulell()[i+2][j] == jugador && t.getTaulell()[i+3][j] != rival ){
                eval += ptres;
            }
            else if(t.getTaulell()[i][j] == jugador && t.getTaulell()[i+1][j] == jugador && t.getTaulell()[i+2][j] != rival && t.getTaulell()[i+3][j] != rival ){
                eval += pdos;
            }
            //verticals 
            if(j >= 3){
                if(i <=2){
                    if(t.getTaulell()[i][j] == jugador && t.getTaulell()[i+1][j-1] == jugador && t.getTaulell()[i+2][j-2] == jugador && t.getTaulell()[i+3][j-3] == jugador ){
                        eval += pvictoria;
                    }
                    if(t.getTaulell()[i][j] == jugador && t.getTaulell()[i+1][j-1] == jugador && t.getTaulell()[i+2][j-2] == jugador   ){
                        eval += ptres;
                    }
                }
                else{
                    if(t.getTaulell()[i][j] == jugador && t.getTaulell()[i-1][j-1] == jugador && t.getTaulell()[i-2][j-2] == jugador && t.getTaulell()[i-3][j-3] == jugador ){
                        eval += pvictoria;
                    }
                    if(t.getTaulell()[i][j] == jugador && t.getTaulell()[i+1][j-1] == jugador && t.getTaulell()[i+2][j-2] == jugador && t.getTaulell()[i-1][j+1] != rival ){
                        eval += ptres;
                    }
                }
            }
            
        }
        
        //diagonals
        
        
        return eval;
    
    }
    int heuristica(Tauler t){
        int eval=0;
        // prioritats -> evitar que l'altre faci linia, fer linia, mantenir com mes posibilitats de fer linia millor
        // evitar bloquejos
        int puntsJugador = 0;
        int puntsRival = 0;
        //Buscar Threats resolts
        for (int i = 0; i < t.getX(); i++){
            for (int j = 0; j <t.getY(); j++){
                //defenses del jugador a victories del rival
                puntsJugador += comptarDefenses(t, 2, i, j);
                //defenses del rival a victories del jugador 
                puntsRival += comptarDefenses(t, 1, i, j);
                //posibilitats que te el jugador de fer diverses linies
                puntsJugador += calculaPosiblesLinies(t, 2, i, j);
                //posibilitats que te el jugador de fer diverses linies
                puntsRival += calculaPosiblesLinies(t, 1, i, j);
                //posibles victories jugador actual (calcular l'index de posibilitats de fer linia via diagonal vertical horitzontal
                //molts punts si fem una victoria, tambe sumem punts si tenim 2 i 3 fitxes amb opcio a linia
                // + opcions a linia = mes punts = millor tauler
                // no obstant, fer linia = MOLTS MES PUNTS = he d'escollir aquest tauler
                // si tenim dos taulers amb linia segura, tindra mes pes el que tingui mes "futur", osigui mes camins oberts per fer linia
                puntsJugador += victoriesPosibles(t, 2, i, j);
                //posibles victories del rival.
                puntsRival += victoriesPosibles(t, 1, i, j);
            }
        }
        return (puntsJugador - puntsRival);
        //Tria la casella amb millor value
  
    }

}
