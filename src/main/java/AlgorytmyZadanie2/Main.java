
package AlgorytmyZadanie2;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import static java.lang.Character.isDigit;

public class Main {
    
    public static void main (String[] args) throws java.lang.Exception 
    { 
        int ile;
        String[] actualValue;
        BufferedReader br = new BufferedReader(new FileReader("Graff.txt"));
        //BufferedReader br = new BufferedReader(new FileReader("Graff2.txt"));
        String line = null;
        
        line = br.readLine();
        ile=Integer.parseInt(line.trim());
       // System.out.println(ile);
        int graph[][] =new int[ile][ile]; 
        for(int i=0;i<ile;i++){
            line=br.readLine();
            actualValue = line.split(",");
            for(int j=0;j<ile;j++){
               graph[i][j]=Integer.parseInt(actualValue[j].trim());
            }
        }
        MaxFlow m = new MaxFlow(ile); 
        int max = m.fordFulkerson(graph, 0, ile-1); 
        LikwidacjaKanalu lik=new LikwidacjaKanalu(max);
        lik.podsumuj(graph,ile);
  
        System.out.println("Maximum flow " + max); 
  
    } 
} 
    

