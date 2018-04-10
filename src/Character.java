import java.util.ArrayList;
/**
 * This class is made to construct and operate on character objects 
 * @author SelloutMillionare
 */
public class Character 
{
	/**
	 * The unparsed full string that is passed to the constructor
	 */
	private String reference;
	/**
	 * Array containing all the characteristic objects for a given instance of a character
	 */
	private ArrayList<Characteristic> stats = new ArrayList<Characteristic>();
	/**
	 * Constructs a character based on a string of data
	 * @param unparsed a String containing all the data that will be turned into characteristics
	 */
	public Character(String unparsed)
	{
		if(unparsed.length() > 1)
		{
			reference = unparsed;
			String[] parsed = unparsed.split("~");
			for(String s : parsed)
			{
				stats.add(new Characteristic(s.toLowerCase().split(":")));
			}
		}
		/*if (parsed.length < 31)
		{
			throw new Exception("less");
		}
		else if(parsed.length > 31)
		{
			throw new Exception("more");
		}
		name = parsed[0];
		alignment = parsed[1];
		ideals = parsed[2];
		bonds = parsed[3];
		flaws = parsed[4];
		background = parsed[5];
		race = parsed[6].split("/")[0];
		subrace = parsed[6].split("/")[1];
		racialTraits = parsed[7].split("/");
		classs = parsed[8];
		classFeatures = parsed[9].split("/");
		proficiencies = parsed[10].split("/");
		skills = parsed[11].split("/");
		XP = Integer.parseInt(parsed[12]);
		proficiencyBonus = Integer.parseInt(parsed[13]);
		HP = Integer.parseInt(parsed[14]);
		hitDice = parsed[15].split("/");
		str = Integer.parseInt(parsed[16]);
		strMod = Integer.parseInt(parsed[17]);
		dex = Integer.parseInt(parsed[18]);
		dexMod = Integer.parseInt(parsed[19]);
		con = Integer.parseInt(parsed[20]);
		conMod = Integer.parseInt(parsed[21]);
		intel = Integer.parseInt(parsed[22]);
		intelMod = Integer.parseInt(parsed[23]);
		wis = Integer.parseInt(parsed[24]);
		wisMod = Integer.parseInt(parsed[25]);
		cha = Integer.parseInt(parsed[26]);
		chaMod = Integer.parseInt(parsed[27]);
		equipment = parsed[28].split("/");
		GP = Integer.parseInt(parsed[29]);;
		AC = Integer.parseInt(parsed[30]);;*/
	}
	/**
	 * prints all stats on new lines
	 */
	public void printAll()
	{
		for(Characteristic c : stats)
		{
			System.out.println(c);
		}
		/*System.out.println("name: " + name);
		System.out.println("alignment: " + alignment);
		System.out.println("ideals: " + ideals);
		System.out.println("bonds: " + bonds);
		System.out.println(flaws);
		System.out.println(background);
		System.out.println(race);
		System.out.println(subrace);
		printArr(racialTraits);
		System.out.println(classs);
		printArr(classFeatures);
		printArr(proficiencies);
		printArr(skills);
		System.out.println(XP);
		System.out.println(proficiencyBonus);
		System.out.println(HP);
		printArr(hitDice);
		System.out.println(str);
		System.out.println(strMod);
		System.out.println(dex);
		System.out.println(dexMod);
		System.out.println(con);
		System.out.println(conMod);
		System.out.println(intel);
		System.out.println(intelMod);
		System.out.println(wis);
		System.out.println(wisMod);
		System.out.println(cha);
		System.out.println(chaMod);
		printArr(equipment);
		System.out.println(GP);
		System.out.println(AC);*/
	}
	/**
	 * Makes a character into a string, containing all data
	 * @return String of all character data
	 */
	public String toString()
	{
		String output = "[";
		for(int i = 0; i < stats.size(); i++)
		{
			if(i + 1 < stats.size())
				output += stats.get(i).getName() + " : " + stats.get(i).getData() + " ~ ";
			else
				output += stats.get(i).getName() + " : " + stats.get(i).getData();
		}
		return output + "]";
	}
	/**
	 * takes any array and prints all the elements
	 * @param arr the array to be printed
	 */
	public static void printArr(String[] arr)
	{
		for(String s : arr)
			System.out.print(s + " ");
		System.out.println();
	}
	/**
	 * Finds all <code>Characteristic</code> objects who's name matches a parameter
	 * @param query name to search for 
	 * @return an array of all <code>Characteristic</code> objects found if any, otherwise returns an emptey <code>Characteristic</code>
	 */
	public Characteristic[] findAll(String query)
	{
		
		int numFound = 0;
		for(Characteristic c : stats)
		{
			if(c.getName().equals(query.trim().toLowerCase()))
			{
				//System.out.println(c.getName() + " = " + query);
				numFound++;
			}
		}
		//System.out.println(numFound);
		if(numFound == 0)
			return new Characteristic[]{};
		Characteristic[] temp = new Characteristic[numFound];
		int position = 0;
		for(Characteristic c : stats)
		{
			if(c.getName().equals(query.trim().toLowerCase()))
			{
				temp[position] = c;
				position++;
			}
		}
	//	System.out.println(temp[0]);
		return temp;
		
	}
	public Characteristic[] findAllOfType(String query)
	{
		
		int numFound = 0;
		for(Characteristic c : stats)
		{
			if(c.getType().equals(query.trim().toLowerCase()))
			{
				//System.out.println(c.getName() + " = " + query);
				numFound++;
			}
		}
		//System.out.println(numFound);
		if(numFound == 0)
			return new Characteristic[] {};
		Characteristic[] temp = new Characteristic[numFound];
		int position = 0;
		for(Characteristic c : stats)
		{
			if(c.getType().equals(query.trim().toLowerCase()))
			{
				temp[position] = c;
				position++;
			}
		}
	//	System.out.println(temp[0]);
		return temp;
		
	}
	/**
	 * get method for name
	 * @return name of character
	 */
	public String getName()
	{
		try
		{
			return findAll("name")[0].getData();
		}
		catch(Exception e)
		{
			return " ";
		}
	}
	/**
	 * gets the data belonging to the <code>Characteristic</code> that matches a parameter
	 * @param query the reference for the <code>Characteristic</code> being searched
	 * @return the data from the first <code>Characteristic</code> found that matches <b>query</b>
	 */
	public String getSingleData(String query)
	{
		return findAll(query)[0].getData();
	}
	/**
	 * gets all data from <code>Characteristic</code> objects that match a parameter
	 * @param query the reference for the <code>Characteristic</code> being searched
	 * @return the data from all <code>Characteristic</code> objects that match <b>query</b>
	 */
	public String getAllData(String query)
	{
		String output = "";
		for(Characteristic c : findAll(query))
		{
			output += " " + c.getData() + " ";
		}
		return output;	
	}
}
//[Name, Alignment, Ideals, Bonds, Flaws, Background, 
//Race/Subrace, racialTrait1/racialTrait2, Class, 
//classFeature1/classFeature2, proficiency1/proficiency2/<tool>+X, 
//skill1/<points>skill2, XP, proficiencyBonus,
//HP, hitDie1<number>/hitDie2<number>, Strength, sMod, 
//Dexterity, dMod, Constitution, coMod, Intelligence, iMod, 
//Wisdom, wMod, Charisma, chMod, equipment1<damageDie><damageMod><damageType>/equipment2,
//GP, AC]