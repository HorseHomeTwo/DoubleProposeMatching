import java.util.*;

public class Main
{
	public static void main(String[] args)
	{
		ArrayList<Person> people = Parser.parse();
		//for (int i = 0; i < people.size(); i++)
		//	System.out.println(people.get(i));
		
		Scorer.scoring(people.toArray(new Person[people.size()]));
		
		PriorityQueue<Person> single =
				new PriorityQueue<Person>(Matcher.desireCompare);
		single.addAll(people);
		Matcher.match(single);
		
		
		
		while (!people.isEmpty())
		{
		}
	}
}