# javafree-cloud-2022
 平台工程

## 打jar包启动
### 报错问题：
```
springboot jar启动报错(中文配置：org.yaml.snakeyaml.error.YAMLException: java.nio.charset.MalformedInputExcept
```

dea2020.1不报错，用jar包启动就报错了，检查yml文件中包含中文，去掉再启动就好了

或者

启动命令增加编码参数

```
java -Dfile.encoding=utf-8 -jar xxx.jar
或者
JAVA_TOOL_OPTIONS
-Dfile.encoding=UTF-8 -Duser.language=en
```

