package TaxiBooking.src;

import java.util.LinkedList;

public class Places {
	public int placeId;
	public LinkedList<Taxis> taxiList;
	
	public Places(int placeId)
	{
		this.placeId=placeId;
		taxiList=new LinkedList<Taxis>();
	}
	public static boolean initalTaxis(int custId,int from,int to,int time)
	{
		
		LinkedList<Taxis> taxs = TaxiAndPlaces.taxis;
		int s=taxs.size();
		if(s==0)
			return false;
		
		for(int i=0;i<s;i++)
		{
			Taxis txs=taxs.get(i);
			if(txs.currentTime==0&&txs.totalAmount==0)
			{
				txs.booking(from,to,custId);
				txs.currentTime=time;
				Places plc=TaxiAndPlaces.places.get(from);
				LinkedList<Taxis> tl=plc.taxiList;
				tl.remove(txs);
				return true;
			}
		}
		
		return false;
		
	}
	public static boolean pickPointTaxi(int custId,int from,int to,int time) {
		int min=Integer.MAX_VALUE;
		LinkedList<Places> places=TaxiAndPlaces.places;
		Places pl=places.get(from);
		LinkedList<Taxis> taxs=pl.taxiList;
		int s=taxs.size();
		Taxis fin=null;
		for(int i=0;i<s;i++)
		{
			Taxis txs=taxs.get(i);
			int amount=txs.totalAmount;
			if(txs.currentTime==time)
			{
				if(min>amount)
				{
					min=amount;
					fin=txs;
				}
			}
		}
		if(fin!=null)
		{
			fin.booking(from,to,custId);
			fin.currentTime+=time;
			Places plc=TaxiAndPlaces.places.get(from);
			LinkedList<Taxis> tl=plc.taxiList;
			tl.remove(fin);
			return true;
		}
		return false;
	}
	public static boolean nearestTaxi(int custId,int from,int to,int time) {
		int min=Integer.MAX_VALUE;
		LinkedList<Places> places=TaxiAndPlaces.places;
		int l=places.size();
		for(int j=0;j<l;j++)
		{
			Places pl=places.get(j);
			LinkedList<Taxis> taxs=pl.taxiList;
			int s=taxs.size();
			Taxis fin=null;
			for(int i=0;i<s;i++)
			{
				Taxis txs=taxs.get(i);
				int amount=txs.totalAmount;
				if(txs.currentTime==time)
				{
					if(min>amount)
					{
						min=amount;
						fin=txs;
					}
				}
			}
			if(fin!=null)
			{
				fin.booking(from,to,custId);
				int tp=Math.abs(from-j);
				fin.currentTime+=time+tp;
				Places plc=TaxiAndPlaces.places.get(j);
				LinkedList<Taxis> tl=plc.taxiList;
				tl.remove(fin);
				return true;
			}
		}
		
		return false;
	}

}
