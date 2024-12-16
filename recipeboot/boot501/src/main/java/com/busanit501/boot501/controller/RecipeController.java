package com.busanit501.boot501.controller;

import com.busanit501.boot501.dto.PageRequestDTO;
import com.busanit501.boot501.dto.PageResponseDTO;
import com.busanit501.boot501.dto.RecipeDTO;
import com.busanit501.boot501.service.RecipeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j2
@RequestMapping("/recipe")
@RequiredArgsConstructor

public class RecipeController {
    private final RecipeService recipeService;

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model ) {
        PageResponseDTO<RecipeDTO> responseDTO = recipeService.list(pageRequestDTO);
        log.info(responseDTO);
        model.addAttribute("responseDTO", responseDTO);
    }

    @GetMapping("/register")
    public void register() {

    }
    @PostMapping("/register")
    public String registerPost(@Valid RecipeDTO recipeDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
//        log.info("recipeController register post 로직처리: ");
//        log.info("recipeController register post  recipeDTO : " + recipeDTO);

        if (bindingResult.hasErrors()) {
            log.info("has errors : 유효성 에러가 발생함.");
            // 1회용으로, 웹 브라우저에서, errors , 키로 조회 가능함. -> 뷰 ${errors}
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/recipe/register";
        }
        Long recipeid = recipeService.register(recipeDTO);


        redirectAttributes.addFlashAttribute("result", recipeid);
        redirectAttributes.addFlashAttribute("resultType", "register");

        return "redirect:/recipe/list";

    }

    @GetMapping("/read")
    public void read(Long bno, PageRequestDTO pageRequestDTO,
                     Model model) {
        RecipeDTO recipeDTO = recipeService.readOne(bno);
        model.addAttribute("dto", recipeDTO);
    }

    @GetMapping("/update")
    public void update(Long bno, PageRequestDTO pageRequestDTO,
                       Model model) {
        RecipeDTO recipeDTO = recipeService.readOne(bno);
        model.addAttribute("dto", recipeDTO);
    }
}
