import java.util.Dictionary;

public class Person
{
	// general information
	String nikename, fullname;
	Sex gender, wanted;
	String phone, email, wechat;
	String matching;
	
	// matching information
	Person[] ranking;
	int next = 0;
	boolean single;
	double coupleScore;
	Person partner;
	Dictionary<Person, Double> scores;
	
	public void loves(Person other, double score)
	{
		single = false;
		partner = other;
		coupleScore = score;
	}
	
	public void getDitched()
	{
		single = true;
		partner = null;
		coupleScore = 0;
	}
}