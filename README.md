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

## 用user_id获取报名信息和考试信息
- /api/sign_up/exam?id=
- get
- return: List\<
    {
        "examName": ,
        "examDesc": ,
        "finish": ,
        "examId": ,
        "userId": 
    }\>

## 把考试状态置为结束
- /api/sign_up
- put
- 请求体格式：{"userId":,"examId":}
- return：
    - 成功：1
    - 失败：0

## 根据exam_id获取已经结束的考试
- /api/sign_up/finished?id=
- get
- return: List\<
    {
        "userId": ,
        "examId": ,
        "finish": "1",
        "userName": 
    }\>

## 根据user_id和exam_id获取答题情况
- /api/take_exam?uid=&eid=
- get
- return: List\<Answer\>

## 提交答卷
- /api/take_exam
- post
- 请求体格式：List\<Answer\>
    - Answer属性：
        - userId: Long
        - questionId: Long
        - answer: String
        - score (optional): Long
- 选择题会自动打分
- return: 
    - 成功： "success"
    - 失败： "failure"

## 修改题目得分
- /api/grade
- put
- 请求体格式：{"userId":Long,"questionId":Long,"score":Long}
- return: 
    - 成功： "success"
    - 失败： "failure"

## 根据user_id和exam_id获取考试分数
- /api/grade?uid=&eid=
- get
- return: [ 选择题分数, 解答题分数, 选择题总分, 解答题总分 ]
    - 如果有没打分的题目，受影响的返回值是null
## 根据exam_id获取考试详情
- /api/pay?examId=
- get
- return: Exam
    - 如果考试不存在return null
## 添加题目
- /api/question
- put
- 请求体格式：Question
    - Question属性：
        - Long id
        - Long examId
        - Long number
        - Boolean isChoice
        - String desc
        - String stdAnswer
        - Long maxScore
- 返回"success"或"failure"
