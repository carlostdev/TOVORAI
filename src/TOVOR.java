import aiinterface.AIInterface;
import aiinterface.CommandCenter;
import struct.FrameData;
import struct.GameData;
import struct.Key;
import enumerate.Action;

public class TOVOR implements AIInterface {
	
	Key key;
	CommandCenter cc;
	boolean cPlayer;
	FrameData fd;

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
				System.out.println(fd.getCharacter(cPlayer).getAction());
				System.out.println(fd.getDistanceX());
				if (fd.getDistanceX() > 100)
				{
					cc.commandCall("6");
				}
				else
				{
					if(fd.getCharacter(cPlayer).getAction() == Action.STAND)
					{
						cc.commandCall("A");
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
