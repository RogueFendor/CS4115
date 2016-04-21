

import java.util.*;
import java.io.*;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.JFrame;
public class is13116371 
{
        
        /**
              
                This  class is the Gos class i.e it is responsible to 
                run all GA functions and methods.
                 
                 Doubles:
                   crossOverProbability    is the crossover probability value  
                   mutationProbabilty      is the mutation porobability
                   reproductionProbabilty  is the reproduction probaility
                   chalice  randomly generated value to identify what GA to apply                
                 
                 allreadyUsed an boolean array[]       decided after long unsucessful attempt to use this to 
                                                       make sure that no dublicate occure in the orderings population  
                
                ArrayList<double[]> myCreation         is the newly created population after GA function
                ArrayList<double[]> lucifersCreation   is the population handed to God to "Pufify"



        */
        public static class God{

       
                private double crossOverProbability;
                private double mutationProbabilty;
                private double reproductionProbabilty;
                private double chalice;
                private boolean [] allreadyUsed;
                private ArrayList<double[]> myCreation;
                private ArrayList<double[]> lucifersCreation;

                public double getChalice() {
                    return chalice;
                }

                public void setChalice(double chalice) {
                    this.chalice = chalice;
                }
                public ArrayList<double[]> getLucifersCreation() {
                    return lucifersCreation;
                }

                public void setLucifersCreation(ArrayList<double[]> lucifersCreation) {
                    this.lucifersCreation = lucifersCreation;
                    this.myCreation = new ArrayList<double[]>();
                    this.allreadyUsed = new boolean[lucifersCreation.get(0).length];
                }

                public God(double cp,double mp,double rp){
                   this.crossOverProbability =cp;
                   this.mutationProbabilty=mp;
                   this.reproductionProbabilty=rp;
                   
                   
                }
                
                /**
                       This method simply returns a new double needed 
                       to decide what GA function we call
                */
                public void iAmTheLigthAndTheTruth(){
                    this.chalice =  (double)(Math.random() * 1);
                 
                }
                /**
                     This method is the second coming i.e we generate random index values 
                     to randomly access indexes of the orderings

                */
                public int theSeconComing(int maxNum,int i){
                   boolean runGenerator = true; 
                   int num=0;
                   
                   if(i ==1){
                   while(runGenerator == true){
                     num = (int)(Math.random() * maxNum);
                     //System.out.println("Test "+num);
                       if(this.allreadyUsed[num]==true){
                               //System.out.println("Doing Nothing");
                       }
                       else{
                          // System.out.println("Doing Something");
                           this.allreadyUsed[num]=true;
                           runGenerator = false;
                          //break;
                       }
                   }
                   }
                   else{
                    num = (int)(Math.random() * maxNum);
                   }
                  
                   return num;
                }
                /**
                     Returns a new Generation of populations
                */
                public ArrayList<double[]> getMyCreation() {
                    return myCreation;
                }
              
                public void setMyCreation(ArrayList<double[]> myCreation) {
                    this.myCreation = myCreation;
                }
                public double getCrossOverProbability() {
                    return crossOverProbability;
                }

                public void setCrossOverProbability(double crossOverProbability) {
                    this.crossOverProbability = crossOverProbability;
                }

                public double getMutationProbabilty() {
                    return mutationProbabilty;
                }

                public void setMutationProbabilty(double mutationProbabilty) {
                    this.mutationProbabilty = mutationProbabilty;
                }

                public double getReproductionProbabilty() {
                    return reproductionProbabilty;
                }

