package com.busanit501.springex.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.Arrays;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageRequestDTO {

    @Builder.Default
    @Min(value = 1)
    @Positive
    private int page = 1;

    @Builder.Default
    @Min(value = 10)
    @Max(value = 100)
    @Positive
    private int size = 10;
    private String link;
    private String keyword;
    private String[] types;
    private LocalDate from;
    private LocalDate to;

    public int getSkip() {
        return (page-1)*size;
    }



    public String getLink() {
        StringBuilder builder = new StringBuilder();
        builder.append("page=" + this.page);
        builder.append("&size=" + this.size);


        if(keyword != null) {
            try {
                builder.append("&keyword="+ URLEncoder.encode(keyword, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        if (types != null && types.length > 0) {
            for (int j = 0; j < types.length; j++) {
                builder.append("&types=").append(types[j]);
            }
        }

        if(from != null){
            builder.append("&from="+ from.toString());
        }

        if(to != null){
            builder.append("&to="+ to.toString());
        }

        return builder.toString();
    }

    public boolean checkType(String type) {
        if (types == null || types.length == 0) {
            return false;
        }

        return Arrays.stream(types).anyMatch(type::equals);
    }
}
