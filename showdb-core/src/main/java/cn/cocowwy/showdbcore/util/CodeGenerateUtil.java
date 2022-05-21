package cn.cocowwy.showdbcore.util;

import org.springframework.util.StringUtils;

/**
 * @author Cocowwy
 * @create 2022-03-03-22:01
 */
public class CodeGenerateUtil {
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
     * 处理变量名（属性名）
     * @param columnName 字段名称
     * @return
     */
    public static String columnName(String columnName) {
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

    public static String getType(String dataType) {
        String type = "";
        if ("tinyint".equals(substringBefore(dataType, "("))) {
            type = "Byte";
        }
        if ("smallint".equals(substringBefore(dataType, "("))) {
            type = "Short";
        }
        if ("mediumint".equals(substringBefore(dataType, "("))) {
            type = "Integer";
        }
        if ("int".equals(substringBefore(dataType, "("))) {
            type = "Integer";
        }
        if ("integer".equals(substringBefore(dataType, "("))) {
            type = "Integer";
        }
        if ("bigint".equals(substringBefore(dataType, "("))) {
            type = "Long";
        }
        if ("bit".equals(substringBefore(dataType, "("))) {
            type = "Boolean";
        }
        if ("double".equals(substringBefore(dataType, "("))) {
            type = "Double";
        }
        if ("float".equals(substringBefore(dataType, "("))) {
            type = "Float";
        }
        if ("decimal".equals(substringBefore(dataType, "("))) {
            type = "Long";
        }
        if ("char".equals(substringBefore(dataType, "("))) {
            type = "String";
        }
        if ("varchar".equals(substringBefore(dataType, "("))) {
            type = "String";
        }
        if ("date".equals(substringBefore(dataType, "("))) {
            type = "Date";
        }
        if ("time".equals(substringBefore(dataType, "("))) {
            type = "Date";
        }
        if ("year".equals(substringBefore(dataType, "("))) {
            type = "Date";
        }
        if ("timestamp".equals(substringBefore(dataType, "("))) {
            type = "Timestamp";
        }
        if ("datetime".equals(substringBefore(dataType, "("))) {
            type = "Timestamp";
        }
        if ("tinytext".equals(substringBefore(dataType, "("))) {
            type = "String";
        }
        if ("enum".equals(substringBefore(dataType, "("))) {
            type = "String";
        }
        if ("set".equals(substringBefore(dataType, "("))) {
            type = "String";
        }
        if ("text".equals(substringBefore(dataType, "("))) {
            type = "String";
        }
        if ("mediumtext".equals(substringBefore(dataType, "("))) {
            type = "String";
        }
        if ("longtext".equals(substringBefore(dataType, "("))) {
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
}
