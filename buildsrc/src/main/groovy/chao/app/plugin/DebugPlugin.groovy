package chao.app.plugin

import chao.app.plugin.extension.DebugExtension
import chao.app.plugin.tasks.ManifestTask
import chao.app.plugin.tasks.TestTask
import org.gradle.api.Plugin
import org.gradle.api.Project

class DebugPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {

        project.extensions.create("debughelper", DebugExtension)

        project.tasks.create("testTask",TestTask)

        project.gradle.addBuildListener(new DebugBuildListener())


        project.afterEvaluate {
            def android = project.extensions.android
            android.applicationVariants.all { variant ->
                def variantOutput = variant.outputs.first()
                DebugHelperVariant debugVariant = new DebugHelperVariant(project, variant)
                //一定要是在debug模式才能修改manifest，否则导致线上版本打开了debug界面就完完了

                ManifestTask manifestTask = project.tasks.create("manifestTask${debugVariant.variantName}", ManifestTask)
                if (debugVariant.variantName == "Debug") {
                    manifestTask.debugVariant = debugVariant

                    println "===============>" + variantOutput.processManifest.project.name

                    manifestTask.mustRunAfter variantOutput.processManifest
                    variantOutput.processResources.dependsOn manifestTask
                }
            }
        }
    }
}