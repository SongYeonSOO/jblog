package com.estsoft.jblog.controller;

import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.estsoft.jblog.annotation.Auth;
import com.estsoft.jblog.service.BlogService;
import com.estsoft.jblog.vo.BlogVo;
import com.estsoft.jblog.vo.CategoryVo;
import com.estsoft.jblog.vo.PostVo;

@Controller
@RequestMapping("/blog")
public class BlogController {
	@Autowired
	private BlogService blogService;
	@Autowired
	private BlogService postService;
	@Autowired
	private BlogService categoryService;
	private static final String SAVE_PATH = "/temp";

	@RequestMapping("/{blogId}")
	public String MyBlogMain1(@PathVariable("blogId") String blogId, Model model) {
		// category list
		List<CategoryVo> clist = blogService.SearchCategory(blogId);
		model.addAttribute("category", clist);

		Long category_no = clist.get(0).getCategory_no();
		model.addAttribute("category_no", category_no);

		BlogVo bvo = blogService.Blog(blogId);
		model.addAttribute("bvo", bvo);

		// post list
		PostVo onepvo;
		CategoryVo cvo = clist.get(0);
		List<PostVo> plist = blogService.SearchPost(cvo);
		if (plist.isEmpty()) {
			onepvo = null;
		} else {
			onepvo = plist.get(0);
		}
		model.addAttribute("onepvo", onepvo);
		model.addAttribute("plist", plist);
		model.addAttribute("blogId", blogId);

		return "blog/blog-main";

	}

	@RequestMapping("/{blogId}/{category_no}")
	public String MyBlogMain2(@PathVariable("category_no") Long category_no, @PathVariable("blogId") String blogId,
			@RequestParam(value = "no", required = true, defaultValue = "-1") Long no, Model model) {

		// category list
		List<CategoryVo> clist = blogService.SearchCategory(blogId);
		model.addAttribute("category", clist);

		BlogVo bvo = blogService.Blog(blogId);
		model.addAttribute("bvo", bvo);
		// post list
		CategoryVo cvo = new CategoryVo();
		cvo.setCategory_no(category_no);
		List<PostVo> plist = blogService.SearchPost(cvo);
		if (plist.isEmpty()) {
			plist = null;
		}
		model.addAttribute("plist", plist);
		System.out.println(plist);
		System.out.println("no" + no);

		// postView
		PostVo onepvo = null;
		if (no == -1 && plist != null) {
			onepvo = plist.get(0);
		} else if (no != -1) {
			onepvo = postService.SearchOnePost(no);
			System.out.println(onepvo);
		}

		model.addAttribute("no", no);
		model.addAttribute("onepvo", onepvo);
		// 기타 보낼 것
		model.addAttribute("blogId", blogId);
		model.addAttribute("category_no", category_no);

		return "blog/blog-main";

	}

	// blog관리
	@RequestMapping("/{blogId}/blog-admin-basic")
	public String AdminBasic(@PathVariable("blogId") String blogId, Model model) {

		BlogVo bvo = blogService.Blog(blogId);
		model.addAttribute("bvo", bvo);

		return "blog/blog-admin-basic";
	}

	// HOW TO CONTROL LOGO?
	@RequestMapping("/{blogId}/blog-admin-basic/upload")
	public String Fileupload(@PathVariable("blogId") String blogId, @RequestParam("logo") MultipartFile file1,
			@RequestParam("title") String title, Model model) {

		BlogVo vo = new BlogVo();
		vo.setId(blogId);
		vo.setTitle(title);

		// 첫 번째 파일 처리
		if (file1.isEmpty() == false) {

			// 파일 이름 처리
			String fileOriginalName = file1.getOriginalFilename();
			String extName = fileOriginalName.substring(fileOriginalName.lastIndexOf(".") + 1,
					fileOriginalName.length());
			String saveFileName = genSaveFileName(extName);
			Long size = file1.getSize();
			// 파일복사해서 이동 및 이름변경
			writeFile(file1, SAVE_PATH, saveFileName);
			String url = "/product-images/" + saveFileName;
			model.addAttribute("productImageUrl1", url);

			vo.setLogo(url);
			if (vo.getTitle() == null) {
				vo.setTitle("modify your blog title");
			}
			if (vo.getLogo() == null) {
				vo.setLogo("modify your logo");
			}

			blogService.UpdateBlog(vo);

			model.addAttribute("bvo", vo);
		}

		return "redirect:/blog/" + blogId;
	}

