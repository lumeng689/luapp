package org.luapp.practise.proxy;

import com.sun.org.apache.bcel.internal.generic.MONITORENTER;
import org.apache.commons.io.FileUtils;

import javax.tools.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 自定义代理类
 * <p/>
 * Created by lumeng on 2015/3/7.
 */
public class CustomProxy {
    public static Object newInstance(Class infce) {

        String methodStr = "";
        for (Method m : infce.getMethods()) {

                methodStr += "    @Override\n" +
                        "    public void " + m.getName() + "() {\n" +
                        "        System.out.println(\"Before......\");\n" +
                        "        m." + m.getName() + "();\n" +
                        "        System.out.println(\"After......\");\n" +
                        "    }\n";

        }

        String str = "package org.luapp.practise.proxy;\n" +
                "\n" +
                "/**\n" +
                " * Created by lumeng on 2015/3/7.\n" +
                " */\n" +
                "public class $Proxy0 implements " + infce.getName() + "{\n" +
                " private " + infce.getName() + " m;\n" +
                "	public $Proxy0(" + infce.getName() + " m) {" + "\n" +
                "		super();" + "\n" +
                "		this.m = m;\n}\n" +
                methodStr +
                "}\n";

        String fileName = CustomProxy.class.getResource("/").getFile() + "org/luapp/practise/proxy/$Proxy0.java";
        System.out.println(fileName);
        File f = new File(fileName);
        File p = f.getParentFile();
        if (!p.exists()) {
            p.mkdirs();
        }

        if (!p.exists()) {
            System.out.println("==========================");
        }

        try {
            FileUtils.writeStringToFile(f, str);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileMgr = compiler.getStandardFileManager(null, null, null);
        Iterable units = fileMgr.getJavaFileObjects(fileName);
        JavaCompiler.CompilationTask ct = compiler.getTask(null, fileMgr, null, null, null, units);
        ct.call();
        try {
            fileMgr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ClassLoader cl = ClassLoader.getSystemClassLoader();
        try {
            Class clz = cl.loadClass("org.luapp.practise.proxy.$Proxy0");
            Constructor ctr = clz.getConstructor(infce);
            return ctr.newInstance(new Car());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
        Movable mv = (Movable) newInstance(Movable.class);
        mv.move();
    }
}
