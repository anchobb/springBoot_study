##### --------------------------
### Start.spring.io 파일받기
##### --------------------------
![제목 없음](https://github.com/EDU-MY-GROUP/EDU_SPRING_BOOT/assets/84259104/d2459980-0822-42e0-a2d7-81c71d571c64)
##### [참고] https://milenote.tistory.com/176#--%--spring%--boot%--%EC%--%A-%EC%A-%--

##### --------------------------
### Gradle Options 설정
##### -------------------------- 
##### file -> Settings -> gradle 검색 -> Build And run using : IntelliJ IDEA , Run tests using : IntelliJ IDEA 선택
##### 다시 gradle 동작
##### -------------------------- 
### JDK 설정
##### -------------------------- 
#####  project 우클릭 ->  "Open Module Settings" -> 
#####  project -> SDK -> Java Version 변경
#####  SDKS -> JDK home path 설정 확인
##### --------------------------
### UTF-8 설정
##### --------------------------
##### [참고] https://eroul-ri.tistory.com/10
##### --------------------------
### 자동 import 설정
##### --------------------------
##### [참고] https://tychejin.tistory.com/340
##### --------------------------
### JSP설정
##### --------------------------
#### 1) build.gradle
##### dependencies {
##### implementation 'org.apache.tomcat.embed:tomcat-embed-jasper' // 추가
##### implementation 'javax.servlet:jstl' // 추가
##### }
#### 2) application.properties jsp 경로설정
##### #JSP Path
##### spring.mvc.view.prefix: /WEB-INF/views/
##### spring.mvc.view.suffix: .jsp
##### #JSP 수정시 서버 재시작없이 바로 적용될 수 있게 설정
##### server.servlet.jsp.init-parameters.development=true
#### 3) main/webapp/WEB-INF/views 폴더생성
##### 
#### 4) View Template File 설정
##### File -> Settings -> File and Code Templates -> + 버튼 ->JSP 파일과 기본 코드 추가
##### --------------------------
### UTF-8 
##### --------------------------
##### src>main>resources>application.properties
#UTF-8 Setting <br>
spring.servlet.filter.encoding.filter-name=encodingFilter <br>
spring.servlet.filter.encoding.filter-class=org.springframework.web.filter.CharacterEncodingFilter <br>
spring.servlet.filter.encoding.init-param.encoding=UTF-8 <br>
spring.servlet.filter.encoding.init-param.forceEncoding=true <br>
spring.servlet.filter.encoding.url-pattern=/* <br>





##### -------------------------- 
### Git 연결
##### -------------------------- 
##### [참고] https://goddaehee.tistory.com/249
##### -------------------------- 
### 플러그인 
##### -------------------------- 
### DataBase Navigatoer
##### [참고] https://developer-ping9.tistory.com/172

### Tabnine 코드 자동완성 플러그인(AI 추천)
##### [참고] https://yehza.tistory.com/436

### Emmet 
##### [참고] https://blog.yijunho.com/181
##### [공식] https://emmet.io/


### Git Toolbox 
##### [참고]  https://devgoat.tistory.com/26


### 인텔리제이 단축키 학습 플러그인
##### [참고][ https://jeonghwan-kim.github.io/dev/2021/10/29/return-to-intellij.html](https://hbase.tistory.com/447)

### 괄호구분 플러그인(색상별로)
#### [참고] https://hbase.tistory.com/436

 





###
##### - 
##### - 






