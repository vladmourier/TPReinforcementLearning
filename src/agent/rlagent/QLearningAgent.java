package agent.rlagent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import environnement.Action;
import environnement.Environnement;
import environnement.Etat;
import java.util.Set;
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
        List<Action> actions = this.getActionsLegales(e), politique = new ArrayList<>();
        double max = 0;
        for(Action ac : actions){
            if(getQValeur(e, ac)> max){
                max = getQValeur(e, ac);
                actions.clear();
            }
            if(getQValeur(e, ac)== max){
                actions.add(ac);
            }
        }
        return politique;
    }
    
    /**
     * @return la valeur d'un etat
     */
    @Override
    public double getValeur(Etat e) {
        double max =0;
        if(Q.containsKey(e)){
            if(!Q.get(e).isEmpty()){
                for(Action d : Q.get(e).keySet()){
                    if(Q.get(e).get(d)>max){
                        max = Q.get(e).get(d);
                    }
                }
                return max;
            }
        }
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
    
    /**
     * setter sur Q-valeur
     */
    @Override
    public void setQValeur(Etat e, Action a, double d) {
        //TODO
        addQValue(e, a, d);
        
        
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
        Double value , max=0.;
        if(Q.containsKey(esuivant)){
            List<Action> actions = this.getActionsLegales(esuivant);
            for(Action action : actions)
            {
                if (getQValeur(esuivant, action)>max){
                    max = getQValeur(esuivant, action);
                }
            }
            value = ((1-alpha)*getQValeur(e, a)) + alpha*(reward + (gamma * max));
            System.out.println(actions.size());
            setQValeur(e, a, value);
        } else {
            value=new Double(0);
            setQValeur(e, a, value);
        }
        System.out.println("value =" +value);
        System.out.println("reward =" +reward);
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
        this.Q = new HashMap<>();
        
        
        this.notifyObs();
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
