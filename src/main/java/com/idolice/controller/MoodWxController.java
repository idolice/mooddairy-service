package com.idolice.controller;

import com.idolice.domain.request.WxUserMoodInfoVO;
import com.idolice.service.WxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MoodWxController {
    @Autowired
    WxService wxService;
    @RequestMapping(value = "/openId")
    public ResponseEntity<String> getOpenId(@RequestParam String jsCode) {
       return wxService.getOpenId(jsCode);
    }

    @RequestMapping(value = "/userMood", method = RequestMethod.POST)
    public HttpStatus saveUserMood(@RequestBody WxUserMoodInfoVO wxUserMoodInfoVO) {
        return wxService.saveUserMood(wxUserMoodInfoVO);
    }




}
