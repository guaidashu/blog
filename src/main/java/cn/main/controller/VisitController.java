package cn.main.controller;

import cn.main.dao.DAOFactory;
import cn.main.tool.ResultJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "visit")
public class VisitController {

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public @ResponseBody
    ResultJson index() {
        ResultJson resultJson = new ResultJson();
        List<Map> list = null;
        try {
            list = DAOFactory.getVisitInstance().queryOne();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int count = Integer.parseInt(list.get(0).get("visit_count").toString());
        count = count + 1;
        Map<String, String> map = new HashMap<>();
        map.put("visit_count", count + "");
        map.put("id", list.get(0).get("id").toString());
        try {
            int result = DAOFactory.getVisitInstance().update(map);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resultJson.setText("ok");
        return resultJson;
    }
}
