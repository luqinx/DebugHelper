package chao.app.plugin.tasks

import chao.app.plugin.DebugHelperVariant
import groovy.xml.Namespace
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
/**
 *
 */
class ManifestTask extends DefaultTask {

    private static final String LAUNCHER_CATEGORY_LAUNCHER_PATTERN = "<category\\s+android:name=\"android.intent.category.LAUNCHER\"\\s+/>"
    private static final String LAUNCHER_ACTION_MAIN_PATTERN = "<action\\s+android:name=\"android.intent.action.MAIN\"\\s+/>"


    public static final String MANIFEST_LAUNCHER_CATEGORY = "android.intent.category.LAUNCHER"
    public static final String MANIFEST_LAUNCHER_ACTION_MAIN = "android.intent.action.MAIN"

    public static final String MANIFEST_NAME_CATEGORY = "category"

    private static final String TEMP_REPLACEMENT = "<temp>this is a temporary replacement</temp>"


    static final String PLUGIN_LAUNCHER_ACTIVITY = "chao.app.protocal.PluginLauncherActivity"
//    static final String PLUGIN_LAUNCHER_ACTIVITY = "chao.app.uidebug.DebugSupportFragmentContainer" //todo 临时测试


    DebugHelperVariant debugVariant


    public ManifestTask() {
        group = "debughelper"
    }

    @TaskAction
    public void modifyLauncherAttribute() {
        if (!project.plugins.findPlugin("com.android.application") || project.plugins.findPlugin("com.android.library")) {
            return
        }
        if (!debugVariant.configuration.debugOn) {
            return
        }

        def ns = new Namespace("http://schemas.android.com/apk/res/android", "android")

        File output = removeManifestLauncherCategory(debugVariant.manifestPath)
        def root = new XmlParser().parse(output)
        Node launcherActivity = root.application[0].find {
            it.attributes()[ns.name] == PLUGIN_LAUNCHER_ACTIVITY
        }
        Node intentFilterNode = new Node(launcherActivity,"intent-filter")
        Map map = [:]
        map."android:name" = MANIFEST_LAUNCHER_ACTION_MAIN
        new Node(intentFilterNode,"action",map)

        map = [:]
        map."android:name" = MANIFEST_LAUNCHER_CATEGORY
        new Node(intentFilterNode,"category",map)


//        File output = removeManifestLauncherCategory(variant.manifestPath)
//        def root = new XmlParser().parse(output)
//
//        if (variant.variantName.equalsIgnoreCase("debug") && project.name != "DebugHelper") {
//            //非debug模式下，
//            root.application[0].findAll {
//                it.'intent-filter' && it.'intent-filter'.temp
//            }.each {
//                it.'intent-filter'.findAll {
//                    it.temp != null
//                }.each{
//                    it.remove(it.temp)
//                    Map map = [:]
//                    map."android:name=" = MANIFEST_LAUNCHER_CATEGORY
//                    it.appendNode(MANIFEST_NAME_CATEGORY, map)
//                }
//            }
//
//
//        } else {
//            //如果是debug模式，且是DebugHelper的Launcher,添加Launcher Category
//
//            def launcherActivity = root.application[0].find {
//                it.attributes()[ns.name] == "chao.app.debug.AnnotationLauncher"
//            }
//
//            Node intentFilter = launcherActivity.'intent-filter'[0]
//            intentFilter.remove(intentFilter.temp)
//
//            Map map = [:]
//            map."android:name=" = MANIFEST_LAUNCHER_CATEGORY
//            intentFilter.appendNode(MANIFEST_NAME_CATEGORY, map)
//
//        }
        def printer = new XmlNodePrinter(new PrintWriter(output, "utf-8"))
        printer.preserveWhitespace = true
        printer.print(root)

    }


    private File removeManifestLauncherCategory(String manifest) {
        if (manifest == null) {
            manifest = debugVariant.manifestPath
        }

        String newManifest = new File(manifest).getText("utf-8").replaceAll(LAUNCHER_CATEGORY_LAUNCHER_PATTERN, "").replaceAll(LAUNCHER_ACTION_MAIN_PATTERN,"")
        File newFile = new File(manifest);
        FileOutputStream fos = new FileOutputStream(newFile)
        fos.write(newManifest.getBytes())
        fos.flush()
        fos.close()
        return newFile
    }

}