package chao.app.groovy

public class Test {

    public static void main(def args) {
        Map map = [:]
        map.a = 100
        map.b = "hello"
        map.c = "chao"


        List list = [1,2,3,4,5]
        def ret = list.find {
            print it
            return it == 2
        }

        println ret
    }
}