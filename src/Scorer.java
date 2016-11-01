import java.util.*;

public class Scorer
{
	public static void scoring(Person[] people)
	{
		System.out.println("***** Start Scoring *****");
		for (int i = 0; i < people.length; i++)
		{
			Person current = people[i];
			current.scores = new HashMap<Person, Double>();
			System.out.println("@" + current.fullname + " score others");
			
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
			
			System.out.println("@" + current.fullname + " start ranking");
			current.ranking = (Person[])current.scores.keySet().toArray();
			ranker = current;
			Arrays.sort(current.ranking, rankCompare);
		}
	}
	
	public static double evaluate(String evaluator, String candidate)
	{
		evaluator = evaluator.replaceAll("[`~!@#$%^&*()_+-={};':|]<>?/.,","");
		candidate = candidate.replaceAll("[`~!@#$%^&*()_+-={};':|]<>?/.,","");
		System.out.println("Evaluator: " + evaluator);
		System.out.println("Candidate: " + candidate);
		double score = 0;
		for (int i = 0; i < evaluator.length(); i++)
		{
			if (candidate.contains(""+evaluator.charAt(i)))
			{
				score += 1.0 / evaluator.length();
			}
		}
		System.out.println("Score: " + score*100);
		return score *= 100;
	}
	
	public static Person ranker;
	
	public static Comparator<Person> rankCompare = new Comparator<Person>()
	{
		public int compare(Person p1, Person p2)
		{
			double p1score = ranker.scores.get(p1);
			double p2score = ranker.scores.get(p2);
			return (int)Math.round(p1score - p2score);
		}
	};
}