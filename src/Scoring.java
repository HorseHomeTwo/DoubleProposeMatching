import java.util.*;

public class Scoring
{
	public static void scoring(Person[] people)
	{
		for (int i = 0; i < people.length; i++)
		{
			Person current = people[i];
			current.scores = new HashMap<Person, Double>();
			
			for (int j = 0; j < people.length; j++)
			{
				Person other = people[j];
				if (current == other)
					continue;
				else if (current.wanted != other.gender || other.wanted != current.gender)
					continue;
				else
				{
					double score = evaluate(current.matchtext, other.matchtext);
					current.scores.put(other,score);
				}
					
			}
			
			current.ranking = (Person[])current.scores.keySet().toArray();
		}
	}
	
	public static double evaluate(String evaluator, String candidate)
	{
		return 520;
	}
}