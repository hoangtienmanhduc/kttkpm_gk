package com.example.bank.controller;

import com.example.bank.models.Account;
import com.example.bank.repositories.AccountRepository;
import com.example.bank.services.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@ComponentScan(basePackages = " com.example.bank")
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/login")
    public String login(Principal principal) {
        if (principal != null) {
            return "redirect:/";
        } else {
            return "login";
        }
    }

    @GetMapping("/signup")
    public String register() {
        return "registerForm";
    }

    @PostMapping("/signup")
    public String register(@RequestParam(name = "phone") String phone,
                           @RequestParam(name = "cccdnumber") String cccdnumber,
                           @RequestParam(name = "accountname") String accountname,
                           @RequestParam(name = "accountnumber") String accountnumber,
                           @RequestParam(name = "address") String address,
                           @RequestParam(name = "password") String password,
                           HttpServletRequest request,
                           Model model) {

        Account account = new Account(phone, cccdnumber,accountname,accountnumber,address, password);
        try {
            authService.accountRegister(account);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "registerForm";
        }
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam(name = "phone") String phone,
                        @RequestParam(name = "password") String password,
                        HttpSession session,
                        HttpServletResponse response) {

        if (authService.accountLogin(phone, password)) {

            Account account = null;
            if (accountRepository.findAccountByPhone(phone).isPresent()) {
                account = accountRepository.findAccountByPhone(phone).get();
            }

            // set session
            response.addCookie(new Cookie("phone", account.getPhone()));
            response.addCookie(new Cookie("isLogin", "true"));
            session.setAttribute("isLogin", true);
            session.setAttribute("account", account);
            return "redirect:/";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, HttpServletResponse response) {
        session.invalidate();
        response.addCookie(new Cookie("isLogin", "false"));
        response.addCookie(new Cookie("phone", ""));
        return "redirect:/login";
    }
}
