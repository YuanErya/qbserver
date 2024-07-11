# qb聊天室服务端

本项目是qb聊天室的服务端项目，基于Springboot,WebSocket,Redis实现的。主要功能有：登录注册，验证码登录注册，聊天室实时聊天，定向私聊，无人时自动回复等功能。

## 项目介绍
这个项目是我研究websocket的时候来实现的一个小demo。
主要是基于SpringBoot构建的项目，登录注册，发送验证码等操作都是基于http请求实现的，实时聊天是基于WebSocket实现的。Redis在项目中充当的角色只是存储了验证码方便设置过期时间。原本打算是消息的存储也放在redis里面。但是因为一些原因不会再继续完善这个项目。
聊天室只有你一个人的时候会触发自动回复。
并且在逻辑上是实现了私聊的功能。通过规定的指令方式可以达到私聊的目的。

![image-20231130185725905](https://cdn.jsdelivr.net/gh/YuanErya/pictures@main/img/202311301857933.png)

![image-20231130190343403](https://cdn.jsdelivr.net/gh/YuanErya/pictures@main/img/202311301903437.png)

## 安装和运行

服务端需要配置数据的连接信息等：mysql连接信息，redis连接信息 。

![image-20231130205018599](https://cdn.jsdelivr.net/gh/YuanErya/pictures@main/img/202311302050707.png)

还需要配置smtp服务器相关信息。

![image-20231130205055943](https://cdn.jsdelivr.net/gh/YuanErya/pictures@main/img/202311302050974.png)

不配置smtp相关信息，服务是能够正常启动的，生成的验证码可以在redis中看到。

## 联系方式

如果有兴趣了解这个小demo也可以联系我

