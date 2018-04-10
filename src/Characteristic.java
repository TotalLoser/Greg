public class Characteristic
{
	private String name;
	private	String data;
	private String type;
	public Characteristic(String tname, String tdata)
	{
		this(tname, "none", tdata);
	}
	public Characteristic(String tname, String ttype, String tdata)
	{
		name = tname.trim().toLowerCase();
		type = ttype.trim().toLowerCase();
		data = tdata.trim().toLowerCase();

	}
	public Characteristic(String[] parsed)
	{
		if (parsed.length == 2)
		{
			name = parsed[0].trim().toLowerCase();
			type = parsed[0].trim().toLowerCase();
			data = parsed[1].trim().toLowerCase();
		}
		else if(parsed.length == 3)
		{
			name = parsed[0].trim().toLowerCase();
			type = parsed[1].trim().toLowerCase();
			data = parsed[2].trim().toLowerCase();
		}
	}
	public Characteristic()
	{
		name = "none";
		data = "none";
		type = "none";
	}
	public String getName()
	{
		return name;
	}
	public String getData()
	{
		return data;
	}
	public String getType()
	{
		return type;
	}
	public String toString()
	{
		if(type.equals("stat"))
			return name + " : " + data + " mod : " + mod(Integer.parseInt(data)) ; 
		return name + " : " + data;
	}
	public int mod(int statval)
	{
		return (statval / 2) - 5;
	}
}
