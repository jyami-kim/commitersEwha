#### 로그인 한 유저 조회 = [GET] /api/user/me

##### request Header

```json
Authentication: Bearer [Token]
```

##### response Body (UserInfoResponse.java)

```json
{
    "status": 200,
    "message": "현재 로그인한 유저 정보 조회 성공",
    "response": {
        "id": 1,
        "name": "김민정(엘텍공과대학 소프트웨어학부)",
        "email": "mjung1798@ewhain.net",
        "imageUrl": "https://lh3.googleusercontent.com/a-/AOh14Gio-AMzn40ulT31zywavhO_9ntlm3H1O1OQQFIY",
        "emailVerified": false,
        "role": "USER",
        "description": "개발자 쟈미",
        "major": "컴퓨터공학전공",
        "entranceYear": 0,
        "job": "backend-developer",
        "company": "kakao",
        "badges": [
            {
                "badgeId": 1,
                "title": "개발자",
                "description": "커밋터스 이화를 만든 개발자",
                "image": null
            }
        ],
        "devStacks": [
            {
                "devStackId": 1,
                "title": "JAVA",
                "color": "#000000",
                "backgroundColor": "\n#e9a6af"
            }
        ],
        "graduate": false
    }
}
```



#### 회원가입 추가 정보 입력 = [POST] /api/user

##### request Header

```json
Authentication: Bearer [Token]
```

##### request Body (UserSignupRequest.java)

```json
{
	"description" : "개발자 쟈미",
    "major" : "컴퓨터공학전공",
    "entraceYear" : 2017,
    "isGraduate" : false,
    "job" : "backend-developer",
    "company" : "kakao",
    "badgeIdList" : [1],
    "devStackIdList" : [1,5]
}
```



##### response Body (UserInfoResponse.java)

```json
{
    "status": 200,
    "message": "현재 로그인한 유저 정보 조회 성공",
    "response": {
        "id": 1,
        "name": "김민정(엘텍공과대학 소프트웨어학부)",
        "email": "mjung1798@ewhain.net",
        "imageUrl": "https://lh3.googleusercontent.com/a-/AOh14Gio-AMzn40ulT31zywavhO_9ntlm3H1O1OQQFIY",
        "emailVerified": false,
        "role": "USER",
        "description": "개발자 쟈미",
        "major": "컴퓨터공학전공",
        "entranceYear": 0,
        "job": "backend-developer",
        "company": "kakao",
        "badges": [
            {
                "badgeId": 1,
                "title": "개발자",
                "description": "커밋터스 이화를 만든 개발자",
                "image": null
            }
        ],
        "devStacks": [
            {
                "devStackId": 1,
                "title": "JAVA",
                "color": "#000000",
                "backgroundColor": "\n#e9a6af"
            }
        ],
        "graduate": false
    }
}
```



#### 존재하는 뱃지 조회 = [GET] /api/badge

##### responseBody (List<badge>)

```json
{
    "status": 200,
    "message": "현재 모든 뱃지 리스트 조회 성공",
    "response": [
        {
            "badgeId": 1,
            "title": "개발자",
            "description": "커밋터스 이화를 만든 개발자",
            "image": null
        }
    ]
}
```



#### 존재하는 개발 스택 조회 = [GET] /api/devStack

##### responseBody (List<devStack>)

```json
{
    "status": 200,
    "message": "현재 모든 DEV STACK 태그 리스 조회 성공",
    "response": [
        {
            "devStackId": 1,
            "title": "JAVA",
            "color": "#000000",
            "backgroundColor": "\n#e9a6af"
        },
        {
            "devStackId": 2,
            "title": "C",
            "color": "#000000",
            "backgroundColor": "\n#e9a6af"
        },
        {
            "devStackId": 3,
            "title": "Python",
            "color": "#000000",
            "backgroundColor": "\n#e9a6af"
        },
        {
            "devStackId": 4,
            "title": "Kotlin",
            "color": "#000000",
            "backgroundColor": "\n#e9a6af"
        }
    ]
}
```

