import com.intellij.database.model.DasTable
import com.intellij.database.model.ObjectKind
import com.intellij.database.util.Case
import com.intellij.database.util.DasUtil
 
import java.util.Random

import java.time.LocalDateTime

/*
 * Available context bindings:
 *   SELECTION   Iterable<DasObject>
 *   PROJECT     project
 *   FILES       files helper
 *   update by   MJ
 */

packageName = "com.javafree.cloud.admin.entity" //包名
tablePrefix = "sys_org_"            //表前缀
pkName = "id"                     //主键名
extendClass = ""  //继承的类  extends SqlParam
typeMapping = [
        (~/(?i)int/)                      : "Integer",
        (~/(?i)float|double|decimal|real/): "Double",
        (~/(?i)datetime|timestamp/)       : "Date",
        (~/(?i)date/)                     : "Date",
        (~/(?i)time/)                     : "Date",
        (~/(?i)/)                         : "String"
]

FILES.chooseDirectoryAndSave("Choose directory", "Choose where to store generated files") { dir ->
    SELECTION.filter { it instanceof DasTable }.each { generate(it, dir) }
}

//生成文件
def generate(table, dir) {
    def className = javaName(table.getName().replace(tablePrefix, ""), true)
    def fields = calcFields(table)
    new File(dir, className + ".java").withPrintWriter { out -> generate(out, className, fields, table) }//输出实体类
}

//生成类的内容
def generate(out, className, fields, table) {
    //包名
    out.println "package $packageName;"
    out.println ""

    //引入的包
    //out.println "import lombok.Getter;"
    //out.println "import lombok.Setter;"

    out.println "import java.io.Serializable;"
    out.println "import javax.persistence.Entity;"
    out.println "import javax.persistence.Table;"
    out.println "import javax.persistence.Column;"
    out.println "import javax.persistence.Id;"
    out.println "import javax.persistence.GeneratedValue;"    
    out.println "import org.hibernate.annotations.GenericGenerator;"
    out.println "import java.util.Date;"
    out.println "import io.swagger.annotations.ApiModel;"
    out.println "import io.swagger.annotations.ApiModelProperty;"
    

    //类的注释
    out.println "/**\n" +
            " * @Description:    " + table.getComment() + "\n" +
            " * @Database:   表名为 " + table.getName() + "\n" +
            " */"
    out.println ""

    //类的注解
    out.println "@Entity"
    //out.println "@Getter"//输出注解Get
    //out.println "@Setter"//输出注解Set
    out.println "@Table(name =\"" + table.getName() + "\")"
    //swagger ApiModel
    out.println "@ApiModel(value = \" "+className+"对象 \", description = \""+ table.getComment()+"\")"
    out.println "public class $className $extendClass implements Serializable{"
    out.println ""

    //类的序列化ID
    out.println "\tprivate static final Long serialVersionUID = " + new Random().nextLong() + "L;"
    out.println ""

    //输出字段注释和字段
    fields.each() {
        if (isNotEmpty(it.commoent)) {
            out.println "\t/**"
            out.println "\t * ${it.commoent}"
            out.println "\t */"
        }
        //输出swagger 注解
        if (isNotEmpty(it.commoent)) {
            out.println "\t@ApiModelProperty(\"${it.commoent}\")"
        }
        if (it.annos != "") out.println "  ${it.annos}"
        out.println "\tprivate ${it.type} ${it.name};"
        out.println ""
    }
    //输出字段的get set方法，若使用了lombok的set get注解，可注释此处
    fields.each() {
        out.println "\tpublic ${it.type} get${it.name.capitalize()}() {"
        out.println "\t\treturn ${it.name};"
        out.println "\t}"
        out.println ""
        out.println "\tpublic void set${it.name.capitalize()}(${it.type} ${it.name}) {"
        out.println "\t\tthis.${it.name} = ${it.name};"
        out.println "\t}"
        out.println ""
    }
    out.println "}"
}

//字段的注解
def calcFields(table) {
    DasUtil.getColumns(table).reduce([]) { fields, col ->
        def spec = Case.LOWER.apply(col.getDataType().getSpecification())
        def typeStr = typeMapping.find { p, t -> p.matcher(spec).find() }.value
        def comm = [
                name    : changeStyle(javaName(col.getName(), false), false),
                type    : typeStr,
                commoent: col.getComment(),
                annos   : "\t@Column(name = \"" + col.getName() + "\")"
        ]
        //判断是否主键
        if (pkName.equals(Case.LOWER.apply(col.getName()))) {
            comm.annos = "\t@Id\n"
            comm.annos +="\t@GenericGenerator(name = \"jpa-uuid\", strategy = \"uuid\")\n"
            comm.annos += "\t@GeneratedValue(generator = \"jpa-uuid\")\n"
            comm.annos += "\t@Column(name = \"" + col.getName() + "\",length = 32)"
        }
        fields += [comm]
    }
}

//命名风格（使用驼峰命名法）
def javaName(str, capitalize) {
    def s = com.intellij.psi.codeStyle.NameUtil.splitNameIntoWords(str)
            .collect { Case.LOWER.apply(it).capitalize() }
            .join("")
            .replaceAll(/[^\p{javaJavaIdentifierPart}[_]]/, "_")
    capitalize || s.length() == 1 ? s : Case.LOWER.apply(s[0]) + s[1..-1]
}

//验证非空
def isNotEmpty(content) {
    return content != null && content.toString().trim().length() > 0
}

static String changeStyle(String str, boolean toCamel) {
    if (!str || str.size() <= 1)
        return str

    if (toCamel) {
        String r = str.toLowerCase().split('_').collect { cc -> Case.LOWER.apply(cc).capitalize() }.join('')
        return r[0].toLowerCase() + r[1..-1]
    } else {
        str = str[0].toLowerCase() + str[1..-1]
        return str.collect { cc -> ((char) cc).isUpperCase() ? '_' + cc.toLowerCase() : cc }.join('')
    }
}
