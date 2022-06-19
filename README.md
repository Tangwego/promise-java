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
            // TODO async 
            // resolve async result
            r.resolve(result);
        }).then((result) -> {
            // TODO success
            // handle success result
        }).exception((e) -> {
            // TODO exception
            // handle exceptions
        }).complete((result)->{
            // TODO async task complete
            // finally
        });

```