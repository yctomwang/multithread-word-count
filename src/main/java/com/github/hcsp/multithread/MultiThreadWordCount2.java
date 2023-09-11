package com.github.hcsp.multithread;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.*;


public class MultiThreadWordCount2 {
    // 使用threadNum个线程，并发统计文件中各单词的数量
    private static ConcurrentHashMap<String, Integer> resMap = new ConcurrentHashMap<>();
    public static Map<String, Integer> count(int threadNum, List<File> files) {
        for(int i =0;i<threadNum;i++){
            final int index_rubbish = i;
            new Thread(() -> {
                //todo: each new thread know which file they are going to open
                //then
                try {
                    BufferedReader br = new BufferedReader(new FileReader(files.get(index_rubbish)));
                    String line;
                    while ((line = br.readLine()) != null) {
                        //todo: what we are getting here
                        //string manipulation here

                        String[] str_line = line.split(" ");//use the white space as delimeter
                        for(String afterSplitStr : str_line){
                            //todo: check if afterSplitstr is inside the dict
                            if(resMap.containsKey(afterSplitStr)){
                                resMap.put(afterSplitStr,resMap.get(afterSplitStr)+1);
                            }else{
                                resMap.put(afterSplitStr, 1);
                            }
                        }


                        //todo: update the concurrent hashmap right here

                    }
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }).start();
        }
            HashMap<String, Integer> hashMap = new HashMap<>(resMap);
            return hashMap;
    }


}
