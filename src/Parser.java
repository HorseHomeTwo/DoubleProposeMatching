import java.io.*;
import java.util.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

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
			HSSFCell cell;
			
			//int ROWSNO = excel.getPhysicalNumberOfRows();
			//System.out.println("* This File Has " + ROWSNO + " Rows *");
			
			for (int i = 1; i < ROWSNO; i++)
			{
				row = excel.getRow(i);
				if (row != null && row.getCell(0) != null && row.getCell(0).getNumericCellValue() != 0)
				{
					System.out.println("parsing line " + i);
					Person person = new Person();
					
					if (row.getCell(2).getStringCellValue().equals("ÄÐÉú"))
						person.gender = Person.Sex.Male;
					else
						person.gender = Person.Sex.Female;
					
					if (row.getCell(3).getStringCellValue().equals("ÄÐÉú"))
						person.wanted = Person.Sex.Male;
					else
						person.wanted = Person.Sex.Female;
					
					if (row.getCell(4) == null)
						person.fullname = "PersonAtRow" + i;
					else
						person.fullname = row.getCell(4).getStringCellValue();
					person.nikename = row.getCell(1).getStringCellValue();
					
					(row.getCell(5)).setCellType(Cell.CELL_TYPE_STRING);
					
					if (row.getCell(7) == null)
						person.wechat = "N/A";
					else
					{
						(row.getCell(7)).setCellType(Cell.CELL_TYPE_STRING);
						person.wechat = row.getCell(7).getStringCellValue();
					}
					
					person.phone = row.getCell(5,Row.CREATE_NULL_AS_BLANK).getStringCellValue();
					person.email = row.getCell(6,Row.CREATE_NULL_AS_BLANK).getStringCellValue();
					
					
					person.matchtext = row.getCell(8,Row.CREATE_NULL_AS_BLANK).getStringCellValue() +
									   row.getCell(9,Row.CREATE_NULL_AS_BLANK).getStringCellValue() +
									   row.getCell(11,Row.CREATE_NULL_AS_BLANK).getStringCellValue();
					System.out.println("Adding Person => " + person.fullname);
					people.add(person);
				}
			}
			xls.close();
		} catch (Exception ioe) {
			ioe.printStackTrace();
		}
		System.out.println("***** Finish Parsing *****");
		return people;
	}
}