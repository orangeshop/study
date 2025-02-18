

커뮤니티 앱 
실배포까지 준비 (KMP or rn)

결론

글마다 접근 제한이 있음

ex) 
원문 : 가 나 다 라 마바사
제한 : 가 ■ ■ ■ 마바사

가중치가 높을 수록 제한이 풀림

접근 제한을 풀려면 해당 유형의 글을 자주 읽었거나
해당 글쓴이를 자주 방문했거나 

글을 적었는데 추천수가 높다? -> 다른 글 잠금 해제 가능

FCM -> 당일 최다 득표 글을 보여줌

Member
    - id
    - name
    - email
    - password
    - created_at (datetime)
    - updated_at (datetime)

Member status
    - id 
    - Member id (FK)
    - 유저별 가중치를 어떻게 보유?
    - 글의 성향을 어떻게 체크? ai?

post
    - Member id (FK)
    - post id
    - post 내용
    - time
    - image -> 후 순위
    - **access_level (int)**  ← 0: 전부 공개, 1: 일부 마스킹, 2: 전체 잠금 등
    - created_at
    - updated_at
    

post detail
    - post id (FK)
    - geo
    - like count
    - dislike count

comment
    - id
    - post id
    - 댓글 내용
    - 댓글 시간
    - 

    