                public void setReproductionProbabilty(double reproductionProbabilty) {
                    this.reproductionProbabilty = reproductionProbabilty;
                }
                /**

                        This method is called when we do Crossover populations are merged and produce 
                        child populations hence the bridechamber

                */
               public static double [] theBridesChamber(double [] p1, double []p2,double [] child){
                ArrayList<Double> list = new ArrayList<Double>();
                   int len= (p1.length);
                    for(int i=0;i<len;i++){
                       list.add(i,p1[i]);
                       list.add(i+1,p2[i]);
                    }
                    for(int j=0;j<list.size();j++){
                       double n = list.get(j);
                       for(int x =j+1;x<list.size();x++){
                          // System.out.print("test "+n+ " "+list.get(x)+" ");
                        if(n==list.get(x)){
                           // System.out.println("hit removing"+list.get(x));
                          list.remove(x);
                        }
                       }
                    }
                   for(int y=0;y<list.size();y++){
                      child[y]=list.get(y);
                   }
                   return child;

                }
                /**

                   This method is called to set all allready used indexes back to false i.e we destroy the record
                   of a previous selected population in te ordering
                */
                public void theFlood(){
                   for(int i =0;i<this.allreadyUsed.length;i++){
                     this.allreadyUsed[i]=false;
                   }
                }
                public String getToString(double []a){
                 String n=""; 
                    for (int i=0;i<a.length;i++){
                        n+=" "+a[i];
                    }
                    return n;
                }
                /**
                    This method simply checks that we did not select the same population twice in the crossover process 
                */
                public int checkDup(double []a1,double []a2 ,int i){
                String n1=getToString(a1); 
                String n2=getToString(a2);
                boolean runThis =true;
                int ctr = 0; 
                int returnVal = 0;
                while (runThis ==true && ctr<99){
                      if(n2.equals(n1)){
                          ctr++;
                         // System.out.println("What is the counter"+ctr);
                         n2 = getToString(this.lucifersCreation.get(ctr));          
                      }
                      else{
                        returnVal = ctr; 
                        runThis=false;
                      }
                      
                    }
                    return returnVal;
                }
                /**

                This method performs the GA operations ie we call this method after initiall set up
                */
                public void theSevenDaysOfCreation(){
                    int len = this.lucifersCreation.size();
                    int randomInt;
                 /*
                   System.out.println("WHOLE POP" );
                   for(int a = 0 ;a <len ;a++){
                          for(int b = 0 ;b < this.lucifersCreation.get(a).length;b++){
                                    System.out.print(this.lucifersCreation.get(a)[b]+" ");
                          }
                          System.out.println();
                    }
                    
                     System.out.println("\n\n#########################");    
                 */
                  this.allreadyUsed= new boolean [len];
                    double mp = this.mutationProbabilty;
                    double rp = this.reproductionProbabilty;
                    double [] tmp;
                    double [] tmp2;
                    int ctr =len;
                    for(int i=0;i<len;i++){
                      
                       iAmTheLigthAndTheTruth();
                      try{
                       if(this.chalice < rp){
                         //  System.out.println("RP");
                           randomInt = this.theSeconComing(this.lucifersCreation.size(),1);
                           tmp = this.lucifersCreation.get(randomInt);
                           this.myCreation.add(i,tmp);
                       }
                      else if(this.chalice >= rp && this.chalice <( mp + rp) ){
                        //  System.out.println("Mutation");
                           randomInt = this.theSeconComing(this.lucifersCreation.size(),1);
                           tmp = this.lucifersCreation.get(randomInt);
                           int blood = tmp.length;
                           int jachin = this.theSeconComing(blood,0);
                           int boaz = this.theSeconComing(blood,0);
                           while(jachin == boaz){
                              boaz = this.theSeconComing(blood,0);
                           }
                           double chromosome1 = tmp[jachin];
                           double chromosome2 = tmp[boaz];
                           tmp[jachin]=chromosome2;
                           tmp[boaz] = chromosome1;
                           int len2 =tmp.length;     
                           /*
                                 System.out.println("Bridal Chamber");
                                 for(int a=0;a<len2;a++){
                                       System.out.print(tmp[a]+" ");
                                 }
                                //// was in here
                                 System.out.println("\n*****************");
                           */
                           this.myCreation.add(i,tmp);
                       }
                      else if (this.chalice >= (rp + mp)){
                           
                            if(i < len-2){
                               // System.out.println("CROSS OVER");
                             //     System.out.println("run "+ i);
                                randomInt = this.theSeconComing(this.lucifersCreation.size(),1);
                                tmp = this.lucifersCreation.get(randomInt);
                                double []child1 = new double[tmp.length];
                                double []child2 = new double[tmp.length];
                                randomInt = this.theSeconComing(this.lucifersCreation.size(),1);
                                tmp2 = this.lucifersCreation.get((randomInt));
                                String a1=getToString(tmp);
                                String a2 = getToString(tmp2);
                                /*
                                if(a1.equals(a2)){
                                  tmp2 = this.lucifersCreation.get((i+1));
                                }
                                */
                                
                                child1 = this.theBridesChamber(tmp, tmp2, child1);
                                child2 = this.theBridesChamber(tmp2, tmp, child2);
                                 
                                
                                this.myCreation.add(i,child1);
                                this.myCreation.add((i+1),child2);
                                len = len-1;
                            
                            }
                            else{
                                int lastSelection=0;
                               for(int j = 0;j< this.allreadyUsed.length;j++){
                                 if(this.allreadyUsed[i]==false){
                                     lastSelection = j;
                                 break;
                                 }
                               }
                               tmp = this.lucifersCreation.get(lastSelection);
                               this.myCreation.add(i,tmp);
                            }     
                       }
                      }
                      catch(Exception e){
                          System.out.println("TEST last count "+i+"  chalice "+ this.chalice+"\n\n"+e);
                          System.out.println(this.lucifersCreation.size());
                          break;
                      }  
                    }
                   
                }      
        }
        public static  class MySorter {
     
