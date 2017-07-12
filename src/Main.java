import java.io.*;
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
		
		try {
		    PrintWriter writer = new PrintWriter("result.txt", "UTF-8");
			while (!people.isEmpty())
			{
				Person person = people.remove(0);
				if (person.single)
				{
					System.out.println(person.phone + " " + person.email + " IsSingle " + person.gender);
					continue;
				}
				else
				{
					Person partner = person.partner;
					people.remove(partner);
					//System.out.println(person.fullname + " <3 " + partner.fullname);
					
					writer.println("亲爱的"+person.email+"收件人");
					writer.println("万能的许愿机已经完成了！现在！");
					writer.println("万能的许愿机针对你所填写的信息为你找到了有缘人");
					writer.println( person.nikename+"同学，恭喜你成功与"+partner.nikename+"同学配对。");
					writer.println("注意：对方非常害羞，所以希望你可以主动一点哟 ^_^");

					writer.println("为了增加配对活动的趣味性，我们只公布以下信息");
					writer.println("有缘人档案");
					writer.println("昵称："+partner.nikename);
					writer.println("电话："+partner.phone);
					writer.println("邮箱："+partner.email);
					writer.println();
					
					writer.println("亲爱的"+partner.email+"收件人");
					writer.println("万能的许愿机已经完成了！现在！");
					writer.println("万能的许愿机针对你所填写的信息为你找到了有缘人");
					writer.println( partner.nikename+"同学，恭喜你成功与"+person.nikename+"同学配对。");
					writer.println("注意：对方非常害羞，所以希望你可以主动一点哟 ^_^");

					writer.println("为了增加配对活动的趣味性，我们只公布以下信息");
					writer.println("有缘人档案");
					writer.println("昵称："+person.nikename);
					writer.println("电话："+person.phone);
					writer.println("邮箱："+person.email);
					writer.println();
					
				}
			}
			writer.close();
		} catch (Exception e){
			System.exit(1);
		}
	}
}