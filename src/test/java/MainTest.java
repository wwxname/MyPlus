import com.fasterxml.jackson.core.JsonProcessingException;

import java.time.LocalDateTime;

/**
 * @author plus me
 * @date 2018/9/10
 */
public class MainTest {

    public static void main(String args[]) throws JsonProcessingException {
        LocalDateTime localDateTime = LocalDateTime.now();
        int y = localDateTime.toLocalDate().getYear();
        int m = localDateTime.getMonthValue();
        int d = localDateTime.getDayOfMonth();
        System.err.print(y + "年" + m + "月" + d + "号");
        System.err.print("-");
        System.err.println(y + "年" + m + "月" + d + "号");

        String s = localDateTime.toLocalDate().toString() + "至" + localDateTime.toLocalDate().toString();
        System.err.println(s);

    }
}
