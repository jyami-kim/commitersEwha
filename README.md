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





### Custom Classes for OAuth2 Authentication

OAuth2 프로토콜은 CSRF 공격을 방지하기 위해 state 매개변수를 사용할 걸 권장한다.

authentication을 하는 중에, 어플리케이션은 이 state 파라미터를 authorization request에서 보낸다. 그리고 OAuth2 provider 이 파라미터를 변경하지 않은 채 OAuth2 콜백에서 리턴한다.

어플리케이션에서는 state 파라미터의 값과 OAuth2 provider가 초기에 보내준 값(oauth2_auth_request)이 같은지를 비교한다. 그래서 만약 그 둘이 매치가 되지 않으면 authentication 요청이 실패한다.

이 플로우를 타기위해서는 애플리케이션은 state 파라미터를 어디든지 반드시 저장해야한다. 그렇게 해서 나중에 그것과 OAuth2 provider에서 리턴받는 값과의 비교를 한다.

state처럼 우리는 redirect_uri도 짧은시간 살아있는 쿠키로 저장한다. 

``HttpCookieOAuth2AuthorizationRequestRepository.java``이 클래스는 authoriaztion request 안에있는 쿠키를 저장하고 그것을 검색한다.



### CustomOAuth2UserService

Spring Security의 ``DefaultOAuth2UserService`` 를 확장하고 ``loadUser()`` 메서드를 구현한다.

loadUser() 메서드는 OAuth2 provider로부터 access token이 얻은 후에 불린다.

OAuth2 provider로부터 유저의 세부사항이 먼저 fetch되는데 이때 유저가 우리의 데이터베이스에 이미 존재한다면(email) 해당 정보의 세부사항을 업데이트하고, 존재하지 않는다면 새로운 유저를 만든다.

