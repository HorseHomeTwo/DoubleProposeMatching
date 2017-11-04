import java.io.*;
import java.util.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.*;
import org.apache.poi.ss.usermodel.Cell;

public class Parser
{
	public static String filename = "file.xls";
	public static final int COLSNO = 12;
	public static final int ROWSNO = 300;
	
	@SuppressWarnings("deprecation")
	public static ArrayList<Person> parse()
	{
		System.out.println("***** Start Parsing *****");
		ArrayList<Person> people = new ArrayList<Person>();
		try {
			FileInputStream fis = new FileInputStream(filename);
			POIFSFileSystem pfs = new POIFSFileSystem(fis);
			HSSFWorkbook xls = new HSSFWorkbook(pfs);
			HSSFSheet excel = xls.getSheetAt(0);
			HSSFRow row;
			
			for (int i = 1; i < ROWSNO; i++)
			{
				row = excel.getRow(i);
				if (row != null && row.getCell(0) != null && row.getCell(0).getStringCellValue() != null)
				{
					if (row.getCell(14) == null || row.getCell(15) == null)
					{
						continue;
					}
					
					System.out.println("parsing line " + i);
					Person person = new Person();
					
					if (row.getCell(2) != null)
						person.nickname = row.getCell(2).getStringCellValue();
					else
						person.nickname = "person" + i;
					
					if (row.getCell(3).getStringCellValue().equals("男"))
						person.gender = Person.Sex.Male;
					else
						person.gender = Person.Sex.Female;
					
					if (row.getCell(4).getStringCellValue().equals("男"))
						person.wanted = Person.Sex.Male;
					else
						person.wanted = Person.Sex.Female;
					
					if (row.getCell(5) == null)
						person.age = 0;
					else
					{
						String age = row.getCell(5).getStringCellValue();
						person.age = 2017 - Integer.parseInt(age.substring(age.lastIndexOf("/")+ 1));
					}
					
					if (row.getCell(6) == null)
						person.matchtext = "";
					else
						person.matchtext = row.getCell(6).getStringCellValue();
					
					if (row.getCell(7) == null)
						person.comingToCanada = "";
					else
						person.comingToCanada = row.getCell(7).getStringCellValue();
					
					if (row.getCell(8).getStringCellValue().equals("没有，给我在线匹配一个TA吧"))
						person.wantMatching = true;
					else
						person.wantMatching = false;
					(row.getCell(9)).setCellType(Cell.CELL_TYPE_STRING);
					if (row.getCell(9) == null)
						person.secretcode = "";
					else
						person.secretcode = row.getCell(9).getStringCellValue();
					
					if (row.getCell(10) == null)
					{
						person.expectAgeMin = 18;
						person.expectAgeMax = 100;
					}
					else
					{
						String expectage = row.getCell(10).getStringCellValue();
						person.expectAgeMin = Integer.parseInt(expectage.substring(0, 2));
						if (expectage.charAt(expectage.length()-1) == '+')
							person.expectAgeMax = 100;
						else
							person.expectAgeMax = Integer.parseInt(expectage.substring(expectage.lastIndexOf("-") + 1));
					}
					(row.getCell(11)).setCellType(Cell.CELL_TYPE_STRING);
					if (row.getCell(11) == null)
						person.message = "";
					else
						person.message = row.getCell(11).getStringCellValue();
					(row.getCell(12)).setCellType(Cell.CELL_TYPE_STRING);
					if (row.getCell(12) == null)
						person.phone = "";
					else
						person.phone = row.getCell(12).getStringCellValue();
					
					if (row.getCell(13) == null)
						person.email = "";
					else
						person.email = row.getCell(13).getStringCellValue();
					(row.getCell(14)).setCellType(Cell.CELL_TYPE_STRING);
					person.wechat = row.getCell(14).getStringCellValue();
					System.out.println("Adding Person => " + person.nickname);
					people.add(person);
				}
			}
			xls.close();
		} catch (Exception ioe) {
			ioe.printStackTrace();
			System.exit(1);
		}
		System.out.println("***** Finish Parsing *****");
		return people;
	}
}