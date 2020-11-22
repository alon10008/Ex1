package ex1.src;

import java.io.*;
import java.util.*;

public class WGraph_Algo implements weighted_graph_algorithms {

    WGraph_DS wg;

    public void init(weighted_graph g) {
        this.wg = (WGraph_DS) g;
    }

    @Override
    public weighted_graph getGraph()
    {
        return this.wg;
    }

    @Override

    public weighted_graph copy() //DEEP COPY//
    {
        WGraph_DS w_g = new WGraph_DS();
        w_g.node = new HashMap(this.wg.node);
        w_g.mc = this.wg.getMC();
        w_g.edge = this.wg.edgeSize();
        return w_g;
    }
    @Override

    public boolean isConnected()   // dijkstra algorithm
    {
        if(this.wg.node.size()==1 || this.wg.node.size()==0)
            return true;
        PriorityQueue<node_info> pq = new PriorityQueue<node_info>();
        Object[] arr = this.wg.node.keySet().toArray();
        double distance;
        int size = this.wg.nodeSize();
        for(int i=0 ; i<arr.length ; i++)
        {
            this.wg.node.get((int)arr[i]).setTag(Double.POSITIVE_INFINITY);
            pq.add(this.wg.node.get((int)arr[i]));
            if(size<(int)arr[i])
                size = (int)arr[i];
        }
        this.wg.node.get((int)arr[0]).setTag(0);
        int[] parent = new int[size+1];
        boolean[] visited = new boolean[size+1];
        for(int i=0 ; i<visited.length ; i++)
        {
            visited[i] = false;
            parent[i] = -1;
        }
        while(!pq.isEmpty())
        {
            node_info v = pq.poll();
            arr = this.wg.node.get(v.getKey()).neigh.keySet().toArray();
            for(int i=0 ; i<arr.length ; i++)
            {
                node_info u = this.wg.node.get(v.getKey()).neigh.get((int)arr[i]);
                if(!visited[u.getKey()])
                {
                    distance = v.getTag() + this.wg.node.get(v.getKey()).weight.get(u.getKey());
                    if(u.getTag() > distance)
                    {
                        u.setTag(distance);
                        parent[u.getKey()] = v.getKey();
                        pq.remove(u);
                        pq.add(u);
                    }
                }
            }
            visited[v.getKey()] = true;
        }
        arr = this.wg.node.keySet().toArray();
        for(int i=1 ; i<arr.length ; i++)
            if(parent[(int)arr[i]]==-1)
                return false;
        return true;
    }

    @Override
    public double shortestPathDist(int src, int dest) //dijkstra algorithm
    {
        PriorityQueue<node_info> pq = new PriorityQueue<node_info>();
        Object[] arr = this.wg.node.keySet().toArray();
        double distance;
        int size = this.wg.nodeSize();
        for(int i=0 ; i<arr.length ; i++)
        {
            this.wg.node.get((int)arr[i]).setTag(Double.POSITIVE_INFINITY);
            pq.add(this.wg.node.get((int)arr[i]));
            if(size<(int)arr[i])
                size = (int)arr[i];
        }
        this.wg.node.get(src).setTag(0);
        pq.remove(this.wg.node.get(src));
        pq.add(this.wg.node.get(src));
        int[] parent = new int[size+1];
        boolean[] visited = new boolean[size+1];
        for(int i=0 ; i<visited.length ; i++)
        {
            visited[i] = false;
            parent[i] = -1;
        }
        while(!pq.isEmpty())
        {
            node_info v = pq.poll();
            arr = this.wg.node.get(v.getKey()).neigh.keySet().toArray();
            for(int i=0 ; i<arr.length ; i++)
            {
                node_info u = this.wg.node.get(v.getKey()).neigh.get((int)arr[i]);
                if(!visited[u.getKey()])
                {
                    distance = v.getTag() + this.wg.node.get(v.getKey()).weight.get(u.getKey());
                    if(u.getTag() > distance)
                    {
                        u.setTag(distance);
                        parent[u.getKey()] = v.getKey();
                        pq.remove(u);
                        pq.add(u);
                    }
                }
            }
            visited[v.getKey()] = true;
        }
        return this.wg.node.get(dest).getTag();
    }

