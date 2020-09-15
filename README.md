```shell script
docker run -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=commiters -e MYSQL_USER=jyami -e MYSQL_PASSWORD=commiters --name commiters mysql
docker exec -i -t mysql_test bash
mysql -u root -p
```



### OAuth2 Login Flow

1. OAuth2 login 플로우는 맨처음 frontend client 에서 엔드포인트에 서 요청을 보내면서 시작된다.

   ``http://localhost:8080/oauth2/authorize/{provider}?redirect_uri=<redirect_uri_after_login>``

   - provider : google, facebook, github
   - redirect_uri : OAuth2 provider가 성공적으로 인증을 완료했을 때 redirect 할 URI를 지정한다. (OAuth2의 redirectUri 와는 다르다)

2. endpoint로 인증 요청을 받으면, Spring Security의 OAuth2 클라이언트는 user를 provider가 제공하는 AuthorizationUrl로 redirect 한다.

   Authorization request와 관련된 state는 ``authorizationRequestRepository`` 에 저장된다 (Security Config에 정의함)

   provider에서 제공한 AutorizationUrl에서 허용/거부가 정해진다.

   - 이때 만약 유저가 앱에 대한 권한을 모두 허용하면 provider는 사용자를 callback url로 redirect한다. (``http://localhost:8080/oauth2/callback/{provider}``)  그리고 이때 사용자 인증코드 (authroization code) 도 함께 갖고있다.
   - 만약 거부하면 callbackUrl로 똑같이 redirect 하지만 error가 발생한다.

3. Oauth2 에서의 콜백 결과가 에러이면 Spring Security는 ``oAuth2AuthenticationFailureHanlder`` 를 호출한다.  (Security Config에 정의함)

4. Oauth2 에서의 콜백 결과가 성공이고 사용자 인증코드 (authorization code)도 포함하고 있다면 Spring Security는 ``access_token`` 에 대한 authroization code를 교환하고, ``customOAuth2UserService`` 를 호출한다 (Security Config에 정의함)

5. ``customOAuth2UserService`` 는 인증된 사용자의 세부사항을 검색한 후에 데이터베이스에 Create를 하거나 동일 Email로 Update 하는 로직을 작성한다.

6. 마지막으로 ``oAuth2AuthenticationSuccessHandler`` 이 불리고 그것이 JWT authentication token을 만들고 queryString에서의 redirect_uri로 간다 (1번에서 client가 정의한 ) 이때 JWT token과 함께!





​    