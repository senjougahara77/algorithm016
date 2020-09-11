package Test;

public class plusone {
        public int[] plusOne(int[] digits) {
            int[] temp = new int[digits.length + 1];
            temp[temp.length - 1] = 1;
            for(int i = digits.length - 1; i >= 0; i--){
                if(digits[i] == 9 && temp[i + 1] == 1){
                    digits[i] = 0;
                    temp[i] = 1;
                }
                else if(temp[i + 1] == 1){
                    digits[i] += 1;
                }
            }
            if(temp[0] == 1){
                int[] res = new int[digits.length + 1];
                res[0] = 1;
                return res;
            }
            return digits;
        }
}

