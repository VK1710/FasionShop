package com.main.Controller;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.main.Dao.SizeDao;
import com.main.Dao.StreamDao;
import com.main.Entity.Size;
import com.main.Entity.Stream;
import com.main.Model.SizeModel;
import com.main.Model.StreamModel;
import com.main.Service.SessionService;
import com.main.Service.StreamService;


@Controller
public class LiveController {
	
	@Autowired
	StreamService sv;
	
	@Autowired
	StreamDao dao;
	
	@Autowired
	SessionService session;
	
	@RequestMapping(value = {"/admin/LiveStream/Link"},method = RequestMethod.GET)
	public String showPage(Model model) {
		StreamModel entity = new StreamModel();
	model.addAttribute("linkStream",entity);
		return "manager/addStream";
	}
	
	@PostMapping("/admin/LiveStream/Link")
	public String save(Model model, @ModelAttribute("linkStream")@Valid StreamModel entity, BindingResult result) {
		try {
		
		if(entity.getLinkStream().isEmpty()) {
			result.rejectValue("LinkStream", "NotBlank.addStream.name");
			return "manager/addStream";
		}else{
			
			 Stream stream = new Stream(); 
			 stream.setValue(entity.getLinkStream());
			 entity.setId_Stream(1);
			 stream.setId_Stream(entity.getId_Stream());
			 
				dao.save(stream);
			model.addAttribute("Link",entity);
			return "user/live";}
		}catch (DataException e) {
			model.addAttribute("alert","Vui Long Xoa Du Lieu");
		}
		return null;
	}

	/*
	 * @RequestMapping(value = {"/live"},method = RequestMethod.GET) public String
	 * index(Model model) { StreamModel entity = new StreamModel();
	 * model.addAttribute("Link",entity.getLinkStream());
	 * System.out.println(entity.getLinkStream()); return "/user/live"; }
	 */
	@RequestMapping	("/live")
	public String live(Model model) {;
		List<Stream> listStream = sv.listAll();
			Collection<Stream> arr = dao.findID();
			System.out.println(arr);
			model.addAttribute("Link", listStream); 
			return "/user/live";
	}

}
