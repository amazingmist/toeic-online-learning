package vn.myclass.core.common.utils;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

public class UploadUtil {
    private final int MAX_MEMORY_SIZE = 1024 * 1024 * 3; // 3MB
    private final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

    public void writeOrUpdateFile(HttpServletRequest request) throws Exception {
        // Check that we have a file upload request
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        if (!isMultipart) {
            System.out.println("Do not have enctype=\"multipart/form-data\" in form upload");
        }

        // Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // Set factory constraints
        factory.setSizeThreshold(MAX_MEMORY_SIZE);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

        // Set overall request size constraint
        upload.setSizeMax(MAX_REQUEST_SIZE);

        try {
            // Parse the request
            List<FileItem> items = upload.parseRequest(request);

            for (FileItem item : items) {
                if (!item.isFormField()) {
                    String fileName = item.getName();
                    File uploaddFile = new File(System.getProperty("user.home") + File.separator + "Documents", fileName);

                    if (uploaddFile.exists()) {
                        uploaddFile.delete();
                    }
                    item.write(uploaddFile);
                }
            }
        } catch (Exception ex) {
            throw ex;
        }
    }
}
