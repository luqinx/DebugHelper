package chao.app.plugin

import org.gradle.api.Project

class PluginUtil {

    private static final String BUILD_DIR = "debughelper"

    private static String buildDir

    public static boolean isAndroidModule(Project project) {
        return project.plugins.findPlugin("com.android.application") || project.plugins.findPlugin("com.android.library")
    }

    public static File getBuildDir(Project project) {
        if (buildDir) {
            return buildDir
        }
        return new File(project.getBuildDir(),BUILD_DIR)
    }

    public static Map<String,File> getProjectManifests(Project project) {
        if (!isAndroidModule(project)) {
            return null
        }
        project.android.buildTypes.each {
//            println "==========> " + it.name.capitalize()
        }
        return null
    }
}