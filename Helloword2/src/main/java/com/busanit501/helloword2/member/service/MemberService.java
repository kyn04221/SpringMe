package com.busanit501.helloword2.member.service;

import com.busanit501.helloword2.member.dao.MemberDAO;
import com.busanit501.helloword2.member.dto.MemberDTO;
import com.busanit501.helloword2.member.dto.MemberVO;
import com.busanit501.helloword2.member.util.MemberMapperUtil;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public enum MemberService {
    //crud
    INSTANCE;

    private MemberDAO dao;
    private ModelMapper modelMapper;

    MemberService(){
        dao = new MemberDAO();
        modelMapper = MemberMapperUtil.INSTANCE.get();
    }

    //dto > vo
    public void register (MemberDTO dto) throws Exception {

        MemberVO vo = modelMapper.map(dto, MemberVO.class);
        //기존 출력방식
        // System.out.println("todoVo : "+ todoVO);

        log.info("MemberVO : "+ vo);
        // DAO 외주 맡기기,
        dao.insert(vo);
    }
    // vo > dto
    public List<MemberDTO> listAll() throws SQLException {
        List<MemberVO> voList = dao.selectAll();
        log.info("voList : "+ voList);

        List<MemberDTO> dtoList = voList.stream()
                .map(vo -> modelMapper.map(vo, MemberDTO.class))
                .collect(Collectors.toList());
        return dtoList;
    }

    public MemberDTO get(Long tmo) throws SQLException {
        log.info("tmo : " + tmo);
        ///  디비에서 하나 조회 결과 받았음.
        MemberVO VO = dao.selectOne(tmo);
        // VO -> DTO 변환 작업.
        MemberDTO memberDTO = modelMapper.map(VO, MemberDTO.class);
        return memberDTO;

    }

    //4 수정 기능
    public void update(MemberDTO dto) throws SQLException {
        // 화면에서 넘겨 받은 데이터는 TodoDTO 타입 박스에 담겨서 오고,
        // DAO 계층에서 박스의 타입 TodoVO 사용하니, 변환 작업 필요함.
        // 항상 데이터가 전달 유무 확인.
        log.info("MemberDTO : " + dto);
        MemberVO VO = modelMapper.map(dto, MemberVO.class);
        dao.updateOne(VO);

    }

    //5 삭제 기능.
    public void delete(Long mno) throws SQLException {
        dao.deletemember(mno);
    }

}
