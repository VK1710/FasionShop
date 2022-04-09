package com.main.Controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.context.annotation.SessionScope;

import com.main.Dao.CategoryDao;
import com.main.Dao.FavoriteDao;
import com.main.Dao.ManufactureDao;
import com.main.Dao.ProductCateDao;
import com.main.Dao.ProductDao;
import com.main.Dao.UserDao;
import com.main.Entity.Category;
import com.main.Entity.Manufacture;
import com.main.Entity.Product;
import com.main.Model.ShowCategory;
import com.main.Service.SessionService;

@Controller
public class IndexController {
	@Autowired
	SessionService sessionService;

	@Autowired
	CategoryDao categoryDao;

	@Autowired
	FavoriteDao favoriteDao;

	@Autowired
	ProductCateDao productcateDao;

	@Autowired
	UserDao dao;

	@Autowired
	ProductDao productDao;

	@Autowired
	ManufactureDao manuDao;

	@GetMapping("/index")
	public String index(Model model, Principal principal) {

		return "user/index";
	}

	@GetMapping("/logout")
	public String logout() {
		return "user/index";
	}

	@GetMapping("/successLogin")
	public String login(Model model, Principal principal) {
		User loginedUser = (User) ((Authentication) principal).getPrincipal();

		com.main.Entity.User entity = dao.findByEmail(loginedUser.getUsername());

		sessionService.set("userLogin", entity);
		int count = favoriteDao.countFavorite(entity.getId());
		sessionService.set("countFavorite", count);

		return "user/index";
	}

	@ModelAttribute("manufacture")
	public List<Manufacture> manufacture(Model model) {
		List<Manufacture> list = manuDao.findAll();
		return list;
	}

	@ModelAttribute("procate")
	public List<ShowCategory> procate(Model model) {
		List<ShowCategory> list = productcateDao.getSelectCategory();
		return list;
	}

	@ModelAttribute("views")
	public List<Product> fillViews(Model model) {
		List<Product> list = productDao.fillViewsDESC();
		return list;
	}

	@ModelAttribute("dates")
	public List<Product> fillDate(Model model) {
		List<Product> list = productDao.fillDateDESC();
		return list;
	}

}
