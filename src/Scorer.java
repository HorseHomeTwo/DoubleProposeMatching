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
			
			for (int j = 0; j < people.length; j++)
			{
				Person other = people[j];
				if (current == other)
					continue;
				else if (current.wanted != other.gender || other.wanted != current.gender)
					continue;
				else if (!current.wantMatching && !other.wantMatching && current.secretcode.equals(other.secretcode))
				{
					current.scores.put(other, 101.0);
				}
				else if (!current.wantMatching || !other.wantMatching) 
				{
					continue;
				}
				else
				{
					double hobbyscore = evaluate(current.matchtext, other.matchtext);
					double agescore = evaluateAge(other.age, current.expectAgeMin, current.expectAgeMax);
					double score = (hobbyscore + agescore)/2;
					current.scores.put(other,score);
				}
					
			}
			
			System.out.println("@@@ " + current.nickname + " begin ranking");
			Set<Person> keyset = current.scores.keySet();
			current.ranking = keyset.toArray(new Person[keyset.size()]);
			ranker = current;
			Arrays.sort(current.ranking, rankCompare);
			System.out.println("Ranking: ");
			for (int k = 0; k < current.ranking.length; k++) {
				Person p = current.ranking[k];
				double score = current.scores.get(p);
				System.out.print(p.nickname + ":" + score + ", ");
			}
			System.out.println();
		}
		System.out.println("***** Finish Scoring *****");
	}
	
	public static double evaluate(String evaluator, String candidate)
	{
		evaluator = evaluator.replaceAll("[`~!@#$%^&*()_+={};':|<>?/.,£¬¡££¡£¿]","");
		candidate = candidate.replaceAll("[`~!@#$%^&*()_+={};':|<>?/.,£¬¡££¡£¿]","");
		double score = 0;
		for (int i = 0; i < evaluator.length(); i++)
		{
			if (candidate.contains(""+evaluator.charAt(i)))
			{
				score += 1.0 / evaluator.length();
			}
		}
		return score *= 100;
	}
	
	public static double evaluateAge(int age, int min, int max)
	{
		if (age <= max && age >= min)
			return 100.0;
		else
			return 0.0;
	}
	
	public static Person ranker;
	
	public static Comparator<Person> rankCompare = new Comparator<Person>()
	{
		public int compare(Person p1, Person p2)
		{
			double p1score = ranker.scores.get(p1);
			double p2score = ranker.scores.get(p2);
			return (int)Math.round(p2score - p1score);
		}
	};
}