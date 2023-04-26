package com.example.companymanagement_backend.util;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

/**
 * writer : 이호진
 * init : 2023.04.26
 * updated by writer :
 * update :
 * description : BigDecimal calculating Test
 */
@Slf4j
public class BigDecimalTest {

    /**
     * writer : 이호진
     * init : 2023.04.26
     * updated by writer :
     * update :
     * description : BigDecimal calculating Test
     *
     * comment : BigDecimal을 double로 바꾸니 소수점 13까지 나온다
     *           -> String.format("%.3f", number)를 써서 해결
     */
    @Test
    @DisplayName(value = "BigDecimal의 사칙연산 계산 테스트")
    void calculateBigDecimalTest() {
        // given
        Integer a = 20;// 인상 비율
        // salary 정하기
        BigDecimal salary = new BigDecimal("1000.10");
        // when
        BigDecimal newSalary = increaseSalary(salary, a);
        // then
//        BigDecimal result = new BigDecimal("1200.120");
//        BigDecimal result = BigDecimal.valueOf(1000.10 + 1000.10 * 0.2);
        BigDecimal result = new BigDecimal(String.format("%.3f", 1000.10 + 1000.10 * 0.2));
        Assertions.assertThat(newSalary).isEqualTo(result);
    }

    private BigDecimal increaseSalary(BigDecimal salary, Integer increasedPct) {

        double increasedRate = searchIncreasedRate(increasedPct);// 인상된 비율
        // BigDecimal로 변환
        BigDecimal increasedRateBigDecimal = BigDecimal.valueOf(increasedRate);
        // 인상된 비율로 인상된 봉급 찾기
        BigDecimal increasedMoney = salary.multiply(increasedRateBigDecimal);
        // 인상된 금액 더하기
        return salary = salary.add(increasedMoney);
    }
    // double로 교체
    private double searchIncreasedRate(Integer increasedPct) {
        // %를 소수점으로 변경
        double increasedRate = increasedPct / 100.0;

        return increasedRate;
    }
}
