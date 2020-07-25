

## 配置
```java
android {


    defaultConfig {
        javaCompileOptions {
            annotationProcessorOptions {
                includeCompileClasspath true
                arguments = [ moduleName : project.getName() ]
            }
        }
    }
}
dependencies {
    annotationProcessor( project(':process'))
}
```