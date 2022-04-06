package cn.cocowwy.showdbui.service;

import cn.cocowwy.showdbcore.cache.ShowDbCache;
import cn.cocowwy.showdbcore.config.GlobalContext;
import cn.cocowwy.showdbcore.constants.DBEnum;
import cn.cocowwy.showdbcore.entities.TableField;
import cn.cocowwy.showdbcore.entities.TableInfo;
import cn.cocowwy.showdbcore.entities.TableStructVo;
import cn.cocowwy.showdbcore.strategy.StructExecuteStrategy;
import cn.cocowwy.showdbcore.strategy.impl.mysql.MySqlExecuteStrategy;
import cn.cocowwy.showdbcore.util.CodeGenerateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author cocowwy.cn
 * @create 2022-03-03-11:15
 */
@Service
public class StructService {
    @Autowired
    List<StructExecuteStrategy> structExecuteStrategies;
    /**
     * 数据源类型所对应的执行策略，适配切换数据源能路由到指定策略
     */
    private static final Map<DBEnum, StructExecuteStrategy> STRUCT_STRATEGY = new HashMap<>(1);

    @PostConstruct
    void init() {
        structExecuteStrategies.forEach(s -> {
            if (s instanceof MySqlExecuteStrategy) {
                STRUCT_STRATEGY.put(DBEnum.MySQL, s);
            }
        });
    }

    /**
     * 获取所有表的结构
     * Get the structure of all tables
     * @return
     */
    public TableStructVo tableStruct(String ds, Integer pageSize, Integer pageNumber) {
        String key = ShowDbCache.buildCacheKey(ds, "tableStruct", pageSize + "@" + pageNumber);

        List<TableStructVo.TableStruct> rts = (List<TableStructVo.TableStruct>) ShowDbCache.cache().computeIfAbsent(key, (k) -> {
            List<String> tables = page(tableNames(ds), pageSize, pageNumber);
            List<TableStructVo.TableStruct> rt = tables.stream().map(table -> {
                List<TableField> tableFields = STRUCT_STRATEGY.get(GlobalContext.mapDs2DbType(ds)).tableStructure(ds, table);
                TableInfo tableInfo = new TableInfo();
                tableInfo.setTableName(table);
                tableInfo.setTableComment(STRUCT_STRATEGY.get(GlobalContext.mapDs2DbType(ds)).tableComment(ds, table).getTableComment());
                TableStructVo.TableStruct tableStruct = new TableStructVo.TableStruct();
                tableStruct.setTableInfo(tableInfo);
                tableStruct.setTableFieldList(tableFields);
                return tableStruct;
            }).collect(Collectors.toList());
            return rt;
        });

        TableStructVo tableStructVo = new TableStructVo();
        tableStructVo.setTotal(tableNames(ds).size());
        tableStructVo.setTableStructs(rts);

        return tableStructVo;
    }

    /**
     * 获取所有的表名称集合
     * Get a collection of all table names
     * @return
     */
    public List<String> tableNames(String ds) {
        String tablesKey = ShowDbCache.buildCacheKey(ds, "tableLists", "names");

        List<String> rts = (List<String>) ShowDbCache.cache().computeIfAbsent(tablesKey,
                (key) -> STRUCT_STRATEGY.get(GlobalContext.mapDs2DbType(ds)).tableNames(ds));
        return rts;
    }

