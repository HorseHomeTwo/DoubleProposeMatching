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
	
	public String toString()
	{
		String s = fullname + " : " + nikename + "\n";
		s += "Gender: " + gender + ", Wants: " + wanted + "\n";
		s += "Phone: " + phone + ", Email: " + email + ", Wechat: " + wechat + "\n";
		return s += matchtext;
	}
	
	public static void printArray(Person[] people)
	{
		System.out.print("[");
		for (int i = 0; i < people.length; i++)
		{
			System.out.print(people[i].fullname + ", ");
		}
		System.out.println("]");
	}
}