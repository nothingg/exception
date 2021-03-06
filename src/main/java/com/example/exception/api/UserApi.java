package com.example.exception.api;

import com.example.exception.business.UserBusiness;
import com.example.exception.entity.User;
import com.example.exception.exception.BaseException;
import com.example.exception.model.MLoginRequest;
import com.example.exception.model.MRegisterRequest;
import com.example.exception.model.MRegisterResponse;
import com.example.exception.model.TestResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user")
public class UserApi {

    private final UserBusiness business;

    public UserApi(UserBusiness business) {
        this.business = business;
    }

    @GetMapping
    public TestResponse test(){
        TestResponse response = new TestResponse();
        response.setName("Nat");
        response.setFood("KFC");

        return response;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody MLoginRequest request) throws BaseException {

        String response = business.login(request);
        return ResponseEntity.ok(response);

    }


    @PostMapping("/register")
    public ResponseEntity<MRegisterResponse> register(@RequestBody MRegisterRequest request) throws BaseException {
        MRegisterResponse response = business.register(request);

        return ResponseEntity.ok(response);  // new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> uploadProfilePicture(@RequestPart MultipartFile file) throws BaseException {

        String response = business.uploadProfilePicture(file);
        return ResponseEntity.ok(response);
    }
}