     /**
     This class is responsible to perform selection and sorting on the fitness value array and 
     the ordering array.
     */
            private double array[];
            private ArrayList<double[]>pops;
            private int length;
            protected File outputFile;
            protected PrintWriter writer;
            protected FileWriter out;
            protected BufferedWriter bw;
            protected Scanner in;
            
            public MySorter(){
              outputFile = new File("out.txt");
            if(!outputFile.exists()) {
                 try {
                     outputFile.createNewFile();
                 } catch (IOException ex) {
                    System.out.println("Error in creating file "+ex);
                 }
            }
            writeBestFitnessValue("firstCut");
            
            }
            public void testFitnessArray(){
              for(int i = 0;i<array.length;i++){
              }
            }
            public void writeBestFitnessValue(String n){
            try
             {
               if(n.equals("firstCut")){
               out= new FileWriter(this.outputFile);
                n="";
               }
               else{
               out= new FileWriter(this.outputFile,true);
                
               }
               bw = new BufferedWriter(out);
               writer = new PrintWriter(bw);
               writer.println(n);

             } 
             catch (IOException e) {
               System.out.println("Error in sys.log "+e);
             }
             finally{
               try{
                 writer.close();
                 bw.close();
                 out.close();
               }
               catch( Exception e){
                    System.out.println( e.getClass().getName() + ": " + e.getMessage() );
                    System.out.println("Error writing file");
               }

            }
            }
            public void writePopulationsArray(){
             double[] array = new double [pops.get(0).length];
             String res="";
             double fitnessValue=this.array[0];
             for (int j=0;j<pops.size();j++){
              res+="Fitness val:  "+this.array[j]+" Pop: ";
              array = pops.get(j);
              
              for(int i = 0;i<array.length;i++){
                res+= " "+(int)array[i];             
              }
              res+="\n";
             }

             try
             {
               out= new FileWriter(this.outputFile, true);
               bw = new BufferedWriter(out);
               writer = new PrintWriter(bw);
               writer.println(res);

             } 
             catch (IOException e) {
               System.out.println("Error in sys.log "+e);
             }
             finally{
               try{
                 writer.close();
                 bw.close();
                 out.close();
               }
               catch( Exception e){
                    System.out.println( e.getClass().getName() + ": " + e.getMessage() );
                    System.out.println("Error writing file");
               }

            }
            }
            public void sort(double[] inputArr,ArrayList<double[]> populations) {

                if (inputArr == null || inputArr.length == 0) {
                    return;
                }
                this.array = inputArr;
                this.pops = populations;
                length = inputArr.length;
                quickSort(0, length - 1);
            }
            public void Darwin(ArrayList<double[]> populations){
                       int len =populations.size();
                       int breakUp =len / 3 ;
                       int switcher = len - breakUp;
                       double [] array1;
                       for (int i=0;i<breakUp;i++){
                         array1 = populations.get(i);
                          this.pops.remove((switcher + i));
                          this.pops.add((switcher + i),array1);
                          double tmp = this.array[i];
                          this.array[(switcher + i)] =tmp;
                       }  
            }
            public void quickSort(int lowerIndex, int higherIndex) {
                int i = lowerIndex;
                int j = higherIndex;
                double pivot = array[lowerIndex+(higherIndex-lowerIndex)/2];
                while (i <= j) {
                    while (array[i] < pivot) {
                        i++;
                    }
                    while (array[j] > pivot) {
                        j--;
                    }
                    if (i <= j) {
                        exchangeNumbers(i, j);
                        exchangeArrays(i,j);
                        i++;
                        j--;
                    }
                }
                // call quickSort() method recursively
                if (lowerIndex < j)
                    quickSort(lowerIndex, j);
                if (i < higherIndex)
                    quickSort(i, higherIndex);
            }
            private void exchangeNumbers(int i, int j) {
                double temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
             private void exchangeArrays(int i, int j) {
                double [] temp = this.pops.get(i);
                double [] temp2 = this.pops.get(j);
                this.pops.remove(i);
                this.pops.add(i,temp2);
                this.pops.remove(j);
                this.pops.add(j,temp);
            }
        }
  public static class AI extends JFrame{
      /**
      This class is responsible to display final result from ordering
      */
      public  int v=0;  // number of nodes
      public  int [][] adj;  // the adjacency matrix 
      public  double [] ordering_last_result;
  
