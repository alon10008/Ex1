package ex1.src;

import java.util.Collection;
import java.util.HashMap;
import java.util.Vector;

public class WGraph_DS implements weighted_graph {



    HashMap<Integer,NodeInfo> node;
    int edge, mc;
    public class NodeInfo implements node_info, Comparable<node_info>{

        int key;
        double tag;
        HashMap<Integer, node_info> neigh;
        HashMap<Integer,Double> weight;
        String info;
        public NodeInfo(int key) {
            this.key = key;
            neigh = new HashMap<Integer, node_info>();
            weight = new HashMap<Integer,Double>();
            info = new String(""+key);
            this.tag = Double.POSITIVE_INFINITY;
        }
        public int getKey() {return this.key;}
        public String getInfo()
        {
            String s = new String(""+this.key+"|");
            Object arr[] = this.neigh.keySet().toArray();
            for(int i=0 ; i<arr.length ; i++)
            {
                s += this.neigh.get((int)arr[i]).getKey()+"-"+this.weight.get((int)arr[i]) + "/";
            }
            return s;
        }
        public void setInfo(String s)
        {
            this.info = s;
        }
        public double getTag()
        {
            return tag;
        }
        public void setTag(double t)
        {
            this.tag = t;
        }
        public String toString()
        {
            return "" + this.key;
        }
        public int compareTo(node_info a) {
            if(this.getTag()<a.getTag())
                return -1;
            else if(a.getTag()<this.getTag())
                return 1;
            else
                return 1;
        }
    } ///////////-----end class NodeInfo-----///////////

    public WGraph_DS()
    {
        this.node = new HashMap<Integer,NodeInfo>();
        this.edge = 0;
        this.mc = 0;
    }

    public node_info getNode(int key)
    {
        return this.node.get(key);
    }

    @Override
    public boolean hasEdge(int node1, int node2)
    {
        if(node1==node2)
            return true;
        return this.node.get(node1).neigh.containsKey(node2);
    }

    @Override
    public double getEdge(int node1, int node2)
    {
        if(node1==node2)
            return 0;
        else if(!this.node.get(node1).weight.containsKey(node2))
            return -1;
        return this.node.get(node1).weight.get(node2);
    }
    @Override
    public void addNode(int key) {
        if(this.node.containsKey(key))
            return;
        NodeInfo n = new NodeInfo(key);
        this.node.put(n.key,n);
        this.mc++;
    }

    @Override
    public void connect(int node1, int node2, double w) {
        if(node1==node2)   //no edge to the same vertex
            return;        //do nothing
        if(!this.node.containsKey(node1) || !this.node.containsKey(node2)) //if the graph not have one of the vertex
            return;                                                        //do nothing
        else if(this.node.get(node1).neigh.containsKey(node2))
        {
            this.node.get(node1).weight.remove(node2);                 //  if the edge already exist
            this.node.get(node1).weight.put(node2,w);                 //   only update the new weight.
            this.node.get(node2).weight.remove(node1);
            this.node.get(node2).weight.put(node1,w);
            return;
        }
        this.node.get(node1).neigh.put(node2,this.node.get(node2));
        this.node.get(node2).neigh.put(node1,this.node.get(node1));
        this.node.get(node1).weight.put(node2,w);
        this.node.get(node2).weight.put(node1,w);
        this.edge++;
        this.mc++;
    }

    @Override
    public Collection<node_info> getV()
    {
        Collection<node_info> v = new Vector<node_info>();
        Object[] arr = this.node.keySet().toArray();
        for(int i=0 ; i<arr.length ; i++)
            v.add(this.node.get((int)arr[i]));
        return v;
    }

    @Override
    public Collection<node_info> getV(int node_id)
    {
        Collection<node_info> v = new Vector<node_info>();
        Object[] arr = this.node.get(node_id).neigh.keySet().toArray();
        for(int i=0 ; i<arr.length ; i++)
            v.add(this.node.get((int)arr[i]));
        return v;
    }

    @Override
    public node_info removeNode(int key)
    {
        if(!this.node.containsKey(key))
            return null;
        NodeInfo ni = this.node.get(key);
        Object arr[] = ni.neigh.keySet().toArray();
        for(int i=0 ; i<arr.length ; i++)
        {
            this.node.get((int)arr[i]).neigh.remove(key);
            ni.weight.remove((int)arr[i]);
            ni.neigh.remove((int)arr[i]);
            this.node.get((int)arr[i]).weight.remove(key);
            this.edge--;
        }
        this.node.remove(key);
        this.mc++;
        return ni;
    }

    @Override
    public void removeEdge(int node1, int node2)
    {
        if(!this.node.containsKey(node1) || !this.node.containsKey(node2) || !this.node.get(node1).weight.containsKey(node2))
            return;
        this.node.get(node1).weight.remove(node2);
        this.node.get(node1).neigh.remove(node2);
        this.node.get(node2).weight.remove(node1);
        this.node.get(node2).neigh.remove(node1);
        this.mc--;
        this.edge--;
    }

    @Override
    public int nodeSize()
    {
        return this.node.size();
    }

    @Override
    public int edgeSize()
    {
        return this.edge;
    }

    @Override
    public int getMC()
    {
        return this.mc;
    }

}

