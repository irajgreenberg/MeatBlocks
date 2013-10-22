import processing.core.*;


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

		mesh = new MeatMesh(this, new PVector(width/2.0f, height/2.0f, -600), /*90*/27, new Dimension3(2000, 2000, 2000));
		//mesh.jitter();

		//start oscP5
		oscP5 = new OscP5(this, 12001);
		//  myRemoteLocation = new NetAddress("127.0.0.1", 12000);
	}

	public void draw(){
		background(0);
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
		if(theOscMessage.checkAddrPattern("/aurora/meatBlock1")==true) {
			Object[] objs = theOscMessage.arguments();
				float amp = (Float)objs[2]*10;
				float freq = map((Float)objs[1], 51, 2722, 0, PI/6.0f);
				mesh.blocks[0].setDynamicFreq(new PVector(freq, freq, freq));
				mesh.blocks[0].setDynamicScale(new Dimension3(amp, amp, amp));
				mesh.blocks[0].setDynamicAlpha(constrain(amp, .3f, 1.0f));
				mesh.jitter(0, random(-5, 5));
				mesh.blocks[0].rot = new PVector(random(-PI/32.0f, PI/32.0f), random(-PI/32.0f, PI/32.0f), random(-PI/32.0f, PI/32.0f));
		}
		if(theOscMessage.checkAddrPattern("/aurora/meatBlock2")==true) {
			Object[] objs = theOscMessage.arguments();
				float amp = (Float)objs[2]*10;
				float freq = map((Float)objs[1], 51, 2722, 0, PI/6.0f);
				mesh.blocks[1].setDynamicFreq(new PVector(freq, freq, freq));
				mesh.blocks[1].setDynamicScale(new Dimension3(amp, amp, amp));
				mesh.blocks[1].setDynamicAlpha(constrain(amp, .3f, 1.0f));
				mesh.jitter(1, random(-5, 5));
				mesh.blocks[1].rot = new PVector(random(-PI/32.0f, PI/32.0f), random(-PI/32.0f, PI/32.0f), random(-PI/32.0f, PI/32.0f));
		}
		if(theOscMessage.checkAddrPattern("/aurora/meatBlock3")==true) {
			Object[] objs = theOscMessage.arguments();
				float amp = (Float)objs[2]*10;
				float freq = map((Float)objs[1], 51, 2722, 0, PI/6.0f);
				mesh.blocks[2].setDynamicFreq(new PVector(freq, freq, freq));
				mesh.blocks[2].setDynamicScale(new Dimension3(amp, amp, amp));
				mesh.blocks[2].setDynamicAlpha(constrain(amp, .3f, 1.0f));
				mesh.jitter(2, random(-5, 5));
				mesh.blocks[2].rot = new PVector(random(-PI/32.0f, PI/32.0f), random(-PI/32.0f, PI/32.0f), random(-PI/32.0f, PI/32.0f));
		}
		if(theOscMessage.checkAddrPattern("/aurora/meatBlock24")==true) {
			// status, index, freq, 
			Object[] objs = theOscMessage.arguments();
			for(int i=0; i<objs.length; ++i){
				int index = (Integer)objs[1];
				float amp = (Float)objs[3];
				if((Integer)objs[0]==1){
					mesh.blocks[index].setDynamicAlpha(1.0f);
					mesh.jitter(index, 3.4f);
					mesh.blocks[index].rot = new PVector(random(-PI/4.0f, PI/4.0f), random(-PI/4.0f, PI/4.0f), random(-PI/4.0f, PI/4.0f));
				}
			}
		}
	}


	public static void main(String args[]) {
		PApplet.main(new String[] {/*"--present", */"Controller" });
	}

}
