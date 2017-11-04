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
					writer.println(person.nickname + "(΢��:" + person.wechat + ") ���ź�û����Գɹ�");
					if (!person.wantMatching)
						writer.println("��ѡ������TAһ����룬����İ�������ƥ�䡣 ���ţ�" + person.secretcode);
					else
						writer.println("����Ѱ�ҵ�TA�����������");
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
					writer.println("message to him/her: " + "��" + person.nickname + ", ������" + partner.nickname + "(wechat: " + partner.wechat + ")�ɹ����");
					writer.println("�Է���" + partner.comingToCanada + "���ļ��ô�");
					writer.println("TA�Ľ��������ǣ�" + partner.message);
					writer.println("ע�⣺�Է��ǳ����ߣ�����ϣ�����������һ��Ӵ��");
					writer.println();
					writer.println("<<" + partner.nickname + ">>");
					writer.println("wechat: " + partner.wechat);
					writer.println("phone: " + partner.phone);
					writer.println("email: " + partner.email);
					writer.println("message to him/her: " + "��" + partner.nickname + ", ������" + person.nickname + "(wechat: " + person.wechat + ")�ɹ����");
					writer.println("�Է���" + person.comingToCanada + "���ļ��ô�");
					writer.println("TA�Ľ��������ǣ�" + person.message);
					writer.println("ע�⣺�Է��ǳ����ߣ�����ϣ�����������һ��Ӵ��");
					writer.println("---------------------------------------------------------------------------------------------------");
				}
			}
			writer.close();
		} catch (Exception e){
			System.exit(1);
		}
	}
}