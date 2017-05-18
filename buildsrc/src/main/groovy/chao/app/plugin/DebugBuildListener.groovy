package chao.app.plugin

import org.gradle.BuildListener
import org.gradle.BuildResult
import org.gradle.api.Task
import org.gradle.api.execution.TaskExecutionListener
import org.gradle.api.initialization.Settings
import org.gradle.api.invocation.Gradle
import org.gradle.api.tasks.TaskState;

class DebugBuildListener implements BuildListener,TaskExecutionListener {

    @Override
    void buildStarted(Gradle gradle) {
        println("------>buildStarted.")
    }

    @Override
    void settingsEvaluated(Settings settings) {
        println("------>settingsEvaluated : " + settings.toString())
    }

    @Override
    void projectsLoaded(Gradle gradle) {
        println("------>projectsLoaded!")
    }

    @Override
    void projectsEvaluated(Gradle gradle) {
        println("------>projectsEvaluated")
    }

    @Override
    void buildFinished(BuildResult buildResult) {
        println("------>buildFinished")
    }

    @Override
    void beforeExecute(Task task) {
        def log = new File(task.project.getBuildDir(),"buildlog")
        PrintWriter pw = new PrintWriter(log)
        if (task.name.contains("Manifest")) {
            pw.println "===========> " + task.name
        }
        pw.flush()
        pw.close()

    }

    @Override
    void afterExecute(Task task, TaskState taskState) {
//        println("------>afterExecute")
    }
}