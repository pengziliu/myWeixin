package com.lzp.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lzp.bean.Page;
import com.lzp.dao.QuestionsRepositoty;
import com.lzp.entity.Questions;
import com.lzp.service.QuestionsService;
import com.lzp.util.ParamUtils;

@Controller
@RequestMapping("/question/")
public class QuestionController {
	
		@Autowired
		private QuestionsRepositoty questionsRepositoty;
		
		
		@Autowired
		private QuestionsService questionsService;
	
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
	    @RequestMapping("index")
	    public ModelAndView index() {
	    	ModelAndView modelAndView = new ModelAndView("question/index");
	    	modelAndView.addObject("alist", questionsRepositoty.findAll());
	        return modelAndView;
	    }
	    
	    @RequestMapping(value="save",method = RequestMethod.POST)
	    public String index(Model model, Questions questions) {
	    	
	    	if(questionsRepositoty.findByTitle(questions.getTitle())!=null){
	    		model.addAttribute("error","唯一字段冲突");
	    	}
	    	questions.setCreateDate(df.format(new Date()));
	    	questionsRepositoty.save(questions);
	    	model.addAttribute("alist", questionsRepositoty.findAll());
	        return "question/index";
	    }
	    
	    @RequestMapping(value="delete",method = RequestMethod.GET)
	    public String delete(String id,Model model) {
	    	
	    	questionsRepositoty.delete(id);
	    	model.addAttribute("alist", questionsRepositoty.findAll());
	        return "question/index";
	    }
	
	    
	    @RequestMapping("/list")
	    @ResponseBody
	    public Page list(Integer pageSize,Integer pageNum,String type ) {
	    	return questionsService.list(pageSize,pageNum,type);
	    }
	

}
