package ng.koboflow.common.money;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

public class FloatingPointPitfallsTest {
    @Test
    void doublesCannotRepresentMostDecimalAmounts() {
        double balance = 0.0;
        for (int i = 0; i < 10; i++) {
            balance += 0.10; // add 10 kobo
        }

        assertThat(balance).isNotEqualTo(1.0); // balance is 0.9999999999999999
        assertThat(balance).isEqualTo(0.9999999999999999);
        assertThat(0.1 + 0.2).isEqualTo(0.30000000000000004);
    }

    @Test
    void longKoboIsExact() {
        long balance = 0L;
        for (int i = 0; i < 10; i++) {
            balance += 10L; // add 10 kobo
        }

        assertThat(balance).isEqualTo(100L);
    } 

    @Test
    void bigDecimalContructorKeepsTheBinaryError() {
        assertThat(new BigDecimal(0.1).toPlainString())
        .startsWith("0.1000000000000000055511151231257827");

        assertThat(new BigDecimal("0.1")).isEqualTo(BigDecimal.valueOf(0.1));
    }

    @Test
    void bigDecimalEqualsIsNotTheSameAsCompareTo() {
        BigDecimal a = new BigDecimal("5000.0");
        BigDecimal b = new BigDecimal("5000.00");

        assertThat(a).isNotEqualTo(b);
        // assertThat(a).isEqualTo(b); // fails
        assertThat(a).isEqualByComparingTo(b);
    }
}
