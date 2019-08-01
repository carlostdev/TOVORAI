import java.util.Random;

import aiinterface.AIInterface;
import struct.FrameData;
import struct.GameData;
import struct.Key;

public class TOVOR implements AIInterface {
	
	Key key;
	Random rand;

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

	@Override
	public void getInformation(FrameData arg0, boolean arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public int initialize(GameData arg0, boolean arg1) {
		// TODO Auto-generated method stub
		rand = new Random();
		key = new Key();
		return 0;
	}

	@Override
	public Key input() {
		// TODO Auto-generated method stub
		return key;
	}

	@Override
	public void processing() {
		// TODO Auto-generated method stub
		key.A = (rand.nextInt(100) > 70);
		key.B = (rand.nextInt(100) > 70);
		key.C = (rand.nextInt(100) > 70);
		key.D = (rand.nextInt(100) > 70);
		key.U = (rand.nextInt(100) > 70);
		key.L = (rand.nextInt(100) > 70);
		key.R = (rand.nextInt(100) > 70);
	}

	@Override
	public void roundEnd(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
