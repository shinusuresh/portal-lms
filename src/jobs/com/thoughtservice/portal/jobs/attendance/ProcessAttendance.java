package com.thoughtservice.portal.jobs.attendance;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;

import com.thoughtservice.portal.lms.admin.task.AttendanceService;

public class ProcessAttendance {

	private static final Log LOGGER = LogFactory
			.getLog(ProcessAttendance.class);

	private AttendanceService attendanceService;

	private @Value("${attendance.folder}") String folderPath;
	private @Value("${attendance.folder.archive}") String archiveFolderPath;

	@Scheduled(cron = "${attendance.cron.schedule}")
	public void trigger() {
		// Process xls in a particular folder

		try {
			processFiles();
		} catch (IOException e) {
			LOGGER.error("Error while processing file ", e);
		}
	}

	private void processFiles() throws IOException {
		LOGGER.debug("Processing files under path " + folderPath);
		File inBoundFolder = new File(folderPath);
		if (inBoundFolder.exists()) {
			LOGGER.debug("Inbound folder exists. Searching for attendance files.");

			// Iterate over all files in inbound folder
			Collection<File> attendanceFiles = FileUtils.listFiles(
					inBoundFolder, null, false);
			for (File attendanceFile : attendanceFiles) {
				if (attendanceFile.exists()) {
					LOGGER.debug("Attendance file exists. Processing file "
							+ attendanceFile.getName());
					// Invoke service and process file
					attendanceService.parseUpload(attendanceFile);

					// Once processed move to archive
					File archiveFile = new File(archiveFolderPath);
					LOGGER.debug("Archiving attendance file "
							+ archiveFile.getName() + " after processing.");
					//If file already exist in archive. Delete the old one first and move the new one
					if(archiveFile.exists()) {
						FileUtils.deleteQuietly(archiveFile);
					}
					FileUtils.moveFileToDirectory(attendanceFile, archiveFile,
							true);
				}
			}
		}
	}

	public void setAttendanceService(AttendanceService attendanceService) {
		this.attendanceService = attendanceService;
	}

}
