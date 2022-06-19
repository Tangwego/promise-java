# Promise-Java
#### A promise implementation for java based.

## Goal
```
1、To build a promise framework for java like javascript mode.
2、To reduce callback functions.
3、Help easy to use.
```

## Usage

example:
```
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

```