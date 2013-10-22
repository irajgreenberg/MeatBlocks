
import processing.core.*;

import java.util.ArrayList;

public class Block {

	protected PApplet p;
	protected PVector loc, rot;
	protected PVector locSpd=new PVector(), rotSpd=new PVector();
	protected Dimension3 dim;
	protected String textureURL;
	
	// osc dynamics
	protected float baseAlpha = .15f;
	protected float dynamicAlpha = .15f;
	protected Dimension3 dynamicScale = new Dimension3(1,1,1);
	protected PVector dynamicFreq = new PVector();
	protected PVector theta = new PVector();
	protected boolean isFadeable = false;

	// containers
	protected ArrayList<PVector> verts;
	protected ArrayList<Face3> faces;
	protected ArrayList< Tuple3<Integer, Integer, Integer> > inds;

	public Block(){
	}

	public Block(PApplet p, PVector loc, PVector rot, Dimension3 dim, String textureURL){
		this.p = p;
		this.loc = loc;
		this.rot = rot;
		this.dim = dim;
		this.textureURL = textureURL;
		init();
	}

	public void setDynamics(PVector locSpd, PVector rotSpd){
		this.locSpd = locSpd;
		this.rotSpd = rotSpd;
	}
	protected void init(){
		verts = new ArrayList<PVector>();
		faces = new ArrayList<Face3>();
		inds = new ArrayList< Tuple3<Integer, Integer, Integer> >();

		verts.add(new PVector(dim.w*-.5f, dim.h*-.5f, dim.d*-.5f));
		verts.add(new PVector(dim.w*-.5f, dim.h*-.5f, dim.d*.5f));
		verts.add(new PVector(dim.w*.5f, dim.h*-.5f, dim.d*.5f));
		verts.add(new PVector(dim.w*.5f, dim.h*-.5f, dim.d*-.5f));
		verts.add(new PVector(dim.w*-.5f, dim.h*.5f, dim.d*.5f));
		verts.add(new PVector(dim.w*-.5f, dim.h*.5f, dim.d*-.5f));
		verts.add(new PVector(dim.w*.5f, dim.h*.5f, dim.d*-.5f));
		verts.add(new PVector(dim.w*.5f, dim.h*.5f, dim.d*.5f));


		inds.add(new Tuple3<Integer, Integer, Integer>(0, 5, 4));
		inds.add(new Tuple3<Integer, Integer, Integer>(0, 4, 1));
		inds.add(new Tuple3<Integer, Integer, Integer>(1, 4, 2));
		inds.add(new Tuple3<Integer, Integer, Integer>(2, 4, 7));
		inds.add(new Tuple3<Integer, Integer, Integer>(2, 7, 6));
		inds.add(new Tuple3<Integer, Integer, Integer>(2, 6, 3));
		inds.add(new Tuple3<Integer, Integer, Integer>(0, 3, 5));
		inds.add(new Tuple3<Integer, Integer, Integer>(3, 6, 5));
		inds.add(new Tuple3<Integer, Integer, Integer>(0, 1, 2));
		inds.add(new Tuple3<Integer, Integer, Integer>(0, 2, 3));
		inds.add(new Tuple3<Integer, Integer, Integer>(4, 5, 6));
		inds.add(new Tuple3<Integer, Integer, Integer>(4, 6, 7));

		// compose faces
		for(int i=0; i<inds.size(); ++i){
			faces.add(new Face3(p, verts.get(inds.get(i).elem0), verts.get(inds.get(i).elem1), verts.get(inds.get(i).elem2), textureURL));
		}

	}
	public void render(){
		p.pushMatrix();
		p.translate(loc.x, loc.y, loc.z);
		p.scale(1.0f+p.abs(p.sin(theta.x)*dynamicScale.w), 
				1.0f+p.abs(p.sin(theta.y)*dynamicScale.h), 
				1.0f+p.abs(p.sin(theta.z)*dynamicScale.d));
		theta.add(dynamicFreq);
		
		
		p.rotateX(rot.x);
		p.rotateY(rot.y);
		p.rotateZ(rot.z);

		for(int i=0; i<faces.size(); ++i){
			faces.get(i).render();
		}
		p.popMatrix();
		loc.add(locSpd);
		rot.add(rotSpd);
	}
	
	public void setDynamicScale(Dimension3 dynamicScale){
		this.dynamicScale = dynamicScale;
	}
	
	public void setDynamicAlpha(float dynamicAlpha){
		this.dynamicAlpha = dynamicAlpha;
	}
	
	public void setDynamicFreq(PVector dynamicFreq){
		this.dynamicFreq = dynamicFreq;
	}

	public void setIsFadeable(boolean isFadeable){
		this.isFadeable = isFadeable;
	}


}
