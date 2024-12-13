package serviceTest;


import com.busanit501.minitest.dto.FoodDTO;
import com.busanit501.minitest.dto.PageRequestDTO;
import com.busanit501.minitest.dto.PageResponseDTO;
import com.busanit501.minitest.service.FoodService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@Log4j2
@ExtendWith(SpringExtension.class) //JUnit5 테스트 설정.
//JUnit4 테스트 설정. @Runwith
// 설정 파일의 경로를 지정.
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
//방법2
//@RequiredArgsConstructor
public class FoodServiceTest {
    // 방법1
    @Autowired
    private FoodService foodService;
    //방법2
//    private final Service todoService;
    @Test
    public void testRegister() {
        FoodDTO foodDTO = FoodDTO.builder()
                .menu("샘플데이터 서비스에서 입력")
                .price("50000원")
                .build();

        foodService.register(foodDTO);
    }

    @Test
    public void testGetOne() {
        FoodDTO foodDTO = foodService.getOne(2L);
        log.info("foodDTO : " + foodDTO);
    } //

    @Test
    public void testDelete() {
        foodService.delete(16L);
    } //

    @Test
    public void testUpdate() {
        // 업데이트 할 더미 데이터 필요, TodoVO
        FoodDTO foodDTO = FoodDTO.builder()
                .tno(5L)
                .menu("수정 제목")
                .price("수정 제목")
                .build();
        foodService.update(foodDTO);
    }


    @Test
    public void testPageList() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(180)
                .size(10)
                .build();

        PageResponseDTO<FoodDTO> list = foodService.getListWithPage(pageRequestDTO);
        list.getDtoList().stream().forEach(dto -> log.info("dto : " + dto));
        log.info("list total : " + list.getTotal());
        log.info("list prev : " + list.isPrev());
        log.info("list next : " + list.isNext());
        log.info("list start : " + list.getStart());
        log.info("list end : " + list.getEnd());

    }
}//

