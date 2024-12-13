package com.busanit501.springex.controller;

import com.busanit501.springex.dto.PageRequestDTO;
import com.busanit501.springex.dto.PageResponseDTO;
import com.busanit501.springex.dto.RecipeDTO;
import com.busanit501.springex.service.RecipeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/recipe")
@Log4j2
@RequiredArgsConstructor
public class RecipeController {


    private final RecipeService recipeService;

    @RequestMapping("/list")
    public String list(@Valid PageRequestDTO pageRequestDTO,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes,
                       Model model) {

        if (bindingResult.hasErrors()) {
            log.info("has errors : 유효성 에러가 발생함.");

            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/recipe/list";
        }

        PageResponseDTO<RecipeDTO> pageResponseDTO = recipeService.selectList(pageRequestDTO);

        model.addAttribute("pageResponseDTO", pageResponseDTO);

        redirectAttributes.addAttribute("page",pageRequestDTO.getPage());
        redirectAttributes.addAttribute("size",pageRequestDTO.getSize());
        return "/recipe/list";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public void register() {
        log.info("recipeController register : 화면제공은 해당 메서드 명으로 제공함.");
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerPost(@Valid RecipeDTO recipeDTO, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            log.info("has errors : 유효성 에러가 발생함.");

            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/recipe/register";
        }
        recipeService.register(recipeDTO);
        return "redirect:/recipe/list";
    }

    @RequestMapping("/read")
    public String read(Long recipe_id, @Valid PageRequestDTO pageRequestDTO,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes,
                       Model model) {

        if (bindingResult.hasErrors()) {
            log.info("has errors : 유효성 에러가 발생함.");

            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("recipe_id", recipe_id);
            return "redirect:/recipe/read";
        }
        RecipeDTO recipeDTO = recipeService.getOne(recipe_id);

        model.addAttribute("recipeDTO", recipeDTO);
        redirectAttributes.addAttribute("page",pageRequestDTO.getPage());
        redirectAttributes.addAttribute("size",pageRequestDTO.getSize());
        return "/recipe/read";
    }



    @RequestMapping("/update")
    public String update(Long recipe_id, @Valid PageRequestDTO pageRequestDTO, BindingResult bindingResult,
                         RedirectAttributes redirectAttributes, Model model) {
        log.info("recipeController update :");
        if (bindingResult.hasErrors()) {
            log.info("has errors : 유효성 에러가 발생함.");

            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("recipe_id", recipe_id);

            return "redirect:/recipe/update";
        }
        RecipeDTO recipeDTO = recipeService.getOne(recipe_id);

        model.addAttribute("recipeDTO", recipeDTO);
        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
        redirectAttributes.addAttribute("size", pageRequestDTO.getSize());
        return "/recipe/update";

    }


    @PostMapping("/update")
    public String updateLogic(@Valid RecipeDTO recipeDTO, BindingResult bindingResult,
                              @Valid PageRequestDTO pageRequestDTO,
                              BindingResult pageBindingResult,
                              RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            log.info("has errors : 유효성 에러가 발생함. 검사 대상 :recipeDTO ");

            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("recipe_id", recipeDTO.getRecipe_id());
            redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
            redirectAttributes.addAttribute("size", pageRequestDTO.getSize());
            return "redirect:/recipe/update"+pageRequestDTO.getLink();
        }

        if (pageBindingResult.hasErrors()) {
            log.info("has errors : 유효성 에러가 발생함. 검사 대상 :PageRequestDTO ");

            redirectAttributes.addFlashAttribute("errors2", pageBindingResult.getAllErrors());

            redirectAttributes.addAttribute("recipe_id", recipeDTO.getRecipe_id());
            redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
            redirectAttributes.addAttribute("size", pageRequestDTO.getSize());
            return "redirect:/recipe/update"+pageRequestDTO.getLink();
        }

        recipeService.update(recipeDTO);

        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
        redirectAttributes.addAttribute("size", pageRequestDTO.getSize());
        return "redirect:/recipe/list?"+pageRequestDTO.getLink();
    }


    @PostMapping("/delete")
    public String delete(Long recipe_id, PageRequestDTO pageRequestDTO,
                         RedirectAttributes redirectAttributes
    ) {
        recipeService.delete(recipe_id);
        return "redirect:/recipe/list?"+pageRequestDTO.getLink();
    }
}








