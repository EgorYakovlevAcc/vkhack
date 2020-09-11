package com.vkhack.demo.controller;

import com.vkhack.demo.service.DonateService;
import com.vkhack.demo.service.PostService;
import com.vkhack.demo.model.Donate;
import com.vkhack.demo.model.Post;
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
            Integer collectedPrice = random.nextInt(price) + 1;
            donate.setCollectedPrice(collectedPrice);
            donateService.save(donate);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("createDonate FAILED");
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
    public String getIndex() {
        return "index";
    }

    @PostMapping("/post/{id}")
    public ResponseEntity createDonatePost(@PathVariable("id") Long id, @RequestBody Post post) {
        try {
            Donate donate = donateService.findDonateById(id);
            post.setDonate(donate);
            postService.save(post);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("createDonatePost FAILED");
        }
    }

    @GetMapping("/info")
    @ResponseBody
    public Donate getDonateInfo(@RequestParam("id") Long id) {
        return donateService.findDonateById(id);
    }

    @GetMapping("/list")
    @ResponseBody
    public List<Donate> getListOfDonates() {
        return donateService.findAll();
    }

    @DeleteMapping("/clear")
    public ResponseEntity removeDonates() {
        try {
            donateService.deleteAll();
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("getDonateInfo FAILED");
        }
    }

    @DeleteMapping("/clear/post/{postId}")
    public ResponseEntity removeDonates(@PathVariable("postId") Long postId) {
        try {
            postService.deletePostById(postId);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("removeDonates FAILED");
        }
    }
}