	private void writeFile(MultipartFile file, String path, String fileName) {
		FileOutputStream fos = null;
		try {
			byte fileData[] = file.getBytes();
			fos = new FileOutputStream(path + "/" + fileName);
			fos.write(fileData);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (Exception e) {
				}
			}
		}
	}

	private String genSaveFileName(String extName) {

		Calendar calendar = Calendar.getInstance();
		String fileName = "";

		fileName += calendar.get(Calendar.YEAR);
		fileName += calendar.get(Calendar.MONTH);
		fileName += calendar.get(Calendar.DATE);
		fileName += calendar.get(Calendar.HOUR);
		fileName += calendar.get(Calendar.MINUTE);
		fileName += calendar.get(Calendar.SECOND);
		fileName += calendar.get(Calendar.MILLISECOND);
		fileName += ("." + extName);

		return fileName;
	}

	////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping("/{blogId}/blog-admin-category")
	public String Category(@PathVariable("blogId") String blogId, Model model) {
		BlogVo bvo = blogService.Blog(blogId);
		model.addAttribute("bvo", bvo);

		List<CategoryVo> clist = blogService.SearchCategory(blogId);
		model.addAttribute("category", clist);
		model.addAttribute("blogId", blogId);
		return "blog/blog-admin-category";

	}

	@RequestMapping("/{blogId}/blog-admin-categoryList")
	@ResponseBody
	public Map<String, Object> Category2(@PathVariable("blogId") String blogId) {

		List<CategoryVo> clist = blogService.SearchCategory(blogId);

		Map<String, Object> map = new HashMap<>();
		map.put("data", clist);

		return map;

	}

	@RequestMapping("/{blogId}/blog-admin-categorying")
	@ResponseBody
	public Map<String, Object> CategoryInsert(@PathVariable("blogId") String blogId, @ModelAttribute CategoryVo cvo) {
		BlogVo bvo = blogService.Blog(blogId);
		cvo.setNo(bvo.getNo());
		cvo.setPost_count(0L);
		categoryService.insertCate(cvo);

		Map<String, Object> map = new HashMap<>();
		map.put("data", cvo);
		map.put("result", "success");
		return map;

	}

	@RequestMapping("/{blogId}/blog-admin-categorydelete/{category_no}")
	@ResponseBody
	public Map<String, Object> CategoryDelete(@PathVariable("category_no") Long category_no,
			@PathVariable("blogId") String blogId, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("ooooooooooo");
		categoryService.deleteCate(category_no);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("data", true);
		return map;
	}

	@RequestMapping("/{blogId}/blog-admin-write")
	public String Write(@ModelAttribute PostVo vo, @PathVariable("blogId") String blogId, Model model) {
		BlogVo bvo = blogService.Blog(blogId);
		model.addAttribute("bvo", bvo);
		List<CategoryVo> clist = blogService.SearchCategory(blogId);
		model.addAttribute("category", clist);
		model.addAttribute("vo", vo);
		model.addAttribute("blogId", blogId);
		return "blog/blog-admin-write";
	}

	@Auth
	@RequestMapping("/{blogId}/blog-admin-writing")
	// @AuthUser로 받는 parameter는 반드시 인증된 사용자가 넘어오게된다
	public String write(@ModelAttribute PostVo vo, @PathVariable("blogId") String blogId,
			@RequestParam(value = "category") Long cvono) {
		vo.setCategory_no(cvono);
		postService.insert(vo);
		categoryService.countUpdate(vo.getCategory_no());

		return "redirect:/blog/" + blogId;
	}

}
