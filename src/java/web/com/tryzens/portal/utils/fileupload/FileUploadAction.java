package com.tryzens.portal.utils.fileupload;

import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.struts.SpringBindingActionForm;

import com.tryzens.portal.lms.common.notifications.Notification;
import com.tryzens.portal.lms.common.notifications.Notification.STATUS;

/**
 * Controller for Attendance upload.
 * 
 * @author Arun
 *
 */
@SuppressWarnings("deprecation")
public class FileUploadAction extends DispatchAction {

	private static final Log LOGGER = LogFactory.getLog(FileUploadAction.class);

	private @Value("${attendance.folder}") String folderPath;

	public ActionForward load(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("success");
	}

	public ActionForward upload(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FileOutputStream outputStream = null;
		try {
			SpringBindingActionForm springBindingActionForm = (SpringBindingActionForm) form;

			FormFile data = (FormFile) springBindingActionForm
					.getMultipartRequestHandler().getFileElements()
					.get("uploadedFile");
			LOGGER.debug(data.getFileName() + " recieved with size "
					+ data.getFileSize());

			String dest = folderPath + "/" + data;
			outputStream = new FileOutputStream(new File(dest));
			outputStream.write(data.getFileData());

			LOGGER.debug(data.getFileName() + " uploaded to " + folderPath);

			Notification statusNotification = new Notification(
					data.getFileName()
							+ " uploaded successfully for processing.", "000",
					STATUS.SUCCESS);
			request.setAttribute("notification", statusNotification);

		} catch (Exception e) {
			LOGGER.error("Exception while uploading attendance file ", e);
			Notification statusNotification = new Notification(
					"Uploading attendance failed with reason " + e.getMessage(),
					"912", STATUS.ERROR);
			request.setAttribute("notification", statusNotification);
		} finally {
			if (outputStream != null)
				outputStream.close();
		}
		return mapping.findForward("success");

	}

}