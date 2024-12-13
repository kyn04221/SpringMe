package com.busanit501.helloword2.food.service;

import com.busanit501.helloword2.food.dao.FoodMemberDAO;
import com.busanit501.helloword2.food.dto.FoodMemberDTO;
import com.busanit501.helloword2.food.dto.FoodMemberVO;
import com.busanit501.helloword2.food.util.FoodMapperUtil;
import com.busanit501.helloword2.jdbc.dto.MemberDTO;
import com.busanit501.helloword2.jdbc.dto.MemberVO;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

import java.sql.SQLException;


@Log4j2
public enum FoodMemberService {
    //crud
    INSTANCE;

    private FoodMemberDAO fmdao;
    private ModelMapper modelMapper;

    FoodMemberService(){
        fmdao = new FoodMemberDAO();
        modelMapper = FoodMapperUtil.INSTANCE.get();
    }

    // vo > dto
    public FoodMemberDTO login(String mid, String mpw) throws SQLException {
        FoodMemberVO fmVO = fmdao.getMemberWithpw(mid,mpw);
        FoodMemberDTO fmDTO = modelMapper.map(fmVO, FoodMemberDTO.class);
        return fmDTO;
    }
    public void updateUuid(String mid, String uuid) throws SQLException {
        fmdao.updateUuid(mid,uuid);
    }

    public FoodMemberDTO getMemberWithUuidService(String uuid) throws SQLException {
        FoodMemberVO memberVO= fmdao.getMemberWithUuid(uuid);
        FoodMemberDTO fmDTO = modelMapper.map(memberVO, FoodMemberDTO.class);
        return fmDTO;
    }
}
