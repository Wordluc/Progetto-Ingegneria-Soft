package Gestione;


import java.util.LinkedList;


public class resStep {
        private LinkedList<String> steps=new LinkedList<>();
        private String desc;

        public static resStep getResInstant(String step,String desc){
                resStep r=new resStep();
                r.addStep(0,step);
                r.desc=desc;
                return r;
        }
        public LinkedList<String> getStep(){return steps;}
        public String getStep(int i){return steps.get(i);}
        public String getDesc(){return desc;}
        public int getSize(){return steps.size();}
        public void setSteps(LinkedList<String> steps){this.steps=steps;}
        public void setDesc(String desc){this.desc=desc;}
        public void delStep(int i){this.steps.remove(i);}
        public void addStep(int i, String r){this.steps.add(i,r);}

}
