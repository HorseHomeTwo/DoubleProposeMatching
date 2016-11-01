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
				System.out.println(proposer.fullname + " <3 " + receiver.fullname);
			}
			else if (coupleScore > receiver.coupleScore)
			{
				receiver.partner.getDitched();
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
}