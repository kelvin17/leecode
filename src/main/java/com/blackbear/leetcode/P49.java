package com.blackbear.leetcode;

import java.util.*;

public class P49 {

    public static void main(String[] args) {
        String[] strs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        Solution49 solution49 = new Solution49();
        List<List<String>> result = solution49.groupAnagrams(strs);
        System.out.println(result);
    }
}

class Solution49 {
    public List<List<String>> groupAnagrams(String[] strs) {

        List result = new ArrayList();
        int length = strs.length;
        if (length <= 1) {

            result.add(Arrays.stream(strs).toList());

            return result;
        }

        List<ResultObj> resultObjs = new ArrayList<>();

        //todo
        for (int i = 0; i < length; i++) {
            String current = strs[i];

            //1. 处理当前的单词
            Map<Character, Integer> currentMap = new HashMap<>();
            for (Character cr : current.toCharArray()) {
                Integer num = currentMap.get(cr);
                if (num == null) {
                    currentMap.put(cr, 1);
                } else {
                    currentMap.put(cr, num + 1);
                }
            }

            //2. 遍历已有的
            if (resultObjs.isEmpty()) {

                List broList = new ArrayList();
                broList.add(current);
                resultObjs.add(new ResultObj(currentMap, broList));

            } else {
                boolean flag = false;
                for (ResultObj item : resultObjs) {
                    Map<Character, Integer> tmpMap = item.getDescirbe();
                    if (tmpMap.size() != currentMap.size()) {
                        continue;
                    }

                    flag = true;
                    for (Map.Entry<Character, Integer> innerItem : tmpMap.entrySet()) {

                        if (currentMap.get(innerItem.getKey()) == null) {
                            //不相等
                            flag = false;
                            break;
                        }
                        if (!currentMap.get(innerItem.getKey()).equals(innerItem.getValue())) {
                            //不相等
                            flag = false;
                            break;
                        }
                        //相等。可以继续
                    }

                    if (flag) {
                        item.getBro().add(current);
                        break;
                    }
                }


                if (!flag) {
                    //是一个新的
                    List broList = new ArrayList();
                    broList.add(current);
                    resultObjs.add(new ResultObj(currentMap, broList));
                }
            }
        }


        for (ResultObj item : resultObjs) {
            result.add(item.getBro());
        }

        return result;
    }
}

class ResultObj {

    public Map<Character, Integer> getDescirbe() {
        return descirbe;
    }

    public List<String> getBro() {
        return bro;
    }

    public ResultObj(Map<Character, Integer> descirbe, List<String> bro) {
        this.descirbe = descirbe;
        this.bro = bro;
    }

    private Map<Character, Integer> descirbe = new HashMap<>();

    private List<String> bro = new ArrayList<>();

}