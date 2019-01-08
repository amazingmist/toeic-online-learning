package vn.myclass.core.common.util;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FileUploadUtil {
    private final Logger logger = Logger.getLogger(this.getClass());
    private final int MAX_MEMORY_SIZE = 1024 * 1024 * 3; // 3MB
    private final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

    public Object[] writeOrUpdateFile(HttpServletRequest request, Set<String> titleValueSet, String path) {
        ServletContext servletContext = request.getServletContext();
        String address = servletContext.getRealPath("file_upload");

//        those are all return value in this method
        boolean isSuccess;
        String fileLocation = null;
        String fileName = null;
        Map<String, String> returnValueMap = new HashMap<>();

        // Check that we have a file upload request
        isSuccess = ServletFileUpload.isMultipartContent(request);

        // Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // Set factory constraints
        factory.setSizeThreshold(MAX_MEMORY_SIZE);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

        // Set overall request size constraint
        upload.setSizeMax(MAX_REQUEST_SIZE);

        // Parse the request
        List<FileItem> items = null;
        try {
            items = upload.parseRequest(request);
        } catch (FileUploadException e) {
            logger.error(e.getMessage(), e);
            isSuccess = false;
        }

        if (items != null) {
            for (FileItem item : items) {
//            check if this field is a file upload and it have a submitted file
                if (!item.isFormField() && StringUtils.isNotBlank(item.getName())) {
                    File uploadedFile = new File(address + File.separator + path, item.getName());
                    fileLocation = uploadedFile.getAbsolutePath();
                    fileName = uploadedFile.getName();

//                check if this file is already existed
                    try {
                        uploadedFile.delete();
                        item.write(uploadedFile);
                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                        isSuccess = false;
                        fileLocation = null;
                        fileName = null;
                    }
                } else if (item.isFormField()) {
//                this file is not a file upload
                    if (titleValueSet != null) {
                        String fieldName = item.getFieldName();
                        String fieldValue = item.getString();

//                    check if this field is required return
                        if (titleValueSet.contains(fieldName)) {
                            returnValueMap.put(fieldName, fieldValue);
                        }
                    }
                }
            }
        }

        return new Object[]{isSuccess, fileLocation, fileName, returnValueMap};
    }
}
