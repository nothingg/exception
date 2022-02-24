package com.example.exception.service;

import com.example.exception.entity.Social;
import com.example.exception.entity.User;
import com.example.exception.exception.BaseException;
import com.example.exception.exception.UserException;
import com.example.exception.repository.SocialRepository;
import com.example.exception.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class SocialService {

    private final SocialRepository socialRepository;

    public SocialService( SocialRepository socialRepository) {
        this.socialRepository = socialRepository;
    }

    public Optional<Social> findByUser(User user){
        return socialRepository.findByUser(user);
    }

    public Social createSocial(User user,String facebook , String line , String instagram , String tiktok){
        Social social = new Social();
        social.setFacebook(facebook);
        social.setLine(line);
        social.setInstagram(instagram);
        social.setTiktok(tiktok);
        social.setUser(user);

        return socialRepository.save(social);
    }

}
