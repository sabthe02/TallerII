package sistema.logica;
import java.util.TreeMap;
import java.io.Serializable;
import java.util.Iterator;

public abstract class Diccionario <K,T> implements Serializable{
	
	

	private static final long serialVersionUID = 1L;
	protected TreeMap <K,T> arbol;
	
	public Diccionario () {
		this.arbol = new TreeMap <K,T>();
	}
	
	public boolean member(K clave) {
		return arbol.containsKey(clave);
	}
	
	public T find (String clave) {
		return arbol.get(clave);
	}
	
	public void insert (K clave, T objeto) {
		arbol.put(clave, objeto);
	}
	
	public void delete (K clave) {
		arbol.remove(clave);
	}
	
	public int size ()
	{
		return arbol.size();
	}
	
	public boolean empty() {
		return arbol.isEmpty();
	}

	
}
