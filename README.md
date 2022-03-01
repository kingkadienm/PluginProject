# PluginProject
Android  占位式手写插件化


使用的时候只需要继承BaseActivity、BaseReceiver、BaseService 具体参考plugin_package 
app为宿主层 不关心插件里面的具体实现业务 通过Proxy XXX 来进行检测运行 每次运行插件的时候必须要先加载一次插件

 
