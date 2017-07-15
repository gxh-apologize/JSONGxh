package cn.gxh.jsongxh;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by GXH on 2017/7/13.
 */
public class FastJson {

    private static final int JONS_ARRAY=1;
    private static final int JONS_OBJECT=2;
    private static final int JONS_ERROR=3;

    public static String toJson(Object object) {
        //Json载体
        StringBuffer sb = new StringBuffer();
        //判断是否是集合类型
        if (object instanceof List<?>) {
            sb.append("[");
            List<?> list = (List<?>) object;
            //循环取集合类型
            for (int i = 0; i < list.size(); i++) {
                objectToJson(sb, list.get(i));
                if (i < list.size() - 1) {
                    sb.append(",");
                }
            }
        } else {
            objectToJson(sb, object);
        }
        return sb.toString();
    }

    /**
     * 解析单独的JsonObject
     *
     * @param sb
     * @param object
     */
    private static void objectToJson(StringBuffer sb, Object object) {
        sb.append("{");
        List<Field> fields = new ArrayList<>();
        getAllField(object.getClass(), fields);
        for (int i = 0; i < fields.size(); i++) {
            Method method = null;
            Object fieldValue = null;
            Field field = fields.get(i);
            String fieldName = field.getName();
            String methodName = "get" + ((char) (fieldName.charAt(0) - 32) + fieldName.substring(1));
            try {
                method = object.getClass().getMethod(methodName);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                if (!fieldName.startsWith("is")) {
                    methodName = "is" + ((char) (fieldName.charAt(0) - 32) + fieldName.substring(1));
                }
                try {
                    method = object.getClass().getMethod(methodName);
                } catch (NoSuchMethodException e1) {
                    e1.printStackTrace();
                }
            }

            if (method != null) {
                try {
                    fieldValue = method.invoke(object);
                } catch (Exception e) {
                    replaceChar(i, fields, sb);
                    continue;
                }
            }

            if (fieldValue != null) {
                sb.append("\"");
                sb.append(fieldName);
                sb.append("\":");

                if (fieldValue instanceof Integer || fieldValue instanceof Double ||
                        fieldValue instanceof Long || fieldValue instanceof Boolean) {
                    sb.append(fieldValue.toString());
                } else if (fieldValue instanceof String) {
                    sb.append("\"");
                    sb.append(fieldValue.toString());
                    sb.append("\"");
                } else if (fieldValue instanceof List<?>) {
                    listToJson(sb, fieldValue);
                } else {
                    //类类型数据的解析
                    objectToJson(sb, fieldValue);
                }

                sb.append(",");
            }

            //删除最后一个逗号
            replaceChar(i, fields, sb);
        }

        sb.append("}");
    }

    private static void replaceChar(int i, List<Field> fields, StringBuffer sb) {
        if (i == fields.size() - 1 && sb.charAt(sb.length() - 1) == ',') {
            sb.deleteCharAt(sb.length() - 1);
        }
    }


