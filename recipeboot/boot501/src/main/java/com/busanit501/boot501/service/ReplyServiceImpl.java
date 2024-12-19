package com.busanit501.boot501.service;

import com.busanit501.boot501.domain.RReply;
import com.busanit501.boot501.domain.Recipe;
import com.busanit501.boot501.dto.PageRequestDTO;
import com.busanit501.boot501.dto.PageResponseDTO;
import com.busanit501.boot501.dto.RReplyDTO;
import com.busanit501.boot501.repository.RecipeRepository;
import com.busanit501.boot501.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;

    private final RecipeRepository recipeRepository;

    private final ModelMapper modelMapper;

    @Override
    public Long register(RReplyDTO replyDTO) {
        // 화면에서 받은 데이터 DTO 타입 -> 엔티티 타입으로 변경,
        log.info("Registering new replyDTO: " + replyDTO);
        RReply reply = modelMapper.map(replyDTO, RReply.class);
        Optional<Recipe> result = recipeRepository.findById(replyDTO.getRecipeid());
        Recipe recipe = result.orElseThrow();
        reply.changeRecipe(recipe);
        log.info("Registering new reply: " + reply);
        Long rno = replyRepository.save(reply).getRno();
        return rno;
    }
    @Override
    public RReplyDTO readOne(Long rno) {
        Optional<RReply> result = replyRepository.findById(rno);
        RReply reply = result.orElseThrow();
        RReplyDTO replyDTO = modelMapper.map(reply, RReplyDTO.class);
        return replyDTO;
    }

    @Override
    public void update(RReplyDTO replyDTO) {
        Optional<RReply> result = replyRepository.findById(replyDTO.getRno());
        RReply reply = result.orElseThrow();
//        reply.changeReplyText(replyDTO.getReplyText());
        reply.changeReplyTextReplyer(replyDTO.getReplyText(),replyDTO.getReplyer());
        replyRepository.save(reply);
    }

    @Override
    public void delete(Long rno) {
        replyRepository.deleteById(rno);
    }

    @Override
    public PageResponseDTO<RReplyDTO> listWithReply(Long recipeid, PageRequestDTO pageRequestDTO) {
        Pageable pageable = PageRequest.of(pageRequestDTO.getPage()-1 <= 0 ? 0:pageRequestDTO.getPage()-1,
                pageRequestDTO.getSize(), Sort.by("rno").ascending());
        Page<RReply> result = replyRepository.listOfRecipe(recipeid, pageable);
        List<RReplyDTO> dtoList = result.getContent().stream()
                .map(reply -> {
                    RReplyDTO replyDTO = modelMapper.map(reply, RReplyDTO.class);
                    replyDTO.setRno(reply.getRno());
                    return replyDTO;
                })
                .collect(Collectors.toList());
        PageResponseDTO<RReplyDTO> pageResponseDTO = PageResponseDTO.<RReplyDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
        return pageResponseDTO;
    }
}