import java.util.*;

public class Matcher
{
	public static void match(PriorityQueue<Person> single)
	{
		System.out.println("***** Start Matching *****");
		while (!single.isEmpty())
		{
			Person proposer = single.poll();
			System.out.println("@" + proposer.nickname + " proposes to " + proposer.next);
			if (proposer.next >= proposer.ranking.length)
			{
				System.out.println(proposer.nickname + " Will not propose any more!");
				continue;
			}
			Person receiver = proposer.ranking[proposer.next++];
			double score1 = proposer.scores.get(receiver);
			double score2 = receiver.scores.get(proposer);
			double coupleScore = score1 + score2;
			if (receiver.single)
			{
				proposer.loves(receiver, coupleScore);
				receiver.loves(proposer, coupleScore);
				single.remove(receiver);
				System.out.println(proposer.nickname + " <3 " + receiver.nickname);
			}
			else if (coupleScore > receiver.coupleScore)
			{
				System.out.println(receiver.partner.nickname + " Got Ditched");
				receiver.partner.getDitched();
				single.add(receiver.partner);
				proposer.loves(receiver, coupleScore);
				receiver.loves(proposer, coupleScore);
				System.out.println(proposer.nickname + " <3 " + receiver.nickname);
			}
			else
			{
				single.add(proposer);
				System.out.println(proposer.nickname + " Got Rejected");
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