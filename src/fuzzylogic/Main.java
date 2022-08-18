package fuzzylogic;


import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Rule;
import net.sourceforge.jFuzzyLogic.rule.Variable;


public class Main {
    public static void main(String[] args) throws Exception {
        String fileName = "fcl/file.fcl";
        FIS fis = FIS.load(fileName,true);

        if( fis == null ) { 
            System.err.println("Can't load file: '" + fileName + "'");
            return;
        }

        // Change the variables, based on symptoms fuzzy logic will decide the disease
        fis.setVariable("cough", 5);
        fis.setVariable("temperature", 36.8);
        fis.setVariable("headache", 10);
        fis.setVariable("sneezing", 4);
        fis.setVariable("sore_throat", 7);
        fis.setVariable("muscle_aches", 5);

        
        fis.evaluate();
        Variable diagnosis = fis.getVariable("diagnosis");
        JFuzzyChart.get().chart(diagnosis, diagnosis.getDefuzzifier(), true);
        System.out.println(fis.getVariable("diagnosis"));

        
        for( Rule r : fis.getFunctionBlock("tipper").getFuzzyRuleBlock("job_rules").getRules()) {
              System.out.println(r);
          }
    }
}