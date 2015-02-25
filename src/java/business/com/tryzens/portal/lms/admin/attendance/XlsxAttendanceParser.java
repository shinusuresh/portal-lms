package com.tryzens.portal.lms.admin.attendance;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.tryzens.portal.user.attendance.Attendance;

public class XlsxAttendanceParser implements AttendanceParser {

	private static final Log LOGGER = LogFactory
			.getLog(XlsxAttendanceParser.class);

	public List<Attendance> parseAttendance(File attendanceFile) {
		if (attendanceFile == null) {
			return null;
		}
		List<Attendance> attendances = new ArrayList<Attendance>();
		LOGGER.debug("Processing file " + attendanceFile.getName()
				+ " with parser " + XlsxAttendanceParser.class.getName());
		try {

			FileInputStream file = new FileInputStream(attendanceFile);

			// Get the workbook instance for XLS file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get first sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			// Iterate through each rows from first sheet
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();

				// For each row, iterate through each columns
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {

					Cell cell = cellIterator.next();

					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_BOOLEAN:
						// System.out.print(cell.getBooleanCellValue() +
						// "\t\t");
						break;
					case Cell.CELL_TYPE_NUMERIC:
						// System.out.print(cell.getNumericCellValue() +
						// "\t\t");
						break;
					case Cell.CELL_TYPE_STRING:
						/*
						 * if(StringUtils.isEmpty(cell.getStringCellValue())) {
						 * System.out.print("EMPTY" + "\t\t"); } else {
						 * System.out.print(cell.getStringCellValue() + "\t\t");
						 * }
						 */
						if ("Attendance Date".contains(cell
								.getStringCellValue())) {
							System.out.println("Cell is "
									+ cell.getRow().getRowNum());
						}

						break;
					case Cell.CELL_TYPE_BLANK:
						// System.out.print("EMPTY" + "\t\t");
						break;
					}
				}
				System.out.println("");
			}
			file.close();

		} catch (FileNotFoundException e) {
			LOGGER.error("File is not available ", e);
		} catch (IOException e) {
			LOGGER.error("Exception occured while processing the file ", e);
		}
		return attendances;
	}

}
