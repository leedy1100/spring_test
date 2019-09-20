package com.mvc.test.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mvc.test.board.biz.CustomerBiz;
import com.mvc.test.board.dto.CustomerDto;

@Controller
public class BoardController {

	@Autowired
	CustomerBiz biz;
	
	@RequestMapping("/")
	public String index() {
		
		return "index";
	}
	
	@RequestMapping("/list")
	public String selectList(Model model) {
		
		List<CustomerDto> resultList = biz.selectList();
		model.addAttribute("list",resultList);
		
		return "boardlist";
	}
	
	@RequestMapping(value = "/selectone", method = RequestMethod.GET)
	public String selectOne(Model model, String id) {

		model.addAttribute("dto", biz.selectOne(id));

		return "selectone";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(Model model, String id) {

		model.addAttribute("dto", biz.selectOne(id));

		return "update";
	}

//	@RequestMapping(value = "/updateres", method = RequestMethod.POST)
//	public String updateres(Model model, @ModelAttribute CustomerDto dto) {
//
//		model.addAttribute("dto", biz.update(dto));
//
//		int res = 0;
//
//		res = biz.update(dto);
//
//		if (res > 0) {
//			model.addAttribute("dto", biz.selectOne(dto.getId()));
//			return "selectone";
//		} else {
//			model.addAttribute("dto", biz.selectOne(dto.getId()));
//			return "selectone";
//		}
//
//	}

	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insert() {

		return "insert";
	}

	@RequestMapping(value = "/insertres", method = RequestMethod.POST)
	public String insertres(Model model, @ModelAttribute CustomerDto dto) {

		int res = 0;

		res = biz.insert(dto);

		if (res > 0) {
			model.addAttribute("list", biz.selectList());
			return "boardlist";
		} else {
			return "insert";
		}

	}

	@RequestMapping(value = "/delete")
	public String delete(Model model, String id) {

		int res = 0;

		res = biz.delete(id);

		if (res > 0) {
			model.addAttribute("list", biz.selectList());
			return "boardlist";
		} else {
			model.addAttribute("dto", biz.selectOne(id));
			return "selectone";
		}

	}
	
	@RequestMapping(value = "/loginform")
	public String login() {

		return "login";
	}

	@RequestMapping("/loginajax")
	@ResponseBody
	public Map<String, Boolean> loginAjax(String id, String password, HttpSession session) {
		// @ResponseBody : java 객체를 response 객체에 binding ,응답하면 요청되는 바디에 바로 넣어준다

		CustomerDto dto = biz.login(id, password);
		
		boolean loginchk = false;
		
		if (dto != null) {
			session.setAttribute("dto", dto);
			loginchk = true;
		}
		
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		map.put("loginchk", loginchk);

		return map;
	}
}