    /**
     * 获取表结构
     * Get the structure of all tables
     * @return
     */
    public TableStructVo tableStruct(String ds, Integer pageSize, Integer pageNumber, String name) {
        String key = ShowDbCache.buildCacheKey(ds, "tableStruct", name + "#" + pageSize + "#" + pageNumber);

        List<String> tabkes = tableNames(ds).stream().filter(it -> it.contains(name)).collect(Collectors.toList());
        List<TableStructVo.TableStruct> rts = (List<TableStructVo.TableStruct>) ShowDbCache.cache().computeIfAbsent(key, (k) -> {
            List<String> likeTale = page(tabkes, pageSize, pageNumber);
            List<TableStructVo.TableStruct> rt = likeTale.stream().map(table -> {
                List<TableField> tableFields = STRUCT_STRATEGY.get(GlobalContext.mapDs2DbType(ds)).tableStructure(ds, table);
                TableInfo tableInfo = new TableInfo();
                tableInfo.setTableName(table);
                tableInfo.setTableComment(STRUCT_STRATEGY.get(GlobalContext.mapDs2DbType(ds)).tableComment(ds, table).getTableComment());
                TableStructVo.TableStruct tableStruct = new TableStructVo.TableStruct();
                tableStruct.setTableInfo(tableInfo);
                tableStruct.setTableFieldList(tableFields);
                return tableStruct;
            }).collect(Collectors.toList());
            return rt;
        });

        TableStructVo tableStructVo = new TableStructVo();
        tableStructVo.setTotal(tabkes.size());
        tableStructVo.setTableStructs(rts);
        return tableStructVo;
    }

    /**
     * 表详细信息
     * @return
     */
    public TableStructVo tableDetailInfo(String ds, String table) {
        String key = ShowDbCache.buildCacheKey(ds, "tableDetailInfo", table);
        TableStructVo rt = (TableStructVo) ShowDbCache.cache().computeIfAbsent(key, (k) -> {
            TableStructVo vo = new TableStructVo();
            vo.setTableInfo(STRUCT_STRATEGY.get(GlobalContext.mapDs2DbType(ds)).tableInfo(ds, table));
            return vo;
        });
        return rt;
    }

    /**
     * 表创建语句
     * Table creation statement
     * @param table 表名
     * @return
     */
    public String tableCreateStatement(String ds, String table) {
        String key = ShowDbCache.buildCacheKey(ds, "createStatement", table);
        return (String) ShowDbCache.cache()
                .computeIfAbsent(key, (k) -> STRUCT_STRATEGY.get(GlobalContext.mapDs2DbType(ds)).createTableStatement(ds, table));
    }

