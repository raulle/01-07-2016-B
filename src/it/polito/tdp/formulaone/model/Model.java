package it.polito.tdp.formulaone.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.formulaone.db.FormulaOneDAO;

public class Model {
	
	private FormulaOneDAO dao;
	private SimpleDirectedWeightedGraph<Constructor,DefaultWeightedEdge> grafo;
	
	public Model(){
		this.dao= new FormulaOneDAO();
	}
	
	public List<Circuit> getCircuiti(){
		return dao.getAllCircuits();
	}
	
	public void creaGrafo(int circId){
		grafo = new SimpleDirectedWeightedGraph<Constructor,DefaultWeightedEdge>(DefaultWeightedEdge.class);
		Graphs.addAllVertices(grafo, dao.getConstructors(circId));
		
		Map<Integer,Constructor> piloti= new HashMap<Integer,Constructor>();
		for(Constructor d : grafo.vertexSet())
			piloti.put(d.getConstructorId(), d);
		
		List<Integer> id= dao.getRaceId(circId);
		for(Constructor dd : grafo.vertexSet()){
			for(Constructor ddd : grafo.vertexSet()){
				for(Integer i : id){
					Constructor d1= piloti.get(dd.getConstructorId());
					Constructor d2= piloti.get(ddd.getConstructorId());
					double punti1=dao.getPuntiCostruttoreGara(i, d1.getConstructorId());
					double punti2=dao.getPuntiCostruttoreGara(i, d2.getConstructorId());
					if(punti1>punti2){
						if(grafo.addEdge(d1, d2)==null){
							DefaultWeightedEdge e=grafo.getEdge(d1, d2);
							grafo.setEdgeWeight(e, grafo.getEdgeWeight(e)+1);
						}
						else if(grafo.addEdge(d1, d2)!=null){
							DefaultWeightedEdge e=grafo.addEdge(d1, d2);
							grafo.setEdgeWeight(e,1);
						}
						//System.out.println(e);
					}
				}
			}
		}
//		for(DefaultWeightedEdge e : grafo.edgeSet()){
//			System.out.println(grafo.getEdgeWeight(e));
//		}
		//System.out.println(grafo);
	}
	
	public Constructor getBest(int circId){
		if(grafo==null)
			this.creaGrafo(circId);
		Constructor c=null;
		int max=0;
		for(Constructor cc : grafo.vertexSet()){
			int puntiUsc=0;
			int puntiEntr=0;
			int diff=0;
			for(DefaultWeightedEdge e : grafo.outgoingEdgesOf(cc)){
				puntiEntr+= grafo.getEdgeWeight(e);
			}
			for(DefaultWeightedEdge e : grafo.incomingEdgesOf(cc)){
				puntiUsc+= grafo.getEdgeWeight(e);
			}
			diff=puntiEntr-puntiUsc;
			if(diff>max){
				max=diff;
				c=cc;
			}
		}
		System.out.println(c);
		return c;
	}


}
