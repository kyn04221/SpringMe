package com.busanit501.helloword2.jdbc.service;

import com.busanit501.helloword2.jdbc.dao.MemberDAO;

import com.busanit501.helloword2.jdbc.dto.MemberDTO;
import com.busanit501.helloword2.jdbc.dto.MemberVO;
import com.busanit501.helloword2.jdbc.util.MapperUtil;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

import java.sql.SQLException;


    @Log4j2
    public enum MemberService {
        //crud
        INSTANCE;

        private MemberDAO memberDAO;
        private ModelMapper modelMapper;

        MemberService(){
            memberDAO = new MemberDAO();
            modelMapper = MapperUtil.INSTANCE.get();
        }

        // 로그인 확인용.
        public MemberDTO login(String mid, String mpw) throws SQLException {
            MemberVO memberVO = memberDAO.getMemberWithpw(mid,mpw);
            MemberDTO memberDTO = modelMapper.map(memberVO, MemberDTO.class);
            return memberDTO;
        }

        public void updateUuid(String mid, String uuid) throws SQLException {
            memberDAO.updateUuid(mid,uuid);
        }

        public MemberDTO getMemberWithUuidService(String uuid) throws SQLException {
            MemberVO memberVO= memberDAO.getMemberWithUuid(uuid);
            MemberDTO memberDTO = modelMapper.map(memberVO, MemberDTO.class);
            return memberDTO;
        }


    }
