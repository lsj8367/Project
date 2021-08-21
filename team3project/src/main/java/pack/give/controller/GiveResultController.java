package pack.give.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pack.give.dao.GiveDao;
import pack.model.OldBookDto;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class GiveResultController {
	private final GiveDao giveDao;
	
	@RequestMapping(value = "showgive")
	@ResponseBody
	public Map<String, Object> getGiveResult(HttpSession session){
		String ob_userid = (String)session.getAttribute("id");

		List<Map<String, String>> dataList = new ArrayList<Map<String,String>>();
		Map<String, String> data = new HashMap<>();
		
		List<OldBookDto> obList = giveDao.getGiveList(ob_userid);
		for(OldBookDto d:obList) {
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
