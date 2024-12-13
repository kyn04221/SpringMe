package com.busanit501.minitest.controller;

import com.busanit501.minitest.dto.FoodDTO;
import com.busanit501.minitest.dto.PageRequestDTO;
import com.busanit501.minitest.dto.PageResponseDTO;
import com.busanit501.minitest.service.FoodService;
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
import java.util.List;

@Controller
@RequestMapping("/food")
@Log4j2
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;


    @RequestMapping("/FoodList")
    public void list(@Valid PageRequestDTO pageRequestDTO ,
                     BindingResult bindingResult,
                     Model model) {
        log.info("FoodController list : 화면제공은 해당 메서드 명으로 제공함.");
        if(bindingResult.hasErrors()) {
            pageRequestDTO = PageRequestDTO.builder().build();
        }
        PageResponseDTO<FoodDTO> pageResponseDTO = foodService.getListWithPage(pageRequestDTO);
        log.info("FoodController list 데이터 유무 확인 :" + pageResponseDTO);
        //데이터 탑재. 서버 -> 웹
        model.addAttribute("pageResponseDTO", pageResponseDTO);

    }

    @RequestMapping(value = "/foodRegister", method = RequestMethod.GET)
    public void register() {
        log.info("FoodController register : 화면제공은 해당 메서드 명으로 제공함.");
    }

    @RequestMapping(value = "/foodRegister", method = RequestMethod.POST)
    public String registerPost(@Valid FoodDTO foodDTO, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        log.info("FoodController register post 로직처리: ");
        log.info("FoodController register post  FoodDTO : " + foodDTO);


        if (bindingResult.hasErrors()) {
            log.info("has errors : 유효성 에러가 발생함.");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/food/foodRegister";
        }
        foodService.register(foodDTO);


        return "redirect:/food/FoodList";
    }

    @RequestMapping("/foodread") // 리드.업데이트 로직은 똒같음
    public void read(Long tno, @Valid PageRequestDTO pageRequestDTO,
                     Model model) {
        log.info("FoodController read :");
        FoodDTO foodDTO = foodService.getOne(tno);
        log.info("FoodController read 데이터 유무 확인 :" + foodDTO);
        model.addAttribute("foodDTO", foodDTO);

    }

    // 수정 1) 폼 2) 로직 처리
    @RequestMapping("/foodupdate")
    public void update(Long tno,@Valid PageRequestDTO pageRequestDTO, Model model) {
        log.info("foodController update :");
        FoodDTO foodDTO = foodService.getOne(tno);
        log.info("foodController update 데이터 유무 확인 :" + foodDTO);
        //데이터 탑재. 서버 -> 웹
        model.addAttribute("foodDTO", foodDTO);

    }

    //수정 로직 처리
    @PostMapping("/foodupdate")
    // 수정할 항목을 모두 받아서, TodoDTO 담습니다. 여기에 tno 도 포함 시키기
    public String updateLogic(@Valid FoodDTO foodDTO, BindingResult bindingResult, PageRequestDTO pageRequestDTO,
                              RedirectAttributes redirectAttributes) {

        // 유효성 체크 -> 유효성 검증시, 통과 안된 원인이 있다면,
        if (bindingResult.hasErrors()) {
            log.info("has errors : 유효성 에러가 발생함.");
            // 1회용으로, 웹 브라우저에서, errors , 키로 조회 가능함. -> 뷰 ${errors}
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("tno",foodDTO.getTno());
            redirectAttributes.addAttribute("page",pageRequestDTO.getPage());
            redirectAttributes.addAttribute("size",pageRequestDTO.getSize());
            return "redirect:/food/foodupdate";
        }

        // 수정하는 로직 필요함.
        // 주의사항, 체크박스의 값의 문자열 on 전달 받습니다.
        log.info("foodDTO확인 finished의 변환 여부 확인. : " + foodDTO);

        foodService.update(foodDTO);
        redirectAttributes.addAttribute("page",pageRequestDTO.getPage());
        redirectAttributes.addAttribute("size",pageRequestDTO.getSize());
        return "redirect:/food/FoodList";
    }

    @PostMapping("/fooddelete")
    public String delete(Long tno, PageRequestDTO pageRequestDTO,
                         RedirectAttributes redirectAttributes) {
        foodService.delete(tno);
        redirectAttributes.addAttribute("page",pageRequestDTO.getPage());
        redirectAttributes.addAttribute("size",pageRequestDTO.getSize());
        return "redirect:/food/FoodList";
    }

}