    @Override
    public List<node_info> shortestPath(int src, int dest) //dijkstra algorithm
    {
        Stack<node_info> s = new Stack<node_info>();
        if(src==dest)
            return s;
        PriorityQueue<node_info> pq = new PriorityQueue<node_info>();
        Object[] arr = this.wg.node.keySet().toArray();
        double distance;
        int size = this.wg.nodeSize();
        for(int i=0 ; i<arr.length ; i++)
        {
            this.wg.node.get((int)arr[i]).setTag(Double.POSITIVE_INFINITY);
            pq.add(this.wg.node.get((int)arr[i]));
            if(size<(int)arr[i])
                size = (int)arr[i];
        }
        this.wg.node.get(src).setTag(0);
        int[] parent = new int[size+1];
        boolean[] visited = new boolean[size+1];
        for(int i=0 ; i<visited.length ; i++)
        {
            visited[i] = false;
            parent[i] = -1;
        }
        while(!pq.isEmpty())
        {
            node_info v = pq.poll();
            arr = this.wg.node.get(v.getKey()).neigh.keySet().toArray();
            for(int i=0 ; i<arr.length ; i++)
            {
                node_info u = this.wg.node.get(v.getKey()).neigh.get((int)arr[i]);
                if(!visited[u.getKey()])
                {
                    distance = v.getTag() + this.wg.node.get(v.getKey()).weight.get(u.getKey());
                    if(u.getTag() > distance)
                    {
                        u.setTag(distance);
                        parent[u.getKey()] = v.getKey();
                        pq.remove(u);
                        pq.add(u);
                    }
                }
            }
            visited[v.getKey()] = true;
        }
        s.push(this.wg.node.get(dest));
        int i=dest;
        while((int)parent[i]!=src)
        {
            s.push(this.wg.node.get(parent[i]));
            i = parent[i];
        }
        List<node_info> l = new Vector<node_info>();
        s.push(this.wg.node.get(src));
        while(!s.empty())
            l.add(s.pop());
        return l;
    }

    @Override
    public boolean save(String file) {
        if(this.wg==null)
            return true;
        Object[] arr = this.wg.node.keySet().toArray();
        try
        {
            FileWriter fw = new FileWriter(file);
            PrintWriter pw = new PrintWriter(fw);
            for(int i=0 ; i<arr.length ; i++)
                pw.println(this.wg.node.get((int)arr[i]).getInfo());
            pw.close();
            fw.close();
        }
        catch(IOException e)
        {
            System.out.println("IOException, Graph was not saved!");
            return false;
        }
        return true;
    }

    @Override
    public boolean load(String file) {
        WGraph_DS graph = new WGraph_DS();
        HashMap<Integer,String> nodes = new HashMap<Integer,String>();
        int key;
        String s = new String("");
        try
        {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            while(s!=null)
            {
                s = br.readLine();
                if(s!=null)
                {
                    key =  Integer.parseInt(s.substring(0,s.indexOf('|')));
                    nodes.put(key,s.substring(s.indexOf("|")+1));
                    graph.addNode(key);
                }
            }
            int index;
            Object arr[] = nodes.keySet().toArray();
            for(int i=0 ; i<arr.length ; i++ )
            {
                boolean flag = true;
                s = nodes.get((int)arr[i]);
                if(s.indexOf('|')!=(s.length()-1))
                    while(flag)
                    {
                        graph.connect((int)arr[i],Integer.parseInt(s.substring(0,s.indexOf('-'))),Double.parseDouble(s.substring(s.indexOf('-')+1,s.indexOf('/'))));
                        if(s.indexOf('/')!=(s.length()-1))
                            s = s.substring(s.indexOf('/')+1);
                        else
                            flag = false;
                    }
            }
            br.close();
            fr.close();
            this.init(graph);
        }
        catch(IOException e)
        {
            System.out.println("IOException, Graph was not loaded!");
            return false;
        }
        return true;
    }

}
