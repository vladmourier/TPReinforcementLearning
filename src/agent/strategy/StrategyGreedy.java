package agent.strategy;

import java.util.List;
import java.util.Random;

import agent.rlagent.RLAgent;
import environnement.Action;
import environnement.Etat;
/**
 * Strategie qui renvoit une action aleatoire avec probabilite epsilon, une action gloutonne (qui suit la politique de l'agent) sinon
 * Cette classe a acces a un RLAgent par l'intermediaire de sa classe mere.
 * @author lmatignon
 *
 */
public class StrategyGreedy extends StrategyExploration{
    //TODO
    private double epsilon;
    private Random rand=new Random();
    
    
    
    public StrategyGreedy(RLAgent agent,double epsilon) {
        super(agent);
        //TODO
        this.epsilon = epsilon;
    }
    
    /**
     * @return action selectionnee par la strategie d'exploration
     */
    @Override
    public Action getAction(Etat _e) {
        //VOTRE CODE
        //getAction renvoi null si _e absorbant
        if(getAgent().getEnv().estAbsorbant()){
            return null;
        }
        List<Action> politique = getAgent().getPolitique(_e);
        List<Action> actions_explo = getAgent().getActionsLegales(_e);
        double value = rand.nextDouble();
        if(value<=epsilon){
            return actions_explo.get(rand.nextInt(actions_explo.size()));
        } else {
            return politique.get(this.rand.nextInt(politique.size()));
        }
        
    }
    
    
    
    public void setEpsilon(double epsilon) {
        //VOTRE CODE
        this.epsilon = epsilon;
    }
    
    
    
}
