package samplehazlecache;


import java.io.Serializable;

public class SampleUser implements Serializable {

private final long serialVersionUID=1L;
private String userid;
private String firstname;
public String getUserid() {
return userid;
}
public void setUserid(String userid) {
this.userid = userid;
}
public String getFirstname() {
return firstname;
}
public void setFirstname(String firstname) {
this.firstname = firstname;
}




}


