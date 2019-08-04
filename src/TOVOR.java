import java.util.Arrays;
import java.util.LinkedList;

import aiinterface.AIInterface;
import aiinterface.CommandCenter;
import struct.FrameData;
import struct.GameData;
import struct.Key;
import enumerate.Action;
import simulator.Simulator;

public class TOVOR implements AIInterface {
	
	Key key;
	CommandCenter cc;
	boolean cPlayer;
	FrameData fd;
	GameData gd;

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

	@Override
	public void getInformation(FrameData arg0, boolean arg1) {
		// TODO Auto-generated method stub
		fd = arg0;
		cc.setFrameData(fd, cPlayer);
	}

	@Override
	public int initialize(GameData arg0, boolean arg1) {
		// TODO Auto-generated method stub
		key = new Key();
		cc = new CommandCenter();
		cPlayer = arg1;
		fd = new FrameData();
		gd = arg0;
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
		if(!fd.getEmptyFlag() && fd.getRemainingFramesNumber()>0) 
		{
			Action action = fd.getCharacter(cPlayer).getAction();
			if(fd.getCharacter(cPlayer).getEnergy() > 150 && action == Action.STAND) 
			{
				if (fd.getDistanceX() > 300)
				{
					cc.commandCall(Action.STAND_D_DF_FC.toString());
				}
				else
				{
					cc.commandCall(Action.BACK_JUMP.toString());
				}
			}
			else 
			{
				if (fd.getDistanceX() > 100)
				{
					cc.commandCall("6");
				}
				else
				{
					if(action == Action.STAND)
					{
						LinkedList<Action> myact = new LinkedList<Action>(Arrays.asList(Action.STAND_FB));
						LinkedList<Action> opact =  new LinkedList<Action>(Arrays.asList(Action.STAND));
						Simulator sim = new Simulator(gd);
						FrameData sd = sim.simulate(fd, cPlayer, myact, opact, 14);
						if(sd.getCharacter(cPlayer).isHitConfirm())
						{
							cc.commandCall(Action.STAND_FB.toString());
						}
						else
						{
							cc.commandCall("B");
						}
					}
				}
			}
			key = cc.getSkillKey();
		}

	}

	@Override
	public void roundEnd(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
