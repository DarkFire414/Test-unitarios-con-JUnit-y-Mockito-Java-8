package com.spring.JUnitIndexController.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {
    @PostMapping("/welcome")
    @ResponseBody
    public String welcome (
            @RequestParam(required = false, name = "params") String[] paramsArray
    ) {
        StringBuilder msg = new StringBuilder();
        if (paramsArray == null || paramsArray.length== 0) {
            msg.append("params está vacío");
        }
        else {
            for (int i = 0; i < paramsArray.length; i++) {
                msg.append("params[" + i + "] = " + paramsArray[i] + "\n");
            }
        }
        return msg.toString();
    }

}
