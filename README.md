# 运行要求

mysql运行在3306端口，用户名root，密码123456，数据库名mydb

# api使用方法

## 登录
- /api/user/login?name=&pwd=
- get
- return:
    - 用户名密码正确：用户信息（User类）
    - 错误：返回null

## 注册
- /api/user/register
- post
- 请求体格式：{"name":"","password":"","type":"student/teacher"}
- return: 
    - 用户已存在：-1
    - 注册成功：1

## 获取考试列表
- /api/exam/all
- get
- return: List\<Exam\>

## 用exam_id获取题目列表
- /api/exam/content?id=
- get
- return: List\<Question\>

## 用户报名考试
- /api/sign_up
- post
- 请求体格式：{"userId":,"examId":}
- return: 
    - 成功：1
    - 失败：0

## 用user_id获取报名信息
- /api/sign_up?id=
- get
- return: List\<SignUpInfo\>

## 把考试状态置为结束
- /api/sign_up
- put
- 请求体格式：{"userId":,"examId":}
- return：
    - 成功：1
    - 失败：0

## 根据user_id和exam_id获取答题情况
- /api/take_exam?uid=&eid=
- get
- return: List\<Answer\>