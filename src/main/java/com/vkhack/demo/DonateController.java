package com.vkhack.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Controller
@RequestMapping("/donate")
@CrossOrigin()
public class DonateController {
    @Autowired
    private DonateService donateService;
    @Autowired
    private PostService postService;

    @PostMapping("/create")
    public ResponseEntity createDonate(@RequestBody Donate donate) {
        try {
            Integer price = donate.getPrice();
            Random random = new Random(price);
            Integer collectedPrice = random.nextInt();
            donate.setCollectedPrice(collectedPrice);
            donateService.save(donate);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/image/{id}")
    public ResponseEntity addImageToDonate(@PathVariable("id") Long donateId,
                                           @RequestParam("image") MultipartFile multipartFile) {
        Optional.ofNullable(donateId)
                .map(id -> donateService.findDonateById(id))
                .ifPresent(donate -> {
                    Optional.ofNullable(multipartFile)
                            .map(this::getImageFromMultipartFile)
                            .ifPresent(arr -> {
                                donate.setImage(arr);
                                donateService.save(donate);
                            });
                });
        return ResponseEntity.ok(null);
    }

    private byte[] getImageFromMultipartFile(MultipartFile multipartFile) {
        try {
            return multipartFile.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping(value = {"/", "/index"})
    public String getIndex(@RequestBody Donate donate) {
        return "index";
    }

    @PostMapping("/post")
    public ResponseEntity createDonatePost(@RequestBody Post post) {
        try {
            postService.save(post);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/info")
    public Donate getDonateInfo(@RequestParam("id") Long id) {
        return donateService.findDonateById(id);
    }

    @GetMapping("/list")
    public List<Donate> getListOfDonates() {
        return donateService.findAll();
    }

    @DeleteMapping("/clear")
    public ResponseEntity removeDonates() {
        try {
            donateService.deleteAll();
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