  // v is the size of the graph, or size of each ordering 
  // adjacency_matrix is the matrix or 2D array which you initially read it from the txt file.
  // ordering is the 1D array which contains the very last ordering

  
  public AI()  // this method must have same name as the class above.
  {
    setTitle("AI");
    setSize(960,960);
    setVisible(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
  }
        public void setMatrix(int [][] mat){
          this.adj  = mat;
        }
        public void setOrderingLastResult(double[] res){
          this.ordering_last_result = res;
          this.v = this.ordering_last_result.length;
          this.preparePops();
        }
        public void preparePops(){
             int v = ordering_last_result.length;
                double[] temp = new double [v];
                temp= ordering_last_result.clone();
                double min=0;
                for(int i=0;i<v;i++)
                {
                      for(int j=0;j<v;j++)
                      {
                            if(temp[j]==min)
                            {
                                  ordering_last_result[i]=j;
                                  min++;
                                  break;
                            }
                      }
                }
        }
  public void paint(Graphics g)
  {
    int radius =100;
    int mov =200;
    
    double w = Math.sqrt((double)v);
    if(Math.floor(w)<w)
    {
      w=Math.floor(w);
      w++;
    }   
    for(int i=0;i<v;i++)
    {
      for(int j=i+1;j<v;j++)
      { 
        if(adj[i][j]==1)  
        {
                                    g.drawLine(
                                    (int)(((double) ordering_last_result[i] % w)*radius + mov), 
                                    (int)(((double) ordering_last_result[i] / w)*radius+mov), 
                                    (int)(((double) ordering_last_result[j] % w)*radius + mov),
                                    (int)(((double) ordering_last_result[j] / w)*radius+mov));
        }
      }
    }
  }
    
    }
  
        
        public static class Coordinates{
           
           /*
                  This class is just helper class for fitness value calculations
           */
            private int i_vertex_x;
            private int j_vertex_x;
            private int i_vertex_y;
            private int j_vertex_y;

            public void Coordinates(){
              
            }
            public int getI_vertex_x() {
                return i_vertex_x;
            }

            public void setI_vertex_x(int i_vertex_x) {
                this.i_vertex_x = i_vertex_x;
            }

            public int getJ_vertex_x() {
                return j_vertex_x;
            }

            public void setJ_vertex_x(int j_vertex_x) {
                this.j_vertex_x = j_vertex_x;
            }

            public int getI_vertex_y() {
                return i_vertex_y;
            }

            public void setI_vertex_y(int i_vertex_y) {
                this.i_vertex_y = i_vertex_y;
            }

