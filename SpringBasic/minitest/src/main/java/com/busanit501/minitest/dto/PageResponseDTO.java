package com.busanit501.minitest.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.util.List;


@Getter
@ToString
public class PageResponseDTO<E> {

    private int page;
    private int size; // 페이징당 보여줄 갯수
    private int total;

    private int start;
    private int end;

    // 이전페이지 존재여부
    private boolean prev;
    // 다음페이지 존재여부
    private boolean next;


    private List<E> dtoList;


    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(List<E> dtoList, int total,
                           PageRequestDTO pageRequestDTO) {
        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getSize();
        this.total = total;
        this.dtoList = dtoList;

        // start, end, prev,next 초기화가 필요함.
        this.end = ((int) Math.ceil(page / 10.0)) * 10;
        this.start = this.end - 9;
        // last
        // total : 75개, 화면에 페이지 번호를 10개씩 출력,
        // last : 8
        // end : 10
        // 10 > 8 ? last(8) : end(10),
        // 결론, end = 8

        //예시2)
        // total : 123개, 화면에 페이지 번호를 10개씩 출력,
        // last : 13
        // end :  현재  page 1, -> end = 10
        // 10 > 13 ? last(13) : end(10) ,
        // 결론, end = 10
        int last = (int)(Math.ceil(total/10.0));
        this.end = end > last ? last :end;

        this.prev = this.start > 1;
        //예시, total = 123, end 10, size 10
        //예시2, total = 85, end 10, size 10
        this.next = total > this.end * this.size;

    }





}
