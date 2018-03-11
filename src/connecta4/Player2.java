/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connecta4;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuari
 */
public class Player2 {
    private Tauler meutaulell;
    Player2(Tauler entrada){
        meutaulell = entrada;
    }
    public int[] tirada(){
        //Jugador Huma       
        
       int i= Integer.parseInt(JOptionPane.showInputDialog("Entra la X (1 a "+(int) meutaulell.getX()+"):")) -1;
      
       //busco una posicio buida
        for(int j=0;j<meutaulell.getY();j++){
            if (meutaulell.getpos(i,j) == 0){
                return new int[]{i,j}; 
            }
        }
        
        //Un retorn per defecte
        return new int[]{1,1};  
    }
}