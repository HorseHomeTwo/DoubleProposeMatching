import java.io.*;
import java.util.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.*;

public class Parser
{
	public static String filename = "file.xlsx";
	public static final int COLSNO = 12;
	
	public static Person[] parse()
	{
		ArrayList<Person> people = new ArrayList<Person>();
		try {
			FileInputStream fis = new FileInputStream(filename);
			POIFSFileSystem pfs = new POIFSFileSystem(fis);
			HSSFWorkbook xlsx = new HSSFWorkbook(pfs);
			HSSFSheet excel = xlsx.getSheetAt(0);
			HSSFRow row;
			
			int ROWSNO = excel.getPhysicalNumberOfRows();
			
			for (int i = 0; i < ROWSNO; i++)
			{
				row = excel.getRow(i);
				if (row != null)
				{
					Person person = new Person();
					
					if (row.getCell(3).getStringCellValue().equals("ÄÐÉú"))
						person.gender = Person.Sex.Male;
					else
						person.gender = Person.Sex.Female;
					
					if (row.getCell(4).getStringCellValue().equals("ÄÐÉú"))
						person.wanted = Person.Sex.Male;
					else
						person.wanted = Person.Sex.Female;
					
					person.fullname = row.getCell(5).getStringCellValue();
					person.nikename = row.getCell(2).getStringCellValue();
					
					person.phone = row.getCell(6).getStringCellValue();
					person.email = row.getCell(7).getStringCellValue();
					person.wechat = row.getCell(8).getStringCellValue();
					
					person.matchtext = row.getCell(9).getStringCellValue() +
									   row.getCell(10).getStringCellValue() +
									   row.getCell(12).getStringCellValue();
					
					people.add(person);
				}
			}
			xlsx.close();
		} catch (Exception ioe) {
			ioe.printStackTrace();
		}
		return null;
	}
}