            public int getJ_vertex_y() {
                return j_vertex_y;
            }

            public void setJ_vertex_y(int j_vertex_y) {
                this.j_vertex_y = j_vertex_y;
            }
                                  
            
        
        }
        public static class SquareTable{ 

              private double [][] populationString;
              private int newSize;               
             
              public SquareTable(){
                           
              }
              public int getNewSize() {
                 return newSize;
              }

              public void setNewSize(int newSize) {
                this.newSize = this.populationString.length;
              }
              public double[][] getPopulationString() {
               return populationString;
              }

              public void setPopulationString(double[] populationString) {
                 
                   this.setMatrix(populationString.length);
                   this.calculate(populationString);
                   
              }

     
              public int [] getVertex(int row,int col,int val){
                  int [] multiplex = new int [4];
                  int len = this.getNewSize();
                  for(int i = 0;i< len;i++){
                    for (int j = 0;j < len;j++){
                       if(populationString[i][j]==row){
                         multiplex[0]=i;
                         multiplex[1]=j;
                       }
                        if(populationString[i][j]==col){
                         multiplex[2]=i;
                         multiplex[3]=j;
                       }
                    }
                  }
                return multiplex;
              }
              public void calculate(double[] populationString){
              String res="";
               int c_ctr =0;
               int len = this.populationString.length;
               int len2 = populationString.length;
                for(int i=0;i<len;i++){
                   for(int j=0;j<len;j++){
                     if(c_ctr< len2){
                        this.populationString[i][j]=populationString[c_ctr];
                        res+=""+this.populationString[i][j]+" ";
                        c_ctr++;
                     }
                   }
                   res+="\n";   
                }
              }
              public void newMatrixPop(){
                String res="";
                 int c_ctr =0;
                 int len = this.populationString.length;
                 for(int i=0;i<len;i++){
                   for(int j=0;j<len;j++){
                     if(c_ctr< len){
                        res+=""+this.populationString[i][j]+" ";
                     }
                   }
                   res+="\n";   
                }
                 System.out.println(res);
              }
              public void setMatrix(int index) {
                 
                 int len = (int)Math.round(Math.sqrt(index));
                 this.populationString = new double [len][len];
                 this.setNewSize(len);
                 
                 
              }

            }
        
