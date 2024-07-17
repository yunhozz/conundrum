package programmers;

import java.util.HashSet;

class 전화번호목록 {

    public boolean solution(String[] phone_book) {
        HashSet<String> set = new HashSet<>() {{
            for (String phone : phone_book) {
                add(phone);
            }
        }};

        for (String phone : phone_book) {
            for (int j = 0; j < phone.length(); j++) {
                if (set.contains(phone.substring(0, j))) {
                    return false;
                }
            }
        }

        return true;
    }
}