package com.bytemaximus.sms2fa.controller;

import com.bytemaximus.sms2fa.repository.CredentialRepository;
import com.bytemaximus.sms2fa.repository.TokenRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class NavigationController {

    private CredentialRepository credentialRepository;
    private TokenRepository tokenRepository;

    public NavigationController(CredentialRepository credentialRepository, TokenRepository tokenRepository) {
        this.credentialRepository = credentialRepository;
        this.tokenRepository = tokenRepository;
    }

    @GetMapping("/index")
    public String showMainPage(Model model) {
        model.addAttribute("credentials", credentialRepository.findAll());
        return "index";
    }

    @GetMapping("/credential/{credentialId}/tokens")
    public String showTokens(Model model, @PathVariable Integer credentialId){
        model.addAttribute("tokens", tokenRepository.findAllByCredentialId(credentialId));
        return "tokens";
    }

    @GetMapping("/addcredential")
    public String addCredential() {
        return "add-credential";
    }

    @GetMapping("/addtoken")
    public String addToken() {
        return "add-token";
    }
}
