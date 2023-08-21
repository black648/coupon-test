* docker 설치 ( 공식 홈피에서 받는걸 추천)
  * brew install docker
  * brew link docker 
  * docker version

* mariaDB 설치
  * brew install mariadb
  * 관리자 계정 설정
    * set password for 'root'@'localhost' = password('변경할 비밀번호');

  * DB 생성
    * CREATE DATABASE coupon_test CHARACTER SET='utf8' COLLATE='utf8_bin';

  * 사용자 생성
    * CREATE USER project_user IDENTIFIED BY 'project_user';

  * 로컬 DB접근 허용
    * GRANT ALL PRIVILEGES on coupon_test.* to 'project_user'@'localhost' identified by 'project_user';
