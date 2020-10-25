# 깃헙 API 사용을 위한 간단 정리

### Oauth2 플로우

[reference]

https://github.com/cheese10yun/springboot-oauth2/blob/master/docs/OAuth2-Grant.md#authorization-code-grant-type-%EB%B0%A9%EC%8B%9D

[scope]

https://developer.github.com/apps/building-oauth-apps/understanding-scopes-for-oauth-apps/

```
Authorization : token [AccessToken]
```



### repository 가져오기

[reference]

- https://developer.github.com/v3/repos/#list-repositories-for-the-authenticated-user
- https://docs.github.com/en/free-pro-team@latest/rest/reference/repos

```
https://api.github.com/user/repos?sort=create
```

 

| Name      | Description                                                  |
| --------- | ------------------------------------------------------------ |
| sort      | `created`, `updated`, `pushed`, `full_name`.                 |
| direction | Can be one of `asc` or `desc`. Default: when using `full_name`: `asc`, otherwise `desc` |
| page      | Page number of the results to fetch.                         |
| per_page  | Results per page (max 100)                                   |



### Commit 수집하기

[reference]

- https://developer.github.com/v3/repos/statistics/

- https://docs.github.com/en/free-pro-team@latest/rest/reference/repos#statistics-exclude-some-types-of-commits



저장소 통계를 계산하는 것은 비용이 많이 드는 작업이므로 가능하면 캐시 된 데이터를 반환하려고합니다. 리포지토리의 통계를 쿼리 할 때 데이터가 캐시되지 않은 경우 202 응답을 받게됩니다. 이러한 통계 컴파일을 시작하기 위해 백그라운드 작업도 시작됩니다. 작업이 완료 될 때까지 잠시 기다린 다음 요청을 다시 제출하십시오. 작업이 완료되면 해당 요청은 응답 본문에 통계가 포함 된 200 응답을 수신합니다.

To summarize:

- All statistics exclude merge commits.
- Contributor statistics also exclude empty commits.



##### organization data 가져오기

```
https://api.github.com/repos/study-mouse/studymouse-server/stats/contributors
```

author 이 list로 나온다. 해당하는 author 필터후에 weeks 가져오기

이때는 주별로 add / deletion / commit 만 나온다.



##### commit으로 가져오기

```
https://docs.github.com/en/free-pro-team@latest/rest/reference/repos#statistics-exclude-some-types-of-commits
```

statistics api를 가져오기엔 사람별로 보여주지 않고 organization 기준으로 가져옴.
개인 repository에서도 contribution이 되기 때문에 개인의 데이터가 아님