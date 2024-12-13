package mapper;

import com.busanit501.minitest.domain.FoodVO;
import com.busanit501.minitest.dto.FoodDTO;
import com.busanit501.minitest.dto.PageRequestDTO;
import com.busanit501.minitest.mapper.FoodMapper;

import com.busanit501.minitest.service.FoodService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

@Log4j2
@ExtendWith(SpringExtension.class) //JUnit5 테스트 설정.
//JUnit4 테스트 설정. @Runwith
// 설정 파일의 경로를 지정.
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class FoodMapperTest {
    // 해당 인스턴스가 없다면, 널로 받을게.
    // 기본값은 required = true
    @Autowired(required = false)
    private FoodMapper foodMapper;


    @Autowired
    private FoodService foodService;

    
    @Test
    public void testGetTime() {

        log.info("getTime : " + foodMapper.getTime());
    }

    @Test
    public void testInsert() {
        FoodVO foodVO = FoodVO.builder()
                .menu("샘플 테스트")
                .price("20000원")
                .build();
    }

    @Test
    public void testSelectAll() {
        List<FoodVO> lists = foodMapper.selectAll();
        for (FoodVO foodVO:lists) {
            log.info("foodVo : " + foodVO);
        }
    }

    @Test
    public void testGetAll() {
        List<FoodDTO> list = foodService.getAll();
        for (FoodDTO foodDTO:list) {
            log.info("foodDTO : " + foodDTO);
        }
    }

    @Test
    public void testSelectOne() {
        FoodVO  foodVO = foodMapper.selectOne(9L);
        log.info("foodVO : " + foodVO);
    }

    @Test
    public void testDelete() {
        foodMapper.delete(14L);
    }

    @Test
    public void testUpdate() {
        // 업데이트 할 더미 데이터 필요, TodoVO
        FoodVO foodVO = FoodVO.builder()
                .tno(2L)
                .menu("샘플 rkskek")
                .price("20000원")
                .build();

        foodMapper.update(foodVO);
    }

    // 페이징 처리해서 전체 조회
    @Test
    public void testSelectAllWithPage() {
        // 페이징 준비물을 담은 PageRequestDTO 필요함,
        // 더미로 PageRequestDTO 만들고,
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(2)
                .size(10)
                .build();

        List<FoodVO> list = foodMapper.selectList(pageRequestDTO);
        list.forEach(vo -> log.info("vo : " + vo));
    }

    // 페이징 처리해서 전체 갯수 조회
    @Test
    public void testGetCount() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(2)
                .size(10)
                .build();
        int total = foodMapper.getCount(pageRequestDTO);
        log.info("total : " + total);
    }
}