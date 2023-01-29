package com.shoppingMall.entity;

import com.shoppingMall.constant.ItemSellStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDateTime;

//item클래스를 entity로 선언
@Entity
//어떤 테이블과 매칭될지 지정
@Table(name = "item")
//lombok-Getter, Setter, toString
@Getter
@Setter
@ToString
public class Item {
    //기본키 어노테이션
    @Id
    //매핑될 컬럼의 이름
    @Column(name = "item_id")
    //JPA가 자동으로 생성 전략 결정
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; //상품 코드
    //nullable=false == not null
    @Column(nullable = false, length = 50)
    private String itemName; //상품명
    @Column(name = "price", nullable = false)
    private  int price; //가격
    @Column(nullable = false)
    private int stockNum; //재고
    @Lob
    @Column(nullable = false)
    private String itemDetail; //상품 설명
    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus; //상품 판매 상태
    private LocalDateTime regTime; //등록 시간
    private LocalDateTime updateTime; //수정 시간
}
