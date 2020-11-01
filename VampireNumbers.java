import java.util.ArrayList;
import java.util.List;

public class VampireNumbers {
    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        long min = 1;
        long max = 999999;
        if (args.length == 2) {
            min = Long.valueOf(args[0]);
            max = Long.valueOf(args[1]);
        }
        for (long i = min; i <= max; i++) {
            String iStr = String.valueOf(i);
            long iLen = iStr.length();
            if (iLen % 2 != 0) {
                continue;
            }
            double sqrt = Math.sqrt(i);
            for (long fang1 = 1; fang1 <= sqrt; fang1++) {
                long fang2 = i / fang1;
                if (fang1 * fang2 != i) {
                    continue;
                }
                String fang1Str = String.valueOf(fang1);
                String fang2Str = String.valueOf(fang2);
                int fang1Len = fang1Str.length();
                int fang2Len = fang2Str.length();
                if (fang1Len != fang2Len ||
                        iLen % fang1Len != 0 ||
                        iLen / fang1Len != 2 ||
                        iLen % fang2Len != 0 ||
                        iLen / fang2Len != 2 ||
                        (fang1Str.charAt(fang1Len - 1) == '0' && fang2Str.charAt(fang2Len - 1) == '0')
                ) {
                    continue;
                }
                // collect digits of number
                List<Character> digits = new ArrayList<>();
                for (int c = 0; c < iStr.length(); c++) {
                    Character d = iStr.charAt(c);
                    digits.add(d);
                }
                // check if digits of fangs exist in vampire number
                boolean breakLoop = false;
                for (int c = 0; c < fang1Str.length(); c++) {
                    Character d = fang1Str.charAt(c);
                    if (digits.contains(d)) {
                        digits.remove(d);
                    } else {
                        breakLoop = true;
                        break;
                    }
                }
                if (breakLoop) {
                    break;
                }
                for (int c = 0; c < fang2Str.length(); c++) {
                    Character d = fang2Str.charAt(c);
                    if (digits.contains(d)) {
                        digits.remove(d);
                    } else {
                        breakLoop = true;
                        break;
                    }
                }
                if (breakLoop) {
                    break;
                }
                // found a vampire number!
                System.out.printf("%d * %d = %d\n", fang1, fang2, fang1 * fang2);
            }

        }
        time = System.currentTimeMillis() - time;
        System.out.printf("elapsed time: %.3f seconds\n", time / 1000.0);
    }
}