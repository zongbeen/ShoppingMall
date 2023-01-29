package com.shoppingMall.repository;

import com.shoppingMall.constant.ItemSellStatus;
import com.shoppingMall.entity.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest //Test - Bean을 IoC컨테이너에 등록
//application.properties에 설정해둔 값보다
// application-test.properties에 같은 설정이 있다면 더 높은 우선순위 부여
@TestPropertySource(locations = "classpath:application-test.properties")
class ItemRepositoryTest {
    @Autowired
    ItemRepository itemRepository; //Autowired 이용하여 Bean을 주입하고 ItemRepository를 사용

    @Test //테스트 대상 지정
    @DisplayName("상품 저장 테스트") //테스트명 노출
    public void createItemTest(){
        Item item = new Item();
        item.setItemName("테스트상품");
        item.setPrice(10000);
        item.setItemDetail("테스트 상품 설명");
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setStockNum(100);
        item.setRegTime(LocalDateTime.now());
        item.setUpdateTime(LocalDateTime.now());
        Item savedItem = itemRepository.save(item);
        System.out.println(savedItem.toString());
    }

    public void createItemList() { //데이터 생성을 위해 상품을 저장하는 메서드
        for(int i=1; i<=10; i++) {
            Item item = new Item();
            item.setItemName("테스트 상품" + i);
            item.setPrice(10000 + i);
            item.setItemDetail("테스트 상품 상세 설명" + i);
            item.setItemSellStatus(ItemSellStatus.SELL);
            item.setStockNum(100);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            Item savedItem = itemRepository.save(item);
        }
    }

    @Test //테스트 대상 지정
    @DisplayName("상품명 조회 테스트") //테스트명 노출
    public void findByItemNameTest(){
        this.createItemList();
        // itemRepository 인터페이스에서 작성한 메서드 호출
        List<Item> itemList = itemRepository.findByItemName("테스트상품1");
        for(Item item : itemList) {
            System.out.println(item.toString());
        }
    }

    @Test //테스트 대상 지정
    @DisplayName("상품명, 상세설명 조회 테스트")
    public void findByItemNameOrItemDetailTest(){
        this.createItemList();
        List<Item> itemList = itemRepository.findByItemNameOrItemDetail("테스트상품1", "상세설명5");
        for(Item item : itemList) {
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("가격 LessThan 테스트")
    public void findByPriceLessThanTest() {
        this.createItemList();
        List<Item> itemList = itemRepository.findByPriceLessThan(10005);
        for(Item item : itemList) {
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("가격 내림차순 조회 테스트")
    public void findByPriceLessThanOrderByPriceDesc() {
        this.createItemList();
        List<Item> itemList = itemRepository.findByPriceLessThanOrderByPriceDesc(10009);
        for(Item item : itemList) {
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("@Query를 이용한 상품 조회 테스트")
    public void findByItemDetailTest() {
        this.createItemList();
        List<Item> itemList = itemRepository.findByItemDetail("테스트 상품 설명");
        for(Item item : itemList) {
            System.out.println(item.toString());
        }
    }

//    @Test
//    @DisplayName("nativeQuery 이용한 상품 조회 테스트")
//    public void findByItemDetailByNative() {
//        this.createItemList();
//        List<Item> itemList = itemRepository.findByItemDetailByNative("테스트 상품 설명");
//        for(Item item : itemList) {
//            System.out.println(item.toString());
//        }
//    }

}