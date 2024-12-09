package com.example.springbasicjsp.controller;

import com.example.springbasicjsp.data.Post;
import com.example.springbasicjsp.repository.PostRepository;
import jakarta.validation.constraints.Null;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.Optional;



@Controller
public class PostController {

    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @PostMapping("/mvc/posts/save")
    public String savePost(@ModelAttribute @Valid Post post, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errorMessage", "입력값이 올바르지 않습니다.");
            return "post-form"; // post-form.jsp로 이동
        }

        try {
            // 데이터 저장 로직
            postRepository.savePostUser(post); // 사용자 정보 저장
            post.setUserId(post.getId()); // ID 설정
            postRepository.savePostWork(post); // 작업 정보 저장

            return "redirect:/mvc/posts/list"; // 성공 시 목록 페이지로 이동
        } catch (Exception e) {
            // 예외를 처리하고 사용자에게 알림
            e.printStackTrace();
            model.addAttribute("errorMessage", "데이터 저장 중 오류가 발생했습니다: " + e.getMessage());
            return "error-page"; // error-page.jsp로 이동
        }

    }
    @GetMapping("/mvc/posts/list")
    public String listPosts(Model model) {
        // 작성자 데이터 가져오기
        List<Post> posts = postRepository.findAllPosts();
        // 디버깅용 데이터 확인
        System.out.println("Retrieved posts: " + posts.size());
        for (Post post : posts) {
            System.out.println(post); // 데이터 출력 확인
        }
        model.addAttribute("posts", posts);
        return "post-form"; // post-list.jsp로 이동
    }

    @GetMapping("/mvc/posts/search")
    public String searchPosts(@RequestParam(name = "id", required = false) Long id,
                              @RequestParam(name = "date", required = false) String date,
                              Model model) {
        try {
            // 검색 결과 가져오기
            List<Post> posts = postRepository.findPostSearch(id, date);

            // 검색 결과가 있는 경우
            if (!posts.isEmpty()) {
                model.addAttribute("posts", posts);
                return "post-form"; // 검색 결과를 보여주는 페이지
            } else {
                model.addAttribute("errorMessage", "검색 결과를 찾을 수 없습니다.");
                return "error-page"; // 검색 결과가 없을 때
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", "오류가 발생했습니다: " + e.getMessage());
            return "error-page"; // 예외 발생 시 에러 페이지로 이동
        }
    }



    @GetMapping("/mvc/posts/edit")
    public String editPost(@RequestParam Long id, Model model) {
        // 데이터베이스에서 해당 ID의 데이터 가져오기
        Optional<Post> postOptional = postRepository.findPostUserById(id);

        if (postOptional.isPresent()) {
            model.addAttribute("post", postOptional.get());
            return "post-edit"; // post-edit.jsp로 이동
        } else {
            model.addAttribute("errorMessage", "수정할 데이터를 찾을 수 없습니다.");
            return "error-page"; // 에러 페이지로 이동
        }
    }

    @PostMapping("/mvc/posts/update")
    public String updatePost(@ModelAttribute Post post, Model model) {
        try {
            // userId를 post의 id로 설정
            post.setUserId(post.getId());

            // 사용자 정보 수정
            postRepository.updatePostUser(post);

            // 작업 정보 수정
            postRepository.upsertPostWork(post);

            return "redirect:/mvc/posts/list"; // 수정 완료 후 목록 페이지로 이동
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "수정 중 오류가 발생했습니다: " + e.getMessage());
            return "error-page"; // 에러 페이지로 이동
        }
    }



    @GetMapping("/mvc/posts/delete")
    public String deletePost(@RequestParam Long id, Model model) {
        try {
            postRepository.deletePostWorkByUserId(id); // 작업 데이터 삭제
            postRepository.deletePostUserById(id); // 사용자 데이터 삭제
            return "redirect:/mvc/posts/list"; // 목록 페이지로 이동
        } catch (Exception e) {
            model.addAttribute("errorMessage", "삭제 중 오류가 발생했습니다: " + e.getMessage());
            return "error-page";
        }
    }



}
