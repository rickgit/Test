package edu.ptu.java.gradleplugin;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

import java.io.File;

class PublicPlugin implements Plugin<Project> {
    void apply(Project project) {
        project.afterEvaluate {
            if (project.plugins.hasPlugin("com.android.application")) {
                def android = project.extensions.getByName("android")
                android.applicationVariants.all {def variant ->
                    def processResourcesTask = project.tasks.getByName("process${variant.name.capitalize()}Resources")
                    if (processResourcesTask) {
//                        def properties = processResourcesTask.properties
//                        println("解析："+"process${variant.name.capitalize()}Resources")
//                        for (int i = 0; i < properties.size(); i++) {
//                            println("解析"+ properties[i])
//                        }
//                        def aaptOptions = processResourcesTask.aaptOptions
//                        File publicTxtFile = project.rootProject.file('public.txt')
//                        //public文件存在，则应用，不存在则生成
//                        if (publicTxtFile.exists()) {
//                            project.logger.error "${publicTxtFile} exists, apply it."
//                            //aapt2添加--stable-ids参数应用
//                            aaptOptions.additionalParameters("--stable-ids", "${publicTxtFile}")
//                        } else {
//                            project.logger.error "${publicTxtFile} not exists, generate it."
//                            //aapt2添加--emit-ids参数生成
//                            aaptOptions.additionalParameters("--emit-ids", "${publicTxtFile}")
//                        }
                    }
                }
            }
        }
    }
}