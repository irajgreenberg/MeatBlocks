import processing.core.*;
import java.util.ArrayList;


public class LBlock extends Block {

	public float lRatio;
	public float thicknessRatio;
	
	public LBlock(){
	}

	public LBlock(PApplet p, PVector loc, PVector rot, Dimension3 dim, String textureURL, 
			float thicknessRatio, float lRatio){
		super(p, loc, rot, dim, textureURL);
		// ration between two els
		this.lRatio = lRatio;
		// controls thickness
		this.thicknessRatio = thicknessRatio;
		init();
	}


	@Override
	protected void init() {
	  /*
	  *---*
	  |   |
	  |	  | 
	  *   *----* 
      |        |
      *--------* 
	  */
		
		verts = new ArrayList<PVector>();
		faces = new ArrayList<Face3>();
		inds = new ArrayList< Tuple3<Integer, Integer, Integer> >();

//		verts
		// front face
		verts.add(new PVector(dim.w*.5f*lRatio, dim.h*thicknessRatio, dim.d/2));
		verts.add(new PVector(-dim.w*thicknessRatio, dim.h*thicknessRatio, dim.d/2));
		verts.add(new PVector(-dim.w*thicknessRatio, dim.h*-.5f, dim.d/2));
		verts.add(new PVector(-dim.w*.5f, dim.h*-.5f, dim.d/2));
		verts.add(new PVector(-dim.w*.5f, dim.h*.5f, dim.d/2));
		verts.add(new PVector(dim.w*.5f*lRatio, dim.h*.5f, dim.d/2));
		
		verts.add(new PVector(dim.w*.5f*lRatio, dim.h*thicknessRatio, -dim.d/2));
		verts.add(new PVector(-dim.w*thicknessRatio, dim.h*thicknessRatio, -dim.d/2));
		verts.add(new PVector(-dim.w*thicknessRatio, dim.h*-.5f, -dim.d/2));
		verts.add(new PVector(-dim.w*.5f, dim.h*-.5f, -dim.d/2));
		verts.add(new PVector(-dim.w*.5f, dim.h*.5f, -dim.d/2));
		verts.add(new PVector(dim.w*.5f*lRatio, dim.h*.5f, -dim.d/2));


		// front face (CCW)
		inds.add(new Tuple3<Integer, Integer, Integer>(0, 1, 4));
		inds.add(new Tuple3<Integer, Integer, Integer>(0, 4, 5));
		inds.add(new Tuple3<Integer, Integer, Integer>(2, 3, 4));
		inds.add(new Tuple3<Integer, Integer, Integer>(2, 4, 1));
		
		// Back Face
		inds.add(new Tuple3<Integer, Integer, Integer>(6, 10, 7));
		inds.add(new Tuple3<Integer, Integer, Integer>(6, 11, 10));
		inds.add(new Tuple3<Integer, Integer, Integer>(9, 8, 7));
		inds.add(new Tuple3<Integer, Integer, Integer>(9, 7, 10));


		// sides
		for(int i=0; i<6; ++i){
			if (i<6-1){
				inds.add(new Tuple3<Integer, Integer, Integer>(i, 6+i, 7+i));
				inds.add(new Tuple3<Integer, Integer, Integer>(i, 7+i, 1+i));
			} else {

				// close shape
				inds.add(new Tuple3<Integer, Integer, Integer>(i, 6+i, 6));
				inds.add(new Tuple3<Integer, Integer, Integer>(i, 6, 0));
			}
		}
		
		// compose faces
		for(int i=0; i<inds.size(); ++i){
			faces.add(new Face3(p, verts.get(inds.get(i).elem0), verts.get(inds.get(i).elem1), verts.get(inds.get(i).elem2), textureURL));
		}
	}

}
