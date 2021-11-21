package AlgorytmyZadanie2;

import java.util.ArrayList;
import java.util.List;

public class LikwidacjaKanalu {

    int maxFLow;

    public LikwidacjaKanalu(int maxFLow) {
        this.maxFLow = maxFLow;
    }

    public int[][] likwidacja(int[][] graph, int rozmiar) {
        int mFlow;
        boolean czy = true;
        int[][] rGraph = new int[rozmiar][rozmiar];
        for (int u = 0; u < rozmiar; u++) {
            for (int v = 0; v < rozmiar; v++) {
                rGraph[u][v] = graph[u][v];
            }
        }
        MaxFlow max = new MaxFlow(rozmiar);
        mFlow = max.fordFulkerson(graph, 0, rozmiar - 1);
        for (int i = rozmiar-1; i >-1 ; i--) {
            for (int j = rozmiar-1; j >-1; j--) {
                while (czy && rGraph[i][j] != 0) {
                    rGraph[i][j] -= 1;
                    if (max.fordFulkerson(rGraph, 0, rozmiar - 1) != mFlow) {
                        rGraph[i][j] += 1;
                        czy = false;
                    }
                }
                czy = true;
            }
        }

        return rGraph;
    }

    public int porownaj(int[][] graph, int[][] graph2, int rozmiar) {
        int x = 0;
        int y;
        for (int i = 0; i < rozmiar; i++) {
            for (int j = 0; j < rozmiar; j++) {
                y = graph[i][j] - graph2[i][j];
                x += y;
            }
        }
        return x;
    }

    public void podsumuj(int[][] graph, int rozmiar) {
        String s = " ";
        String zmiana = " ";
        String usuniete = " ";
        int[][] rGraph = likwidacja(graph, rozmiar);
        for (int i = 0; i < rozmiar; i++) {
            for (int j = 0; j < rozmiar; j++) {
                s += rGraph[i][j];
                s += " ";
            }
            System.out.println(s);
            s = " ";
        }
        MaxFlow max = new MaxFlow(rozmiar);
        //System.out.println(max.fordFulkerson(graph, 0, rozmiar-1));
        int roznica = porownaj(graph, rGraph, rozmiar);
        System.out.println("Odzyskano " + roznica + " metrow rury miedzianej");
  
        for (int k = 0; k < rozmiar; k++) {
            for (int j = 0; j < rozmiar; j++) {
                if (graph[k][j] != rGraph[k][j]) {
                    zmiana += k;
                zmiana += "->";
                zmiana +=j;
                zmiana+="; ";
                }

            }
        }
        System.out.println("Zmieniono kanaly" + zmiana);

        for (int j = 0; j < rozmiar-1; j++) {
            for (int i = 0; i < rozmiar; i++) {
                if (rGraph[j][i]==0&& graph[j][i] != rGraph[j][i]) {
                    usuniete += j;
                    usuniete += "->";
                    usuniete += i;
                    usuniete += "; ";
                }
        
            }

         
        }
        System.out.println("Usunieto kanaly" + usuniete );
        
        System.out.println("Zysk: "+roznica*180);

    }

}
