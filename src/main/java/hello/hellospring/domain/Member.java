package hello.hellospring.domain;

public class Member {
    private Long id; //고객이 정하는 ID가 아니라 시스템이 정하는 ID로 데이터를 구분한다.
    private String name; // 회원 이름.

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
