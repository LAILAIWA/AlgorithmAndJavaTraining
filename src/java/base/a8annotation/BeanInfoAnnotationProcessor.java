package java.base.a8annotation;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.beans.Introspector;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@SupportedAnnotationTypes("java.base.a8annotation.Property")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class BeanInfoAnnotationProcessor extends AbstractProcessor {
    /**
     * 处理注解声明
     * @param annotations 本轮要处理的注解集
     * @param rounEnv 包含当前处理轮次的有关信息的RoundEnv引用
     * @return
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment rounEnv) {
        for (TypeElement t : annotations){//迭代遍历注解过的方法
            Map<String,Property> props = new LinkedHashMap<>();
            String beanClassName = null;
            for(Element e : rounEnv.getElementsAnnotatedWith(t)){
                String mname = e.getSimpleName().toString();
                String[] prefixes = {"get","set","is"};//剥离定义的前缀
                boolean found = false;
                for(int i = 0; !found && i < prefixes.length;i++){
                    if(mname.startsWith(prefixes[i])){
                        found = true;
                        int start = prefixes[i].length();
                        String name = Introspector.decapitalize(mname.substring(start));//转换小写，得到属性名
                        props.put(name,e.getAnnotation(Property.class));
                    }
                }
                if(!found){//未找到合法的注释属性
                    processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
                            "@Property must be applied to getXxx, setXxx, or isXxx method",e);
                }else if(beanClassName == null){
                    beanClassName = ((TypeElement) e.getEnclosingElement()).getQualifiedName().toString();
                }
            }
            try {
                if(beanClassName != null){
                    writeBeanInfoFile(beanClassName,props);
                }
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }
        return true;
    }

    private void writeBeanInfoFile(String beanClassName, Map<String,Property> props)throws IOException{//编写源文件，生成XxxBeanInfo，processingEnv可以访问各种处理服务
        JavaFileObject sourceFile = processingEnv.getFiler().createSourceFile(beanClassName + "BeanInfo");
        PrintWriter out = new PrintWriter(sourceFile.openWriter());
        int i = beanClassName.lastIndexOf(".");
        if(i > 0){
            out.print("package ");
            out.print(beanClassName.substring(0,i));
            out.println(";");
        }
        out.print("public class ");
        out.print(beanClassName.substring(i + 1));
        out.println("BeanInfo extends java.beans.SimpleBeanInfo");
        out.println("{");
        out.println("    public java.beans.PropertyDescriptor[] getPropertyDescriptors(){");
        out.println("        try{");
        for(Map.Entry<String,Property> e : props.entrySet()){
            out.print("            java.beans.PropertyDescriptor ");
            out.print(e.getKey());
            out.println("Descriptor");
            out.print("            = new java.beans.PropertyDescriptor(\"");
            out.print(e.getKey());
            out.print("\",");
            out.print(beanClassName);
            out.println(".class);");
            String ed = e.getValue().editor().toString();
            if(!ed.equals("")){
                out.print("            ");
                out.print(e.getKey());
                out.print("Descriptor.setPropertyEditorClass(");
                out.print(ed);
                out.println(".class);");
            }
        }
        out.println("            return new java.beans.PropertyDescriptor[]{");
        boolean first = true;
        for(String p : props.keySet()){
            if(first) first = false;
            else out.print(",");
            out.println();
            out.print("            ");
            out.print(p);
            out.print("Descriptor");
        }
        out.println();
        out.println("            };");
        out.println("        }");
        out.println("        catch (java.beans.IntrospectionException e){");
        out.println("            e.printStackTrace();");
        out.println("            return null;");
        out.println("        }");
        out.println("    }");
        out.println("}");
        out.close();
    }
}
