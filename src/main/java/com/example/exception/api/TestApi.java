package com.example.exception.api;

import com.example.exception.business.TestBusiness;
import com.example.exception.exception.BaseException;
import com.example.exception.model.MRegisterRequest;
import com.example.exception.model.TestResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/test")
public class TestApi {

    private final TestBusiness business;

    public TestApi(TestBusiness business) {
        this.business = business;
    }

    @GetMapping
    public TestResponse test(){
        TestResponse response = new TestResponse();
        response.setName("Nat");
        response.setFood("KFC");

        return response;
    }

    @PostMapping
    @RequestMapping("/register")
    public String register(@RequestBody MRegisterRequest request) throws BaseException {
        String response = business.register(request);

        return response;
    }

    @PostMapping
    public ResponseEntity<String> uploadProfilePicture(@RequestPart MultipartFile file){

        return null;
    }
}
