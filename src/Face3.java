/** 
 * Simple triangle face class
 * encapsulating three PVectors.
 * Includes own getNormal method.
 * By: Ira Greenberg 
 * December 2010
 */

import processing.core.*;

public class Face3 {
	PApplet p;
	public PVector v0, v1, v2;
	public PVector[] vs;
	public PVector n;
	String textureURL;
	PImage img;

	public Face3() {
	}

	public Face3(PApplet p, PVector v0, PVector v1, PVector v2) {
		this.p = p;
		this.v0 = v0;
		this.v1 = v1;
		this.v2 = v2;
		vs = new PVector[] {
				v0, v1, v2
		};
		p.fill(1.0f, 1.0f, 1.0f, 1.0f);
	}

	public Face3(PApplet p, PVector v0, PVector v1, PVector v2, String textureURL) {
		this.p = p;
		this.v0 = v0;
		this.v1 = v1;
		this.v2 = v2;
		this.textureURL = textureURL;
		img = p.loadImage(textureURL);
		vs = new PVector[] {
				v0, v1, v2
		};
		
		p.fill(1.0f, 1.0f, 1.0f, 1.0f);
	}

	public PVector getNormal() {
		n = new PVector();
		n = PVector.cross(PVector.sub(v1, v2), PVector.sub(v0, v2), null);
		n.normalize();
		return(n);
	}

	public void render() {
		if (img != null){
			p.textureMode(p.NORMAL);
			p.beginShape(p.TRIANGLES);
			p.texture(img);
			p.vertex(vs[0].x, vs[0].y, vs[0].z, 0, 1);
			p.vertex(vs[1].x, vs[1].y, vs[1].z, 1, 1);
			p.vertex(vs[2].x, vs[2].y, vs[2].z, 1, 0);
			p.endShape();
		} else {
			p.beginShape(p.TRIANGLES);
			p.vertex(vs[0].x, vs[0].y, vs[0].z);
			p.vertex(vs[1].x, vs[1].y, vs[1].z);
			p.vertex(vs[2].x, vs[2].y, vs[2].z);
			p.endShape();
		}
	}

	public PVector getCentroid() {
		PVector centroid = new PVector();
		centroid.set(v0);
		centroid.add(v1);
		centroid.add(v2);
		centroid.div(3);
		return centroid;
	}
	
	public void setTexture(String textureURL){
		this.textureURL = textureURL;
	}
}
