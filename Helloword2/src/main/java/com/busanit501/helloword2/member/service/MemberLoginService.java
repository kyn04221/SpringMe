package com.busanit501.helloword2.member.service;

import com.busanit501.helloword2.member.dao.MemberLoginDAO;
import com.busanit501.helloword2.member.dto.MemberDTO;
import com.busanit501.helloword2.member.dto.MemberVO;
import com.busanit501.helloword2.member.util.MemberMapperUtil;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

import java.sql.SQLException;


    @Log4j2
    public enum MemberLoginService {
        //crud
        INSTANCE;

        private MemberLoginDAO memberDAO;
        private ModelMapper modelMapper;

        MemberLoginService(){
            memberDAO = new MemberLoginDAO();
            modelMapper = MemberMapperUtil.INSTANCE.get();
        }

        public MemberDTO login(String mid, String mpw) throws SQLException {
            MemberVO memberVO = memberDAO.getMemberWithpw(mid,mpw);
            MemberDTO memberDTO = modelMapper.map(memberVO, MemberDTO.class);
            return memberDTO;
        }


    }
