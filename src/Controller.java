import processing.core.*;
import processing.opengl.PGL;
import processing.opengl.PGraphicsOpenGL;

import javax.media.opengl.GL2;

import oscP5.*;
import netP5.*;


public class Controller extends PApplet {



	// osc stuff
	OscP5 oscP5;
	NetAddress myRemoteLocation;
	String[] maxVarNames = {
			"id", "flag", "amp", "freq"
	};
	int noteID = 0;
	int id, flag;
	float amp, freq;


	MeatMesh mesh;

	public Controller() {
	}

	public void setup(){
		size(displayWidth, displayHeight, P3D);
		// eases consistency with Java Color(1.0f, 1.0f, 1.0f, 1.0f)
		colorMode(RGB, 1.0f);

		// crank up anti-aliasing
		smooth(8);
		// ensure objects fly completely by camera before being clipped
		frustum(-2, 2, -2*(float)displayHeight/(float)displayWidth, 2*(float)displayHeight/(float)displayWidth, 1, 4000);

		mesh = new MeatMesh(this, new PVector(width/2.0f, height/2.0f, -500), 100, new Dimension3(2000, 2000, 2000));
		//mesh.jitter();

		//start oscP5
		//oscP5 = new OscP5(this, 12001);
		//  myRemoteLocation = new NetAddress("127.0.0.1", 12000);
	}

	public void draw(){
		background(.4f, 0, 0);
		//background(0.0f);
		//translate(width/2, height/2, 0);
		lightSpecular(1.0f, 1.0f, 1.0f);
		directionalLight(1.0f, 1.0f, 1.0f, -.1f, -.2f, -1.0f);
		specular(255, 255, 255);
		mesh.start();
		mesh.render();
		//mesh.balls.get(0).pos.add(new PVector(random(-3.4f, 3.4f), random(-3.4f, 3.4f), random(-3.4f, 3.4f)));
		//mesh.blocks[0].rot = new PVector(random(-3.4f, 3.4f), random(-3.4f, 3.4f), random(-3.4f, 3.4f));
	}

	public void mousePressed(){
		mesh.jitter();
	}


	void oscEvent(OscMessage theOscMessage) {

		if(theOscMessage.checkAddrPattern("/aurora/layer1")==true) {
			Object[] objs = theOscMessage.arguments();
			//println(objs);
		}
	}


	public static void main(String args[]) {
		PApplet.main(new String[] {/*"--present", */"Controller" });
	}

}
