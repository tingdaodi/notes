# notes
MyBatis-Plus: XML 映射结果 <id colum="ID" property="id"/>
由于主键是唯一的，MyBatis 在转换的过程中，去掉重复项，只返回一条数据；
改为: <result colum="ID" property="id"/> 可解决问题
