package top.wdcc.promise;

/**
 * @author wavin
 * @date 2022/6/19
 */
public class PromiseFunctionTest {
    public static void main(String[] args) {
        Promise promise = new Promise((r) -> {
            System.out.println("context");
            System.out.println(1/0);
            //r.resolve("Fuck");
        }).then((result) -> {
            System.out.println("caller then");
            System.out.println(String.valueOf(result));
        }).exception((e) -> {
            System.out.println("caller capture - " + e.toString());
        }).complete((result)->{
            System.out.println("complete");
            System.out.println(result);
        });

    }
}
