package base.controller;

import base.service.OAuth20GoogleService;
import com.github.scribejava.core.oauth.OAuth20Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

@Controller
public class GoogleController {

    @Autowired
    private OAuth20GoogleService googleService;


    @RequestMapping(value = "/login-google", method = RequestMethod.GET)
    public String loginInGoogle() {
        OAuth20Service oAuth20Service = googleService.getService();
        return "redirect:" + googleService.getAuth(oAuth20Service);
    }

    @GetMapping(value = "/callback")
    public String callback(@RequestParam String code, HttpServletResponse servletResponse) {
        try {
            googleService.setAuthentication(code);
        } catch (Exception e) {
            servletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        return "redirect:/login";
    }
}
