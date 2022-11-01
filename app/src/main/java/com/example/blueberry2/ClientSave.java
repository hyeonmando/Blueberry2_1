package com.example.blueberry2;

public class ClientSave {
    String nId; // 고유 번호
    String name; // 이름
    String email; //아이디-이메일
    String pwd; //비밀번호
    String birth; // 생년월일

    public String getnId() {
        return nId;
    }

    public void setnId(String nId) {
        this.nId = nId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public ClientSave(String nId,String name, String email, String pwd, String birth){

        this.nId=nId;
        this.name=name;
        this.email=email;
        this.pwd=pwd;
        this.birth=birth;
    }

}
