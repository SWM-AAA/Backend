package com.aaa.jeppy.common.entity;
// jdk8 이후부터 사용된 패키지로 날짜, 시간, 날짜 시간을 다루는 클래스 사용중인건 날짜 시간을 다룸
// https://java119.tistory.com/52
import java.time.LocalDateTime;

// hibernate에서 제공하는 기능으로 쿼리 발생시 현재 시간을 저장해주는 annotation
// import org.hibernate.annotations.CreationTimestamp;
// spring에서 제공해준다는 점만 다를 뿐 위의 기능과 다를바가 없다. 원하는 것을 사용하면 된다.
// 하지만 hibernate annotation을 사용하지 않는 추세이므로 CreatedData를 사용할 것이다.
// https://www.inflearn.com/questions/258989/creationtimestamp%EC%99%80-createddate%EC%9D%98-%EC%84%A0%ED%83%9D
import org.springframework.data.annotation.CreatedDate;
import jakarta.persistence.Column;

public abstract class BaseModel {
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime CreatedDate;
    
}
