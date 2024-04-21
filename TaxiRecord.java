package TaxiBooking.src;

public class TaxiRecord {
	public int custId,bookingId,amount;
	public char pick,drop;
	TaxiRecord(int custId,int bookingId,int amount,int from,int to)
	{
		this.custId=custId;
		this.bookingId=bookingId;
		this.amount=amount;
		char pick=(char)((int)'a'+from);
		char drop=(char)((int)'a'+to);
		this.pick=pick;
		this.drop=drop;
	}
}
