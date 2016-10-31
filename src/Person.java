import java.util.*;

public class Person
{
	public enum Sex
	{
		Male, Female;
	}
	
	// general information
	String nikename, fullname;
	Sex gender, wanted;
	String phone, email, wechat;
	String matchtext;
	
	// matching information
	Person[] ranking;
	int next = 0;
	boolean single = true;
	double coupleScore = 0;
	Person partner= null;
	HashMap<Person, Double> scores;
	
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