package vn.myclass.core.common.util;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import vn.myclass.core.common.constant.CoreConstant;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50)
public class FileUploadUtil {
    private final Logger logger = Logger.getLogger(this.getClass());
    private final int MAX_MEMORY_SIZE = 1024 * 1024 * 3; // 3MB
    private final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

    private Set<String> fileExtensionSet;

    public FileUploadUtil() {
        this.fileExtensionSet = new HashSet<>();
        this.setUploadFileIsImage();
    }

    public void setUploadFileIsImage() {
        fileExtensionSet.clear();
        fileExtensionSet.add("png");
        fileExtensionSet.add("jpg");
        fileExtensionSet.add("jpeg");
        fileExtensionSet.add("gif");
    }

    public void setUploadFileIsExcel() {
        fileExtensionSet.clear();
        fileExtensionSet.add("xls");
        fileExtensionSet.add("xlsx");
    }

    public void addUploadFileExtension(String extension) {
        fileExtensionSet.add(extension);
    }

    public Map<String, String> writeOrUpdateFile(HttpServletRequest request, String partName, String parentPath) throws Exception {
        Map<String, String> fileInfoMap = new HashMap();

//        these are all return value in this method
        String fileName = "";
        String fileLocation = "";

        Path uploadPath = Paths.get(CoreConstant.BASE_UPLOAD_PATH, parentPath);

        Part part = request.getPart(partName);
        fileName = part.getSubmittedFileName();
//        Convert to ascii string
        fileName = StringUtil.covertUnicodeToASCIIString(fileName);
        Path uploadFilePath = Paths.get(uploadPath.toString(), fileName);

//            check accepted extension
        String fileExtension = FilenameUtils.getExtension(uploadFilePath.toString());
        if (fileExtensionSet.contains(fileExtension)) {
            createFolderIfNotExisted(uploadPath);

            part.write(uploadFilePath.toString());

            fileLocation = uploadFilePath.toString();
        } else {
            if (StringUtils.isNotBlank(fileExtension)) {
                throw new UnsupportedOperationException();
            }
        }

        String uploadFileUrl = uploadFilePath.toString();
        fileInfoMap.put("fileName", fileName);
        fileInfoMap.put("parentAndFileName", uploadFileUrl.substring(uploadFileUrl.lastIndexOf(parentPath)));
        fileInfoMap.put("fileLocation", fileLocation);

        return fileInfoMap;
    }

    private void createFolderIfNotExisted(Path path) {
        File folder = path.toFile();
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }
}
