package C043.PowerUp.controllers;

import C043.PowerUp.entities.Review;
import C043.PowerUp.entities.User;
import C043.PowerUp.payloads.RespDTO;
import C043.PowerUp.payloads.ReviewDTO;
import C043.PowerUp.security.Validation;
import C043.PowerUp.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private Validation validation;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RespDTO postReview(@AuthenticationPrincipal User user,
                              @RequestBody @Validated ReviewDTO body,
                              BindingResult validation) {
        this.validation.validate(validation);
        Review newReview = this.reviewService.postReview(user, body);
        return new RespDTO(newReview.getId());
    }

    @GetMapping("/{gameId}")
    public List<Review> getAllReviews(@PathVariable int gameId) {
        return this.reviewService.getAllReviews(gameId);
    }

    @PutMapping
    public RespDTO updateReview(@AuthenticationPrincipal User user,
                                @RequestBody @Validated ReviewDTO body,
                                BindingResult validation) {
        this.validation.validate(validation);
        Review updatedReview = this.reviewService.updateReview(user, body);
        return new RespDTO(updatedReview.getId());
    }

    @DeleteMapping("/{gameId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReview(@AuthenticationPrincipal User user,
                             @PathVariable int gameId) {
        this.reviewService.deleteReview(user, gameId);
    }

    @GetMapping("/me/{gameId}")
    public Review getMyReview(@AuthenticationPrincipal User user,
                              @PathVariable int gameId) {
        return this.reviewService.getUserReview(user, gameId);
    }
}
