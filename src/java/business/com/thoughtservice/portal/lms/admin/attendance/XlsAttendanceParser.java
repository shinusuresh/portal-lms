package com.thoughtservice.portal.lms.admin.attendance;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.thoughtservice.portal.user.User;
import com.thoughtservice.portal.user.attendance.Attendance;

public class XlsAttendanceParser implements AttendanceParser {

	private static final Log LOGGER = LogFactory
			.getLog(XlsAttendanceParser.class);

	public List<Attendance> parseAttendance(File attendanceFile) {
		if (attendanceFile == null) {
			return null;
		}
		List<Attendance> attendances = new ArrayList<Attendance>();
		Date attendanceDate = null;
		int count = 1;
		LOGGER.debug("Processing file " + attendanceFile.getName()
				+ " with parser " + XlsAttendanceParser.class.getName());
		try {

			FileInputStream file = new FileInputStream(attendanceFile);

			// Get the workbook instance for XLS file
			HSSFWorkbook workbook = new HSSFWorkbook(file);

			// Get first sheet from the workbook
			HSSFSheet sheet = workbook.getSheetAt(0);

			// Iterate through each rows from first sheet
			Iterator<Row> rowIterator = sheet.iterator();

			while (rowIterator.hasNext()) {

				// For each row, iterate through each columns
				Row row = rowIterator.next();
				LOGGER.debug("ro row row row" + row.getRowNum());
				// For now the crude way is to go by row number as this is kind
				// of fixed

				// This is Attendance Date :
				if (row.getRowNum() == 7) {
					// Get the Date - Column 2
					// LOGGER.debug(cell.getColumnIndex());
					Iterator<Cell> cellIterator = row.cellIterator();
					while (cellIterator.hasNext()) {

						Cell cell = cellIterator.next();
						if (cell.getColumnIndex() == 5) {
							try {
								LOGGER.debug("Attendance for date - "
										+ cell.getStringCellValue());
								attendanceDate = DateUtils.parseDate(
										cell.getStringCellValue(),
										new String[] { "dd-MMM-yyyy" });
							} catch (ParseException e) {
								LOGGER.error(
										"Exception occured while parsing attendance date. ",
										e);
							}
						}
					}
				}

				// Here is where we literally iterate through punch ins and outs
				if (row.getRowNum() >= 10 ) {
					Iterator<Cell> cellIterator = row.cellIterator();
					Attendance attendance = new Attendance();
					User user = new User();
					attendance.setAttendanceDate(attendanceDate);
					LOGGER.debug("attendance date"
							+ attendance.getAttendanceDate());
					// iterating through column
					while (cellIterator.hasNext()) {

						Cell cell = cellIterator.next();
						switch (cell.getColumnIndex()) {

						// E.Code
						case 2:
							LOGGER.debug("column index........."
									+ cell.getColumnIndex());
							LOGGER.debug("column type........."
									+ cell.getCellType());
							if ((cell.getCellType() != 3)) {
								//If cell type is numeric then use getNumeric else use getStringCellValue
								//For cell types, refer - https://poi.apache.org/apidocs/org/apache/poi/ss/usermodel/Cell.html
								String employeeCode = "";
								switch(cell.getCellType()) {
									//Numeric
									case 0:
										employeeCode = Integer.toString((int)cell.getNumericCellValue());
										break;
									//String 	
									case 1:
										employeeCode = cell.getStringCellValue();
										break;
								}
								LOGGER.debug("Employee code is "+employeeCode);
								System.out.print("employee code"
										+ employeeCode + "\t\t");
								user.setEmployeeCode(Long.parseLong(employeeCode));
								attendance.setUser(user);
								LOGGER.debug("attendance id"
										+ attendance.getUser().getId());
							} else {
								System.out.print("EMPTY" + "\t\t");
							}

							break;

			//name
						case 3:
							LOGGER.debug("column index........."
									+ cell.getColumnIndex());
							LOGGER.debug("column type........."
									+ cell.getCellType());
							if ((cell.getCellType() != 3)) {
								user.setFirstName(cell.getStringCellValue());
								attendance.setUser(user);
							} else {
								System.out.print("EMPTY" + "\t\t");
							}
							break;
							//shift
						case 5:
							LOGGER.debug("column index........."
									+ cell.getColumnIndex());
							LOGGER.debug("column type........."
									+ cell.getCellType());
							if ((cell.getCellType() != 3)) {
								LOGGER.debug("name is "
										+ cell.getStringCellValue());
								System.out.print("name"
										+ cell.getStringCellValue() + "\t\t");
								attendance.setShift(cell.getStringCellValue());
							} else {
								System.out.print("EMPTY" + "\t\t");
							}
					
							break;
					//shift in time
						case 6:
							LOGGER.debug("column index........."
									+ cell.getColumnIndex());
							LOGGER.debug("column type........."
									+ cell.getCellType());
							if ((cell.getCellType() != 3)) {
								LOGGER.debug("shift in time "
										+ cell.getStringCellValue());
								System.out.print("shift in time is"
										+ cell.getStringCellValue() + "\t\t");
								String time= cell.getStringCellValue();
								 LOGGER.debug(time);
								    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
								    Date date = null;
								    try {
								        date = sdf.parse(time);
								    } catch (ParseException e) {
								        e.printStackTrace();
								    }
								    String formattedTime = sdf.format(date);

								    LOGGER.debug(formattedTime);
								    java.sql.Time sInTime = new java.sql.Time(date.getTime());
									attendance.setShiftInTime(sInTime);
							
							} else {
								System.out.print("EMPTY" + "\t\t");
							}
					
							break;
							
							//shift out
						case 7:
							LOGGER.debug("column index........."
									+ cell.getColumnIndex());
							LOGGER.debug("column type........."
									+ cell.getCellType());
							if ((cell.getCellType() != 3)) {
								LOGGER.debug("shift out time is"
										+ cell.getStringCellValue());
								System.out.print("shift out time is"
										+ cell.getStringCellValue() + "\t\t");
								String time= cell.getStringCellValue();
								 LOGGER.debug(time);
								    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
								    Date date = null;
								    try {
								        date = sdf.parse(time);
								    } catch (ParseException e) {
								        e.printStackTrace();
								    }
								    String formattedTime = sdf.format(date);

								    LOGGER.debug(formattedTime);
								    java.sql.Time sInTime = new java.sql.Time(date.getTime());
									attendance.setShiftOutTime(sInTime);
							
							} else {
								System.out.print("EMPTY" + "\t\t");
							}
					
							break;
							//actual time in  
						case 9:
							LOGGER.debug("column index........."
									+ cell.getColumnIndex());
							LOGGER.debug("column type........."
									+ cell.getCellType());
							if ((cell.getCellType() != 3)) {
								LOGGER.debug("actual in time is "
										+ cell.getStringCellValue());
								System.out.print("actual in time is"
										+ cell.getStringCellValue() + "\t\t");
								String time= cell.getStringCellValue();
								 LOGGER.debug(time);
								    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
								    Date date = null;
								    try {
								        date = sdf.parse(time);
								    } catch (ParseException e) {
								        e.printStackTrace();
								    }
								    String formattedTime = sdf.format(date);

								    LOGGER.debug(formattedTime);
								    java.sql.Time sInTime = new java.sql.Time(date.getTime());
									attendance.setActualInTime(sInTime);
							} else {
								System.out.print("EMPTY" + "\t\t");
							}
				
							break;
							//actual time out
						case 11:
							LOGGER.debug("column index........."
									+ cell.getColumnIndex());
							LOGGER.debug("column type........."
									+ cell.getCellType());
							if ((cell.getCellType() != 3)) {
								LOGGER.debug("actual time out  "
										+ cell.getStringCellValue());
								System.out.print("actual out time is"
										+ cell.getStringCellValue() + "\t\t");
								String time= cell.getStringCellValue();
								 LOGGER.debug(time);
								    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
								    Date date = null;
								    try {
								        date = sdf.parse(time);
								    } catch (ParseException e) {
								        e.printStackTrace();
								    }
								    String formattedTime = sdf.format(date);

								    LOGGER.debug(formattedTime);
								    java.sql.Time sInTime = new java.sql.Time(date.getTime());
									attendance.setActualOutTime(sInTime);
							} else {
								System.out.print("EMPTY" + "\t\t");
							}
							break;
							//work duration
						case 12:
							LOGGER.debug("column index........."
									+ cell.getColumnIndex());
							LOGGER.debug("column type........."
									+ cell.getCellType());
							if ((cell.getCellType() != 3)) {
								LOGGER.debug("work duration "
										+ cell.getStringCellValue());
								System.out.print("work duration is"
										+ cell.getStringCellValue() + "\t\t");
								String time= cell.getStringCellValue();
								 LOGGER.debug(time);
								    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
								    Date date = null;
								    try {
								        date = sdf.parse(time);
								    } catch (ParseException e) {
								        e.printStackTrace();
								    }
								    String formattedTime = sdf.format(date);

								    LOGGER.debug(formattedTime);
								    java.sql.Time sInTime = new java.sql.Time(date.getTime());
									attendance.setWorkDuration(sInTime);;
							} else {
								System.out.print("EMPTY" + "\t\t");
							}
							break;
					//	over time	
						case 14:
							LOGGER.debug("column index........."
									+ cell.getColumnIndex());
							LOGGER.debug("column type........."
									+ cell.getCellType());
							if ((cell.getCellType() != 3)) {
								LOGGER.debug("OT "
										+ cell.getStringCellValue());
								System.out.print("OT"
										+ cell.getStringCellValue() + "\t\t");
								String time= cell.getStringCellValue();
								 LOGGER.debug(time);
								    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
								    Date date = null;
								    try {
								        date = sdf.parse(time);
								    } catch (ParseException e) {
								        e.printStackTrace();
								    }
								    String formattedTime = sdf.format(date);

								    LOGGER.debug(formattedTime);
								    java.sql.Time sInTime = new java.sql.Time(date.getTime());
									attendance.setOt(sInTime);
							} else {
								System.out.print("EMPTY" + "\t\t");
							}
							break;
							//total duration
						case 15:
							LOGGER.debug("column index........."
									+ cell.getColumnIndex());
							LOGGER.debug("column type........."
									+ cell.getCellType());
							if ((cell.getCellType() != 3)) {
								LOGGER.debug("total duration "
										+ cell.getStringCellValue());
								System.out.print("total duration is"
										+ cell.getStringCellValue() + "\t\t");
								String time= cell.getStringCellValue();
								 LOGGER.debug(time);
								    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
								    Date date = null;
								    try {
								        date = sdf.parse(time);
								    } catch (ParseException e) {
								        e.printStackTrace();
								    }
								    String formattedTime = sdf.format(date);

								    LOGGER.debug(formattedTime);
								    java.sql.Time sInTime = new java.sql.Time(date.getTime());
									attendance.setTotalDuration(sInTime);
							} else {
								System.out.print("EMPTY" + "\t\t");
							}
							break;
						//	late by
						case 17:
							LOGGER.debug("column index........."
									+ cell.getColumnIndex());
							LOGGER.debug("column type........."
									+ cell.getCellType());
							if ((cell.getCellType() != 3)) {
								LOGGER.debug("late by "
										+ cell.getStringCellValue());
								System.out.print("late by"
										+ cell.getStringCellValue() + "\t\t");
								String time= cell.getStringCellValue();
								 LOGGER.debug(time);
								    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
								    Date date = null;
								    try {
								        date = sdf.parse(time);
								    } catch (ParseException e) {
								        e.printStackTrace();
								    }
								    String formattedTime = sdf.format(date);

								    LOGGER.debug(formattedTime);
								    java.sql.Time sInTime = new java.sql.Time(date.getTime());
									attendance.setLateBy(sInTime);
							} else {
								System.out.print("EMPTY" + "\t\t");
							}
							break;
							//early going by
						case 18:
							LOGGER.debug("column index........."
									+ cell.getColumnIndex());
							LOGGER.debug("column type........."
									+ cell.getCellType());
							if ((cell.getCellType() != 3)) {
								LOGGER.debug("Early going by "
										+ cell.getStringCellValue());
								System.out.print("early going by"
										+ cell.getStringCellValue() + "\t\t");
								String time= cell.getStringCellValue();
								 LOGGER.debug(time);
								    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
								    Date date = null;
								    try {
								        date = sdf.parse(time);
								    } catch (ParseException e) {
								        e.printStackTrace();
								    }
								    String formattedTime = sdf.format(date);

								    LOGGER.debug(formattedTime);
								    java.sql.Time sInTime = new java.sql.Time(date.getTime());
									attendance.setEarlyGoingBy(sInTime);
							} else {
								System.out.print("EMPTY" + "\t\t");
							}
							break;
						//status	
						case 19:
							LOGGER.debug("column index........."
									+ cell.getColumnIndex());
							LOGGER.debug("column type........."
									+ cell.getCellType());
							if ((cell.getCellType() != 3)) {
								LOGGER.debug("status"
										+ cell.getStringCellValue());
								System.out.print("status"
										+ cell.getStringCellValue() + "\t\t");
									attendance.setStatus(cell.getStringCellValue());
							} else {
								System.out.print("EMPTY" + "\t\t");
							}
							break;
						//punch records	
						case 21:
							LOGGER.debug("column index........."
									+ cell.getColumnIndex());
							LOGGER.debug("column type........."
									+ cell.getCellType());
							if ((cell.getCellType() != 3)) {
								LOGGER.debug("punch records "
										+ cell.getStringCellValue());
								System.out.print(" punch records"
										+ cell.getStringCellValue() + "\t\t");
									attendance.setPunchRecords(cell.getStringCellValue());
							} else {
								System.out.print("EMPTY" + "\t\t");
							}
							break;
						}

					}// end while
					LOGGER.debug("count" + count++);
					attendances.add(attendance);
				}// end if
			}// end cellIterator while
				// Add attendance to list
			LOGGER.debug("");

			// end rowiterator while
			file.close();

			

		} catch (FileNotFoundException e) {
			LOGGER.error("File is not available ", e);
		} catch (IOException e) {
			LOGGER.error("Exception occured while processing the file ", e);
		}
		LOGGER.debug("this is" + attendances.get(0).getAttendanceDate());
		LOGGER.debug("this is" + attendances.size());
		
		return attendances;
	}

}
