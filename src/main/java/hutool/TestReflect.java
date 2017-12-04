package hutool;

import annotation.MyValue;
import com.kun.domain.Student;
import com.xiaoleilu.hutool.util.ReflectUtil;

public class TestReflect {
    public static void main(String args[]){
//        Student student=new Student();
//        ReflectUtil.invoke(student,"setAge",78);
//        ReflectUtil.invoke(student,"setName","就是我");
//        System.out.println(student);
//        System.out.println(ReflectUtil.<Object>invoke(student, "getName"));
//        System.out.println(ReflectUtil.<Object>invoke(student, "getAge"));
//
//        Object age = ReflectUtil.getFieldValue(student, "age");
//        System.out.println(age);
//
//        ReflectUtil.setFieldValue(student,"name","abc");
//        Object name = ReflectUtil.getFieldValue(student, "name");
//        System.out.println(name);





//        Object value;
//        Class<Student> studentClass = Student.class;
//        boolean isExist = studentClass.isAnnotationPresent(MyValue.class);
//        if (isExist){
//            MyValue annotation = studentClass.getAnnotation(MyValue.class);
//            value=annotation.value();
//        }


        Object annotationValue = getAnnotationValue(Student.class,MyValue.class);
        System.out.println(annotationValue);
    }

    private static Object getAnnotationValue(Class targetClass, Class<?> annotationClass, String method) {
        boolean isExist = targetClass.isAnnotationPresent(annotationClass);
        if (isExist){
            MyValue annotation = (MyValue) targetClass.getAnnotation(annotationClass);
            return ReflectUtil.invoke(annotation,method);
        }
        return null;
    }

    private static Object getAnnotationValue(Class studentClass, Class annotationClass) {
        return getAnnotationValue(studentClass,annotationClass,"value");
    }
}
