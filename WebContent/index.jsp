<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 
	private int u_id;
	private String u_name;
	private String u_icon;
	private char u_sex;
	private String u_province;
	private String u_city;
	private String u_description;
	private Date u_register;
	private Date u_update;
 -->
<form method="post" action="addUser.action"  enctype="multipart/form-data">
用户名：<input type="text" name="user.name"><br/>
用户图标<input name="file" type="file" /><br/>
性别：男<input type="radio" name="user.sex" value="1"> 女<input type="radio" name="user.sex" value="0"><br/>

省份：<input type="text" name="user.province"><br/>
城市：<input type="text" name="user.city"><br/>
描述：
<textarea rows="5" cols="30" name="user.description"></textarea>
<br/>

<input type="submit"><br/>

</form>

<hr>

<!-- 
	private long pid;
	private User user;
	private String content;
	private String icon;
	private Date date; -->
<form method="post" action="addPost.action"  enctype="multipart/form-data">
发布show<input name="file" type="file" /><br/>
内容：
<textarea rows="5" cols="30" name="post.content"></textarea>
用户ID：<input type="text" value="" name="user.uid"><br/>
<br/>

<input type="submit"><br/>

</form>

<h1>评论</h1>
<hr>
<form method="post" action="nsjson/addComment.json">
评论内容：<textarea rows="5" cols="30" name="comments.comment"></textarea>
<br/>
<input type="submit"><br/>
</form>

<h1>点赞</h1>
<form method="post" action="nsjson/addLike.json">
该用户点赞：<input type="text" value="1" name="user.uid"><br/>
点赞的秀秀：<input type="text" value="42" name="post.pid"><br/>
<br/>
<input type="submit"><br/>
</form>
	
</body>
</html>