            public static int popSize;
            public static void main(String [] args){

               ArrayList<double[]>populations = new ArrayList<double[]>();
               String message = 
                                   "[*]"+
                                   "[*]   Author: Benjamin Keil\n"+
                                   "[*]   Student ID: 13116371\n"+
                                   "[*]   \n"+
                                   "[*]   This application reads in a list of nodes and generates\n"+
                                   "[*]    best possible paths in a user defined population via genetic\n"+
                                   "[*]    selection algorithms.\n"+
                                   "[*]   \n"+
                                   "[*]   is131163 usage: java is13116371 <enter file name> <enter orderings size>\n"+
                                   "[*]   example: java is13116371 data.txt 100\n";
                if(args.length!=2){
                  System.out.println(message);
                  System.exit(1);
                }
                try{
                  try{
                     popSize=Integer.parseInt(args[1]);
                   }
                   catch(NumberFormatException e){
                      System.out.println("Error no readable format for number of orderings setting back to default 100");
                      popSize =100;       
                   }
                  File dataFile = new File(args[0]);
                  Scanner in = new Scanner(dataFile);
                  System.out.println("Reading file and starting process\n  Please wait until finish..");
                  int maxNum = getMaxEdges(in)+1;
                  int [][]matrixArray = new int [maxNum][maxNum];
                  double [] fitnesseFunc = new double[popSize];
                  setToZero(matrixArray,maxNum);
                  createMatrix(dataFile,in,maxNum,matrixArray);
                  //displayMatrix(matrixArray,maxNum);
                  for(int a=0;a<popSize;a++){
                    generatePopulation2(maxNum,populations,a);
                  }
                  callToOsiris(populations,matrixArray);
                  //displayPopulations2(populations,maxNum);
                  runFitnessFunction(populations,maxNum,matrixArray,fitnesseFunc);
                  MySorter betterSorting = new MySorter();
                  betterSorting.sort(fitnesseFunc, populations);
                  betterSorting.Darwin(populations);
                //  printMinVal(fitnesseFunc);
                  //AI visualization = new AI();
                  //visualization.setMatrix(matrixArray);
                  //visualization.setOrderingLastResult(populations.get(0));
                  //visualization.repaint();
                 callToOsiris(populations,matrixArray);
                 God god =new God(0.10,0.05,0.85);
                 int count=0;
                 while(count < 500){
                     count++;
                     god.setLucifersCreation(populations);
                     god.theFlood();
                     god.theSevenDaysOfCreation();
                     populations = god.getMyCreation();
                     runFitnessFunction(populations,maxNum,matrixArray,fitnesseFunc);
                     //displayPopulations2(populations,maxNum);
                     betterSorting.sort(fitnesseFunc, populations);
                     // callToOsiris(populations,matrixArray);
                     betterSorting.writePopulationsArray();
                     betterSorting.Darwin(populations);
                     //printMinVal(fitnesseFunc);
                     //count++  
                 }
               
                 populations = god.getMyCreation();
                  runFitnessFunction(populations,maxNum,matrixArray,fitnesseFunc);
                  betterSorting.sort(fitnesseFunc, populations);
                  betterSorting.Darwin(populations);
                   // v is the number of nodes
                 callToOsiris(populations,matrixArray);
                 System.out.println("Process Finsihed ...");
                 betterSorting.writeBestFitnessValue("\n\n[*]  Best Fitness value "+fitnesseFunc[0]);
                }
                catch( IOException e){
                    System.out.println("ERROR\nthis file does not exists!\nDid you specify the path? See help below"+"\n\n"+message);
                }
            }
            public static void printMinVal(double [] fitnesseFunc){
                 int len =fitnesseFunc.length;
                 for(int i=0;i<len;i++){
                   
                 }
            }
            public static void callToOsiris(ArrayList<double[]> populations,int [][]matrixArray){
            
                    AI visualization = new AI();
                    visualization.setMatrix(matrixArray);
                    visualization.setOrderingLastResult(populations.get(0));
                    visualization.repaint();
            
            }
            public static void runFitnessFunction(ArrayList<double[]> populations,int n,int [][]matrixArray, double [] fitnesseFunc){
                SquareTable[] tables = new SquareTable[popSize];
                SquareTable squareTable;
                Coordinates vertexHelper = new Coordinates(); 
                double fitness=0;
                int returnMultiplex[];
                double len1;
                double len2;
                
                /**
                 * create individual population matrix and store in array of SquareTable type.
                 */
               
                for(int i=0;i<populations.size();i++){   
                  squareTable = new SquareTable();
                  squareTable.setPopulationString(populations.get(i));
                  tables[i]=squareTable;
                  // testRun to see what is being created here
                  // tables[i].newMatrixPop();
                }
                for(int x= 0;x<popSize;x++){
                    for(int row = 0; row< matrixArray.length;row++){

                       for(int col=0;col<n;col++){
                          if(matrixArray[row][col]==1){
                           returnMultiplex= tables[x].getVertex(row, col, col);
                           //System.out.println("Test MP "+returnMultiplex[0]+" "+returnMultiplex[1]+returnMultiplex[2]+" "+returnMultiplex[3]);
                              vertexHelper.setI_vertex_x(returnMultiplex[0]);
                              vertexHelper.setI_vertex_y(returnMultiplex[1]);
                              vertexHelper.setJ_vertex_x(returnMultiplex[2]);
                              vertexHelper.setJ_vertex_y(returnMultiplex[3]);
                              len1 = Math.pow((vertexHelper.getI_vertex_x())-(vertexHelper.getJ_vertex_x()),2);
                              len2 = Math.pow((vertexHelper.getI_vertex_y())-(vertexHelper.getJ_vertex_y()),2);
                              len1 = Math.sqrt(len1 + len2);
                              fitness = fitness + len1;
                          }
                       }
                    }
                    fitnesseFunc[x]=fitness;
                    fitness = 0;  
                }
            }
      public static void setToZero(int [][] matrixArray,int n){
    for(int i=0;i<n;i++){
      for(int j=0;j<n;j++){
          matrixArray[i][j] = 0;    
      }
    }
      }
            public static int getMaxEdges(Scanner in) throws IOException{
            String n =""; 
            int max1=0;
            int max2=0;
                    while (in.hasNextLine()){
               n =in.nextLine();
               String [] tmp = n.split(" ");
               if(Integer.parseInt(tmp[0]) > max1){
                    max1 = Integer.parseInt(tmp[0]);
               }
               if(Integer.parseInt(tmp[1]) > max1){
                    max2 = Integer.parseInt(tmp[1]);
               }
                    }
                in.close();
                if (max1 > max2)
                    return max1;
                else
                    return max2;
            }
            public static void createMatrix(File dataFile, Scanner in, int n,int [][] matrixArray) throws IOException{
            String currentLine="";


             in = new Scanner(dataFile);

             while (in.hasNextLine()){

               currentLine =in.nextLine();
               String [] tmp = currentLine.split(" ");
               matrixArray[Integer.parseInt(tmp[0])][Integer.parseInt(tmp[1])] =1;
               matrixArray[Integer.parseInt(tmp[1])][Integer.parseInt(tmp[0])] =1;
                    }
                in.close();
            } 
            public static void arrayHelper2(int num,int maxNum,double[]array,int i){
                    num= randomNum(maxNum);
                          while(singleArrayHasDublicate2(num,array,i)==true){
                           num=randomNum(maxNum); 
                          }
                          array[i]=num;
            }
            public static void displaySinglePopulation(double [] n){
                System.out.println("********SinglePop*********");
              for(int i = 0;i<n.length;i++){
                 System.out.print(n[i]+" ");
              }
              System.out.println("\n***********************");
            }
            public static void createSingleArray2(int maxNum,double[] array){
               int num=0;
               for(int i=0;i<maxNum;i++){
                     if(i==0){
                       num= randomNum(maxNum);
                       array[i]=num;
                     }
                         else{
                               arrayHelper2(num,maxNum,array,i);
                            }
                       }
            }
            public static void generatePopulation2(int maxNum, ArrayList<double[]> populations,int a){
                    // generate random permutation of string of 0 to max num
                  int num=0;
                  double [] array = new double[maxNum];
                  int j =0;
                  createSingleArray2(maxNum,array);
                  if(a == 0){
                     addArray2(maxNum,array,populations,a);
                  }
                   else{
                      while(ArrayHasDublicateArray2(array,populations) == true){
                       createSingleArray2(maxNum,array);
                   }
                   addArray2(maxNum,array,populations,a);
                   }
            }
            public static void addArray2(int maxNum,double []array, ArrayList<double[]> populations,int a){
        populations.add(a,array);
            }
            public static void displayPopulations2(ArrayList<double[]> populations,int n){
    int len = populations.size();
    for(int i=0;i<len;i++){
      for(int j=0;j<n;j++){
          double [] tmp = populations.get(i);
          System.out.print(tmp[j]+" ");
      }
      System.out.println("\n ");
    }
      }
            public static boolean ArrayHasDublicateArray2(double [] array,ArrayList<double[]> populations){
    boolean isDouble = false;
    int listLen = populations.size();
    int arrayLen = array.length;
    int ctr=0;
    boolean keepGoing = true;
    for(int i=0;i<listLen && keepGoing;i++){
                    for(int j=0;j<arrayLen;j++){
                      double [] tmp=populations.get(i);
                      if(array[j] != tmp[j]){
                          keepGoing = false;
                          break;
                      } 
                      else{
                              isDouble = true;
                      }
                    }
          }
    return isDouble;
            }
            public static boolean singleArrayHasDublicate2(int num,double []array,int currentCols){
    boolean isDouble = false;
    for(int i=0;i<currentCols;i++){
      if(array[i] == num){
        isDouble = true;
        break;
      }
    }
    return isDouble;
            }
      public static int randomNum(int maxNum){
               return (int)(Math.random() * maxNum);
      }
            
    }
