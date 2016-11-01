import java.util.*;

public class Matcher
{
	public static void match(PriorityQueue<Person> single)
	{
		System.out.println("***** Start Matching *****");
		while (!single.isEmpty())
		{
			Person proposer = single.poll();
			System.out.println("@" + proposer.fullname + " proposes");
			if (proposer.next >= proposer.ranking.length)
			{
				System.out.println(proposer.fullname + " Will Be Single Forever");
				continue;
			}
			Person receiver = proposer.ranking[proposer.next++];
			double coupleScore = 
					proposer.scores.get(receiver) +
					receiver.scores.get(proposer);
			if (receiver.single)
			{
				proposer.loves(receiver, coupleScore);
				receiver.loves(proposer, coupleScore);
				single.remove(receiver);
				System.out.println(proposer.fullname + " <3 " + receiver.fullname);
			}
			else if (coupleScore > receiver.coupleScore)
			{
				receiver.partner.getDitched();
				single.add(receiver.partner);
				proposer.loves(receiver, coupleScore);
				receiver.loves(proposer, coupleScore);
				System.out.println(proposer.fullname + " <3 " + receiver.fullname);
			}
			else
			{
				single.add(proposer);
				System.out.println(proposer.fullname + " Got Rejected");
			}
		}
		System.out.println("***** Done Matching *****");
	}
	
	public static Comparator<Person> desireCompare = new Comparator<Person>()
	{
		public int compare(Person p1, Person p2)
		{
			double p1desire, p2desire;
			if (p1.next >= p1.ranking.length)
				p1desire = -1;
			else
				p1desire = p1.scores.get(p1.ranking[p1.next]);
			if (p2.next >= p2.ranking.length)
				p2desire = -1;
			else
				p2desire = p2.scores.get(p2.ranking[p2.next]);
			return (int)Math.round(p2desire - p1desire);
		}
	};
}