package com.example.yangt.io;

import com.fasterxml.jackson.databind.deser.std.StringDeserializer;

import java.io.*;

public class FileReaderTest {
    public static void main(String[] args) throws Exception {
        //String s1 = fileReaderDemo_1();
        //String s2 = fileReaderDemo_2();
        //System.out.println(s1);
        //System.out.println(s2);
        //fileWriter();
        //fileReadAndWriter();
        exchangeStream();
    }

    private static void fileReadAndWriter(){
        Reader reader = null;
        Writer writer = null;
        try {
            File sourfile = new File("C:\\A_D\\PaiLa\\PersonalWorkspace\\SpringCloud\\producer\\src\\main\\java\\com\\example\\yangt\\io\\reader.txt");
            File destfile = new File("C:\\A_D\\PaiLa\\PersonalWorkspace\\SpringCloud\\producer\\src\\main\\java\\com\\example\\yangt\\io\\writer.txt");

            reader = new FileReader(sourfile);
            writer = new FileWriter(destfile);
            char[] chars = new char[1024];
            int len;
            while ((len = reader.read(chars)) != -1){
                writer.write(chars,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (writer != null){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static void fileWriter() {
        File file = new File("C:\\A_D\\PaiLa\\PersonalWorkspace\\SpringCloud\\producer\\src\\main\\java\\com\\example\\yangt\\io\\fileWriter.txt");
        Writer fileWriter = null;
        try {
            fileWriter = new FileWriter(file,false);
            String[] strs = new String[5];
            strs[0] = "kobe1";
            strs[1] = "kobe2";
            strs[2] = "kobe3";
            strs[3] = "kobe4";
            strs[4] = "kobe5";
            for (int i = 0; i < strs.length; i++) {
                fileWriter.write(strs[i]+"-");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fileWriter != null){
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * filereader  1
     * @return str
     */
    private static String fileReaderDemo_1() {
        String str = "";
        Reader fileReader = null;
        File file = new File("C:\\A_D\\PaiLa\\PersonalWorkspace\\SpringCloud\\producer\\src\\main\\java\\com\\example\\yangt\\io\\reader.txt");

        try {
             fileReader = new FileReader(file);
             int data;

             while ((data = fileReader.read()) != -1){
                 str = str + (char)data;
                 System.out.println((char) data);
             };
            System.out.println(str);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(fileReader != null){
                    fileReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return str;
    }

    /**
     * filereader 2
     * @return
     */
    private static String fileReaderDemo_2() {
        String str = "";
        Reader fileReader = null;
        File file = new File("C:\\A_D\\PaiLa\\PersonalWorkspace\\SpringCloud\\producer\\src\\main\\java\\com\\example\\yangt\\io\\reader.txt");

        try {
            fileReader = new FileReader(file);
            char[] chars = new char[5];
            int len;

            while ((len = fileReader.read(chars)) != -1){
                //错误写法 1
               /* for (int i = 0; i < chars.length; i++) {
                    System.out.println(chars[i]);
                }*/

                // 错误写法 2
                //str = str+new String(chars);

                //正确写法 1
                  /*for (int i = 0; i < len; i++) {
                      str = str + chars[i];
                }*/

                //正确写法 2
                str = str + new String(chars,0,len);
            };
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return str;
    }

    /**
     * 转换流   InputStreamReader  InputStreamWriter
     * @throws Exception
     */
    public static void exchangeStream() throws Exception {
        File file = new File("C:\\A_D\\PaiLa\\PersonalWorkspace\\SpringCloud\\producer\\src\\main\\java\\com\\example\\yangt\\io\\reader.txt");
        InputStream inputStream = new FileInputStream(file);

        String str = "";
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        char[] chars = new char[1024];
        int len;
        while ((len = inputStreamReader.read(chars)) != -1){
            str = str + new String(chars,0,len);
        }
        System.out.println(str);
    }

}