    /**
     * 数据库文档生成
     * @param response
     */
    public void dsTableDoc(HttpServletResponse response, String ds) throws IOException {
        String key = ShowDbCache.buildCacheKey(ds, "dsTableDoc", ds);
        StringBuilder htmlStr = (StringBuilder) ShowDbCache.get(key);
        if (htmlStr == null) {
            htmlStr = new StringBuilder("<div style='margin-left: 20%'><h1>").append(ds).append("</h1></div><div>");
            for (String table : this.tableNames(ds)) {
                List<TableField> tableFields = STRUCT_STRATEGY.get(GlobalContext.mapDs2DbType(ds)).tableStructure(ds, table);
                TableInfo tableInfo = new TableInfo();
                tableInfo.setTableName(table);
                tableInfo.setTableComment(STRUCT_STRATEGY.get(GlobalContext.mapDs2DbType(ds)).tableComment(ds, table).getTableComment());
                TableStructVo.TableStruct tableStruct = new TableStructVo.TableStruct();
                tableStruct.setTableInfo(tableInfo);
                tableStruct.setTableFieldList(tableFields);

                htmlStr.append("<div style='margin-top:20px;margin-left:20%'>")
                        .append("<h2>")
                        .append(table).append("  ")
                        .append(tableInfo.getTableComment())
                        .append("</h2>")
                        .append("<table border='2'>")
                        .append("<tr>\n" +
                                "<th>字段</th>\n" +
                                "<th>类型</th>\n" +
                                "<th>主鍵</th>\n" +
                                "<th>字段描述</th>\n" +
                                "<th>不为空</th>\n" +
                                "<th>默认值</th>\n" +
                                " </tr>");

                for (TableField field : tableFields) {
                    htmlStr.append("<tr>").append("<td>").append(field.getFieldName()).append("</td>");
                    htmlStr.append("<td>").append(field.getType()).append("</td>");
                    htmlStr.append("<td>").append(field.getPk().equals(Boolean.TRUE) ? "是" : "").append("</td>");
                    htmlStr.append("<td>").append(field.getComment()).append("</td>");
                    htmlStr.append("<td>").append(field.getNullable().equals(Boolean.TRUE) ? "是" : "").append("</td>");
                    htmlStr.append("<td>").append(field.getColumnDefault() == null ? "" : field.getColumnDefault()).append("</td>").append("</tr>");
                }
                htmlStr.append("</table></div>");
            }
            htmlStr.append("</div>");
            ShowDbCache.put(key, htmlStr);
        }

        ServletOutputStream stream = null;
        try {
            stream = response.getOutputStream();
            stream.write(htmlStr.toString().getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            // ignore exception..
        } finally {
            stream.close();
        }

    }

    /**
     * DB SQL
     * @param response
     * @param ds
     * @throws IOException
     */
    public void dbCreateStatement(HttpServletResponse response, String ds) throws IOException {
        String key = ShowDbCache.buildCacheKey(ds, "createDbStatement", ds);
        StringBuilder text = (StringBuilder) ShowDbCache.get(key);
        if (text == null) {
            text = new StringBuilder("CREATE DATABASE ").append(ds).append(";\n");
            for (String table : this.tableNames(ds)) {
                text.append("-- ").append(table).append(";\n");
                text.append("DROP TABLE IF EXISTS '").append(table).append("'").append(";\n");
                text.append(this.tableCreateStatement(ds, table)).append(";\n\n");
            }
        }

        ServletOutputStream stream = null;
        try {
            stream = response.getOutputStream();
            stream.write(text.toString().getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            // ignore exception..
        } finally {
            stream.close();
        }
    }

    /**
     * Java 表实体 生成
     */
    public String tableJavaCode(String ds, String table) {
        String rt = (String) ShowDbCache.cache().computeIfAbsent(ShowDbCache.buildCacheKey(ds, "tableJavaCode", table),
                (key) -> {
                    List<TableField> tableFields = STRUCT_STRATEGY.get(GlobalContext.mapDs2DbType(ds)).tableStructure(ds, table);
                    StringBuilder code = new StringBuilder("@Data\npublic class ");
                    code.append(CodeGenerateUtil.className(table));
                    code.append("{");
                    code.append("\n");
                    tableFields.forEach(field -> {
                        String javaName = CodeGenerateUtil.columnName(field.getFieldName());
                        String javaType = CodeGenerateUtil.getType(field.getType());
                        if (!StringUtils.isEmpty(field.getComment())) {
                            code.append("    /**\n");
                            code.append("     * ");
                            code.append(field.getComment());
                            code.append("\n");
                            code.append("     */");
                            code.append("\n");
                        }
                        code.append("    ");
                        code.append("private");
                        code.append(" ");
                        code.append(javaType);
                        code.append(" ");
                        code.append(javaName);
                        code.append(";");
                        code.append("\n");
                    });
                    code.append("}");
                    return code.toString();
                });
        return rt;
    }

    /**
     * 内存分页
     */
    private static <T> List<T> page(List<T> data, int size, int num) {
        if (data.size() < 1) {
            return new ArrayList<>(0);
        }
        int totalNum = data.size();
        // start 1
        int pageNum = num;
        int pageSize = size;
        int totalPage = 0;
        if (totalNum > 0) {
            totalPage = totalNum % pageSize == 0 ? totalNum / pageSize : totalNum / pageSize + 1;
        }
        if (pageNum > totalPage) {
            pageNum = totalPage;
        }
        int startPoint = (pageNum - 1) * pageSize;
        int endPoint = startPoint + pageSize;
        if (totalNum <= endPoint) {
            endPoint = totalNum;
        }
        return data.subList(startPoint, endPoint);
    }
}
