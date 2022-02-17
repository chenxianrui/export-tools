# export-tools

### 前言

因为在实习中发现有些导出excel的功能代码重复太多，比如要实现两个不同的导出表头内容excel就要实现两个接口，里面重复的代码太多了。因此萌发实现一个简单可复用
的excel导出组件。


### 使用
1.首先需要实现ExcelHandler类中的三个方法，这三个方法分别是拼装文件名，表头，表中具体数据。如下图所示：

![在这里插入图片描述](https://img-blog.csdnimg.cn/cda2e428942043f3961534566b8649a8.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBA6Z2e6buRaWk=,size_18,color_FFFFFF,t_70,g_se,x_16)

![在这里插入图片描述](https://img-blog.csdnimg.cn/a4c3acb5cff3432e875b1a7b3522aaf3.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBA6Z2e6buRaWk=,size_20,color_FFFFFF,t_70,g_se,x_16)

需要在ExcelHandler接口实现类上添加上咱们的自定义注解@ExcelFile作为标记是处理Excel文件数据的，然后我们需要实现该三种方法，填充文件名，表头和数据。如下图：

![在这里插入图片描述](https://img-blog.csdnimg.cn/ddefb9ded374408fa48c67d2b8cc105a.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBA6Z2e6buRaWk=,size_20,color_FFFFFF,t_70,g_se,x_16)

        方法中都有一个入参Object，该入参使用者可以传入任意类型的数据。比如需要通过一个String类型的字段去查询数据就可以传入String类型的入参，也可以传入一些集合作为入参。
        
        细心的你也发现了方法的返回类型有List<String>和List<List<String>>类型的，是为了配合excel文件的数据格式，因为表头只有一行，而数据是有多行的。
      
2.在需要实现的接口使用这三个方法即可，如下图：

![在这里插入图片描述](https://img-blog.csdnimg.cn/8fa1251075e74412be927b9e2efc7fc0.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBA6Z2e6buRaWk=,size_20,color_FFFFFF,t_70,g_se,x_16)

3.效果

输入对应的url即可下载对应的excel文件。

![在这里插入图片描述](https://img-blog.csdnimg.cn/5ab99305cb8f4e0589a8b4b25b68d1c6.png)

### jar包使用

这部分的代码也可以打包成可依赖的jar供其他服务使用。不过使用的服务需要再添加以下代码或依赖等步骤，否则会报错：

1.添加依赖包，在文件目录创建一个lib文件夹，将jar包拷贝至此

![在这里插入图片描述](https://img-blog.csdnimg.cn/2625175e3dd14c41a044c5f4ddfce032.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBA6Z2e6buRaWk=,size_19,color_FFFFFF,t_70,g_se,x_16)

2.pom.xml文件添加本地依赖，poi依赖和aop依赖

![在这里插入图片描述](https://img-blog.csdnimg.cn/05cfd82ec2fd440abf442a295cbfe137.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBA6Z2e6buRaWk=,size_20,color_FFFFFF,t_70,g_se,x_16)

3.因为该组件用到了aop编程，因此使用的服务需要在启动类中添加扫描对应的目录

![在这里插入图片描述](https://img-blog.csdnimg.cn/6e88af2b580b45a3b47ec8d628871ce3.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBA6Z2e6buRaWk=,size_20,color_FFFFFF,t_70,g_se,x_16)

4.剩下使用步骤如上面即可。