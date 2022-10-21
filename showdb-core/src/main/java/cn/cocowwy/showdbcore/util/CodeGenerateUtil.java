package cn.cocowwy.showdbcore.util;

import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 代码生成工具
 * @author Cocowwy
 * @create 2022-03-03-22:01
 */
public class CodeGenerateUtil {
    public static String LEFT_PARENTS = "(";


    /**
     * 生成类名
     * @param tableName 数据表表名
     * @return
     */
    public static String className(String tableName) {
        String fileName = "";
        //获得表名
        //去掉表名的下划线
        String[] tablesName = tableName.split("_");
        for (int j = 0; j < tablesName.length; j++) {
            //将每个单词的首字母变成大写
            tablesName[j] = tablesName[j].substring(0, 1).toUpperCase() + tablesName[j].substring(1);
            fileName += tablesName[j].replace("Tb", "");
        }
        return fileName;
    }

    /**
     * 驼峰命名
     * @param columnName 带有下划线的字段名称
     * @return 驼峰命名
     */
    public static String camelCase(String columnName) {
        //将字段名称user_name格式变成userName格式
        String colName = "";
        //根据下划线将名字分为数组
        String[] columnsName = columnName.split("_");
        //遍历数组，将除第一个单词外的单词的首字母大写
        for (int h = 0; h < columnsName.length; h++) {
            for (int k = 1; k < columnsName.length; k++) {
                columnsName[k] = columnsName[k].substring(0, 1).toUpperCase() + columnsName[k].substring(1);
            }
            //拼接字符串以获得属性名（字段名称）
            colName += columnsName[h];
        }
        return colName;
    }

    /**
     * 数据库类型 映射成 Java 类型
     * @param dataType
     * @return
     */
    public static String getType(String dataType) {
        String type = "";
        if ("tinyint".equals(substringBefore(dataType, LEFT_PARENTS))) {
            type = "Byte";
        }
        if ("smallint".equals(substringBefore(dataType, LEFT_PARENTS))) {
            type = "Short";
        }
        if ("mediumint".equals(substringBefore(dataType, LEFT_PARENTS))) {
            type = "Integer";
        }
        if ("int".equals(substringBefore(dataType, LEFT_PARENTS))) {
            type = "Integer";
        }
        if ("integer".equals(substringBefore(dataType, LEFT_PARENTS))) {
            type = "Integer";
        }
        if ("bigint".equals(substringBefore(dataType, LEFT_PARENTS))) {
            type = "Long";
        }
        if ("bit".equals(substringBefore(dataType, LEFT_PARENTS))) {
            type = "Boolean";
        }
        if ("double".equals(substringBefore(dataType, LEFT_PARENTS))) {
            type = "Double";
        }
        if ("float".equals(substringBefore(dataType, LEFT_PARENTS))) {
            type = "Float";
        }
        if ("decimal".equals(substringBefore(dataType, LEFT_PARENTS))) {
            type = "Long";
        }
        if ("char".equals(substringBefore(dataType, LEFT_PARENTS))) {
            type = "String";
        }
        if ("varchar".equals(substringBefore(dataType, LEFT_PARENTS))) {
            type = "String";
        }
        if ("date".equals(substringBefore(dataType, LEFT_PARENTS))) {
            type = "Date";
        }
        if ("time".equals(substringBefore(dataType, LEFT_PARENTS))) {
            type = "Date";
        }
        if ("year".equals(substringBefore(dataType, LEFT_PARENTS))) {
            type = "Date";
        }
        if ("timestamp".equals(substringBefore(dataType, LEFT_PARENTS))) {
            type = "LocalDateTime";
        }
        if ("datetime".equals(substringBefore(dataType, LEFT_PARENTS))) {
            type = "LocalDateTime";
        }
        if ("tinytext".equals(substringBefore(dataType, LEFT_PARENTS))) {
            type = "String";
        }
        if ("enum".equals(substringBefore(dataType, LEFT_PARENTS))) {
            type = "String";
        }
        if ("set".equals(substringBefore(dataType, LEFT_PARENTS))) {
            type = "String";
        }
        if ("text".equals(substringBefore(dataType, LEFT_PARENTS))) {
            type = "String";
        }
        if ("mediumtext".equals(substringBefore(dataType, LEFT_PARENTS))) {
            type = "String";
        }
        if ("longtext".equals(substringBefore(dataType, LEFT_PARENTS))) {
            type = "String";
        }
        return type;
    }

    public static String substringBefore(String str, String separator) {
        if (!StringUtils.isEmpty(str) && separator != null) {
            if (separator.isEmpty()) {
                return "";
            } else {
                int pos = str.indexOf(separator);
                return pos == -1 ? str : str.substring(0, pos);
            }
        } else {
            return str;
        }
    }

    public static String currentTime() {
        String DATE_FORMAT = "yyyy-MM-dd HH:mm";
        return new SimpleDateFormat(DATE_FORMAT).format(new Date());
    }
}
