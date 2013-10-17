/** 
 * Generic 2 element container
 * By: Ira Greenberg 
 * December 2010
 */

public class Tuple2<T, U> {
	public T elem0;
	public U elem1;

	public Tuple2(){
	}

	public Tuple2(T elem0, U elem1){
		this.elem0 = elem0;
		this.elem1 = elem1;
	}
}