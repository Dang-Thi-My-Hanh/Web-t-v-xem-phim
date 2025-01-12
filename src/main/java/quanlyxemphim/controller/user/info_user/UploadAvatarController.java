package hcmuaf.nlu.edu.vn.quanlyxemphim.controller.user.info_user;

import hcmuaf.nlu.edu.vn.quanlyxemphim.model.Users;
import hcmuaf.nlu.edu.vn.quanlyxemphim.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

@WebServlet("/uploadAvatar")
@MultipartConfig(maxFileSize = 16177215) // Giới hạn kích thước tệp tải lên (16MB)
public class UploadAvatarController extends HttpServlet {
    private  final UserService userService ;
    public UploadAvatarController() {
        userService = new UserService();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy thông tin người dùng từ session
        Users user = (Users) request.getSession().getAttribute("user");


        // Xử lý tệp tải lên
        Part filePart = request.getPart("avatar");
        if (filePart != null && filePart.getSize() > 0) {
            // Đọc nội dung tệp và lưu vào hệ thống tệp
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String uploadDir = getServletContext().getRealPath("") + File.separator + "uploads" + File.separator + "avatars";
            File uploadDirFile = new File(uploadDir);
            if (!uploadDirFile.exists()) {
                uploadDirFile.mkdirs();
            }
            String filePath = uploadDir + File.separator + fileName;
            try (InputStream inputStream = filePart.getInputStream();
                 FileOutputStream outputStream = new FileOutputStream(filePath)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }

            // Cập nhật đường dẫn ảnh đại diện trong cơ sở dữ liệu
            String avatarPath = "uploads/avatars/" + fileName;
             userService.updateAvatar(user.getId(), avatarPath);
                  }

        // Chuyển hướng về trang thông tin khách hàng
        request.getRequestDispatcher("/informationCustomer").forward(request, response);
    }
}

