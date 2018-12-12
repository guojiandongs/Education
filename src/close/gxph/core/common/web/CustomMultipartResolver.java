package close.gxph.core.common.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import close.gxph.core.constant.Contants;

public class CustomMultipartResolver extends CommonsMultipartResolver {

	@Override
	public MultipartParsingResult parseRequest(HttpServletRequest request)
			throws MultipartException {
		String encoding = "utf-8";
		FileUpload fileUpload = prepareFileUpload(encoding);
		// final HttpSession session = request.getSession(true);
		final Session session = SecurityUtils.getSubject().getSession(true);
		fileUpload.setProgressListener(new ProgressListener() {
			public void update(long pBytesRead, long pContentLength, int pItems) {
				int percent = (int) (((float) pBytesRead / (float) pContentLength) * 100);
				session.setAttribute(Contants.UPLOAD_PERCERT, percent + "%");
			}
		});
		try {
			List<FileItem> fileItems = ((ServletFileUpload) fileUpload)
					.parseRequest(request);
			return parseFileItems(fileItems, encoding);
		} catch (FileUploadBase.SizeLimitExceededException ex) {
			throw new MaxUploadSizeExceededException(fileUpload.getSizeMax(),
					ex);
		} catch (FileUploadException ex) {
			throw new MultipartException(
					"Could not parse multipart servlet request", ex);
		}
	}
}