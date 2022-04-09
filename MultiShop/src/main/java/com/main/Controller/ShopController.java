package com.main.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.main.Dao.CategoryDao;
import com.main.Dao.ManufactureDao;
import com.main.Dao.ProductCateDao;
import com.main.Dao.ProductColorDao;
import com.main.Dao.ProductDao;
import com.main.Dao.ProductSizeDao;
import com.main.Dao.UserDao;
import com.main.Entity.Category;
import com.main.Entity.Product;
import com.main.Model.ShowCategory;
import com.main.Model.ShowSelect;
import com.main.Service.SessionService;
import com.main.Service.ShoppingCartServiceImpl;

@Controller
public class ShopController {
	
	@Autowired
	SessionService sessionService;
	
	@Autowired
	CategoryDao categoryDao;
	
	@Autowired
	ProductCateDao productcateDao;
	
	@Autowired
	ProductSizeDao productsizeDao;
	
	@Autowired
	ProductColorDao productcolorDao;

	@Autowired
	UserDao dao;
	
	@Autowired
	ProductDao productDao;
	
	@Autowired
	ManufactureDao manuDao;
	
	
	@GetMapping("/shop/category/{id}")
	public String index(@PathVariable("id") int id, Model model,
			@RequestParam("p") Optional<Integer> p,@RequestParam(required = false) Integer sort){
		if(sort == null) {
		sessionService.set("cateId", id);
		Pageable pageable = PageRequest.of(p.orElse(0), 9);
		Page<Product> list = productDao.fillShopDESC(id, pageable);
		model.addAttribute("shop", list);
		}else {
			sessionService.set("cateId", id);
		Pageable pageable = PageRequest.of(p.orElse(0), 9);
		if(sort == 1) {
		Page<Product> list = productDao.fillPriceLow(id, pageable);
		model.addAttribute("shop", list);
		}else {
		Page<Product> list = productDao.fillPriceHigh(id, pageable);
		model.addAttribute("shop", list);}
		}
		return "user/shop";
	}
	
	public String index1(@PathVariable("id") int id, Model model,
			@RequestParam("p") Optional<Integer> p){
		sessionService.set("cateId", id);
		Pageable pageable = PageRequest.of(p.orElse(0), 9);
		Page<Product> list = productDao.fillShopDESC(id, pageable);
		model.addAttribute("shop", list);
		return "user/shop";
	}
	
	@ModelAttribute("nameCate")
	public String nameCate(@PathVariable("id") int id) {
		Category entity = categoryDao.getById(id);
		return entity.getName();
	}
	
	@ModelAttribute("procate")
	public List<ShowCategory> procate(Model model) {
		List<ShowCategory> list = productcateDao.getSelectCategory();
		return list;
	}
	
	@ModelAttribute("color")
	public List<ShowSelect> color(@PathVariable("id") int id) {
		List<ShowSelect> list = productcolorDao.getSelectColor(id);
		return list;
	}
	
	@ModelAttribute("size")
	public List<ShowSelect> size(@PathVariable("id") int id) {
		List<ShowSelect> list = productsizeDao.getSelectSize(id);
		return list;
	}
	
}
