package ex1.tests;


import ex1.src.WGraph_Algo;
import ex1.src.WGraph_DS;
import ex1.src.weighted_graph;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AlgoTest {

    @Test
    void getGraph(){

        WGraph_DS g = new WGraph_DS();
        g.addNode(5);
        WGraph_Algo ga = new WGraph_Algo();
        ga.init(g);
        assertEquals(ga.getGraph(),g);

    }

    @Test
    void copy(){

        weighted_graph g0 = new WGraph_DS();
        g0.addNode(5);
        WGraph_Algo ga = new WGraph_Algo();
        ga.init(g0);
        weighted_graph g1 = ga.copy();
        assertEquals(g0,g1);
        g0.removeNode(5);
        assertNotEquals(g0,g1);

    }
    @Test
    void isConnected(){

        WGraph_DS g = new WGraph_DS();
        g.addNode(1);
        g.addNode(2);
        g.addNode(3);
        g.addNode(4);    //GRAPH WITHOUT EDGES//
        WGraph_Algo ga = new WGraph_Algo();
        ga.init(g);
        assertFalse(ga.isConnected());
        g.connect(1,2,4);
        g.connect(2,3,5);
        g.connect(3,4,6); //CONNECT THE GRAPH//
        ga.init(g);
        assertTrue(ga.isConnected());

    }
    @Test
    void shortestPathDis(){

        WGraph_DS g = new WGraph_DS();
        g.addNode(1);
        g.addNode(2);
        g.addNode(3);
        WGraph_Algo ga = new WGraph_Algo();
        ga.init(g);
        assertEquals(Double.POSITIVE_INFINITY,ga.shortestPathDist(1,2));   //NO PATH RETURN INFINITY//
        g.connect(1,2,3);
        g.connect(2,3,6);
        ga.init(g);
        assertEquals(9,ga.shortestPathDist(1,3));  //SHORTEST PATH IS 3//

    }

    @Test
    void saveLoad(){

        WGraph_DS g = new WGraph_DS();
        g.addNode(1);
        g.addNode(2);
        g.addNode(3);
        g.connect(1,2,8);
        g.connect(2,3,9);
        g.connect(3,1,19);
        WGraph_Algo ga1 = new WGraph_Algo();
        ga1.init(g);
        String file = "g0.obj";
        ga1.save(file);   //SAVE THE GRAPH TO g0.obj//
        WGraph_Algo ga2 = new WGraph_Algo();
        ga2.load(file);  //LOAD THE GRAPH FROM g0.obj//
        assertEquals(ga1.getGraph().nodeSize(),ga2.getGraph().nodeSize());
        assertEquals(ga1.getGraph().edgeSize(),ga2.getGraph().edgeSize());
        assertEquals(ga1.getGraph().getV().toString(),ga2.getGraph().getV().toString());
        assertEquals(ga1.getGraph().getEdge(1,2),ga2.getGraph().getEdge(1,2));
        assertEquals(ga1.getGraph().getEdge(2,3),ga2.getGraph().getEdge(2,3));
        assertEquals(ga1.getGraph().getEdge(3,1),ga2.getGraph().getEdge(3,1));//ga1 AND ga2 HAVE THE SAME GRAPH//

    }

}
