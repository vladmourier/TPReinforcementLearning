package agent.rlagent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import environnement.Action;
import environnement.Environnement;
import environnement.Etat;
/**
 *
 * @author laetitiamatignon
 *
 */
public class QLearningAgent extends RLAgent{
    //TODO
    private HashMap<Etat, HashMap<Action, Double>> Q;
    
    
    /**
     *
     * @param alpha
     * @param gamma
     * @param Environnement
     */
    public QLearningAgent(double alpha, double gamma,
            Environnement _env) {
        super(alpha, gamma,_env);
        //TODO
        this.Q = new HashMap<>();
        
        
    }
    
    
    
    
    /**
     * renvoi la (les) action(s) de plus forte(s) valeur(s) dans l'etat e
     *
     *  renvoi liste vide si aucunes actions possibles dans l'etat
     */
    @Override
    public List<Action> getPolitique(Etat e) {
        //TODO
        return null;
        
        
    }
    
    /**
     * @return la valeur d'un etat
     */
    @Override
    public double getValeur(Etat e) {
        //TODO
        return 0.0;
        
    }
    
    /**
     *
     * @param e
     * @param a
     * @return Q valeur du couple (e,a)
     */
    @Override
    public double getQValeur(Etat e, Action a) {
        //TODO
        return 0.0;
    }
    
    /**
     * setter sur Q-valeur
     */
    @Override
    public void setQValeur(Etat e, Action a, double d) {
        //TODO
        
        
        //mise a jour vmin et vmax pour affichage gradient de couleur
        //...
        
        
        
        this.notifyObs();
    }
    
    
    /**
     *
     * mise a jour de la Q-valeur du couple (e,a) apres chaque interaction <etat e,action a, etatsuivant esuivant, recompense reward>
     * la mise a jour s'effectue lorsque l'agent est notifie par l'environnement apres avoir realise une action.
     * @param e
     * @param a
     * @param esuivant
     * @param reward
     */
    @Override
    public void endStep(Etat e, Action a, Etat esuivant, double reward) {
        //TODO
        
    }
    
    @Override
    public Action getAction(Etat e) {
        this.actionChoisie = this.stratExplorationCourante.getAction(e);
        return this.actionChoisie;
    }
    
    /**
     * reinitialise les Q valeurs
     */
    @Override
    public void reset() {
        super.reset();
        this.episodeNb =0;
        //TODO
        
        
        this.notifyObs();
    }
    
    public Double getQValue(Etat e, Action a){
        Double d = 0.;
        if(Q.containsKey(e)){
            HashMap<Action, Double> map = Q.get(e);
            if(map.containsKey(a)){
                if(map.get(a) != null){
                    d = map.get(a);
                }
            }
        }
        return d;
    }
    
    public void addQValue(Etat e, Action a, Double d){
        HashMap<Action, Double> map;
        if(Q.containsKey(e)){
            map = Q.get(e);
            map.put(a, d);
            Q.put(e, map);
        } else {
            map = new HashMap<>();
            map.put(a, d);
            Q.put(e, map);
        }
    }
    
}
