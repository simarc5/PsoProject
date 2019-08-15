/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

/**
 *
 * @author kahma
 */
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Dijkstra {

  // Keep a fast index to nodes in the map
  private Map<String, Vertex> vertexNames;

  /**
   * Construct an empty Dijkstra with a map. The map's key is the name of a vertex
   * and the map's value is the vertex object.
   */
  public Dijkstra() {
    vertexNames = new HashMap<String, Vertex>();
  }

  /**
   * Adds a vertex to the dijkstra. Throws IllegalArgumentException if two vertices
   * with the same name are added.
   * 
   * @param v
   *          (Vertex) vertex to be added to the dijkstra
   */
  public void addVertex(Vertex v) {
    if (vertexNames.containsKey(v.name))
      throw new IllegalArgumentException("Cannot create new vertex with existing name.");
    vertexNames.put(v.name, v);
  }

  /**
   * Gets a collection of all the vertices in the dijkstra
   * 
   * @return (Collection<Vertex>) collection of all the vertices in the dijkstra
   */
  public Collection<Vertex> getVertices() {
    return vertexNames.values();
  }

  /**
   * Gets the vertex object with the given name
   * 
   * @param name
   *          (String) name of the vertex object requested
   * @return (Vertex) vertex object associated with the name
   */
  public Vertex getVertex(String name) {
    return vertexNames.get(name);
  }

  /**
   * Adds a directed edge from vertex u to vertex v
   * 
   * @param nameU
   *          (String) name of vertex u
   * @param nameV
   *          (String) name of vertex v
   * @param cost
   *          (double) cost of the edge between vertex u and v
   */
  public void addEdge(String nameU, String nameV, Double cost) {
    if (!vertexNames.containsKey(nameU))
      throw new IllegalArgumentException(nameU + " does not exist. Cannot create edge.");
    if (!vertexNames.containsKey(nameV))
      throw new IllegalArgumentException(nameV + " does not exist. Cannot create edge.");
    Vertex sourceVertex = vertexNames.get(nameU);
    Vertex targetVertex = vertexNames.get(nameV);
    Edge newEdge = new Edge(sourceVertex, targetVertex, cost);
    sourceVertex.addEdge(newEdge);
  }

  /**
   * Adds an undirected edge between vertex u and vertex v by adding a directed
   * edge from u to v, then a directed edge from v to u
   * 
   * @param nameU
   *          (String) name of vertex u
   * @param nameV
   *          (String) name of vertex v
   * @param cost
   *          (double) cost of the edge between vertex u and v
   */
  public void addUndirectedEdge(String nameU, String nameV, double cost) {
    addEdge(nameU, nameV, cost);
    addEdge(nameV, nameU, cost);
  }


  /**
   * Dijkstra's Algorithm. 
   * 
   * @param s
   *          (String) starting location name
   */
  public void doDijkstra(String s) {
        // TODO 
      //setting sentinel value
      double MAX_VALUE=1000000;

      for (String u : vertexNames.keySet())
      {
        Vertex node = vertexNames.get(u);
        node.distance=MAX_VALUE;
        node.prev=null;
        node.known=false;

      }
      
      //decrease the distance for the first index
      vertexNames.get(s).distance=0;

      LinkedList<Vertex> vertexList= new LinkedList<>();
          for (String u : vertexNames.keySet())
          {
              //add all the vertices to vertexList
            vertexList.addLast(vertexNames.get(u));
          }
           //while vertexList is not empty
          while(vertexList.size()>0)
          {

            int index= findClosestVertex(vertexList);
            // Vertex v = vertexList.closestVertex();
            Vertex v= vertexList.get(index);
            v.known=true;
            ///once we have discovered a vertex, we remove it from the list
            vertexList.remove(index);

            List<Edge> adjacencyList = v.adjacentEdges;

              for (Edge e : adjacencyList)
              {
                if (!e.target.known)
                {
                    //check if distance needs an update or not
                        //means check total weight from source to vertex v is less than
                        //the current distance value, if yes then update the distance
                  double distanceCost=e.distance;
                  if (v.distance + distanceCost<e.target.distance)
                  {
                    e.target.distance=v.distance + distanceCost;
                    e.target.prev=v;
                  }


                }
              }



          }



          }

           public static int findClosestVertex(LinkedList<Vertex> list)
        {

          double minDist=list.get(0).distance;
          int minIndex=0;
          int i=0;

        for (i=1; i< list.size(); i++)
        { 
          double dist= list.get(i).distance;
          if (dist<minDist)
          {
            minDist=dist;
            minIndex=i;
            // closestVertex=list[i];
          }

        }
        return minIndex;
      }

          

         
        
  /**
   * Returns a list of edges for a path from location s to location t. This will be the
   * shortest path from s to t as prescribed by Dijkstra's algorithm
   * 
   * @param s
   *          (String) starting location
   * @param t
   *          (String) ending location
   * @return (List<Edge>) list of edges from s to t
   */
  public List<Edge> getDijkstraPath(String s, String t) {
    doDijkstra(s);

    // TODO
    Vertex v = vertexNames.get(t);
    LinkedList<Edge> shortestPathEdges = new LinkedList<>();
    Vertex penultimateVertex = null;

    while(v.prev!=null)
    {
      List<Edge> adjacencyList = v.adjacentEdges; 
      for (Edge e : adjacencyList)
      {
        if (e.target.equals(v.prev))
        {
          shortestPathEdges.addFirst(e);
        }
      }

      penultimateVertex = v;
      v=v.prev;
    }
    for (Edge e : v.adjacentEdges)
    {
        if (e.target.equals(penultimateVertex))
        {
          shortestPathEdges.addFirst(e);
        }
    }    
    return shortestPathEdges; 
  }

 

  /**
   * Prints out the adjacency list of the dijkstra for debugging
   */
  public void printAdjacencyList() {
    for (String u : vertexNames.keySet()) {
      StringBuilder sb = new StringBuilder();
      sb.append(u);
      sb.append(" -> [ ");
      for (Edge e : vertexNames.get(u).adjacentEdges) {
        sb.append(e.target.name);
        sb.append("(");
        sb.append(e.distance);
        sb.append(") ");
      }
      sb.append("]");
      System.out.println(sb.toString());
    }
  }


 

}
