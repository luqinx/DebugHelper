package chao.app.plugin.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

public class TestTask extends DefaultTask {

    @TaskAction
    public void printTask() {
        println("----------------------->this is a test task")
    }

    @TaskAction
    public void printTask2() {
        println("----------------------->this ia another task")
    }
}