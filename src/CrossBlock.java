import processing.core.*;
import java.util.ArrayList;


public class CrossBlock extends Block {

	public CrossBlock(){
	}

	public CrossBlock(PApplet p, PVector loc, PVector rot, Dimension3 dim, String textureURL){
		super(p, loc, rot, dim, textureURL);
		init();
	}


	@Override
	protected void init() {
		/*
		 *--* 3
		 |	| 2
	  *--*  *--* 1
      |        |
      *--*  *--* 
         |  |
         *--*
		 */
		
		verts = new ArrayList<PVector>();
		faces = new ArrayList<Face3>();
		inds = new ArrayList< Tuple3<Integer, Integer, Integer> >();

//		verts
		// front face
		verts.add(new PVector(dim.w*.166f, dim.h*.166f, dim.d/2));
		verts.add(new PVector(dim.w*.5f, dim.h*.166f, dim.d/2));
		verts.add(new PVector(dim.w*.5f, dim.h*-.166f, dim.d/2));
		verts.add(new PVector(dim.w*.166f, dim.h*-.166f, dim.d/2));
		verts.add(new PVector(dim.w*.166f, dim.h*-.5f, dim.d/2));
		verts.add(new PVector(dim.w*-.166f, dim.h*-.5f, dim.d/2));

		verts.add(new PVector(dim.w*-.166f, dim.h*-.166f, dim.d/2));
		verts.add(new PVector(dim.w*-.5f, dim.h*-.166f, dim.d/2));
		verts.add(new PVector(dim.w*-.5f, dim.h*.166f, dim.d/2));
		verts.add(new PVector(dim.w*-.166f, dim.h*.166f, dim.d/2));
		verts.add(new PVector(dim.w*-.166f, dim.h*.5f, dim.d/2));
		verts.add(new PVector(dim.w*.166f, dim.h*.5f, dim.d/2));

		// back face
		verts.add(new PVector(dim.w*.166f, dim.h*.166f, -dim.d/2));
		verts.add(new PVector(dim.w*.5f, dim.h*.166f, -dim.d/2));
		verts.add(new PVector(dim.w*.5f, dim.h*-.166f, -dim.d/2));
		verts.add(new PVector(dim.w*.166f, dim.h*-.166f, -dim.d/2));
		verts.add(new PVector(dim.w*.166f, dim.h*-.5f, -dim.d/2));
		verts.add(new PVector(dim.w*-.166f, dim.h*-.5f, -dim.d/2));

		verts.add(new PVector(dim.w*-.166f, dim.h*-.166f, -dim.d/2));
		verts.add(new PVector(dim.w*-.5f, dim.h*-.166f, -dim.d/2));
		verts.add(new PVector(dim.w*-.5f, dim.h*.166f, -dim.d/2));
		verts.add(new PVector(dim.w*-.166f, dim.h*.166f, -dim.d/2));
		verts.add(new PVector(dim.w*-.166f, dim.h*.5f, -dim.d/2));
		verts.add(new PVector(dim.w*.166f, dim.h*.5f, -dim.d/2));

		// front face (CCW)
		inds.add(new Tuple3<Integer, Integer, Integer>(0, 1, 2));
		inds.add(new Tuple3<Integer, Integer, Integer>(0, 2, 3));
		inds.add(new Tuple3<Integer, Integer, Integer>(6, 3, 4));
		inds.add(new Tuple3<Integer, Integer, Integer>(6, 4, 5));
		inds.add(new Tuple3<Integer, Integer, Integer>(9, 0, 3));
		inds.add(new Tuple3<Integer, Integer, Integer>(9, 3, 6));
		inds.add(new Tuple3<Integer, Integer, Integer>(10, 11, 0));
		inds.add(new Tuple3<Integer, Integer, Integer>(10, 0, 9));
		inds.add(new Tuple3<Integer, Integer, Integer>(8, 9, 6));
		inds.add(new Tuple3<Integer, Integer, Integer>(8, 6, 7));
		// Back Face
		inds.add(new Tuple3<Integer, Integer, Integer>(12, 15, 13));
		inds.add(new Tuple3<Integer, Integer, Integer>(15, 14, 13));
		inds.add(new Tuple3<Integer, Integer, Integer>(15, 17, 16));
		inds.add(new Tuple3<Integer, Integer, Integer>(15, 18, 17));
		inds.add(new Tuple3<Integer, Integer, Integer>(23, 21, 12));
		inds.add(new Tuple3<Integer, Integer, Integer>(23, 22, 21));
		inds.add(new Tuple3<Integer, Integer, Integer>(21, 20, 19));
		inds.add(new Tuple3<Integer, Integer, Integer>(21, 19, 18));
		inds.add(new Tuple3<Integer, Integer, Integer>(12, 21, 18));
		inds.add(new Tuple3<Integer, Integer, Integer>(12, 18, 15));

		// sides
		for(int i=0; i<12; ++i){
			if (i<12-1){
				inds.add(new Tuple3<Integer, Integer, Integer>(i, 12+i, 13+i));
				inds.add(new Tuple3<Integer, Integer, Integer>(i, 13+i, 1+i));
			} else {

				// close shape
				inds.add(new Tuple3<Integer, Integer, Integer>(i, 12+i, 12));
				inds.add(new Tuple3<Integer, Integer, Integer>(i, 12, 0));
			}
		}
		
		// compose faces
		for(int i=0; i<inds.size(); ++i){
			faces.add(new Face3(p, verts.get(inds.get(i).elem0), verts.get(inds.get(i).elem1), verts.get(inds.get(i).elem2), textureURL));
		}
	}

}
