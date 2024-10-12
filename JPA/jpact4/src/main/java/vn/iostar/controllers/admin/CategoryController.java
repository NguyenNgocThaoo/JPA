package vn.iostar.controllers.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import vn.iostar.entity.*;
import vn.iostar.services.*;
import vn.iostar.services.impl.*;
import vn.iostar.ultis.Constant;
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)

@WebServlet(urlPatterns = { "/admin/categories", "/admin/category/add", "/admin/category/insert",
		"/admin/category/edit", "/admin/category/update", "/admin/category/delete", "/admin/category/search",})
public class CategoryController extends HttpServlet{
	CategoryService cateService = new CategoryService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Xem, Xóa, Tìm kiếm chỉ có ở doget
				String url = req.getRequestURL().toString();
				resp.setCharacterEncoding("UTF-8");
				req.setCharacterEncoding("UTF-8");
				if (url.contains("categories")) {
					List<Category> list = cateService.findAll();
					req.setAttribute("listcate", list);
					req.getRequestDispatcher("/WEB-INF/views/admin/category-list.jsp").forward(req, resp);
				} else if (url.contains("add")) {
					req.getRequestDispatcher("/WEB-INF/views/admin/category-add.jsp").forward(req, resp);
				} else if (url.contains("edit")) {
					int id = Integer.parseInt(req.getParameter("id"));
					Category category = cateService.findById(id);
					
					req.setAttribute("cate", category);
					req.getRequestDispatcher("/WEB-INF/views/admin/category-edit.jsp").forward(req, resp);
				} else if (url.contains("delete")) {
					int id = Integer.parseInt(req.getParameter("id"));			
					try {
						cateService.delete(id);
					} catch (Exception e) {
						e.printStackTrace();
					}
					resp.sendRedirect(req.getContextPath() + "/admin/categories");
				}
		 
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURL().toString();
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");

		if (url.contains("insert")) {
			Category category = new Category();
			String catename = req.getParameter("categoryname");
			int status = Integer.parseInt(req.getParameter("status"));
			category.setCategoryname(catename);
			category.setStatus(status);
			String imagelink = req.getParameter("imagelink");
			
			String fname="";
			String uploadPath = Constant.UPLOAD_DIR;
			File uploadDir = new File(uploadPath);
			if(!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				Part part = req.getPart("images");
				if(part.getSize()>0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					//Đổi tên file
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index+1);
					fname = System.currentTimeMillis() + "." + ext;
					//Upload file 
					part.write(uploadPath + "/" + fname);
					//Ghi tên file vào data
					category.setImages(fname);
				}
				else if(imagelink != null)
				{
					category.setImages(imagelink);
				}
				else {
					category.setImages("avata.png");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			cateService.insert(category);
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
		}
		else if (url.contains("update")){
			int categoryid = Integer.parseInt(req.getParameter("categoryid"));
			String catename = req.getParameter("categoryname");
			int status = Integer.parseInt(req.getParameter("status"));
			Category category = new Category();
			category.setCategoryId(categoryid);
			category.setCategoryname(catename);
			category.setStatus(status);
			
			//Lưu ảnh cũ
			Category cateold = cateService.findById(categoryid);
			String fileold = cateold.getImages();
			// Xử lí images
			String fname="";
			String uploadPath = Constant.UPLOAD_DIR;
			File uploadDir = new File(uploadPath);
			if(!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				Part part = req.getPart("images");
				if(part.getSize()>0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					//Đổi tên file
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index+1);
					fname = System.currentTimeMillis() + "." + ext;
					//Upload file 
					part.write(uploadPath + "/" + fname);
					//Ghi tên file vào data
					category.setImages(fname);
				}
				else {
					category.setImages(fileold);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			cateService.update(category);
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
		}
	}
	
}
