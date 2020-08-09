package com.kaka;


import java.io.*;
/*
*
* */
public class MyClassLoader extends ClassLoader {
    private String name;

    private ClassLoader classLoader;

    @Override
    protected Class<?> findClass(String name) {
        byte[] data1 = getBytes("D:\\githubrepository\\axiom_tomcat\\com\\kaka\\Test1.class");

        Class<?> clazz = this.defineClass(name, data1, 0, data1.length);
        return  clazz;
    }

    public MyClassLoader(ClassLoader classLoader, String name) {
        super(classLoader);
        this.name = name;
    }

    @Override
    public Class<?> loadClass(String name)  {
        ClassLoader system = getSystemClassLoader().getParent();

        // 这块重点就是不能抛出异常,第一轮loadObject正常
        // 第二轮查询Test1查不到也要继续执行下去
        Class<?> clazz = null;
        try {
            clazz = system.loadClass(name);
        } catch (ClassNotFoundException e) {

        }

        if (clazz != null) {
            return clazz;
        }

        return findClass(name);
    }

    public static void main(String[] args) throws ClassNotFoundException {
        // myClassLoader是自定义的,因此父加载器是application加载器
        MyClassLoader myClassLoader = new MyClassLoader(MyClassLoader.class.getClassLoader(), "MyClassLoader");
        Class<?> clazz = myClassLoader.loadClass("com.kaka.Test1");
        System.out.println(clazz.getClassLoader());
    }


    public byte[] getBytes(String filePath)  {
        InputStream is = null;
        byte[] data = null;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            is = new FileInputStream(new File(filePath));
            int c = 0;
            while ((c=is.read()) != -1){
                baos.write(c);
            }
            data = baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return data;

    }


}
