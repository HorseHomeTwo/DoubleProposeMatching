import java.io.*;
import java.util.*;

public class Main
{
	public static void main(String[] args)
	{
		ArrayList<Person> people = Parser.parse();
		
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
					System.out.println(person.nickname + " is single " + person.gender);
					writer.println(person.nickname + "(微信:" + person.wechat + ") 很遗憾没有配对成功");
					if (!person.wantMatching)
						writer.println("你选择和你的TA一起参与，但你的暗号无人匹配。 暗号：" + person.secretcode);
					else
						writer.println("你所寻找的TA已与他人配对");
					writer.println("---------------------------------------------------------------------------------------------------");
					continue;
				}
				else
				{
					Person partner = person.partner;
					people.remove(partner);
					System.out.println(person.nickname + " <3 " + partner.nickname);
					
					writer.println();
					writer.println(person.nickname + " <3 " + partner.nickname);
					writer.println();
					writer.println("<<" + person.nickname + ">>");
					writer.println("wechat: " + person.wechat);
					writer.println("phone: " + person.phone);
					writer.println("email: " + person.email);
					writer.println("message to him/her: " + "嗨" + person.nickname + ", 你已与" + partner.nickname + "(wechat: " + partner.wechat + ")成功配对");
					writer.println("对方是" + partner.comingToCanada + "来的加拿大。");
					writer.println("TA的交友宣言是：" + partner.message);
					writer.println("注意：对方非常害羞，所以希望你可以主动一点哟！");
					writer.println();
					writer.println("<<" + partner.nickname + ">>");
					writer.println("wechat: " + partner.wechat);
					writer.println("phone: " + partner.phone);
					writer.println("email: " + partner.email);
					writer.println("message to him/her: " + "嗨" + partner.nickname + ", 你已与" + person.nickname + "(wechat: " + person.wechat + ")成功配对");
					writer.println("对方是" + person.comingToCanada + "来的加拿大。");
					writer.println("TA的交友宣言是：" + person.message);
					writer.println("注意：对方非常害羞，所以希望你可以主动一点哟！");
					writer.println("---------------------------------------------------------------------------------------------------");
				}
			}
			writer.close();
		} catch (Exception e){
			System.exit(1);
		}
	}
}