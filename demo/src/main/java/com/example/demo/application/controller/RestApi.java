package com.example.demo.application.controller;

import com.example.demo.application.resource.RestReq;
import com.example.demo.application.resource.RestRes;
import com.example.demo.domain.service.RestService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class RestApi {
    private final RestService restService;

    // --------------------------
    // -- Basic Http Requests. --
    // --------------------------
    // GET    - retrieves data.
    // POST   - creates data.
    // PUT    - updates data entirely.
    // PATCH  - allows partially updating data.
    // DELETE - removes data.
    @GetMapping("/")
    // @PostMapping("/")
    // @PutMapping("/")
    // @PatchMapping("/")
    // @DeleteMapping("/")
    public ResponseEntity<RestRes> demoGet() {
        String content = restService.doSomething();
        RestRes restRes = new RestRes(content);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(restRes, headers, HttpStatus.OK);
    }

    // ------------------------
    // -- Parameter Control. --
    // ------------------------
    @GetMapping("/pc")
    public ResponseEntity<String> reqParam(@RequestParam int id) {
        String content = restService.doSomething(id);
        // snip
        return new ResponseEntity<>("Request Param", null, HttpStatus.OK);
    }

    @GetMapping("/pc/{id}")
    public ResponseEntity<String> pathVar(@PathVariable int id) {
        String content = restService.doSomething(id);
        // snip
        return new ResponseEntity<>("Path Variable", null, HttpStatus.OK);
    }

    @PostMapping("/pc")
    public ResponseEntity<String> reqMapping(RestReq restReq) {
        String content = restService.doSomething(restReq.getId());
        // snip
        return new ResponseEntity<>("Request Mapping", null, HttpStatus.OK);
    }

    // -------------------------
    // -- Validation Samples. --
    // -------------------------
    @GetMapping("/valid")
    public ResponseEntity<String> reqParam(
            @RequestParam("id") @Min(0) @Max(1) int id,
            @RequestParam("name") @Size(min = 1, max = 5) String name) {
        // snip
        return new ResponseEntity<>("Valid - Request Param", null, HttpStatus.OK);
    }

    @GetMapping("/valid/{id}/{name}")
    public ResponseEntity<String> pathVar(
            @PathVariable("id") @Min(0) @Max(1) int id,
            @PathVariable("name") @Size(min = 1, max = 5) String name) {
        // snip
        return new ResponseEntity<>("Valid - Path Variable", null, HttpStatus.OK);
    }

    @PostMapping("/valid")
    public ResponseEntity<String> reqBody(@RequestBody @Valid RestReq restReq) {
        // snip
        return new ResponseEntity<>("Valid - Request Body", null, HttpStatus.OK);
    }
}
