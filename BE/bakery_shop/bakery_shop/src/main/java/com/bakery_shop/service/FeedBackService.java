//package com.bakery_shop.service;
//
//import com.bakery_shop.model.ApiResponse;
//import com.bakery_shop.model.Mapper;
//import com.bakery_shop.model.request.FeedbackRequest;
//import com.bakery_shop.repository.FeedbackRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class FeedBackService {
//
//    private final FeedbackRepository feedbackRepository;
//    private final Mapper mapper;
//
//    // CREATE
//    public ApiResponse<?> create(FeedbackRequest request) {
//        FeedbackEntity entity = mapper.toFeedbackEntity(request); // map Request -> Entity
//        FeedbackEntity saved = feedbackRepository.save(entity);
//        return ApiResponse.success(saved, "Feedback created successfully");
//    }
//
//    // READ ALL
//    public ApiResponse<?> getAll() {
//        List<FeedbackEntity> list = feedbackRepository.findAll();
//        return ApiResponse.success(list, "List of feedbacks retrieved successfully");
//    }
//
//    // READ ONE
//    public ApiResponse<?> getById(Long id) {
//        FeedbackEntity entity = feedbackRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Feedback not found"));
//        return ApiResponse.success(entity, "Feedback retrieved successfully");
//    }
//
//    // UPDATE
//    public ApiResponse<?> update(Long id, FeedbackRequest request) {
//        FeedbackEntity existing = feedbackRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Feedback not found"));
//
//        existing.setName(request.getName());
//        existing.setMessage(request.getMessage());
//
//        FeedbackEntity updated = feedbackRepository.save(existing);
//        return ApiResponse.success(updated, "Feedback updated successfully");
//    }
//
//    // DELETE
//    public ApiResponse<?> delete(Long id) {
//        feedbackRepository.deleteById(id);
//        return ApiResponse.success(null, "Feedback deleted successfully");
//    }
//}
