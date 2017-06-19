package chao.app.plugin.extension;

class DebugExtension {

    boolean debugOn = false

    String debugClass = null

    String MainClass = null

    String manifest = null


    @Override
    public String toString() {
        return "DebugExtension{" +
                "debugOn=" + debugOn +
                ", debugClass=" + debugClass +
                ", MainClass=" + MainClass +
                '}';
    }
}