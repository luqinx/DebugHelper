package chao.app.plugin

import chao.app.plugin.extension.DebugExtension
import org.gradle.api.Project

/**
 * Created by chao on 2017/5/17.
 */
class DebugHelperVariant {

    def androidVariant
    String variantName //只有debug一种模式，release模式不允许使用DebugHelper插件
    String manifestPath
    DebugExtension configuration

    public DebugHelperVariant(Project project,Object androidVariant) {
        this.androidVariant = androidVariant
        this.configuration = project.debughelper
        this.variantName = androidVariant.name.capitalize()
        this.manifestPath = androidVariant.outputs.first().processManifest.manifestOutputFile
    }

    public DebugHelperVariant() {
    }

}
