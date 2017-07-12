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
					
					writer.println("�װ���"+person.email+"�ռ���");
					writer.println("���ܵ���Ը���Ѿ�����ˣ����ڣ�");
					writer.println("���ܵ���Ը�����������д����ϢΪ���ҵ�����Ե��");
					writer.println( person.nikename+"ͬѧ����ϲ��ɹ���"+partner.nikename+"ͬѧ��ԡ�");
					writer.println("ע�⣺�Է��ǳ����ߣ�����ϣ�����������һ��Ӵ ^_^");

					writer.println("Ϊ��������Ի��Ȥζ�ԣ�����ֻ����������Ϣ");
					writer.println("��Ե�˵���");
					writer.println("�ǳƣ�"+partner.nikename);
					writer.println("�绰��"+partner.phone);
					writer.println("���䣺"+partner.email);
					writer.println();
					
					writer.println("�װ���"+partner.email+"�ռ���");
					writer.println("���ܵ���Ը���Ѿ�����ˣ����ڣ�");
					writer.println("���ܵ���Ը�����������д����ϢΪ���ҵ�����Ե��");
					writer.println( partner.nikename+"ͬѧ����ϲ��ɹ���"+person.nikename+"ͬѧ��ԡ�");
					writer.println("ע�⣺�Է��ǳ����ߣ�����ϣ�����������һ��Ӵ ^_^");

					writer.println("Ϊ��������Ի��Ȥζ�ԣ�����ֻ����������Ϣ");
					writer.println("��Ե�˵���");
					writer.println("�ǳƣ�"+person.nikename);
					writer.println("�绰��"+person.phone);
					writer.println("���䣺"+person.email);
					writer.println();
					
				}
			}
			writer.close();
		} catch (Exception e){
			System.exit(1);
		}
	}
}