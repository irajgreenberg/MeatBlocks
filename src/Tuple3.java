/** 
 * Generic 3 element container
 * By: Ira Greenberg 
 * December 2010
 */

public class Tuple3<T, U, V> {
	public T elem0;
	public U elem1;
	public V elem2;

	public Tuple3(){
	}

	public Tuple3(T elem0, U elem1, V elem2){
		this.elem0 = elem0;
		this.elem1 = elem1;
		this.elem2 = elem2;
	}
}