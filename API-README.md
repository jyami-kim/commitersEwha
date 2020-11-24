[GET] /api/badge

    {
      "status": -200,
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

[GET] /api/devStack

    {
      "status": -200,
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

[GET] /api/github/{autherId}

require : login

    {
        "status": -200,
        "message": "나열된 커밋맵 찾기 성공",
        "response": [
          {
            "commitDate":"2020-11-22",
            "commitCount":2
          },
          {
            "commitDate":"2020-11-21",
            "commitCount":2
          }
        ]
    }

[GET] /api/github/commitMap/{autherId}

require : login

    {
        "status": -200,
        "message": "github 기본정보 조회 성공",
        "response": {
            "name": "KimMinjeong",
            "email": "mor2222@naver.com",
            "imageUrl": "https://avatars1.githubusercontent.com/u/26458200?v=4",
            "providerId": 26458200,
            "authorId": "mjung1798"
        }
    }

[GET] /api/post/{postId}

require: login

    {
        "status": -200,
        "message": "커뮤니티 포스트 자세하게 조회하기 성공",
        "response": {
            "postId": 2,
            "title": "두번째 게시글",
            "detail": "게시글 내용 22222",
            "userId": 1,
            "userName": "김민정(엘텍공과대학 소프트웨어학부)",
            "category": "EMPLOYMENT",
            "hit": 13,
            "createdDate": "2020-09-30T21:38:34",
            "modifiedDate": "2020-10-03T18:53:33",
            "commentResponses": [
                {
                    "commentId": 15,
                    "content": "대댓글 테스트",
                    "parent": -1,
                    "postId": 2,
                    "userId": 1,
                    "createdDate": "2020-10-03T18:44:28",
                    "modifiedDate": "2020-10-03T18:44:28",
                    "commentLikeSize": 0
                }
            ],
            "hashTags": [
                "hello",
                "java",
                "develope"
            ],
            "postLikeSize": null
        }
    }

[GET] /api/posts

    {
        "status": -200,
        "message": "커뮤니티 포스트 리스트 조회 성공",
        "response": {
            "content": [
                {
                    "postId": 2,
                    "title": "두번째 게시글",
                    "detail": "게시글 내용 22222",
                    "userId": 1,
                    "userName": "김민정(엘텍공과대학 소프트웨어학부)",
                    "category": "EMPLOYMENT",
                    "hit": 13,
                    "createdDate": "2020-09-30T21:38:34",
                    "modifiedDate": "2020-11-24T21:55:54",
                    "commentResponses": [],
                    "hashTags": [
                        "hello",
                        "java",
                        "develope"
                    ],
                    "postLikeSize": 1
                },
                {
                    "postId": 3,
                    "title": "첫번째 게시글",
                    "detail": "게시글 내용",
                    "userId": 1,
                    "userName": "김민정(엘텍공과대학 소프트웨어학부)",
                    "category": "EMPLOYMENT",
                    "hit": 0,
                    "createdDate": "2020-09-30T21:38:35",
                    "modifiedDate": "2020-09-30T21:38:35",
                    "commentResponses": [],
                    "hashTags": [],
                    "postLikeSize": 0
                },
                {
                    "postId": 4,
                    "title": "첫번째 게시글",
                    "detail": "게시글 내용",
                    "userId": 1,
                    "userName": "김민정(엘텍공과대학 소프트웨어학부)",
                    "category": "EMPLOYMENT",
                    "hit": 0,
                    "createdDate": "2020-09-30T21:38:36",
                    "modifiedDate": "2020-09-30T21:38:36",
                    "commentResponses": [],
                    "hashTags": [],
                    "postLikeSize": 0
                },
                {
                    "postId": 5,
                    "title": "제목",
                    "detail": "세부사항",
                    "userId": 2,
                    "userName": "jyami",
                    "category": "ADVERTISING",
                    "hit": 1,
                    "createdDate": "2020-10-02T16:25:11",
                    "modifiedDate": "2020-10-02T19:06:26",
                    "commentResponses": [],
                    "hashTags": [
                        "java"
                    ],
                    "postLikeSize": 0
                },
                {
                    "postId": 6,
                    "title": "제목",
                    "detail": "세부사항",
                    "userId": 2,
                    "userName": "jyami",
                    "category": "ADVERTISING",
                    "hit": 0,
                    "createdDate": "2020-10-02T16:42:20",
                    "modifiedDate": "2020-10-02T16:42:20",
                    "commentResponses": [],
                    "hashTags": [
                        "java",
                        "python"
                    ],
                    "postLikeSize": 0
                },
                {
                    "postId": 7,
                    "title": "제목",
                    "detail": "세부사항",
                    "userId": 2,
                    "userName": "jyami",
                    "category": "ADVERTISING",
                    "hit": 0,
                    "createdDate": "2020-10-02T16:50:49",
                    "modifiedDate": "2020-10-02T16:50:49",
                    "commentResponses": [],
                    "hashTags": [
                        "spring"
                    ],
                    "postLikeSize": 0
                },
                {
                    "postId": 8,
                    "title": "제목",
                    "detail": "세부사항",
                    "userId": 2,
                    "userName": "jyami",
                    "category": "ADVERTISING",
                    "hit": 0,
                    "createdDate": "2020-10-02T16:51:05",
                    "modifiedDate": "2020-10-02T16:51:05",
                    "commentResponses": [],
                    "hashTags": [],
                    "postLikeSize": 0
                },
                {
                    "postId": 9,
                    "title": "제목",
                    "detail": "세부사항",
                    "userId": 2,
                    "userName": "jyami",
                    "category": "ADVERTISING",
                    "hit": 0,
                    "createdDate": "2020-10-02T16:52:05",
                    "modifiedDate": "2020-10-02T16:52:05",
                    "commentResponses": [],
                    "hashTags": [],
                    "postLikeSize": 0
                }
            ],
            "pageable": {
                "sort": {
                    "sorted": true,
                    "unsorted": false,
                    "empty": false
                },
                "offset": 0,
                "pageNumber": 0,
                "pageSize": 10,
                "paged": true,
                "unpaged": false
            },
            "totalPages": 1,
            "totalElements": 8,
            "last": true,
            "numberOfElements": 8,
            "first": true,
            "sort": {
                "sorted": true,
                "unsorted": false,
                "empty": false
            },
            "size": 10,
            "number": 0,
            "empty": false
        }
    }

[GET] /api/scrap/baekjoon/rank

    {
      "status": -200,
      "message": "백준 사이트 이화여대 랭킹 조회 성공",
      "response": {
        "rank": 70,
        "universityName": "이화여자대학교",
        "link": "https://www.acmicpc.net//school/ranklist/352",
        "totalNumber": 212,
        "collectNumber": 1296,
        "submitNumber": 34708,
        "rate": "47.383%"
      }
    }

[GET] /api/scrap/ewha/job

    {
      "status": -200,
      "message": "이화 홈페이지 취업 게시물 결과 조회 성공",
      "response": {
        "jobList": [
          {
            "number": 334,
            "title": "[한국인터넷교육방송]",
            "link": "http://cms.ewha.ac.kr/user/boardList.action?command=view&page=1&boardId=1737001&boardSeq=35617517&year=2020&month=11&startDate=&endDate=",
            "newBadge": true
          },
          {
            "number": 333,
            "title": "[과학기술정보통신부] 2021 소프트웨어 산업 전망 컨퍼런스",
            "link": "http://cms.ewha.ac.kr/user/boardList.action?command=view&page=1&boardId=1737001&boardSeq=35594611&year=2020&month=11&startDate=&endDate=",
            "newBadge": true
          },
          {
            "number": 332,
            "title": "[대웅그룹] 2021 상반기 대웅그룹 인턴십 채용",
            "link": "http://cms.ewha.ac.kr/user/boardList.action?command=view&page=1&boardId=1737001&boardSeq=35589832&year=2020&month=11&startDate=&endDate=",
            "newBadge": true
          },
          {
            "number": 331,
            "title": "[패스트캠퍼스] 2021년 동계 슈퍼루키 인턴십(채용 연계형) 공고",
            "link": "http://cms.ewha.ac.kr/user/boardList.action?command=view&page=1&boardId=1737001&boardSeq=35542216&year=2020&month=11&startDate=&endDate=",
            "newBadge": true
          },
          {
            "number": 330,
            "title": "아마존웹서비시즈코리아(AWS) 채용공고",
            "link": "http://cms.ewha.ac.kr/user/boardList.action?command=view&page=1&boardId=1737001&boardSeq=35539229&year=2020&month=11&startDate=&endDate=",
            "newBadge": true
          },
          {
            "number": 329,
            "title": "리브레오피스(LibreOffice) 한일 온라인 세미나",
            "link": "http://cms.ewha.ac.kr/user/boardList.action?command=view&page=1&boardId=1737001&boardSeq=35532881&year=2020&month=11&startDate=&endDate=",
            "newBadge": false
          },
          {
            "number": 328,
            "title": "[Hitachi-LG Data Storage] 시장조사 인턴(체험형)",
            "link": "http://cms.ewha.ac.kr/user/boardList.action?command=view&page=1&boardId=1737001&boardSeq=35532866&year=2020&month=11&startDate=&endDate=",
            "newBadge": false
          },
          {
            "number": 327,
            "title": "화웨이코리아 ICT 챌린지 2020 모집 안내",
            "link": "http://cms.ewha.ac.kr/user/boardList.action?command=view&page=1&boardId=1737001&boardSeq=35513969&year=2020&month=11&startDate=&endDate=",
            "newBadge": false
          },
          {
            "number": 326,
            "title": "[매일유업] IT 인턴채용 안내",
            "link": "http://cms.ewha.ac.kr/user/boardList.action?command=view&page=1&boardId=1737001&boardSeq=35467942&year=2020&month=11&startDate=&endDate=",
            "newBadge": false
          },
          {
            "number": 325,
            "title": "[아이티센그룹] 신입 IT엔지니어 공개 채용 안내",
            "link": "http://cms.ewha.ac.kr/user/boardList.action?command=view&page=1&boardId=1737001&boardSeq=35467727&year=2020&month=11&startDate=&endDate=",
            "newBadge": false
          },
          {
            "number": 324,
            "title": "[TmaxGroup/R&D] 티맥스 R&D 개발연구원 신입/경력 모집",
            "link": "http://cms.ewha.ac.kr/user/boardList.action?command=view&page=1&boardId=1737001&boardSeq=35400150&year=2020&month=11&startDate=&endDate=",
            "newBadge": false
          },
          {
            "number": 323,
            "title": "[현대건설] 2021년 현대건설 신입사원 채용 (~11/23(월))",
            "link": "http://cms.ewha.ac.kr/user/boardList.action?command=view&page=1&boardId=1737001&boardSeq=35392982&year=2020&month=11&startDate=&endDate=",
            "newBadge": false
          },
          {
            "number": 322,
            "title": "나도 할 수 있다! 아마존 인턴십 & 글로벌 기업 취업!!",
            "link": "http://cms.ewha.ac.kr/user/boardList.action?command=view&page=1&boardId=1737001&boardSeq=35343770&year=2020&month=11&startDate=&endDate=",
            "newBadge": false
          },
          {
            "number": 321,
            "title": "[서울시 X OPENKNOWL] 서울형 강소기업 탐방 프로그램",
            "link": "http://cms.ewha.ac.kr/user/boardList.action?command=view&page=1&boardId=1737001&boardSeq=35313989&year=2020&month=11&startDate=&endDate=",
            "newBadge": false
          },
          {
            "number": 320,
            "title": "한국공학한림원 차세대공학리더 YEHS 및 NAEK 포럼 참가자 모집",
            "link": "http://cms.ewha.ac.kr/user/boardList.action?command=view&page=1&boardId=1737001&boardSeq=35284593&year=2020&month=11&startDate=&endDate=",
            "newBadge": false
          }
        ]
      }
    }

[GET] /api/scrap/ewha/notification

    {
      "status": -200,
      "message": "이화 홈페이지 공지사항 사이트 결과 조회 성공",
      "response": {
        "jobList": [
          {
            "number": 2772,
            "title": "네이버커넥트 부스트캠프 AI Tech 교육생 모집",
            "type": "[일반]",
            "link": "http://cms.ewha.ac.kr/user/boardList.action?command=view&page=1&boardId=1631514&boardSeq=35610936&year=2020&month=11&startDate=&endDate=",
            "newBadge": true
          },
          {
            "number": 2771,
            "title": "2020 겨울학기(12~2월) 대학원영어특강 개강안내",
            "type": "[대학원]",
            "link": "http://cms.ewha.ac.kr/user/boardList.action?command=view&page=1&boardId=1631514&boardSeq=35596704&year=2020&month=11&startDate=&endDate=",
            "newBadge": true
          },
          {
            "number": 2770,
            "title": "2021 소프트웨어 산업 전망 컨퍼런스",
            "type": "[일반]",
            "link": "http://cms.ewha.ac.kr/user/boardList.action?command=view&page=1&boardId=1631514&boardSeq=35594351&year=2020&month=11&startDate=&endDate=",
            "newBadge": true
          },
          {
            "number": 2769,
            "title": "SW중심대학 소개영상 이벤트",
            "type": "[일반]",
            "link": "http://cms.ewha.ac.kr/user/boardList.action?command=view&page=1&boardId=1631514&boardSeq=35586166&year=2020&month=11&startDate=&endDate=",
            "newBadge": true
          },
          {
            "number": 2768,
            "title": "2021학년도 전공변경 시행안내",
            "type": "[학부]",
            "link": "http://cms.ewha.ac.kr/user/boardList.action?command=view&page=1&boardId=1631514&boardSeq=35514627&year=2020&month=11&startDate=&endDate=",
            "newBadge": true
          },
          {
            "number": 2767,
            "title": "<2020 공학페스티벌> 안내",
            "type": "[일반]",
            "link": "http://cms.ewha.ac.kr/user/boardList.action?command=view&page=1&boardId=1631514&boardSeq=35513984&year=2020&month=11&startDate=&endDate=",
            "newBadge": false
          },
          {
            "number": 2766,
            "title": "2020학년도 비사범대 교직과정 이수 신청 안내",
            "type": "[학부]",
            "link": "http://cms.ewha.ac.kr/user/boardList.action?command=view&page=1&boardId=1631514&boardSeq=35452507&year=2020&month=11&startDate=&endDate=",
            "newBadge": false
          },
          {
            "number": 2765,
            "title": "[일대원 학생회] 총장선거와 학생회선거를 위한 개인정보 수집 및 이용 (오늘까지)",
            "type": "[대학원]",
            "link": "http://cms.ewha.ac.kr/user/boardList.action?command=view&page=1&boardId=1631514&boardSeq=35391164&year=2020&month=11&startDate=&endDate=",
            "newBadge": false
          },
          {
            "number": 2764,
            "title": "2020년 10월31일 전국 여고생 프로그래밍 경진 대회 수상자 발표",
            "type": "[일반]",
            "link": "http://cms.ewha.ac.kr/user/boardList.action?command=view&page=1&boardId=1631514&boardSeq=35293573&year=2020&month=11&startDate=&endDate=",
            "newBadge": false
          },
          {
            "number": 2763,
            "title": "2020 ICT콕 대한민국바로알리기 AI 공모전",
            "type": "[일반]",
            "link": "http://cms.ewha.ac.kr/user/boardList.action?command=view&page=1&boardId=1631514&boardSeq=35284579&year=2020&month=11&startDate=&endDate=",
            "newBadge": false
          },
          {
            "number": 2762,
            "title": "YEHS 및 제252회 NAEK Forum 참가자 모집 안내",
            "type": "[학부]",
            "link": "http://cms.ewha.ac.kr/user/boardList.action?command=view&page=1&boardId=1631514&boardSeq=35283130&year=2020&month=11&startDate=&endDate=",
            "newBadge": false
          },
          {
            "number": 2761,
            "title": "[마감11/9 월요일] 이대부속초등학교 SW⸱AI 창의융합 해커톤 행사 교육봉사자 모집 공고",
            "type": "",
            "link": "http://cms.ewha.ac.kr/user/boardList.action?command=view&page=1&boardId=1631514&boardSeq=35192452&year=2020&month=11&startDate=&endDate=",
            "newBadge": false
          },
          {
            "number": 2760,
            "title": "2020학년도 전기(2021년 2월) 졸업예정여부 1차 조회 및 졸업의사 신청 관련 안내",
            "type": "[학부]",
            "link": "http://cms.ewha.ac.kr/user/boardList.action?command=view&page=1&boardId=1631514&boardSeq=35186579&year=2020&month=11&startDate=&endDate=",
            "newBadge": false
          },
          {
            "number": 2759,
            "title": "20-2학기 대학원 종(통)합시험 시행 및 신청 안내",
            "type": "[대학원]",
            "link": "http://cms.ewha.ac.kr/user/boardList.action?command=view&page=1&boardId=1631514&boardSeq=35166166&year=2020&month=11&startDate=&endDate=",
            "newBadge": false
          },
          {
            "number": 2758,
            "title": "2020-2 대학원 각 학위과정 영어시험 성적표 제출 안내",
            "type": "[대학원]",
            "link": "http://cms.ewha.ac.kr/user/boardList.action?command=view&page=1&boardId=1631514&boardSeq=35165903&year=2020&month=11&startDate=&endDate=",
            "newBadge": false
          }
        ]
      }
    }
