//package com.bakery_shop.controller;
//
//import com.bakery_shop.model.ApiResponse;
//import com.bakery_shop.model.request.FeedbackRequest;
//import com.bakery_shop.service.FeedBackService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/feedbacks")
//@RequiredArgsConstructor
//public class FeedbackController {
//
//    private final FeedBackService feedbackService;
//
//    // CREATE
//    @PostMapping
//    public ResponseEntity<ApiResponse<?>> createFeedback(@RequestBody FeedbackRequest request) {
//        // Service trả ApiResponse trực tiếp
//        return ResponseEntity.ok(feedbackService.create(request));
//    }
//
//    // READ ALL
//    @GetMapping
//    public ResponseEntity<ApiResponse<?>> getAllFeedbacks() {
//        return ResponseEntity.ok(feedbackService.getAll());
//    }
//
//    // READ ONE
//    @GetMapping("/{id}")
//    public ResponseEntity<ApiResponse<?>> getFeedbackById(@PathVariable Long id) {
//        return ResponseEntity.ok(feedbackService.getById(id));
//    }
//
//    // UPDATE
//    @PutMapping("/{id}")
//    public ResponseEntity<ApiResponse<?>> updateFeedback(@PathVariable Long id,
//                                                         @RequestBody FeedbackRequest request) {
//        return ResponseEntity.ok(feedbackService.update(id, request));
//    }
//
//    // DELETE
//    @DeleteMapping("/{id}")
//    public ResponseEntity<ApiResponse<?>> deleteFeedback(@PathVariable Long id) {
//        return ResponseEntity.ok(feedbackService.delete(id));
//    }
//}
