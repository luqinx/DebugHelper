### protocol

1. protocol为什么不直接放入DebugHelper模块而是另起一个模块

DebugHelper模块是在调试时也就是当项目的Build Variants为debug时才引入，gradle中使用debugCompile添加依赖
```
...
compile project(xxx)

debugCompile project(':DebugHelper')
...
```
这样添加的好处是发布正式版本的包时，DebugHelper不会被编译到正式包中。 protocol模块是接口模块，只是保留了DebugHelper相关接口。