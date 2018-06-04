package com.idolice.controller;

import com.idolice.Model.MoodIndex;
import com.idolice.domain.request.WxUserMoodInfoVO;
import com.idolice.service.MoodService;
import com.idolice.service.WxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MoodWxController {
    @Autowired
    WxService wxService;
    @Autowired
    MoodService moodService;
    @RequestMapping(value = "/openId")
    public ResponseEntity<String> getOpenId(@RequestParam String jsCode) {
       return wxService.getOpenId(jsCode);
    }

    @RequestMapping(value = "/userMood", method = RequestMethod.POST)
    public HttpStatus saveUserMood(@RequestBody WxUserMoodInfoVO wxUserMoodInfoVO) {
        return wxService.saveUserMood(wxUserMoodInfoVO);
    }

    @RequestMapping(value = "/mood/{month}/{openId}", method = RequestMethod.GET)
    public ResponseEntity<List<MoodIndex>> getMoodOfMonth(@PathVariable int month, @PathVariable String openId) {
        ResponseEntity<List<MoodIndex>> responseEntity =
                new ResponseEntity<>(moodService.getMoodForMonth(month, openId), HttpStatus.ACCEPTED);
        return responseEntity;
    }




}
