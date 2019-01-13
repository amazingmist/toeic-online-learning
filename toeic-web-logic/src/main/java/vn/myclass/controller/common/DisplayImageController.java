package vn.myclass.controller.common;

import vn.myclass.core.common.constant.CoreConstant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

@WebServlet("/repository/*")
public class DisplayImageController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fileName = req.getPathInfo().substring(1);
        Path filePath = Paths.get(CoreConstant.BASE_UPLOAD_PATH, fileName);

        FileInputStream fileInputStream = new FileInputStream(filePath.toFile());
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

        OutputStream outputStream = resp.getOutputStream();
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
        int ch = 0;
        while ((ch = bufferedInputStream.read()) != -1){
            bufferedOutputStream.write(ch);
        }

        bufferedInputStream.close();
        fileInputStream.close();

        bufferedOutputStream.close();
        outputStream.close();
    }
}
