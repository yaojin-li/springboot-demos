package com.example.demo.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @Description: mybatis plus generator
 * --------------------------------------
 * @ClassName: CodeGenerator.java
 * @Date: 2021/3/7 21:40
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
public class CodeGenerator {

    /**
     * 当前项目地址
     */
    public static final String PROJECT_PATH = System.getProperty("user.dir");

    /**
     * 作者
     */
    public static final String AUTHOR = "lixj";

    /**
     * 数据库 URL
     */
    public static final String JDBC_MYSQL_URL = "jdbc:mysql://localhost:3306/employees?useUnicode=true&characterEncoding=UTF8&useSSL=false&serverTimezone=Asia/Shanghai";

    /**
     * 数据库驱动
     */
    public static final String JDBC_DRIVER_NAME = "com.mysql.cj.jdbc.Driver";

    /**
     * 数据库连接用户名
     */
    public static final String JDBC_USERNAME = "root";

    /**
     * 数据库连接密码
     */
    public static final String JDBC_PASSWORD = "123456";

    /**
     * 代码生成存放的包
     */
    public static final String PARENT_PACKAGE = "com.generator.code";

    /**
     * 联系方式
     */
    public static final String CONTACT = "lixj_zj@163.com";

    /**
     * 自定义配置
     */
    public static InjectionConfig injectionConfig() {
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
                Map<String, Object> map = new HashMap<String, Object>();
                // 自定义目标文件中的时间
                map.put("dateTime", getDateTime());
                // 自定义目标文件中的联系方式
                map.put("contact", CONTACT);
                this.setMap(map);
            }
        };

//        // 将xml生成到resource下面
//        // Velocity模板
//        String templatePath = "/templates/mapper.xml.vm";
//        // 自定义输出配置
//        List<FileOutConfig> focList = new ArrayList<>();
//        // 自定义配置会被优先输出
//        focList.add(new FileOutConfig(templatePath) {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
//                return PROJECT_PATH + "/src/main/resources/mapper/"
////                        + pc.getModuleName()
//                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
//            }
//        });
//        cfg.setFileOutConfigList(focList);

        return cfg;
    }

    public static void main(String[] args) {
        AutoGenerator generator = new AutoGenerator();
        // 全局变量配置
        generator.setGlobalConfig(globalConfig());

        // 数据源配置
        generator.setDataSource(dataSourceConfig());

        // 包配置
        generator.setPackageInfo(packageConfig());

        // 自定义配置
        generator.setCfg(injectionConfig());

        // 配置模板
        generator.setTemplate(templateConfig());

        // 数据库表配置
        generator.setStrategy(strategyConfig());

        // 模板引擎
        generator.setTemplateEngine(new VelocityTemplateEngine());
        generator.execute();
    }

    /**
     * 配置全局变量
     */
    public static GlobalConfig globalConfig() {
        GlobalConfig gc = new GlobalConfig();
        // 作者
        gc.setAuthor(AUTHOR);
        // 默认false
        gc.setSwagger2(true);
        // 设置生成文件标准名称
        gc.setEntityName("%s");
        gc.setControllerName("%sController");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        // 输出路径
        gc.setOutputDir(PROJECT_PATH + "/src/main/java");
        // 默认false，是否覆盖已生成文件
        gc.setFileOverride(true);
        // 默认true，是否打开输出目录
        gc.setOpen(false);
        // 默认false，是否开启二级缓存
        gc.setEnableCache(false);
        // 默认false
        gc.setBaseResultMap(true);
        // 时间策略 默认TIME_PACK
        gc.setDateType(DateType.TIME_PACK);
        // 默认false，基本列列表
        gc.setBaseColumnList(true);
        // 指定生成的主键类型
        gc.setIdType(IdType.AUTO);
        return gc;
    }

    /**
     * 数据源配置
     */
    public static DataSourceConfig dataSourceConfig() {
        DataSourceConfig dc = new DataSourceConfig();
        // 数据库信息查询 //默认mysql
        dc.setDbQuery(new MySqlQuery());
        // 数据库类型
        dc.setDbType(DbType.MYSQL);
        // 类型转换 默认mysql
        dc.setTypeConvert(new MySqlTypeConvert());
        dc.setUrl(JDBC_MYSQL_URL);
        dc.setDriverName(JDBC_DRIVER_NAME);
        dc.setUsername(JDBC_USERNAME);
        dc.setPassword(JDBC_PASSWORD);
        return dc;
    }

    /**
     * 包配置
     */
    public static PackageConfig packageConfig() {
        PackageConfig pc = new PackageConfig();
        // 代码生成到哪个包下面
        pc.setParent(PARENT_PACKAGE);
        // 所属模块名称
        // pc.setModuleName("");
        // 默认entity,controller,service,service.impl,mapper,mapper.xml
        // pc.setEntity("entity");
        return pc;
    }

    /**
     * 配置模板
     */
    public static TemplateConfig templateConfig() {
        TemplateConfig tc = new TemplateConfig();
        // 使用自定义模板生成实体类，模板路径配置：templates/entity.java
        // 默认加载templates目录下的模板文件，.vm 后缀不用加
        tc.setEntity("templates/entity.java");
        return tc;
    }

    /**
     * 数据库表配置
     */
    public static StrategyConfig strategyConfig() {
        StrategyConfig sc = new StrategyConfig();
        // 是否大写命名 默认false
        sc.setCapitalMode(false);
        // 是否跳过视图 默认false
        sc.setSkipView(true);
        // 表映射 驼峰命名
        sc.setNaming(NamingStrategy.underline_to_camel);
        // 字段映射 驼峰
        sc.setColumnNaming(NamingStrategy.underline_to_camel);
        // 是否使用 lombok 默认为false
        sc.setEntityLombokModel(true);
        // 是否生成@RestController 默认false
        sc.setRestControllerStyle(true);
        // 实体是否生成 serialVersionUID 默认true
        sc.setEntitySerialVersionUID(true);
        // 将mysql字段名生成静态变量 默认false
        sc.setEntityColumnConstant(true);
        // 表名。用，隔开
        sc.setInclude(scanner("表名，多个英文逗号分割").split(","));
        // 不需要生成  二选一
        // sc.setExclude("");
        // 生成字段注解 默认false
        sc.setEntityTableFieldAnnotationEnable(true);
        // 驼峰转连字符 默认false
        sc.setControllerMappingHyphenStyle(false);
        // 表中逻辑删除字段（status）。TODO 需要要与具体业务结合。
        sc.setLogicDeleteFieldName("status");
        return sc;
    }

    /**
     * 读取控制台输入表名
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    /**
     * 获取当前时间
     */
    private static String getDateTime() {
        LocalDateTime localDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return localDate.format(formatter);
    }

}
