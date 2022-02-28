package com.bzfar.config;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.parsers.BlockAttackSqlParser;
import com.baomidou.mybatisplus.extension.parsers.DynamicTableNameParser;
import com.baomidou.mybatisplus.extension.parsers.ITableNameHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TableNameHandler;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.DynamicTableNameInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser;
import com.bzfar.HeadContext;
import com.bzfar.handle.TenantLineInnerHandler;
import com.bzfar.table.TableProxy;
import com.bzfar.table.TableSuffix;
import com.bzfar.table.TenantLineInner;
import com.github.pagehelper.PageHelper;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.NullValue;
import net.sf.jsqlparser.expression.StringValue;
import org.apache.axis.utils.StringUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.reflections.Reflections;
import org.reflections.scanners.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Configuration
@MapperScan("com.bzfar.mapper")
public class MyBatisConfig {

    private List<String> tableNames = new ArrayList<>();

    private List<String> suffixTables = new ArrayList<>();

    @Autowired
    private MetaHandler metaHandler;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private TenantLineInnerHandler tenantLineInnerHandler;

    @Bean
    public GlobalConfig globalConfig() {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setMetaObjectHandler(metaHandler);
        return globalConfig;
    }

    /**
     * 针对yaml中配置的表名进行拦截,匹配成功后的表名加上法院代码形成新的表名，例如:user -> user_whzy
     */
    @Bean
    public MybatisPlusInterceptor paginationInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        DynamicTableNameInnerInterceptor dynamicTableNameInnerInterceptor = new DynamicTableNameInnerInterceptor();
        tableNames = getTableName();
        if (tableNames.size() > 0) {
            dynamicTableNameInnerInterceptor.setTableNameHandlerMap(new HashMap<String, TableNameHandler>(2) {{
                tableNames.forEach(tableTitle -> put(tableTitle, (sql, tableName) -> tableName + "_" + HeadContext.getFydm()));
            }});
        }
        suffixTables = getSuffixTableNames();
        if (suffixTables.size() > 0) {
            dynamicTableNameInnerInterceptor.setTableNameHandlerMap(new HashMap<String, TableNameHandler>(2) {{
                suffixTables.forEach(tableTitle -> put(tableTitle, (sql, tableName) -> tableName + "_" + HeadContext.getMysqlSuffix()));
            }});
        }
        if (tableNames.size() > 0 || suffixTables.size() > 0) {
            interceptor.addInnerInterceptor(dynamicTableNameInnerInterceptor);
        }
        interceptor.addInnerInterceptor(new TenantLineInnerInterceptor(new TenantLineHandler() {
            @Override
            public Expression getTenantId() {
                String deviceNum = HeadContext.getDeviceNum();
                if (StringUtils.isEmpty(deviceNum)) {
                    deviceNum = request.getHeader("x-device");
                }
                if (null == deviceNum) {
                    return new NullValue();
                }
                //     return new BzcyStringValue(deviceNum);
                return new StringValue("'" + deviceNum + "'");
            }

            @Override
            public String getTenantIdColumn() {
                return "device";
            }

            @Override
            public boolean ignoreTable(String tableName) {
                List<String> tables = new ArrayList();
                Reflections reflections = new Reflections("com.bzfar.entity", new Scanner[0]);
                Set<Class<?>> classesList = reflections.getTypesAnnotatedWith(TenantLineInner.class);
                Iterator var4 = classesList.iterator();
                while (var4.hasNext()) {
                    Class clazz = (Class) var4.next();
                    TableName annotation = (TableName) clazz.getAnnotation(TableName.class);
                    if (null == annotation) {
                        String name = clazz.getSimpleName();
                        name = name.substring(0, 1).toLowerCase() + name.substring(1);
                        name = name.replaceAll("([A-Z]+)", "_$1").toLowerCase();
                        tables.add(name);
                    } else {
                        String name = annotation.value();
                        tables.add(name);
                    }
                }
                boolean filter = true;
                if (tables.size() > 0) {
                    if (tables.contains(tableName)) {
                        filter = false;
                    }
                }
                return filter;
            }
        }));
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return interceptor;
    }

    /**
     * 扫描包体
     *
     * @return
     */
    private List<String> getTableName() {
        List<String> tables = new ArrayList<>();
        Reflections reflections = new Reflections("com.bzfar.entity");
        Set<Class<?>> classesList = reflections.getTypesAnnotatedWith(TableProxy.class);
        for (Class clazz : classesList) {
            String name = clazz.getSimpleName();
            name = name.substring(0, 1).toLowerCase() + name.substring(1);
            name = name.replaceAll("([A-Z]+)", "_$1").toLowerCase();
            tables.add(name);
        }
        return tables;
    }

    /**
     * 扫描包体获取需要拼接的类名
     *
     * @return
     */
    private List<String> getSuffixTableNames() {
        List<String> tables = new ArrayList<>();
        Reflections reflections = new Reflections("com.bzfar.entity");
        Set<Class<?>> SuffixList = reflections.getTypesAnnotatedWith(TableSuffix.class);
        for (Class clazz : SuffixList) {
            String name = clazz.getSimpleName();
            name = name.substring(0, 1).toLowerCase() + name.substring(1);
            name = name.replaceAll("([A-Z]+)", "_$1").toLowerCase();
            tables.add(name);
        }
        return tables;
    }

    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum", "true");
        properties.setProperty("rowBoundsWithCount", "true");
        properties.setProperty("reasonable", "true");
        pageHelper.setProperties(properties);
        return pageHelper;
    }
}