    private static void listToJson(StringBuffer sb, Object fieldValue) {
        List<?> list = (List<?>) fieldValue;
        sb.append("[");
        for (int i = 0; i < list.size(); i++) {
            objectToJson(sb, list.get(i));

            if (i < list.size() - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
    }

    /**
     * 反射获取当前所有成员变量
     * Object类型、final修饰的变量不需要获取
     *
     * @param aClass
     * @param fields
     */
    private static List<Field> getAllField(Class<?> aClass, List<Field> fields) {
        if (fields == null) {
            fields = new ArrayList<>();
        }

        //排除Object类型
        if (aClass.getSuperclass() != null) {
            //当前Class所有成员变量
            Field[] declaredFields = aClass.getDeclaredFields();
            for (Field field : declaredFields) {
                //是否是final修饰
                if (!Modifier.isFinal(field.getModifiers())) {
                    fields.add(field);
                }
            }
            getAllField(aClass.getSuperclass(), fields);
        }
        return fields;
    }

    //---------------------------------------json转Mode

    /**
     * Json转Mode
     *
     * @param clazz
     * @param json
     * @return
     */
    public static Object parseObject(Class clazz, String json) {
        Object object = null;
        //jsonArray类型
        if (json.charAt(0) == '[') {
            try {
                object = toList(clazz, json);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (json.charAt(0) == '{') {
            try {
                JSONObject jsonObject = new JSONObject(json);
                //反射    Mode一定要有空的构造方法
                object = clazz.newInstance();
                Iterator<?> iterator = jsonObject.keys();
                while (iterator.hasNext()) {
                    String key = (String) iterator.next();
                    Object fieldValue = null;
                    List<Field> fields = getAllField(clazz, null);
                    for (Field field : fields) {
                        //将key和成员变量匹配
                        if (field.getName().equalsIgnoreCase(key)) {
                            field.setAccessible(true);
                            fieldValue = getFieldValue(field, jsonObject, key);
                            if (fieldValue != null) {
                                field.set(object, fieldValue);
                            }
                            field.setAccessible(false);
                        }
                    }

                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return object;
    }

    /**
     * 获取成员变量的值，key对应的值
     *
     * @param field
     * @param jsonObject
     * @param key
     * @return
     */
    private static Object getFieldValue(Field field, JSONObject jsonObject, String key)
            throws JSONException {
        //值
        Object fieldValue = null;
        //获取成员变量类型
        Class<?> fieldClass = field.getType();
        String fieldType = fieldClass.getSimpleName().toString();
        if (fieldType.equals("int") || fieldType.equals("Integer")) {
            fieldValue = jsonObject.getInt(key);

        } else if (fieldType.equals("String")) {
            fieldValue = jsonObject.getString(key);

        } else if (fieldType.equals("boolean")) {
            fieldValue = jsonObject.getBoolean(key);

        } else if (fieldType.equals("long")) {
            fieldValue = jsonObject.getLong(key);

        } else if (fieldType.equals("double")) {
            fieldValue = jsonObject.getDouble(key);

        }else{
            //集合类型、对象类型
            String jsonValue= jsonObject.getString(key);
            int jsonType=getJsonType(jsonValue);
            switch (jsonType){
                case JONS_OBJECT:
                    fieldValue=parseObject(fieldClass,jsonValue);
                    break;
                case JONS_ARRAY:
                    Type genericType = field.getGenericType();
                    if(genericType instanceof ParameterizedType){
                        ParameterizedType parameterizedType= (ParameterizedType)
                                genericType;
                        Type[] types = parameterizedType.getActualTypeArguments();
                        for(Type type:types){
                            Class<?> fieldArgClass= (Class<?>) type;
                            fieldValue=toList(fieldArgClass,jsonValue);
                        }
                    }
                    break;
                case JONS_ERROR:
                    break;
            }
        }
        return fieldValue;
    }

    /**
     * 当前json类型
     * @param jsonValue
     * @return
     */
    private static int getJsonType(String jsonValue) {
        char firstChar=jsonValue.charAt(0);
        if(firstChar=='['){
            return JONS_ARRAY;
        }else if(firstChar=='{'){
            return JONS_OBJECT;
        }else{
            return JONS_ERROR;
        }
    }

    /**
     * 解析数组json
     *
     * @param clazz
     * @param json
     * @return
     */
    private static Object toList(Class clazz, String json) throws JSONException {
        List<Object> list=new ArrayList<>();
        JSONArray jsonArray=new JSONArray(json);
        for(int i=0;i<jsonArray.length();i++){
            String jsonValue = jsonArray.getJSONObject(i).toString();
            switch (getJsonType(jsonValue)){
                case JONS_OBJECT:
                    list.add(parseObject(clazz,jsonValue));
                    break;
                case JONS_ARRAY:
                    List<?> infoList= (List<?>) toList(clazz,jsonValue);
                    list.add(infoList);
                    break;
                case JONS_ERROR:
                    break;
            }
        }
        return list;
    }
}
