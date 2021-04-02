package pack.give.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import pack.give.common.CommandMap;
import pack.give.dao.GiveDao;
import pack.give.service.GiveService;
import pack.model.OldBookDto;
import pack.model.UserDto;
import pack.user.model.UserInter;

@Controller
public class GiveResultController {
	Log log = LogFactory.getLog(GiveResultController.class);

	@Autowired
	GiveService giveService;

	@Autowired
	UserInter userInter;

	@Autowired
	private GiveDao giveDao;
	
	@RequestMapping(value = "showgive")
	@ResponseBody
	public Map<String, Object> getGiveResult(HttpSession session){
		String ob_userid = (String)session.getAttribute("id");

		List<Map<String, String>> dataList = new ArrayList<Map<String,String>>();
		Map<String, String> data = null;
		
		List<OldBookDto> obList = giveDao.getGiveList(ob_userid);
		for(OldBookDto d:obList) {
			data = new HashMap<String, String>();
			data.put("ob_donor", d.getOb_donor());
			data.put("ob_no", Integer.toString(d.getOb_no()));
			data.put("ob_name", d.getOb_name());
			data.put("ob_author", d.getOb_author());
			data.put("ob_ddate", d.getOb_ddate());
			data.put("ob_comp", d.getOb_comp());
			data.put("ob_comment", d.getOb_comment());
			data.put("ob_genre", d.getOb_genre());
			data.put("ob_inter", d.getOb_inter());
			data.put("ob_donor", d.getOb_donor());
			data.put("user_id", d.getUser_id());
			data.put("user_name", d.getUser_name());
			dataList.add(data);
		}
		Map<String, Object> giveDatas = new HashMap<String, Object>();
		giveDatas.put("datas", dataList);
		return giveDatas;
	}
}
