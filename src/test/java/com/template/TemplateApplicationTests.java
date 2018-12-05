package com.template;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TemplateApplicationTests {

    @Test
    public void contextLoads() {
        List<String> phones = new ArrayList<>();
        phones.add("13411112222");
        phones.add("13511112222");
        phones.add("13611112222");
        phones.add("13711112222");
        phones.add("13811112222");
        phones.add("13911112222");
        phones.add("14711112222");
        phones.add("15011112222");
        phones.add("15111112222");
        phones.add("15211112222");
        phones.add("15711112222");
        phones.add("15811112222");
        phones.add("15911112222");
        phones.add("17811112222");
        phones.add("18211112222");
        phones.add("18311112222");
        phones.add("18411112222");
        phones.add("18711112222");
        phones.add("18811112222");
        phones.add("19811112222");
        for (String data : phones) {
            System.out.println(data + ":" + isMobileNO(data));
        }
    }

    public boolean isMobileNO(String mobiles) {
//		134
//		135
//		136
//		137
//		138
//		139
//		147
//		150
//		151
//		152
//		157
//		158
//		159
//		178
//		182
//		183
//		184
//		187
//		188
//		198
        Pattern p = Pattern.compile("^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$");

        Matcher m = p.matcher(mobiles);

        return m.matches();
    }

    @Test
    public void test1() {
        Date date = new Date();
        System.out.println(date.getTime());
    }

    @Test
    public void test2() {
        int[] arr = {4, 7, 2, 1, 5, 8, 9, 11};
        System.out.println("排序前数组为：");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        for (int i = 0; i < arr.length - 1; i++) {//外层循环控制排序趟数
            for (int j = 0; j < arr.length - 1 - i; j++) {//内层循环控制每一趟排序多少次
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println();
        System.out.println("排序后的数组为：");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        //1 2 4 5 7 8 9 11
        System.out.println();
        // 1 2 1 2 1 1 2
        int[] diVal = new int[arr.length - 1];
        for (int i = 0; i < arr.length - 1; i++) {
            diVal[i] = arr[i + 1] - arr[i];

        }
        for (int num : diVal) {
            System.out.print(num + " ");
        }
        List<Integer> ls = new ArrayList<>();
        // 1 2 1 2 1 1 2
        for (int i = 0; i < diVal.length - 1; i++) {
            int a = diVal[i];
            int b = diVal[i + 1];
            if (a == b && a == 1) {
                ls.add(i);
            }
        }
        System.out.println(ls.toString());
        int[] result = new int[arr.length];
        for(int j=0;j<ls.size();j++){
            for(int i = 0; i < arr.length; i++){
                if(i==ls.get(j)){
                    result[i+1]=0;
                }else{
                    result[i]=arr[i];
                }
            }
        }
        System.out.println();
        for (int num : result) {
            System.out.print(num + " ");
        }
    }
}
