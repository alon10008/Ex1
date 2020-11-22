package ex1.tests;

import ex1.src.WGraph_DS;
import ex1.src.node_info;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GraphTest {


    @Test
    void hasEdge(){

        WGraph_DS g = new WGraph_DS();
        g.addNode(1);
        g.addNode(2);
        assertFalse(g.hasEdge(1,2));
        g.connect(1,2,4);
        assertTrue(g.hasEdge(1,2));

    }
    @Test
    void getEdge(){

        WGraph_DS g = new WGraph_DS();
        g.addNode(1);
        g.addNode(2);
        assertEquals(-1,g.getEdge(1,2)); //NOT CONNECTED//
        g.connect(1,2,8);
        assertEquals(8,g.getEdge(1,2));  //CONNECTED//

    }
    @Test
    void connect(){

        WGraph_DS g = new WGraph_DS();
        g.addNode(1);
        g.addNode(2);
        assertFalse(g.hasEdge(1,2)); //NO EDGE//
        g.connect(1,2,4);
        assertTrue(g.hasEdge(1,2));  //HAS EDGE//

    }
    @Test
    void removeNode(){

        WGraph_DS g = new WGraph_DS();
        g.addNode(1);
        node_info a = g.getNode(1);
        assertEquals(a,g.removeNode(1)); //RETURN THE REMOVED NODE//
        assertEquals(null,g.getNode(1)); //RETURN NULL//

    }
    @Test
    void removeEdge(){

        WGraph_DS g = new WGraph_DS();
        g.addNode(1);
        g.addNode(2);
        assertFalse(g.hasEdge(1,2));
        g.connect(1,2,3);
        assertTrue(g.hasEdge(1,2));
        g.removeEdge(2,1);
        assertFalse(g.hasEdge(1,2));

    }

}
